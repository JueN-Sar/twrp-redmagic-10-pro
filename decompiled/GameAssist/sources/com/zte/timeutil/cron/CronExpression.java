package com.zte.timeutil.cron;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;

/* loaded from: classes2.dex */
public final class CronExpression implements Serializable, Cloneable {
    protected static final int DAY_OF_MONTH = 3;
    protected static final int DAY_OF_WEEK = 5;
    protected static final int HOUR = 2;
    public static final int MAX_YEAR;
    protected static final int MINUTE = 1;
    protected static final int MONTH = 4;
    protected static final int SECOND = 0;
    protected static final int YEAR = 6;
    protected static final Map<String, Integer> dayMap;
    protected static final Map<String, Integer> monthMap;
    private static final long serialVersionUID = 12423409423L;
    private final String cronExpression;
    protected transient TreeSet<Integer> daysOfMonth;
    protected transient TreeSet<Integer> daysOfWeek;
    protected transient TreeSet<Integer> hours;
    protected transient TreeSet<Integer> minutes;
    protected transient TreeSet<Integer> months;
    protected transient TreeSet<Integer> seconds;
    protected transient TreeSet<Integer> years;
    protected static final int ALL_SPEC_INT = 99;
    protected static final Integer ALL_SPEC = Integer.valueOf(ALL_SPEC_INT);
    protected static final int NO_SPEC_INT = 98;
    protected static final Integer NO_SPEC = Integer.valueOf(NO_SPEC_INT);
    private TimeZone timeZone = null;
    protected transient boolean lastdayOfWeek = false;
    protected transient int nthdayOfWeek = 0;
    protected transient boolean lastdayOfMonth = false;
    protected transient boolean nearestWeekday = false;
    protected transient int lastdayOffset = 0;
    protected transient boolean expressionParsed = false;

    static {
        HashMap hashMap = new HashMap(20);
        monthMap = hashMap;
        HashMap hashMap2 = new HashMap(60);
        dayMap = hashMap2;
        hashMap.put("JAN", 0);
        hashMap.put("FEB", 1);
        hashMap.put("MAR", 2);
        hashMap.put("APR", 3);
        hashMap.put("MAY", 4);
        hashMap.put("JUN", 5);
        hashMap.put("JUL", 6);
        hashMap.put("AUG", 7);
        hashMap.put("SEP", 8);
        hashMap.put("OCT", 9);
        hashMap.put("NOV", 10);
        hashMap.put("DEC", 11);
        hashMap2.put("SUN", 1);
        hashMap2.put("MON", 2);
        hashMap2.put("TUE", 3);
        hashMap2.put("WED", 4);
        hashMap2.put("THU", 5);
        hashMap2.put("FRI", 6);
        hashMap2.put("SAT", 7);
        MAX_YEAR = Calendar.getInstance().get(1) + 100;
    }

    public CronExpression(String str) {
        if (str == null) {
            throw new IllegalArgumentException("cronExpression cannot be null");
        }
        String upperCase = str.toUpperCase(Locale.US);
        this.cronExpression = upperCase;
        b(upperCase);
    }

    private void c(int i2, int i3, int i4) {
        if (i2 > 59 && (i3 == 0 || i3 == 1)) {
            throw new ParseException("Increment > 60 : " + i2, i4);
        }
        if (i2 > 23 && i3 == 2) {
            throw new ParseException("Increment > 24 : " + i2, i4);
        }
        if (i2 > 31 && i3 == 3) {
            throw new ParseException("Increment > 31 : " + i2, i4);
        }
        if (i2 > 7 && i3 == 5) {
            throw new ParseException("Increment > 7 : " + i2, i4);
        }
        if (i2 <= 12 || i3 != 4) {
            return;
        }
        throw new ParseException("Increment > 12 : " + i2, i4);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        try {
            b(this.cronExpression);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00c6, code lost:
    
        if (r0 != com.zte.timeutil.cron.CronExpression.ALL_SPEC_INT) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d1, code lost:
    
        if (r0 != com.zte.timeutil.cron.CronExpression.ALL_SPEC_INT) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00db, code lost:
    
        if (r0 != com.zte.timeutil.cron.CronExpression.ALL_SPEC_INT) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00f1, code lost:
    
        if (r0 != com.zte.timeutil.cron.CronExpression.ALL_SPEC_INT) goto L102;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(int r19, int r20, int r21, int r22) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.timeutil.cron.CronExpression.a(int, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x009b, code lost:
    
        if (r4.indexOf(76) == (-1)) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a1, code lost:
    
        if (r4.length() <= 1) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a7, code lost:
    
        if (r4.contains(",") != false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b1, code lost:
    
        throw new java.text.ParseException("Support for specifying 'L' with other days of the week is not implemented", -1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void b(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.timeutil.cron.CronExpression.b(java.lang.String):void");
    }

    public Object clone() {
        return new CronExpression(this);
    }

    protected int e(int i2, String str, int i3, int i4) {
        if (i2 >= str.length()) {
            a(i3, -1, -1, i4);
            return i2;
        }
        char charAt = str.charAt(i2);
        if (charAt == 'L') {
            if (i4 != 5) {
                throw new ParseException("'L' option is not valid here. (pos=" + i2 + ")", i2);
            }
            if (i3 < 1 || i3 > 7) {
                throw new ParseException("Day-of-Week values must be between 1 and 7", -1);
            }
            this.lastdayOfWeek = true;
            k(i4).add(Integer.valueOf(i3));
            return i2 + 1;
        }
        if (charAt == 'W') {
            if (i4 == 3) {
                this.nearestWeekday = true;
                if (i3 > 31) {
                    throw new ParseException("The 'W' option does not make sense with values larger than 31 (max number of days in a month)", i2);
                }
                k(i4).add(Integer.valueOf(i3));
                return i2 + 1;
            }
            throw new ParseException("'W' option is not valid here. (pos=" + i2 + ")", i2);
        }
        if (charAt == '#') {
            if (i4 != 5) {
                throw new ParseException("'#' option is not valid here. (pos=" + i2 + ")", i2);
            }
            int i5 = i2 + 1;
            try {
                int parseInt = Integer.parseInt(str.substring(i5));
                this.nthdayOfWeek = parseInt;
                if (parseInt < 1 || parseInt > 5) {
                    throw new Exception();
                }
                k(i4).add(Integer.valueOf(i3));
                return i2 + 2;
            } catch (Exception unused) {
                throw new ParseException("A numeric value between 1 and 5 must follow the '#' option", i5);
            }
        }
        if (charAt != '-') {
            if (charAt != '/') {
                a(i3, -1, 0, i4);
                return i2 + 1;
            }
            int i6 = i2 + 1;
            if (i6 >= str.length() || str.charAt(i6) == ' ' || str.charAt(i6) == '\t') {
                throw new ParseException("'/' must be followed by an integer.", i2);
            }
            int parseInt2 = Integer.parseInt(String.valueOf(str.charAt(i6)));
            int i7 = i2 + 2;
            if (i7 >= str.length()) {
                c(parseInt2, i4, i7);
                a(i3, -1, parseInt2, i4);
                return i7;
            }
            char charAt2 = str.charAt(i7);
            if (charAt2 < '0' || charAt2 > '9') {
                throw new ParseException("Unexpected character '" + charAt2 + "' after '/'", i7);
            }
            ValueSet m2 = m(parseInt2, str, i7);
            int i8 = m2.f18127a;
            c(i8, i4, i7);
            a(i3, -1, i8, i4);
            return m2.f18128b;
        }
        int parseInt3 = Integer.parseInt(String.valueOf(str.charAt(i2 + 1)));
        int i9 = i2 + 2;
        if (i9 >= str.length()) {
            a(i3, parseInt3, 1, i4);
            return i9;
        }
        char charAt3 = str.charAt(i9);
        if (charAt3 >= '0' && charAt3 <= '9') {
            ValueSet m3 = m(parseInt3, str, i9);
            parseInt3 = m3.f18127a;
            i9 = m3.f18128b;
        }
        if (i9 >= str.length() || str.charAt(i9) != '/') {
            a(i3, parseInt3, 1, i4);
            return i9;
        }
        int parseInt4 = Integer.parseInt(String.valueOf(str.charAt(i9 + 1)));
        int i10 = i9 + 2;
        if (i10 >= str.length()) {
            a(i3, parseInt3, parseInt4, i4);
            return i10;
        }
        char charAt4 = str.charAt(i10);
        if (charAt4 < '0' || charAt4 > '9') {
            a(i3, parseInt3, parseInt4, i4);
            return i10;
        }
        ValueSet m4 = m(parseInt4, str, i10);
        a(i3, parseInt3, m4.f18127a, i4);
        return m4.f18128b;
    }

    protected int f(int i2, String str) {
        while (i2 < str.length() && (str.charAt(i2) != ' ' || str.charAt(i2) != '\t')) {
            i2++;
        }
        return i2;
    }

    public String g() {
        return this.cronExpression;
    }

    protected int h(String str) {
        Integer num = dayMap.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    protected int i(String str) {
        Integer num = monthMap.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    protected int j(String str, int i2) {
        return Integer.parseInt(str.substring(i2, f(i2, str)));
    }

    TreeSet k(int i2) {
        switch (i2) {
            case 0:
                return this.seconds;
            case 1:
                return this.minutes;
            case 2:
                return this.hours;
            case 3:
                return this.daysOfMonth;
            case 4:
                return this.months;
            case 5:
                return this.daysOfWeek;
            case 6:
                return this.years;
            default:
                return null;
        }
    }

    public TimeZone l() {
        if (this.timeZone == null) {
            this.timeZone = TimeZone.getDefault();
        }
        return this.timeZone;
    }

    protected ValueSet m(int i2, String str, int i3) {
        char charAt = str.charAt(i3);
        StringBuilder sb = new StringBuilder(String.valueOf(i2));
        while (charAt >= '0' && charAt <= '9') {
            sb.append(charAt);
            i3++;
            if (i3 >= str.length()) {
                break;
            }
            charAt = str.charAt(i3);
        }
        ValueSet valueSet = new ValueSet();
        if (i3 >= str.length()) {
            i3++;
        }
        valueSet.f18128b = i3;
        valueSet.f18127a = Integer.parseInt(sb.toString());
        return valueSet;
    }

    public void n(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    protected int o(int i2, String str) {
        while (i2 < str.length() && (str.charAt(i2) == ' ' || str.charAt(i2) == '\t')) {
            i2++;
        }
        return i2;
    }

    protected int p(int i2, String str, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int o2 = o(i2, str);
        if (o2 >= str.length()) {
            return o2;
        }
        char charAt = str.charAt(o2);
        if (charAt >= 'A' && charAt <= 'Z' && !str.equals("L") && !str.equals("LW") && !str.matches("^L-[0-9]*[W]?")) {
            int i8 = o2 + 3;
            String substring = str.substring(o2, i8);
            if (i3 == 4) {
                i7 = i(substring) + 1;
                if (i7 <= 0) {
                    throw new ParseException("Invalid Month value: '" + substring + "'", o2);
                }
                if (str.length() <= i8 || str.charAt(i8) != '-') {
                    i6 = -1;
                } else {
                    int i9 = o2 + 4;
                    String substring2 = str.substring(i9, o2 + 7);
                    i6 = i(substring2) + 1;
                    if (i6 <= 0) {
                        throw new ParseException("Invalid Month value: '" + substring2 + "'", i9);
                    }
                    o2 = i9;
                }
            } else {
                if (i3 != 5) {
                    throw new ParseException("Illegal characters for this position: '" + substring + "'", o2);
                }
                int h2 = h(substring);
                if (h2 < 0) {
                    throw new ParseException("Invalid Day-of-Week value: '" + substring + "'", o2);
                }
                if (str.length() > i8) {
                    char charAt2 = str.charAt(i8);
                    if (charAt2 == '-') {
                        int i10 = o2 + 4;
                        String substring3 = str.substring(i10, o2 + 7);
                        i6 = h(substring3);
                        if (i6 < 0) {
                            throw new ParseException("Invalid Day-of-Week value: '" + substring3 + "'", i10);
                        }
                        o2 = i10;
                        i7 = h2;
                    } else if (charAt2 == '#') {
                        o2 += 4;
                        try {
                            int parseInt = Integer.parseInt(str.substring(o2));
                            this.nthdayOfWeek = parseInt;
                            if (parseInt < 1 || parseInt > 5) {
                                throw new Exception();
                            }
                        } catch (Exception unused) {
                            throw new ParseException("A numeric value between 1 and 5 must follow the '#' option", o2);
                        }
                    } else if (charAt2 == 'L') {
                        this.lastdayOfWeek = true;
                        o2++;
                    }
                }
                i6 = -1;
                i7 = h2;
            }
            a(i7, i6, i6 == -1 ? 0 : 1, i3);
            return o2 + 3;
        }
        if (charAt == '?') {
            int i11 = o2 + 1;
            int i12 = o2 + 2;
            if (i12 < str.length() && str.charAt(i11) != ' ' && str.charAt(i12) != '\t') {
                throw new ParseException("Illegal character after '?': " + str.charAt(i11), i11);
            }
            if (i3 != 5 && i3 != 3) {
                throw new ParseException("'?' can only be specified for Day-of-Month or Day-of-Week.", i11);
            }
            if (i3 == 5 && !this.lastdayOfMonth && this.daysOfMonth.last().intValue() == NO_SPEC_INT) {
                throw new ParseException("'?' can only be specified for Day-of-Month -OR- Day-of-Week.", i11);
            }
            a(NO_SPEC_INT, -1, 0, i3);
            return i11;
        }
        if (charAt == '*' || charAt == '/') {
            if (charAt == '*' && (i5 = o2 + 1) >= str.length()) {
                a(ALL_SPEC_INT, -1, 0, i3);
                return i5;
            }
            if (charAt == '/' && ((i4 = o2 + 1) >= str.length() || str.charAt(i4) == ' ' || str.charAt(i4) == '\t')) {
                throw new ParseException("'/' must be followed by an integer.", o2);
            }
            if (charAt == '*') {
                o2++;
            }
            if (str.charAt(o2) == '/') {
                int i13 = o2 + 1;
                if (i13 >= str.length()) {
                    throw new ParseException("Unexpected end of string.", i13);
                }
                r7 = j(str, i13);
                o2 = r7 > 10 ? o2 + 3 : o2 + 2;
                c(r7, i3, o2);
            }
            a(ALL_SPEC_INT, -1, r7, i3);
            return o2;
        }
        if (charAt != 'L') {
            if (charAt < '0' || charAt > '9') {
                throw new ParseException("Unexpected character: " + charAt, o2);
            }
            int parseInt2 = Integer.parseInt(String.valueOf(charAt));
            int i14 = o2 + 1;
            if (i14 >= str.length()) {
                a(parseInt2, -1, -1, i3);
                return i14;
            }
            char charAt3 = str.charAt(i14);
            if (charAt3 >= '0' && charAt3 <= '9') {
                ValueSet m2 = m(parseInt2, str, i14);
                parseInt2 = m2.f18127a;
                i14 = m2.f18128b;
            }
            return e(i14, str, parseInt2, i3);
        }
        int i15 = o2 + 1;
        if (i3 == 3) {
            this.lastdayOfMonth = true;
        }
        if (i3 == 5) {
            a(7, 7, 0, i3);
        }
        if (i3 != 3 || str.length() <= i15) {
            return i15;
        }
        if (str.charAt(i15) == '-') {
            int i16 = o2 + 2;
            ValueSet m3 = m(0, str, i16);
            int i17 = m3.f18127a;
            this.lastdayOffset = i17;
            if (i17 > 30) {
                throw new ParseException("Offset from last day must be <= 30", i16);
            }
            i15 = m3.f18128b;
        }
        if (str.length() <= i15 || str.charAt(i15) != 'W') {
            return i15;
        }
        this.nearestWeekday = true;
        return i15 + 1;
    }

    public String toString() {
        return this.cronExpression;
    }

    public CronExpression(CronExpression cronExpression) {
        String g2 = cronExpression.g();
        this.cronExpression = g2;
        try {
            b(g2);
            if (cronExpression.l() != null) {
                n((TimeZone) cronExpression.l().clone());
            }
        } catch (ParseException unused) {
            throw new AssertionError();
        }
    }
}
