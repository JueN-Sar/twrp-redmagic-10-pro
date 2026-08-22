package com.zte.mifavor.widget;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseTagAdapter<T> {

    /* renamed from: a, reason: collision with root package name */
    private List f17592a;

    /* renamed from: b, reason: collision with root package name */
    private OnDataChangedListener f17593b;

    /* renamed from: c, reason: collision with root package name */
    private HashSet f17594c;

    interface OnDataChangedListener {
    }

    public int a() {
        List list = this.f17592a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Object b(int i2) {
        return this.f17592a.get(i2);
    }

    HashSet c() {
        return this.f17594c;
    }

    public abstract View d(FlowLayout flowLayout, int i2, Object obj);

    public void e(int i2, View view) {
        Log.d("BaseTagAdapter", "onSelected " + i2);
    }

    void f(OnDataChangedListener onDataChangedListener) {
        this.f17593b = onDataChangedListener;
    }

    public boolean g(int i2, Object obj) {
        return false;
    }

    public void h(int i2, View view) {
        Log.d("BaseTagAdapter", "unSelected " + i2);
    }
}
