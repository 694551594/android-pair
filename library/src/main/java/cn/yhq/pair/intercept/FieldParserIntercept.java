package cn.yhq.pair.intercept;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JxltEngine;
import org.apache.commons.jexl3.MapContext;

import java.util.Locale;

import cn.yhq.pair.item.FieldPairItem;
import cn.yhq.pair.item.PairIntercept;

/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public class FieldParserIntercept implements PairIntercept<FieldPairItem> {
    private JexlContext jexlContext = new MapContext();
    private JexlEngine jexlEngine = new JexlBuilder().create();
    private JxltEngine jxltEngine = jexlEngine.createJxltEngine();

    public FieldParserIntercept() {

    }

    public void setEntity(Object entity) {
        this.jexlContext.set(entity.getClass().getSimpleName().toLowerCase(Locale.getDefault()), entity);
    }

    @Override
    public FieldPairItem intercept(Chain<FieldPairItem> chain) throws Exception {
        JxltEngine.Expression expression = jxltEngine.createExpression(chain.getItem().getExp());
        Object newValue = expression.evaluate(jexlContext);
        chain.getItem().setText(newValue);
        return chain.getItem();
    }
}
