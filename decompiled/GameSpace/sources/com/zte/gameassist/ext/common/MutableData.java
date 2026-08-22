package com.zte.gameassist.ext.common;

import com.zte.gameassist.ext.common.MutableData;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class MutableData<T> {
    private T data;
    private final List<Observer<? super T>> observers;

    public interface Observer<T> {
        void onChanged(T t);
    }

    public MutableData() {
        this(null);
    }

    public MutableData(T t) {
        this.observers = new ArrayList();
        this.data = t;
    }

    private void notifyObservers() {
        this.observers.forEach(new Consumer() { // from class: com.zte.gameassist.ext.common.MutableData$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MutableData.this.m450x1a51cebd((MutableData.Observer) obj);
            }
        });
    }

    public synchronized T getData() {
        return this.data;
    }

    /* renamed from: lambda$notifyObservers$0$com-zte-gameassist-ext-common-MutableData, reason: not valid java name */
    /* synthetic */ void m450x1a51cebd(Observer observer) {
        observer.onChanged(this.data);
    }

    public synchronized void observe(boolean z, Observer<? super T> observer) {
        if (!z) {
            this.observers.remove(observer);
        } else if (!this.observers.contains(observer)) {
            this.observers.add(observer);
            observer.onChanged(this.data);
        }
    }

    public int observeCount() {
        return this.observers.size();
    }

    public synchronized void setData(T t) {
        if (this.data != t) {
            this.data = t;
            notifyObservers();
        }
    }
}
