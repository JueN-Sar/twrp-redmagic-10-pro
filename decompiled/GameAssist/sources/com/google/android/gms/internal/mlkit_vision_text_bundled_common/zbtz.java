package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtz;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;

/* loaded from: classes.dex */
public class zbtz<MessageType extends zbuf<MessageType, BuilderType>, BuilderType extends zbtz<MessageType, BuilderType>> extends zbsi<MessageType, BuilderType> {

    /* renamed from: c, reason: collision with root package name */
    private final zbuf f12974c;

    /* renamed from: h, reason: collision with root package name */
    protected zbuf f12975h;

    protected zbtz(zbuf zbufVar) {
        this.f12974c = zbufVar;
        if (zbufVar.p()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.f12975h = zbufVar.x();
    }

    private static void i(Object obj, Object obj2) {
        zbvu.a().b(obj.getClass()).c(obj, obj2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn
    public final boolean b() {
        return zbuf.o(this.f12975h, false);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsi
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public final zbtz clone() {
        zbtz zbtzVar = (zbtz) this.f12974c.q(5, null, null);
        zbtzVar.f12975h = m();
        return zbtzVar;
    }

    public final zbtz k(zbuf zbufVar) {
        if (!this.f12974c.equals(zbufVar)) {
            if (!this.f12975h.p()) {
                o();
            }
            i(this.f12975h, zbufVar);
        }
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvl
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public final zbuf d() {
        zbuf m2 = m();
        if (zbuf.o(m2, true)) {
            return m2;
        }
        throw new zbwk(m2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvl
    public zbuf m() {
        if (!this.f12975h.p()) {
            return this.f12975h;
        }
        this.f12975h.k();
        return this.f12975h;
    }

    protected final void n() {
        if (this.f12975h.p()) {
            return;
        }
        o();
    }

    protected void o() {
        zbuf x = this.f12974c.x();
        i(x, this.f12975h);
        this.f12975h = x;
    }
}
