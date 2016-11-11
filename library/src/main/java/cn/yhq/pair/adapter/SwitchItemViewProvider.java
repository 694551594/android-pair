package cn.yhq.pair.adapter;

import android.view.View;
import cn.android.developers.sdk.R;
import cn.developer.sdk.adapter.IExpandableCheckedChangeListener;
import cn.developer.sdk.adapter.core.ViewHolder;
import cn.developer.sdk.pair.core.PairGroup;
import cn.developer.sdk.pair.core.PairItem;
import cn.developer.sdk.pair.core.SwitchValueItem.SwitchValue;

import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChanged;

/**
 * 开关item视图提供器
 * 
 * @author Yanghuiqiang 2015-6-29
 * 
 */
public class SwitchItemViewProvider extends BaseChildItemViewProvider implements OnToggleChanged {

  @Override
  public void setupView(ViewHolder viewHolder, int groupPosition, PairGroup groupEntity,
      int childPosition, PairItem childEntity) {
    super.setupView(viewHolder, groupPosition, groupEntity, childPosition, childEntity);

    SwitchValue textValue = childEntity.getValueItem().getValue();

    ToggleButton valueToggleButton = viewHolder.getView(R.id.sb_pair_item_value);
    valueToggleButton.setTag(R.id.tag_item_position, childPosition);
    valueToggleButton.setTag(R.id.tag_item_group_position, groupPosition);
    valueToggleButton.setVisibility(View.VISIBLE);
    valueToggleButton.setOnToggleChanged(SwitchItemViewProvider.this);

    if (textValue.isChecked()) {
      valueToggleButton.setToggleOn();
    } else {
      valueToggleButton.setToggleOff();
    }

  }

  @Override
  public void onToggle(ToggleButton toggleButton, boolean on) {
    int childPosition = (Integer) toggleButton.getTag(R.id.tag_item_position);
    int groupPosition = (Integer) toggleButton.getTag(R.id.tag_item_group_position);
    if (onCheckedChangeListener != null) {
      onCheckedChangeListener.onCheckedChanged(groupPosition, childPosition, on);
    }
  }

  protected IExpandableCheckedChangeListener onCheckedChangeListener;

  public void setOnCheckedChangeListener(IExpandableCheckedChangeListener onCheckedChangeListener) {
    this.onCheckedChangeListener = onCheckedChangeListener;
  }


}
