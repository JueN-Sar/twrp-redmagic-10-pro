package cn.nubia.systemwrapper;

import android.content.Context;
import cn.nubia.gameassist.utils.GameKeysHelper;
import com.zte.shared.wrapper.GameKeysHelperWrapper;

/* loaded from: classes.dex */
public class GameKeysWrapper {

    /* renamed from: a, reason: collision with root package name */
    private static final GameKeysWrapper f9222a = new GameKeysWrapper();

    public static GameKeysWrapper b() {
        return f9222a;
    }

    public void a(Context context, int i2) {
        GameKeysHelper.b().a(context, i2);
    }

    public int c(Context context) {
        return GameKeysHelper.b().c(context);
    }

    public boolean d(Context context, String str, int i2) {
        return GameKeysHelperWrapper.getDefault().isPackageInstalled(context, str, i2);
    }

    public void e(Context context, int i2) {
        GameKeysHelper.b().d(context, i2);
    }
}
