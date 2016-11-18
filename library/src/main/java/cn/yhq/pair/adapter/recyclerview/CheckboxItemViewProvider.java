package cn.yhq.pair.adapter.recyclerview;

import android.view.View;
import android.widget.CheckBox;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.CheckPairItem;

/**
 * 开关item视图提供器
 *
 * @author Yanghuiqiang 2015-6-29
 */
public class CheckboxItemViewProvider extends BaseItemViewProvider<CheckPairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int position, CheckPairItem childEntity) {
        super.setupItemView(viewHolder, position, childEntity);
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
