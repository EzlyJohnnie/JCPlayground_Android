package johnnie.com.jcrecyclerviewhelper.adapter;

/**
 * Created by Johnnie on 20/06/17.
 */

public class IndexPath {
    public int section = -1;
    public int row = -1;

    public IndexPath() {
    }

    public IndexPath(int section, int row) {
        this.section = section;
        this.row = row;
    }
}
