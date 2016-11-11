package cn.yhq.pair.core;



public class ImageValueItem extends BaseValueItem<ImageValueItem.ImageValue> {

  public ImageValueItem() {
    super(ValueType.IMAGE);
  }

  public static ImageValueItem createValueItem(int resId, String url, int width, int height) {
    ImageValueItem imageValueItem = new ImageValueItem();
    ImageValue imageValue = new ImageValue();
    imageValue.setResId(resId);
    imageValue.setUrl(url);
    imageValue.setWidth(width);
    imageValue.setHeight(height);
    imageValueItem.setValue(imageValue);
    return imageValueItem;
  }

  public static class ImageInfo {
    private int resId;
    private String url;
    private int width;
    private int height;

    public int getResId() {
      return resId;
    }

    public void setResId(int resId) {
      this.resId = resId;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public int getWidth() {
      return width;
    }

    public void setWidth(int width) {
      this.width = width;
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }


  }

  public static class ImageValue extends BaseValue<ImageInfo> {

    public ImageValue() {
      this.value = new ImageInfo();
    }

    public ImageValue setResId(int resId) {
      value.resId = resId;
      return this;
    }

    public ImageValue setUrl(String url) {
      value.url = url;
      return this;
    }

    public ImageValue setWidth(int width) {
      value.width = width;
      return this;
    }

    public ImageValue setHeight(int height) {
      value.height = height;
      return this;
    }

    public int getResId() {
      return value.resId;
    }

    public String getUrl() {
      return value.url;
    }

    public int getWidth() {
      return value.width;
    }

    public int getHeight() {
      return value.height;
    }

    @Override
    public ImageInfo convert(Object o) {
      return null;
    }

  }

}
