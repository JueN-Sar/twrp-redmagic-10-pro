package cn.nubia.multisubscreen.primary;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.ICompetitionLightProxy;
import com.zte.gameassist.common.IModuleProxy;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class CompetitionLightCtrl extends AbsFunCtrl implements IModuleProxy.ICallback<ICompetitionLightProxy> {

    /* renamed from: n, reason: collision with root package name */
    private ICompetitionLightProxy f7948n;

    /* renamed from: o, reason: collision with root package name */
    private int f7949o;

    public CompetitionLightCtrl(Context context, String str) {
        super(context, str);
    }

    private synchronized ICompetitionLightProxy s() {
        try {
            if (this.f7948n == null) {
                this.f7948n = (ICompetitionLightProxy) c().a(ICompetitionLightProxy.class);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f7948n;
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void f() {
        s().setListening(true, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsCtrl
    public void g() {
        s().setListening(false, this);
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl
    public boolean o(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 2) {
                if (jSONArray.getInt(0) != this.f7949o) {
                    s().d();
                }
                this.f7942l = jSONArray.getLong(1);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return true;
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: t, reason: merged with bridge method [inline-methods] */
    public void onChanged(ICompetitionLightProxy iCompetitionLightProxy) {
        this.f7949o = iCompetitionLightProxy.c() ? 1 : 0;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.f7949o);
        jSONArray.put(iCompetitionLightProxy.b() ? 1 : 0);
        jSONArray.put(this.f7942l);
        q(jSONArray.toString());
    }
}
