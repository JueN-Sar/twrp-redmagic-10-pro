package com.zte.timeutil.nlp;

import com.zte.timeutil.formatter.DateTimeFormatterUtil;
import com.zte.timeutil.utils.CollectionUtil;
import com.zte.timeutil.utils.StringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class TimeNLPUtil {
    public static List a(String str, String str2) {
        if (StringUtil.c(str) || StringUtil.c(str)) {
            return null;
        }
        List a2 = TextAnalysis.b().a(str);
        if (CollectionUtil.a(a2)) {
            return null;
        }
        ArrayList arrayList = new ArrayList(a2.size());
        for (int i2 = 0; i2 < a2.size(); i2++) {
            TimeContext timeContext = new TimeContext();
            if (StringUtil.c(str2)) {
                str2 = DateTimeFormatterUtil.f(new Date(), "yyyy-MM-dd-HH-mm-ss");
            }
            timeContext.e(str2);
            timeContext.d(str2);
            if (StringUtil.c(((TimeText) a2.get(i2)).c())) {
                break;
            }
            TimeNLP timeNLP = new TimeNLP((TimeText) a2.get(i2), TextAnalysis.b(), timeContext);
            arrayList.add(timeNLP);
            timeNLP.d();
        }
        return TimeNLP.b(arrayList);
    }
}
