package com.zte.gameassist.ext.common;

import com.zte.gameassist.ext.common.MutableData;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class MutableData<T> {

    /* renamed from: a, reason: collision with root package name */
    private final List f16664a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private Object f16665b;

    public interface Observer<T> {
        void a(Object obj);
    }

    public MutableData(Object obj) {
        this.f16665b = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(Observer observer) {
        observer.a(this.f16665b);
    }

    private void d() {
        this.f16664a.forEach(new Consumer() { // from class: com.zte.gameassist.ext.common.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MutableData.this.c((MutableData.Observer) obj);
            }
        });
    }

    public synchronized Object b() {
        return this.f16665b;
    }

    public synchronized void e(boolean z, Observer observer) {
        try {
            if (!z) {
                this.f16664a.remove(observer);
            } else if (!this.f16664a.contains(observer)) {
                this.f16664a.add(observer);
                observer.a(this.f16665b);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public int f() {
        return this.f16664a.size();
    }

    public synchronized void g(Object obj) {
        if (this.f16665b != obj) {
            this.f16665b = obj;
            d();
        }
    }
}
