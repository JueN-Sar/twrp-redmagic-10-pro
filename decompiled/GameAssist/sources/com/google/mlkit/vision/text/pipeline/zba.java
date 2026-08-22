package com.google.mlkit.vision.text.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbkx;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbok;

/* loaded from: classes.dex */
final class zba extends zbn {

    /* renamed from: a, reason: collision with root package name */
    private final zbo f16147a;

    /* renamed from: b, reason: collision with root package name */
    private final zbok f16148b;

    /* renamed from: c, reason: collision with root package name */
    private final zbkx f16149c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f16150d;

    zba(zbo zboVar, zbok zbokVar, zbkx zbkxVar, boolean z) {
        this.f16147a = zboVar;
        this.f16148b = zbokVar;
        if (zbkxVar == null) {
            throw new NullPointerException("Null lineBoxParcels");
        }
        this.f16149c = zbkxVar;
        this.f16150d = z;
    }

    @Override // com.google.mlkit.vision.text.pipeline.zbn
    public final zbkx a() {
        return this.f16149c;
    }

    @Override // com.google.mlkit.vision.text.pipeline.zbn
    public final zbok b() {
        return this.f16148b;
    }

    @Override // com.google.mlkit.vision.text.pipeline.zbn
    public final zbo c() {
        return this.f16147a;
    }

    @Override // com.google.mlkit.vision.text.pipeline.zbn
    public final boolean d() {
        return this.f16150d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zbn) {
            zbn zbnVar = (zbn) obj;
            if (this.f16147a.equals(zbnVar.c()) && this.f16148b.equals(zbnVar.b()) && this.f16149c.equals(zbnVar.a()) && this.f16150d == zbnVar.d()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (true != this.f16150d ? 1237 : 1231) ^ ((((((this.f16147a.hashCode() ^ 1000003) * 1000003) ^ this.f16148b.hashCode()) * 1000003) ^ this.f16149c.hashCode()) * 1000003);
    }

    public final String toString() {
        zbkx zbkxVar = this.f16149c;
        zbok zbokVar = this.f16148b;
        return "VkpResults{status=" + this.f16147a.toString() + ", textParcel=" + zbokVar.toString() + ", lineBoxParcels=" + zbkxVar.toString() + ", fromColdCall=" + this.f16150d + "}";
    }
}
