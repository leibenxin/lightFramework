package org.lightweb4j.framework.utils;

/**
 * @Author benxin_lei
 * @Date 2020-08-27 0:23
 * @Version 1.0.0
 */
public final class CastUtil {

    /**
     * 将Object转换成String，默认值为""
     * @param obj
     * @return
     */
    public static String castString(Object obj){
        return CastUtil.castString(obj, "");
    }

    /**
     * 将Object转换成String，需提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue){
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 将Object转换成Int型，默认值为0
     * @param obj
     * @return
     */
    public static int castInt(Object obj){
        return CastUtil.castInt(obj, 0);
    }

    /**
     * 将Object转换成Int类型，需提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj, int defaultValue){
        int intValue = defaultValue;
        if (obj != null){
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue = defaultValue;
                }
            }
        }

        return intValue;
    }

    /**
     * 将Object转换成double，默认值0
     * @param obj
     * @return
     */
    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj, 0);
    }

    /**
     * 将Object转换成double，需提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj, double defaultValue){
        double value = defaultValue;
        if (obj != null){
            String strValue = CastUtil.castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    value = Double.parseDouble(strValue);
                }catch (NumberFormatException ex){
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * 将Object转换成long，默认值0
     * @param obj
     * @return
     */
    public static long castLong(Object obj){
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 将Object转换成Long型，需提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj, long defaultValue){
        long value = defaultValue;
        if(obj != null){
            String strValue = CastUtil.castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    value = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * Object转换成boolean类型
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj){
        return CastUtil.castBoolean(obj, false);
    }

    /**
     * Object转换成boolean（提供默认值）
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj, boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj != null){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }

        return booleanValue;
    }
}
