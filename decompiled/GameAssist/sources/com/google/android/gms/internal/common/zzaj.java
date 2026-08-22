package com.google.android.gms.internal.common;

import java.util.Iterator;
import org.jspecify.nullness.NullMarked;

@NullMarked
/* loaded from: classes.dex */
public abstract class zzaj implements Iterator {
    protected zzaj() {
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
