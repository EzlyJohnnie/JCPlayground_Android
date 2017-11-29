package johnnie.com.jcplayground_android.ui.RecyclerViewDemo;

import java.util.ArrayList;

/**
 * Created by Johnnie on 19/11/17.
 */

public class JCRecyclerViewHelperConfig {
    //first tier
    public final static ArrayList<String> RECYCLER_VIEW_FEATURE_LIST;
    public final static String RECYCLER_VIEW_FEATURE_LINEAR_DEMO = "LinearLayout demo";
    public final static String RECYCLER_VIEW_FEATURE_GRID_DEMO = "GridLayout demo";


    static{
        RECYCLER_VIEW_FEATURE_LIST = new ArrayList<>();
        RECYCLER_VIEW_FEATURE_LIST.add(RECYCLER_VIEW_FEATURE_LINEAR_DEMO);
        RECYCLER_VIEW_FEATURE_LIST.add(RECYCLER_VIEW_FEATURE_GRID_DEMO);
    }
}
