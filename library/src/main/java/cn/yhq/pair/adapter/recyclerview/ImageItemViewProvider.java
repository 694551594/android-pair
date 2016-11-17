package cn.yhq.pair.adapter.recyclerview;

import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import cn.yhq.adapter.recycler.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.ImagePairItem;
import cn.yhq.pair.utils.DisplayUtils;

public class ImageItemViewProvider extends BasePairItemViewProvider<ImagePairItem> {

    @Override
    public void setupItemView(ViewHolder viewHolder, int position, ImagePairItem childEntity) {

        ImageView imageView = viewHolder.bindResId(R.id.image).getView(R.id.image);

        if (TextUtils.isEmpty(childEntity.getUrl())) {
            if (childEntity.getResId() != 0) {
                viewHolder.setImage(childEntity.getResId());
            }
        } else {
            viewHolder.setImage(childEntity.getUrl());
        }

        int width = childEntity.getWidth();
        int height = childEntity.getHeight();

        LayoutParams params = imageView.getLayoutParams();
        if (width != 0 && height != 0) {
            params.height = DisplayUtils.dp2Px(getContext(), height);
            ;
            params.width = DisplayUtils.dp2Px(getContext(), width);
            ;
        } else {
            params.height = DisplayUtils.dp2Px(getContext(), 32);
            params.width = DisplayUtils.dp2Px(getContext(), 32);
        }
        imageView.setLayoutParams(params);
    }

    @Override
    public int getItemViewStubLayoutId() {
        return R.layout.imageview;
    }
}
