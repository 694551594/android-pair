package cn.yhq.pair.adapter.recyclerview;

import android.view.View;
import android.widget.Switch;

import cn.yhq.adapter.recycler.ViewHolder;
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
        viewHolder.bindResId(R.id.switch_button)
                .setChecked(childEntity.isChecked())
                .setTag(childEntity)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Switch switchButton = (Switch) view;
                        SwitchPairItem switchPairItem = (SwitchPairItem) view.getTag();
                        switchPairItem.setChecked(switchButton.isChecked());
                    }
                });

    }

    @Override
    public int getItemViewStubLayoutId() {
        return R.layout.switchbutton;
    }
}
