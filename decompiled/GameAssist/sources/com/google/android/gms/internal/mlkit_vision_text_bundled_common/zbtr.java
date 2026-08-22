package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.mlkit.common.MlKitException;
import java.util.Map;

/* loaded from: classes.dex */
final class zbtr extends zbtq {
    zbtr() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtq
    final void a(Object obj) {
        ((zbub) obj).zbb.h();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtq
    final void b(zbwy zbwyVar, Map.Entry entry) {
        zbuc zbucVar = (zbuc) entry.getKey();
        zbww zbwwVar = zbww.zba;
        switch (zbucVar.f12978h.ordinal()) {
            case 0:
                zbwyVar.I(32149011, ((Double) entry.getValue()).doubleValue());
                break;
            case 1:
                zbwyVar.n(32149011, ((Float) entry.getValue()).floatValue());
                break;
            case 2:
                zbwyVar.C(32149011, ((Long) entry.getValue()).longValue());
                break;
            case 3:
                zbwyVar.m(32149011, ((Long) entry.getValue()).longValue());
                break;
            case 4:
                zbwyVar.l(32149011, ((Integer) entry.getValue()).intValue());
                break;
            case 5:
                zbwyVar.c(32149011, ((Long) entry.getValue()).longValue());
                break;
            case 6:
                zbwyVar.f(32149011, ((Integer) entry.getValue()).intValue());
                break;
            case 7:
                zbwyVar.y(32149011, ((Boolean) entry.getValue()).booleanValue());
                break;
            case 8:
                zbwyVar.t(32149011, (String) entry.getValue());
                break;
            case 9:
                zbwyVar.d(32149011, entry.getValue(), zbvu.a().b(entry.getValue().getClass()));
                break;
            case 10:
                zbwyVar.r(32149011, entry.getValue(), zbvu.a().b(entry.getValue().getClass()));
                break;
            case 11:
                zbwyVar.G(32149011, (zbtc) entry.getValue());
                break;
            case 12:
                zbwyVar.a(32149011, ((Integer) entry.getValue()).intValue());
                break;
            case 13:
                zbwyVar.l(32149011, ((Integer) entry.getValue()).intValue());
                break;
            case 14:
                zbwyVar.D(32149011, ((Integer) entry.getValue()).intValue());
                break;
            case 15:
                zbwyVar.B(32149011, ((Long) entry.getValue()).longValue());
                break;
            case 16:
                zbwyVar.x(32149011, ((Integer) entry.getValue()).intValue());
                break;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                zbwyVar.L(32149011, ((Long) entry.getValue()).longValue());
                break;
        }
    }
}
