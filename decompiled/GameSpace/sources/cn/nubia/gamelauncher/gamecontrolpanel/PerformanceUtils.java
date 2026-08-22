package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.gamecontrolpanel.TouchOperationBean;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.TouchOperationHelper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class PerformanceUtils {
    private static final String GYRO_ENABLE = "/sys/class/sensors_sensitivity/gyro/gyro_enable";
    private static final String GYRO_SEN_WHITE_LIST = "gyro_sen_white_list";
    private static final String GYRO_SEN_X = "/sys/class/sensors_sensitivity/gyro/gyro_x";
    private static final String GYRO_SEN_Y = "/sys/class/sensors_sensitivity/gyro/gyro_y";
    private static final String TAG = "PerformanceUtils";
    public static List<String> mGyroSenAppList = Arrays.asList("com.tencent.tmgp.pubgm", HighLightsUtils.CJZC_PACKAGE_NAME, "com.tencent.tmgp.projectg", HighLightsUtils.PUBG_PACKAGE_NAME, "com.tencent.iglite", "com.tencent.igce", "com.rekoo.pubgm", "com.pubg.krmobile", "com.vng.pubgmobile", "com.tencent.tmgp.cf", "com.tencent.tmgp.cfmnac", "com.tencent.tmgp.cfm.google", "com.tencent.tmgp.af", "com.tencent.af", "com.tencent.mf.uam", "com.tencent.tmgp.dfm", "com.netease.hyxd", "com.netease.hyxd.baidu", "com.netease.hyxd.aligames", "com.netease.hyxd.yixin", "com.netease.hyxd.nubia", "com.netease.ko", "com.netease.lztg", "com.netease.lztg.aligames", "com.netease.lztg.baidu", "com.netease.lztg.yixin", "com.netease.lztg.nubia", "com.tencent.tmgp.yongyong.lztg", "com.netease.zjz", "com.netease.zjz.nubia", "com.netease.zjz.yyxx.sougou", "com.netease.chiji", "com.sofunny.Sausage", "com.sofunny.Chicken", "com.netease.wptqz", "com.netease.g93na", "com.netease.jddsaef", "com.netease.jddsaef.nubia", HighLightsUtils.YS_PACKAGE_NAME);
    public static List<String> mSharpenDisplayAppList = Arrays.asList(HighLightsUtils.CJZC_PACKAGE_NAME, "com.tencent.tmgp.projectg", HighLightsUtils.PUBG_PACKAGE_NAME, "com.tencent.igce", "com.tencent.iglite", "com.pubg.krmobile", "com.vng.pubgmobile", "com.rekoo.pubgm");
    private static final List<String> mDTSXULTRAAppNameList = Arrays.asList(HighLightsUtils.CJZC_PACKAGE_NAME, "com.tencent.mf.uam", "com.tencent.tmgp.cf", HighLightsUtils.SMZH_PACKAGE_NAME, "com.tencent.tmgp.gnyx");

    /* renamed from: cn.nubia.gamelauncher.gamecontrolpanel.PerformanceUtils$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$cn$nubia$gamelauncher$gamecontrolpanel$TouchOperationBean$OperationTypeParams;

        static {
            int[] iArr = new int[TouchOperationBean.OperationTypeParams.values().length];
            $SwitchMap$cn$nubia$gamelauncher$gamecontrolpanel$TouchOperationBean$OperationTypeParams = iArr;
            try {
                iArr[TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cn$nubia$gamelauncher$gamecontrolpanel$TouchOperationBean$OperationTypeParams[TouchOperationBean.OperationTypeParams.TOUCH_SEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cn$nubia$gamelauncher$gamecontrolpanel$TouchOperationBean$OperationTypeParams[TouchOperationBean.OperationTypeParams.TOUCH_FOLLOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cn$nubia$gamelauncher$gamecontrolpanel$TouchOperationBean$OperationTypeParams[TouchOperationBean.OperationTypeParams.TOUCH_MICRO_SENSITIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$cn$nubia$gamelauncher$gamecontrolpanel$TouchOperationBean$OperationTypeParams[TouchOperationBean.OperationTypeParams.GYROSEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static void cleanOperationParamFromDB(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.i(TAG, "******* cleanOperationParamFromDB return pkgname is null");
            return;
        }
        cleanOperationParamFromDB(context, str, TouchOperationBean.OperationTypeParams.TOUCH_FOLLOW.getDBFieldName());
        cleanOperationParamFromDB(context, str, TouchOperationBean.OperationTypeParams.TOUCH_SEN.getDBFieldName());
        cleanOperationParamFromDB(context, str, TouchOperationBean.OperationTypeParams.TOUCH_SAMPLE.getDBFieldName());
        cleanOperationParamFromDB(context, str, TouchOperationBean.OperationTypeParams.TOUCH_PROTECTION.getDBFieldName());
        cleanOperationParamFromDB(context, str, TouchOperationBean.OperationTypeParams.TOUCH_MICRO_SENSITIVE.getDBFieldName());
        cleanOperationParamFromDB(context, str, TouchOperationBean.OperationTypeParams.GYROSEN.getDBFieldName());
    }

    private static void cleanOperationParamFromDB(Context context, String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            LogUtil.i(TAG, "******* cleanOperationParamFromDB DBName is null return");
            return;
        }
        String string = Settings.Global.getString(context.getContentResolver(), str2);
        LogUtil.i(TAG, "******* cleanOperationParamFromDB :" + str2 + " pkgName : " + str + " orgDBvalue: " + string);
        if (string != null && string.indexOf(str) != -1) {
            for (String str4 : string.split(",")) {
                str3 = str4.trim();
                if (!str3.isEmpty() && str3.indexOf(str) != -1) {
                    break;
                }
            }
        }
        str3 = null;
        if (TextUtils.isEmpty(str3)) {
            LogUtil.i(TAG, "******* cleanOperationParamFromDB no info in db " + str2 + "  return");
            return;
        }
        try {
            String replace = string.replace(str3 + ",", "");
            LogUtil.i(TAG, "******* cleanOperationParamFromDB:" + str2 + " cleandata: " + str3 + " result : " + replace);
            Settings.Global.putString(context.getContentResolver(), str2, replace);
        } catch (Exception e) {
            LogUtil.e(TAG, "******cleanOperationParamFromDB  Exception !!" + e.toString());
        }
    }

    public static void enableGyroSenFun(final boolean z, final int[] iArr, Handler handler) {
        if (handler == null) {
            LogUtil.i(TAG, "******* enableGyroSenFun workhandler is null !");
        } else {
            handler.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.PerformanceUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    PerformanceUtils.writeNodeValue(PerformanceUtils.GYRO_ENABLE, z ? 1 : 0);
                    int[] iArr2 = iArr;
                    if (iArr2 == null || 2 != iArr2.length) {
                        return;
                    }
                    PerformanceUtils.writeGyroSenNode(iArr2[0], iArr2[1]);
                }
            });
        }
    }

    public static int[] getOperationParamFromDB(Context context, String str, TouchOperationBean.OperationTypeParams operationTypeParams) {
        String str2;
        if (TextUtils.isEmpty(str) || operationTypeParams == null) {
            LogUtil.i(TAG, "******* getOperationParamFromDB return null" + str + " typeParams " + operationTypeParams);
            operationTypeParams.reset();
            return null;
        }
        int[] iArr = new int[0];
        int i = AnonymousClass2.$SwitchMap$cn$nubia$gamelauncher$gamecontrolpanel$TouchOperationBean$OperationTypeParams[operationTypeParams.ordinal()];
        if (i == 1) {
            iArr = TouchOperationHelper.getTouchSampleRateDefaultValue();
        } else if (i == 2) {
            iArr = TouchOperationHelper.getTouchSenDefaultValue();
        } else if (i == 3) {
            iArr = TouchOperationHelper.getTouchFollowDefaultValue();
        } else if (i == 4) {
            iArr = TouchOperationHelper.getTouchMicroSensitiveDefaultValue();
        } else if (i == 5) {
            iArr = TouchOperationHelper.getGyroSenDefaultValue();
        }
        String string = Settings.Global.getString(context.getContentResolver(), operationTypeParams.getDBFieldName());
        LogUtil.i(TAG, "******* package: " + str + " readProviderData " + operationTypeParams + " value =" + string);
        if (string != null && string.indexOf(str) != -1) {
            for (String str3 : string.split(",")) {
                str2 = str3.trim();
                if (!str2.isEmpty() && str2.indexOf(str) != -1) {
                    break;
                }
            }
        }
        str2 = null;
        if (TextUtils.isEmpty(str2)) {
            LogUtil.i(TAG, "******* oprationValueInfo is null return default");
            operationTypeParams.reset();
            return iArr;
        }
        try {
            int[] parserParamFromDB = parserParamFromDB(str2.substring(str2.indexOf("+") + 1, str2.length()), iArr);
            LogUtil.i(TAG, "getOperationParamFromDB typeParams : " + operationTypeParams + " return : " + Arrays.toString(parserParamFromDB));
            operationTypeParams.setValue(parserParamFromDB);
            return parserParamFromDB;
        } catch (Exception e) {
            LogUtil.e(TAG, "******getOperationParamFromDB  Exception !!" + e.toString());
            return null;
        }
    }

    public static boolean hasGyroSenFun(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return mGyroSenAppList.contains(str);
    }

    public static boolean isSupportGyroSen(Context context) {
        return 1 == Settings.Global.getInt(context.getContentResolver(), "isSupportGyroSen", 0);
    }

    public static int[] parseIntGyroSenValue(String str) {
        try {
            int indexOf = str.indexOf("&");
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 1, str.length());
            int parseInt = Integer.parseInt(substring);
            int parseInt2 = Integer.parseInt(substring2);
            LogUtil.d(TAG, "parseIntGyroSenValue, sen_x = " + parseInt + ", sen_y = " + parseInt2);
            return new int[]{parseInt, parseInt2};
        } catch (Exception e) {
            LogUtil.e(TAG, "parseIntGyroSenValue: " + e);
            return null;
        }
    }

    private static int[] parserParamFromDB(String str, int[] iArr) {
        if (TextUtils.isEmpty(str)) {
            return iArr;
        }
        try {
            if (str.indexOf("&") == -1) {
                return new int[]{Integer.parseInt(str)};
            }
            String[] split = str.split("&");
            if (split.length <= 0) {
                return iArr;
            }
            int[] iArr2 = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                iArr2[i] = Integer.parseInt(split[i]);
            }
            return iArr2;
        } catch (Exception e) {
            LogUtil.e(TAG, "parserParamFromDB error " + e.toString());
            return iArr;
        }
    }

    public static void saveOperationParamToDB(Context context, String str, TouchOperationBean.OperationTypeParams operationTypeParams, Handler handler) {
        if (TextUtils.isEmpty(str) || operationTypeParams == null) {
            LogUtil.i(TAG, "******* saveOperationParamToDB pkgname is null");
            return;
        }
        int[] value = operationTypeParams.getValue();
        if (value == null || value.length <= 0) {
            LogUtil.i(TAG, "******* saveOperationParamToDB currentValue is null");
            return;
        }
        int i = 0;
        String str2 = "";
        int i2 = 0;
        while (i2 < value.length) {
            str2 = i2 == 0 ? value[0] + "" : str2 + "&" + value[i2];
            i2++;
        }
        String string = Settings.Global.getString(context.getContentResolver(), operationTypeParams.getDBFieldName());
        LogUtil.i(TAG, "saveOperationParamToDB params = " + operationTypeParams.toString() + " currentUIValue " + Arrays.toString(value) + " toSaveValue : " + str2 + " currentProviderValue :" + string);
        if (!TextUtils.isEmpty(string) && string.contains(str)) {
            String[] split = string.split(",");
            int length = split.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str3 = split[i];
                if (!TextUtils.isEmpty(str3) && str3.contains(str)) {
                    string = string.replace(str3, str + "+" + str2);
                    break;
                }
                i++;
            }
        } else {
            string = TextUtils.isEmpty(string) ? str + "+" + str2 + "," : string + str + "+" + str2 + ",";
        }
        LogUtil.i(TAG, "saveOperationParamToDB pkgname : " + str + " parms: " + str2 + " dbvalue : " + string);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        Settings.Global.putString(context.getContentResolver(), operationTypeParams.getDBFieldName(), string);
    }

    public static boolean supportDTSXULTR(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return mDTSXULTRAAppNameList.contains(str);
    }

    public static boolean supportSharpenDisplay(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return mSharpenDisplayAppList.contains(str);
    }

    public static void updateGyroSenAppList(List<String> list) {
        LogUtil.d(TAG, "updateGyroSenAppList");
        mGyroSenAppList = list;
    }

    public static void updateGyroSensorWhiteList(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        Settings.Global.putString(context.getContentResolver(), GYRO_SEN_WHITE_LIST, mGyroSenAppList.toString());
    }

    public static void updateSharpenDisplayAppList(List<String> list) {
        LogUtil.d(TAG, "updateSharpenDisplayAppList");
        mSharpenDisplayAppList = list;
    }

    public static void writeGyroSenNode(int i, int i2) {
        writeNodeValue(GYRO_SEN_X, i);
        writeNodeValue(GYRO_SEN_Y, i2);
    }

    public static void writeNodeValue(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.i(TAG, "writeNodeValue path is null !");
            return;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            bufferedWriter.write(String.valueOf(i));
            bufferedWriter.flush();
            bufferedWriter.close();
            LogUtil.i(TAG, "writeNodeValue:" + i);
        } catch (IOException e) {
            LogUtil.e(TAG, "writeNodeValue Exception:" + e.toString());
        }
    }
}
