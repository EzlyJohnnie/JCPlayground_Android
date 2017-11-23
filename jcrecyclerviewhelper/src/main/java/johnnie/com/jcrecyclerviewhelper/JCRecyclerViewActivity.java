package johnnie.com.jcrecyclerviewhelper;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import johnnie.com.jccommon.BaseUI.JCBaseActivity;
import johnnie.com.jcrecyclerviewhelper.featureList.JCRecyclerViewSubFeatureListFragment;

public class JCRecyclerViewActivity extends JCBaseActivity {

    private final static String KEY_FRAGMENT = "key_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jc_recycler_view_helper_main_activity);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        loadData(savedInstanceState);
        initViewComponents();
        initView(savedInstanceState);
    }

    private void loadData(Bundle savedInstanceState) {

    }

    private void initViewComponents() {

    }

    private void initView(Object savedInstanceState) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(KEY_FRAGMENT);
        if(fragment == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, JCRecyclerViewSubFeatureListFragment.newInstance(), KEY_FRAGMENT)
                    .commit();
        }
    }
}
