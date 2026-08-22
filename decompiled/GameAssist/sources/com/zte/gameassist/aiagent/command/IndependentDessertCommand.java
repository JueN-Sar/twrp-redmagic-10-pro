package com.zte.gameassist.aiagent.command;

import android.content.Context;
import android.content.Intent;
import com.zte.gameassist.aiagent.BaseCommand;
import com.zte.gameassist.aiagent.CommandExecutor;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class IndependentDessertCommand extends BaseCommand implements CommandExecutor {
    private final String TAG = "IndependentDessertCommand";

    private void startImageInterpretation(InMsg inMsg) {
        Intent intent = new Intent("cn.zte.gamefloat.image.search");
        intent.setPackage("cn.zte.gamefloat");
        intent.putExtra("event", "start");
        intent.putExtra("ID", inMsg.c());
        intent.putExtra("packageName", SystemMgr.t());
        this.mContext.startService(intent);
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void execute(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        String e2 = inMsg.e();
        GaLog.e("IndependentDessertCommand", "execute intent=" + e2);
        if ("game_start_image_interpretation".equals(e2)) {
            if (ZteFeature.supportImageSearch()) {
                startImageInterpretation(inMsg);
            } else {
                replyToBeSupportedMessage(iGameAssistClientCallback, inMsg);
            }
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
