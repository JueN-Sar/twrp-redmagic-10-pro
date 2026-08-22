package cn.nubia.nbgame.sdk.util;

import android.content.Context;

/* loaded from: classes.dex */
public class Constant {

    /* renamed from: a, reason: collision with root package name */
    public static byte[] f8312a;

    public interface IInitParams {
    }

    public static void a(Context context, boolean z) {
        if (context == null) {
            return;
        }
        SPUtils.c(context).f("LAST_LOGIN_IS_IDCARD", z);
    }
}
