package cn.yhq.pair.adapter.recyclerview;

import android.support.v7.widget.SwitchCompat;
import android.view.View;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.SwitchPairItem;

/**
 * 开关item视图提供器
 *
 * @author Yanghuiqiang 2015-6-29
 */
public class SwitchItemViewProvider extends BaseItemViewProvider<SwitchPairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int position, SwitchPairItem childEntity) {
        super.setupItemView(viewHolder, position, childEntity);

        viewHolder.bindResId(R.id.switch_button)
                .setChecked(childEntity.isChecked())
                .setTag(childEntity)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SwitchCompat switchButton = (SwitchCompat) view;
                        SwitchPairItem switchPairItem = (SwitchPairItem) view.getTag();
                        switchPairItem.setChecked(switchButton.isChecked());
                    }
                });

    }

    @Override
    public int getItemViewStubLayoutId() {
        return R.layout.pair_widget_switchbutton;
    }
}
