package johnnie.com.jcrecyclerviewhelper.featureList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import johnnie.com.jccommon.simpleViewHolder.JCSimpleViewHolder;
import johnnie.com.jccommon.simpleViewHolder.JCSimpleViewHolderListener;
import johnnie.com.jcrecyclerviewhelper.adapter.IndexPath;
import johnnie.com.jcrecyclerviewhelper.adapter.JCMultiSectionAdapter;

/**
 * Created by Johnnie on 19/11/17.
 */

public class JCRecyclerViewSubFeatureAdapter extends JCMultiSectionAdapter {

    private ArrayList<String> titles;
    private JCSimpleViewHolderListener listener;

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    public void setListener(JCSimpleViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    protected boolean shouldRenderCells() {
        return titles != null;
    }

    @Override
    protected boolean isDataEmpty() {
        return titles != null && titles.isEmpty();
    }

    @Override
    public int getRowCountInSection(int section) {
        return titles == null ? 0 : titles.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCell(ViewGroup parent, int viewType) {
        return JCSimpleViewHolder.newInstance(parent);
    }

    @Override
    protected void onBindCell(RecyclerView.ViewHolder holder, final IndexPath indexPath, int viewType) {
        JCSimpleViewHolder vh = (JCSimpleViewHolder) holder;
        vh.txtTitle.setText(titles.get(indexPath.row));
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClicked(titles.get(indexPath.row));
                }
            }
        });
    }
}