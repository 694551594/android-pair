package cn.yhq.pair.adapter.provider;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.item.TextPairItem;

public class TextItemViewProvider extends BaseChildItemViewProvider<TextPairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int groupPosition, PairCatalog groupEntity, int childPosition, TextPairItem childEntity) {
        String value = childEntity.getText();

        viewHolder.bindResId(R.id.text)
                .setVisibility(View.VISIBLE)
                .setText(TextUtils.isEmpty(value) ? "" : Html.fromHtml(value));
    }

}
