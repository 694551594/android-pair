package cn.yhq.pair.core;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.developer.sdk.util.JsonUtils;

public class PairIntentAction extends PairClickAction {
  private static final long serialVersionUID = 8558891889666350856L;
  private String targetClass;
  private int requestCode = -1;
  private FieldHelper fieldHelper;
  private List<IntentArgs> intentArgsList;

  public PairIntentAction(PairItem pairItem) {
    super(pairItem);
    fieldHelper = new FieldHelper();
    intentArgsList = new ArrayList<IntentArgs>();
  }

  public static class IntentArgs {
    private String type;
    private String key;
    private String value;

    public static IntentArgs make() {
      return new IntentArgs();
    }

    public String getType() {
      return type;
    }

    public IntentArgs setType(String type) {
      this.type = type;
      return this;
    }

    public String getKey() {
      return key;
    }

    public IntentArgs setKey(String key) {
      this.key = key;
      return this;
    }

    public String getValue() {
      return value;
    }

    public IntentArgs setValue(String value) {
      this.value = value;
      return this;
    }


  }

  public PairIntentAction setRequestCode(int requestCode) {
    this.requestCode = requestCode;
    return this;
  }

  public PairIntentAction setTargetClass(String targetClass) {
    this.targetClass = targetClass;
    return this;
  }

  public PairIntentAction addIntentArgs(IntentArgs intentArgs) {
    this.intentArgsList.add(intentArgs);
    return this;
  }

  private Bundle convertArgs() {
    Bundle args = new Bundle();
    for (IntentArgs intentArgs : intentArgsList) {
      ArgsType argsType = ArgsType.convert(intentArgs.type);
      addArgs(args, argsType, intentArgs.getKey(), intentArgs.getValue());
    }
    return args;
  }

  private String convertField(String value) {
    fieldHelper.setEntity(pairItem.getEntity());
    return fieldHelper.convert(value);
  }

  private String convertJson(String value) {
    return JsonUtils.toJson(pairItem.getEntity());
  }

  private void addArgs(Bundle args, ArgsType type, String key, String value) {
    switch (type) {
      case BOOLEAN:
        args.putBoolean(key, Boolean.valueOf(value));
        break;
      case DOUBLE:
        args.putDouble(key, Double.valueOf(value));
        break;
      case FLOAT:
        args.putFloat(key, Float.valueOf(value));
        break;
      case INT:
        args.putInt(key, Integer.valueOf(value));
        break;
      case STRING:
        args.putString(key, value);
        break;
      case FIELD:
        args.putString(key, convertField(value));
        break;
      case JSON:
        args.putString(key, convertJson(value));
        break;
      default:
        break;

    }
  }

  public static enum ArgsType {
    STRING("string"), INT("int"), FLOAT("float"), DOUBLE("double"), BOOLEAN("boolean"), FIELD(
        "field"), JSON("json");

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

  @Override
  public void onClick(Context context) {
    try {
      Intent intent = new Intent(context, Class.forName(targetClass));
      intent.putExtras(convertArgs());
      if (requestCode == -1) {
        context.startActivity(intent);
      } else if (context instanceof Activity) {
        ((Activity) context).startActivityForResult(intent, requestCode);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

}
