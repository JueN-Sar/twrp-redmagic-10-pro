package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;

@KeepForSdk
/* loaded from: classes.dex */
public class RemoteModelFileManager {

    /* renamed from: h, reason: collision with root package name */
    private static final GmsLogger f16005h = new GmsLogger("RemoteModelFileManager", "");

    /* renamed from: a, reason: collision with root package name */
    private final MlKitContext f16006a;

    /* renamed from: b, reason: collision with root package name */
    private final String f16007b;

    /* renamed from: c, reason: collision with root package name */
    private final ModelType f16008c;

    /* renamed from: d, reason: collision with root package name */
    private final ModelValidator f16009d;

    /* renamed from: e, reason: collision with root package name */
    private final RemoteModelFileMover f16010e;

    /* renamed from: f, reason: collision with root package name */
    private final SharedPrefManager f16011f;

    /* renamed from: g, reason: collision with root package name */
    private final ModelFileHelper f16012g;

    public RemoteModelFileManager(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, ModelFileHelper modelFileHelper, RemoteModelFileMover remoteModelFileMover) {
        this.f16006a = mlKitContext;
        ModelType d2 = remoteModel.d();
        this.f16008c = d2;
        this.f16007b = d2 == ModelType.TRANSLATE ? remoteModel.c() : remoteModel.e();
        this.f16009d = modelValidator;
        this.f16011f = SharedPrefManager.f(mlKitContext);
        this.f16012g = modelFileHelper;
        this.f16010e = remoteModelFileMover;
    }

    public File a(boolean z) {
        return this.f16012g.f(this.f16007b, this.f16008c, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x009f, code lost:
    
        com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager.f16005h.b("RemoteModelFileManager", "Hash does not match with expected: ".concat(java.lang.String.valueOf(r12)));
        com.google.android.gms.internal.mlkit_common.zzss.b("common").f(com.google.android.gms.internal.mlkit_common.zzsk.f(), r13, com.google.android.gms.internal.mlkit_common.zzmu.MODEL_HASH_MISMATCH, true, r10.f16008c, com.google.android.gms.internal.mlkit_common.zzna.SUCCEEDED);
        r11 = new com.google.mlkit.common.MlKitException("Hash does not match with expected", 102);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.io.File b(android.os.ParcelFileDescriptor r11, java.lang.String r12, com.google.mlkit.common.model.RemoteModel r13) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager.b(android.os.ParcelFileDescriptor, java.lang.String, com.google.mlkit.common.model.RemoteModel):java.io.File");
    }

    public final synchronized File c(File file) {
        File file2 = new File(String.valueOf(this.f16012g.e(this.f16007b, this.f16008c).getAbsolutePath()).concat("/0"));
        if (file2.exists()) {
            return file;
        }
        return file.renameTo(file2) ? file2 : file;
    }

    public final synchronized String d() {
        return this.f16012g.j(this.f16007b, this.f16008c);
    }

    public final synchronized void e(File file) {
        File[] listFiles;
        File a2 = a(false);
        if (a2.exists() && (listFiles = a2.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.equals(file)) {
                    this.f16012g.b(file);
                    return;
                }
            }
        }
    }

    public final synchronized boolean f(File file) {
        File e2 = this.f16012g.e(this.f16007b, this.f16008c);
        if (!e2.exists()) {
            return false;
        }
        File[] listFiles = e2.listFiles();
        boolean z = true;
        if (listFiles == null) {
            return true;
        }
        for (File file2 : listFiles) {
            if (!file2.equals(file) && !this.f16012g.b(file2)) {
                z = false;
            }
        }
        return z;
    }
}
