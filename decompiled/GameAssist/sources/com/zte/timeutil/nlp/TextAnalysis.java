package com.zte.timeutil.nlp;

import com.zte.timeutil.utils.RegexResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class TextAnalysis {

    /* renamed from: b, reason: collision with root package name */
    private static volatile TextAnalysis f18162b;

    /* renamed from: c, reason: collision with root package name */
    private static volatile Pattern f18163c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f18164a = true;

    private TextAnalysis() {
        try {
            f18163c = RegexResourceUtil.a("TimeRegex.Gzip");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static TextAnalysis b() {
        if (f18162b == null) {
            synchronized (TextAnalysis.class) {
                try {
                    if (f18162b == null) {
                        f18162b = new TextAnalysis();
                    }
                } finally {
                }
            }
        }
        return f18162b;
    }

    public List a(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TimeText());
        Matcher matcher = f18163c.matcher(str);
        int i2 = -1;
        int i3 = 0;
        while (matcher.find()) {
            int start = matcher.start();
            if (i2 == start) {
                i3--;
                TimeText timeText = (TimeText) arrayList.get(i3);
                timeText.f(timeText.c() + matcher.group());
            } else {
                TimeText timeText2 = (TimeText) arrayList.get(i3);
                timeText2.f(matcher.group());
                timeText2.e(start);
            }
            i2 = matcher.end();
            ((TimeText) arrayList.get(i3)).d(i2);
            i3++;
            if (arrayList.size() - 1 < i3) {
                arrayList.add(new TimeText());
            }
        }
        return arrayList;
    }

    public boolean c() {
        return this.f18164a;
    }
}
