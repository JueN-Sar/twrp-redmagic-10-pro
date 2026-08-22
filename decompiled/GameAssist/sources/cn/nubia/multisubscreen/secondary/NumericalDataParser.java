package cn.nubia.multisubscreen.secondary;

import android.text.TextUtils;
import cn.nubia.multisubscreen.data.BatchData;
import cn.nubia.multisubscreen.view.SinkDisplayView;
import cn.nubia.multisubscreen.view.SinkTitleView;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class NumericalDataParser {
    public static void a(BatchData batchData, SinkTitleView sinkTitleView, SinkDisplayView sinkDisplayView) {
        for (String str : batchData.getKeys()) {
            String str2 = batchData.get(str);
            GaLog.a("MultiSubScreen_NumericalDataParser", "parse key = " + str + ", and value = " + str2);
            if (!TextUtils.isEmpty(str2)) {
                str.hashCode();
                switch (str) {
                    case "play_time":
                        sinkTitleView.setGameDuration(str2);
                        break;
                    case "battery_level":
                        sinkTitleView.setBatteryLevel(str2);
                        sinkDisplayView.setBatteryLevel(Integer.parseInt(str2));
                        break;
                    case "fan_speed":
                        try {
                            JSONArray jSONArray = new JSONArray(str2);
                            if (jSONArray.length() == 2) {
                                sinkDisplayView.r(jSONArray.getInt(0), jSONArray.getInt(1));
                                break;
                            } else {
                                break;
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                            break;
                        }
                    case "cps":
                        sinkTitleView.setCps(str2);
                        sinkDisplayView.setCps(str2);
                        break;
                    case "cpu":
                        try {
                            if (new JSONArray(str2).length() == 2) {
                                sinkDisplayView.q(r4.getInt(0), r4.getInt(1));
                                break;
                            } else {
                                break;
                            }
                        } catch (JSONException e3) {
                            e3.printStackTrace();
                            break;
                        }
                    case "fps":
                        sinkTitleView.setFps(str2);
                        sinkDisplayView.setFps(str2);
                        break;
                    case "gpu":
                        try {
                            if (new JSONArray(str2).length() == 2) {
                                sinkDisplayView.t(r4.getInt(0), r4.getInt(1));
                                break;
                            } else {
                                break;
                            }
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                            break;
                        }
                    case "mpm":
                        sinkTitleView.setMpm(str2);
                        sinkDisplayView.setMpm(str2);
                        break;
                    case "net":
                        sinkTitleView.setNet(str2);
                        sinkDisplayView.setNet(str2);
                        break;
                    case "current_time":
                        sinkTitleView.setCurrentTime(str2);
                        break;
                }
            }
        }
    }
}
