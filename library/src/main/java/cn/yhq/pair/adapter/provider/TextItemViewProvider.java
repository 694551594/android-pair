package cn.yhq.pair.adapter.provider;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.BaseTextPairItem;
import cn.yhq.pair.item.PairCatalog;

public class TextItemViewProvider extends BaseChildItemViewProvider<BaseTextPairItem<?>> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int groupPosition, PairCatalog groupEntity, int childPosition, BaseTextPairItem<?> childEntity) {
        String value = childEntity.getText();

        viewHolder.bindResId(R.id.text)
                .setVisibility(View.VISIBLE)
                .setText(TextUtils.isEmpty(value) ? "" : Html.fromHtml(value));
    }

    @Override
    public int getItemViewStubLayoutId() {
        return R.layout.textview;
    }

}
