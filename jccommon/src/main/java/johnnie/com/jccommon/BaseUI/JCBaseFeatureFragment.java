package johnnie.com.jccommon.BaseUI;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import johnnie.com.jccommon.R;

/**
 * Created by Johnnie on 5/11/17.
 */

public abstract class JCBaseFeatureFragment extends JCBaseFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //if parent is JCBaseHostFragment, and back stack > 1, set back button
        Fragment parentFragment = getParentFragment();
        if(parentFragment instanceof JCBaseHostFragment && parentFragment.getChildFragmentManager().getBackStackEntryCount() > 0){
            setNavigationIcon(getContext().getResources().getDrawable(R.drawable.jc_back));
            setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop();
                }
            });
        }
        else{
            setNavigationIcon(null);
            setNavigationOnClickListener(null);
        }

    }

    public Toolbar getToolbar(){
        if(getParentFragment() != null && getParentFragment() instanceof JCBaseHostFragment){
            return ((JCBaseHostFragment)getParentFragment()).getToolbar();
        }

        return  null;
    }

    protected void setTitle(String title){
        if(getToolbar() != null){
            getToolbar().setTitle(title);
        }
    }

    /**
     * should call after {@link #onViewCreated(View view, Bundle savedInstanceState)}
     * @param drawable
     */
    protected void setNavigationIcon(Drawable drawable){
        if(getToolbar() != null){
            getToolbar().setNavigationIcon(drawable);
        }
    }

    /**
     * should call after {@link #onViewCreated(View view, Bundle savedInstanceState)}
     * @param onClickListener
     */
    protected void setNavigationOnClickListener(View.OnClickListener onClickListener){
        if(getToolbar() != null){
            getToolbar().setNavigationOnClickListener(onClickListener);
        }
    }

    public void push(Fragment fragment, boolean animated){
        if(getParentFragment() != null && getParentFragment() instanceof JCBaseHostFragment){
            ((JCBaseHostFragment)getParentFragment()).push(fragment, animated);
        }
    }

    public void replace(Fragment fragment){
        if(getParentFragment() != null && getParentFragment() instanceof JCBaseHostFragment){
            ((JCBaseHostFragment)getParentFragment()).replace(fragment);
        }
    }

    public boolean pop(){
        if(getParentFragment() != null && getParentFragment() instanceof JCBaseHostFragment){
            return ((JCBaseHostFragment)getParentFragment()).pop();
        }

        return false;
    }

    public void presentFragmentOnTop(Fragment fragment){
        if(getParentFragment() != null && getParentFragment() instanceof JCBaseHostFragment){
            ((JCBaseHostFragment)getParentFragment()).presentFragmentOnTop(fragment);
        }
    }

    public void fadeInFragmentOnTop(Fragment fragment){
        if(getParentFragment() != null && getParentFragment() instanceof JCBaseHostFragment){
            ((JCBaseHostFragment)getParentFragment()).fadeInFragmentOnTop(fragment);
        }
    }

    public void pushFragment(Fragment fragment, int inAnim, int outAnim, int popEnterAnim, int popExitAnim){
        if(getParentFragment() != null && getParentFragment() instanceof JCBaseHostFragment){
            ((JCBaseHostFragment)getParentFragment()).pushFragment(fragment, inAnim, outAnim, popEnterAnim, popExitAnim);
        }
    }
}
