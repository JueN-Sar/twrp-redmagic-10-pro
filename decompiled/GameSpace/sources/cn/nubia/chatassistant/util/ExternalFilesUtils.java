package cn.nubia.chatassistant.util;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import cn.nubia.chatassistant.bean.Song;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ExternalFilesUtils {
    private static final String TAG = "ExternalFilesUtils";

    public static List<Song> getExternalFilesMusic(Context context) {
        ArrayList arrayList = new ArrayList();
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        LogUtils.d(TAG, "getExternalFilesDirfile");
        String[] list = externalFilesDir.list();
        int length = list.length;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        int i = 0;
        while (i < length) {
            String str = list[i];
            try {
                MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                try {
                    if (str.startsWith("noRename") || str.equals("RawAudio.raw")) {
                        try {
                            mediaMetadataRetriever2.release();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        File file = new File(externalFilesDir, str);
                        Song song = new Song();
                        song.setName(str.substring(0, str.lastIndexOf(".")));
                        song.setPath(file.getAbsolutePath());
                        mediaMetadataRetriever2.setDataSource(file.getAbsolutePath());
                        String extractMetadata = mediaMetadataRetriever2.extractMetadata(9);
                        song.setDuration(Integer.valueOf(extractMetadata).intValue());
                        song.setSize(file.length());
                        song.setTime(String.valueOf(file.lastModified() / 1000));
                        if (2000 <= Integer.valueOf(extractMetadata).intValue() && Integer.valueOf(extractMetadata).intValue() <= 10240) {
                            arrayList.add(song);
                        }
                        try {
                            mediaMetadataRetriever2.release();
                        } catch (IOException e2) {
                            throw new RuntimeException(e2);
                        }
                    }
                    i++;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                } catch (Throwable th) {
                    th = th;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    if (mediaMetadataRetriever != null) {
                        try {
                            mediaMetadataRetriever.release();
                        } catch (IOException e3) {
                            throw new RuntimeException(e3);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return arrayList;
    }
}
