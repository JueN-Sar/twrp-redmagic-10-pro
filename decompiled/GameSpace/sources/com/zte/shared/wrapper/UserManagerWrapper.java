package com.zte.shared.wrapper;

import android.content.Context;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class UserManagerWrapper {
    public static UserHandle getUserHandle(Context context, int i) {
        int i2;
        UserManager userManager = (UserManager) context.getSystemService("user");
        Iterator it = userManager.getProfiles(userManager.getUserHandle()).iterator();
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            UserInfo userInfo = (UserInfo) it.next();
            if (userInfo.isManagedProfile() && userInfo.id == i) {
                i2 = userInfo.id;
                break;
            }
        }
        UserHandle userHandle = i2 != -1 ? new UserHandle(i2) : null;
        Log.i("SysShared", "getDoubleAppsProfile: userHandle" + i2);
        return userHandle;
    }

    public static int getUserId(int i) {
        return UserHandle.getUserId(i);
    }
}
