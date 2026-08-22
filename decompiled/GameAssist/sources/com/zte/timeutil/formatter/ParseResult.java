package com.zte.timeutil.formatter;

import java.time.LocalDateTime;

/* loaded from: classes2.dex */
public class ParseResult {

    /* renamed from: a, reason: collision with root package name */
    LocalDateTime f18160a;

    /* renamed from: b, reason: collision with root package name */
    int f18161b;

    public ParseResult(LocalDateTime localDateTime, int i2) {
        this.f18160a = localDateTime;
        this.f18161b = i2;
    }

    public LocalDateTime a() {
        return this.f18160a;
    }

    public int b() {
        return this.f18161b;
    }
}
