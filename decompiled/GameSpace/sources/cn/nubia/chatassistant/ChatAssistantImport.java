package cn.nubia.chatassistant;

import android.content.Context;
import cn.nubia.chatassistant.util.LogUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class ChatAssistantImport {
    public static final String SW_OFF = "0";
    public static final String SW_ON = "1";
    public static final String TAG = "ChatAssistantImport";
    private static String mXmlPath;

    public static boolean deleteDir(Context context, String str) {
        LogUtils.d(TAG, "deleteDir dirName=" + str);
        File file = new File(context.getFilesDir().getPath() + "/chat_assistant");
        boolean z = true;
        if (file.listFiles() != null) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                File file2 = listFiles[i];
                String[] split = file2.getName().split("_", 2);
                if (split.length > 1 && split[1].equals(str)) {
                    deleteFolder(file2);
                    break;
                }
                i++;
            }
        }
        LogUtils.d(TAG, "deleteDir ret=" + z);
        return z;
    }

    private static void deleteFolder(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    deleteFolder(file2);
                } else {
                    file2.delete();
                }
            }
        }
        file.delete();
    }

    public static File getRealFileName(String str, String str2) {
        LogUtils.i(TAG, "getRealFileName file name:" + str2 + "  ;;baseDir : " + str);
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
        LogUtils.i(TAG, " getRealFileName ret :  " + file.getAbsolutePath());
        return file;
    }

    public static boolean isDirExist(Context context, String str) {
        LogUtils.d(TAG, "isDirExist dirName=" + str);
        File file = new File(context.getFilesDir().getPath() + "/chat_assistant");
        boolean z = false;
        if (file.listFiles() != null) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String[] split = listFiles[i].getName().split("_", 2);
                if (split.length > 1 && split[1].equals(str)) {
                    z = true;
                    break;
                }
                i++;
            }
        }
        LogUtils.d(TAG, "isDirExist ret=" + z);
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int parserChatAssistantDataFile(android.content.Context r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.chatassistant.ChatAssistantImport.parserChatAssistantDataFile(android.content.Context, java.lang.String):int");
    }

    private static int unzipFile(String str, String str2) {
        int i;
        ZipFile zipFile;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        File file = new File(str);
        int i2 = -1;
        try {
            zipFile = new ZipFile(file);
            i = 0;
        } catch (IOException e) {
            LogUtils.e(TAG, "new zipfile failed");
            e.printStackTrace();
            i = -1;
            zipFile = null;
        }
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
                    } catch (FileNotFoundException e2) {
                        LogUtils.e(TAG, "upZipFile new outputstream failed");
                        e2.printStackTrace();
                        i = -1;
                        bufferedOutputStream = null;
                    }
                    try {
                        bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(nextElement));
                    } catch (IOException e3) {
                        LogUtils.e(TAG, "upZipFile new inputstream failed");
                        e3.printStackTrace();
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
                            } catch (IOException e4) {
                                LogUtils.e(TAG, "upZipFile write ... failed");
                                e4.printStackTrace();
                                i = -1;
                            }
                        }
                        bufferedInputStream.close();
                        bufferedOutputStream.close();
                    }
                    realFileName.setWritable(true, false);
                    realFileName.setReadable(true, false);
                    mXmlPath = realFileName.getParentFile() != null ? realFileName.getParentFile().getPath() : "";
                    LogUtils.d(TAG, "unzipFile: outFile.path : " + realFileName.getParentFile().getPath());
                }
            }
            try {
                zipFile.close();
            } catch (IOException e5) {
                LogUtils.e(TAG, "upZipFile zfile close failed");
                e5.printStackTrace();
            }
        }
        i2 = i;
        file.delete();
        return i2;
    }
}
