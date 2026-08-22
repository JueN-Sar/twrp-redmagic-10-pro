package com.zte.timeutil.nlp;

import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class TimeNLPCallable implements Callable<List<TimeNLP>> {

    /* renamed from: a, reason: collision with root package name */
    private String f18187a;

    /* renamed from: b, reason: collision with root package name */
    private String f18188b;

    @Override // java.util.concurrent.Callable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public List call() {
        return TimeNLPUtil.a(this.f18187a, this.f18188b);
    }
}
