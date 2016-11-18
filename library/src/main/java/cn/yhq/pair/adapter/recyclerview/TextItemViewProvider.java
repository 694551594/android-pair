package cn.yhq.pair.adapter.recyclerview;

import android.text.Html;
import android.text.TextUtils;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.TextPairItem;

public class TextItemViewProvider extends BaseItemViewProvider<TextPairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int position, TextPairItem childEntity) {
        super.setupItemView(viewHolder, position, childEntity);

        String value = childEntity.getText();

        viewHolder.bindResId(R.id.text)
                .setText(TextUtils.isEmpty(value) ? "" : Html.fromHtml(value));
    }

    @Override
    public int getItemViewStubLayoutId() {
        return R.layout.textview;
    }

}
