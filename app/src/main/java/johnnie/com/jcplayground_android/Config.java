package johnnie.com.jcplayground_android;

import java.util.ArrayList;

/**
 * Created by Johnnie on 5/11/17.
 */

public class Config {
    //first tier
    public final static ArrayList<String> DEMO_LIST;
    public final static String DEMO_RECYCLER_VIEW = "Recycler view related";
    public final static String DEMO_OTHER = "Other";


    static{
        DEMO_LIST = new ArrayList<>();
        DEMO_LIST.add(DEMO_RECYCLER_VIEW);
        DEMO_LIST.add(DEMO_OTHER);
    }
}

