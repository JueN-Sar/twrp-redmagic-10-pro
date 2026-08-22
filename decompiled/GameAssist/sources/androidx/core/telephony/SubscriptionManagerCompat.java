package androidx.core.telephony;

import android.telephony.SubscriptionManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
public class SubscriptionManagerCompat {

    @RequiresApi
    private static class Api29Impl {
        @DoNotInline
        static int a(int i2) {
            return SubscriptionManager.getSlotIndex(i2);
        }
    }
}
