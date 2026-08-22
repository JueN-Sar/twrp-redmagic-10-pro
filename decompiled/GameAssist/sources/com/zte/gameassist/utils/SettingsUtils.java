package com.zte.gameassist.utils;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.SystemMgr;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class SettingsUtils {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f17046a;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(String str, String str2, AbsGameAssistToken.GameAssistControllerWrapper gameAssistControllerWrapper) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, str);
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_VALUE, str2);
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_TYPE, "system");
        gameAssistControllerWrapper.invake("set_settings", bundle, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Context context, final String str, final String str2) {
        SystemMgr.y(context).x().ifPresent(new Consumer() { // from class: com.zte.gameassist.utils.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SettingsUtils.c(str, str2, (AbsGameAssistToken.GameAssistControllerWrapper) obj);
            }
        });
    }

    public static void e(final Context context, final String str, final String str2) {
        Runnable runnable = new Runnable() { // from class: com.zte.gameassist.utils.b
            @Override // java.lang.Runnable
            public final void run() {
                SettingsUtils.d(context, str, str2);
            }
        };
        if (f17046a) {
            runnable.run();
            return;
        }
        try {
            Settings.System.putString(context.getContentResolver(), str, str2);
        } catch (Exception e2) {
            f17046a = true;
            runnable.run();
            e2.printStackTrace();
        }
    }
}
