package cn.yhq.pair.adapter;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.android.developers.sdk.R;
import cn.developer.sdk.adapter.core.ViewHolder;
import cn.developer.sdk.pair.core.ImageValueItem.ImageValue;
import cn.developer.sdk.pair.core.PairGroup;
import cn.developer.sdk.pair.core.PairItem;
import cn.developer.sdk.util.DisplayUtils;

public class ImageItemViewProvider extends BaseChildItemViewProvider {

  @Override
  public void setupView(ViewHolder viewHolder, int groupPosition, PairGroup groupEntity,
      int childPosition, PairItem childEntity) {
    super.setupView(viewHolder, groupPosition, groupEntity, childPosition, childEntity);
    ImageValue imageValue = childEntity.getValueItem().getValue();
    SimpleDraweeView valueImageView = viewHolder.getView(R.id.iv_pair_item_value);
    valueImageView.setVisibility(View.VISIBLE);

    if (imageValue.getResId() != 0 && !TextUtils.isEmpty(imageValue.getUrl())) {
      valueImageView.setHierarchy(getGenericDraweeHierarchy(imageValue.getResId()));
      valueImageView.setImageURI(Uri.parse(imageValue.getUrl()));
    } else if (!TextUtils.isEmpty(imageValue.getUrl())) {
      valueImageView.setImageURI(Uri.parse(imageValue.getUrl()));
    } else if (imageValue.getResId() != 0) {
      valueImageView.setImageURI(Uri.parse("res:///" + imageValue.getResId()));
    } else {}

    int width = imageValue.getWidth();
    int height = imageValue.getHeight();

    LayoutParams params = valueImageView.getLayoutParams();
    if (width != 0 && height != 0) {
      params.height = DisplayUtils.dp2Px(getContext(), height);;
      params.width = DisplayUtils.dp2Px(getContext(), width);;
    } else {
      params.height = DisplayUtils.dp2Px(getContext(), 40);
      params.width = DisplayUtils.dp2Px(getContext(), 40);
    }
    valueImageView.setLayoutParams(params);
  }

  private GenericDraweeHierarchy getGenericDraweeHierarchy(int res) {
    GenericDraweeHierarchy gdh =
        new GenericDraweeHierarchyBuilder(this.getContext().getResources())
            .setFailureImage(this.getContext().getResources().getDrawable(res))// fresco:failureImage="@drawable/error"失败图
            .setPlaceholderImage(this.getContext().getResources().getDrawable(res))// fresco:placeholderImage="@color/wait_color"占位图
            //.setRoundingParams(RoundingParams.asCircle())
            .build();
    return gdh;
  }


}
