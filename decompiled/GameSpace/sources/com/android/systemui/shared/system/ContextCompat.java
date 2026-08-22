package com.android.systemui.shared.system;

import android.content.Context;

/* loaded from: classes2.dex */
public class ContextCompat {
    private final Context mWrapped;

    public ContextCompat(Context context) {
        this.mWrapped = context;
    }

    public int getUserId() {
        return this.mWrapped.getUserId();
    }
}
