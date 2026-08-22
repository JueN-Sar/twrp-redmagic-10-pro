package cn.nubia.plugin.gameshader;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import com.google.mlkit.common.MlKitException;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.GameAssistControllerWrapper;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ShaderUtils {

    /* renamed from: a, reason: collision with root package name */
    private static GameAssistControllerWrapper.Callback f8514a = new GameAssistControllerWrapper.Callback() { // from class: cn.nubia.plugin.gameshader.ShaderUtils.1
        @Override // com.zte.shared.wrapper.GameAssistControllerWrapper.Callback
        protected void onCallback(Bundle bundle) {
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static HashMap f8515b = new HashMap();

    private static class Vector3 {
    }

    public static Drawable a(String str) {
        int i2;
        str.hashCode();
        switch (str) {
            case "darklight":
                i2 = R.drawable.gameshader_darklight;
                break;
            case "blackwhite":
                i2 = R.drawable.gameshader_blackwhite;
                break;
            case "oldfilm":
                i2 = R.drawable.gameshader_oldfilm;
                break;
            case "oldtime":
                i2 = R.drawable.gameshader_oldtime;
                break;
            case "crayon":
                i2 = R.drawable.gameshader_crayon;
                break;
            case "clearness":
                i2 = R.drawable.gameshader_clearness;
                break;
            case "eagleeye":
                i2 = R.drawable.gameshader_eagleeye;
                break;
            case "normal":
                i2 = R.drawable.gameshader_setting_img;
                break;
            case "sketch":
                i2 = R.drawable.gameshader_sketch;
                break;
            case "picturestory":
                i2 = R.drawable.gameshader_picturestory;
                break;
            case "scopegun":
                i2 = R.drawable.gameshader_scopegun;
                break;
            case "oil":
                i2 = R.drawable.gameshader_oil;
                break;
            case "gray":
                i2 = R.drawable.gameshader_gray;
                break;
            case "lomo":
                i2 = R.drawable.gameshader_lomo;
                break;
            case "snow":
                i2 = R.drawable.gameshader_snow;
                break;
            case "test":
                i2 = R.drawable.gameshader_setting_img;
                break;
            case "night":
                i2 = R.drawable.gameshader_night;
                break;
            case "negative":
                i2 = R.drawable.gameshader_negative;
                break;
            case "cyberpunk":
                i2 = R.drawable.gameshader_cyberpunk;
                break;
            default:
                i2 = R.drawable.gameshader_setting_img;
                break;
        }
        return GameAssistApplication.j().getDrawable(i2);
    }

    public static int b(String str) {
        str.hashCode();
        switch (str) {
            case "darklight":
                return 15;
            case "blackwhite":
                return 12;
            case "oldfilm":
                return 2;
            case "oldtime":
                return 7;
            case "crayon":
                return 13;
            case "clearness":
                return 17;
            case "eagleeye":
                return 16;
            case "sketch":
                return 3;
            case "picturestory":
                return 8;
            case "scopegun":
                return 4;
            case "oil":
                return 14;
            case "gray":
                return 6;
            case "lomo":
                return 10;
            case "snow":
                return 9;
            case "test":
                return 99;
            case "night":
                return 5;
            case "negative":
                return 1;
            case "cyberpunk":
                return 11;
            default:
                return 0;
        }
    }

    public static String c(int i2) {
        if (i2 == 32) {
            return "investigate";
        }
        if (i2 == 64) {
            return "fixedlook";
        }
        if (i2 == 99) {
            return "test";
        }
        if (i2 == 128) {
            return "deletemask";
        }
        switch (i2) {
            case 0:
                return "normal";
            case 1:
                return "negative";
            case 2:
                return "oldfilm";
            case 3:
                return "sketch";
            case 4:
                return "scopegun";
            case 5:
                return "night";
            case 6:
                return "gray";
            case 7:
                return "oldtime";
            case 8:
                return "picturestory";
            case 9:
                return "snow";
            case 10:
                return "lomo";
            case 11:
                return "cyberpunk";
            case 12:
                return "blackwhite";
            case 13:
                return "crayon";
            case 14:
                return "oil";
            case 15:
                return "darklight";
            case 16:
                return "eagleeye";
            case MlKitException.NETWORK_ISSUE /* 17 */:
                return "clearness";
            default:
                return i2 + "";
        }
    }

    public static String d(String str) {
        int i2;
        str.hashCode();
        switch (str) {
            case "darklight":
                i2 = R.string.gameshader_darklight_des;
                break;
            case "blackwhite":
                i2 = R.string.gameshader_blackwhite_des;
                break;
            case "oldfilm":
                i2 = R.string.gameshader_oldfilm_des;
                break;
            case "oldtime":
                i2 = R.string.gameshader_oldtime_des;
                break;
            case "crayon":
                i2 = R.string.gameshader_crayon_des;
                break;
            case "clearness":
                i2 = R.string.gameshader_clearness_des;
                break;
            case "eagleeye":
                i2 = R.string.gameshader_eagleeye_des;
                break;
            case "sketch":
                i2 = R.string.gameshader_sketch_des;
                break;
            case "picturestory":
                i2 = R.string.gameshader_picturestory_des;
                break;
            case "scopegun":
                i2 = R.string.gameshader_scopegun_des;
                break;
            case "oil":
                i2 = R.string.gameshader_oil_des;
                break;
            case "gray":
                i2 = R.string.gameshader_gray_des;
                break;
            case "lomo":
                i2 = R.string.gameshader_lomo_des;
                break;
            case "snow":
                i2 = R.string.gameshader_snow_des;
                break;
            case "night":
                i2 = R.string.gameshader_night_des;
                break;
            case "negative":
                i2 = R.string.gameshader_negative_des;
                break;
            case "cyberpunk":
                i2 = R.string.gameshader_cyberpunk_des;
                break;
            default:
                return "";
        }
        return GameAssistApplication.j().getString(i2);
    }

    public static String e(String str) {
        int i2;
        str.hashCode();
        switch (str) {
            case "darklight":
                i2 = R.string.gameshader_darklight;
                break;
            case "blackwhite":
                i2 = R.string.gameshader_blackwhite;
                break;
            case "oldfilm":
                i2 = R.string.gameshader_oldfilm;
                break;
            case "oldtime":
                i2 = R.string.gameshader_oldtime;
                break;
            case "crayon":
                i2 = R.string.gameshader_crayon;
                break;
            case "clearness":
                i2 = R.string.gameshader_clearness;
                break;
            case "eagleeye":
                i2 = R.string.gameshader_eagleeye;
                break;
            case "normal":
                i2 = R.string.gameshader_normal;
                break;
            case "sketch":
                i2 = R.string.gameshader_sketch;
                break;
            case "picturestory":
                i2 = R.string.gameshader_picturestory;
                break;
            case "scopegun":
                i2 = R.string.gameshader_scopegun;
                break;
            case "oil":
                i2 = R.string.gameshader_oil;
                break;
            case "gray":
                i2 = R.string.gameshader_gray;
                break;
            case "lomo":
                i2 = R.string.gameshader_lomo;
                break;
            case "snow":
                i2 = R.string.gameshader_snow;
                break;
            case "test":
                i2 = R.string.gameshader_normal;
                break;
            case "night":
                i2 = R.string.gameshader_night;
                break;
            case "negative":
                i2 = R.string.gameshader_negative;
                break;
            case "cyberpunk":
                i2 = R.string.gameshader_cyberpunk;
                break;
            default:
                i2 = R.string.gameshader_normal;
                break;
        }
        return GameAssistApplication.j().getString(i2);
    }

    public static String f(String str) {
        int i2;
        str.hashCode();
        switch (str) {
            case "darklight":
                i2 = R.string.gameshader_single_darklight;
                break;
            case "blackwhite":
                i2 = R.string.gameshader_single_blackwhite;
                break;
            case "oldfilm":
                i2 = R.string.gameshader_single_oldfilm;
                break;
            case "oldtime":
                i2 = R.string.gameshader_single_oldtime;
                break;
            case "crayon":
                i2 = R.string.gameshader_single_crayon;
                break;
            case "clearness":
                i2 = R.string.gameshader_single_clearness;
                break;
            case "eagleeye":
                i2 = R.string.gameshader_single_eagleeye;
                break;
            case "normal":
                i2 = R.string.gameshader_single_normal;
                break;
            case "sketch":
                i2 = R.string.gameshader_single_sketch;
                break;
            case "picturestory":
                i2 = R.string.gameshader_single_picturestory;
                break;
            case "scopegun":
                i2 = R.string.gameshader_single_scopegun;
                break;
            case "oil":
                i2 = R.string.gameshader_single_oil;
                break;
            case "gray":
                i2 = R.string.gameshader_single_gray;
                break;
            case "lomo":
                i2 = R.string.gameshader_single_lomo;
                break;
            case "snow":
                i2 = R.string.gameshader_single_snow;
                break;
            case "test":
                i2 = R.string.gameshader_single_normal;
                break;
            case "night":
                i2 = R.string.gameshader_single_night;
                break;
            case "negative":
                i2 = R.string.gameshader_single_negative;
                break;
            case "cyberpunk":
                i2 = R.string.gameshader_single_cyberpunk;
                break;
            default:
                i2 = R.string.gameshader_single_normal;
                break;
        }
        return GameAssistApplication.j().getString(i2);
    }

    private static void g(String str, int i2) {
        GaLog.a("GameShaderMgr", "invokeSurfaceFlingerShader packageName=" + str + " type=0x" + Integer.toHexString(i2));
        String A = SystemMgr.A(str);
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_key_code", 6001);
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, A + "");
        bundle.putInt(AbsGameAssistToken.BUNDLE_KEY_TYPE, i2);
        bundle.putInt(AbsGameAssistToken.BUNDLE_KEY_VALUE, 0);
        try {
            GameAssistControllerWrapper.invake("set_surfaceflinger", bundle, f8514a);
        } catch (Exception e2) {
            GaLog.b("GameShaderMgr", "Could not setSurfaceFlingerShader" + e2.toString());
        }
    }

    public static boolean h(int i2) {
        return i2 == 2 || i2 == 9;
    }

    public static void i(String str, boolean z) {
        j(str, 128, z);
    }

    private static void j(String str, int i2, boolean z) {
        GaLog.a("GameShaderMgr", "setEffect packageName=" + str + " effect=" + c(i2) + " enable=" + z);
        int intValue = f8515b.containsKey(str) ? ((Integer) f8515b.get(str)).intValue() : 0;
        int i3 = z ? i2 | intValue : (~i2) & intValue;
        f8515b.put(str, Integer.valueOf(i3));
        g(str, i3);
    }

    public static void k(String str, boolean z) {
        j(str, 64, z);
    }

    public static void l(String str, boolean z) {
        j(str, 32, z);
    }

    public static void m(String str) {
        GaLog.a("GameShaderMgr", "setProp name=debug.fixedlook.value type=" + str);
        Bundle bundle = new Bundle();
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, "debug.fixedlook.value");
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_VALUE, str);
        try {
            GameAssistControllerWrapper.invake("set_prop", bundle, f8514a);
        } catch (Exception e2) {
            GaLog.b("GameShaderMgr", "Could not setProp" + e2.toString());
        }
    }

    public static void n(String str, int i2) {
        GaLog.a("GameShaderMgr", "setSurfaceFlingerShader packageName=" + str + " type=" + c(i2));
        int intValue = f8515b.containsKey(str) ? ((Integer) f8515b.get(str)).intValue() : 0;
        int i3 = i2 > 0 ? i2 | (intValue & (-32)) : intValue & (-32);
        f8515b.put(str, Integer.valueOf(i3));
        g(str, i3);
    }
}
