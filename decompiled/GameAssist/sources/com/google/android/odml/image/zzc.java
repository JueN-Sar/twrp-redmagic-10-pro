package com.google.android.odml.image;

/* loaded from: classes.dex */
final class zzc extends ImageProperties {

    /* renamed from: a, reason: collision with root package name */
    private final int f15780a;

    /* renamed from: b, reason: collision with root package name */
    private final int f15781b;

    @Override // com.google.android.odml.image.ImageProperties
    public final int a() {
        return this.f15780a;
    }

    @Override // com.google.android.odml.image.ImageProperties
    public final int b() {
        return this.f15781b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImageProperties) {
            ImageProperties imageProperties = (ImageProperties) obj;
            if (this.f15780a == imageProperties.a() && this.f15781b == imageProperties.b()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f15781b ^ ((this.f15780a ^ 1000003) * 1000003);
    }

    public final String toString() {
        int i2 = this.f15780a;
        int i3 = this.f15781b;
        StringBuilder sb = new StringBuilder(65);
        sb.append("ImageProperties{imageFormat=");
        sb.append(i2);
        sb.append(", storageType=");
        sb.append(i3);
        sb.append("}");
        return sb.toString();
    }
}
