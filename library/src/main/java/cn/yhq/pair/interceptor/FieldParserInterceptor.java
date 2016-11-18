package cn.yhq.pair.interceptor;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JxltEngine;
import org.apache.commons.jexl3.MapContext;

import java.util.Locale;

import cn.yhq.pair.item.FieldPairItem;
import cn.yhq.pair.item.Interceptor;

/**
 * Created by Yanghuiqiang on 2016/11/17.
 */

public class FieldParserInterceptor implements Interceptor<FieldPairItem> {
    private JexlContext jexlContext = new MapContext();
    private JexlEngine jexlEngine = new JexlBuilder().create();
    private JxltEngine jxltEngine = jexlEngine.createJxltEngine();

    @Override
    public FieldPairItem intercept(Chain<FieldPairItem> chain) throws Exception {
        Object entity = chain.getPair().getEntity();
        this.jexlContext.set(entity.getClass().getSimpleName().toLowerCase(Locale.getDefault()), entity);
        JxltEngine.Expression expression = jxltEngine.createExpression(chain.getPair().getExp());
        Object newValue = expression.evaluate(jexlContext);
        chain.getPair().setText(newValue);
        return chain.handle(chain.getPair());
    }
}
