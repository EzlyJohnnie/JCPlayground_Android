package johnnie.com.jcrecyclerviewhelper.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Johnnie on 20/06/17.
 */

public abstract class JCMultiSectionAdapter extends RecyclerView.Adapter {

    public static final int VIEW_TYPE_SECTION_HEADER = 1989022001;
    public static final int VIEW_TYPE_CELL = 1989022002;
    public static final int VIEW_TYPE_EMPTY_PLACE_HOLDER = 1989022003;

    private ArrayList<Integer> headerViewType = new ArrayList<>();
    private ArrayList<Integer> cellViewType = new ArrayList<>();

    private WeakReference<RecyclerView> mRecyclerViewRef;

    public RecyclerView getRecyclerView() {
        RecyclerView recyclerView = null;
        if(mRecyclerViewRef != null && mRecyclerViewRef.get() != null){
            recyclerView = mRecyclerViewRef.get();
        }
        return recyclerView;
    }

    protected void registerCellViewType(int[] viewTypes){
        for(int viewType : viewTypes){
            cellViewType.add(viewType);
        }
    }

    protected void registerHeaderViewType(int[] viewTypes){
        for(int viewType : viewTypes){
            headerViewType.add(viewType);
        }
    }

    /**
     * used when first time init the recycler view
     * recycler view will show an empty cell by default when init
     *
     * @return false -> no cell will display
     *          always return true if empty cell is not used
     *          normally, return data != null;
     */
    protected abstract boolean shouldRenderCells();

    /**
     * if your data set is empty
     * @return true -> will show empty cell
     *          normally, return data == null || data.isEmpty();
     */
    protected abstract boolean isDataEmpty();

    public int getSectionCount(){return 1;}
    abstract public int getRowCountInSection(int section);

    protected int getEmptyCellViewType(){return VIEW_TYPE_EMPTY_PLACE_HOLDER;}
    protected int getSectionHeaderViewType(IndexPath indexPath){return VIEW_TYPE_SECTION_HEADER;}
    protected int getCellViewType(IndexPath indexPath){return VIEW_TYPE_CELL;}

    protected RecyclerView.ViewHolder onCreateEmptyPlaceholder(ViewGroup parent){
        View view = new LinearLayout(parent.getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(lp);
        return new DummySectionHeaderViewHolder(view);
    }
    protected RecyclerView.ViewHolder onCreateSectionHeader(ViewGroup parent, int viewType){
        View view = new LinearLayout(parent.getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(lp);
        return new DummyEmptyPlaceholderViewHolder(view);
    }

    protected abstract RecyclerView.ViewHolder onCreateCell(ViewGroup parent, int viewType);

    protected void onBindEmptyView(int viewType){}
    protected void onBindSectionHeader(RecyclerView.ViewHolder holder, IndexPath indexPath, int viewType){}
    protected abstract void onBindCell(RecyclerView.ViewHolder holder, IndexPath indexPath, int viewType);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerViewRef = new WeakReference<RecyclerView>(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        if(isDataEmpty()){
            viewType = getEmptyCellViewType();
        }
        else{
            IndexPath indexPath = findIndexPath(position);
            if(indexPath.row < 0){
                viewType = getSectionHeaderViewType(indexPath);
            }
            else{
                viewType = getCellViewType(indexPath);
            }
        }

        return viewType;
    }

    public IndexPath findIndexPath(int position){
        IndexPath indexPath = null;
        if(getTotalCount() > 0) {
            int sectionCount = getSectionCount();
            int totalCount = 0;
            for (int section = 0; section < sectionCount; section++) {
                int rowCount = getRowCountInSection(section);
                totalCount += rowCount + 1;

                if (totalCount > position) {
                    indexPath = new IndexPath();
                    totalCount -= (rowCount + 1);
                    int row = position - totalCount;
                    indexPath.section = section;
                    indexPath.row = row - 1;
                    break;
                }
            }
        }

        return indexPath;
    }

    private int findPosition(IndexPath indexPath){
        int position = -1;

        if(indexPath != null && indexPath.section >= 0 && indexPath.row >= 0){
            position = 0;
            for(int section = 0; section <= indexPath.section; section++){
                if(section < indexPath.section){
                    position += getRowCountInSection(section) + 1;
                }
                else{
                    position += indexPath.row + 1;
                }
            }
            return position;
        }

        return  position;
    }

    protected boolean isItemVisibleForIndexPath(IndexPath indexPath){
        boolean isVisible = false;

        if(getRecyclerView() != null){
            int position = findPosition(indexPath);
            int firstVisibleItem = findFirstVisibleItemPosition(getRecyclerView().getLayoutManager());
            int lastVisibleItem = findLastVisibleItemPosition(getRecyclerView().getLayoutManager());

            if(firstVisibleItem >= 0  && lastVisibleItem >= 0 && lastVisibleItem > firstVisibleItem //first and last position is valid
                    && position >= firstVisibleItem && position <= lastVisibleItem)
            {
                isVisible = true;
            }
        }

        return isVisible;
    }

    private int findFirstVisibleItemPosition(RecyclerView.LayoutManager layoutManager){
        int position = -1;
        if(layoutManager instanceof LinearLayoutManager){
            position = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
        }
        return position;
    }

    private int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager){
        int position = -1;
        if(layoutManager instanceof LinearLayoutManager){
            position = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
        }
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        if(viewType == VIEW_TYPE_SECTION_HEADER || headerViewType.contains(viewType)){
            viewHolder = onCreateSectionHeader(parent, viewType);
        }
        else if(viewType == VIEW_TYPE_EMPTY_PLACE_HOLDER){
            viewHolder = onCreateEmptyPlaceholder(parent);
        }
        else if(viewType == VIEW_TYPE_CELL || cellViewType.contains(viewType)){
            viewHolder = onCreateCell(parent, viewType);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        IndexPath indexPath = findIndexPath(position);

        if(viewType == VIEW_TYPE_SECTION_HEADER || headerViewType.contains(viewType)){
            onBindSectionHeader(holder, indexPath, viewType);
        }
        else if(viewType == VIEW_TYPE_EMPTY_PLACE_HOLDER){
            onBindEmptyView(viewType);
        }
        else if(viewType == VIEW_TYPE_CELL || cellViewType.contains(viewType)){
            onBindCell(holder, indexPath, viewType);
        }
    }

    @Override
    public int getItemCount() {
        int count = getTotalCount();
        if(!shouldRenderCells()){
            count = 0;
        }
        else if(isDataEmpty()){
            count = 1;
        }
        return count;
    }

    private int getTotalCount(){
        int count = 0;
        int sectionCount = getSectionCount();

        for(int section = 0; section < sectionCount; section++){
            count += getRowCountInSection(section) + 1;
        }

        return count;
    }

    public void notifyItemChanged(IndexPath indexPath){
        int position = findPosition(indexPath);
        notifyItemChanged(position);
    }

    public void deleteItemsAtIndexPaths(ArrayList<IndexPath> indexPathsToDelete){
        for(IndexPath indexPath : indexPathsToDelete){
            notifyItemRemoved(findPosition(indexPath));
        }
    }

    private boolean isSectionHeader(int position) {
        int viewType = getItemViewType(position);
        return headerViewType != null && headerViewType.contains(viewType) || viewType == VIEW_TYPE_SECTION_HEADER;
    }

    private boolean isEmptyCell(int position) {
        return getItemViewType(position) == VIEW_TYPE_EMPTY_PLACE_HOLDER;
    }

    private boolean isCell(int position){
        int viewType = getItemViewType(position);
        return cellViewType != null && cellViewType.contains(viewType) || viewType == VIEW_TYPE_CELL;
    }






    private class DummySectionHeaderViewHolder extends RecyclerView.ViewHolder{
        public DummySectionHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }





    private class DummyEmptyPlaceholderViewHolder extends RecyclerView.ViewHolder{
        public DummyEmptyPlaceholderViewHolder(View itemView) {
            super(itemView);
        }
    }





    public class JCSpanSizeLookup extends GridLayoutManager.SpanSizeLookup{

        private int column;

        public JCSpanSizeLookup(int column) {
            this.column = column;
        }

        @Override
        public int getSpanSize(int position) {
            if(isSectionHeader(position)){
                return spanForSectionHeader(findIndexPath(position));
            }
            else if(isEmptyCell(position)){
                return spanForEmptyCell();
            }
            else if(isCell(position)){
                return spanForCell(findIndexPath(position));
            }
            return 1;
        }

        public int spanForSectionHeader(IndexPath indexPath){return column;}
        public int spanForEmptyCell(){return column;}
        public int spanForCell(IndexPath indexPath){return 1;}

    }

}
