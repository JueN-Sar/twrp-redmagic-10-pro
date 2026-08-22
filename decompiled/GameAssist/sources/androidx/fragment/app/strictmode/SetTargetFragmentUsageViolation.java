package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
public final class SetTargetFragmentUsageViolation extends TargetFragmentUsageViolation {
    private final int requestCode;

    @NotNull
    private final Fragment targetFragment;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetTargetFragmentUsageViolation(@NotNull Fragment fragment, @NotNull Fragment targetFragment, int i2) {
        super(fragment, "Attempting to set target fragment " + targetFragment + " with request code " + i2 + " for fragment " + fragment);
        Intrinsics.e(fragment, "fragment");
        Intrinsics.e(targetFragment, "targetFragment");
        this.targetFragment = targetFragment;
        this.requestCode = i2;
    }
}
