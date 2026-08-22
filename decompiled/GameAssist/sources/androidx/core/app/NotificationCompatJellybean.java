package androidx.core.app;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
class NotificationCompatJellybean {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2778a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private static final Object f2779b = new Object();

    static Bundle a(NotificationCompat.Action action) {
        Bundle bundle = new Bundle();
        IconCompat d2 = action.d();
        bundle.putInt("icon", d2 != null ? d2.k() : 0);
        bundle.putCharSequence("title", action.h());
        bundle.putParcelable("actionIntent", action.a());
        Bundle bundle2 = action.c() != null ? new Bundle(action.c()) : new Bundle();
        bundle2.putBoolean("android.support.allowGeneratedReplies", action.b());
        bundle.putBundle("extras", bundle2);
        bundle.putParcelableArray("remoteInputs", c(action.e()));
        bundle.putBoolean("showsUserInterface", action.g());
        bundle.putInt("semanticAction", action.f());
        return bundle;
    }

    private static Bundle b(RemoteInput remoteInput) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteInput.i());
        bundle.putCharSequence("label", remoteInput.h());
        bundle.putCharSequenceArray("choices", remoteInput.e());
        bundle.putBoolean("allowFreeFormInput", remoteInput.c());
        bundle.putBundle("extras", remoteInput.g());
        Set d2 = remoteInput.d();
        if (d2 != null && !d2.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>(d2.size());
            Iterator it = d2.iterator();
            while (it.hasNext()) {
                arrayList.add((String) it.next());
            }
            bundle.putStringArrayList("allowedDataTypes", arrayList);
        }
        return bundle;
    }

    private static Bundle[] c(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        for (int i2 = 0; i2 < remoteInputArr.length; i2++) {
            bundleArr[i2] = b(remoteInputArr[i2]);
        }
        return bundleArr;
    }
}
