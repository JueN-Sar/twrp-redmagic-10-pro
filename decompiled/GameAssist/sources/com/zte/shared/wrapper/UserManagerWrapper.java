package com.zte.shared.wrapper;

import android.content.Context;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class UserManagerWrapper {
    public static UserHandle getUserHandle(Context context, int i2) {
        int i3;
        UserManager userManager = (UserManager) context.getSystemService("user");
        Iterator it = userManager.getProfiles(userManager.getUserHandle()).iterator();
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            UserInfo userInfo = (UserInfo) it.next();
            if (userInfo.isManagedProfile() && (i3 = userInfo.id) == i2) {
                break;
            }
        }
        UserHandle userHandle = i3 != -1 ? new UserHandle(i3) : null;
        Log.i("SysShared", "getDoubleAppsProfile: userHandle" + i3);
        return userHandle;
    }

    public static int getUserId(int i2) {
        return UserHandle.getUserId(i2);
    }
}
