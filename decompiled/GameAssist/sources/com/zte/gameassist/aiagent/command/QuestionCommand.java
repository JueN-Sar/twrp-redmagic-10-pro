package com.zte.gameassist.aiagent.command;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.zte.gameassist.aiagent.BaseCommand;
import com.zte.gameassist.aiagent.CommandExecutor;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.aiagent.bean.NLP;
import com.zte.gameassist.aiagent.bean.OutMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class QuestionCommand extends BaseCommand implements CommandExecutor {
    private static final String TAG = "QuestionCommand";

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void execute(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (!SystemMgr.H()) {
            GameAgentUtil.j(this.mContext, iGameAssistClientCallback, inMsg);
            return;
        }
        NLP h2 = inMsg.h();
        if ("question".equals(h2.c())) {
            if (!"1050".equals(h2.d())) {
                GaLog.a(TAG, "status: " + h2.d());
                return;
            }
            try {
                String optString = new JSONObject(h2.a()).optString("hint");
                GaLog.a(TAG, "hint: " + optString);
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                OutMsg outMsg = new OutMsg(inMsg.c(), 0, optString, "");
                outMsg.b(inMsg.d() + "<///>" + optString);
                iGameAssistClientCallback.onReceivedCallback(1, outMsg.toString());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    public JSONObject getPlugOptions() {
        return null;
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void init(Context context) {
    }

    public boolean isPlugInit() {
        return true;
    }
}
