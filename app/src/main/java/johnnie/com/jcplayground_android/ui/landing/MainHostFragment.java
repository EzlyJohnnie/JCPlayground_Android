package johnnie.com.jcplayground_android.ui.landing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import johnnie.com.jccommon.BaseUI.JCBaseHostFragment;

/**
 * Created by Johnnie on 5/11/17.
 */

public class MainHostFragment extends JCBaseHostFragment {

    public static MainHostFragment newInstance(){
        MainHostFragment fragment = new MainHostFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(johnnie.com.jccommon.R.layout.jc_base_host_fragment, container, false);
        setHasOptionsMenu(true);
        init(root, savedInstanceState);
        return root;
    }

    @Override
    protected void init(View root, Bundle savedInstanceState){
        super.init(root, savedInstanceState);
        replace(MainDemoListFragment.newInstance());
    }
}
