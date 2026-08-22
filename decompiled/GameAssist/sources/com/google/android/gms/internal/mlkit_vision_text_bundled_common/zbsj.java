package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsi;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsj;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class zbsj<MessageType extends zbsj<MessageType, BuilderType>, BuilderType extends zbsi<MessageType, BuilderType>> implements zbvm {
    protected int zba = 0;

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm
    public final zbtc d() {
        try {
            int a2 = a();
            zbtc zbtcVar = zbtc.zbb;
            byte[] bArr = new byte[a2];
            zbth zbthVar = new zbth(bArr, 0, a2);
            g(zbthVar);
            return zbsy.a(zbthVar, bArr);
        } catch (IOException e2) {
            throw new RuntimeException("Serializing " + this.getClass().getName() + " to a ByteString threw an IOException (should never happen).", e2);
        }
    }

    int h(zbvx zbvxVar) {
        throw null;
    }

    public final byte[] i() {
        try {
            int a2 = a();
            byte[] bArr = new byte[a2];
            zbth zbthVar = new zbth(bArr, 0, a2);
            g(zbthVar);
            zbthVar.f();
            return bArr;
        } catch (IOException e2) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e2);
        }
    }
}
