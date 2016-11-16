package cn.yhq.pair.adapter.provider;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.SwitchPairItem;

/**
 * 开关item视图提供器
 *
 * @author Yanghuiqiang 2015-6-29
 */
public class SwitchItemViewProvider extends BaseChildItemViewProvider<SwitchPairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int groupPosition, PairCatalog groupEntity, int childPosition, SwitchPairItem childEntity) {
        Switch switchButton = viewHolder.getView(R.id.switch_button);
        viewHolder.bindResId(R.id.switch_button)
                .setVisibility(View.VISIBLE)
                .setChecked(childEntity.isChecked());
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }
}
