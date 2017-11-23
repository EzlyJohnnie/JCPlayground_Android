package johnnie.com.jcplayground_android.ui.landing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import johnnie.com.jccommon.BaseUI.JCBaseFeatureFragment;
import johnnie.com.jccommon.simpleViewHolder.JCSimpleViewHolderListener;
import johnnie.com.jcplayground_android.Config;
import johnnie.com.jcplayground_android.R;
import johnnie.com.jcrecyclerviewhelper.featureList.JCRecyclerViewSubFeatureListFragment;

/**
 * Created by Johnnie on 5/11/17.
 */

public class MainDemoListFragment extends JCBaseFeatureFragment implements JCSimpleViewHolderListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private MainDemoListAdapter adapter;

    public static MainDemoListFragment newInstance(){
        MainDemoListFragment fragment = new MainDemoListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_demo_list, container, false);
        setHasOptionsMenu(true);
        init(root, savedInstanceState);
        return root;
    }

    private void init(View root, Bundle savedInstanceState) {
        initViewComponents(root);
        initToolbar();
        initRecyclerView();
    }

    private void initViewComponents(View root) {
        refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) root.findViewById(R.id.demo_list);
    }

    private void initToolbar() {
        setTitle("JCPlayground Demo");
    }

    private void initRecyclerView() {
        showLoadingView(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
            }
        });

        adapter = new MainDemoListAdapter();
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadList();
    }

    private void loadList() {
        adapter.setDemoList(Config.DEMO_LIST);
        showLoadingView(false);
    }

    private void showLoadingView(final boolean isShow){
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(isShow);
            }
        });
    }


    @Override
    public boolean onBackClicked() {
        return false;
    }





    @Override
    public void onItemClicked(String demoTitle) {
        Log.e("asd", demoTitle);
        Fragment fragment = null;
        if(demoTitle.equals(Config.DEMO_RECYCLER_VIEW)){
            fragment = JCRecyclerViewSubFeatureListFragment.newInstance();
        }
        else if(demoTitle.equals(Config.DEMO_OTHER)){

        }

        if(fragment != null) {
            push(fragment, true);
        }

    }
}
