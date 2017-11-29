package johnnie.com.jcplayground_android.ui.RecyclerViewDemo.featureList;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import johnnie.com.jccommon.BaseUI.JCBaseFeatureFragment;
import johnnie.com.jccommon.simpleViewHolder.JCSimpleViewHolderListener;
import johnnie.com.jcplayground_android.ui.RecyclerViewDemo.JCRecyclerViewHelperConfig;
import johnnie.com.jcrecyclerviewhelper.R;

/**
 * Created by Johnnie on 12/10/17.
 */

public class JCRecyclerViewSubFeatureListFragment extends JCBaseFeatureFragment implements JCSimpleViewHolderListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private JCRecyclerViewSubFeatureAdapter adapter;

    public static JCRecyclerViewSubFeatureListFragment newInstance(){
        JCRecyclerViewSubFeatureListFragment fragment = new JCRecyclerViewSubFeatureListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.jc_recycler_view_fragment, container, false);
        setHasOptionsMenu(true);
        init(root, savedInstanceState);
        return root;
    }

    private void init(View root, Bundle savedInstanceState) {
        loadData(savedInstanceState == null ? getArguments() : savedInstanceState);
        initViewComponents(root);

        if(savedInstanceState == null) {
            initView();
        }
    }

    private void loadData(Bundle bundle) {
        if(bundle != null){

        }
    }

    private void initViewComponents(View root) {
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
    }

    private void initView() {
        showLoadingView(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadList();
            }
        });
        adapter = new JCRecyclerViewSubFeatureAdapter();
        adapter.setListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadList();
    }

    private void loadList() {
        adapter.setTitles(JCRecyclerViewHelperConfig.RECYCLER_VIEW_FEATURE_LIST);
        showLoadingView(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        int menuRes = R.menu.jc_recycler_view_menu;
        inflater.inflate(menuRes, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onBackClicked() {
        return false;
    }

    private void showLoadingView(final boolean isShow){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(isShow);
            }
        });
    }

    @Override
    public void onItemClicked(String title) {

    }
}
