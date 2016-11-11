package cn.yhq.pair.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.TextUtils;
import cn.developer.sdk.pair.adapter.IPairItemProcessor;
import cn.developer.sdk.pair.core.BaseValueItem.ValueType;
import cn.developer.sdk.pair.core.FieldValueItem.FieldValue;
import cn.developer.sdk.pair.core.TextValueItem.TextValue;

public class DatePairItemProcessor implements IPairItemProcessor {
  private final static SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy年MM月dd日",
      Locale.getDefault());
  private SimpleDateFormat mSimpleDateFormat;

  public DatePairItemProcessor() {
    this(mDateFormat);
  }

  public DatePairItemProcessor(SimpleDateFormat simpleDateFormat) {
    this.mSimpleDateFormat = simpleDateFormat;
  }

  @Override
  public PairItem process(PairItem pairItem) {
    ValueType valueType = pairItem.getValueItem().getValueType();
    if (valueType == ValueType.TEXT) {
      TextValue textValue = pairItem.getValueItem().getValue();
      if (!TextUtils.isEmpty(textValue.getText())) {
        textValue.setText(mSimpleDateFormat.format(new Date(Long.parseLong(textValue.getText()))));
      }
    } else if (valueType == ValueType.FIELD) {
      FieldValue fieldValue = pairItem.getValueItem().getValue();
      if (!TextUtils.isEmpty(fieldValue.getText())) {
        fieldValue.setText(mSimpleDateFormat.format(new Date(Long.parseLong(fieldValue.getText()))));
      }
    }
    return pairItem;
  }

}
