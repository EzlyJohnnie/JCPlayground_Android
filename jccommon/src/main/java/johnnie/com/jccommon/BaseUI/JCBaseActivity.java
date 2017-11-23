package johnnie.com.jccommon.BaseUI;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Johnnie on 12/10/17.
 */

public class JCBaseActivity extends AppCompatActivity {

    protected static final String KEY_MAIN_FRAGMENT = "key_main_fragment";

    @Override
    public void onBackPressed(){
        boolean hasHandle = false;
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(KEY_MAIN_FRAGMENT);
        if(fragment != null && fragment instanceof JCBackableFragment){
            hasHandle = ((JCBackableFragment)fragment).onBackClicked();
        }

        if(!hasHandle) {
            super.onBackPressed();
        }
    }
}
