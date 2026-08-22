package com.zte.gameassist.aiagent.command;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import cn.zte.gameaiasst.IGameAiAsstCallback;
import cn.zte.gameaiasst.IGameAiAsstInterface;
import com.zte.gameassist.aiagent.BaseCommand;
import com.zte.gameassist.aiagent.CommandExecutor;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.R;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.aiagent.bean.OutMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class IntelligentNavigatorCommand extends BaseCommand implements CommandExecutor {
    private static final int MSG_TIMEOUT = 1;
    private static final String TAG = "IntelligentNavigatorCommand";
    private static final int TIME_TIMEOUT = 60000;
    private static int mCount;
    private Context mContext;
    private CustomInMsg mCustomInMsg;
    private IGameAiAsstInterface mGameAiAsstService;
    private boolean mIsBound;
    private Handler mMainLooperHandler;
    private ServiceConnection mServiceConnection;
    private String mCurPkg = "";
    final IGameAiAsstCallback mCallback = new IGameAiAsstCallback.Stub() { // from class: com.zte.gameassist.aiagent.command.IntelligentNavigatorCommand.2
        private final String ERROR_MSG = "Invalid response";

        @Override // cn.zte.gameaiasst.IGameAiAsstCallback
        public void responseResult(String str, int i2) {
            synchronized (this) {
                try {
                    int i3 = 20;
                    if (IntelligentNavigatorCommand.this.mCustomInMsg.a() != i2) {
                        if (str != null) {
                            int length = str.length();
                            StringBuilder sb = new StringBuilder();
                            sb.append("response result=");
                            if (length < 20) {
                                i3 = length;
                            }
                            sb.append(str.substring(0, i3));
                            GaLog.a(IntelligentNavigatorCommand.TAG, sb.toString());
                        }
                        GaLog.a(IntelligentNavigatorCommand.TAG, "response id " + i2 + " is different form " + IntelligentNavigatorCommand.this.mCustomInMsg.a());
                        return;
                    }
                    if (str == null || "Invalid response".equals(str)) {
                        GaLog.a(IntelligentNavigatorCommand.TAG, "response result=" + str);
                        IntelligentNavigatorCommand intelligentNavigatorCommand = IntelligentNavigatorCommand.this;
                        intelligentNavigatorCommand.replyResultAsync(intelligentNavigatorCommand.mContext.getString(R.string.aiagent_to_be_supported), false);
                        return;
                    }
                    int length2 = str.length();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("response result=");
                    if (length2 < 20) {
                        i3 = length2;
                    }
                    sb2.append(str.substring(0, i3));
                    GaLog.a(IntelligentNavigatorCommand.TAG, sb2.toString());
                    IntelligentNavigatorCommand.this.replyResultAsync(str, true);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    };

    private static class CustomInMsg {

        /* renamed from: a, reason: collision with root package name */
        private InMsg f16429a;

        /* renamed from: b, reason: collision with root package name */
        private int f16430b;

        public CustomInMsg(InMsg inMsg, int i2) {
            this.f16429a = inMsg;
            this.f16430b = i2;
        }

        public int a() {
            return this.f16430b;
        }

        public InMsg b() {
            return this.f16429a;
        }
    }

    class IntelligentServiceConnection implements ServiceConnection {
        IntelligentServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GaLog.a(IntelligentNavigatorCommand.TAG, "intelligent navigator connected");
            IntelligentNavigatorCommand.this.mGameAiAsstService = IGameAiAsstInterface.Stub.asInterface(iBinder);
            try {
                IntelligentNavigatorCommand.this.mGameAiAsstService.registerCallback(IntelligentNavigatorCommand.this.mCallback);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            IntelligentNavigatorCommand intelligentNavigatorCommand = IntelligentNavigatorCommand.this;
            intelligentNavigatorCommand.requestGameAIAsst(intelligentNavigatorCommand.mCustomInMsg);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GaLog.a(IntelligentNavigatorCommand.TAG, "intelligent navigator connected");
            IntelligentNavigatorCommand.this.unbindService();
        }
    }

    private void bindService() {
        if (this.mIsBound) {
            unbindService();
        }
        GaLog.a(TAG, "bindService");
        Intent intent = new Intent("cn.zte.gameaiasst.GAMEAIASSTSERVICE");
        intent.setPackage("cn.zte.gameaiasst");
        String t = SystemMgr.t();
        this.mCurPkg = t;
        intent.putExtra("pkg", t);
        this.mContext.bindService(intent, this.mServiceConnection, 1);
        this.mIsBound = true;
        resetTimeOutMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replyResultAsync(String str, boolean z) {
        if (SystemMgr.H()) {
            GameAgentUtil.p(this.mContext, new OutMsg("", 0, str, "").toString());
            GameAgentUtil.r(this.mContext, this.mCustomInMsg.b(), this.mCustomInMsg.b(), z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestGameAIAsst(CustomInMsg customInMsg) {
        String d2 = customInMsg.b().d();
        if (TextUtils.isEmpty(d2)) {
            int length = d2.length();
            StringBuilder sb = new StringBuilder();
            sb.append("ask intelligent navigator ");
            if (length >= 15) {
                length = 15;
            }
            sb.append(d2.substring(0, length));
            GaLog.e(TAG, sb.toString());
        } else {
            GaLog.k(TAG, "ask intelligent navigator " + d2);
        }
        IGameAiAsstInterface iGameAiAsstInterface = this.mGameAiAsstService;
        if (iGameAiAsstInterface == null) {
            bindService();
            return;
        }
        try {
            iGameAiAsstInterface.requestGameAIAsst(this.mCustomInMsg.b().d(), this.mCustomInMsg.a());
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    private void resetTimeOutMessage() {
        this.mMainLooperHandler.removeMessages(1);
        this.mMainLooperHandler.sendEmptyMessageDelayed(1, 60000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbindService() {
        if (this.mIsBound) {
            GaLog.a(TAG, "unbindService");
            this.mCurPkg = "";
            unregisterCallback();
            try {
                this.mContext.unbindService(this.mServiceConnection);
                this.mIsBound = false;
            } catch (Exception e2) {
                GaLog.a(TAG, "unbindService error " + e2.getMessage());
            }
            this.mMainLooperHandler.removeMessages(1);
        }
    }

    private void unregisterCallback() {
        IGameAiAsstInterface iGameAiAsstInterface = this.mGameAiAsstService;
        if (iGameAiAsstInterface == null) {
            return;
        }
        try {
            iGameAiAsstInterface.unregisterCallback(this.mCallback);
            this.mGameAiAsstService = null;
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void execute(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (!SystemMgr.H()) {
            GameAgentUtil.j(this.mContext, iGameAssistClientCallback, inMsg);
            return;
        }
        GaLog.a(TAG, "execute " + this.mGameAiAsstService + " " + iGameAssistClientCallback);
        GameAgentUtil.o(this.mContext, iGameAssistClientCallback, inMsg);
        int i2 = mCount;
        if (i2 == Integer.MAX_VALUE) {
            mCount = 0;
        } else {
            mCount = i2 + 1;
        }
        this.mCustomInMsg = new CustomInMsg(inMsg, mCount);
        String str = this.mCurPkg;
        if (str == null || !str.equals(SystemMgr.t())) {
            bindService();
        } else if (this.mGameAiAsstService == null) {
            bindService();
        } else {
            requestGameAIAsst(this.mCustomInMsg);
            resetTimeOutMessage();
        }
    }

    public JSONObject getPlugOptions() {
        return null;
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void init(Context context) {
        GaLog.a(TAG, "init");
        this.mContext = context;
        this.mServiceConnection = new IntelligentServiceConnection();
        this.mMainLooperHandler = new Handler(Looper.getMainLooper()) { // from class: com.zte.gameassist.aiagent.command.IntelligentNavigatorCommand.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                GaLog.a(IntelligentNavigatorCommand.TAG, "timeout");
                IntelligentNavigatorCommand.this.unbindService();
            }
        };
    }

    public boolean isPlugInit() {
        return true;
    }
}
