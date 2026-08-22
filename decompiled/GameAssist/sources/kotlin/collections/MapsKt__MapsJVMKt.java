package kotlin.collections;

import com.google.android.gms.common.api.Api;
import java.util.Collections;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
    public static Map a(Map builder) {
        Intrinsics.e(builder, "builder");
        return ((MapBuilder) builder).i();
    }

    public static Map b(int i2) {
        return new MapBuilder(i2);
    }

    public static int c(int i2) {
        return i2 < 0 ? i2 : i2 < 3 ? i2 + 1 : i2 < 1073741824 ? (int) ((i2 / 0.75f) + 1.0f) : Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    public static final Map d(Pair pair) {
        Intrinsics.e(pair, "pair");
        Map singletonMap = Collections.singletonMap(pair.c(), pair.d());
        Intrinsics.d(singletonMap, "singletonMap(pair.first, pair.second)");
        return singletonMap;
    }

    public static final Map e(Map map) {
        Intrinsics.e(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map singletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        Intrinsics.d(singletonMap, "with(entries.iterator().…ingletonMap(key, value) }");
        return singletonMap;
    }
}
