package cn.yhq.pair.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.android.developers.sdk.R;

/**
 * 空的itemview
 * 
 * @author Yanghuiqiang 2015-6-26
 * 
 */
public class EmptyItemViewProvider extends BaseChildItemViewProvider {

  @Override
  public View getConvertView(Context context, LayoutInflater inflater, ViewGroup parent) {
    return inflater.inflate(R.layout.com_emptyview_16, parent, false);
  }

}
