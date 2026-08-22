package cn.nubia.plugin.gameratio;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameRatioDataMgr {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8351a;

    /* renamed from: b, reason: collision with root package name */
    private Context f8352b;

    public GameRatioDataMgr(Context context) {
        this.f8352b = context;
    }

    public static String B(String str) {
        str.hashCode();
        switch (str) {
            case "1.33":
                return "4:3";
            case "1.78":
                return "16:9";
            case "2.33":
                return "21:9";
            case "3.56":
                return "32:9";
            default:
                return "origin";
        }
    }

    private String e(String str) {
        return str + ":";
    }

    private String f(String str) {
        return str + ",";
    }

    private String h(String str, String str2) {
        String[] split;
        String[] split2 = str.split(",");
        if (split2 != null && split2.length != 0) {
            for (String str3 : split2) {
                if (!TextUtils.isEmpty(str3) && str3.contains(e(str2)) && (split = str3.split(":")) != null && split.length >= 2 && str2.equals(split[0])) {
                    return str3;
                }
            }
        }
        return null;
    }

    private boolean q(String str, String str2) {
        String[] split;
        if (!TextUtils.isEmpty(str) && (split = str.split("[,:\\s]+")) != null && split.length != 0) {
            for (String str3 : split) {
                if (str3.trim().equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String v(String str) {
        str.hashCode();
        return !str.equals("1") ? !str.equals("2") ? "system" : "portrait" : "landscape";
    }

    private void w(String str) {
        Settings.Global.putString(this.f8352b.getContentResolver(), "nubia_gameratio_enable_pkgs", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public void t(String str) {
        Settings.Global.putString(this.f8352b.getContentResolver(), "nubia_gameratio_ori_pkgs", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: y, reason: merged with bridge method [inline-methods] */
    public void u(String str) {
        Settings.Global.putString(this.f8352b.getContentResolver(), "nubia_gameratio_size_pkgs", str);
    }

    public void A(int i2) {
        Settings.Global.putInt(this.f8352b.getContentResolver(), "nubia_gameratio_state", i2);
    }

    public void C(String str, String str2, String str3, Consumer consumer) {
        String h2;
        if (TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            GaLog.e("GameRatio", "delete data " + str + " pkg " + str2);
            if (q(str, str2) && (h2 = h(str, str2)) != null) {
                consumer.accept(str.replace(f(h2), ""));
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            consumer.accept(str2 + ":" + str3 + ",");
            return;
        }
        GaLog.e("GameRatio", "add data " + str + " pkg " + str2);
        if (!q(str, str2)) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(str2 + ":" + str3 + ",");
            consumer.accept(sb.toString());
            return;
        }
        String h3 = h(str, str2);
        if (h3 != null) {
            StringBuilder sb2 = new StringBuilder(str.replace(f(h3), ""));
            sb2.append(str2 + ":" + str3 + ",");
            consumer.accept(sb2.toString());
            return;
        }
        StringBuilder sb3 = new StringBuilder(str);
        sb3.append(str2 + ":" + str3 + ",");
        consumer.accept(sb3.toString());
    }

    public void D(String str, String str2) {
        C(l(), str, str2, new Consumer() { // from class: cn.nubia.plugin.gameratio.d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GameRatioDataMgr.this.t((String) obj);
            }
        });
    }

    public void E(String str, String str2) {
        C(p(), str, str2, new Consumer() { // from class: cn.nubia.plugin.gameratio.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GameRatioDataMgr.this.u((String) obj);
            }
        });
    }

    public void c(String str) {
        GaLog.e("GameRatio", "disable " + str);
        String g2 = g();
        if (TextUtils.isEmpty(g2)) {
            return;
        }
        String f2 = f(str);
        if (g2.contains(f2)) {
            w(g2.replace(f2, ""));
        }
    }

    public void d(String str) {
        GaLog.e("GameRatio", "enable " + str);
        String g2 = g();
        String f2 = f(str);
        if (TextUtils.isEmpty(g2)) {
            w(f2);
        } else {
            if (g2.contains(f2)) {
                return;
            }
            w(g2 + f2);
        }
    }

    public String g() {
        String string = Settings.Global.getString(this.f8352b.getContentResolver(), "nubia_gameratio_enable_pkgs");
        return string == null ? "" : string;
    }

    public int i(String str) {
        str.hashCode();
        if (str.equals("1")) {
            return 1;
        }
        return !str.equals("2") ? 0 : 2;
    }

    public int j(String str) {
        str.hashCode();
        switch (str) {
            case "1.33":
                return 1;
            case "1.78":
                return 2;
            case "2.33":
                return 3;
            case "3.56":
                return 4;
            default:
                return 0;
        }
    }

    public String k(String str) {
        String h2;
        String[] split;
        String l2 = l();
        if (!TextUtils.isEmpty(l2) && (h2 = h(l2, str)) != null && (split = h2.split(":")) != null && split.length >= 2) {
            String str2 = split[1];
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
        }
        return "";
    }

    public String l() {
        String string = Settings.Global.getString(this.f8352b.getContentResolver(), "nubia_gameratio_ori_pkgs");
        return string == null ? "" : string;
    }

    public String m(int i2) {
        return i2 != 1 ? i2 != 2 ? "" : "2" : "1";
    }

    public String n(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "3.56" : "2.33" : "1.78" : "1.33";
    }

    public String o(String str) {
        String h2;
        String[] split;
        String p2 = p();
        if (!TextUtils.isEmpty(p2) && (h2 = h(p2, str)) != null && (split = h2.split(":")) != null && split.length >= 2) {
            String str2 = split[1];
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
        }
        return "";
    }

    public String p() {
        String string = Settings.Global.getString(this.f8352b.getContentResolver(), "nubia_gameratio_size_pkgs");
        return string == null ? "" : string;
    }

    public boolean r(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return g().contains(f(str));
    }

    public boolean s() {
        if (this.f8351a) {
            return false;
        }
        boolean z = this.f8352b.getSharedPreferences("nubia_gameratio", 0).getInt("has_shown", 0) == 0;
        if (!z) {
            this.f8351a = true;
        }
        return z;
    }

    public void z() {
        SharedPreferences.Editor edit = this.f8352b.getSharedPreferences("nubia_gameratio", 0).edit();
        edit.putInt("has_shown", 1);
        edit.apply();
        this.f8351a = true;
    }
}
