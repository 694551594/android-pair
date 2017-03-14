package cn.yhq.pair.item;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;

/**
 * Created by Administrator on 2016/11/15.
 */

public class SwitchPairItem extends TwoStatePairItem<SwitchPairItem> {

    public SwitchPairItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchPairItem(Context context) {
        this(context, null);
    }

    @Override
    public int getWidgetLayoutResource() {
        return R.layout.pair_widget_switchbutton;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder) {
        super.onBindViewHolder(viewHolder);
        viewHolder.bindResId(R.id.switch_button)
                .setChecked(this.isChecked())
                .setTag(this)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SwitchCompat switchButton = (SwitchCompat) view;
                        SwitchPairItem switchPairItem = (SwitchPairItem) view.getTag();
                        switchPairItem.setChecked(switchButton.isChecked());
                    }
                });
    }
}
