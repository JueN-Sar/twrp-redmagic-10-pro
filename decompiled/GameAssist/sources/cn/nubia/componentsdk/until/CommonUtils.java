package cn.nubia.componentsdk.until;

/* loaded from: classes.dex */
public class CommonUtils {

    /* renamed from: a, reason: collision with root package name */
    private static long f6054a = 1;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010d A[Catch: IOException -> 0x0109, TryCatch #6 {IOException -> 0x0109, blocks: (B:71:0x0105, B:62:0x010d, B:64:0x0112), top: B:70:0x0105 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0112 A[Catch: IOException -> 0x0109, TRY_LEAVE, TryCatch #6 {IOException -> 0x0109, blocks: (B:71:0x0105, B:62:0x010d, B:64:0x0112), top: B:70:0x0105 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.componentsdk.until.CommonUtils.a(java.lang.String):java.lang.String");
    }

    public static boolean b() {
        if (f6054a == 1) {
            f6054a = System.currentTimeMillis();
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - f6054a;
        if (0 < j2 && j2 < 400) {
            return true;
        }
        f6054a = currentTimeMillis;
        return false;
    }
}
