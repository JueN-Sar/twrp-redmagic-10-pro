package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover;
import java.io.File;

/* loaded from: classes.dex */
public final class zza implements RemoteModelFileMover {

    /* renamed from: c, reason: collision with root package name */
    private static final GmsLogger f15895c = new GmsLogger("CustomModelFileMover", "");

    /* renamed from: a, reason: collision with root package name */
    private final String f15896a;

    /* renamed from: b, reason: collision with root package name */
    private final ModelFileHelper f15897b;

    public zza(MlKitContext mlKitContext, String str) {
        this.f15896a = str;
        this.f15897b = new ModelFileHelper(mlKitContext);
    }

    private static boolean b(File file, File file2) {
        String absolutePath = file.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        if (file.renameTo(file2)) {
            f15895c.b("CustomModelFileMover", String.format("Moved file from %s to %s successfully", absolutePath, absolutePath2));
            file2.setExecutable(false);
            file2.setWritable(false);
            return true;
        }
        GmsLogger gmsLogger = f15895c;
        gmsLogger.b("CustomModelFileMover", String.format("Move file to %s failed, remove the temp file %s.", absolutePath2, absolutePath));
        if (!file.delete()) {
            gmsLogger.b("CustomModelFileMover", "Failed to delete the temp file: ".concat(String.valueOf(absolutePath)));
        }
        return false;
    }

    @Override // com.google.mlkit.common.sdkinternal.model.RemoteModelFileMover
    public final File a(File file) {
        File file2;
        ModelFileHelper modelFileHelper = this.f15897b;
        String str = this.f15896a;
        ModelType modelType = ModelType.CUSTOM;
        File e2 = modelFileHelper.e(str, modelType);
        File file3 = new File(new File(e2, String.valueOf(this.f15897b.d(e2) + 1)), "model.tflite");
        File parentFile = file3.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            ((File) Preconditions.i(parentFile)).mkdirs();
        }
        File file4 = null;
        if (!b(file, file3)) {
            return null;
        }
        File g2 = this.f15897b.g(this.f15896a, modelType, "labels.txt");
        if (g2.exists()) {
            file2 = new File(parentFile, "labels.txt");
            if (!b(g2, file2)) {
                return null;
            }
        } else {
            file2 = null;
        }
        File g3 = this.f15897b.g(this.f15896a, modelType, "manifest.json");
        if (g3.exists()) {
            File file5 = new File(parentFile, "manifest.json");
            if (!b(g3, file5)) {
                return null;
            }
            file4 = file5;
        }
        return (file2 == null && file4 == null) ? file3 : parentFile;
    }
}
