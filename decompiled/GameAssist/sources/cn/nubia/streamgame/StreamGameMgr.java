package cn.nubia.streamgame;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.panel.TouchHelper;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.input.EventDispatcher;
import com.zte.gameassist.input.InterfaceEventListener;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class StreamGameMgr implements TouchHelper.OnTouchHelperCallback {

    /* renamed from: n, reason: collision with root package name */
    private static final String f9211n = "StreamGameMgr";

    /* renamed from: o, reason: collision with root package name */
    private static volatile StreamGameMgr f9212o;

    /* renamed from: h, reason: collision with root package name */
    private final Context f9214h;

    /* renamed from: i, reason: collision with root package name */
    private StreamGameTouchHelper f9215i;

    /* renamed from: j, reason: collision with root package name */
    private EventDispatcher f9216j;

    /* renamed from: c, reason: collision with root package name */
    private final Handler f9213c = new Handler(Looper.getMainLooper());

    /* renamed from: k, reason: collision with root package name */
    public boolean f9217k = false;

    /* renamed from: l, reason: collision with root package name */
    private final EventListener f9218l = new EventListener(this) { // from class: cn.nubia.streamgame.StreamGameMgr.1
        @Override // com.zte.gameassist.common.EventListener
        public void a(int i2, Object... objArr) {
        }
    };

    /* renamed from: m, reason: collision with root package name */
    private ContentObserver f9219m = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: cn.nubia.streamgame.StreamGameMgr.2
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (Settings.Global.getUriFor("streamgame_game_scene").equals(uri)) {
                try {
                    StreamGameMgr.this.g();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    };

    private StreamGameMgr() {
        GameAssistApplication j2 = GameAssistApplication.j();
        this.f9214h = j2;
        j2.getContentResolver().registerContentObserver(Settings.Global.getUriFor("streamgame_game_scene"), false, this.f9219m);
        GaLog.a(f9211n, f9211n);
    }

    public static StreamGameMgr e() {
        if (f9212o == null) {
            synchronized (StreamGameMgr.class) {
                try {
                    if (f9212o == null) {
                        f9212o = new StreamGameMgr();
                    }
                } finally {
                }
            }
        }
        return f9212o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        GaLog.a(f9211n, "onStreamGameChange");
        f();
        if (TextUtils.isEmpty(Settings.Global.getString(this.f9214h.getContentResolver(), "streamgame_game_scene"))) {
            h(this.f9215i);
            this.f9216j.m();
        } else {
            this.f9216j.j(this.f9214h, "streamgame");
            c(this.f9215i);
        }
    }

    @Override // cn.nubia.gameassist.panel.TouchHelper.OnTouchHelperCallback
    public void a() {
    }

    public void c(InterfaceEventListener interfaceEventListener) {
        this.f9216j.d(interfaceEventListener);
    }

    @Override // cn.nubia.gameassist.panel.TouchHelper.OnTouchHelperCallback
    public void d() {
        boolean z = !this.f9215i.r();
        GaLog.a(f9211n, "onTouchSlideIn slideFromRight=" + z);
        if (z) {
            Settings.Global.putString(this.f9214h.getContentResolver(), "streamgame_game_edge_slide", "" + System.currentTimeMillis());
        }
    }

    public void f() {
        if (this.f9217k) {
            return;
        }
        this.f9217k = true;
        this.f9216j = new EventDispatcher();
        EventListenerMgr.b(this.f9218l, 2);
        StreamGameTouchHelper streamGameTouchHelper = new StreamGameTouchHelper(this.f9214h);
        this.f9215i = streamGameTouchHelper;
        streamGameTouchHelper.v(this);
        GaLog.a(f9211n, "init ");
    }

    public void h(InterfaceEventListener interfaceEventListener) {
        this.f9216j.k(interfaceEventListener);
    }

    @Override // cn.nubia.gameassist.panel.TouchHelper.OnTouchHelperCallback
    public void pilferPointers() {
        EventDispatcher eventDispatcher = this.f9216j;
        if (eventDispatcher != null) {
            eventDispatcher.i();
        }
    }
}
