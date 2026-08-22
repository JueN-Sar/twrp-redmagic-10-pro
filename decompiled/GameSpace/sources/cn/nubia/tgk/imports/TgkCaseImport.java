package cn.nubia.tgk.imports;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Xml;
import cn.nubia.tgk.TgkHelper;
import cn.nubia.tgk.data.TgkData;
import cn.nubia.tgk.data.TgkDataContract;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public class TgkCaseImport {
    public static final String SW_OFF = "0";
    public static final String SW_ON = "1";
    public static final String TAG = "ImportTgkData";
    private static String mPackageName = "";
    private static String mXmlPath;

    private static Bitmap getPreviewImg(String str) {
        FileInputStream fileInputStream;
        Log.d(TAG, "in  picTobyte");
        Bitmap bitmap = null;
        try {
            fileInputStream = new FileInputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        if (fileInputStream != null) {
            Log.d(TAG, "in  is not null");
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }

    public static File getRealFileName(String str, String str2) {
        Log.i(TAG, "getRealFileName file name:" + str2 + "  ;;baseDir : " + str);
        String[] split = str2.split("/");
        File file = new File(str);
        if (split.length >= 1) {
            int i = 0;
            while (i < split.length - 1) {
                File file2 = new File(file, new String(split[i].getBytes()));
                i++;
                file = file2;
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(file, new String(split[split.length - 1].getBytes()));
        }
        Log.i(TAG, " getRealFileName ret :  " + file.getAbsolutePath());
        return file;
    }

    private static boolean isTgkCaseCountLessMax(Context context, String str) {
        int tgkCasesCountStatic = TgkHelper.getTgkCasesCountStatic(context.getContentResolver(), str);
        if (tgkCasesCountStatic < 20) {
            return true;
        }
        Log.e(TAG, "isTgkCaseCountLessMax false count =" + tgkCasesCountStatic);
        return false;
    }

    public static TgkData parserFile(Context context, String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            return null;
        }
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(fileInputStream, "utf-8");
            TgkData tgkData = new TgkData();
            tgkData.state |= 8;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                String name = newPullParser.getName();
                if (eventType != 2) {
                    if (eventType == 3 && "tgk_case_info".equals(name)) {
                        Log.e(TAG, "parse end");
                    }
                } else if (!"tgk_case_info".equals(name)) {
                    if (TgkDataContract.TgkEntry.TGK_CASE_ORG_NAME.equals(name)) {
                        tgkData.originalName = newPullParser.nextText();
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME.equals(name)) {
                        tgkData.showName = newPullParser.nextText();
                    } else if ("package_name".equals(name)) {
                        String nextText = newPullParser.nextText();
                        mPackageName = nextText;
                        tgkData.packageName = nextText;
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_MAIN_SW.equals(name)) {
                        tgkData.mainSw = "1".equals(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_L_SW.equals(name)) {
                        tgkData.optionSwArray[0] = "1".equals(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_R_SW.equals(name)) {
                        tgkData.optionSwArray[1] = "1".equals(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_M_SW.equals(name)) {
                        tgkData.optionSwArray[2] = "1".equals(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_VIBRATE_SW.equals(name)) {
                        tgkData.vibrateSw = "1".equals(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_L_SENSITIVITY.equals(name)) {
                        tgkData.sensitivityArray[0] = Integer.valueOf(newPullParser.nextText()).intValue();
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_R_SENSITIVITY.equals(name)) {
                        tgkData.sensitivityArray[1] = Integer.valueOf(newPullParser.nextText()).intValue();
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_L_POINTS.equals(name)) {
                        tgkData.pointsArray[0] = TgkHelper.stringToRect(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_R_POINTS.equals(name)) {
                        tgkData.pointsArray[1] = TgkHelper.stringToRect(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_M_POINTS.equals(name)) {
                        tgkData.pointsArray[2] = TgkHelper.stringToRect(newPullParser.nextText());
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_L_OPTION.equals(name)) {
                        tgkData.optionArray[0] = Integer.valueOf(newPullParser.nextText()).intValue();
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_R_OPTION.equals(name)) {
                        tgkData.optionArray[1] = Integer.valueOf(newPullParser.nextText()).intValue();
                    } else if (TgkDataContract.TgkEntry.TGK_CASE_M_OPTION.equals(name)) {
                        tgkData.optionArray[2] = Integer.valueOf(newPullParser.nextText()).intValue();
                    }
                }
            }
            for (int i = 0; i < TgkHelper.TGK_COUNT; i++) {
                if (4 == tgkData.optionArray[i]) {
                    tgkData.optionSwArray[i] = false;
                    tgkData.optionArray[i] = 0;
                }
            }
            return tgkData;
        } catch (IOException unused) {
            Log.e(TAG, "parse failed IOException");
            return null;
        } catch (XmlPullParserException unused2) {
            Log.e(TAG, "parse failed XmlPullParserException");
            return null;
        }
    }

    public static int parserImportTgkDataFile(Context context, String str) {
        String substring = str.substring(0, str.lastIndexOf("/") + 1);
        str.substring(0, str.length() - 4);
        int unzipFile = unzipFile(str, substring);
        if (unzipFile == 0) {
            TgkData parserFile = parserFile(context, mXmlPath + "/tgk_case_info.xml");
            if (parserFile != null) {
                TgkData queryTgkCaseStatic = TgkHelper.queryTgkCaseStatic(context.getContentResolver(), 1, null, "original_name LIKE ? AND show_name LIKE ? AND package_name LIKE ?", new String[]{parserFile.originalName, parserFile.showName, parserFile.packageName}, null);
                if (queryTgkCaseStatic != null) {
                    parserFile.ID = queryTgkCaseStatic.ID;
                    parserFile.state = queryTgkCaseStatic.state;
                    parserFile.picture = getPreviewImg(mXmlPath + "/preview_img.png");
                    TgkHelper.updateTgkCaseAllData(context.getContentResolver(), 1, parserFile);
                } else if (isTgkCaseCountLessMax(context, parserFile.packageName)) {
                    parserFile.picture = getPreviewImg(mXmlPath + "/preview_img.png");
                    TgkHelper.insertImportTgkCase(context, parserFile);
                } else {
                    unzipFile = -2;
                }
            } else {
                unzipFile = -1;
            }
        }
        Log.e(TAG, "parserImportTgkDataFile return ret = " + unzipFile);
        return unzipFile;
    }

    public static String[] parserTgkDataFile(Context context, String str) {
        String[] strArr = {"-1", ""};
        strArr[0] = String.valueOf(parserImportTgkDataFile(context, str));
        strArr[1] = mPackageName;
        return strArr;
    }

    private static int unzipFile(String str, String str2) {
        int i;
        ZipFile zipFile;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        File file = new File(str);
        try {
            zipFile = new ZipFile(file);
        } catch (Exception e) {
            Log.e(TAG, "new zipfile failed");
            e.printStackTrace();
            try {
                zipFile = (ZipFile) ZipFile.class.getConstructor(File.class, Integer.TYPE, Boolean.TYPE).newInstance(file, 1, false);
            } catch (Exception e2) {
                Log.e(TAG, "new zipfile failed1");
                e2.printStackTrace();
                i = -1;
                zipFile = null;
            }
        }
        i = 0;
        if (zipFile != null) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            byte[] bArr = new byte[1024];
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                if (nextElement.isDirectory()) {
                    new File(new String((str2 + nextElement.getName()).getBytes())).mkdir();
                } else {
                    File realFileName = getRealFileName(str2, nextElement.getName());
                    try {
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(realFileName));
                    } catch (FileNotFoundException e3) {
                        Log.e(TAG, "upZipFile new outputstream failed");
                        e3.printStackTrace();
                        i = -1;
                        bufferedOutputStream = null;
                    }
                    try {
                        bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(nextElement));
                    } catch (IOException e4) {
                        Log.e(TAG, "upZipFile new inputstream failed");
                        e4.printStackTrace();
                        i = -1;
                        bufferedInputStream = null;
                    }
                    if (bufferedOutputStream != null && bufferedInputStream != null) {
                        while (true) {
                            try {
                                int read = bufferedInputStream.read(bArr, 0, 1024);
                                if (read == -1) {
                                    break;
                                }
                                bufferedOutputStream.write(bArr, 0, read);
                            } catch (IOException e5) {
                                Log.e(TAG, "upZipFile write ... failed");
                                e5.printStackTrace();
                                i = -1;
                            }
                        }
                        bufferedInputStream.close();
                        bufferedOutputStream.close();
                    }
                    realFileName.setWritable(true, false);
                    realFileName.setReadable(true, false);
                    mXmlPath = realFileName.getParentFile() != null ? realFileName.getParentFile().getPath() : "";
                    Log.d(TAG, "unzipFile: outFile.path : " + realFileName.getParentFile().getPath());
                }
            }
            try {
                zipFile.close();
            } catch (IOException e6) {
                Log.e(TAG, "upZipFile zfile close failed");
                e6.printStackTrace();
                return -1;
            }
        }
        return i;
    }
}
