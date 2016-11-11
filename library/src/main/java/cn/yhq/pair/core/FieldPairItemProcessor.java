package cn.yhq.pair.core;

import cn.developer.sdk.pair.adapter.IPairItemProcessor;
import cn.developer.sdk.pair.core.BaseValueItem.ValueType;
import cn.developer.sdk.pair.core.FieldValueItem.FieldValue;

public class FieldPairItemProcessor implements IPairItemProcessor {
  private FieldHelper fieldHelper;

  public FieldPairItemProcessor() {
    fieldHelper = new FieldHelper();
  }

  public FieldPairItemProcessor(Object entity) {
    fieldHelper = new FieldHelper(entity);
  }

  public void setEntity(Object entity) {
    fieldHelper.setEntity(entity);
  }

  @Override
  public PairItem process(PairItem pairItem) {
    ValueType valueType = pairItem.getValueItem().getValueType();
    if (valueType == ValueType.FIELD) {
      FieldValue fieldValue = pairItem.getValueItem().getValue();
      this.setEntity(pairItem.getEntity());
      pairItem.setText(fieldHelper.convert(fieldValue.getExp()));
    }
    return pairItem;
  }

}
