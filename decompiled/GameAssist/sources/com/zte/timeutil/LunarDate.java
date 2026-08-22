package com.zte.timeutil;

import com.zte.shared.wrapper.WindowManagerWrapper;
import com.zte.timeutil.calculator.DateTimeCalculatorUtil;
import com.zte.timeutil.converter.DateTimeConverterUtil;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/* loaded from: classes2.dex */
public final class LunarDate implements Temporal, Serializable {
    private static final long serialVersionUID = 7999322619343295974L;
    private Date date;
    private String gDate;
    private int gDay;
    private int gMonth;
    private int gYear;
    private String lAnimal;
    private String lDate;
    private String lDateCn;
    private int lDay;
    private String lDayCn;
    private int lMonth;
    private String lMonthCn;
    private int lYear;
    private String lYearCn;
    private String leapMonthCn;
    private final LocalDate localDate;
    private String solarTerm;
    private String suiCi;
    private String weekCn;
    private static final long[] lunarInfo = {19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 92821, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42416, 83315, 21168, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46752, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 23232, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19195, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448, 84835, 37744, 18936, 18800, 25776, 92326, 59984, 27424, 108228, 43744, 37600, 53987, 51552, 54615, 54432, 55888, 23893, 22176, 42704, 21972, 21200, 43448, 43344, 46240, 46758, 44368, 21920, 43940, 42416, 21168, 45683, 26928, 29495, 27296, 44368, 84821, 19296, 42352, 21732, 53600, 59752, 54560, 55968, 92838, 22224, 19168, 43476, 41680, 53584, 62034, 54560};
    public static final String[] lunarMonth = {"", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
    private static final String[] tianGan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] diZhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] animals = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    public static final String[] numStr = {"〇", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    public static final String[] solarTerms = {"小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"};
    private static final long[] solarTermInfo = {0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758};

    private LunarDate(LocalDate localDate) {
        this.localDate = localDate;
        try {
            u();
        } catch (Exception e2) {
            System.err.println("new LunarDate has error: " + e2.getMessage());
        }
    }

    public static final int A(int i2, int i3) {
        return (((long) (65536 >> i3)) & lunarInfo[i2 + (-1900)]) == 0 ? 29 : 30;
    }

    public static LunarDate B(int i2, int i3, int i4) {
        return C(i2, i3, i4, v(i2) == i3);
    }

    public static LunarDate C(int i2, int i3, int i4, boolean z) {
        return j(y(i2, i3, i4, z));
    }

    public static final int D(int i2, int i3) {
        return DateTimeCalculatorUtil.q(LocalDateTime.of(1900, 1, 6, 2, 5), (long) (((i2 - 1900) * 3.15569259747E10d) + (solarTermInfo[i3] * 60000))).getDayOfMonth();
    }

    public static final String a(int i2) {
        return animals[(i2 - 4) % 12];
    }

    public static final long[] b(int i2, int i3, int i4) {
        long j2;
        long[] jArr = new long[8];
        int i5 = 1900;
        long a2 = DateTimeCalculatorUtil.a(LocalDate.of(1900, 1, 31).atStartOfDay(), LocalDate.of(i2, i3, i4).atStartOfDay());
        jArr[5] = 40 + a2;
        jArr[4] = 14;
        int i6 = 0;
        while (i5 <= 2100 && a2 > 0) {
            i6 = z(i5);
            a2 -= i6;
            jArr[4] = jArr[4] + 12;
            i5++;
        }
        if (a2 < 0) {
            a2 += i6;
            i5--;
            jArr[4] = jArr[4] - 12;
        }
        jArr[0] = i5;
        jArr[3] = i5 - 1864;
        int v = v(i5);
        long j3 = 0;
        jArr[6] = 0;
        int i7 = 1;
        while (i7 < 13 && a2 > j3) {
            if (v > 0 && i7 == v + 1 && jArr[6] == j3) {
                i7--;
                jArr[6] = 1;
                i6 = w((int) jArr[0]);
            } else {
                i6 = A((int) jArr[0], i7);
            }
            if (jArr[6] == 1 && i7 == v + 1) {
                j2 = 0;
                jArr[6] = 0;
            } else {
                j2 = 0;
            }
            a2 -= i6;
            if (jArr[6] == j2) {
                jArr[4] = jArr[4] + 1;
            }
            i7++;
            j3 = 0;
        }
        if (a2 == j3 && v > 0 && i7 == v + 1) {
            if (jArr[6] == 1) {
                jArr[6] = j3;
            } else {
                jArr[6] = 1;
                i7--;
                jArr[4] = jArr[4] - 1;
            }
        }
        if (a2 < j3) {
            a2 += i6;
            i7--;
            jArr[4] = jArr[4] - 1;
        }
        jArr[1] = i7;
        jArr[2] = a2 + 1;
        int i8 = (i3 - 1) * 2;
        int D = D(i2, i8);
        int i9 = i8 + 1;
        int D2 = D(i2, i9);
        if (i4 != D) {
            i8 = i4 == D2 ? i9 : -1;
        }
        jArr[7] = i8;
        return jArr;
    }

    public static LocalDate c(Date date) {
        if (date != null) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        throw new IllegalArgumentException("The date parameter cannot be null");
    }

    public static final String d(int i2) {
        return e(i2 - 1864);
    }

    private static final String e(int i2) {
        return tianGan[i2 % 10] + diZhi[i2 % 12];
    }

    public static LunarDate g(LocalDate localDate) {
        return new LunarDate(localDate);
    }

    public static LunarDate h(LocalDateTime localDateTime) {
        return new LunarDate(DateTimeConverterUtil.c(localDateTime));
    }

    public static LunarDate i(Temporal temporal) {
        return new LunarDate(DateTimeConverterUtil.e(temporal));
    }

    public static LunarDate j(Date date) {
        return new LunarDate(DateTimeConverterUtil.f(date));
    }

    public static final String k(int i2) {
        if (i2 == 10) {
            return "初十";
        }
        if (i2 == 20) {
            return "二十";
        }
        if (i2 == 30) {
            return "三十";
        }
        int i3 = i2 / 10;
        String str = i3 == 0 ? "初" : "";
        if (i3 == 1) {
            str = "十";
        }
        if (i3 == 2) {
            str = "廿";
        }
        if (i3 == 3) {
            str = "三";
        }
        switch (i2 % 10) {
            case 1:
                return str + "一";
            case 2:
                return str + "二";
            case 3:
                return str + "三";
            case 4:
                return str + "四";
            case 5:
                return str + "五";
            case 6:
                return str + "六";
            case 7:
                return str + "七";
            case 8:
                return str + "八";
            case 9:
                return str + "九";
            default:
                return str + "";
        }
    }

    public static final String l(int i2) {
        String[] strArr = numStr;
        String str = strArr[i2 % 10];
        String str2 = strArr[(i2 / 10) % 10];
        String str3 = strArr[(i2 / 100) % 10];
        return strArr[(i2 / 1000) % 10] + str3 + str2 + str;
    }

    public static final String q(int i2) {
        switch (i2) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期日";
            default:
                return "";
        }
    }

    public static final int v(int i2) {
        return (int) (lunarInfo[i2 - 1900] & 15);
    }

    public static final int w(int i2) {
        if (v(i2) != 0) {
            return (lunarInfo[i2 + (-1900)] & 65536) != 0 ? 30 : 29;
        }
        return 0;
    }

    public static LocalDate x(LunarDate lunarDate) {
        int i2 = lunarDate.lYear;
        return c(y(i2, lunarDate.lMonth, lunarDate.lDay, v(i2) == lunarDate.lMonth));
    }

    public static Date y(int i2, int i3, int i4, boolean z) {
        int v = v(i2);
        if (z && v != i3) {
            return null;
        }
        if (i2 != 2100 || i3 != 12 || i4 <= 1) {
            if (i2 != 1900 || i3 != 1 || i4 >= 31) {
                int A = A(i2, i3);
                int w = z ? w(i2) : A;
                if (i2 < 1900 || i2 > 2100 || i4 > w) {
                    return null;
                }
                boolean z2 = false;
                int i5 = 0;
                for (int i6 = 1900; i6 < i2; i6++) {
                    i5 += z(i6);
                }
                for (int i7 = 1; i7 < i3; i7++) {
                    int v2 = v(i2);
                    if (!z2 && v2 <= i7 && v2 > 0) {
                        i5 += w(i2);
                        z2 = true;
                    }
                    i5 += A(i2, i7);
                }
                if (z) {
                    i5 += A;
                }
                return new Date((((i5 + i4) - 31) * 86400000) - 2203804800000L);
            }
        }
        return null;
    }

    private static final int z(int i2) {
        int i3 = 348;
        for (int i4 = WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS; i4 > 8; i4 >>= 1) {
            if ((lunarInfo[i2 - 1900] & i4) != 0) {
                i3++;
            }
        }
        return i3 + w(i2);
    }

    public String f() {
        return String.format("%02d", Integer.valueOf(this.lMonth)) + String.format("%02d", Integer.valueOf(this.lDay));
    }

    @Override // java.time.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        return this.localDate.getLong(temporalField);
    }

    @Override // java.time.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        return this.localDate.isSupported(temporalField);
    }

    public Date m() {
        return this.date;
    }

    public String n() {
        return this.leapMonthCn;
    }

    public LocalDate o() {
        return this.localDate;
    }

    public String p() {
        return this.solarTerm;
    }

    @Override // java.time.temporal.Temporal
    public Temporal plus(long j2, TemporalUnit temporalUnit) {
        return this.localDate.plus(j2, temporalUnit);
    }

    public String r() {
        return this.lDateCn;
    }

    public String s() {
        return this.lDayCn;
    }

    public int t() {
        return this.lYear;
    }

    public String toString() {
        return "LunarDate [localDate=" + this.localDate + ",lDateCn=" + this.lDateCn + ", suiCi=" + this.suiCi + ", lAnimal=" + this.lAnimal + ", lYear=" + this.lYear + ", lMonth=" + this.lMonth + ", lDay=" + this.lDay + ", lYearCn=" + this.lYearCn + ", lMonthCn=" + this.lMonthCn + ", lDayCn=" + this.lDayCn + ", weekCn=" + this.weekCn + ", solarTerm=" + this.solarTerm + ", leapMonthCn=" + this.leapMonthCn + "]";
    }

    public void u() {
        int year = this.localDate.getYear();
        int monthValue = this.localDate.getMonthValue();
        int dayOfMonth = this.localDate.getDayOfMonth();
        this.gYear = year;
        this.gMonth = monthValue;
        this.gDay = dayOfMonth;
        this.date = DateTimeConverterUtil.a(this.localDate);
        long[] b2 = b(year, monthValue, dayOfMonth);
        int i2 = (int) b2[0];
        this.lYear = i2;
        this.lMonth = (int) b2[1];
        this.lDay = (int) b2[2];
        this.suiCi = d(i2);
        this.lAnimal = a(this.lYear);
        this.lYearCn = l(this.lYear);
        this.lMonthCn = lunarMonth[this.lMonth];
        this.lDayCn = k(this.lDay);
        this.weekCn = q(this.localDate.getDayOfWeek().getValue());
        long j2 = b2[7];
        if (j2 != -1) {
            this.solarTerm = solarTerms[(int) j2];
        } else {
            this.solarTerm = "";
        }
        if (b2[6] == 1) {
            this.leapMonthCn = "闰";
        } else {
            this.leapMonthCn = "";
        }
        this.lDateCn = this.lYearCn + "年" + this.leapMonthCn + this.lMonthCn + "月" + this.lDayCn;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%04d", Integer.valueOf(this.lYear)));
        sb.append("-");
        sb.append(String.format("%02d", Integer.valueOf(this.lMonth)));
        sb.append("-");
        sb.append(String.format("%02d", Integer.valueOf(this.lDay)));
        this.lDate = sb.toString();
        this.gDate = this.localDate.toString();
    }

    @Override // java.time.temporal.Temporal
    public long until(Temporal temporal, TemporalUnit temporalUnit) {
        return this.localDate.until(temporal, temporalUnit);
    }

    @Override // java.time.temporal.Temporal
    public Temporal with(TemporalField temporalField, long j2) {
        return this.localDate.with(temporalField, j2);
    }

    @Override // java.time.temporal.Temporal
    public boolean isSupported(TemporalUnit temporalUnit) {
        return this.localDate.isSupported(temporalUnit);
    }
}
