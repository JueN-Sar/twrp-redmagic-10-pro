package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class EnumEntriesSerializationProxy<E extends Enum<E>> implements Serializable {

    @NotNull
    private static final Companion Companion = new Companion(null);
    private static final long serialVersionUID = 0;

    /* renamed from: c, reason: collision with root package name */
    @NotNull
    private final Class<E> f18424c;

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EnumEntriesSerializationProxy(@NotNull E[] entries) {
        Intrinsics.e(entries, "entries");
        Class<E> cls = (Class<E>) entries.getClass().getComponentType();
        Intrinsics.b(cls);
        this.f18424c = cls;
    }

    private final Object readResolve() {
        E[] enumConstants = this.f18424c.getEnumConstants();
        Intrinsics.d(enumConstants, "c.enumConstants");
        return EnumEntriesKt.a(enumConstants);
    }
}
