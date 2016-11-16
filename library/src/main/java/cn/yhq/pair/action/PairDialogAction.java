package cn.yhq.pair.action;

import android.content.Context;

import cn.yhq.dialog.core.IDialog;
import cn.yhq.pair.item.PairItem;


public abstract class PairDialogAction implements PairClickAction {
    private IDialog dialog;
    private Context context;

    public PairDialogAction(Context context) {
        this.context = context;
    }

    @Override
    public boolean onClick(PairItem pairItem) {
        dialog = onCreateDialog(context);
        dialog.show();
        return false;
    }

    protected abstract IDialog onCreateDialog(Context context);

}
