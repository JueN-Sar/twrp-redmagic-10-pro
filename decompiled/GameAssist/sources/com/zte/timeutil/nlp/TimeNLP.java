package com.zte.timeutil.nlp;

import com.zte.timeutil.calculator.DateTimeCalculatorUtil;
import com.zte.timeutil.converter.DateTimeConverterUtil;
import com.zte.timeutil.enums.MomentEnum;
import com.zte.timeutil.enums.RegexEnum;
import com.zte.timeutil.formatter.DateTimeFormatterUtil;
import com.zte.timeutil.formatter.ParseResult;
import com.zte.timeutil.holiday.ChineseHolidayEnum;
import com.zte.timeutil.holiday.LocalHolidayEnum;
import com.zte.timeutil.utils.ArrayUtil;
import com.zte.timeutil.utils.CollectionUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class TimeNLP {
    private static Map u;

    /* renamed from: a, reason: collision with root package name */
    private String f18168a;

    /* renamed from: b, reason: collision with root package name */
    private int f18169b;

    /* renamed from: c, reason: collision with root package name */
    private int f18170c;

    /* renamed from: f, reason: collision with root package name */
    private Date f18173f;

    /* renamed from: g, reason: collision with root package name */
    private LocalDateTime f18174g;

    /* renamed from: j, reason: collision with root package name */
    private TextAnalysis f18177j;

    /* renamed from: l, reason: collision with root package name */
    private TimeContext f18179l;

    /* renamed from: p, reason: collision with root package name */
    private Date f18183p;

    /* renamed from: q, reason: collision with root package name */
    private Date f18184q;

    /* renamed from: d, reason: collision with root package name */
    private String f18171d = "";

    /* renamed from: e, reason: collision with root package name */
    private String f18172e = "";

    /* renamed from: h, reason: collision with root package name */
    private Boolean f18175h = Boolean.TRUE;

    /* renamed from: i, reason: collision with root package name */
    private boolean f18176i = true;

    /* renamed from: k, reason: collision with root package name */
    private TimeContext f18178k = new TimeContext();

    /* renamed from: m, reason: collision with root package name */
    private boolean f18180m = false;

    /* renamed from: n, reason: collision with root package name */
    private boolean f18181n = false;

    /* renamed from: o, reason: collision with root package name */
    private boolean f18182o = false;

    /* renamed from: r, reason: collision with root package name */
    private int f18185r = -1;

    /* renamed from: s, reason: collision with root package name */
    private int f18186s = 0;
    private boolean t = false;

    static {
        HashMap hashMap = new HashMap();
        u = hashMap;
        hashMap.put(0, ChronoField.YEAR);
        u.put(1, ChronoField.MONTH_OF_YEAR);
        u.put(2, ChronoField.DAY_OF_MONTH);
        u.put(3, ChronoField.HOUR_OF_DAY);
        u.put(4, ChronoField.MINUTE_OF_HOUR);
        u.put(5, ChronoField.SECOND_OF_MINUTE);
        u.put(10, ChronoUnit.YEARS);
        u.put(11, ChronoUnit.MONTHS);
        u.put(12, ChronoUnit.DAYS);
        u.put(13, ChronoUnit.HOURS);
        u.put(14, ChronoUnit.MINUTES);
        u.put(15, ChronoUnit.SECONDS);
    }

    public TimeNLP(TimeText timeText, TextAnalysis textAnalysis, TimeContext timeContext) {
        this.f18168a = null;
        this.f18177j = null;
        this.f18179l = new TimeContext();
        this.f18168a = TextPreprocess.d(timeText.c());
        this.f18169b = timeText.b();
        this.f18170c = timeText.a();
        this.f18177j = textAnalysis;
        this.f18179l = timeContext;
        J();
    }

    private void A(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f18178k.c()[i3] != -1) {
                return;
            }
        }
        a(i2);
        for (int i4 = 0; i4 < i2; i4++) {
            if (this.f18178k.c()[i4] != -1) {
                return;
            }
        }
        if (this.f18177j.c()) {
            LocalDateTime now = LocalDateTime.now();
            if (this.f18179l.b() != null) {
                String[] split = this.f18179l.b().split("-");
                now = LocalDateTime.of(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue(), Integer.valueOf(split[2]).intValue(), Integer.valueOf(split[3]).intValue(), Integer.valueOf(split[4]).intValue(), Integer.valueOf(split[5]).intValue());
            }
            int i5 = now.get((TemporalField) u.get(Integer.valueOf(i2)));
            if (i2 != 1 || this.f18178k.c()[1] > i5) {
                if (i2 == 3 && this.f18178k.c()[3] >= 0 && this.f18178k.c()[3] <= 11) {
                    Matcher matcher = RegexEnum.NormHourAfternoon.d().matcher(this.f18168a);
                    Matcher matcher2 = RegexEnum.NormHourNight.d().matcher(this.f18168a);
                    if ((matcher.find() || matcher2.find()) && i5 < this.f18178k.c()[3] + 12) {
                        return;
                    }
                } else if (i5 < this.f18178k.c()[i2]) {
                    return;
                }
                LocalDateTime plus = now.plus(1L, (TemporalUnit) u.get(Integer.valueOf(i2 + 9)));
                for (int i6 = 0; i6 < i2; i6++) {
                    this.f18178k.c()[i6] = plus.get((TemporalField) u.get(Integer.valueOf(i6)));
                }
            }
        }
    }

    private LocalDateTime B(int i2, LocalDateTime localDateTime) {
        if (!this.f18177j.c()) {
            return localDateTime;
        }
        for (int i3 = 0; i3 < 2; i3++) {
            if (this.f18178k.c()[i3] != -1) {
                return localDateTime;
            }
        }
        LocalDateTime now = LocalDateTime.now();
        if (this.f18179l.b() != null) {
            String[] split = this.f18179l.b().split("-");
            now = LocalDateTime.of(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue(), Integer.valueOf(split[2]).intValue(), Integer.valueOf(split[3]).intValue(), Integer.valueOf(split[4]).intValue(), Integer.valueOf(split[5]).intValue());
        }
        return now.get(ChronoField.DAY_OF_WEEK) < i2 ? localDateTime : localDateTime.plusWeeks(1L);
    }

    private void C(int i2) {
        LocalDate now = LocalDate.now();
        if (this.f18178k.c()[0] == -1) {
            this.f18178k.c()[0] = now.getYear();
        }
        if (this.f18178k.c()[1] == -1) {
            this.f18178k.c()[1] = now.getMonthValue();
        }
        this.f18178k.c()[2] = now.getDayOfMonth();
        this.f18180m = true;
        this.f18185r = 6;
        this.f18186s = i2;
    }

    private void D(int i2) {
        LocalDateTime atTime = LocalDate.now().atTime(LocalTime.now());
        if (this.f18178k.c()[0] == -1) {
            this.f18178k.c()[0] = atTime.getYear();
        }
        if (this.f18178k.c()[1] == -1) {
            this.f18178k.c()[1] = atTime.getMonthValue();
        }
        if (this.f18178k.c()[2] == -1) {
            this.f18178k.c()[2] = atTime.getDayOfMonth();
        }
        this.f18178k.c()[3] = atTime.getHour();
        this.f18180m = true;
        this.f18185r = 7;
        this.f18186s = i2;
    }

    private void E(int i2) {
        LocalDateTime atTime = LocalDate.now().atTime(LocalTime.now());
        if (this.f18178k.c()[0] == -1) {
            this.f18178k.c()[0] = atTime.getYear();
        }
        if (this.f18178k.c()[1] == -1) {
            this.f18178k.c()[1] = atTime.getMonthValue();
        }
        if (this.f18178k.c()[2] == -1) {
            this.f18178k.c()[2] = atTime.getDayOfMonth();
        }
        if (this.f18178k.c()[3] == -1) {
            this.f18178k.c()[3] = atTime.getHour();
        }
        this.f18178k.c()[4] = atTime.getMinute();
        this.f18180m = true;
        this.f18185r = 8;
        this.f18186s = i2;
    }

    private void F(int i2) {
        LocalDate now = LocalDate.now();
        if (this.f18178k.c()[0] == -1) {
            this.f18178k.c()[0] = now.getYear();
        }
        this.f18178k.c()[1] = now.getMonthValue();
        if (this.f18178k.c()[2] == -1) {
            this.f18178k.c()[2] = now.getDayOfMonth();
        }
        this.f18180m = true;
        this.f18185r = 4;
        this.f18186s = i2;
    }

    private void G(int i2) {
        LocalDate now = LocalDate.now();
        if (this.f18178k.c()[0] == -1) {
            this.f18178k.c()[0] = now.getYear();
        }
        if (this.f18178k.c()[1] == -1) {
            this.f18178k.c()[1] = now.getMonthValue();
        }
        this.f18178k.c()[2] = now.getDayOfMonth();
        this.f18180m = true;
        this.f18185r = 5;
        this.f18186s = i2;
        this.f18182o = true;
    }

    private void H(int i2) {
        LocalDate now = LocalDate.now();
        this.f18178k.c()[0] = now.getYear();
        if (this.f18178k.c()[1] == -1) {
            this.f18178k.c()[1] = now.getMonthValue();
        }
        if (this.f18178k.c()[2] == -1) {
            this.f18178k.c()[2] = now.getDayOfMonth();
        }
        this.f18180m = true;
        this.f18185r = 3;
        this.f18186s = i2;
    }

    private void I(LocalDateTime localDateTime) {
        String[] split = DateTimeFormatterUtil.d(localDateTime, "yyyy-MM-dd-HH-mm-ss").split("-");
        this.f18178k.c()[0] = Integer.parseInt(split[0]);
        this.f18178k.c()[1] = Integer.parseInt(split[1]);
        this.f18178k.c()[2] = Integer.parseInt(split[2]);
        this.f18178k.c()[3] = Integer.parseInt(split[3]);
        this.f18178k.c()[4] = Integer.parseInt(split[4]);
        this.f18178k.c()[5] = Integer.parseInt(split[5]);
    }

    private void J() {
        LocalDateTime x = x();
        if (x == null) {
            h();
            k();
            p();
            z();
            n();
            j();
            o();
            f();
            g();
            i();
            l();
            m();
            w();
            y();
            e();
            x = LocalDateTime.of(1970, 1, 1, 0, 0);
        }
        String[] split = this.f18179l.b().split("-");
        int i2 = 5;
        while (i2 >= 0 && this.f18178k.c()[i2] < 0) {
            i2--;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f18178k.c()[i3] < 0) {
                this.f18178k.c()[i3] = Integer.parseInt(split[i3]);
            }
        }
        String[] strArr = new String[6];
        strArr[0] = String.valueOf(this.f18178k.c()[0]);
        if (this.f18178k.c()[0] >= 10 && this.f18178k.c()[0] < 100) {
            strArr[0] = "19" + String.valueOf(this.f18178k.c()[0]);
        }
        if (this.f18178k.c()[0] > 0 && this.f18178k.c()[0] < 10) {
            strArr[0] = "200" + String.valueOf(this.f18178k.c()[0]);
        }
        for (int i4 = 1; i4 < 6; i4++) {
            strArr[i4] = String.valueOf(this.f18178k.c()[i4]);
        }
        if (Integer.parseInt(strArr[0]) != -1) {
            this.f18171d += strArr[0] + "年";
            x = x.withYear(Integer.valueOf(strArr[0]).intValue());
            if (Integer.parseInt(strArr[1]) != -1) {
                this.f18171d += strArr[1] + "月";
                x = x.withMonth(Integer.valueOf(strArr[1]).intValue());
                if (Integer.parseInt(strArr[2]) != -1) {
                    this.f18171d += strArr[2] + "日";
                    x = x.withDayOfMonth(Integer.valueOf(strArr[2]).intValue());
                    if (Integer.parseInt(strArr[3]) != -1) {
                        this.f18171d += strArr[3] + "时";
                        x = x.withHour(Integer.valueOf(strArr[3]).intValue());
                        if (Integer.parseInt(strArr[4]) != -1) {
                            this.f18171d += strArr[4] + "分";
                            x = x.withMinute(Integer.valueOf(strArr[4]).intValue());
                            if (Integer.parseInt(strArr[5]) != -1) {
                                this.f18171d += strArr[5] + "秒";
                                x = x.withSecond(Integer.valueOf(strArr[5]).intValue());
                            }
                        }
                    }
                }
            }
        }
        this.f18179l.f((int[]) this.f18178k.c().clone());
        this.f18178k.e(this.f18179l.b());
        this.f18178k.d(this.f18179l.a());
        this.f18173f = DateTimeConverterUtil.b(x);
        if (this.f18180m) {
            int a2 = ArrayUtil.a(this.f18178k.c());
            int i5 = this.f18185r;
            if (i5 == 1 && a2 == 3) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(DateTimeCalculatorUtil.s(x, 7L));
            } else if (i5 == 2 && !this.f18181n && a2 == 4) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusHours(this.f18186s));
            } else if (i5 == 3) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusYears(this.f18186s));
            } else if (i5 == 4) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusMonths(this.f18186s));
            } else if (i5 == 5) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusDays((this.f18186s * 7) - 1));
            } else if (i5 == 6) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusDays(this.f18186s));
            } else if (i5 == 7) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusHours(this.f18186s));
            } else if (i5 == 8) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusMinutes(this.f18186s));
            } else if (i5 == 9) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusHours(this.f18186s));
            } else if (i5 == 10) {
                this.f18183p = this.f18173f;
                this.f18184q = DateTimeConverterUtil.b(x.plusMonths(1L));
            }
        }
        this.f18174g = x;
        this.f18172e = DateTimeFormatterUtil.e(x, DateTimeFormatterUtil.Q);
    }

    private void a(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.f18178k.c()[i3] == -1 && this.f18179l.c()[i3] != -1) {
                this.f18178k.c()[i3] = this.f18179l.c()[i3];
            }
        }
        if (this.f18176i && i2 == 3 && this.f18179l.c()[i2] >= 12 && this.f18178k.c()[i2] < 12) {
            int[] c2 = this.f18178k.c();
            c2[i2] = c2[i2] + 12;
        }
        this.f18176i = false;
    }

    public static List b(List list) {
        if (CollectionUtil.a(list)) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TimeNLP timeNLP = (TimeNLP) it.next();
            if (timeNLP != null && timeNLP.c().getYear() != 70) {
                arrayList.add(timeNLP);
            }
        }
        return arrayList;
    }

    private void e() {
        String[] split = this.f18179l.b().split("-");
        String str = this.f18178k.c()[0] != -1 ? "" + Integer.toString(this.f18178k.c()[0]) : "" + split[0];
        for (int i2 = 1; i2 < 6; i2++) {
            String str2 = str + "-";
            str = this.f18178k.c()[i2] != -1 ? str2 + Integer.toString(this.f18178k.c()[i2]) : str2 + split[i2];
        }
        this.f18179l.e(str);
    }

    private void f() {
        String[] split = this.f18179l.b().split("-");
        int[] iArr = new int[6];
        boolean z = false;
        for (int i2 = 0; i2 < 6; i2++) {
            iArr[i2] = Integer.parseInt(split[i2]);
        }
        boolean z2 = true;
        LocalDateTime of = LocalDateTime.of(iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5]);
        if (RegexEnum.NormBaseRelatedDayBefore.d().matcher(this.f18168a).find()) {
            of = of.minusDays(Integer.parseInt(r2.group()));
            z = true;
        }
        if (RegexEnum.NormBaseRelatedDayAfter.d().matcher(this.f18168a).find()) {
            of = of.plusDays(Integer.parseInt(r2.group()));
            z = true;
        }
        if (RegexEnum.NormBaseRelatedMonthBefore.d().matcher(this.f18168a).find()) {
            of = of.minusMonths(Integer.parseInt(r2.group()));
            z = true;
        }
        if (RegexEnum.NormBaseRelatedMonthAfter.d().matcher(this.f18168a).find()) {
            of = of.plusMonths(Integer.parseInt(r2.group()));
            z = true;
        }
        if (RegexEnum.NormBaseRelatedYearBefore.d().matcher(this.f18168a).find()) {
            of = of.minusYears(Integer.parseInt(r2.group()));
            z = true;
        }
        if (RegexEnum.NormBaseRelatedYearAfter.d().matcher(this.f18168a).find()) {
            of = of.plusYears(Integer.parseInt(r2.group()));
        } else {
            z2 = z;
        }
        if (z2) {
            I(of);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g() {
        /*
            Method dump skipped, instructions count: 522
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.timeutil.nlp.TimeNLP.g():void");
    }

    private void h() {
        Matcher matcher = RegexEnum.NormChineseHoliday.d().matcher(this.f18168a);
        if (matcher.find()) {
            this.t = true;
            String str = (String) ChineseHolidayEnum.h().get(matcher.group());
            if (str.equals("CHUXI")) {
                LocalDate n2 = ChineseHolidayEnum.n();
                this.f18178k.c()[0] = n2.getYear();
                this.f18178k.c()[1] = n2.getMonthValue();
                this.f18178k.c()[2] = n2.getDayOfMonth();
                return;
            }
            LocalDate l2 = ChineseHolidayEnum.l(Integer.parseInt(str.substring(0, 2)), Integer.parseInt(str.substring(2, 4)));
            this.f18178k.c()[0] = l2.getYear();
            this.f18178k.c()[1] = l2.getMonthValue();
            this.f18178k.c()[2] = l2.getDayOfMonth();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:150:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void i() {
        /*
            Method dump skipped, instructions count: 1131
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.timeutil.nlp.TimeNLP.i():void");
    }

    private void j() {
        Matcher matcher = RegexEnum.NormDay.d().matcher(this.f18168a);
        if (matcher.find()) {
            this.f18178k.c()[2] = Integer.parseInt(matcher.group());
            A(2);
        }
        Matcher matcher2 = RegexEnum.XthDays.d().matcher(this.f18168a);
        if (matcher2.find()) {
            if (this.f18178k.c()[2] <= 0) {
                int[] c2 = this.f18178k.c();
                String group = matcher2.group(3);
                Objects.requireNonNull(group);
                c2[2] = Integer.parseInt(group);
                return;
            }
            int[] c3 = this.f18178k.c();
            int i2 = c3[2];
            Objects.requireNonNull(matcher2.group(3));
            c3[2] = i2 + (Integer.parseInt(r0) - 1);
        }
    }

    private void k() {
        Matcher matcher = RegexEnum.NormLocalHoliday.d().matcher(this.f18168a);
        if (matcher.find()) {
            this.t = true;
            String str = (String) LocalHolidayEnum.h().get(matcher.group());
            if (!str.contains("W")) {
                this.f18178k.c()[0] = LocalDate.now().getYear();
                this.f18178k.c()[1] = Integer.parseInt(str.substring(0, 2));
                this.f18178k.c()[2] = Integer.parseInt(str.substring(2, 4));
                return;
            }
            String[] split = str.split("-");
            LocalDate n2 = LocalHolidayEnum.n(Integer.parseInt(split[0]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            this.f18178k.c()[0] = n2.getYear();
            this.f18178k.c()[1] = n2.getMonthValue();
            this.f18178k.c()[2] = n2.getDayOfMonth();
        }
    }

    private void l() {
        Matcher matcher = RegexEnum.NormHourBetween.d().matcher(this.f18168a);
        if (matcher.find()) {
            int[] c2 = this.f18178k.c();
            String group = matcher.group(1);
            Objects.requireNonNull(group);
            c2[3] = Integer.parseInt(group);
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group2 = matcher.group(4);
            Objects.requireNonNull(group2);
            this.f18186s = Integer.parseInt(group2) - this.f18178k.c()[3];
        } else {
            Matcher matcher2 = RegexEnum.NormHour.d().matcher(this.f18168a);
            if (matcher2.find()) {
                this.f18178k.c()[3] = Integer.parseInt(matcher2.group());
                A(3);
                this.f18175h = Boolean.FALSE;
                this.f18181n = true;
            }
        }
        Matcher matcher3 = RegexEnum.NormHourDayBreakBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher3.find()) {
            int[] c3 = this.f18178k.c();
            String group3 = matcher3.group(1);
            Objects.requireNonNull(group3);
            c3[3] = Integer.parseInt(group3);
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group4 = matcher3.group(4);
            Objects.requireNonNull(group4);
            this.f18186s = Integer.parseInt(group4) - this.f18178k.c()[3];
        } else {
            Matcher matcher4 = RegexEnum.NormHourDayBreakExcept.d().matcher(this.f18168a);
            if (matcher4.find()) {
                int[] c4 = this.f18178k.c();
                String group5 = matcher4.group(1);
                Objects.requireNonNull(group5);
                c4[3] = Integer.parseInt(group5);
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormHourDayBreak.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.day_break.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 6;
            }
        }
        Matcher matcher5 = RegexEnum.NormHourEarlyMorningBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher5.find()) {
            int[] c5 = this.f18178k.c();
            String group6 = matcher5.group(2);
            Objects.requireNonNull(group6);
            c5[3] = Integer.parseInt(group6);
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group7 = matcher5.group(5);
            Objects.requireNonNull(group7);
            this.f18186s = Integer.parseInt(group7) - this.f18178k.c()[3];
        } else {
            Matcher matcher6 = RegexEnum.NormHourEarlyMorningExcept.d().matcher(this.f18168a);
            if (matcher6.find()) {
                int[] c6 = this.f18178k.c();
                String group8 = matcher6.group(2);
                Objects.requireNonNull(group8);
                c6[3] = Integer.parseInt(group8);
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormHourEarlyMorning.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.early_morning.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 2;
            }
        }
        Matcher matcher7 = RegexEnum.NormHourMorningBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher7.find()) {
            int[] c7 = this.f18178k.c();
            String group9 = matcher7.group(1);
            Objects.requireNonNull(group9);
            c7[3] = Integer.parseInt(group9);
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group10 = matcher7.group(4);
            Objects.requireNonNull(group10);
            this.f18186s = Integer.parseInt(group10) - this.f18178k.c()[3];
        } else {
            Matcher matcher8 = RegexEnum.NormHourMorningExcept.d().matcher(this.f18168a);
            if (matcher8.find()) {
                int[] c8 = this.f18178k.c();
                String group11 = matcher8.group(1);
                Objects.requireNonNull(group11);
                c8[3] = Integer.parseInt(group11);
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormHourMorning.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.morning.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 4;
            }
        }
        Matcher matcher9 = RegexEnum.NormHourNoonBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher9.find()) {
            int[] c9 = this.f18178k.c();
            String group12 = matcher9.group(2);
            Objects.requireNonNull(group12);
            c9[3] = Integer.parseInt(group12);
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group13 = matcher9.group(5);
            Objects.requireNonNull(group13);
            this.f18186s = Integer.parseInt(group13) - this.f18178k.c()[3];
        } else {
            Matcher matcher10 = RegexEnum.NormHourNoonExcept.d().matcher(this.f18168a);
            if (matcher10.find()) {
                int[] c10 = this.f18178k.c();
                String group14 = matcher10.group(2);
                Objects.requireNonNull(group14);
                c10[3] = Integer.parseInt(group14);
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormHourNoon.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] >= 0 && this.f18178k.c()[3] <= 10) {
                    int[] c11 = this.f18178k.c();
                    c11[3] = c11[3] + 12;
                }
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.noon.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 2;
            }
        }
        Matcher matcher11 = RegexEnum.NormHourAfternoonBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher11.find()) {
            String group15 = matcher11.group(2);
            Objects.requireNonNull(group15);
            int parseInt = Integer.parseInt(group15);
            this.f18178k.c()[3] = parseInt < 12 ? parseInt + 12 : parseInt;
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group16 = matcher11.group(5);
            Objects.requireNonNull(group16);
            this.f18186s = Integer.parseInt(group16) - parseInt;
        } else {
            Matcher matcher12 = RegexEnum.NormHourAfternoonExcept.d().matcher(this.f18168a);
            if (matcher12.find()) {
                String group17 = matcher12.group(2);
                Objects.requireNonNull(group17);
                int parseInt2 = Integer.parseInt(group17);
                int[] c12 = this.f18178k.c();
                if (parseInt2 < 12) {
                    parseInt2 += 12;
                }
                c12[3] = parseInt2;
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormHourAfternoon.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] >= 0 && this.f18178k.c()[3] <= 11) {
                    int[] c13 = this.f18178k.c();
                    c13[3] = c13[3] + 12;
                }
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.afternoon.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 5;
            }
        }
        Matcher matcher13 = RegexEnum.NormHourNightBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher13.find()) {
            String group18 = matcher13.group(2);
            Objects.requireNonNull(group18);
            int parseInt3 = Integer.parseInt(group18);
            this.f18178k.c()[3] = parseInt3 < 12 ? parseInt3 + 12 : parseInt3;
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group19 = matcher13.group(5);
            Objects.requireNonNull(group19);
            this.f18186s = Integer.parseInt(group19) - parseInt3;
            return;
        }
        Matcher matcher14 = RegexEnum.NormHourNightExcept.d().matcher(this.f18168a);
        if (matcher14.find()) {
            String group20 = matcher14.group(2);
            Objects.requireNonNull(group20);
            int parseInt4 = Integer.parseInt(group20);
            int[] c14 = this.f18178k.c();
            if (parseInt4 < 12) {
                parseInt4 += 12;
            }
            c14[3] = parseInt4;
            this.f18175h = Boolean.FALSE;
            return;
        }
        if (RegexEnum.NormHourNight.d().matcher(this.f18168a).find()) {
            if (this.f18178k.c()[3] >= 1 && this.f18178k.c()[3] <= 11) {
                int[] c15 = this.f18178k.c();
                c15[3] = c15[3] + 12;
            } else if (this.f18178k.c()[3] == 12) {
                this.f18178k.c()[3] = 0;
            } else if (this.f18178k.c()[3] == -1) {
                this.f18178k.c()[3] = MomentEnum.night.d();
            }
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 2;
            this.f18186s = 6;
        }
    }

    private void m() {
        if (RegexEnum.NormMinuteSpec.d().matcher(this.f18168a).find()) {
            return;
        }
        Matcher matcher = RegexEnum.NormMinute.d().matcher(this.f18168a);
        if (matcher.find() && !matcher.group().equals("")) {
            this.f18178k.c()[4] = Integer.parseInt(matcher.group());
            A(4);
            this.f18175h = Boolean.FALSE;
        }
        if (RegexEnum.NormMinuteOneQuarter.d().matcher(this.f18168a).find()) {
            this.f18178k.c()[4] = 15;
            A(4);
            this.f18175h = Boolean.FALSE;
        }
        if (RegexEnum.NormMinuteHalf.d().matcher(this.f18168a).find()) {
            this.f18178k.c()[4] = 30;
            A(4);
            this.f18175h = Boolean.FALSE;
        }
        if (RegexEnum.NormMinuteThreeQuarter.d().matcher(this.f18168a).find()) {
            this.f18178k.c()[4] = 45;
            A(4);
            this.f18175h = Boolean.FALSE;
        }
    }

    private void n() {
        Matcher matcher = RegexEnum.NormMonth.d().matcher(this.f18168a);
        if (matcher.find()) {
            this.f18178k.c()[1] = Integer.parseInt(matcher.group());
            A(1);
        }
    }

    private void o() {
        Matcher matcher = RegexEnum.NormMonthFuzzyDay.d().matcher(this.f18168a);
        if (matcher.find()) {
            String group = matcher.group();
            Matcher matcher2 = Pattern.compile("(月|\\.|\\-)").matcher(group);
            if (matcher2.find()) {
                int start = matcher2.start();
                String substring = group.substring(0, start);
                String substring2 = group.substring(start + 1);
                this.f18178k.c()[1] = Integer.parseInt(substring);
                this.f18178k.c()[2] = Integer.parseInt(substring2);
                A(1);
            }
        }
    }

    private void p() {
        if (v() || t() || u() || q() || r() || s() || this.f18178k.c()[2] != -1 || !RegexEnum.Recent.d().matcher(this.f18168a).find()) {
            return;
        }
        LocalDate now = LocalDate.now();
        this.f18178k.c()[0] = now.getYear();
        this.f18178k.c()[1] = now.getMonthValue();
        this.f18178k.c()[2] = now.getDayOfMonth();
        this.f18180m = true;
        this.f18185r = 6;
        this.f18186s = 3;
    }

    private boolean q() {
        if (this.f18178k.c()[2] != -1) {
            return false;
        }
        Matcher matcher = RegexEnum.RecentDayWithin.d().matcher(this.f18168a);
        if (matcher.find()) {
            String group = matcher.group(2);
            Objects.requireNonNull(group);
            C(Integer.parseInt(group));
            return true;
        }
        Matcher matcher2 = RegexEnum.RecentDay.d().matcher(this.f18168a);
        if (matcher2.find()) {
            String group2 = matcher2.group(2);
            Objects.requireNonNull(group2);
            C(Integer.parseInt(group2));
            return true;
        }
        Matcher matcher3 = RegexEnum.DayWithin.d().matcher(this.f18168a);
        if (!matcher3.find()) {
            return false;
        }
        String group3 = matcher3.group(1);
        Objects.requireNonNull(group3);
        C(Integer.parseInt(group3));
        return true;
    }

    private boolean r() {
        if (this.f18178k.c()[3] != -1) {
            return false;
        }
        Matcher matcher = RegexEnum.RecentHourWithin.d().matcher(this.f18168a);
        if (matcher.find()) {
            String group = matcher.group(2);
            Objects.requireNonNull(group);
            D(Integer.parseInt(group));
            return true;
        }
        Matcher matcher2 = RegexEnum.RecentHour.d().matcher(this.f18168a);
        if (matcher2.find()) {
            String group2 = matcher2.group(2);
            Objects.requireNonNull(group2);
            D(Integer.parseInt(group2));
            return true;
        }
        Matcher matcher3 = RegexEnum.HourWithin.d().matcher(this.f18168a);
        if (!matcher3.find()) {
            return false;
        }
        String group3 = matcher3.group(1);
        Objects.requireNonNull(group3);
        D(Integer.parseInt(group3));
        return true;
    }

    private boolean s() {
        if (this.f18178k.c()[4] != -1) {
            return false;
        }
        Matcher matcher = RegexEnum.RecentMinuteWithin.d().matcher(this.f18168a);
        if (matcher.find()) {
            String group = matcher.group(2);
            Objects.requireNonNull(group);
            E(Integer.parseInt(group));
            return true;
        }
        Matcher matcher2 = RegexEnum.RecentMinute.d().matcher(this.f18168a);
        if (matcher2.find()) {
            String group2 = matcher2.group(2);
            Objects.requireNonNull(group2);
            E(Integer.parseInt(group2));
            return true;
        }
        Matcher matcher3 = RegexEnum.MinuteWithin.d().matcher(this.f18168a);
        if (!matcher3.find()) {
            return false;
        }
        String group3 = matcher3.group(1);
        Objects.requireNonNull(group3);
        E(Integer.parseInt(group3));
        return true;
    }

    private boolean t() {
        if (this.f18178k.c()[1] != -1) {
            return false;
        }
        Matcher matcher = RegexEnum.RecentMonthWithin.d().matcher(this.f18168a);
        if (matcher.find()) {
            String group = matcher.group(2);
            Objects.requireNonNull(group);
            F(Integer.parseInt(group));
            return true;
        }
        Matcher matcher2 = RegexEnum.RecentMonth.d().matcher(this.f18168a);
        if (matcher2.find()) {
            String group2 = matcher2.group(2);
            Objects.requireNonNull(group2);
            F(Integer.parseInt(group2));
            return true;
        }
        Matcher matcher3 = RegexEnum.MonthWithin.d().matcher(this.f18168a);
        if (!matcher3.find()) {
            return false;
        }
        String group3 = matcher3.group(1);
        Objects.requireNonNull(group3);
        F(Integer.parseInt(group3));
        return true;
    }

    private boolean u() {
        if (this.f18178k.c()[2] != -1) {
            return false;
        }
        Matcher matcher = RegexEnum.RecentWeekWithin.d().matcher(this.f18168a);
        if (matcher.find()) {
            String group = matcher.group(2);
            Objects.requireNonNull(group);
            G(Integer.parseInt(group));
            return true;
        }
        Matcher matcher2 = RegexEnum.RecentWeek.d().matcher(this.f18168a);
        if (matcher2.find()) {
            String group2 = matcher2.group(2);
            Objects.requireNonNull(group2);
            G(Integer.parseInt(group2));
            return true;
        }
        Matcher matcher3 = RegexEnum.WeekWithin.d().matcher(this.f18168a);
        if (!matcher3.find()) {
            return false;
        }
        String group3 = matcher3.group(1);
        Objects.requireNonNull(group3);
        G(Integer.parseInt(group3));
        return true;
    }

    private boolean v() {
        if (this.f18178k.c()[0] != -1) {
            return false;
        }
        Matcher matcher = RegexEnum.RecentYearWithin.d().matcher(this.f18168a);
        if (matcher.find()) {
            String group = matcher.group(2);
            Objects.requireNonNull(group);
            H(Integer.parseInt(group));
            return true;
        }
        Matcher matcher2 = RegexEnum.RecentYear.d().matcher(this.f18168a);
        if (matcher2.find()) {
            String group2 = matcher2.group(2);
            Objects.requireNonNull(group2);
            H(Integer.parseInt(group2));
            return true;
        }
        Matcher matcher3 = RegexEnum.YearWithin.d().matcher(this.f18168a);
        if (!matcher3.find()) {
            return false;
        }
        String group3 = matcher3.group(1);
        Objects.requireNonNull(group3);
        H(Integer.parseInt(group3));
        return true;
    }

    private void w() {
        if (RegexEnum.NormSecondSpec.d().matcher(this.f18168a).find()) {
            return;
        }
        Matcher matcher = RegexEnum.NormSecond.d().matcher(this.f18168a);
        if (matcher.find()) {
            this.f18178k.c()[5] = Integer.parseInt(matcher.group());
            this.f18175h = Boolean.FALSE;
        }
    }

    private LocalDateTime x() {
        Matcher matcher = RegexEnum.NormStandard.d().matcher(this.f18168a);
        Matcher matcher2 = RegexEnum.NormStandardCn.d().matcher(this.f18168a);
        LocalDateTime localDateTime = null;
        if (matcher.find() || matcher2.find()) {
            try {
                ParseResult n2 = DateTimeFormatterUtil.n(this.f18168a);
                localDateTime = n2.a();
                int b2 = n2.b();
                int[] c2 = this.f18178k.c();
                if (b2 >= 1) {
                    c2[0] = localDateTime.getYear();
                }
                if (b2 >= 2) {
                    c2[1] = localDateTime.getMonthValue();
                }
                if (b2 >= 3) {
                    c2[2] = localDateTime.getDayOfMonth();
                }
                if (localDateTime.getHour() >= 0 && b2 >= 4) {
                    c2[3] = localDateTime.getHour();
                }
                if (localDateTime.getMinute() > 0 && b2 >= 5) {
                    c2[4] = localDateTime.getMinute();
                }
                if (localDateTime.getSecond() > 0 && b2 >= 6) {
                    c2[5] = localDateTime.getSecond();
                }
            } catch (Exception e2) {
                System.out.println("normStandardTime error:" + e2.getMessage());
            }
        }
        return localDateTime;
    }

    private void y() {
        Matcher matcher = RegexEnum.NormTotalTime.d().matcher(this.f18168a);
        if (matcher.find()) {
            String[] split = matcher.group().split(":");
            this.f18178k.c()[3] = Integer.parseInt(split[0]);
            this.f18178k.c()[4] = Integer.parseInt(split[1]);
            this.f18178k.c()[5] = Integer.parseInt(split[2]);
            A(3);
            this.f18175h = Boolean.FALSE;
        } else {
            Matcher matcher2 = RegexEnum.NormTotalTimeShort.d().matcher(this.f18168a);
            if (matcher2.find()) {
                String[] split2 = matcher2.group().split(":");
                this.f18178k.c()[3] = Integer.parseInt(split2[0]);
                this.f18178k.c()[4] = Integer.parseInt(split2[1]);
                A(3);
                this.f18175h = Boolean.FALSE;
            }
        }
        Matcher matcher3 = RegexEnum.NormHourNoonBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher3.find()) {
            int[] c2 = this.f18178k.c();
            String group = matcher3.group(2);
            Objects.requireNonNull(group);
            c2[3] = Integer.parseInt(group);
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group2 = matcher3.group(5);
            Objects.requireNonNull(group2);
            this.f18186s = Integer.parseInt(group2) - this.f18178k.c()[3];
        } else {
            Matcher matcher4 = RegexEnum.NormHourNoonExcept.d().matcher(this.f18168a);
            if (matcher4.find()) {
                int[] c3 = this.f18178k.c();
                String group3 = matcher4.group(2);
                Objects.requireNonNull(group3);
                c3[3] = Integer.parseInt(group3);
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormHourNoon.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] >= 0 && this.f18178k.c()[3] <= 10) {
                    int[] c4 = this.f18178k.c();
                    c4[3] = c4[3] + 12;
                }
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.noon.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 2;
            }
        }
        Matcher matcher5 = RegexEnum.NormHourAfternoonBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher5.find()) {
            String group4 = matcher5.group(2);
            Objects.requireNonNull(group4);
            int parseInt = Integer.parseInt(group4);
            this.f18178k.c()[3] = parseInt < 12 ? parseInt + 12 : parseInt;
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group5 = matcher5.group(5);
            Objects.requireNonNull(group5);
            this.f18186s = Integer.parseInt(group5) - parseInt;
        } else {
            Matcher matcher6 = RegexEnum.NormHourAfternoonExcept.d().matcher(this.f18168a);
            if (matcher6.find()) {
                String group6 = matcher6.group(2);
                Objects.requireNonNull(group6);
                int parseInt2 = Integer.parseInt(group6);
                int[] c5 = this.f18178k.c();
                if (parseInt2 < 12) {
                    parseInt2 += 12;
                }
                c5[3] = parseInt2;
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormHourAfternoon.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] >= 0 && this.f18178k.c()[3] <= 11) {
                    int[] c6 = this.f18178k.c();
                    c6[3] = c6[3] + 12;
                }
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.afternoon.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 5;
            }
        }
        Matcher matcher7 = RegexEnum.NormTotalNightBetweenHourExcept.d().matcher(this.f18168a);
        if (matcher7.find()) {
            String group7 = matcher7.group(2);
            Objects.requireNonNull(group7);
            int parseInt3 = Integer.parseInt(group7);
            this.f18178k.c()[3] = parseInt3 < 12 ? parseInt3 + 12 : parseInt3;
            this.f18175h = Boolean.FALSE;
            this.f18180m = true;
            this.f18185r = 9;
            String group8 = matcher7.group(5);
            Objects.requireNonNull(group8);
            this.f18186s = Integer.parseInt(group8) - parseInt3;
        } else {
            Matcher matcher8 = RegexEnum.NormTotalNightExcept.d().matcher(this.f18168a);
            if (matcher8.find()) {
                String group9 = matcher8.group(2);
                Objects.requireNonNull(group9);
                int parseInt4 = Integer.parseInt(group9);
                int[] c7 = this.f18178k.c();
                if (parseInt4 < 12) {
                    parseInt4 += 12;
                }
                c7[3] = parseInt4;
                this.f18175h = Boolean.FALSE;
            } else if (RegexEnum.NormTotalNight.d().matcher(this.f18168a).find()) {
                if (this.f18178k.c()[3] >= 1 && this.f18178k.c()[3] <= 11) {
                    int[] c8 = this.f18178k.c();
                    c8[3] = c8[3] + 12;
                } else if (this.f18178k.c()[3] == 12) {
                    this.f18178k.c()[3] = 0;
                }
                if (this.f18178k.c()[3] == -1) {
                    this.f18178k.c()[3] = MomentEnum.night.d();
                }
                this.f18175h = Boolean.FALSE;
                this.f18180m = true;
                this.f18185r = 2;
                this.f18186s = 6;
            }
        }
        Matcher matcher9 = RegexEnum.NormTotalDateOne.d().matcher(this.f18168a);
        if (matcher9.find()) {
            String[] split3 = matcher9.group().split("-");
            this.f18178k.c()[0] = Integer.parseInt(split3[0]);
            this.f18178k.c()[1] = Integer.parseInt(split3[1]);
            this.f18178k.c()[2] = Integer.parseInt(split3[2]);
        }
        Matcher matcher10 = RegexEnum.NormTotalDateTwo.d().matcher(this.f18168a);
        if (matcher10.find()) {
            String[] split4 = matcher10.group().split("/");
            this.f18178k.c()[1] = Integer.parseInt(split4[0]);
            this.f18178k.c()[2] = Integer.parseInt(split4[1]);
            this.f18178k.c()[0] = Integer.parseInt(split4[2]);
        }
        Matcher matcher11 = RegexEnum.NormTotalDateThree.d().matcher(this.f18168a);
        if (matcher11.find()) {
            String[] split5 = matcher11.group().split("\\.");
            this.f18178k.c()[0] = Integer.parseInt(split5[0]);
            this.f18178k.c()[1] = Integer.parseInt(split5[1]);
            this.f18178k.c()[2] = Integer.parseInt(split5[2]);
        }
    }

    private void z() {
        Matcher matcher = RegexEnum.NormYearTwo.d().matcher(this.f18168a);
        if (matcher.find()) {
            this.f18178k.c()[0] = Integer.parseInt(matcher.group());
            if (this.f18178k.c()[0] >= 0 && this.f18178k.c()[0] < 100) {
                if (this.f18178k.c()[0] < 30) {
                    int[] c2 = this.f18178k.c();
                    c2[0] = c2[0] + 2000;
                } else {
                    int[] c3 = this.f18178k.c();
                    c3[0] = c3[0] + 1900;
                }
            }
        }
        Matcher matcher2 = RegexEnum.NormYearFour.d().matcher(this.f18168a);
        if (matcher2.find()) {
            this.f18178k.c()[0] = Integer.parseInt(matcher2.group());
        }
    }

    public Date c() {
        return this.f18173f;
    }

    public TimeContext d() {
        return this.f18178k;
    }

    public String toString() {
        return this.f18168a + " ---> " + this.f18172e;
    }
}
