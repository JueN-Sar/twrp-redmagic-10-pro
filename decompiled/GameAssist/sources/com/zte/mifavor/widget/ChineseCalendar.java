package com.zte.mifavor.widget;

import android.annotation.SuppressLint;
import android.util.Log;
import cn.nubia.gameassist.view.NubiaTextClock;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressLint({"WrongConstant"})
/* loaded from: classes2.dex */
public final class ChineseCalendar extends GregorianCalendar {
    public static final int CHINESE_DATE = 803;
    public static final int CHINESE_EARTHLY_BRANCH = 807;
    public static final int CHINESE_HEAVENLY_STEM = 806;
    public static final int CHINESE_MONTH = 802;
    public static final int CHINESE_PRINCIPLE_TERM = 805;
    public static final int CHINESE_SECTIONAL_TERM = 804;
    public static final int CHINESE_TERM_OR_DATE = 888;
    public static final int CHINESE_YEAR = 801;
    public static final int CHINESE_ZODIAC = 808;
    private static final int baseChineseDate = 11;
    private static final int baseChineseMonth = 11;
    private static final int baseChineseYear = 1900;
    private static final int baseDate = 1;
    private static final int baseIndex = 0;
    private static final int baseMonth = 1;
    private static final int baseYear = 1901;
    private static final long serialVersionUID = 8;
    private boolean areChineseFieldsComputed;
    private boolean areSolarTermsComputed;
    private int chineseDate;
    private int chineseMonth;
    private int chineseYear;
    private boolean lastSetChinese;
    private int principleTerm;
    private int sectionalTerm;
    private static final String[] chineseWeekNames = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final String[] chineseMonthNames = {"", "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
    private static final String[] chineseDateNames = {"", "初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};
    private static final String[] principleTermNames = {"大寒", "雨水", "春分", "谷雨", "夏满", "夏至", "大暑", "处暑", "秋分", "霜降", "小雪", "冬至"};
    private static final String[] sectionalTermNames = {"小寒", "立春", "惊蛰", "清明", "立夏", "芒种", "小暑", "立秋", "白露", "寒露", "立冬", "大雪"};
    private static final String[] stemNames = {"", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] branchNames = {"", "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] animalNames = {"", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    private static final int[] bigLeapMonthYears = {6, 14, 19, 25, 33, 36, 38, 41, 44, 52, 55, 79, 117, 136, 147, 150, 155, 158, 185, 193};
    private static final char[][] sectionalTermMap = {new char[]{7, 6, 6, 6, 6, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5}, new char[]{5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 3, 4, 4, 3, 3, 3}, new char[]{6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5}, new char[]{5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4, 4, 5, 4, 4, 4, 4, 5}, new char[]{6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5}, new char[]{6, 6, 7, 7, 6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5}, new char[]{7, '\b', '\b', '\b', 7, 7, '\b', '\b', 7, 7, 7, '\b', 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 6, 6, 6, 7, 7}, new char[]{'\b', '\b', '\b', '\t', '\b', '\b', '\b', '\b', 7, '\b', '\b', '\b', 7, 7, '\b', '\b', 7, 7, 7, '\b', 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 7}, new char[]{'\b', '\b', '\b', '\t', '\b', '\b', '\b', '\b', 7, '\b', '\b', '\b', 7, 7, '\b', '\b', 7, 7, 7, '\b', 7, 7, 7, 7, 6, 7, 7, 7, 7}, new char[]{'\t', '\t', '\t', '\t', '\b', '\t', '\t', '\t', '\b', '\b', '\t', '\t', '\b', '\b', '\b', '\t', '\b', '\b', '\b', '\b', 7, '\b', '\b', '\b', 7, 7, '\b', '\b', '\b'}, new char[]{'\b', '\b', '\b', '\b', 7, '\b', '\b', '\b', 7, 7, '\b', '\b', 7, 7, 7, '\b', 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 7}, new char[]{7, '\b', '\b', '\b', 7, 7, '\b', '\b', 7, 7, 7, '\b', 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 6, 6, 6, 7, 7}};
    private static final char[][] sectionalTermYear = {new char[]{'\r', '1', 'U', 'u', 149, 185, 201, 250, 250}, new char[]{'\r', '-', 'Q', 'u', 149, 185, 201, 250, 250}, new char[]{'\r', '0', 'T', 'p', 148, 184, 200, 201, 250}, new char[]{'\r', '-', 'L', 'l', 140, 172, 200, 201, 250}, new char[]{'\r', ',', 'H', 'h', 132, 168, 200, 201, 250}, new char[]{5, '!', 'D', '`', '|', 152, 188, 200, 201}, new char[]{29, '9', 'U', 'x', 148, 176, 200, 201, 250}, new char[]{'\r', '0', 'L', 'h', 132, 168, 196, 200, 201}, new char[]{25, '<', 'X', 'x', 148, 184, 200, 201, 250}, new char[]{16, ',', 'L', 'l', 144, 172, 200, 201, 250}, new char[]{28, '<', '\\', '|', 160, 192, 200, 201, 250}, new char[]{17, '5', 'U', '|', 156, 188, 200, 201, 250}};
    private static final char[][] principleTermMap = {new char[]{21, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 20, 20, 20, 20, 20, 19, 20, 20, 20, 19, 19, 20}, new char[]{20, 19, 19, 20, 20, 19, 19, 19, 19, 19, 19, 19, 19, 18, 19, 19, 19, 18, 18, 19, 19, 18, 18, 18, 18, 18, 18, 18}, new char[]{21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 20, 20, 20, 20, 19, 20, 20, 20, 20}, new char[]{20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 20, 20, 20, 20, 19, 20, 20, 20, 19, 19, 20, 20, 19, 19, 19, 20, 20}, new char[]{21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 21}, new char[]{22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 21}, new char[]{23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 23}, new char[]{23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 23}, new char[]{23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 23}, new char[]{24, 24, 24, 24, 23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 23}, new char[]{23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 22}, new char[]{22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 22}};
    private static final char[][] principleTermYear = {new char[]{'\r', '-', 'Q', 'q', 149, 185, 201}, new char[]{21, '9', ']', '}', 161, 193, 201}, new char[]{21, '8', 'X', 'x', 152, 188, 200, 201}, new char[]{21, '1', 'Q', 't', 144, 176, 200, 201}, new char[]{17, '1', 'M', 'p', 140, 168, 200, 201}, new char[]{28, '<', 'X', 't', 148, 180, 200, 201}, new char[]{25, '5', 'T', 'p', 144, 172, 200, 201}, new char[]{29, '9', 'Y', 'x', 148, 180, 200, 201}, new char[]{17, '-', 'I', 'l', 140, 168, 200, 201}, new char[]{28, '<', '\\', '|', 160, 192, 200, 201}, new char[]{16, ',', 'P', 'p', 148, 180, 200, 201}, new char[]{17, '5', 'X', 'x', 156, 188, 200, 201}};
    private static final char[] daysInGregorianMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final char[] chineseMonths = {0, 4, 173, '\b', 'Z', 1, 213, 'T', 180, '\t', 'd', 5, 'Y', 'E', 149, '\n', 166, 4, 'U', '$', 173, '\b', 'Z', 'b', 218, 4, 180, 5, 180, 'U', 'R', '\r', 148, '\n', 'J', '*', 'V', 2, 'm', 'q', 'm', 1, 218, 2, 210, 'R', 169, 5, 'I', '\r', '*', 'E', '+', '\t', 'V', 1, 181, ' ', 'm', 1, 'Y', 'i', 212, '\n', 168, 5, 169, 'V', 165, 4, '+', '\t', 158, '8', 182, '\b', 236, 't', 'l', 5, 212, '\n', 228, 'j', 'R', 5, 149, '\n', 'Z', 'B', '[', 4, 182, 4, 180, '\"', 'j', 5, 'R', 'u', 201, '\n', 'R', 5, '5', 'U', 'M', '\n', 'Z', 2, ']', '1', 181, 2, 'j', 138, 'h', 5, 169, '\n', 138, 'j', '*', 5, '-', '\t', 170, 'H', 'Z', 1, 181, '\t', 176, '9', 'd', 5, '%', 'u', 149, '\n', 150, 4, 'M', 'T', 173, 4, 218, 4, 212, 'D', 180, 5, 'T', 133, 'R', '\r', 146, '\n', 'V', 'j', 'V', 2, 'm', 2, 'j', 'A', 218, 2, 178, 161, 169, 5, 'I', '\r', '\n', 'm', '*', '\t', 'V', 1, 173, 'P', 'm', 1, 217, 2, 209, ':', 168, 5, ')', 133, 165, '\f', '*', '\t', 150, 'T', 182, '\b', 'l', '\t', 'd', 'E', 212, '\n', 164, 5, 'Q', '%', 149, '\n', '*', 'r', '[', 4, 182, 4, 172, 'R', 'j', 5, 210, '\n', 162, 'J', 'J', 5, 'U', 148, '-', '\n', 'Z', 2, 'u', 'a', 181, 2, 'j', 3, 'a', 'E', 169, '\n', 'J', 5, '%', '%', '-', '\t', 154, 'h', 218, '\b', 180, '\t', 168, 'Y', 'T', 3, 165, '\n', 145, ':', 150, 4, 173, 176, 173, 4, 218, 4, 244, 'b', 180, 5, 'T', 11, 'D', ']', 'R', '\n', 149, 4, 'U', '\"', 'm', 2, 'Z', 'q', 218, 2, 170, 5, 178, 'U', 'I', 11, 'J', '\n', '-', '9', '6', 1, 'm', 128, 'm', 1, 217, 2, 233, 'j', 168, 5, ')', 11, 154, 'L', 170, '\b', 182, '\b', 180, '8', 'l', '\t', 'T', 'u', 212, '\n', 164, 5, 'E', 'U', 149, '\n', 154, 4, 'U', 'D', 181, 4, 'j', 130, 'j', 5, 210, '\n', 146, 'j', 'J', 5, 'U', '\n', '*', 'J', 'Z', 2, 181, 2, 178, '1', 'i', 3, '1', NubiaTextClock.SECONDS, 169, '\n', 'J', 5, '-', 'U', '-', '\t', 'Z', 1, 213, 'H', 180, '\t', 'h', 137, 'T', 11, 164, '\n', 165, 'j', 149, 4, 173, '\b', 'j', 'D', 218, 4, 't', 5, 176, '%', 'T', 3};

    public ChineseCalendar() {
    }

    private void c() {
        int internalGet = internalGet(1);
        int internalGet2 = internalGet(2) + 1;
        int internalGet3 = internalGet(5);
        int i2 = baseYear;
        if (internalGet < baseYear || internalGet > 2100) {
            return;
        }
        if (internalGet < 2000) {
            this.chineseYear = baseChineseYear;
            this.chineseMonth = 11;
            this.chineseDate = 11;
        } else {
            this.chineseYear = 1999;
            this.chineseMonth = 11;
            this.chineseDate = 25;
            i2 = 2000;
        }
        int i3 = 0;
        while (i2 < internalGet) {
            i3 = w(i2) ? i3 + 366 : i3 + 365;
            i2++;
        }
        for (int i4 = 1; i4 < internalGet2; i4++) {
            i3 += n(internalGet, i4 - 1);
        }
        this.chineseDate += i3 + (internalGet3 - 1);
        int l2 = l(this.chineseYear, this.chineseMonth);
        while (true) {
            int i5 = this.chineseDate;
            if (i5 <= l2) {
                return;
            }
            this.chineseDate = i5 - l2;
            int x = x(this.chineseYear, this.chineseMonth);
            this.chineseMonth = x;
            if (x == 1) {
                this.chineseYear++;
            }
            l2 = l(this.chineseYear, x);
        }
    }

    private void f() {
        int i2 = this.chineseYear;
        int i3 = this.chineseMonth;
        int i4 = this.chineseDate;
        this.areChineseFieldsComputed = true;
        ((GregorianCalendar) this).areFieldsSet = true;
        int i5 = 0;
        this.lastSetChinese = false;
        if (i2 < baseChineseYear) {
            i2 = 1899;
        } else if (i2 > 2100) {
            i2 = 2101;
        }
        if (i3 < -12) {
            i3 = -12;
        } else if (i3 > 12) {
            i3 = 12;
        }
        if (i4 < 1) {
            i4 = 1;
        } else if (i4 > 30) {
            i4 = 30;
        }
        int abs = (i2 * 10000) + (Math.abs(i3) * 100) + i4;
        if (abs < 19001111) {
            set(baseYear, 0, 1);
            super.complete();
        } else if (abs > 21001201) {
            set(2100, 11, 31);
            super.complete();
        } else {
            int i6 = Math.abs(i3) <= 12 ? i3 : 12;
            int l2 = l(i2, i6);
            if (l2 == 0) {
                i6 = -i6;
                l2 = l(i2, i6);
            }
            if (i4 > l2) {
                i4 = l2;
            }
            set(i2, Math.abs(i6) - 1, i4);
            c();
            while (true) {
                int i7 = this.chineseYear;
                if (i7 == i2 && this.chineseMonth == i6) {
                    break;
                }
                i5 += l(i7, this.chineseMonth);
                int x = x(this.chineseYear, this.chineseMonth);
                this.chineseMonth = x;
                if (x == 1) {
                    this.chineseYear++;
                }
            }
            super.add(5, i5 + (i4 - this.chineseDate));
        }
        c();
    }

    private void h(int i2) {
        if (!u(i2)) {
            if (!this.lastSetChinese || ((GregorianCalendar) this).areFieldsSet) {
                return;
            }
            f();
            super.complete();
            ((GregorianCalendar) this).areFieldsSet = true;
            this.areChineseFieldsComputed = true;
            this.areSolarTermsComputed = false;
            return;
        }
        if (!this.lastSetChinese && !this.areChineseFieldsComputed) {
            super.complete();
            c();
            ((GregorianCalendar) this).areFieldsSet = true;
            this.areChineseFieldsComputed = true;
            this.areSolarTermsComputed = false;
        }
        if (!v(i2) || this.areSolarTermsComputed) {
            return;
        }
        j();
        this.areSolarTermsComputed = true;
    }

    private void j() {
        int internalGet = internalGet(1);
        int internalGet2 = internalGet(2);
        if (internalGet < baseYear || internalGet > 2100) {
            return;
        }
        this.sectionalTerm = z(internalGet, internalGet2);
        this.principleTerm = y(internalGet, internalGet2);
    }

    public static int l(int i2, int i3) {
        int i4 = i2 - 1900;
        if (1 > i3 || i3 > 8) {
            if (9 > i3 || i3 > 12) {
                int i5 = 0;
                if (((chineseMonths[(i4 * 2) + 1] >> 4) & 15) != Math.abs(i3)) {
                    return 0;
                }
                while (true) {
                    int[] iArr = bigLeapMonthYears;
                    if (i5 >= iArr.length) {
                        return 29;
                    }
                    if (iArr[i5] == i4) {
                        break;
                    }
                    i5++;
                }
            } else if (((chineseMonths[(i4 * 2) + 1] >> (i3 - 9)) & 1) == 1) {
                return 29;
            }
        } else if (((chineseMonths[i4 * 2] >> (i3 - 1)) & 1) == 1) {
            return 29;
        }
        return 30;
    }

    public static int n(int i2, int i3) {
        char c2 = daysInGregorianMonth[i3];
        return (i3 == 1 && w(i2)) ? c2 + 1 : c2;
    }

    public static int s(int i2) {
        return -((chineseMonths[((i2 - 1900) * 2) + 1] >> 4) & 15);
    }

    private boolean u(int i2) {
        if (i2 == 888) {
            return true;
        }
        switch (i2) {
            case CHINESE_YEAR /* 801 */:
            case CHINESE_MONTH /* 802 */:
            case CHINESE_DATE /* 803 */:
            case CHINESE_SECTIONAL_TERM /* 804 */:
            case CHINESE_PRINCIPLE_TERM /* 805 */:
            case CHINESE_HEAVENLY_STEM /* 806 */:
            case CHINESE_EARTHLY_BRANCH /* 807 */:
            case CHINESE_ZODIAC /* 808 */:
                return true;
            default:
                return false;
        }
    }

    private boolean v(int i2) {
        return i2 == 804 || i2 == 805 || i2 == 888;
    }

    public static boolean w(int i2) {
        boolean z = i2 % 100 != 0 ? i2 % 4 == 0 : false;
        if (i2 % 400 == 0) {
            return true;
        }
        return z;
    }

    public static int x(int i2, int i3) {
        int abs = Math.abs(i3) + 1;
        if (i3 > 0 && ((chineseMonths[((i2 - 1900) * 2) + 1] >> 4) & 15) == i3) {
            abs = -i3;
        }
        if (abs == 13) {
            return 1;
        }
        return abs;
    }

    public static int y(int i2, int i3) {
        int i4 = i3 + 1;
        int i5 = 0;
        if (i2 < baseYear || i2 > 2100) {
            return 0;
        }
        int i6 = i2 - 1900;
        while (i6 >= principleTermYear[i3][i5]) {
            i5++;
        }
        char c2 = principleTermMap[i3][(i5 * 4) + (i6 % 4)];
        if (i6 == 171 && i4 == 3) {
            c2 = 21;
        }
        if (i6 == 181 && i4 == 5) {
            return 21;
        }
        return c2;
    }

    public static int z(int i2, int i3) {
        int i4 = i3 + 1;
        int i5 = 0;
        if (i2 < baseYear || i2 > 2100) {
            return 0;
        }
        int i6 = i2 - 1900;
        while (i6 >= sectionalTermYear[i3][i5]) {
            i5++;
        }
        char c2 = sectionalTermMap[i3][(i5 * 4) + (i6 % 4)];
        if (i6 == 121 && i4 == 4) {
            c2 = 5;
        }
        char c3 = (i6 == 132 && i4 == 4) ? (char) 5 : c2;
        if (i6 == 194 && i4 == 6) {
            return 6;
        }
        return c3;
    }

    @Override // java.util.GregorianCalendar, java.util.Calendar
    public void add(int i2, int i3) {
        h(i2);
        if (!u(i2)) {
            super.add(i2, i3);
            this.lastSetChinese = false;
            this.areChineseFieldsComputed = false;
            this.areSolarTermsComputed = false;
            return;
        }
        switch (i2) {
            case CHINESE_YEAR /* 801 */:
                this.chineseYear += i3;
                break;
            case CHINESE_MONTH /* 802 */:
                for (int i4 = 0; i4 < i3; i4++) {
                    int x = x(this.chineseYear, this.chineseMonth);
                    this.chineseMonth = x;
                    if (x == 1) {
                        this.chineseYear++;
                    }
                }
                break;
            case CHINESE_DATE /* 803 */:
                int l2 = l(this.chineseYear, this.chineseMonth);
                for (int i5 = 0; i5 < i3; i5++) {
                    int i6 = this.chineseDate + 1;
                    this.chineseDate = i6;
                    if (i6 > l2) {
                        this.chineseDate = 1;
                        int x2 = x(this.chineseYear, this.chineseMonth);
                        this.chineseMonth = x2;
                        if (x2 == 1) {
                            this.chineseYear++;
                        }
                        l2 = l(this.chineseYear, x2);
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("不支持的field：" + i2);
        }
        this.lastSetChinese = true;
        ((GregorianCalendar) this).areFieldsSet = false;
        this.areChineseFieldsComputed = false;
        this.areSolarTermsComputed = false;
    }

    public Calendar e(int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        if (i2 < baseChineseYear) {
            i2 = 1899;
        } else if (i2 > 2100) {
            i2 = 2101;
        }
        if (i3 < -12) {
            i3 = -12;
        } else if (i3 > 12) {
            i3 = 12;
        }
        if (i4 < 1) {
            i4 = 1;
        } else if (i4 > 30) {
            i4 = 30;
        }
        Log.w("wweer", "chinese: y:  " + i2 + "  m:   " + i3 + "     d:  " + i4);
        int abs = (i2 * 10000) + (Math.abs(i3) * 100) + i4;
        int i5 = 0;
        if (abs < 19001111) {
            set(baseYear, 0, 1);
            super.complete();
        } else if (abs > 21001201) {
            set(2100, 11, 31);
            super.complete();
        } else {
            int i6 = Math.abs(i3) <= 12 ? i3 : 12;
            int l2 = l(i2, i6);
            if (l2 == 0) {
                i6 = -i6;
                l2 = l(i2, i6);
            }
            if (i4 > l2) {
                i4 = l2;
            }
            set(i2, Math.abs(i6) - 1, i4);
            c();
            while (true) {
                int i7 = this.chineseYear;
                if ((i7 != i2 || this.chineseMonth != i6) && i7 >= baseChineseYear && i7 <= 2100) {
                    i5 += l(i7, this.chineseMonth);
                    int x = x(this.chineseYear, this.chineseMonth);
                    this.chineseMonth = x;
                    if (x == 1) {
                        this.chineseYear++;
                    }
                }
            }
            super.add(5, i5 + (i4 - this.chineseDate));
        }
        int internalGet = internalGet(1);
        int internalGet2 = internalGet(2);
        int internalGet3 = internalGet(5);
        calendar.set(1, internalGet);
        calendar.set(2, internalGet2);
        calendar.set(5, internalGet3);
        return calendar;
    }

    @Override // java.util.Calendar
    public int get(int i2) {
        int i3;
        h(i2);
        if (!u(i2)) {
            return super.get(i2);
        }
        if (i2 == 888) {
            return get(5) == get(CHINESE_SECTIONAL_TERM) ? CHINESE_SECTIONAL_TERM : get(5) == get(CHINESE_PRINCIPLE_TERM) ? CHINESE_PRINCIPLE_TERM : get(CHINESE_DATE) == 1 ? CHINESE_MONTH : CHINESE_DATE;
        }
        switch (i2) {
            case CHINESE_YEAR /* 801 */:
                return this.chineseYear;
            case CHINESE_MONTH /* 802 */:
                return this.chineseMonth;
            case CHINESE_DATE /* 803 */:
                return this.chineseDate;
            case CHINESE_SECTIONAL_TERM /* 804 */:
                return this.sectionalTerm;
            case CHINESE_PRINCIPLE_TERM /* 805 */:
                return this.principleTerm;
            case CHINESE_HEAVENLY_STEM /* 806 */:
                i3 = (this.chineseYear - 4) % 10;
                break;
            case CHINESE_EARTHLY_BRANCH /* 807 */:
            case CHINESE_ZODIAC /* 808 */:
                i3 = (this.chineseYear - 4) % 12;
                break;
            default:
                throw new IllegalArgumentException("不支持的field获取：" + i2);
        }
        return i3 + 1;
    }

    public String o(int i2) {
        h(i2);
        if (i2 == 7) {
            return chineseWeekNames[get(i2)];
        }
        if (i2 == 888) {
            return o(get(CHINESE_TERM_OR_DATE));
        }
        switch (i2) {
            case CHINESE_YEAR /* 801 */:
                return o(CHINESE_HEAVENLY_STEM) + o(CHINESE_EARTHLY_BRANCH);
            case CHINESE_MONTH /* 802 */:
                if (this.chineseMonth > 0) {
                    return chineseMonthNames[this.chineseMonth] + "月";
                }
                return "闰" + chineseMonthNames[-this.chineseMonth] + "月";
            case CHINESE_DATE /* 803 */:
                return chineseDateNames[this.chineseDate];
            case CHINESE_SECTIONAL_TERM /* 804 */:
                return sectionalTermNames[get(2)];
            case CHINESE_PRINCIPLE_TERM /* 805 */:
                return principleTermNames[get(2)];
            case CHINESE_HEAVENLY_STEM /* 806 */:
                return stemNames[get(i2)];
            case CHINESE_EARTHLY_BRANCH /* 807 */:
                return branchNames[get(i2)];
            case CHINESE_ZODIAC /* 808 */:
                return animalNames[get(i2)];
            default:
                throw new IllegalArgumentException("不支持的field中文获取：" + i2);
        }
    }

    public String p() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(o(CHINESE_YEAR));
        stringBuffer.append(o(CHINESE_MONTH));
        stringBuffer.append(o(CHINESE_DATE));
        return stringBuffer.toString();
    }

    public String r() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(get(1));
        stringBuffer.append("年");
        stringBuffer.append(o(CHINESE_MONTH));
        stringBuffer.append(o(CHINESE_DATE));
        stringBuffer.append(" ");
        stringBuffer.append(o(7));
        return stringBuffer.toString().replace("十一月", "冬月").replace("十二月", "腊月");
    }

    @Override // java.util.GregorianCalendar, java.util.Calendar
    public void roll(int i2, int i3) {
        h(i2);
        if (!u(i2)) {
            super.roll(i2, i3);
            this.lastSetChinese = false;
            this.areChineseFieldsComputed = false;
            this.areSolarTermsComputed = false;
            return;
        }
        switch (i2) {
            case CHINESE_YEAR /* 801 */:
                this.chineseYear += i3;
                break;
            case CHINESE_MONTH /* 802 */:
                for (int i4 = 0; i4 < i3; i4++) {
                    this.chineseMonth = x(this.chineseYear, this.chineseMonth);
                }
                break;
            case CHINESE_DATE /* 803 */:
                int l2 = l(this.chineseYear, this.chineseMonth);
                for (int i5 = 0; i5 < i3; i5++) {
                    int i6 = this.chineseDate + 1;
                    this.chineseDate = i6;
                    if (i6 > l2) {
                        this.chineseDate = 1;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("不支持的field：" + i2);
        }
        this.lastSetChinese = true;
        ((GregorianCalendar) this).areFieldsSet = false;
        this.areChineseFieldsComputed = false;
        this.areSolarTermsComputed = false;
    }

    @Override // java.util.Calendar
    public void set(int i2, int i3) {
        h(i2);
        if (u(i2)) {
            switch (i2) {
                case CHINESE_YEAR /* 801 */:
                    this.chineseYear = i3;
                    break;
                case CHINESE_MONTH /* 802 */:
                    this.chineseMonth = i3;
                    break;
                case CHINESE_DATE /* 803 */:
                    this.chineseDate = i3;
                    break;
                default:
                    throw new IllegalArgumentException("不支持的field设置：" + i2);
            }
            this.lastSetChinese = true;
        } else {
            super.set(i2, i3);
            this.lastSetChinese = false;
        }
        ((GregorianCalendar) this).areFieldsSet = false;
        this.areChineseFieldsComputed = false;
        this.areSolarTermsComputed = false;
    }

    public String t() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(get(1));
        stringBuffer.append("-");
        stringBuffer.append(get(2) + 1);
        stringBuffer.append("-");
        stringBuffer.append(get(5));
        return stringBuffer.toString();
    }

    @Override // java.util.Calendar
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(t());
        stringBuffer.append(" | ");
        stringBuffer.append(o(7));
        stringBuffer.append(" | [农历]");
        stringBuffer.append(p());
        stringBuffer.append(" ");
        stringBuffer.append(o(CHINESE_ZODIAC));
        stringBuffer.append("年 ");
        stringBuffer.append(get(CHINESE_SECTIONAL_TERM));
        stringBuffer.append("日");
        stringBuffer.append(o(CHINESE_SECTIONAL_TERM));
        stringBuffer.append(" ");
        stringBuffer.append(get(CHINESE_PRINCIPLE_TERM));
        stringBuffer.append("日");
        stringBuffer.append(o(CHINESE_PRINCIPLE_TERM));
        return stringBuffer.toString();
    }

    public ChineseCalendar(Date date) {
        super.setTime(date);
    }

    public ChineseCalendar(Calendar calendar) {
        this(calendar.getTime());
    }

    public ChineseCalendar(int i2, int i3, int i4) {
        super(i2, i3, i4);
    }

    public ChineseCalendar(boolean z, int i2, int i3, int i4) {
        if (z) {
            set(CHINESE_YEAR, i2);
            set(CHINESE_MONTH, i3);
            set(CHINESE_DATE, i4);
            return;
        }
        set(i2, i3, i4);
    }
}
