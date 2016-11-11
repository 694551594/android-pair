package cn.yhq.pair.adapter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import cn.developer.sdk.manager.L;
import cn.developer.sdk.pair.core.FieldValueItem;
import cn.developer.sdk.pair.core.ImageValueItem;
import cn.developer.sdk.pair.core.PairDialogAction;
import cn.developer.sdk.pair.core.PairGroupList;
import cn.developer.sdk.pair.core.PairIntentAction;
import cn.developer.sdk.pair.core.PairIntentAction.IntentArgs;
import cn.developer.sdk.pair.core.PairItem;
import cn.developer.sdk.pair.core.PairPreferenceAction;
import cn.developer.sdk.pair.core.PairPreferenceAction.PreferenceArgs;
import cn.developer.sdk.pair.core.SwitchValueItem;
import cn.developer.sdk.pair.core.TextValueItem;

/**
 * pair xml解析器，负责将xml文件里面解析成java对象
 * 
 * @author Yanghuiqiang 2015-8-21
 * 
 */
public class PairGroupXMLParser {
  private final static String TAG = "PairGroupXMLParser";
  private int mXmlResId;
  private Context mContext;
  private final static String NAMESPACE = null;
  private final static String PAIRGROUP = "PairGroup";
  private final static String PAIRGROUPLIST = "PairGroupList";
  private final static String PAIRITEM = "PairItem";
  private final static String TEXTVALYE = "TextValue";
  private final static String IMAGEVALYE = "ImageValue";
  private final static String SWITCHVALYE = "SwitchValue";
  private final static String FIELDVALUE = "FieldValue";
  private final static String URL = "url";
  private final static String RESID = "resId";
  private final static String CHECK = "check";
  private final static String TEXT = "text";
  private final static String TITLE = "title";
  private final static String KEY = "key";
  private final static String ICON = "icon";
  private final static String VISIABLE = "visiable";
  private final static String PROCESSOR = "processor";
  private final static String INDICATOR = "indicator";
  private final static String DESCRIPTION = "description";
  private final static String EXP = "exp";
  private final static String TYPE = "type";
  private final static String WIDTH = "width";
  private final static String HEIGHT = "height";
  private final static String DEFAULTVALUE = "DefaultValue";
  private final static String VALUE = "value";
  private final static String TARGETCLASS = "target";
  private final static String REQUESTCODE = "requestCode";
  private final static String DIALOGTITLE = "title";
  private final static String DIALOGMESSAGE = "message";
  private final static String DIALOGPBUTTONTEXT = "pButtonText";
  private final static String DIALOGNBUTTONTEXT = "nButtonText";
  private final static String ACTION = "PairAction";
  private final static String ARGS = "Args";
  private final static String INTENT = "Intent";
  private final static String DIALOG = "Dialog";
  private final static String PREFERENCE = "Preference";
  private final static String ID = "id";

  private PairGroupXMLParser(Context context, int xmlResId) {
    this.mContext = context;
    this.mXmlResId = xmlResId;
  }

  public static PairGroupList parser(Context context, int xmlResId) {
    PairGroupXMLParser pairGroupXMLParser = new PairGroupXMLParser(context, xmlResId);
    try {
      return pairGroupXMLParser.parser();
    } catch (Exception e) {
      e.printStackTrace();
      L.e(TAG, e.getLocalizedMessage(), e);
    }
    return null;
  }

  public static ExpandablePairGroupAdapter buildPairAdapter(Context context, int xmlResId) {
    PairGroupList pairGroupList = parser(context, xmlResId);
    if (pairGroupList == null) {
      return null;
    }
    return pairGroupList.buildPairAdapter();
  }

  /**
   * 从resid字符串里面获取真正的资源id
   * 
   * @param resString
   * @return
   */
  private int getResId(String resString) {
    Resources res = mContext.getResources();
    final String packageName = mContext.getPackageName();
    return res.getIdentifier(packageName + resString.replace("@", ":"), null, null);
  }

  /**
   * 判断是否为resid的字符串
   * 
   * @param text
   * @return
   */
  private boolean isResId(String text) {
    return text.contains("@") && text.contains("/");
  }

  public static boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);
    if (!isNum.matches()) {
      return false;
    }
    return true;
  }

  /**
   * 将字符串转换为文本，字符串可能为resid
   * 
   * @param text
   * @return
   */
  private String getText(String text) {
    if (isResId(text)) {
      return this.mContext.getString(getResId(text));
    }
    return text;
  }

  /**
   * 解析xml文件
   * 
   * @return
   * @throws ClassNotFoundException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws XmlPullParserException
   * @throws InstantiationException
   * @throws IOException
   */
  private PairGroupList parser() throws IllegalArgumentException, IllegalAccessException,
      InvocationTargetException, ClassNotFoundException, XmlPullParserException, IOException,
      InstantiationException {
    PairGroupList pairGroupList = null;
    PairItem pairItem = null;
    XmlResourceParser parser = this.mContext.getResources().getXml(mXmlResId);
    int eventType = parser.getEventType();
    while (eventType != XmlPullParser.END_DOCUMENT) {
      switch (eventType) {
        case XmlPullParser.START_DOCUMENT:
          pairGroupList = PairGroupList.newPairGroupList(mContext);
          break;
        case XmlPullParser.START_TAG:
          if (parser.getName().equals(PAIRGROUPLIST)) {
            String groupProcessorBean = parser.getAttributeValue(NAMESPACE, PROCESSOR);
            if (!TextUtils.isEmpty(groupProcessorBean)) {
              PairGroupProcessor pairGroupProcessor = null;
              try {
                pairGroupProcessor =
                    (PairGroupProcessor) Class.forName(groupProcessorBean).newInstance();
              } catch (IllegalAccessException e) {
                pairGroupProcessor =
                    (PairGroupProcessor) Class.forName(groupProcessorBean).getConstructors()[0]
                        .newInstance(mContext);
              } catch (InstantiationException e) {
                pairGroupProcessor =
                    (PairGroupProcessor) Class.forName(groupProcessorBean).getConstructors()[0]
                        .newInstance(mContext);
              }
              pairGroupProcessor.setContext(mContext);
              pairGroupList.addPairGroupProcessor(pairGroupProcessor);
            }
          } else if (parser.getName().equals(PAIRGROUP)) {
            String title = parser.getAttributeValue(NAMESPACE, TITLE);
            if (!TextUtils.isEmpty(title)) {
              pairGroupList.newPairGroup(getText(title));
            } else {
              pairGroupList.newPairGroup();
            }
            boolean visiable = parser.getAttributeBooleanValue(NAMESPACE, VISIABLE, true);
            pairGroupList.setGroupVisiable(visiable);
          } else if (parser.getName().equals(PAIRITEM)) {
            pairItem = new PairItem();
            String icon = parser.getAttributeValue(NAMESPACE, ICON);
            if (!TextUtils.isEmpty(icon)) {
              int resId = getResId(icon);
              pairItem.setIconRes(resId);
            }
            String key = parser.getAttributeValue(NAMESPACE, KEY);
            if (!TextUtils.isEmpty(key)) {
              pairItem.setKey(getText(key));
            }
            boolean indicator = parser.getAttributeBooleanValue(NAMESPACE, INDICATOR, false);
            pairItem.setIndicator(indicator);
            String description = parser.getAttributeValue(NAMESPACE, DESCRIPTION);
            TextValueItem valueItem = TextValueItem.createValueItem("");
            pairItem.setValueItem(valueItem);
            pairItem.setDescription(description);
          } else if (parser.getName().equals(TEXTVALYE)) {
            String text = parser.getAttributeValue(NAMESPACE, TEXT);
            if (!TextUtils.isEmpty(text)) {
              TextValueItem valueItem = TextValueItem.createValueItem(getText(text));
              pairItem.setValueItem(valueItem);
            }
          } else if (parser.getName().equals(SWITCHVALYE)) {
            boolean check = parser.getAttributeBooleanValue(NAMESPACE, CHECK, false);
            SwitchValueItem valueItem = SwitchValueItem.createValueItem(check);
            pairItem.setValueItem(valueItem);
          } else if (parser.getName().equals(IMAGEVALYE)) {
            String url = parser.getAttributeValue(NAMESPACE, URL);
            String res = parser.getAttributeValue(NAMESPACE, RESID);
            int width = parser.getAttributeIntValue(NAMESPACE, WIDTH, 0);
            int height = parser.getAttributeIntValue(NAMESPACE, HEIGHT, 0);
            if (!TextUtils.isEmpty(res)) {
              int resId = getResId(res);
              ImageValueItem valueItem = ImageValueItem.createValueItem(resId, url, width, height);
              pairItem.setValueItem(valueItem);
            } else {
              ImageValueItem valueItem = ImageValueItem.createValueItem(0, url, width, height);
              pairItem.setValueItem(valueItem);
            }
          } else if (parser.getName().equals(FIELDVALUE)) {
            String exp = parser.getAttributeValue(NAMESPACE, EXP);
            FieldValueItem valueItem = FieldValueItem.createValueItem(exp);
            pairItem.setValueItem(valueItem);
          } else if (parser.getName().equals(ACTION)) {

          } else if (parser.getName().equals(ARGS)) {
            String key = parser.getAttributeValue(NAMESPACE, KEY);
            String value = parser.getAttributeValue(NAMESPACE, VALUE);
            String type = parser.getAttributeValue(NAMESPACE, TYPE);
            PairIntentAction pairIntentAction =
                (PairIntentAction) pairItem.getValueItem().getPairAction();
            pairIntentAction.addIntentArgs(IntentArgs.make().setKey(key).setValue(value)
                .setType(type));
          } else if (parser.getName().equals(INTENT)) {
            String targetClass = parser.getAttributeValue(NAMESPACE, TARGETCLASS);
            int requestCode = parser.getAttributeIntValue(NAMESPACE, REQUESTCODE, -1);
            PairIntentAction pairIntentAction = new PairIntentAction(pairItem);
            pairIntentAction.setTargetClass(targetClass).setRequestCode(requestCode);
            pairItem.getValueItem().setPairAction(pairIntentAction);
          } else if (parser.getName().equals(DIALOG)) {
            int dialogId = parser.getAttributeIntValue(NAMESPACE, ID, 1);
            String type = parser.getAttributeValue(NAMESPACE, TYPE);
            String dialogTitle = parser.getAttributeValue(NAMESPACE, DIALOGTITLE);
            String dialogMessage = parser.getAttributeValue(NAMESPACE, DIALOGMESSAGE);
            String dialogPButtonText = parser.getAttributeValue(NAMESPACE, DIALOGPBUTTONTEXT);
            String dialogNButtonText = parser.getAttributeValue(NAMESPACE, DIALOGNBUTTONTEXT);
            PairDialogAction pairDialogAction = new PairDialogAction(pairItem);
            pairDialogAction.setDialogArgs(dialogId, type, dialogTitle, dialogMessage,
                dialogPButtonText, dialogNButtonText);
            pairItem.getValueItem().setPairAction(pairDialogAction);
          } else if (parser.getName().equals(PREFERENCE)) {
            String key = parser.getAttributeValue(NAMESPACE, KEY);
            String defaultValue = parser.getAttributeValue(NAMESPACE, DEFAULTVALUE);
            String type = parser.getAttributeValue(NAMESPACE, TYPE);
            PairPreferenceAction pairPreferenceAction = new PairPreferenceAction(pairItem);
            pairPreferenceAction.setPreferenceArgs(PreferenceArgs.make()
                .setDefaultValue(defaultValue).setKey(key).setType(type));
            pairItem.getValueItem().setPairAction(pairPreferenceAction);
          }
          break;
        case XmlPullParser.END_TAG:
          if (parser.getName().equals(PAIRGROUPLIST)) {
            break;
          } else if (parser.getName().equals(PAIRITEM)) {
            pairGroupList.addPairItem(pairItem);
          }
          break;
      }
      eventType = parser.next();
    }

    return pairGroupList;
  }
}
