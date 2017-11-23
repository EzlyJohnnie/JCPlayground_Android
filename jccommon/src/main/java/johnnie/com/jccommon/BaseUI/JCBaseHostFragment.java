package johnnie.com.jccommon.BaseUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import johnnie.com.jccommon.R;

/**
 * Created by Johnnie on 5/11/17.
 */

public abstract class JCBaseHostFragment extends JCBaseFragment {

    protected static final String KEY_FRAGMENT = "key_fragment";
    protected static final String KEY_TOP_FRAGMENT = "key_topFragment";

    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.jc_base_host_fragment, container, false);
        setHasOptionsMenu(true);
        init(root, savedInstanceState);
        return root;
    }

    @Override
    public void onResume(){
        super.onResume();
        ((JCBaseActivity)getActivity()).setSupportActionBar(toolbar);
    }

    protected void init(View root, Bundle savedInstanceState){
        initViewComponents(root);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void initViewComponents(View root){
        toolbar = (Toolbar) root.findViewById(R.id.jc_toolbar);
    }

    public void push(Fragment fragment, boolean animated){
        int in = animated ? R.anim.slide_right_in : 0;
        int out = animated ? R.anim.slide_left_out : 0;
        int popIn = animated ? R.anim.slide_left_in : 0;
        int popOut = animated ? R.anim.slide_right_out : 0;

        pushFragment(fragment, in, out, popIn, popOut);
    }

    public void pushFragment(Fragment fragment, int inAnim, int outAnim, int popEnterAnim, int popExitAnim){
        getChildFragmentManager().beginTransaction()
                .setCustomAnimations(inAnim, outAnim, popEnterAnim, popExitAnim)
                .replace(R.id.container, fragment, KEY_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public void replace(Fragment fragment){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, KEY_FRAGMENT)
                .commit();
    }

    public boolean pop(){
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            getChildFragmentManager().popBackStackImmediate();
            return true;
        }
        return false;
    }

    public void presentFragmentOnTop(Fragment fragment){
        int in = R.anim.slide_bottom_in;
        int out = 0;
        int popIn = 0;
        int popOut = R.anim.slide_bottom_out;

        pushTopFragment(fragment, in, out, popIn, popOut);
    }

    public void fadeInFragmentOnTop(Fragment fragment){
        int in = R.anim.fade_in;
        int out = 0;
        int popIn = R.anim.fade_out;
        int popOut = 0;

        pushTopFragment(fragment, in, out, popIn, popOut);
    }

    public void pushTopFragment(Fragment fragment, int inAnim, int outAnim, int popEnterAnim, int popExitAnim){
        getChildFragmentManager().beginTransaction()
                .setCustomAnimations(inAnim, outAnim, popEnterAnim, popExitAnim)
                .replace(R.id.top_container, fragment, KEY_TOP_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    public boolean dismissPresentedFragment(){
        return pop();
    }

    @Override
    public boolean onBackClicked() {
        boolean hasHandle = false;

        //if has top fragment
        Fragment presentingFragment = getChildFragmentManager().findFragmentByTag(KEY_TOP_FRAGMENT);
        if(presentingFragment != null){
            //pass back event to presented fragment
            if(presentingFragment instanceof JCBackableFragment){
                hasHandle = ((JCBackableFragment) presentingFragment).onBackClicked();
            }

            //if presented fragment doesn't handle back event, dismiss presented fragment
            if(!hasHandle){
                dismissPresentedFragment();
                hasHandle = true;
            }
        }

        //if no presented fragment, pass event to bottom fragment
        Fragment currentFragment = getChildFragmentManager().findFragmentByTag(KEY_FRAGMENT);
        if(!hasHandle && currentFragment != null && currentFragment instanceof JCBackableFragment){
            hasHandle = ((JCBackableFragment)currentFragment).onBackClicked();
        }

        //if bottom fragment doesn't handle event, try pop
        //if no more back stack, pass event back to parent
        if(!hasHandle){
            hasHandle = pop();
        }

        return hasHandle;
    }
}
