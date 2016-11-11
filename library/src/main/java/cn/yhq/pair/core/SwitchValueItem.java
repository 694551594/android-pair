package cn.yhq.pair.core;



public class SwitchValueItem extends BaseValueItem<SwitchValueItem.SwitchValue> {

  public SwitchValueItem() {
    super(ValueType.SWITCH);
  }

  public static SwitchValueItem createValueItem(boolean checked) {
    SwitchValueItem switchValueItem = new SwitchValueItem();
    SwitchValue switchValue = new SwitchValue();
    switchValue.setChecked(checked);
    switchValueItem.setValue(switchValue);
    return switchValueItem;
  }

  public static class SwitchValue extends BaseValue<Boolean> {

    public SwitchValue() {}

    public boolean isChecked() {
      return this.getValue();
    }

    public void setChecked(boolean checked) {
      this.setValue(checked);
    }

    @Override
    public Boolean convert(Object o) {
      return (Boolean) o;
    }

  }

}
