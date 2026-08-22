package cn.nubia.chatassistant.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class TimeUtils {
    public static String timestampToTime(String str) {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(Long.valueOf(str).longValue() * 1000));
    }
}
