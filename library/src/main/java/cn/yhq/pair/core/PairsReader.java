package cn.yhq.pair.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import cn.developer.sdk.bean.pair.Pair;
import cn.developer.sdk.pair.adapter.IPairGroupProcessor;
import cn.developer.sdk.util.AssetUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class PairsReader {

  public static PairGroupList getPairGroupList(Context context, String filename, String srcJson,
      IPairGroupProcessor pairGroupProcessor) {
    Map<String, String> metadatas =
        new Gson().fromJson(AssetUtils.getJson(context, filename),
            new TypeToken<Map<String, String>>() {}.getType());
    Map<String, String> projectInfos =
        new Gson().fromJson(srcJson, new TypeToken<Map<String, String>>() {}.getType());
    PairGroupList pairGroupList =
        PairGroupList.newPairGroupList(context).addPairGroupProcessor(pairGroupProcessor);
    for (Entry<String, String> entry : metadatas.entrySet()) {
      String name = entry.getKey();
      String value = entry.getValue();
      if (value.equals("null")) {
        pairGroupList.newPairGroup();
      } else {
        pairGroupList.addPairItem(PairItem.makeTextPair(name, projectInfos.get(value)));
      }
    }
    return pairGroupList;
  }

  public static List<PairGroup> getPairGroupData(Context context, String filename, String srcJson) {
    Map<String, String> metadatas =
        new Gson().fromJson(AssetUtils.getJson(context, filename),
            new TypeToken<Map<String, String>>() {}.getType());
    Map<String, String> projectInfos =
        new Gson().fromJson(srcJson, new TypeToken<Map<String, String>>() {}.getType());

    List<PairGroup> data = new ArrayList<PairGroup>();
    PairGroup group = null;
    for (Entry<String, String> entry : metadatas.entrySet()) {
      String name = entry.getKey();
      String value = entry.getValue();
      if (value.equals("null")) {
        group = PairGroup.createPairGroup();
        data.add(group);
      } else {
        group.addPairItem(PairItem.makeTextPair(name, projectInfos.get(value)));
      }
    }

    return data;
  }

  public static List<Pair<String, String>> getPairData(Context context, String filename,
      String srcJson) {
    Map<String, String> metadatas =
        new Gson().fromJson(AssetUtils.getJson(context, filename),
            new TypeToken<Map<String, String>>() {}.getType());
    Map<String, String> projectInfos =
        new Gson().fromJson(srcJson, new TypeToken<Map<String, String>>() {}.getType());

    List<Pair<String, String>> data = new ArrayList<Pair<String, String>>();
    for (Entry<String, String> entry : metadatas.entrySet()) {
      String name = entry.getKey();
      String value = entry.getValue();
      if (value.equals("null")) {
        data.add(null);
      } else {
        data.add(new Pair<String, String>(name, projectInfos.get(value)));
      }
    }

    return data;
  }

  public static List<Pair<String, String>> getPairData(Context context, String filename,
      JsonObject srcJson) {
    Map<String, String> metadatas =
        new Gson().fromJson(AssetUtils.getJson(context, filename),
            new TypeToken<Map<String, String>>() {}.getType());
    Map<String, String> projectInfos =
        new Gson().fromJson(srcJson, new TypeToken<Map<String, String>>() {}.getType());

    List<Pair<String, String>> data = new ArrayList<Pair<String, String>>();
    for (Entry<String, String> entry : metadatas.entrySet()) {
      String name = entry.getKey();
      String value = entry.getValue();
      if (value.equals("null")) {
        data.add(null);
      } else {
        data.add(new Pair<String, String>(name, projectInfos.get(value)));
      }
    }

    return data;
  }
}
