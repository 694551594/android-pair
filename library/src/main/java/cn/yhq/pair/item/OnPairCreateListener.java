package cn.yhq.pair.item;

/**
 * Created by Yanghuiqiang on 2016/11/18.
 */

public abstract class OnPairCreateListener {

    public abstract void onCreate(int id, IPair pair);

    protected <T extends IPair> T getPair(IPair pair) {
        return (T) pair;
    }

    protected TextPairItem getTextPairItem(IPair pair) {
        return (TextPairItem) pair;
    }

    protected ImagePairItem getImagePairItem(IPair pair) {
        return (ImagePairItem) pair;
    }

    protected FieldPairItem getFieldPairItem(IPair pair) {
        return (FieldPairItem) pair;
    }

    protected SwitchPairItem getSwitchPairItem(IPair pair) {
        return (SwitchPairItem) pair;
    }

    protected CheckPairItem getCheckPairItem(IPair pair) {
        return (CheckPairItem) pair;
    }
}
