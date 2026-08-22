package androidx.lifecycle.viewmodel;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public abstract class CreationExtras {

    /* renamed from: a, reason: collision with root package name */
    private final Map f4420a = new LinkedHashMap();

    @Metadata
    public static final class Empty extends CreationExtras {

        /* renamed from: b, reason: collision with root package name */
        public static final Empty f4421b = new Empty();

        private Empty() {
        }

        @Override // androidx.lifecycle.viewmodel.CreationExtras
        public Object a(Key key) {
            Intrinsics.e(key, "key");
            return null;
        }
    }

    @Metadata
    public interface Key<T> {
    }

    public abstract Object a(Key key);

    public final Map b() {
        return this.f4420a;
    }
}
