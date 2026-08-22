package cn.nubia.gameassist.dessert;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.common.QSState;
import cn.nubia.gameassist.utils.TilesUtil;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class TilesManager {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList f6238a;

    /* renamed from: b, reason: collision with root package name */
    private final List f6239b;

    private static class Holder {

        /* renamed from: a, reason: collision with root package name */
        private static final TilesManager f6240a = new TilesManager();
    }

    private class LoadTilesTask extends AsyncTask<Void, Void, Void> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            Iterator it = TilesUtil.g(TilesManager.this.i()).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                try {
                    TilesManager.this.e(str, false);
                } catch (Throwable th) {
                    th.printStackTrace();
                    GaLog.l("TilesManager", "loadDessertTilesInBackground() error , spec : " + str, th);
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r2) {
            super.onPostExecute(r2);
            GaLog.a("TilesManager", "onPostExecute()");
            TilesManager.this.n();
        }

        private LoadTilesTask() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, boolean z) {
        Iterator it = this.f6239b.iterator();
        while (it.hasNext()) {
            String str2 = ((TileInfo) it.next()).f6227a;
            if (str2 != null && str2.equals(str)) {
                return;
            }
        }
        this.f6239b.add(h(str, z));
    }

    private TileInfo h(String str, boolean z) {
        QSState qSState = new QSState(str, i());
        TileInfo tileInfo = new TileInfo();
        tileInfo.f6229c = qSState;
        tileInfo.f6227a = str;
        tileInfo.f6228b = qSState.f6147c;
        tileInfo.f6230d = z;
        return tileInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context i() {
        GameAssistApplication.j();
        return BaseApplication.a();
    }

    public static TilesManager j() {
        return Holder.f6240a;
    }

    private void l(List list, List list2) {
        int indexOf;
        if (!ZteFeature.isSupportLowSugar() || list.contains("low_sugar") || (indexOf = list2.indexOf("low_sugar")) <= 0) {
            return;
        }
        list.add(indexOf, "low_sugar");
    }

    private void m() {
        new LoadTilesTask().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        GaLog.a("TilesManager", "onTilesChanged() mTiles : " + this.f6239b.size());
        Iterator it = this.f6238a.iterator();
        while (it.hasNext()) {
            ((TileStateListener) it.next()).g(g());
        }
    }

    private void o(List list) {
        String g2 = SharedPreferencesUtil.k(i()).g();
        if (g2 != null) {
            l(list, TilesUtil.e(g2));
        }
        q(list);
        String join = TextUtils.join(",", list);
        SharedPreferencesUtil.k(i()).Q(join);
        GaLog.e("TilesManager", "save local specs : " + join);
    }

    private void q(List list) {
        if (FoldMgr.f() && FoldMgr.c().e()) {
            list.remove("game_custom");
        }
    }

    public void d(TileStateListener tileStateListener) {
        if (this.f6238a.contains(tileStateListener)) {
            return;
        }
        this.f6238a.add(tileStateListener);
    }

    public void f() {
        Bundle bundle = new Bundle();
        bundle.putString("package_name", "cn.nubia.gameassist");
        bundle.putString("action_type", "state");
        bundle.putString("action_value", this.f6239b.toString());
        bundle.putInt("report_interval", 1);
        NubiaTrackManager.p().x("cn.nubia.gameassist", "game_control_center_order", bundle);
        GaLog.e("TilesManager", "buryPoint:" + this.f6239b.toString());
    }

    public List g() {
        return new ArrayList(this.f6239b);
    }

    public List k() {
        return this.f6239b;
    }

    public void p() {
        GaLog.a("TilesManager", "reload tiles");
        this.f6239b.clear();
        m();
    }

    public void r(TileStateListener tileStateListener) {
        this.f6238a.remove(tileStateListener);
    }

    public void s(List list) {
        String str;
        this.f6239b.clear();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TileInfo tileInfo = (TileInfo) it.next();
            if (tileInfo != null && (str = tileInfo.f6227a) != null && !"null".equals(str)) {
                arrayList.add(tileInfo.f6227a);
                this.f6239b.add(tileInfo);
            }
        }
        arrayList.add("game_custom");
        o(arrayList);
    }

    private TilesManager() {
        this.f6238a = new ArrayList();
        this.f6239b = new ArrayList();
    }
}
