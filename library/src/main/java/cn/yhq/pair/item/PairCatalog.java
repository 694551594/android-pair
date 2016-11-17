package cn.yhq.pair.item;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PairCatalog extends BasePair<PairCatalog> {
    private String title;

    public PairCatalog() {
        super(Type.CATALOG);
    }

    public String getTitle() {
        return title;
    }

    public PairCatalog setTitle(String title) {
        this.title = title;
        return this;
    }

}
