package cn.yhq.pair.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 * Created by Yanghuiqiang on 2016/11/16.
 */

public class PairView extends RecyclerView {

    public PairView(Context context) {
        super(context);
        initView();
    }

    public PairView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PairView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void initView() {
        this.setLayoutManager(new LinearLayoutManager(getContext()));
        this.setVerticalScrollBarEnabled(false);
        this.setBackgroundResource(android.R.color.white);
    }

}
