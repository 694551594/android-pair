package cn.yhq.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yanghuiqiang on 2017/2/7.
 */
public class PreferencesUtils {

    public static boolean savePreferences(Context context, String key, Object value) {
        boolean success = false;
        if (context == null || key == null) {
            return success;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (value == null) {
            success = preferences.edit().remove(key).commit();
        }
        if (value instanceof Boolean) {
            success = preferences.edit().putBoolean(key, (Boolean) value).commit();
        }
        if (value instanceof Float) {
            success = preferences.edit().putFloat(key, (Float) value).commit();
        }
        if (value instanceof Integer) {
            success = preferences.edit().putInt(key, (Integer) value).commit();
        }
        if (value instanceof Long) {
            success = preferences.edit().putLong(key, (Long) value).commit();
        }
        if (value instanceof String) {
            success = preferences.edit().putString(key, (String) value).commit();
        }

        return success;
    }

    public static boolean getBooleanValue(Context context, String key, boolean defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean value = preferences.getBoolean(key, defaultValue);
        return value;
    }

    public static Map<String, ?> getAllValue(Context context) {
        if (context == null) {
            return new HashMap<>();
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getAll();
    }

    public static int getIntValue(Context context, String key, int defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int value = preferences.getInt(key, defaultValue);
        return value;
    }

    public static float getFloatValue(Context context, String key, float defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        float value = preferences.getFloat(key, defaultValue);
        return value;
    }

    public static String getStringValue(Context context, String key, String defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, defaultValue);
        return value;
    }

    public static long getLongValue(Context context, String key, long defaultValue) {
        if (context == null) {
            return defaultValue;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        long value = preferences.getLong(key, defaultValue);
        return value;
    }
}