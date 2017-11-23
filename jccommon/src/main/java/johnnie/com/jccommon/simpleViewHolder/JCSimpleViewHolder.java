package johnnie.com.jccommon.simpleViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import johnnie.com.jccommon.R;


/**
 * Created by Johnnie on 19/11/17.
 */
public class JCSimpleViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTitle;

    public static JCSimpleViewHolder newInstance(ViewGroup parent){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jc_item_simple_view_holder, parent, false);
        return new JCSimpleViewHolder(view);
    }

    public JCSimpleViewHolder(View view) {
        super(view);
        txtTitle = (TextView) view.findViewById(R.id.txt_title);
    }
}