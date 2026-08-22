package cn.nubia.multisubscreen.primary;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.common.ThreadManager;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class FanModeCtrl extends AbsFunCtrl {

    /* renamed from: n, reason: collision with root package name */
    private ContentObserver f7950n;

    /* renamed from: o, reason: collision with root package name */
    private int f7951o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f7952p;

    public FanModeCtrl(Context context, String str) {
        super(context, str);
        this.f7951o = -1;
        this.f7950n = new ContentObserver(new Handler(ThreadManager.c().f())) { // from class: cn.nubia.multisubscreen.primary.FanModeCtrl.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if (!uri.getLastPathSegment().equals("fan_state_of_mode")) {
                    FanModeCtrl fanModeCtrl = FanModeCtrl.this;
                    fanModeCtrl.f7952p = fanModeCtrl.B();
                    return;
                }
                int z2 = FanModeCtrl.this.z();
                if (FanModeCtrl.this.f7951o != z2) {
                    FanModeCtrl.this.f7951o = z2;
                    FanModeCtrl fanModeCtrl2 = FanModeCtrl.this;
                    fanModeCtrl2.q(fanModeCtrl2.A(fanModeCtrl2.f7951o));
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String A(int i2) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        jSONArray.put(1);
        jSONArray.put(this.f7942l);
        return jSONArray.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean B() {
        return MultiSubScreenUtils.r(this.f7938h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int z() {
        return Settings.System.getInt(this.f7938h.getContentResolver(), "fan_state_of_mode", 1);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        int z = z();
        this.f7951o = z;
        r(A(z));
        this.f7938h.getContentResolver().registerContentObserver(Settings.System.getUriFor("fan_state_of_mode"), false, this.f7950n);
        this.f7938h.getContentResolver().registerContentObserver(Settings.System.getUriFor("fan_state_of_manual"), false, this.f7950n);
        this.f7952p = B();
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        this.f7938h.getContentResolver().unregisterContentObserver(this.f7950n);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean o(String str) {
        if (!this.f7952p) {
            n(this.f7943m);
            return false;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 2) {
                Settings.System.putInt(this.f7938h.getContentResolver(), "fan_state_of_mode", jSONArray.getInt(0));
                this.f7942l = jSONArray.getLong(1);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return true;
    }
}
