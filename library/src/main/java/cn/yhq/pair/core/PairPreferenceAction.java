package cn.yhq.pair.core;

import android.content.Context;
import cn.developer.sdk.util.PreferencesUtils;


public class PairPreferenceAction extends PairAction {
  private static final long serialVersionUID = -2460314516761692936L;
  private PreferenceArgs preferenceArgs;

  public PairPreferenceAction(PairItem pairItem) {
    super(pairItem);
  }

  public PairPreferenceAction setPreferenceArgs(PreferenceArgs preferenceArgs) {
    this.preferenceArgs = preferenceArgs;
    return this;
  }

  public Object getPreference(Context context) {
    if (preferenceArgs == null) {
      return null;
    }
    ArgsType argsType = ArgsType.convert(preferenceArgs.type);
    switch (argsType) {
      case BOOLEAN:
        return PreferencesUtils.getBooleanValue(context, preferenceArgs.key,
            Boolean.valueOf(preferenceArgs.defaultValue));
      case DOUBLE:
        return (double) PreferencesUtils.getFloatValue(context, preferenceArgs.key,
            Float.valueOf(preferenceArgs.defaultValue));
      case FLOAT:
        return PreferencesUtils.getFloatValue(context, preferenceArgs.key,
            Float.valueOf(preferenceArgs.defaultValue));
      case FIELD:
        // TODO 未处理
        return PreferencesUtils.getStringValue(context, preferenceArgs.key,
            preferenceArgs.defaultValue);
      case INT:
        return PreferencesUtils.getIntValue(context, preferenceArgs.key,
            Integer.valueOf(preferenceArgs.defaultValue));
      case STRING:
      default:
        return PreferencesUtils.getStringValue(context, preferenceArgs.key,
            preferenceArgs.defaultValue);
    }
  }

  public void onSavePreference(Context context) {
    if (preferenceArgs == null) {
      return;
    }
    PreferencesUtils.savePreferences(context, preferenceArgs.key, this.pairItem.getValueItem()
        .getValue());
  }

  public static enum ArgsType {
    STRING("string"), INT("int"), FLOAT("float"), DOUBLE("double"), BOOLEAN("boolean"), FIELD(
        "field");

    String type;

    ArgsType(String type) {
      this.type = type;
    }

    public static ArgsType convert(String type) {
      for (ArgsType argsType : ArgsType.values()) {
        if (type.equals(argsType.type)) {
          return argsType;
        }
      }
      return null;
    }

  }

  public static class PreferenceArgs {
    private String type;
    private String key;
    protected String defaultValue;

    public String getDefaultValue() {
      return defaultValue;
    }

    public PreferenceArgs setDefaultValue(String defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    public static PreferenceArgs make() {
      return new PreferenceArgs();
    }

    public String getType() {
      return type;
    }

    public PreferenceArgs setType(String type) {
      this.type = type;
      return this;
    }

    public String getKey() {
      return key;
    }

    public PreferenceArgs setKey(String key) {
      this.key = key;
      return this;
    }

  }
}
