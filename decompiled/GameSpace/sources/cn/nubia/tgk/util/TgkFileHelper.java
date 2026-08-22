package cn.nubia.tgk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.tgk.data.FileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class TgkFileHelper {
    public static final String PICTURE_FILE_NAME_SUFFIX = "_bg.jpg";
    public static final String PICTURE_FOLDER_FILE_NAME = "/tgk";
    private static final String TAG = "TGK_FileHelper";

    public static byte[] bitmapToByte(Context context, String str) {
        Log.i(TAG, "bitmapToByte uri=" + str);
        if (!TgkUtils.isSafePathName(str)) {
            return null;
        }
        Bitmap bitmapFromUri = getBitmapFromUri(context, str);
        if (bitmapFromUri == null) {
            Log.i(TAG, "bitmapToByte null == bitmap!");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapFromUri.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void createFile(Context context) {
        String str = context.getFilesDir().getPath() + PICTURE_FOLDER_FILE_NAME;
        File file = new File(str);
        if (file.exists()) {
            Log.d(TAG, "gamePadCaseDirFile.exists() gamePadCaseDir=" + str);
        } else {
            Log.d(TAG, "createFile");
            file.mkdir();
        }
    }

    public static void deletePreviewPictureFile(Context context, String str) {
        try {
            Log.i(TAG, "filePathUri = " + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String[] split = str.split("/tgk/");
            if (split.length == 2) {
                String str2 = context.getFilesDir().getPath() + PICTURE_FOLDER_FILE_NAME;
                if (new File(str2).exists()) {
                    Log.i(TAG, "strs[1] = " + split[1]);
                    File file = new File(str2, split[1]);
                    Log.i(TAG, "file.exists() = " + file.exists());
                    if (file.exists()) {
                        Log.i(TAG, "file.getAbsolutePath()" + file.getAbsolutePath());
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "deletePreviewPictureFile fail e=" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getBgPreviewPictureFilePath(Context context, String str) {
        Log.d(TAG, "getBgPreviewPictureFilePath start ");
        createFile(context);
        TgkUtils.getScreenSize(context);
        deletePreviewPictureFile(context, str);
        Log.d(TAG, "getBgPreviewPictureFilePath Screenshot start");
        Bitmap screenshot = getScreenshot(context);
        Log.d(TAG, "getBgPreviewPictureFilePath Screenshot end");
        if (screenshot == null) {
            return "";
        }
        String savePreviewPictureFile = savePreviewPictureFile(context, screenshot);
        if (TextUtils.isEmpty(savePreviewPictureFile)) {
            return "";
        }
        File file = new File(savePreviewPictureFile);
        if (!file.exists()) {
            return "";
        }
        Uri uriForFile = FileProvider.getUriForFile(context, FileProvider.AUTHORITY, file);
        context.grantUriPermission("cn.nubia.gamenotes", uriForFile, 1);
        context.grantUriPermission("cn.nubia.gamepad", uriForFile, 1);
        Log.i(TAG, "getBgPreviewPictureFilePath sharedFileUri = " + uriForFile);
        return uriForFile != null ? uriForFile.toString() : "";
    }

    public static Bitmap getBitmapFromUri(Context context, String str) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str)) {
            String[] split = str.split("/tgk/");
            if (split.length == 2) {
                String str2 = context.getFilesDir().getPath() + PICTURE_FOLDER_FILE_NAME;
                if (new File(str2).exists()) {
                    Log.i(TAG, "getBitmapFromUri strs[1] = " + split[1]);
                    File file = new File(str2, split[1]);
                    Log.i(TAG, "getBitmapFromUri file.exists() = " + file.exists());
                    if (file.exists()) {
                        Log.i(TAG, "file.getAbsolutePath()" + file.getAbsolutePath());
                        return BitmapFactory.decodeFile(file.getAbsolutePath());
                    }
                }
            }
        }
        return null;
    }

    public static Bitmap getScreenshot(Context context) {
        return TgkUtils.isAndroidT() ? getScreenshotT(context) : getScreenshotU(context);
    }

    public static Bitmap getScreenshotT(Context context) {
        try {
            Class<?> cls = Class.forName("android.view.SurfaceControl");
            Method declaredMethod = cls.getDeclaredMethod("nubiaScreenshot", Rect.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            Bitmap bitmap = (Bitmap) declaredMethod.invoke(cls, new Rect(0, 2480, 0, 1116), 0);
            Matrix matrix = new Matrix();
            matrix.setScale(0.5f, 0.5f);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception unused) {
            Log.d(TAG, "getScreenshot, fail!");
            return null;
        }
    }

    public static Bitmap getScreenshotU(Context context) {
        try {
            Class<?> cls = Class.forName("com.redmagic.game.gamepadoperation.GamepadScreenshot");
            HashMap hashMap = new HashMap();
            hashMap.put("display", context.getDisplay());
            hashMap.put(Atmosphere.TYPE_CROP, new Rect(0, 0, TgkUtils.mScreenWidth, TgkUtils.mScreenHeight));
            Method declaredMethod = cls.getDeclaredMethod("nubiaScreenshot", Map.class);
            declaredMethod.setAccessible(true);
            Bitmap bitmap = (Bitmap) declaredMethod.invoke(cls, hashMap);
            Matrix matrix = new Matrix();
            matrix.setScale(0.5f, 0.5f);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception unused) {
            Log.d(TAG, "getScreenshot, fail!");
            return null;
        }
    }

    public static String savePreviewPictureFile(Context context, Bitmap bitmap) {
        try {
            Log.d(TAG, "savePreviewPictureFile  start");
            File file = new File(context.getFilesDir().getPath() + PICTURE_FOLDER_FILE_NAME);
            if (!file.exists()) {
                return "";
            }
            File file2 = new File(file, System.currentTimeMillis() + PICTURE_FILE_NAME_SUFFIX);
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file2.getPath();
        } catch (Exception e) {
            Log.d(TAG, "savePreviewPictureFile fail");
            e.printStackTrace();
            return "";
        }
    }

    public static String writeBitmapEncodeToFile(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            File file = new File(context.getFilesDir().getPath() + PICTURE_FOLDER_FILE_NAME);
            if (!file.exists()) {
                file.mkdir();
            }
            if (!file.exists()) {
                return "";
            }
            File file2 = new File(file, System.currentTimeMillis() + PICTURE_FILE_NAME_SUFFIX);
            file2.createNewFile();
            if (!file2.exists()) {
                return "";
            }
            writeBytesToFile(Base64.decode(str, 0), file2.getAbsolutePath());
            Uri uriForFile = FileProvider.getUriForFile(context, FileProvider.AUTHORITY, file2);
            context.grantUriPermission("cn.nubia.gamenotes", uriForFile, 1);
            context.grantUriPermission("cn.nubia.gamepad", uriForFile, 1);
            Log.i(TAG, "writeBitmapEncodeToFile sharedFileUri = " + uriForFile);
            return uriForFile != null ? uriForFile.toString() : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void writeBytesToFile(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(str);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    throw new RuntimeException(e4);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    throw new RuntimeException(e5);
                }
            }
            throw th;
        }
    }
}
