package cn.yhq.pair.ui.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;


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
        // this.addItemDecoration(new PairView.DividerItemDecoration(this.getContext()));
    }

    public static class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        private Drawable mDivider;

        public DividerItemDecoration(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, State state) {
            super.onDraw(c, parent, state);
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin +
                        Math.round(ViewCompat.getTranslationY(child));
                final int bottom = top + mDivider.getIntrinsicHeight();
                int offset = 0;
                // DisplayUtils.dp2Px(parent.getContext(), 16);
                mDivider.setBounds(left + offset, top, right - offset, bottom);
                mDivider.draw(c);
            }
        }


        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
    }

}
