package com.chris.pss.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by noonecode on 2017/5/8.
 */
public class EmptyUtils {

    /**
     * 列表为空（null、size=0），则返回true
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
        if (obj instanceof Collection) return ((Collection) obj).isEmpty();
        if (obj instanceof Map) return ((Map) obj).isEmpty();
        return false;
    }
}
