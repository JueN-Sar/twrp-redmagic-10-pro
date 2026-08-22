package kotlinx.coroutines.internal;

import cn.nubia.gameassist.view.NubiaTextClock;
import com.google.android.gms.common.api.Api;
import kotlin.Metadata;
import kotlin.text.StringsKt__StringNumberConversionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public final /* synthetic */ class SystemPropsKt__SystemProps_commonKt {
    public static final int a(String str, int i2, int i3, int i4) {
        return (int) SystemPropsKt.c(str, i2, i3, i4);
    }

    public static final long b(String str, long j2, long j3, long j4) {
        Long f2;
        String d2 = SystemPropsKt.d(str);
        if (d2 == null) {
            return j2;
        }
        f2 = StringsKt__StringNumberConversionsKt.f(d2);
        if (f2 == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + d2 + NubiaTextClock.QUOTE).toString());
        }
        long longValue = f2.longValue();
        if (j3 <= longValue && longValue <= j4) {
            return longValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j3 + ".." + j4 + ", but is '" + longValue + NubiaTextClock.QUOTE).toString());
    }

    public static final boolean c(String str, boolean z) {
        String d2 = SystemPropsKt.d(str);
        return d2 != null ? Boolean.parseBoolean(d2) : z;
    }

    public static /* synthetic */ int d(String str, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i3 = 1;
        }
        if ((i5 & 8) != 0) {
            i4 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        }
        return SystemPropsKt.b(str, i2, i3, i4);
    }

    public static /* synthetic */ long e(String str, long j2, long j3, long j4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            j3 = 1;
        }
        long j5 = j3;
        if ((i2 & 8) != 0) {
            j4 = Long.MAX_VALUE;
        }
        return SystemPropsKt.c(str, j2, j5, j4);
    }
}
