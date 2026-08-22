package com.zte.gameassist.aiagent;

import android.app.Service;
import android.content.Intent;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.zte.gameassist.aiagent.GameAgentService;
import com.zte.gameassist.aiagent.IGameAgent;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.aiagent.bean.OutMsg;
import com.zte.gameassist.aiagent.policy.AICommandDispatcher;
import com.zte.gameassist.aiagent.policy.CommandParser;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GameAgentService extends Service {

    /* renamed from: h, reason: collision with root package name */
    private IGameAssistClientCallback f16381h;

    /* renamed from: i, reason: collision with root package name */
    private Handler f16382i;

    /* renamed from: c, reason: collision with root package name */
    private final Map f16380c = new HashMap();

    /* renamed from: j, reason: collision with root package name */
    private int f16383j = 1;

    /* renamed from: k, reason: collision with root package name */
    private IBinder.DeathRecipient f16384k = new IBinder.DeathRecipient() { // from class: com.zte.gameassist.aiagent.a
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            GameAgentService.this.q();
        }
    };

    /* renamed from: l, reason: collision with root package name */
    private final IGameAgent.Stub f16385l = new IGameAgent.Stub() { // from class: com.zte.gameassist.aiagent.GameAgentService.1
        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void onWindowStateChanged(int i2) {
            synchronized (this) {
                GameAgentService.this.f16383j = i2;
            }
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void registerCallback(String str, IGameAssistClientCallback iGameAssistClientCallback) {
            GaLog.a("GameAgentService", "registerCallback " + str + " " + iGameAssistClientCallback);
            if (str == null || iGameAssistClientCallback == null) {
                return;
            }
            synchronized (this) {
                try {
                    if ("cn.nubia.redmagickyi".equals(str)) {
                        GameAgentService.this.f16381h = iGameAssistClientCallback;
                        GameAgentService.this.f16381h.asBinder().linkToDeath(GameAgentService.this.f16384k, 0);
                    } else {
                        GameAgentService.this.f16380c.put(str, iGameAssistClientCallback);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void sendMessage(String str, String str2) {
            if (str == null || str2 == null) {
                GaLog.a("GameAgentService", "sendMessage " + str + " " + str2);
                return;
            }
            if ("cn.nubia.redmagickyi".equals(str)) {
                GameAgentService.this.u(str2);
                return;
            }
            GaLog.a("GameAgentService", "sendMessage " + str + " " + str2);
            GameAgentService.this.t(str, str2);
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void sendTextMessage(String str, String str2) {
            GaLog.a("GameAgentService", "sendTextMessage " + str + " " + str2);
            if (str == null || str2 == null || "cn.nubia.redmagickyi".equals(str)) {
                return;
            }
            GameAgentService.this.t(str, new OutMsg(str2).toString());
        }

        @Override // com.zte.gameassist.aiagent.IGameAgent
        public void unregisterCallback(String str, IGameAssistClientCallback iGameAssistClientCallback) {
            GaLog.a("GameAgentService", "unregisterCallback " + str + " " + iGameAssistClientCallback);
            if (str == null || iGameAssistClientCallback == null) {
                return;
            }
            synchronized (this) {
                try {
                    if ("cn.nubia.redmagickyi".equals(str)) {
                        GameAgentService.this.f16381h.asBinder().unlinkToDeath(GameAgentService.this.f16384k, 0);
                        GameAgentService.this.f16381h = null;
                        GameAgentService.this.f16383j = 1;
                    } else {
                        GameAgentService.this.f16380c.remove(str);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    };

    /* renamed from: m, reason: collision with root package name */
    private IGameAssistClientCallback.Stub f16386m = new AnonymousClass2();

    /* renamed from: n, reason: collision with root package name */
    private GamePluginClientCallback f16387n = new GamePluginClientCallback() { // from class: com.zte.gameassist.aiagent.GameAgentService.3
        @Override // com.zte.gameassist.aiagent.GamePluginClientCallback
        public void a(String str, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
            if (inMsg.j() == 1) {
                GameAgentService.this.n(str, iGameAssistClientCallback, inMsg);
            }
        }
    };

    /* renamed from: com.zte.gameassist.aiagent.GameAgentService$2, reason: invalid class name */
    class AnonymousClass2 extends IGameAssistClientCallback.Stub {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceivedCallback$0(int i2, String str) {
            GameAgentService.this.s(i2, str);
        }

        @Override // com.zte.gameassist.aiagent.IGameAssistClientCallback
        public void onReceivedCallback(final int i2, final String str) {
            if (Looper.getMainLooper().isCurrentThread()) {
                GameAgentService.this.s(i2, str);
            } else {
                GameAgentService.this.f16382i.post(new Runnable() { // from class: com.zte.gameassist.aiagent.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        GameAgentService.AnonymousClass2.this.lambda$onReceivedCallback$0(i2, str);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(String str, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        IGameAssistClientCallback iGameAssistClientCallback2;
        synchronized (this) {
            iGameAssistClientCallback2 = (IGameAssistClientCallback) this.f16380c.get(str);
        }
        if (iGameAssistClientCallback2 != null) {
            try {
                iGameAssistClientCallback2.onReceivedCallback(1, inMsg.toString());
                return;
            } catch (RemoteException e2) {
                GaLog.c("GameAgentService", "client callback error", e2);
                GameAgentUtil.m(this, iGameAssistClientCallback, inMsg);
                return;
            }
        }
        GaLog.b("GameAgentService", "client callback not found for " + inMsg.g());
        GameAgentUtil.m(this, iGameAssistClientCallback, inMsg);
    }

    private IGameAssistClientCallback o(String str) {
        return "cn.nubia.redmagickyi".equals(str) ? this.f16381h : (IGameAssistClientCallback) this.f16380c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(InMsg inMsg) {
        GameAgentUtil.g(this, this.f16386m, inMsg, "monitor reply");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q() {
        IGameAssistClientCallback iGameAssistClientCallback = this.f16381h;
        if (iGameAssistClientCallback != null) {
            iGameAssistClientCallback.asBinder().unlinkToDeath(this.f16384k, 0);
            this.f16381h = null;
            this.f16383j = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(String str) {
        try {
            InMsg a2 = CommandParser.a(str);
            if (a2.j() != 0) {
                GaLog.e("GameAgentService", "send other message " + a2.g() + ", " + a2.a());
            } else {
                if (!ZteFeature.isSupportGameVoiceAssist()) {
                    v(new OutMsg(a2.c(), 1, "", "").toString());
                    return;
                }
                Message obtain = Message.obtain();
                obtain.obj = a2;
                obtain.what = 0;
                this.f16382i.removeMessages(0);
                this.f16382i.sendMessageDelayed(obtain, 5000L);
                GameAgentUtil.t(this, a2);
                String d2 = a2.d();
                if (GaLog.f17034b) {
                    GaLog.a("GameAgentService", "send voice message " + str);
                } else if (TextUtils.isEmpty(d2)) {
                    GaLog.k("GameAgentService", "send voice message " + d2 + ", " + a2.e());
                } else {
                    int length = d2.length();
                    StringBuilder sb = new StringBuilder();
                    sb.append("send voice message ");
                    if (length >= 10) {
                        length = 10;
                    }
                    sb.append(d2.substring(0, length));
                    sb.append(", intent ");
                    sb.append(a2.e());
                    GaLog.e("GameAgentService", sb.toString());
                }
            }
            AICommandDispatcher.e(getApplicationContext()).b(this.f16386m, this.f16387n, a2);
        } catch (Exception e2) {
            GaLog.f("GameAgentService", "sendMessageFromVoiceClient error", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(int i2, String str) {
        JSONObject jSONObject;
        GaLog.e("GameAgentService", "onReceivedCallback " + str);
        if (this.f16382i.hasMessages(0)) {
            this.f16382i.removeMessages(0);
        } else {
            GaLog.e("GameAgentService", "reply message timeout");
        }
        if (i2 == 0) {
            GameAgentUtil.p(this, new OutMsg(str).toString());
            return;
        }
        if (i2 == 1) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (3 == jSONObject.optInt("output_type")) {
                GaLog.e("GameAgentService", "receive type to quit voice client");
                GameAgentUtil.q(this);
                return;
            } else {
                if (TextUtils.isEmpty(jSONObject.optString("ID"))) {
                    GameAgentUtil.p(this, str);
                    return;
                }
                v(str);
                return;
            }
        }
        if (i2 != 2) {
            if (i2 == 3) {
                GaLog.e("GameAgentService", "receive cmd to quit voice client");
                GameAgentUtil.q(this);
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            if (TextUtils.isEmpty(jSONObject2.optString("confirm_content"))) {
                jSONObject2.put("confirm_content", 1);
                str = jSONObject2.toString();
            }
            if (TextUtils.isEmpty(jSONObject2.optString("ID"))) {
                GameAgentUtil.p(this, str);
                return;
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        v(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (3 == jSONObject.optInt("output_type")) {
                GaLog.e("GameAgentService", "quit voice client from " + str);
                GameAgentUtil.q(this);
                return;
            }
            if (TextUtils.isEmpty(jSONObject.optString("ID"))) {
                GameAgentUtil.p(this, str2);
            } else {
                v(str2);
            }
            IGameAssistClientCallback o2 = o(str);
            if (o2 != null) {
                o2.onReceivedCallback(1, new String());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(final String str) {
        this.f16382i.post(new Runnable() { // from class: com.zte.gameassist.aiagent.b
            @Override // java.lang.Runnable
            public final void run() {
                GameAgentService.this.r(str);
            }
        });
    }

    private void v(String str) {
        IGameAssistClientCallback iGameAssistClientCallback = this.f16381h;
        if (iGameAssistClientCallback != null) {
            try {
                iGameAssistClientCallback.onReceivedCallback(1, str);
                return;
            } catch (DeadObjectException e2) {
                GaLog.c("GameAgentService", "sendMessageToVoiceClient error", e2);
            } catch (RemoteException e3) {
                GaLog.c("GameAgentService", "sendMessageToVoiceClient error", e3);
            }
        }
        GameAgentUtil.p(this, str);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f16385l;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f16382i = new Handler(Looper.getMainLooper()) { // from class: com.zte.gameassist.aiagent.GameAgentService.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 0) {
                    return;
                }
                GameAgentService.this.p((InMsg) message.obj);
            }
        };
    }
}
