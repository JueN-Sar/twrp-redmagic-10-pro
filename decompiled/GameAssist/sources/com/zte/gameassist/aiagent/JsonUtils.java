package com.zte.gameassist.aiagent;

import android.content.Context;
import com.zte.gameassist.utils.GaLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JsonUtils {
    public static JSONObject a(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream open = context.getAssets().open(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    open.close();
                    return new JSONObject(sb.toString());
                }
                sb.append(readLine);
            }
        } catch (IOException | JSONException e2) {
            e2.printStackTrace();
            GaLog.b("JsonFileMerger", "loadJsonFromAssets e=" + e2);
            return null;
        }
    }
}
