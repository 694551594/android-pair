package cn.yhq.pair.adapter.provider;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import cn.yhq.adapter.core.ViewHolder;
import cn.yhq.pair.R;
import cn.yhq.pair.item.ImagePairItem;
import cn.yhq.pair.item.PairCatalog;
import cn.yhq.pair.utils.DisplayUtils;

public class ImageItemViewProvider extends BaseChildItemViewProvider<ImagePairItem> {

  @Override
  public void setupItemView(ViewHolder viewHolder, int groupPosition, PairCatalog groupEntity, int childPosition, ImagePairItem childEntity) {
    ImageView imageView = viewHolder.getView(R.id.image);
    imageView.setVisibility(View.VISIBLE);

    int width = childEntity.getWidth();
    int height = childEntity.getHeight();

    LayoutParams params = imageView.getLayoutParams();
    if (width != 0 && height != 0) {
      params.height = DisplayUtils.dp2Px(getContext(), height);;
      params.width = DisplayUtils.dp2Px(getContext(), width);;
    } else {
      params.height = DisplayUtils.dp2Px(getContext(), 40);
      params.width = DisplayUtils.dp2Px(getContext(), 40);
    }
    imageView.setLayoutParams(params);
  }
}
