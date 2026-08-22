package com.google.android.gms.auth.api.signin;

import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* loaded from: classes.dex */
public final /* synthetic */ class zaa implements Comparator {

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ zaa f10482c = new zaa();

    private /* synthetic */ zaa() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Parcelable.Creator<GoogleSignInAccount> creator = GoogleSignInAccount.CREATOR;
        return ((Scope) obj).G().compareTo(((Scope) obj2).G());
    }
}
