package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes.dex */
public abstract class Violation extends RuntimeException {

    @NotNull
    private final Fragment fragment;

    public /* synthetic */ Violation(Fragment fragment, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragment, (i2 & 2) != 0 ? null : str);
    }

    public final Fragment a() {
        return this.fragment;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Violation(@NotNull Fragment fragment, @Nullable String str) {
        super(str);
        Intrinsics.e(fragment, "fragment");
        this.fragment = fragment;
    }
}
