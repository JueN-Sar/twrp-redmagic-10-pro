package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.model.RemoteModel;
import java.io.File;

@KeepForSdk
/* loaded from: classes.dex */
public interface ModelValidator {

    @KeepForSdk
    public static class ValidationResult {

        /* renamed from: c, reason: collision with root package name */
        public static final ValidationResult f15988c = new ValidationResult(ErrorCode.OK, null);

        /* renamed from: a, reason: collision with root package name */
        private final ErrorCode f15989a;

        /* renamed from: b, reason: collision with root package name */
        private final String f15990b;

        @KeepForSdk
        public enum ErrorCode {
            OK,
            TFLITE_VERSION_INCOMPATIBLE,
            MODEL_FORMAT_INVALID
        }

        public ValidationResult(ErrorCode errorCode, String str) {
            this.f15989a = errorCode;
            this.f15990b = str;
        }

        public ErrorCode a() {
            return this.f15989a;
        }

        public boolean b() {
            return this.f15989a == ErrorCode.OK;
        }
    }

    ValidationResult a(File file, RemoteModel remoteModel);
}
