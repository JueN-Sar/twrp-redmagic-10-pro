package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcelable;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public interface SafeParcelable extends Parcelable {

    @NonNull
    public static final String NULL = "SAFE_PARCELABLE_NULL_STRING";

    /* JADX WARN: Method from annotation default annotation not found: creatorIsFinal */
    /* JADX WARN: Method from annotation default annotation not found: doNotParcelTypeDefaultValues */
    /* JADX WARN: Method from annotation default annotation not found: validate */
    public @interface Class {
    }

    public @interface Constructor {
    }

    /* JADX WARN: Method from annotation default annotation not found: defaultValue */
    /* JADX WARN: Method from annotation default annotation not found: defaultValueUnchecked */
    /* JADX WARN: Method from annotation default annotation not found: getter */
    /* JADX WARN: Method from annotation default annotation not found: type */
    public @interface Field {
    }

    /* JADX WARN: Method from annotation default annotation not found: getter */
    public @interface Indicator {
    }

    public @interface Param {
    }

    /* JADX WARN: Method from annotation default annotation not found: defaultValue */
    /* JADX WARN: Method from annotation default annotation not found: defaultValueUnchecked */
    public @interface RemovedParam {
    }

    public @interface Reserved {
    }

    /* JADX WARN: Method from annotation default annotation not found: getter */
    /* JADX WARN: Method from annotation default annotation not found: type */
    public @interface VersionField {
    }
}
