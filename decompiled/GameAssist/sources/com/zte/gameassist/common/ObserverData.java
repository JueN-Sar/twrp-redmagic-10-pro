package com.zte.gameassist.common;

import com.zte.gameassist.common.ObserverData;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class ObserverData<T> {

    /* renamed from: a, reason: collision with root package name */
    private final List f16532a;

    /* renamed from: b, reason: collision with root package name */
    private Object f16533b;

    public interface Observer<T> {
        void a(Object obj);
    }

    public ObserverData() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Observer observer) {
        observer.a(this.f16533b);
    }

    private void d() {
        this.f16532a.forEach(new Consumer() { // from class: com.zte.gameassist.common.m
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ObserverData.this.c((ObserverData.Observer) obj);
            }
        });
    }

    public synchronized Object b() {
        return this.f16533b;
    }

    public synchronized void e(boolean z, Observer observer) {
        try {
            if (!z) {
                this.f16532a.remove(observer);
            } else if (!this.f16532a.contains(observer)) {
                this.f16532a.add(observer);
                observer.a(this.f16533b);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void f(Object obj, boolean z) {
        this.f16533b = obj;
        if (z) {
            d();
        }
    }

    public ObserverData(Object obj) {
        this.f16532a = new ArrayList();
        this.f16533b = obj;
    }
}
