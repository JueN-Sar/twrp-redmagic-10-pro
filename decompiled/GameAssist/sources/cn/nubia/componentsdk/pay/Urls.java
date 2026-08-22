package cn.nubia.componentsdk.pay;

/* loaded from: classes.dex */
public class Urls {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f6022a = true;

    /* renamed from: b, reason: collision with root package name */
    private static String f6023b = "https://nubiapay-dev.app.nubia.cn/Api/process";

    /* renamed from: c, reason: collision with root package name */
    private static String f6024c = "https://api-pay-test.nubia.cn/message/getMessage";

    /* renamed from: d, reason: collision with root package name */
    private static ServerEnvironment f6025d = ServerEnvironment.FORMAL;

    /* renamed from: cn.nubia.componentsdk.pay.Urls$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f6026a;

        static {
            int[] iArr = new int[ServerEnvironment.values().length];
            f6026a = iArr;
            try {
                iArr[ServerEnvironment.DEV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6026a[ServerEnvironment.TEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6026a[ServerEnvironment.FORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static {
        int i2 = AnonymousClass1.f6026a[f6025d.ordinal()];
        if (i2 == 1) {
            f6022a = true;
            f6023b = "https://nubiapay-dev.app.nubia.cn/Api/process";
            f6024c = "https://api-pay-test.nubia.cn/message/getMessage";
        } else if (i2 == 2) {
            f6022a = true;
            f6023b = "https://api-pay-test.nubia.cn/Api/process";
            f6024c = "https://api-pay-test.nubia.cn/message/getMessage";
        } else {
            if (i2 != 3) {
                return;
            }
            f6022a = false;
            f6023b = "https://npma.nubia.com/Api/process";
            f6024c = "https://npma.nubia.com/message/getMessage";
        }
    }

    public static String a() {
        return AnonymousClass1.f6026a[f6025d.ordinal()] != 3 ? "https://api-pay-test.nubia.cn/order/query" : "https://npma.nubia.com/order/query";
    }

    public static String b() {
        return f6023b;
    }
}
