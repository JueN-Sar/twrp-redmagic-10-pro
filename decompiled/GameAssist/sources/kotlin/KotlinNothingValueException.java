package kotlin;

import org.jetbrains.annotations.Nullable;

@SinceKotlin
@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class KotlinNothingValueException extends RuntimeException {
    public KotlinNothingValueException() {
    }

    public KotlinNothingValueException(@Nullable String str) {
        super(str);
    }

    public KotlinNothingValueException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }

    public KotlinNothingValueException(@Nullable Throwable th) {
        super(th);
    }
}
