package com.zte.timeutil.constants;

import java.time.LocalTime;
import java.time.MonthDay;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class Constant {

    /* renamed from: a, reason: collision with root package name */
    public static final Pattern f18118a = Pattern.compile("[0-9]+");

    /* renamed from: b, reason: collision with root package name */
    public static final Pattern f18119b = Pattern.compile("^[A-Za-z].*");

    /* renamed from: c, reason: collision with root package name */
    public static final Pattern f18120c = Pattern.compile("[一-鿿]");

    /* renamed from: d, reason: collision with root package name */
    public static final LocalTime f18121d = LocalTime.of(1, 0, 0);

    /* renamed from: e, reason: collision with root package name */
    public static final LocalTime f18122e = LocalTime.of(23, 0, 0);

    /* renamed from: f, reason: collision with root package name */
    public static final MonthDay f18123f = MonthDay.parse("--01-01");

    /* renamed from: g, reason: collision with root package name */
    public static final MonthDay f18124g = MonthDay.parse("--12-31");

    /* renamed from: h, reason: collision with root package name */
    public static final MonthDay f18125h = MonthDay.parse("--12-22");

    /* renamed from: i, reason: collision with root package name */
    public static final MonthDay f18126i = MonthDay.parse("--01-19");
}
