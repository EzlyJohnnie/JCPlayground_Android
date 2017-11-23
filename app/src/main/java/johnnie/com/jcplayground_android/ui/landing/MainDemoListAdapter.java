package johnnie.com.jcplayground_android.ui.landing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import johnnie.com.jccommon.simpleViewHolder.JCSimpleViewHolder;
import johnnie.com.jccommon.simpleViewHolder.JCSimpleViewHolderListener;
import johnnie.com.jcplayground_android.R;
import johnnie.com.jcrecyclerviewhelper.adapter.IndexPath;
import johnnie.com.jcrecyclerviewhelper.adapter.JCMultiSectionAdapter;

/**
 * Created by Johnnie on 5/11/17.
 */

public class MainDemoListAdapter extends JCMultiSectionAdapter{

    private ArrayList<String> demoList;
    private JCSimpleViewHolderListener listener;

    public MainDemoListAdapter() {}

    public void setDemoList(ArrayList<String> demoList){
        this.demoList = demoList;
        notifyDataSetChanged();
    }

    public void setListener(JCSimpleViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    protected boolean shouldRenderCells() {
        return true;
    }

    @Override
    protected boolean isDataEmpty() {
        return demoList == null || demoList.isEmpty();
    }

    @Override
    public int getRowCountInSection(int section) {
        return demoList == null ? 0 : demoList.size();
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCell(ViewGroup parent, int viewType) {
        return JCSimpleViewHolder.newInstance(parent);
    }

    @Override
    protected void onBindCell(RecyclerView.ViewHolder holder, final IndexPath indexPath, int viewType) {
        JCSimpleViewHolder vh = (JCSimpleViewHolder) holder;
        vh.txtTitle.setText(demoList.get(indexPath.row));
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClicked(demoList.get(indexPath.row));
                }
            }
        });
    }
}
