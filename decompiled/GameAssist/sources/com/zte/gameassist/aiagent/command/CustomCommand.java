package com.zte.gameassist.aiagent.command;

import android.content.Context;
import com.zte.gameassist.aiagent.BaseCommand;
import com.zte.gameassist.aiagent.CommandExecutor;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.JsonUtils;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CustomCommand extends BaseCommand implements CommandExecutor {
    private static final String TILES_CONFIG = "tiles/tiles_config.json";
    private final String TAG = CustomCommand.class.getSimpleName();
    private JSONObject mTilesConfig;

    private String getTileSpec(InMsg inMsg) {
        InMsg f2;
        return this.mTilesConfig == null ? "" : (("negative".equals(inMsg.e()) || "positive".equals(inMsg.e())) && (f2 = inMsg.f()) != null) ? this.mTilesConfig.optString(f2.e()) : this.mTilesConfig.optString(inMsg.e());
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void execute(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (inMsg.j() == 0 && !SystemMgr.H()) {
            GameAgentUtil.j(this.mContext, iGameAssistClientCallback, inMsg);
            return;
        }
        try {
            String e2 = inMsg.e();
            String tileSpec = getTileSpec(inMsg);
            GaLog.e(this.TAG, "execute intent=" + e2 + " tileSpec=" + tileSpec);
            EventListenerMgr.g(4, e2, "", iGameAssistClientCallback, inMsg, tileSpec);
        } catch (Exception e3) {
            GaLog.c(this.TAG, "execute error", e3);
        }
    }

    public JSONObject getPlugOptions() {
        return null;
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void init(Context context) {
        this.mContext = context;
        this.mTilesConfig = JsonUtils.a(context, TILES_CONFIG);
    }

    public boolean isPlugInit() {
        return true;
    }
}
