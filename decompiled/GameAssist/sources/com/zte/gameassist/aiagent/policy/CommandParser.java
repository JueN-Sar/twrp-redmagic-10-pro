package com.zte.gameassist.aiagent.policy;

import android.util.Log;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.aiagent.bean.NLP;
import com.zte.gameassist.aiagent.bean.NLP_Results;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CommandParser {
    public static InMsg a(String str) {
        Log.i("CommandParser", "parseInPMsg " + str);
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("ID");
        int optInt = jSONObject.optInt("type");
        if (1 != optInt) {
            return new InMsg(optString, optInt, jSONObject.optString("input"), jSONObject.optString("nlp"));
        }
        InMsg inMsg = new InMsg(optString, optInt);
        String optString2 = jSONObject.optString("name");
        String optString3 = jSONObject.optString("action_id");
        inMsg.m(optString2);
        inMsg.k(optString3);
        return inMsg;
    }

    public static NLP_Results b(String str) {
        Log.i("CommandParser", "parseIntentResult " + str);
        JSONObject jSONObject = new JSONObject(str);
        return new NLP_Results(jSONObject.optInt("score"), jSONObject.optString("domain"), jSONObject.optString("intent"), jSONObject.optString("content"));
    }

    public static NLP c(String str) {
        Log.i("CommandParser", "parseNLPMsg " + str);
        JSONObject jSONObject = new JSONObject(str);
        return new NLP(jSONObject.optString("raw_text"), jSONObject.optString("status"), jSONObject.optJSONArray("results").get(0).toString());
    }
}
