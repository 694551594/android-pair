package cn.yhq.pair.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CheckPairItem extends TwoStatePairItem<CheckPairItem> {

    public CheckPairItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckPairItem(Context context) {
        this(context, null);
    }

    @Override
    public int getWidgetLayoutResource() {
        return R.layout.pair_widget_checkbox;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        super.onBindViewHolder(viewHolder);

        viewHolder.bindResId(R.id.checkbox)
                .setChecked(this.isChecked())
                .setTag(this)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox checkBox = (CheckBox) view;
                        CheckPairItem checkPairItem = (CheckPairItem) view.getTag();
                        checkPairItem.setChecked(checkBox.isChecked());
                    }
                });

        this.setWidgetView(viewHolder.getView(R.id.checkbox));
    }
}
