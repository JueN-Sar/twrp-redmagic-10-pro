package com.zte.gameassist.aiagent.policy;

import android.content.Context;
import android.text.TextUtils;
import com.zte.gameassist.aiagent.CommandExecutor;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.GamePluginClientCallback;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.JsonUtils;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.utils.GaLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class AICommandDispatcher {

    /* renamed from: g, reason: collision with root package name */
    private static volatile AICommandDispatcher f16432g;

    /* renamed from: b, reason: collision with root package name */
    private Context f16434b;

    /* renamed from: d, reason: collision with root package name */
    private final JSONObject f16436d;

    /* renamed from: e, reason: collision with root package name */
    private final JSONObject f16437e;

    /* renamed from: f, reason: collision with root package name */
    private InMsg f16438f;

    /* renamed from: a, reason: collision with root package name */
    private final String f16433a = "CommandDispatcher";

    /* renamed from: c, reason: collision with root package name */
    private final Map f16435c = new HashMap();

    private AICommandDispatcher(Context context) {
        this.f16434b = context;
        this.f16436d = f(context, "settings_config.json");
        this.f16437e = f(context, "tools_config.json");
    }

    private void c(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        CommandExecutor commandExecutor;
        if (!"llm_common_confirm".equals(inMsg.b())) {
            commandExecutor = null;
        } else {
            if ("negative".equals(inMsg.e())) {
                GameAgentUtil.l(this.f16434b, iGameAssistClientCallback, inMsg, false);
                return;
            }
            InMsg inMsg2 = this.f16438f;
            if (inMsg2 == null) {
                GameAgentUtil.m(this.f16434b, iGameAssistClientCallback, inMsg);
                return;
            } else {
                commandExecutor = d(inMsg2.b(), this.f16438f.e());
                inMsg.l(this.f16438f);
            }
        }
        if (commandExecutor == null) {
            commandExecutor = d(inMsg.b(), inMsg.e());
        }
        if (commandExecutor != null) {
            commandExecutor.execute(iGameAssistClientCallback, inMsg);
        } else {
            GaLog.a("CommandDispatcher", "get no executor for voice domain=" + inMsg.b() + " intent=" + inMsg.e());
            GameAgentUtil.m(this.f16434b, iGameAssistClientCallback, inMsg);
        }
        this.f16438f = inMsg;
    }

    private CommandExecutor d(String str, String str2) {
        Map map = (Map) this.f16435c.get(str);
        if (map != null && map.containsKey(str2)) {
            return (CommandExecutor) map.get(str2);
        }
        if (str != null) {
            String optString = str.equals("RedMagicSetting") ? this.f16436d.optString(str2) : str.equals("RedMagicTool") ? this.f16437e.optString(str2) : null;
            if (TextUtils.isEmpty(optString)) {
                GaLog.a("CommandDispatcher", "get no executor for domain=" + str + " intent=" + str2 + " className=" + optString);
                return null;
            }
            GaLog.a("CommandDispatcher", "getExecutor : domain=" + str + " intent=" + str2 + " className=" + optString);
            try {
                CommandExecutor commandExecutor = (CommandExecutor) Class.forName(optString).newInstance();
                commandExecutor.init(this.f16434b);
                if (map == null) {
                    map = new HashMap();
                    this.f16435c.put(str, map);
                }
                map.put(str2, commandExecutor);
                return commandExecutor;
            } catch (Exception e2) {
                GaLog.c("CommandDispatcher", "getExecutor", e2);
            }
        }
        return null;
    }

    public static AICommandDispatcher e(Context context) {
        if (f16432g == null) {
            synchronized (AICommandDispatcher.class) {
                try {
                    if (f16432g == null) {
                        f16432g = new AICommandDispatcher(context);
                    }
                } finally {
                }
            }
        }
        return f16432g;
    }

    public static JSONObject f(Context context, String str) {
        return JsonUtils.a(context, str);
    }

    public void a(IGameAssistClientCallback iGameAssistClientCallback, GamePluginClientCallback gamePluginClientCallback, InMsg inMsg) {
        if (TextUtils.isEmpty(inMsg.g())) {
            GameAgentUtil.m(this.f16434b, iGameAssistClientCallback, inMsg);
            return;
        }
        String[] i2 = inMsg.i();
        if (i2 == null || i2.length < 1) {
            GameAgentUtil.m(this.f16434b, iGameAssistClientCallback, inMsg);
            return;
        }
        if (i2.length == 1) {
            gamePluginClientCallback.a(i2[0], iGameAssistClientCallback, inMsg);
            return;
        }
        CommandExecutor d2 = d(i2[0], i2[1]);
        if (d2 != null) {
            d2.execute(iGameAssistClientCallback, inMsg);
            return;
        }
        GaLog.a("CommandDispatcher", "get no executor for click domain=" + i2[0] + " intent=" + i2[1]);
        GameAgentUtil.m(this.f16434b, iGameAssistClientCallback, inMsg);
    }

    public void b(IGameAssistClientCallback iGameAssistClientCallback, GamePluginClientCallback gamePluginClientCallback, InMsg inMsg) {
        if (inMsg.j() == 1) {
            a(iGameAssistClientCallback, gamePluginClientCallback, inMsg);
        } else {
            c(iGameAssistClientCallback, inMsg);
        }
    }
}
