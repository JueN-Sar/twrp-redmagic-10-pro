package cn.nubia.gameassist.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import cn.nubia.gameassist.R;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.GameKeysHelperWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SearchWindowManager implements View.OnClickListener, ObserverManager.SettingCallback, GameMonitor.Callback {
    public static boolean A = false;
    private static volatile SearchWindowManager x = null;
    private static String y = "search_enabled_pkg";
    private static String z = "search_pkg";

    /* renamed from: c, reason: collision with root package name */
    private Context f7406c;

    /* renamed from: h, reason: collision with root package name */
    private WindowManager f7407h;

    /* renamed from: i, reason: collision with root package name */
    private InputMethodManager f7408i;

    /* renamed from: j, reason: collision with root package name */
    private WindowManager.LayoutParams f7409j;

    /* renamed from: k, reason: collision with root package name */
    private View f7410k;

    /* renamed from: l, reason: collision with root package name */
    private RelativeLayout f7411l;

    /* renamed from: m, reason: collision with root package name */
    private RelativeLayout f7412m;

    /* renamed from: n, reason: collision with root package name */
    private Button f7413n;

    /* renamed from: o, reason: collision with root package name */
    private Button f7414o;

    /* renamed from: p, reason: collision with root package name */
    private EditText f7415p;
    private int u;

    /* renamed from: q, reason: collision with root package name */
    private boolean f7416q = false;

    /* renamed from: r, reason: collision with root package name */
    private Handler f7417r = new Handler(Looper.getMainLooper());

    /* renamed from: s, reason: collision with root package name */
    private boolean f7418s = false;
    private boolean t = false;
    private final int v = 1794;
    private BroadcastReceiver w = new BroadcastReceiver() { // from class: cn.nubia.gameassist.search.SearchWindowManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            GaLog.j("SearchWindowManager", action);
            if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                SearchWindowManager.this.f(intent.getStringExtra("reason"));
            }
        }
    };

    private SearchWindowManager(Context context) {
        this.f7406c = context;
        this.f7407h = (WindowManager) context.getSystemService(WindowManager.class);
        this.f7408i = (InputMethodManager) this.f7406c.getSystemService("input_method");
        SystemMgr.y(this.f7406c).h(this);
    }

    private void e() {
        if (this.f7416q) {
            return;
        }
        try {
            GaLog.a("SearchWindowManager", "addViewToWindow: ");
            this.f7416q = true;
            this.f7407h.addView(this.f7410k, this.f7409j);
        } catch (Exception e2) {
            Log.e("SearchWindowManager", "addViewToWindow Exception!", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void p(String str) {
        if (!this.f7416q || this.f7410k == null || this.f7408i == null) {
            return;
        }
        GaLog.k("SearchWindowManager", "dismissSearchWindow() reason =" + str);
        this.f7410k.setVisibility(8);
        try {
            this.f7408i.hideSoftInputFromWindow(this.f7413n.getWindowToken(), 0);
        } catch (Exception e2) {
            GaLog.c("SearchWindowManager", "hideSoftInputFromWindow", e2);
        }
        x();
    }

    public static SearchWindowManager i(Context context) {
        if (x == null) {
            synchronized (SearchWindowManager.class) {
                try {
                    if (x == null) {
                        x = new SearchWindowManager(context);
                    }
                } finally {
                }
            }
        }
        return x;
    }

    private void l() {
        if (this.f7418s) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        this.f7406c.registerReceiver(this.w, intentFilter, 2);
        ObserverManager.c().b(this.f7406c, Settings.System.getUriFor("keyguard_is_showing"), this);
        ObserverManager.c().b(this.f7406c, Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS), this);
        this.f7418s = true;
    }

    private void m() {
        if (this.f7410k == null) {
            View f2 = InflaterHelper.f(R.layout.layout_search_view, null);
            this.f7410k = f2;
            this.f7411l = (RelativeLayout) f2.findViewById(R.id.search_view_hor_root_layout);
            this.f7412m = (RelativeLayout) this.f7410k.findViewById(R.id.search_view_ver_root_layout);
        }
        if (!this.t ? RotationMgr.j() : this.u == 2) {
            this.f7412m.setVisibility(0);
            this.f7411l.setVisibility(8);
            this.f7413n = (Button) this.f7412m.findViewById(R.id.search_down_port);
            this.f7414o = (Button) this.f7412m.findViewById(R.id.search_view_cancel_port);
            this.f7415p = (EditText) this.f7412m.findViewById(R.id.search_input_port);
        } else {
            this.f7412m.setVisibility(8);
            this.f7411l.setVisibility(0);
            this.f7413n = (Button) this.f7411l.findViewById(R.id.search_down);
            this.f7414o = (Button) this.f7411l.findViewById(R.id.search_view_cancel);
            this.f7415p = (EditText) this.f7411l.findViewById(R.id.search_input);
        }
        this.f7413n.setOnClickListener(this);
        this.f7414o.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q() {
        f("keyguard is showing");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r() {
        f("gamekey");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s() {
        this.f7408i.showSoftInput(this.f7415p, 2);
    }

    private void v() {
        this.f7411l = null;
        this.f7412m = null;
        this.f7410k = null;
        this.f7413n = null;
        this.f7414o = null;
        this.f7415p = null;
    }

    private void x() {
        if (this.f7416q) {
            try {
                GaLog.a("SearchWindowManager", "removeViewFromWindow");
                this.f7416q = false;
                this.f7407h.removeView(this.f7410k);
            } catch (Exception e2) {
                Log.e("SearchWindowManager", "removeViewFromWindow Exception!", e2);
            }
        }
    }

    public void f(final String str) {
        if (this.f7416q) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                p(str);
            } else {
                this.f7417r.post(new Runnable() { // from class: cn.nubia.gameassist.search.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        SearchWindowManager.this.p(str);
                    }
                });
            }
        }
    }

    public void h(PrintWriter printWriter, String str) {
        printWriter.println(str + "  SearchWindowManager:");
        printWriter.println(str + "  mAdded:" + this.f7416q);
        printWriter.println(str + "  mSearchRootView:" + this.f7410k);
        printWriter.println(str + "  mInputMethodManager:" + this.f7408i);
        printWriter.println(str + "  mGameKeysWrapper:" + GameKeysWrapper.b());
        printWriter.println(str + "  mLegacyGameSpaceOpen:" + A);
    }

    public String j() {
        return this.f7406c.getSharedPreferences(y, 0).getString(z, null);
    }

    public void k() {
        GaLog.a("SearchWindowManager", "init");
        m();
        n();
        l();
    }

    public void n() {
        if (this.f7409j == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.f7409j = layoutParams;
            layoutParams.setTitle("GameAssistSearchWindow");
            WindowManager.LayoutParams layoutParams2 = this.f7409j;
            layoutParams2.layoutInDisplayCutoutMode = 2;
            layoutParams2.format = -2;
            layoutParams2.type = 2038;
            layoutParams2.flags = 209782528;
            layoutParams2.windowAnimations = -1;
            WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams2);
        }
    }

    public boolean o(String str, String str2) {
        String j2 = j();
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(j2)) {
            arrayList.add(str);
            String join = String.join(str2, arrayList);
            GaLog.a("SearchWindowManager", "isNewAppEnable: join = " + join);
            u(join);
            return true;
        }
        for (String str3 : j2.split(str2)) {
            if (!str3.isEmpty()) {
                arrayList.add(str3);
            }
        }
        if (arrayList.contains(str)) {
            return false;
        }
        GaLog.a("SearchWindowManager", "isNewAppEnable: add packageName = " + str);
        arrayList.add(str);
        String join2 = String.join(str2, arrayList);
        GaLog.a("SearchWindowManager", "isNewAppEnable: join = " + join2);
        u(join2);
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.search_down || id == R.id.search_down_port) {
            SearchManager.b().d(this.f7415p.getText().toString());
            this.f7415p.setText("");
            f("search");
        } else if (id == R.id.search_view_cancel || id == R.id.search_view_cancel_port) {
            f("cancelSearch");
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onGameStart() {
        GaLog.a("SearchWindowManager", "onGameStart: ");
        k();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onGameStop() {
        GaLog.a("SearchWindowManager", "onGameStop:");
        f("Game Stop");
        z();
        v();
    }

    public void t(Configuration configuration) {
        int i2 = configuration.orientation;
        if (this.u != i2) {
            this.u = i2;
            this.t = true;
            GaLog.a("SearchWindowManager", "onOrientationChanged: mOrientation = " + this.u);
            if (SystemMgr.H()) {
                m();
            }
        }
    }

    public void u(String str) {
        SharedPreferences.Editor edit = this.f7406c.getSharedPreferences(y, 0).edit();
        edit.putString(z, str);
        edit.apply();
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z2, Uri uri) {
        if (Settings.System.getUriFor("keyguard_is_showing").equals(uri)) {
            if (Settings.System.getInt(this.f7406c.getContentResolver(), "keyguard_is_showing", 0) == 1) {
                this.f7417r.post(new Runnable() { // from class: cn.nubia.gameassist.search.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        SearchWindowManager.this.q();
                    }
                });
            }
        } else if (Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS).equals(uri)) {
            boolean z3 = (GameKeysWrapper.b().c(this.f7406c) & 1) != 0;
            if (z3 != A) {
                A = z3;
                this.f7417r.post(new Runnable() { // from class: cn.nubia.gameassist.search.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        SearchWindowManager.this.r();
                    }
                });
            }
        }
    }

    public void y(String str) {
        GaLog.a("SearchWindowManager", "showSearchWindow() reason =" + str);
        if (o(SystemMgr.v(), ",")) {
            this.f7415p.setText("");
        }
        e();
        this.f7410k.setVisibility(0);
        this.f7410k.setSystemUiVisibility(1794);
        this.f7415p.setFocusable(true);
        this.f7415p.requestFocus();
        this.f7417r.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.search.d
            @Override // java.lang.Runnable
            public final void run() {
                SearchWindowManager.this.s();
            }
        }, 300L);
    }

    public void z() {
        if (this.f7418s) {
            this.f7406c.unregisterReceiver(this.w);
            ObserverManager.c().d(this.f7406c, Settings.System.getUriFor("keyguard_is_showing"), this);
            ObserverManager.c().d(this.f7406c, Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS), this);
            this.f7418s = false;
        }
    }
}
