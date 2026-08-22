package com.zte.gameassist.aiagent.command;

import android.content.Context;
import android.text.TextUtils;
import com.zte.gameassist.aiagent.BaseCommand;
import com.zte.gameassist.aiagent.CommandExecutor;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LowSugarDessertCommand extends BaseCommand implements CommandExecutor {
    private final String TAG = "LowSugarDessertCommand";

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void execute(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (!ZteFeature.isSupportLowSugar()) {
            GaLog.e("LowSugarDessertCommand", "execute is not support and return!");
            return;
        }
        String e2 = inMsg.e();
        GaLog.e("LowSugarDessertCommand", "execute intent=" + e2);
        if (inMsg.j() != 1) {
            try {
                EventListenerMgr.g(4, e2, "", iGameAssistClientCallback, inMsg, "low_sugar");
                return;
            } catch (Exception e3) {
                GaLog.c("LowSugarDessertCommand", "execute error", e3);
                return;
            }
        }
        String a2 = inMsg.a();
        GaLog.a("LowSugarDessertCommand", "execute pkgName = " + a2);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        try {
            this.mContext.startActivity(this.mContext.getPackageManager().getLaunchIntentForPackage(a2));
        } catch (Exception e4) {
            GaLog.a("LowSugarDessertCommand", "execute startActivity and has exception = " + e4);
        }
    }

    public JSONObject getPlugOptions() {
        return null;
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void init(Context context) {
        this.mContext = context;
    }

    public boolean isPlugInit() {
        return true;
    }
}
