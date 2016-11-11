package cn.yhq.pair.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldHelper {
  private Object entity;

  public FieldHelper() {}

  public FieldHelper(Object entity) {
    setEntity(entity);
  }

  public void setEntity(Object entity) {
    if (entity == null) {
      return;
    }
    this.entity = entity;
  }

  private static Field getDeclaredField(Class<?> beanClass, String fieldName) {
    Field field = null;
    for (Class<?> clazz = beanClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
      try {
        field = clazz.getDeclaredField(fieldName);
        return field;
      } catch (Exception e) {
        // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
        // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

      }
    }
    return null;
  }

  private static Object getFieldValue(Object bean, String fieldName) {
    try {
      Field field = getDeclaredField(bean.getClass(), fieldName);
      if (field == null) {
        return null;
      }
      field.setAccessible(true);
      Method m = null;
      if (field.getGenericType().toString().equals("boolean")) {
        m = (Method) bean.getClass().getMethod("is" + getMethodName(fieldName));
      } else {
        m = (Method) bean.getClass().getMethod("get" + getMethodName(fieldName));
      }
      Object val = m.invoke(bean);
      return val;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // 把一个字符串的第一个字母大写、效率是最高的、
  private static String getMethodName(String fildeName) throws Exception {
    byte[] items = fildeName.getBytes();
    items[0] = (byte) ((char) items[0] - 'a' + 'A');
    return new String(items);
  }

  public String convert(String exp) {
    if (this.entity == null) {
      return "";
    }
    if (exp.startsWith("${")) {
      String _exp = exp.substring(exp.indexOf("${") + 2, exp.lastIndexOf("}"));
      String field[] = _exp.split("\\.");
      Object result = null;
      Object tempBean = entity;
      for (int i = 0; i < field.length; i++) {
        result = parser(tempBean, field[i]);
        tempBean = result;
      }
      if (result == null) {
        return "";
      }
      return String.valueOf(result);
    }
    return exp;
  }

  private static Object parser(Object bean, String field) {
    Object value = getFieldValue(bean, field);
    return value;
  }

}
