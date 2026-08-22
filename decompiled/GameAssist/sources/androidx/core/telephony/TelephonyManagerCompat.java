package androidx.core.telephony;

import android.annotation.SuppressLint;
import android.telephony.TelephonyManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

/* loaded from: classes.dex */
public class TelephonyManagerCompat {

    @RequiresApi
    private static class Api23Impl {
        @SuppressLint({"MissingPermission"})
        @Nullable
        @RequiresPermission
        @DoNotInline
        static String a(TelephonyManager telephonyManager, int i2) {
            return telephonyManager.getDeviceId(i2);
        }
    }

    @RequiresApi
    private static class Api26Impl {
        @SuppressLint({"MissingPermission"})
        @Nullable
        @RequiresPermission
        @DoNotInline
        static String a(TelephonyManager telephonyManager) {
            return telephonyManager.getImei();
        }
    }

    @RequiresApi
    private static class Api30Impl {
        @DoNotInline
        static int a(TelephonyManager telephonyManager) {
            return telephonyManager.getSubscriptionId();
        }
    }
}
