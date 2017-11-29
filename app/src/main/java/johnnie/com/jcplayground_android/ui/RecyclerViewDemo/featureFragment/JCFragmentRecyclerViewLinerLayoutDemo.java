package johnnie.com.jcplayground_android.ui.RecyclerViewDemo.featureFragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import johnnie.com.jccommon.BaseUI.JCBaseFeatureFragment;
import johnnie.com.jcplayground_android.R;
import johnnie.com.jcrecyclerviewhelper.adapter.IndexPath;
import johnnie.com.jcrecyclerviewhelper.adapter.JCMultiSectionAdapter;

/**
 * Created by Johnnie on 26/11/17.
 */

public class JCFragmentRecyclerViewLinerLayoutDemo extends JCBaseFeatureFragment {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(johnnie.com.jcrecyclerviewhelper.R.layout.jc_recycler_view_fragment, container, false);
        setHasOptionsMenu(true);
        init(root, savedInstanceState);
        return root;
    }

    private void init(View root, Bundle savedInstanceState) {
        ButterKnife.bind(this, root);
        initData();
        initRecyclerView();
    }

    private void initData() {

    }

    private void initRecyclerView() {

    }

    @Override
    public boolean onBackClicked() {
        return false;
    }


    private class Adapter extends JCMultiSectionAdapter{

        @Override
        protected boolean shouldRenderCells() {
            return false;
        }

        @Override
        protected boolean isDataEmpty() {
            return false;
        }

        @Override
        public int getRowCountInSection(int section) {
            return 0;
        }

        @Override
        protected RecyclerView.ViewHolder onCreateCell(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        protected void onBindCell(RecyclerView.ViewHolder holder, IndexPath indexPath, int viewType) {

        }
    }
}
