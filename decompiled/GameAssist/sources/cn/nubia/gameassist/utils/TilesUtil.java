package cn.nubia.gameassist.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.LowSugarComService;
import cn.nubia.componentcenter.service.NeoGameLibComService;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.tiles.GameThirdAppTitle;
import cn.nubia.gameassist.pips.PipTiles;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.shared.wrapper.ActivityManagerWrapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class TilesUtil {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7693a = false;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f7694b = false;

    /* renamed from: c, reason: collision with root package name */
    private static String f7695c = "";

    public static void c(Context context) {
        f7693a = GameKeysWrapper.b().d(context, "cn.nubia.game.magicvoice", 0);
    }

    public static String d(ArrayList arrayList) {
        return (arrayList == null || arrayList.isEmpty()) ? "" : (String) arrayList.stream().filter(new Predicate() { // from class: cn.nubia.gameassist.utils.n
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean p2;
                p2 = TilesUtil.p((String) obj);
                return p2;
            }
        }).map(new Function() { // from class: cn.nubia.gameassist.utils.o
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((String) obj).trim();
            }
        }).collect(Collectors.joining(","));
    }

    public static ArrayList e(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split(",")) {
            String trim = str2.trim();
            if (!TextUtils.isEmpty(trim)) {
                arrayList.add(trim);
            }
        }
        return arrayList;
    }

    public static void f(List list, Context context) {
        if (!ZteFeature.supportFan()) {
            list.remove("fan");
        }
        if (!ZteFeature.supportColorfulLight()) {
            list.remove("competition_light");
        } else if (!ZteFeature.isRedMagicPhone()) {
            if (!f7694b) {
                f7695c = r("/proc/driver/board_id");
                f7694b = true;
            }
            if ("4".equals(f7695c)) {
                list.remove("competition_light");
            }
        } else if (Build.PRODUCT.contains("NX789J")) {
            String str = SystemProperties.get("persist.vendor.custom.variant.id", "");
            if ("IP_PB_CN".equals(str) || "GEN_PB_US".equals(str) || "GEN_PB_EU".equals(str)) {
                list.remove("competition_light");
            }
        }
        if (!ZteFeature.isRedMagicProduct()) {
            list.remove("handle");
        }
        if (!f7693a) {
            list.remove("voice");
        }
        if (!ZteFeature.supportChargeSeparation()) {
            list.remove("charge_separation");
        }
        if (!ZteFeature.supportWindowReply()) {
            list.remove("active_mode");
        }
        if (!ZteFeature.isSupportMultiSubScreen()) {
            list.remove("multi_sub_screen");
        }
        if (!ZteFeature.supportVirtualHandle()) {
            list.remove("virtual_handle");
        }
        LowSugarComService lowSugarComService = (LowSugarComService) Router.getInstance().getService(LowSugarComService.class.getSimpleName());
        if (!ZteFeature.isSupportLowSugar() || lowSugarComService == null || !lowSugarComService.f(SystemMgr.t())) {
            list.remove("low_sugar");
        }
        if (!ZteFeature.isSupportAITranslation()) {
            list.remove("link_mics_translation");
        }
        NeoGameLibComService neoGameLibComService = (NeoGameLibComService) Router.getInstance().getService(NeoGameLibComService.class.getSimpleName());
        if (neoGameLibComService == null || !neoGameLibComService.a()) {
            list.remove("game_benefit");
        }
        if (FoldMgr.f() && FoldMgr.c().e()) {
            list.remove("game_custom");
        }
        if (!ZteFeature.supportImageSearch()) {
            list.remove("image_search");
        }
        if (ZteFeature.supportLiquidCooling()) {
            return;
        }
        list.remove("liquid_cool");
    }

    public static ArrayList g(Context context) {
        String g2 = SharedPreferencesUtil.k(context).g();
        final ArrayList i2 = i(context);
        GaLog.e("TilesUtil", "get local tile list : " + g2);
        if (g2 == null) {
            return i2;
        }
        ArrayList e2 = e(g2);
        e2.removeIf(new Predicate() { // from class: cn.nubia.gameassist.utils.p
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean q2;
                q2 = TilesUtil.q(i2, (String) obj);
                return q2;
            }
        });
        Iterator it = i2.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!e2.contains(str)) {
                int indexOf = i2.contains(str) ? i2.indexOf(str) : 0;
                e2.add(indexOf, str);
                GaLog.j("TilesUtil", "add spec : " + str + " to : " + indexOf);
            }
        }
        return e2;
    }

    private static String h(Context context) {
        return context.getResources().getString(R.string.cc_tiles_game);
    }

    private static ArrayList i(Context context) {
        ArrayList e2 = e(h(context));
        f(e2, context);
        return e2;
    }

    public static List j(Context context) {
        return AppsHelper.a(context);
    }

    public static ArrayList k(Context context) {
        String str;
        try {
            str = context.getContentResolver().getType(Uri.parse("content://com.zte.heartyservice.privacy.provider/app/hide_apps_str"));
        } catch (Exception e2) {
            GaLog.a("TilesUtil", "get hide app list error:" + e2);
            str = null;
        }
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split(";")) {
            String trim = str2.trim();
            if (!TextUtils.isEmpty(trim)) {
                arrayList.add(trim);
            }
        }
        return arrayList;
    }

    public static String l(String str) {
        str.hashCode();
        switch (str) {
            case "game_browser":
                return "cn.nubia.browser";
            case "telegram":
                return "org.telegram.messenger";
            case "game_bilibili":
                return "tv.danmaku.bili";
            case "game_douyin":
                return "com.ss.android.ugc.aweme";
            case "game_kuaishou":
                return "com.smile.gifmaker";
            case "game_qq":
                return "com.tencent.mobileqq";
            case "google_chrome":
                return "com.android.chrome";
            case "instagram":
                return "com.instagram.android";
            case "game_wechat":
                return "com.tencent.mm";
            case "facebook":
                return "com.facebook.katana";
            case "game_uc_browser":
                return "com.UCMobile";
            case "message":
                return "com.facebook.orca";
            case "discord":
                return "com.discord";
            case "game_qq_browser":
                return "com.tencent.mtt";
            case "whatsapp":
                return "com.whatsapp";
            default:
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int m(String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -1695216677:
                if (str.equals("game_browser")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -509027403:
                if (str.equals("game_bilibili")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -415658303:
                if (str.equals("game_douyin")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -257909062:
                if (str.equals("game_kuaishou")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -195606131:
                if (str.equals("game_qq")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 118507539:
                if (str.equals("game_wechat")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1934780818:
                if (str.equals("whatsapp")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return R.drawable.game_ic_qs_browser_off;
            case 1:
                return R.drawable.game_ic_qs_bilibili_off;
            case 2:
                return R.drawable.game_ic_qs_douin_off;
            case 3:
                return R.drawable.game_ic_qs_kuaishou_off;
            case 4:
                return R.drawable.game_ic_qs_qq_off;
            case 5:
                return R.drawable.game_ic_qs_wechat_off;
            case 6:
                return R.drawable.game_ic_qs_whatsapp;
            default:
                return -1;
        }
    }

    public static boolean n(Context context, String str) {
        return GameKeysWrapper.b().d(context, str, 999);
    }

    public static boolean o(Object obj) {
        return (obj instanceof GameThirdAppTitle) || (obj instanceof PipTiles);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean p(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean q(ArrayList arrayList, String str) {
        return (arrayList.contains(str) || "game_custom".equals(str)) ? false : true;
    }

    private static String r(String str) {
        String str2 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            try {
                str2 = bufferedReader.readLine();
                bufferedReader.close();
            } finally {
            }
        } catch (Exception unused) {
            GaLog.b("TilesUtil", "fail to read node value !");
        }
        return str2;
    }

    public static boolean s(Context context, String str) {
        if (context.getPackageManager().getLaunchIntentForPackage(SystemMgr.A(str)) == null) {
            return false;
        }
        return ActivityManagerWrapper.checkTaskSupportWr(SystemMgr.A(str));
    }
}
