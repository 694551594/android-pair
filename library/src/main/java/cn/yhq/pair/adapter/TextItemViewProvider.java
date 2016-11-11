package cn.yhq.pair.adapter;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import cn.android.developers.sdk.R;
import cn.developer.sdk.adapter.core.ViewHolder;
import cn.developer.sdk.pair.core.BaseValueItem.ValueType;
import cn.developer.sdk.pair.core.FieldValueItem.FieldValue;
import cn.developer.sdk.pair.core.PairGroup;
import cn.developer.sdk.pair.core.PairItem;
import cn.developer.sdk.pair.core.TextValueItem.TextValue;

public class TextItemViewProvider extends BaseChildItemViewProvider {

  @Override
  public void setupView(ViewHolder viewHolder, int groupPosition, PairGroup groupEntity,
      int childPosition, PairItem childEntity) {
    super.setupView(viewHolder, groupPosition, groupEntity, childPosition, childEntity);

    String value = "";
    if (childEntity.getValueItem().getValueType() == ValueType.TEXT) {
      TextValue textValue = childEntity.getValueItem().getValue();
      value = textValue.getText();
    } else if (childEntity.getValueItem().getValueType() == ValueType.FIELD) {
      FieldValue fieldValue = childEntity.getValueItem().getValue();
      value = fieldValue.getText();
    }

    viewHolder.bindResId(R.id.tv_pair_item_value).setVisibility(View.VISIBLE)
        .bindTextData(TextUtils.isEmpty(value) ? "" : Html.fromHtml(value));
  }


}
