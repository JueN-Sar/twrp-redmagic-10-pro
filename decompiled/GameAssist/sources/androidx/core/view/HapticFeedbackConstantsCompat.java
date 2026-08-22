package androidx.core.view;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public final class HapticFeedbackConstantsCompat {

    @VisibleForTesting
    static final int FIRST_CONSTANT_INT = 0;

    @VisibleForTesting
    static final int LAST_CONSTANT_INT = 27;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface HapticFeedbackFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface HapticFeedbackType {
    }
}
