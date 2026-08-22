package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.model.RemoteModel;
import java.util.UUID;

@KeepForSdk
/* loaded from: classes.dex */
public class SharedPrefManager {

    /* renamed from: b, reason: collision with root package name */
    public static final Component f15970b = Component.a(SharedPrefManager.class).b(Dependency.g(MlKitContext.class)).b(Dependency.g(Context.class)).d(new ComponentFactory() { // from class: com.google.mlkit.common.sdkinternal.zzs
        @Override // com.google.firebase.components.ComponentFactory
        public final Object a(ComponentContainer componentContainer) {
            return new SharedPrefManager((Context) componentContainer.a(Context.class));
        }
    }).c();

    /* renamed from: a, reason: collision with root package name */
    protected final Context f15971a;

    public SharedPrefManager(Context context) {
        this.f15971a = context;
    }

    public static SharedPrefManager f(MlKitContext mlKitContext) {
        return (SharedPrefManager) mlKitContext.a(SharedPrefManager.class);
    }

    public synchronized void a(RemoteModel remoteModel) {
        p().edit().remove(String.format("downloading_model_id_%s", remoteModel.e())).remove(String.format("downloading_model_hash_%s", remoteModel.e())).remove(String.format("downloading_model_type_%s", c(remoteModel))).remove(String.format("downloading_begin_time_%s", remoteModel.e())).remove(String.format("model_first_use_time_%s", remoteModel.e())).apply();
    }

    public synchronized void b(RemoteModel remoteModel) {
        p().edit().remove(String.format("current_model_hash_%s", remoteModel.e())).commit();
    }

    public synchronized String c(RemoteModel remoteModel) {
        return p().getString(String.format("downloading_model_hash_%s", remoteModel.e()), null);
    }

    public synchronized Long d(RemoteModel remoteModel) {
        long j2 = p().getLong(String.format("downloading_model_id_%s", remoteModel.e()), -1L);
        if (j2 < 0) {
            return null;
        }
        return Long.valueOf(j2);
    }

    public synchronized String e(RemoteModel remoteModel) {
        return p().getString(String.format("bad_hash_%s", remoteModel.e()), null);
    }

    public synchronized String g(RemoteModel remoteModel) {
        return p().getString(String.format("current_model_hash_%s", remoteModel.e()), null);
    }

    public synchronized String h() {
        String string = p().getString("ml_sdk_instance_id", null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        p().edit().putString("ml_sdk_instance_id", uuid).apply();
        return uuid;
    }

    public synchronized long i(RemoteModel remoteModel) {
        return p().getLong(String.format("downloading_begin_time_%s", remoteModel.e()), 0L);
    }

    public synchronized long j(RemoteModel remoteModel) {
        return p().getLong(String.format("model_first_use_time_%s", remoteModel.e()), 0L);
    }

    public synchronized String k() {
        return p().getString("app_version", null);
    }

    public synchronized void l(long j2, ModelInfo modelInfo) {
        String b2 = modelInfo.b();
        p().edit().putString(String.format("downloading_model_hash_%s", b2), modelInfo.a()).putLong(String.format("downloading_model_id_%s", b2), j2).putLong(String.format("downloading_begin_time_%s", b2), SystemClock.elapsedRealtime()).apply();
    }

    public synchronized void m(RemoteModel remoteModel, String str, String str2) {
        p().edit().putString(String.format("bad_hash_%s", remoteModel.e()), str).putString("app_version", str2).apply();
    }

    public synchronized void n(RemoteModel remoteModel, String str) {
        p().edit().putString(String.format("current_model_hash_%s", remoteModel.e()), str).apply();
    }

    public synchronized void o(RemoteModel remoteModel, long j2) {
        p().edit().putLong(String.format("model_first_use_time_%s", remoteModel.e()), j2).apply();
    }

    protected final SharedPreferences p() {
        return this.f15971a.getSharedPreferences("com.google.mlkit.internal", 0);
    }
}
