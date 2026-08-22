package cn.nubia.chatassistant.util;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import java.io.File;

/* loaded from: classes.dex */
public class FileUtils {
    public static void deleteNoRenameFile(final Context context, final String str) {
        try {
            AsyncTask.execute(new Runnable() { // from class: cn.nubia.chatassistant.util.FileUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
                    for (String str2 : externalFilesDir.list()) {
                        if (str2.startsWith(str)) {
                            File file = new File(externalFilesDir.getAbsolutePath() + "/" + str2);
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
