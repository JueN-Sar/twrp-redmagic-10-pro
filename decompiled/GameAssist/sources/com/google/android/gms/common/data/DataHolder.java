package com.google.android.gms.common.data;

import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;

@KeepForSdk
@KeepName
@SafeParcelable.Class
/* loaded from: classes.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zaf();

    /* renamed from: q, reason: collision with root package name */
    private static final Builder f10900q = new zab(new String[0], null);

    /* renamed from: c, reason: collision with root package name */
    final int f10901c;

    /* renamed from: h, reason: collision with root package name */
    private final String[] f10902h;

    /* renamed from: i, reason: collision with root package name */
    Bundle f10903i;

    /* renamed from: j, reason: collision with root package name */
    private final CursorWindow[] f10904j;

    /* renamed from: k, reason: collision with root package name */
    private final int f10905k;

    /* renamed from: l, reason: collision with root package name */
    private final Bundle f10906l;

    /* renamed from: m, reason: collision with root package name */
    int[] f10907m;

    /* renamed from: n, reason: collision with root package name */
    int f10908n;

    /* renamed from: o, reason: collision with root package name */
    boolean f10909o = false;

    /* renamed from: p, reason: collision with root package name */
    private boolean f10910p = true;

    @KeepForSdk
    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final String[] f10911a;

        /* renamed from: b, reason: collision with root package name */
        private final ArrayList f10912b = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        private final HashMap f10913c = new HashMap();
    }

    DataHolder(int i2, String[] strArr, CursorWindow[] cursorWindowArr, int i3, Bundle bundle) {
        this.f10901c = i2;
        this.f10902h = strArr;
        this.f10904j = cursorWindowArr;
        this.f10905k = i3;
        this.f10906l = bundle;
    }

    private final void a0(String str, int i2) {
        Bundle bundle = this.f10903i;
        if (bundle == null || !bundle.containsKey(str)) {
            throw new IllegalArgumentException("No such column: ".concat(String.valueOf(str)));
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i2 < 0 || i2 >= this.f10908n) {
            throw new CursorIndexOutOfBoundsException(i2, this.f10908n);
        }
    }

    public byte[] G(String str, int i2, int i3) {
        a0(str, i2);
        return this.f10904j[i3].getBlob(i2, this.f10903i.getInt(str));
    }

    public Bundle P() {
        return this.f10906l;
    }

    public int R() {
        return this.f10905k;
    }

    public String T(String str, int i2, int i3) {
        a0(str, i2);
        return this.f10904j[i3].getString(i2, this.f10903i.getInt(str));
    }

    public int W(int i2) {
        int length;
        int i3 = 0;
        Preconditions.l(i2 >= 0 && i2 < this.f10908n);
        while (true) {
            int[] iArr = this.f10907m;
            length = iArr.length;
            if (i3 >= length) {
                break;
            }
            if (i2 < iArr[i3]) {
                i3--;
                break;
            }
            i3++;
        }
        return i3 == length ? i3 - 1 : i3;
    }

    public final void Y() {
        this.f10903i = new Bundle();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr = this.f10902h;
            if (i3 >= strArr.length) {
                break;
            }
            this.f10903i.putInt(strArr[i3], i3);
            i3++;
        }
        this.f10907m = new int[this.f10904j.length];
        int i4 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.f10904j;
            if (i2 >= cursorWindowArr.length) {
                this.f10908n = i4;
                return;
            }
            this.f10907m[i2] = i4;
            i4 += this.f10904j[i2].getNumRows() - (i4 - cursorWindowArr[i2].getStartPosition());
            i2++;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            try {
                if (!this.f10909o) {
                    this.f10909o = true;
                    int i2 = 0;
                    while (true) {
                        CursorWindow[] cursorWindowArr = this.f10904j;
                        if (i2 >= cursorWindowArr.length) {
                            break;
                        }
                        cursorWindowArr[i2].close();
                        i2++;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected final void finalize() {
        try {
            if (this.f10910p && this.f10904j.length > 0 && !isClosed()) {
                close();
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: " + toString() + ")");
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        return this.f10908n;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.f10909o;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String[] strArr = this.f10902h;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.n(parcel, 1, strArr, false);
        SafeParcelWriter.p(parcel, 2, this.f10904j, i2, false);
        SafeParcelWriter.g(parcel, 3, R());
        SafeParcelWriter.d(parcel, 4, P(), false);
        SafeParcelWriter.g(parcel, 1000, this.f10901c);
        SafeParcelWriter.b(parcel, a2);
        if ((i2 & 1) != 0) {
            close();
        }
    }
}
