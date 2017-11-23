package johnnie.com.jccommon.BaseUI;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MenuItem;

/**
 * Created by Johnnie on 12/10/17.
 */

public abstract class JCBaseFragment extends Fragment implements JCBackableFragment{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(isRetainInstance());
    }

    protected boolean isRetainInstance() {
        return true;
    }

    protected void setupIconColorForMenuItem(MenuItem item, int color){
        if (item != null) {
            setupIconColorForDrawable(item.getIcon(), color);
        }
    }

    protected void setupIconColorForDrawable(Drawable drawable, int color){
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }
}
