package cn.yhq.pair.adapter.provider;

import android.view.View;
import android.widget.CheckBox;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.CheckPairItem;
import cn.yhq.pair.item.PairCatalog;

/**
 * 开关item视图提供器
 *
 * @author Yanghuiqiang 2015-6-29
 */
public class CheckboxItemViewProvider extends BaseChildItemViewProvider<CheckPairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int groupPosition, PairCatalog groupEntity, int childPosition, CheckPairItem childEntity) {
        viewHolder.bindResId(R.id.checkbox)
                .setChecked(childEntity.isChecked())
                .setTag(childEntity)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CheckBox checkBox = (CheckBox) view;
                        CheckPairItem checkPairItem = (CheckPairItem) view.getTag();
                        checkPairItem.setChecked(checkBox.isChecked());
                    }
                });

    }

    @Override
    public int getItemViewStubLayoutId() {
        return R.layout.checkbox;
    }
}
