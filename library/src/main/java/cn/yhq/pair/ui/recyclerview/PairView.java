package cn.yhq.pair.ui.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import cn.yhq.pair.adapter.recyclerview.PairAdapter;
import cn.yhq.pair.item.IPair;
import cn.yhq.pair.utils.DisplayUtils;


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

            int position = state.getTargetScrollPosition();

            if (position == RecyclerView.NO_POSITION) {
                return;
            }

            int count = state.getItemCount();
            PairAdapter adapter = (PairAdapter) parent.getAdapter();
            IPair pair = adapter.getItem(position);
            if (pair.getType() == IPair.Type.CATALOG) {
                return;
            }
            if (position == count - 1) {
                return;
            }
            if (position < count - 1) {
                IPair nextPair = adapter.getItem(position + 1);
                if (nextPair.getType() == IPair.Type.CATALOG) {
                    return;
                }
            }

            final View child = parent.getChildAt(position);

            if (child == null) {
                return;
            }
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left + DisplayUtils.dp2Px(parent.getContext(), 16), top, right - DisplayUtils.dp2Px(parent.getContext(), 16), bottom);
            mDivider.draw(c);
        }


        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
    }

}
