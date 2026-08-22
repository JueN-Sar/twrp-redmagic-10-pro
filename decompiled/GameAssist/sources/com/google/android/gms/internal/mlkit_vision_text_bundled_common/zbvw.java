package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbvw implements zbvj {

    /* renamed from: a, reason: collision with root package name */
    private final zbvm f13029a;

    /* renamed from: b, reason: collision with root package name */
    private final String f13030b;

    /* renamed from: c, reason: collision with root package name */
    private final Object[] f13031c;

    /* renamed from: d, reason: collision with root package name */
    private final int f13032d;

    zbvw(zbvm zbvmVar, String str, Object[] objArr) {
        this.f13029a = zbvmVar;
        this.f13030b = str;
        this.f13031c = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.f13032d = charAt;
            return;
        }
        int i2 = charAt & 8191;
        int i3 = 1;
        int i4 = 13;
        while (true) {
            int i5 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.f13032d = i2 | (charAt2 << i4);
                return;
            } else {
                i2 |= (charAt2 & 8191) << i4;
                i4 += 13;
                i3 = i5;
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvj
    public final zbvm a() {
        return this.f13029a;
    }

    final String b() {
        return this.f13030b;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvj
    public final boolean c() {
        return (this.f13032d & 2) == 2;
    }

    final Object[] d() {
        return this.f13031c;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvj
    public final int zbc() {
        int i2 = this.f13032d;
        if ((i2 & 1) != 0) {
            return 1;
        }
        return (i2 & 4) == 4 ? 3 : 2;
    }
}
