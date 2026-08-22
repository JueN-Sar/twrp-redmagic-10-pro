package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbabl;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaco;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbadf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaeq;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbagk;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaia;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbaiy;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbgl;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbid;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbip;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbjg;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbjp;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbni;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbro;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtp;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbub;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbun;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbzv;

/* loaded from: classes.dex */
public final class zbcz extends zbub implements zbvn {
    private static final zbcz zbd;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbz zbA;
    private zbjp zbC;
    private zbip zbD;
    private zbj zbE;
    private zbzv zbF;
    private zbabl zbG;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbt zbH;
    private zbni zbJ;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbq zbK;
    private zbaia zbL;
    private zbaia zbM;
    private zbaia zbN;
    private int zbe;
    private int zbf;
    private long zbg;
    private zbgl zbh;
    private zbjp zbi;
    private zbip zbj;
    private zbid zbk;
    private int zbl;
    private zbaco zbm;
    private zbro zbn;
    private zbadf zbo;
    private zbjg zbp;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zben zbq;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcw zbr;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcl zbs;
    private zbaiy zbt;
    private zbdt zbu;
    private boolean zbv;
    private com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbat zbw;
    private zbagk zbx;
    private zbaeq zby;
    private zbbl zbz;
    private byte zbO = 2;
    private zbun zbB = zbuf.C();
    private String zbI = "";

    static {
        zbcz zbczVar = new zbcz();
        zbd = zbczVar;
        zbuf.m(zbcz.class, zbczVar);
    }

    private zbcz() {
    }

    public static zbcz H() {
        return zbd;
    }

    public static zbcz I(byte[] bArr, zbtp zbtpVar) {
        return (zbcz) zbuf.y(zbd, bArr, zbtpVar);
    }

    public final zbabl J() {
        zbabl zbablVar = this.zbG;
        return zbablVar == null ? zbabl.H() : zbablVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf
    protected final Object q(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 == 0) {
            return Byte.valueOf(this.zbO);
        }
        if (i3 == 2) {
            return zbuf.j(zbd, "\u0001\"\u0000\u0002\u0001'\"\u0000\u0001\t\u0001ဂ\u0000\u0002ဉ\u0001\u0003ᐉ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006᠌\u0005\u0007ᐉ\u0006\bᐉ\u0007\tᐉ\b\nဉ\t\u000bᐉ\n\fဉ\u000e\rဉ\u000b\u000eဉ\r\u0010ဇ\u000f\u0011ဉ\u0010\u0012ဉ\f\u0013ᐉ\u0011\u0014ဉ\u0012\u0015ᐉ\u0013\u0016ဉ\u0014\u0018\u001b\u0019ဉ\u0017\u001aဉ\u0018\u001bᐉ\u0015\u001eဉ\u0016\u001fᐉ\u0019!ဉ\u001a\"ဈ\u001b#ဉ\u001c$ဉ\u001d%ဉ\u001e&ဉ\u001f'ဉ ", new Object[]{"zbe", "zbf", "zbg", "zbh", "zbi", "zbj", "zbk", "zbl", com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbea.a(), "zbm", "zbn", "zbo", "zbp", "zbq", "zbu", "zbr", "zbt", "zbv", "zbw", "zbs", "zbx", "zby", "zbz", "zbA", "zbB", zbdg.class, "zbE", "zbF", "zbC", "zbD", "zbG", "zbH", "zbI", "zbJ", "zbK", "zbL", "zbM", "zbN"});
        }
        if (i3 == 3) {
            return new zbcz();
        }
        zbcx zbcxVar = null;
        if (i3 == 4) {
            return new zbcy(zbcxVar);
        }
        if (i3 == 5) {
            return zbd;
        }
        this.zbO = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
