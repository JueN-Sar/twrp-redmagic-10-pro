package cn.nubia.gameassist.dessert.tiles;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameReminderComService;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GameReminderTiles extends QSTile {
    public static final Uri z = Uri.parse("content://com.zte.plugin.reminder");
    private boolean v;
    private final ReminderObserver w;
    private final Handler x;
    private int y;

    /* renamed from: cn.nubia.gameassist.dessert.tiles.GameReminderTiles$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ GameReminderTiles f6475c;

        @Override // java.lang.Runnable
        public void run() {
            GameReminderComService gameReminderComService = (GameReminderComService) Router.getInstance().getService(GameReminderComService.class.getSimpleName());
            if (gameReminderComService != null) {
                gameReminderComService.removeView(((QSTile) this.f6475c).f6153i);
            }
        }
    }

    class ReminderObserver extends ContentObserver {
        public ReminderObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            GameReminderTiles.this.F0();
        }
    }

    public GameReminderTiles(QSTile.Host host) {
        super(host);
        this.x = new Handler(Looper.getMainLooper());
        this.y = 0;
        this.w = new ReminderObserver(new Handler(ThreadManager.c().b()));
    }

    private void D0(final String str, String str2) {
        this.x.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.GameReminderTiles.1
            @Override // java.lang.Runnable
            public void run() {
                GameReminderComService gameReminderComService = (GameReminderComService) Router.getInstance().getService(GameReminderComService.class.getSimpleName());
                if (gameReminderComService != null) {
                    gameReminderComService.setGameReminder(((QSTile) GameReminderTiles.this).f6153i, str, SystemMgr.t(), 0L, 0);
                }
            }
        });
    }

    private void E0() {
        this.x.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.GameReminderTiles.2
            @Override // java.lang.Runnable
            public void run() {
                GameReminderComService gameReminderComService = (GameReminderComService) Router.getInstance().getService(GameReminderComService.class.getSimpleName());
                if (gameReminderComService != null) {
                    gameReminderComService.showView(((QSTile) GameReminderTiles.this).f6153i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0() {
        int count;
        Cursor query = this.f6153i.getContentResolver().query(z, null, "time>?", new String[]{Long.toString(System.currentTimeMillis())}, null);
        if (query == null) {
            count = 0;
        } else {
            try {
                count = query.getCount();
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        this.y = count;
        GaLog.a("GameReminderTiles", "startQuery: " + this.y);
        this.x.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.GameReminderTiles.4
            @Override // java.lang.Runnable
            public void run() {
                GameReminderTiles.this.o0();
            }
        });
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.f6152h.b();
        E0();
        return super.S();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z2) {
        if (this.v == z2) {
            return;
        }
        this.v = z2;
        GaLog.a("GameReminderTiles", "setListening: " + z2);
        if (!this.v) {
            this.f6153i.getContentResolver().unregisterContentObserver(this.w);
            return;
        }
        ContentResolver contentResolver = this.f6153i.getContentResolver();
        Uri uri = z;
        contentResolver.registerContentObserver(uri, false, this.w);
        this.w.onChange(true, uri);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        GaLog.a("GameReminderTiles", "handleUpdateState: " + this.y);
        if (this.y > 0) {
            state.f6175i = true;
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_reminder_light);
        } else {
            state.f6175i = false;
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_reminder_normal);
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_game_reminder);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        GaLog.a("GameReminderTiles", "onAICommnadNotify cmd = " + str);
        if (!SystemMgr.H()) {
            GaLog.a("GameReminderTiles", "onAICommnadNotify not in game scene!");
            return;
        }
        if ("game_turn_on_game_reminder".equals(str)) {
            E0();
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_game_reminder);
            return;
        }
        if ("game_turn_off_game_reminder".equals(str)) {
            GameAgentUtil.n(this.f6153i, iGameAssistClientCallback, inMsg);
            return;
        }
        if ("set_game_alarm".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(inMsg.h().a());
                String optString = jSONObject.optString("action");
                String optString2 = jSONObject.optString("DateTime");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                    D0(optString, optString2);
                    GameAgentUtil.k(this.f6153i, iGameAssistClientCallback, inMsg);
                    return;
                }
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
            } catch (JSONException e2) {
                e2.printStackTrace();
                GaLog.b("GameReminderTiles", "onAICommnadNotify has exception and e = " + e2.toString());
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
            }
        }
    }
}
