package com.zte.gameassist.common;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public class DefaultCommander implements IGameAssistCommander {

    /* renamed from: h, reason: collision with root package name */
    private static final String[] f16472h = {"cn.nubia.gamehelpmodule:Macro-gamehelpermoudle", "com.dts.dtsxultra:GameEqWindow", "com.zte.onemorething:AISpeakerSettingView", "com.zte.onemorething:AITranslationGuideWindow", "com.zte.onemorething:AITranslationSettingView", "com.zte.onemorething:VoiceController", "cn.nubia.gameassist:ScreenExtraction.Settings", "cn.nubia.gameassist:LowSugar"};

    /* renamed from: c, reason: collision with root package name */
    private final Context f16473c;

    public DefaultCommander(Context context) {
        this.f16473c = context;
    }

    private void b(float f2) {
        SystemMgr.y(this.f16473c).X(f2);
    }

    private void c(Bundle bundle) {
        String string = bundle.getString(AbsGameAssistToken.BUNDLE_KEY_NAME);
        String string2 = bundle.getString(AbsGameAssistToken.BUNDLE_KEY_VALUE);
        if (TextUtils.isEmpty(string2)) {
            return;
        }
        Toast.makeText(this.f16473c, string2, 0).show();
        GaLog.e("DCommander", "showToastInGameAsisst from " + string);
    }

    public void a(boolean z) {
        if (SystemMgr.I() != z) {
            Settings.System.putInt(this.f16473c.getContentResolver(), "keyguard_is_showing", z ? 1 : 0);
        }
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        if ("getSystemWindowFilter".equals(str)) {
            bundle.putStringArray("MutexWindows", f16472h);
            callback.callback("getSystemWindowFilter", bundle);
            return;
        }
        if ("onWindowOverrideScreenBrightnessChanged".equals(str)) {
            b(bundle.getFloat("brightness", Float.NaN));
            return;
        }
        if (ZteFeature.isSupportGameAssist() && "onKeyguardState".equals(str) && bundle.containsKey("keyguardShow")) {
            a(bundle.getBoolean("keyguardShow", false));
        } else if ("show_toast".equals(str)) {
            c(bundle);
        }
    }
}
