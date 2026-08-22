package cn.nubia.gameassist.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/* loaded from: classes.dex */
public class TimeUtil {
    public static boolean a(long j2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(simpleDateFormat.format(Long.valueOf(j2)));
            Objects.requireNonNull(parse);
            calendar2.setTime(parse);
            Date parse2 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Objects.requireNonNull(parse2);
            calendar.setTime(parse2);
            return calendar2.get(5) == calendar.get(5);
        } catch (ParseException e2) {
            throw new RuntimeException(e2);
        }
    }
}
