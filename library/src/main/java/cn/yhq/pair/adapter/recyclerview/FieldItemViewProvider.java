package cn.yhq.pair.adapter.recyclerview;

import android.text.Html;
import android.text.TextUtils;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.FieldPairItem;

public class FieldItemViewProvider extends BasePairItemViewProvider<FieldPairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int position, FieldPairItem childEntity) {
        String value = childEntity.getText();

        viewHolder.bindResId(R.id.text)
                .setText(TextUtils.isEmpty(value) ? "" : Html.fromHtml(value));
    }

    @Override
    public int getItemViewStubLayoutId() {
        return R.layout.textview;
    }

}
