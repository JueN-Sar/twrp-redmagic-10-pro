package cn.nubia.tgk.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.tgk.TgkHelper;
import cn.nubia.tgk.data.FileProvider;
import cn.nubia.tgk.data.TgkData;
import cn.nubia.tgk.data.TgkDataContract;
import cn.nubia.tgk.data.TgkGameInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class TgkUtils {
    private static final String APPADD_URI_NO_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false";
    public static final String ATTR_APP_NAME = "component";
    public static final String TAG = "TgkUtils";
    public static int mScreenHeight;
    public static int mScreenWidth;

    public static Bundle applyTgkCase(Context context, String str, String str2, String str3, String str4, int i, int i2) {
        String str5 = str;
        Log.i(TAG, "applyStrategy,name : " + str5 + " uniqueId : " + str2 + " gamePackageName : " + str3 + " fileUri : " + str4 + " screenWidth : " + i + " screenHeight : " + i2);
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str2)) {
            bundle.putInt("resultCode", 0);
            bundle.putString("message", "uniqueId is null");
            return bundle;
        }
        if (!isStrategyPixelAvailable(context, i, i2)) {
            bundle.putInt("resultCode", 0);
            bundle.putString("message", "screen pixel is not available");
            return bundle;
        }
        if (havaSameUniqueId(context, str3, str2)) {
            bundle.putInt("resultCode", 1);
            bundle.putString("message", "same uniqueId");
            return bundle;
        }
        while (haveSamePolicyName(context, str3, str5)) {
            str5 = renameDupliName(str5);
        }
        try {
            JSONObject jSONObject = new JSONObject(readFileToString(context, str4));
            TgkData parseTgkDataInfoFromJson = parseTgkDataInfoFromJson(context, jSONObject);
            if (parseTgkDataInfoFromJson != null) {
                parseTgkDataInfoFromJson.state = 0;
                parseTgkDataInfoFromJson.showName = str5;
                Log.i(TAG, "applyTgkCase tgkData =" + parseTgkDataInfoFromJson);
                long insertImportTgkCase = TgkHelper.insertImportTgkCase(context, parseTgkDataInfoFromJson);
                if (insertImportTgkCase > 0) {
                    importMacro(context, TgkHelper.getTgkLinkKey(insertImportTgkCase, parseTgkDataInfoFromJson.state, 137), str3, jSONObject, TgkDataContract.TgkEntry.TGK_CASE_L_LINK_OPTION, i, i2);
                    importMacro(context, TgkHelper.getTgkLinkKey(insertImportTgkCase, parseTgkDataInfoFromJson.state, 138), str3, jSONObject, TgkDataContract.TgkEntry.TGK_CASE_R_LINK_OPTION, i, i2);
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "applyTgkCase Exception e =" + e.getMessage());
            e.printStackTrace();
        }
        bundle.putInt("resultCode", 1);
        bundle.putString("message", "success");
        return bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0093 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String byteToUri(android.content.Context r5, byte[] r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "TgkUtils"
            java.lang.String r1 = "e = "
            java.io.File r2 = r5.getFilesDir()
            boolean r3 = r2.exists()
            if (r3 != 0) goto L11
            r2.mkdir()
        L11:
            java.io.File r3 = new java.io.File
            java.lang.String r4 = "shared"
            r3.<init>(r2, r4)
            boolean r2 = r3.exists()
            if (r2 != 0) goto L21
            r3.mkdir()
        L21:
            java.io.File r2 = new java.io.File
            r2.<init>(r3, r7)
            boolean r4 = r2.exists()
            if (r4 == 0) goto L2f
            r2.delete()
        L2f:
            java.io.File r2 = new java.io.File
            r2.<init>(r3, r7)
            r7 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L51
            r3.write(r6)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c
            r3.flush()     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4c
            r3.close()     // Catch: java.io.IOException -> L44
            goto L6e
        L44:
            r6 = move-exception
            r6.printStackTrace()
            goto L6e
        L49:
            r5 = move-exception
            r7 = r3
            goto L96
        L4c:
            r6 = move-exception
            r7 = r3
            goto L52
        L4f:
            r5 = move-exception
            goto L96
        L51:
            r6 = move-exception
        L52:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L4f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L4f
            java.lang.StringBuilder r6 = r3.append(r6)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L4f
            android.util.Log.d(r0, r6)     // Catch: java.lang.Throwable -> L4f
            if (r7 == 0) goto L6e
            r7.close()     // Catch: java.io.IOException -> L44
        L6e:
            java.lang.String r6 = "cn.nubia.tgk.data.fileprovider"
            android.net.Uri r6 = cn.nubia.tgk.data.FileProvider.getUriForFile(r5, r6, r2)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "sharedFileUri = "
            r7.<init>(r1)
            java.lang.StringBuilder r7 = r7.append(r6)
            java.lang.String r7 = r7.toString()
            android.util.Log.i(r0, r7)
            java.lang.String r7 = "cn.nubia.gamehelpmodule"
            r0 = 1
            r5.grantUriPermission(r7, r6, r0)
            if (r6 == 0) goto L93
            java.lang.String r5 = r6.toString()
            return r5
        L93:
            java.lang.String r5 = ""
            return r5
        L96:
            if (r7 == 0) goto La0
            r7.close()     // Catch: java.io.IOException -> L9c
            goto La0
        L9c:
            r6 = move-exception
            r6.printStackTrace()
        La0:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.tgk.util.TgkUtils.byteToUri(android.content.Context, byte[], java.lang.String):java.lang.String");
    }

    public static String genUniqueId(TgkData tgkData) {
        if (tgkData == null) {
            Log.e(TAG, "tgkData == null");
            return "";
        }
        StringBuilder sb = new StringBuilder(tgkData.packageName);
        sb.append(tgkData.sensitivityArray[0]);
        sb.append(tgkData.sensitivityArray[1]);
        for (int i = 0; i < 3; i++) {
            sb.append(tgkData.optionSwArray[i]);
        }
        for (int i2 = 0; i2 < 3; i2++) {
            sb.append(tgkData.optionArray[i2]);
        }
        for (int i3 = 0; i3 < 3; i3++) {
            for (int i4 = 0; i4 < 2; i4++) {
                sb.append(tgkData.pointsArray[i3][i4].top);
                sb.append(tgkData.pointsArray[i3][i4].left);
                sb.append(tgkData.pointsArray[i3][i4].bottom);
                sb.append(tgkData.pointsArray[i3][i4].right);
            }
        }
        String md5 = SecurityUtils.getMd5(sb.toString());
        return !TextUtils.isEmpty(md5) ? md5 : "";
    }

    private static ArrayList<String> getGamePackage(Context context) {
        try {
            Cursor query = context.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            try {
                int columnIndex = query.getColumnIndex("component");
                ArrayList<String> arrayList = new ArrayList<>();
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    arrayList.add(query.getString(columnIndex));
                }
                if (query != null) {
                    query.close();
                }
                return arrayList;
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Failed load game app data.", e);
            return null;
        }
    }

    public static void getScreenSize(Context context) {
        if (mScreenHeight == 0 || mScreenWidth == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            Point point = new Point();
            windowManager.getDefaultDisplay().getRealSize(point);
            if (point.x > point.y) {
                mScreenHeight = point.y;
                mScreenWidth = point.x;
            } else {
                mScreenHeight = point.x;
                mScreenWidth = point.y;
            }
            Log.d(TAG, "getScreenSize mScreenHeight=" + mScreenHeight + ";mScreenWidth=" + mScreenWidth);
        }
    }

    public static String getSharedFileUri(Context context, long j, int i, String str) {
        TgkData tgkCase = TgkHelper.getTgkCase(context, j, i, str);
        Log.i(TAG, "getSharedFileUri tgkData : " + tgkCase);
        if (tgkCase == null) {
            Log.i(TAG, "getSharedFileUri failed tgkData is null");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_id", tgkCase.ID);
            jSONObject.put("state", tgkCase.state);
            jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_ORG_NAME, tgkCase.originalName);
            jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME, tgkCase.showName);
            jSONObject.put("package_name", tgkCase.packageName);
            jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_MAIN_SW, tgkCase.mainSw);
            if (tgkCase.optionSwArray != null) {
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_L_SW, tgkCase.optionSwArray[0]);
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_R_SW, tgkCase.optionSwArray[1]);
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_M_SW, tgkCase.optionSwArray[2]);
            }
            jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_VIBRATE_SW, tgkCase.vibrateSw);
            if (tgkCase.sensitivityArray != null) {
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY, tgkCase.sensitivityArray[0]);
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY, tgkCase.sensitivityArray[1]);
            }
            if (tgkCase.pointsArray != null) {
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_L_POINTS, TgkHelper.rectToString(tgkCase.pointsArray[0]));
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_R_POINTS, TgkHelper.rectToString(tgkCase.pointsArray[1]));
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_M_POINTS, TgkHelper.rectToString(tgkCase.pointsArray[2]));
            }
            if (tgkCase.optionArray != null) {
                if (tgkCase.rapidFireCountArray[0] == 5 || tgkCase.optionArray[0] != 6) {
                    jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION, tgkCase.setLinkFlagArray[0] + tgkCase.optionArray[0]);
                } else {
                    jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION, tgkCase.setLinkFlagArray[0] + tgkCase.optionArray[0] + 100 + tgkCase.rapidFireCountArray[0]);
                }
                if (tgkCase.optionArray[0] == 4) {
                    linkOptionWriteToJson(context, "" + TgkHelper.getTgkLinkKey(tgkCase.ID, tgkCase.state, 137), str, jSONObject, TgkDataContract.TgkEntry.TGK_CASE_L_LINK_OPTION);
                }
                if (tgkCase.rapidFireCountArray[1] == 5 || tgkCase.optionArray[1] != 6) {
                    jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION, tgkCase.setLinkFlagArray[1] + tgkCase.optionArray[1]);
                } else {
                    jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION, tgkCase.setLinkFlagArray[1] + tgkCase.optionArray[1] + 100 + tgkCase.rapidFireCountArray[1]);
                }
                if (tgkCase.optionArray[1] == 4) {
                    linkOptionWriteToJson(context, "" + TgkHelper.getTgkLinkKey(tgkCase.ID, tgkCase.state, 138), str, jSONObject, TgkDataContract.TgkEntry.TGK_CASE_R_LINK_OPTION);
                }
                if (tgkCase.rapidFireCountArray[2] == 5 || tgkCase.optionArray[2] != 6) {
                    jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION, tgkCase.setLinkFlagArray[2] + tgkCase.optionArray[2]);
                } else {
                    jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION, tgkCase.setLinkFlagArray[2] + tgkCase.optionArray[2] + 100 + tgkCase.rapidFireCountArray[2]);
                }
            }
            byte[] bitmapTobyte = TgkHelper.bitmapTobyte(tgkCase.picture);
            if (bitmapTobyte != null) {
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_PICTURE, Base64.encodeToString(bitmapTobyte, 0));
            }
            byte[] bitmapToByte = TgkFileHelper.bitmapToByte(context, tgkCase.shotPicture);
            if (bitmapToByte != null) {
                jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_SHOT_PICTURE, Base64.encodeToString(bitmapToByte, 0));
            }
            jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_KEY, tgkCase.uniqueId);
            jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_CHANGE, tgkCase.change);
            jSONObject.put(TgkDataContract.TgkEntry.TGK_CASE_UPDATE_TIME, tgkCase.updateTime);
            jSONObject.put(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE, tgkCase.getIsLandscape());
        } catch (JSONException e) {
            Log.i(TAG, "JSONException", e);
        }
        Uri writePolicyInfoToFile = writePolicyInfoToFile(context, j + "_" + i + "_" + str + ".json", jSONObject);
        context.grantUriPermission("cn.nubia.gamenotes", writePolicyInfoToFile, 1);
        if (writePolicyInfoToFile != null) {
            return writePolicyInfoToFile.toString();
        }
        return null;
    }

    public static ArrayList<TgkData> getTgkCases(Context context, String str) {
        ArrayList<TgkData> arrayList = new ArrayList<>();
        int i = 0;
        while (i < 2) {
            Uri uriByTableId = TgkHelper.getUriByTableId(i);
            if (uriByTableId != null) {
                Cursor query = context.getContentResolver().query(uriByTableId, null, "package_name = ?", new String[]{str}, i == 0 ? "_id ASC" : "_id DESC");
                if (query != null) {
                    if (query.getCount() > 0) {
                        while (query.moveToNext()) {
                            TgkData cursorToTgkDataPrecise = TgkHelper.cursorToTgkDataPrecise(query);
                            if (cursorToTgkDataPrecise != null) {
                                arrayList.add(cursorToTgkDataPrecise);
                            }
                        }
                    }
                    query.close();
                }
            }
            i++;
        }
        return arrayList;
    }

    public static Bundle getTgkEnables(Context context) {
        TgkData selectedCaseData;
        ArrayList<String> gamePackage = getGamePackage(context);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if (gamePackage != null && !gamePackage.isEmpty()) {
            Iterator<String> it = gamePackage.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!TextUtils.isEmpty(next)) {
                    String[] split = next.split(",");
                    boolean z = false;
                    String str = split.length > 1 ? split[0] : "";
                    if (!TextUtils.isEmpty(str)) {
                        TgkGameInfo tgkGameInfoNotApply = TgkHelper.getTgkGameInfoNotApply(context, str);
                        if (tgkGameInfoNotApply != null && (selectedCaseData = tgkGameInfoNotApply.getSelectedCaseData()) != null) {
                            z = selectedCaseData.mainSw;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("packageName", str);
                        bundle.putBoolean("isEnabled", z);
                        arrayList.add(bundle);
                    }
                }
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList("tgk_enable_list", arrayList);
        return bundle2;
    }

    private static String getZeroText(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append('0');
        }
        return sb.toString();
    }

    public static boolean havaSameUniqueId(Context context, String str, String str2) {
        ArrayList<TgkData> tgkCases = getTgkCases(context, str);
        if (tgkCases.size() <= 0) {
            return false;
        }
        Iterator<TgkData> it = tgkCases.iterator();
        while (it.hasNext()) {
            TgkData next = it.next();
            Log.d(TAG, "havaSameUniqueId uniqueId=" + str2 + ";tgkData.uniqueId=" + next.uniqueId);
            if (next != null && !TextUtils.isEmpty(next.uniqueId) && next.uniqueId.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean haveSamePolicyName(Context context, String str, String str2) {
        ArrayList<TgkData> tgkCases = getTgkCases(context, str);
        if (tgkCases.size() <= 0) {
            return false;
        }
        Iterator<TgkData> it = tgkCases.iterator();
        while (it.hasNext()) {
            TgkData next = it.next();
            if (next != null && !TextUtils.isEmpty(next.showName) && next.showName.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static void importMacro(Context context, String str, String str2, JSONObject jSONObject, String str3, int i, int i2) {
        byte[] decode;
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str3)) {
                    String string = jSONObject.getString(str3);
                    if (TextUtils.isEmpty(string) || (decode = Base64.decode(string, 0)) == null || decode.length <= 0) {
                        return;
                    }
                    Log.i(TAG, "importMacro linkFileBytes.length : " + decode.length);
                    String byteToUri = byteToUri(context, decode, "link_option.zip");
                    if (TextUtils.isEmpty(byteToUri)) {
                        return;
                    }
                    Log.i(TAG, "importMacro fileUri : " + byteToUri);
                    Bundle bundle = new Bundle();
                    bundle.putString("packageName", str2);
                    bundle.putString("fileUri", byteToUri);
                    bundle.putInt("screenWidth", i);
                    bundle.putInt("screenHeight", i2);
                    Bundle call = context.getContentResolver().call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "import_motion", context.getPackageName(), bundle);
                    if (call != null) {
                        Log.i(TAG, "result.getInt(\"resultCode\") " + call.getInt("resultCode"));
                        if (call.getInt("resultCode") == 1) {
                            int i3 = call.getInt("_id");
                            Log.i(TAG, "new macro id : " + i3);
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("touch_key_name", "" + str);
                            bundle2.putInt("_id", i3);
                            context.getContentResolver().call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "touch_key_call", "touch_link_motion_bg", bundle2);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "importMacro e=" + e.getMessage());
            }
        }
    }

    public static boolean isAndroidT() {
        Log.i(TAG, "Build.VERSION.SDK_INT=" + Build.VERSION.SDK_INT);
        return Build.VERSION.SDK_INT == 33;
    }

    public static boolean isSafePathName(String str) {
        if (str.contains("../") || str.contains("./") || str.contains("~/")) {
            return false;
        }
        return str.startsWith("/storage") || str.startsWith("/data") || str.startsWith("file:") || str.startsWith("content:");
    }

    private static boolean isStrategyPixelAvailable(Context context, int i, int i2) {
        getScreenSize(context);
        if (i <= i2) {
            i = i2;
        }
        if (i <= i2) {
            i2 = i;
        }
        if (mScreenWidth == i && mScreenHeight == i2) {
            return true;
        }
        Log.i(TAG, "isStrategyPixelAvailable return false localScreenWidth : " + i2 + " screenHeight : " + i + " screenWidth : " + mScreenWidth + " mScreenHeight : " + mScreenHeight);
        return false;
    }

    public static void linkOptionWriteToJson(Context context, String str, String str2, JSONObject jSONObject, String str3) {
        byte[] uriToByteArray;
        try {
            Log.i(TAG, "linkOptionWriteToJson jsonObjectKey : " + str3);
            Bundle bundle = new Bundle();
            bundle.putString("touch_key_name", "" + str);
            bundle.putString("packageName", str2);
            Bundle call = context.getContentResolver().call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "export_motion", context.getPackageName(), bundle);
            if (call != null) {
                String string = call.getString("sharedUri");
                Log.i(TAG, "linkOptionWriteToJson sharedFileUri : " + string);
                if (TextUtils.isEmpty(string) || (uriToByteArray = uriToByteArray(context, string)) == null || uriToByteArray.length <= 0 || jSONObject == null) {
                    return;
                }
                jSONObject.put(str3, Base64.encodeToString(uriToByteArray, 0));
            }
        } catch (JSONException e) {
            Log.i(TAG, "JSONException", e);
        }
    }

    private static TgkData parseTgkDataInfoFromJson(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        TgkData tgkData = new TgkData();
        try {
            tgkData.ID = jSONObject.getLong("_id");
            tgkData.originalName = jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_ORG_NAME);
            tgkData.showName = jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME);
            tgkData.packageName = jSONObject.getString("package_name");
            tgkData.mainSw = jSONObject.getBoolean(TgkDataContract.TgkEntry.TGK_CASE_MAIN_SW);
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_L_SW)) {
                tgkData.optionSwArray[0] = jSONObject.getBoolean(TgkDataContract.TgkEntry.TGK_CASE_L_SW);
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_R_SW)) {
                tgkData.optionSwArray[1] = jSONObject.getBoolean(TgkDataContract.TgkEntry.TGK_CASE_R_SW);
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_M_SW)) {
                tgkData.optionSwArray[2] = jSONObject.getBoolean(TgkDataContract.TgkEntry.TGK_CASE_M_SW);
            }
            tgkData.vibrateSw = jSONObject.getBoolean(TgkDataContract.TgkEntry.TGK_CASE_VIBRATE_SW);
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY)) {
                tgkData.sensitivityArray[0] = jSONObject.getInt(TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY);
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY)) {
                tgkData.sensitivityArray[1] = jSONObject.getInt(TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY);
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_L_POINTS)) {
                tgkData.pointsArray[0] = TgkHelper.stringToRect(jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_L_POINTS));
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_R_POINTS)) {
                tgkData.pointsArray[1] = TgkHelper.stringToRect(jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_R_POINTS));
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_M_POINTS)) {
                tgkData.pointsArray[2] = TgkHelper.stringToRect(jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_M_POINTS));
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION)) {
                TgkHelper.getOptionData(tgkData, jSONObject.getInt(TgkDataContract.TgkEntry.TGK_CASE_L_OPTION), 0);
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION)) {
                TgkHelper.getOptionData(tgkData, jSONObject.getInt(TgkDataContract.TgkEntry.TGK_CASE_R_OPTION), 1);
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION)) {
                TgkHelper.getOptionData(tgkData, jSONObject.getInt(TgkDataContract.TgkEntry.TGK_CASE_M_OPTION), 2);
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_PICTURE)) {
                String string = jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_PICTURE);
                if (!TextUtils.isEmpty(string)) {
                    tgkData.picture = TgkHelper.byteToBitmap(Base64.decode(string, 0));
                }
            }
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_CASE_SHOT_PICTURE)) {
                tgkData.shotPicture = TgkFileHelper.writeBitmapEncodeToFile(context, jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_SHOT_PICTURE));
            }
            tgkData.uniqueId = jSONObject.getString(TgkDataContract.TgkEntry.TGK_CASE_KEY);
            tgkData.change = jSONObject.getInt(TgkDataContract.TgkEntry.TGK_CASE_CHANGE);
            tgkData.updateTime = System.currentTimeMillis();
            if (jSONObject.has(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE)) {
                tgkData.setIsLandscape(jSONObject.getInt(TgkDataContract.TgkEntry.TGK_IS_LANDSCAPE));
            }
            int tgkDisableOpt = TgkHelper.getTgkDisableOpt(context.getContentResolver(), tgkData.packageName);
            int[] iArr = {1, 2, 4, 8, 16, 32, 64};
            for (int i = 0; i < 7; i++) {
                if ((iArr[i] & tgkDisableOpt) > 0) {
                    if (i == tgkData.optionArray[0]) {
                        tgkData.optionArray[0] = 0;
                    }
                    if (i == tgkData.optionArray[1]) {
                        tgkData.optionArray[1] = 0;
                    }
                    int i2 = tgkData.optionArray[2];
                    if (i == i2) {
                        tgkData.optionArray[2] = 0;
                    } else if (1 == i && i2 == 8) {
                        tgkData.optionArray[2] = 7;
                    }
                }
            }
            return tgkData;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFileToString(Context context, String str) throws IOException {
        Log.d(TAG, "readFileToString fileUri : " + str);
        if (!isSafePathName(str)) {
            return "";
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getContentResolver().openInputStream(Uri.parse(str))));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                bufferedReader.close();
                return sb.toString();
            }
            sb.append(readLine);
        }
    }

    public static String renameDupliName(String str) {
        String str2;
        String str3 = str + "_001";
        if (str.contains("_")) {
            String substring = str.substring(str.lastIndexOf("_") + 1, str.length());
            if (TextUtils.isEmpty(substring)) {
                Log.i(TAG, "renameDupliName tailNum is empty ");
                str2 = str + HighLightsUtils.AUTO_FIRST;
            } else if (substring.matches("[0-9]+")) {
                str2 = str.substring(0, str.lastIndexOf("_")) + "_" + new DecimalFormat(getZeroText(substring.length())).format(Integer.valueOf(substring).intValue() + 1);
            } else {
                Log.i(TAG, "renameDupliName tailNum is not match number ");
                str2 = str + "_001";
            }
        } else {
            str2 = str + "_001";
        }
        Log.d(TAG, "renameDupliName name : " + str + " newName : " + str2);
        return str2;
    }

    public static byte[] uriToByteArray(Context context, String str) {
        try {
            Log.d(TAG, "uriToByteArray sharedFileUri : " + str);
            if (!isSafePathName(str)) {
                return null;
            }
            File filesDir = context.getFilesDir();
            if (!filesDir.exists()) {
                filesDir.mkdir();
            }
            File file = new File(filesDir, "tgk");
            if (!file.exists()) {
                file.mkdir();
            }
            File file2 = new File(file, "link_option.zip");
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            if (!file2.exists()) {
                return null;
            }
            Log.d(TAG, "uriToByteArray outFile : " + file2.getAbsolutePath());
            try {
                InputStream openInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                openInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int length = (int) file2.length();
            byte[] bArr2 = new byte[length];
            try {
                FileInputStream fileInputStream = new FileInputStream(file2);
                try {
                    fileInputStream.read(bArr2);
                    fileInputStream.close();
                } finally {
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            Log.d(TAG, "uriToByteArray fileContent : " + length);
            return bArr2;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static Uri writePolicyInfoToFile(Context context, String str, JSONObject jSONObject) {
        File filesDir = context.getFilesDir();
        if (!filesDir.exists()) {
            filesDir.mkdir();
        }
        File file = new File(filesDir, "shared");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, str);
        if (file2.exists()) {
            file2.delete();
        }
        File file3 = new File(file, str);
        try {
            FileWriter fileWriter = new FileWriter(file3);
            fileWriter.write(jSONObject.toString());
            fileWriter.close();
        } catch (IOException e) {
            Log.e(TAG, "write sharedFile exception ", e);
        }
        Uri uriForFile = FileProvider.getUriForFile(context, FileProvider.AUTHORITY, file3);
        Log.i(TAG, "sharedFileUri = " + uriForFile);
        return uriForFile;
    }
}
