package cn.nubia.globalsearch;

import android.content.ContentValues;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.PerformanceUtils;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.FunctionAllocationHelper;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class GlobalSearchUtil {
    private static final String GLOBAL_SEARCH_CONFIG = "global_search_config.xml";
    private static final String GLOBAL_SEARCH_CONFIG_VERSION = "gamespace_global_search_config_version";
    private static final String TAG = "GlobalSearchUtil";

    public static boolean isSupportItem(String str, String str2) {
        List<String> arrayList = new ArrayList<>();
        if (!str2.contains(ControlPanelFeatureHelper.ZTE_FEATURE_GAME_CONTROLPANEL_MENU)) {
            String[] split = str2.split(",");
            int length = split.length;
            boolean z = false;
            for (int i = 0; i < length; i += 2) {
                if (i < length - 1) {
                    String str3 = FeatureUtil.get(split[i], "");
                    if ("".equals(str3)) {
                        arrayList.clear();
                        arrayList = FeatureUtil.getGameCenterSupport(split[i]);
                        z = arrayList == null ? false : arrayList.contains(split[i + 1]);
                    } else {
                        z = str3.contains(split[i + 1]);
                    }
                    if (!z) {
                        break;
                    }
                } else {
                    z = FeatureUtil.getBoolean(str2, false).booleanValue();
                }
            }
            return z;
        }
        Boolean bool = false;
        if (!str2.contains(";")) {
            return supportItem(str2);
        }
        String[] split2 = str2.split(";");
        int length2 = split2.length;
        int i2 = 0;
        boolean z2 = false;
        while (true) {
            if (i2 >= length2) {
                break;
            }
            String str4 = split2[i2];
            if (i2 != 0) {
                if (!z2) {
                    Log.i(TAG, " not support menu ");
                    break;
                }
                bool = str4.contains(",") ? Boolean.valueOf(supportItem(str4)) : TextUtils.equals(str4, "ZTE_FEATURE_MANUAL_RECORD_ONLY") ? Boolean.valueOf(!FeatureUtil.getBoolean(str4, false).booleanValue()) : FeatureUtil.getBoolean(str4, false);
            } else if (str4.contains(",")) {
                z2 = supportItem(str4);
            }
            i2++;
        }
        return z2 && bool.booleanValue();
    }

    public static ArrayList<ContentValues> parserXml(Context context, boolean z) {
        InputStream open;
        ArrayList<ContentValues> arrayList = new ArrayList<>();
        PerformanceUtils.updateGyroSensorWhiteList(context);
        FunctionAllocationHelper.getInstance().getRedmagicHighWhiteList(context);
        FunctionAllocationHelper.getInstance().initResourcePreDownloadWhiteList(context);
        FunctionAllocationHelper.getInstance().initTpGameWhiteList(context);
        InputStream inputStream = null;
        try {
            try {
                try {
                    open = context.getAssets().open(GLOBAL_SEARCH_CONFIG);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            newPullParser.setInput(open, Key.STRING_CHARSET_NAME);
            String str = null;
            ContentValues contentValues = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    String trim = newPullParser.getName().trim();
                    if ("plugin_config".equals(trim)) {
                        str = newPullParser.getAttributeValue(null, "config_version").trim();
                    } else if ("item_config".equals(newPullParser.getName())) {
                        contentValues = new ContentValues();
                    } else if (GlobalSearchConstants.NAME.equals(trim)) {
                        setName(context, GlobalSearchConstants.NAME, newPullParser.nextText(), contentValues);
                        putStringValue(GlobalSearchConstants.APP_LABEL, context.getString(R.string.game_space_app_name), contentValues);
                    } else if ("help".equals(trim)) {
                        setName(context, "help", newPullParser.nextText(), contentValues);
                    } else {
                        putStringValue(trim, newPullParser.nextText(), contentValues);
                    }
                } else if (eventType == 3 && "item_config".equals(newPullParser.getName()) && !contentValues.isEmpty()) {
                    if (z) {
                        if (contentValues.containsKey("status") && !"normal".equals(contentValues.getAsString("status"))) {
                            arrayList.add(contentValues);
                        }
                    } else if (!contentValues.containsKey(GlobalSearchConstants.FEATURE)) {
                        arrayList.add(contentValues);
                    } else if (isSupportItem(contentValues.getAsString(GlobalSearchConstants.NAME), contentValues.getAsString(GlobalSearchConstants.FEATURE))) {
                        arrayList.add(contentValues);
                    }
                }
            }
            setGlobalSearchConfigVersion(context, str);
        } catch (Exception e3) {
            e = e3;
            inputStream = open;
            e.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            inputStream = open;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        if (open != null) {
            open.close();
        }
        return arrayList;
    }

    public static void putStringValue(String str, String str2, ContentValues contentValues) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        contentValues.put(str, str2);
    }

    public static void setGlobalSearchConfigVersion(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Settings.Global.putString(context.getContentResolver(), GLOBAL_SEARCH_CONFIG_VERSION, str);
    }

    public static void setName(Context context, String str, String str2, ContentValues contentValues) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (!str2.contains(",")) {
            if (str2.equals("gcs_game_off_intell_screen_title") && CommonUtil.isNubia()) {
                str2 = "gcs_game_off_intell_screen_title_redmagic";
            }
            contentValues.put(str, context.getString(context.getResources().getIdentifier(str2, "string", context.getPackageName())));
            return;
        }
        String[] split = str2.split(",");
        StringBuilder sb = new StringBuilder();
        for (String str3 : split) {
            int identifier = context.getResources().getIdentifier(str3, "string", context.getPackageName());
            if (identifier != 0) {
                sb.append(context.getString(identifier)).append(",");
            }
        }
        contentValues.put(str, sb.toString());
    }

    private static boolean supportItem(String str) {
        String[] split = str.split(",");
        if (split.length >= 2) {
            return FeatureUtil.get(split[0], null).contains(split[1]);
        }
        return false;
    }

    public static void updateSearchConfigVersion(Context context) {
        try {
            setGlobalSearchConfigVersion(context, DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(context.getAssets().open(GLOBAL_SEARCH_CONFIG)).getDocumentElement().getAttribute("config_version"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
