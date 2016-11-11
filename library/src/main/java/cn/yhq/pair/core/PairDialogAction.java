package cn.yhq.pair.core;

import android.content.Context;
import android.text.TextUtils;
import cn.developer.sdk.base.BaseActivity;
import cn.developer.sdk.dialog.DialogBuilder;

public class PairDialogAction extends PairClickAction {
  private static final long serialVersionUID = 302512681544126700L;
  protected DialogBuilder dialogBuilder;
  private String dialogTitle;
  private String dialogMessage;
  private String dialogPButtonText;
  private String dialogNButtonText;
  private String dialogType;
  private int dialogId;

  public enum DialogType {
    ALERT("alert"), LIST("list"), MESSAGE("message"), LOADING("loading");

    String type;

    DialogType(String type) {
      this.type = type;
    }

    public static DialogType convert(String type) {
      for (DialogType argsType : DialogType.values()) {
        if (type.equals(argsType.type)) {
          return argsType;
        }
      }
      return null;
    }

  }

  public PairDialogAction(PairItem pairItem) {
    super(pairItem);
  }

  public PairDialogAction setDialogArgs(int id, String type, String title, String message,
      String dialogPButtonText, String dialogNButtonText) {
    this.dialogTitle = title;
    this.dialogMessage = message;
    this.dialogNButtonText = dialogNButtonText;
    this.dialogPButtonText = dialogPButtonText;
    this.dialogId = id;
    this.dialogType = type;
    return this;
  }

  public DialogBuilder getDialogBuilder() {
    return dialogBuilder;
  }

  @Override
  public void onClick(Context context) {
    dialogBuilder =
        DialogBuilder.builder(context).setId(dialogId).setTitle(this.dialogTitle)
            .setMessage(this.dialogMessage);
    if (!TextUtils.isEmpty(this.dialogPButtonText)) {
      dialogBuilder.setPositiveButtonText(this.dialogPButtonText);
    }
    if (!TextUtils.isEmpty(this.dialogNButtonText)) {
      dialogBuilder.setNegativeButtonText(this.dialogNButtonText);
    }
    if (context instanceof BaseActivity) {
      BaseActivity activity = (BaseActivity) context;
      switch (DialogType.convert(dialogType)) {
        case ALERT:
          activity.getDialogManager().showAlertDialog(dialogBuilder);
          break;
        case LIST:
          activity.getDialogManager().showListDialog(dialogBuilder);
          break;
        case MESSAGE:
        default:
          activity.getDialogManager().showMessageDialog(dialogBuilder);
          break;
        case LOADING:
          activity.getDialogManager().showLoadingDialog(dialogBuilder);
          break;
      }
    }
  }
}
