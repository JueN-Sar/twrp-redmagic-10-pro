package cn.nubia.systemwrapper;

import android.content.Context;
import com.android.systemui.shared.system.GameKeysHelperWrapper;

/* loaded from: classes2.dex */
public class GameKeysWrapper {
    private static final GameKeysWrapper sIntance = new GameKeysWrapper();

    public static GameKeysWrapper getDefault() {
        return sIntance;
    }

    public boolean isPackageInstalled(Context context, String str, int i) {
        return GameKeysHelperWrapper.getDefault().isPackageInstalled(context, str, i);
    }
}
