package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {

    /* renamed from: h, reason: collision with root package name */
    private boolean f10914h;

    /* renamed from: i, reason: collision with root package name */
    private ArrayList f10915i;

    private final void k() {
        synchronized (this) {
            try {
                if (!this.f10914h) {
                    int count = ((DataHolder) Preconditions.i(this.f10886c)).getCount();
                    ArrayList arrayList = new ArrayList();
                    this.f10915i = arrayList;
                    if (count > 0) {
                        arrayList.add(0);
                        String i2 = i();
                        String T = this.f10886c.T(i2, 0, this.f10886c.W(0));
                        for (int i3 = 1; i3 < count; i3++) {
                            int W = this.f10886c.W(i3);
                            String T2 = this.f10886c.T(i2, i3, W);
                            if (T2 == null) {
                                throw new NullPointerException("Missing value for markerColumn: " + i2 + ", at row: " + i3 + ", for window: " + W);
                            }
                            if (!T2.equals(T)) {
                                this.f10915i.add(Integer.valueOf(i3));
                                T = T2;
                            }
                        }
                    }
                    this.f10914h = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected String d() {
        return null;
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final Object get(int i2) {
        int intValue;
        int intValue2;
        k();
        int j2 = j(i2);
        int i3 = 0;
        if (i2 >= 0 && i2 != this.f10915i.size()) {
            if (i2 == this.f10915i.size() - 1) {
                intValue = ((DataHolder) Preconditions.i(this.f10886c)).getCount();
                intValue2 = ((Integer) this.f10915i.get(i2)).intValue();
            } else {
                intValue = ((Integer) this.f10915i.get(i2 + 1)).intValue();
                intValue2 = ((Integer) this.f10915i.get(i2)).intValue();
            }
            int i4 = intValue - intValue2;
            if (i4 == 1) {
                int j3 = j(i2);
                int W = ((DataHolder) Preconditions.i(this.f10886c)).W(j3);
                String d2 = d();
                if (d2 == null || this.f10886c.T(d2, j3, W) != null) {
                    i3 = 1;
                }
            } else {
                i3 = i4;
            }
        }
        return h(j2, i3);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        k();
        return this.f10915i.size();
    }

    protected abstract Object h(int i2, int i3);

    protected abstract String i();

    final int j(int i2) {
        if (i2 >= 0 && i2 < this.f10915i.size()) {
            return ((Integer) this.f10915i.get(i2)).intValue();
        }
        throw new IllegalArgumentException("Position " + i2 + " is out of bounds for this buffer");
    }
}
