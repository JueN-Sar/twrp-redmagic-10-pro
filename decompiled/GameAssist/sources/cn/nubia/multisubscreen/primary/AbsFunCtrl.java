package cn.nubia.multisubscreen.primary;

import android.content.Context;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class AbsFunCtrl extends AbsCtrl implements GameMonitor.Callback {

    /* renamed from: j, reason: collision with root package name */
    private String f7940j;

    /* renamed from: k, reason: collision with root package name */
    private ChangeListener f7941k;

    /* renamed from: l, reason: collision with root package name */
    protected long f7942l;

    /* renamed from: m, reason: collision with root package name */
    protected String f7943m;

    public interface ChangeListener {
        void a(String str, String str2);
    }

    public AbsFunCtrl(Context context, String str) {
        super(context);
        this.f7942l = -1L;
        this.f7940j = str;
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    protected void b() {
        r(null);
        this.f7942l = -1L;
    }

    public String k() {
        return this.f7940j;
    }

    public String l() {
        return this.f7943m;
    }

    public boolean m(JSONObject jSONObject) {
        String optString = jSONObject.optString(k());
        if (optString == null) {
            return false;
        }
        return o(optString);
    }

    public void n(String str) {
        GaLog.b("MultiSubScreen_PrimaryData", "notifyChange value = " + str);
        ChangeListener changeListener = this.f7941k;
        if (changeListener != null) {
            changeListener.a(k(), str);
        }
    }

    public abstract boolean o(String str);

    public void p(ChangeListener changeListener) {
        this.f7941k = changeListener;
    }

    public boolean q(String str) {
        if (str.equals(this.f7943m)) {
            return true;
        }
        this.f7943m = str;
        if (!d()) {
            return true;
        }
        n(str);
        return true;
    }

    public boolean r(String str) {
        this.f7943m = str;
        return true;
    }
}
