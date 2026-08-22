package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* loaded from: classes.dex */
public final class zaz extends RemoteCreator {

    /* renamed from: c, reason: collision with root package name */
    private static final zaz f11088c = new zaz();

    private zaz() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View c(Context context, int i2, int i3) {
        zaz zazVar = f11088c;
        try {
            return (View) ObjectWrapper.unwrap(((zam) zazVar.b(context)).zae(ObjectWrapper.wrap(context), new zax(1, i2, i3, null)));
        } catch (Exception e2) {
            throw new RemoteCreator.RemoteCreatorException("Could not get button with size " + i2 + " and color " + i3, e2);
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    public final /* synthetic */ Object a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        return queryLocalInterface instanceof zam ? (zam) queryLocalInterface : new zam(iBinder);
    }
}
