package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public class zbua extends zbtz implements zbvn {
    protected zbua(zbub zbubVar) {
        super(zbubVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtz
    protected final void o() {
        super.o();
        if (((zbub) this.f12975h).zbb != zbtu.e()) {
            zbub zbubVar = (zbub) this.f12975h;
            zbubVar.zbb = zbubVar.zbb.clone();
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtz
    /* renamed from: p, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final zbub m() {
        if (!((zbub) this.f12975h).p()) {
            return (zbub) this.f12975h;
        }
        ((zbub) this.f12975h).zbb.h();
        return (zbub) super.m();
    }
}
