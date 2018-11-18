package com.enroll.common.validator;

import org.apache.commons.lang.StringUtils;

import com.enroll.common.exception.RRException;



/**
 * 数据校验
 *
 * @author hsc
 *
 * Jul 20, 2017
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}