package com.google.mlkit.common.model;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
public class DownloadConditions {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f15908a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f15909b;

    public static class Builder {
    }

    public boolean a() {
        return this.f15908a;
    }

    public boolean b() {
        return this.f15909b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadConditions)) {
            return false;
        }
        DownloadConditions downloadConditions = (DownloadConditions) obj;
        return this.f15908a == downloadConditions.f15908a && this.f15909b == downloadConditions.f15909b;
    }

    public int hashCode() {
        return Objects.b(Boolean.valueOf(this.f15908a), Boolean.valueOf(this.f15909b));
    }
}
