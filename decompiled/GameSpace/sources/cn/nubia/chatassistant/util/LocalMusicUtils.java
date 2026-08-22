package cn.nubia.chatassistant.util;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import cn.nubia.chatassistant.bean.Song;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class LocalMusicUtils {
    private static final String TAG = "LocalMusicUtils";
    private static long albumId;
    private static int duration;
    private static long id;
    public static List<Song> list;
    private static String name;
    private static String path;
    private static String singer;
    private static long size;
    public static Song song;
    private static String time;
    private String fileName;
    private String filePath;
    private volatile MediaRecorder mMediaRecorder;
    private OnDecibelListener onDecibelListener;
    private OnMediaListener onMediaListener;
    private final Handler mHandler = new Handler();
    private Runnable mUpdateMicStatusTimer = new Runnable() { // from class: cn.nubia.chatassistant.util.LocalMusicUtils.2
        @Override // java.lang.Runnable
        public void run() {
            LocalMusicUtils.this.updateMicStatus();
        }
    };
    private int BASE = 1;
    private int SPACE = 100;

    public interface OnDecibelListener {
        void onRecordDecibelValue(double d);
    }

    public interface OnMediaListener {
        void onStopRecord(String str, String str2);
    }

    public static List<Song> getMusic(Context context) {
        list = new ArrayList();
        Cursor query = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, "title_key");
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    song = new Song();
                    name = query.getString(query.getColumnIndexOrThrow("_display_name"));
                    id = query.getLong(query.getColumnIndexOrThrow("_id"));
                    singer = query.getString(query.getColumnIndexOrThrow("artist"));
                    path = query.getString(query.getColumnIndexOrThrow("_data"));
                    duration = query.getInt(query.getColumnIndexOrThrow("duration"));
                    time = String.valueOf(query.getInt(query.getColumnIndexOrThrow("date_modified")));
                    size = query.getLong(query.getColumnIndexOrThrow("_size"));
                    albumId = query.getLong(query.getColumnIndexOrThrow("album_id"));
                    Song song2 = song;
                    String str = name;
                    song2.setName(str.substring(0, str.lastIndexOf(".")));
                    song.setSinger(singer);
                    song.setPath(path);
                    song.setDuration(duration);
                    song.setSize(size);
                    song.setId(id);
                    song.setTime(time);
                    song.setAlbumId(albumId);
                    int i = duration;
                    if (2000 <= i && i <= 10240) {
                        list.add(song);
                    }
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            }
        }
        LogUtils.i(TAG, "song size : " + list.size());
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMicStatus() {
        if (this.mMediaRecorder != null) {
            try {
                try {
                    double maxAmplitude = this.mMediaRecorder.getMaxAmplitude() / this.BASE;
                    double log10 = maxAmplitude > 1.0d ? Math.log10(maxAmplitude) * 20.0d : 0.0d;
                    OnDecibelListener onDecibelListener = this.onDecibelListener;
                    if (onDecibelListener != null) {
                        onDecibelListener.onRecordDecibelValue(log10);
                    }
                    LogUtils.d(TAG, "分贝值：" + log10);
                } catch (Exception e) {
                    LogUtils.d(TAG, "Exception：" + e.getMessage() + " , StackTrace Exception：" + Arrays.toString(e.getStackTrace()));
                }
            } finally {
                this.mHandler.postDelayed(this.mUpdateMicStatusTimer, this.SPACE);
            }
        }
    }

    public void setOnDecibelListener(OnDecibelListener onDecibelListener) {
        this.onDecibelListener = onDecibelListener;
    }

    public void setOnMediaListener(OnMediaListener onMediaListener) {
        this.onMediaListener = onMediaListener;
    }

    public void startRecord(Context context) {
        String str = TAG;
        LogUtils.i(str, "startRecord");
        if (this.mMediaRecorder == null) {
            this.mMediaRecorder = new MediaRecorder();
        }
        try {
            this.mMediaRecorder.setAudioSource(1);
            this.mMediaRecorder.setOutputFormat(2);
            this.mMediaRecorder.setAudioEncoder(3);
            this.fileName = ((Object) DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA))) + ".m4a";
            this.filePath = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/noRename" + this.fileName;
            this.mMediaRecorder.setOutputFile(this.filePath);
            this.mMediaRecorder.setMaxDuration(10000);
            this.mMediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() { // from class: cn.nubia.chatassistant.util.LocalMusicUtils.1
                @Override // android.media.MediaRecorder.OnInfoListener
                public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
                    if (i == 800) {
                        LocalMusicUtils.this.stopRecord();
                    }
                }
            });
            this.mMediaRecorder.prepare();
            this.mMediaRecorder.start();
            updateMicStatus();
            LogUtils.i(str, "mMediaRecorder.start");
        } catch (IllegalStateException e) {
            LogUtils.i(TAG, e.getMessage());
        } catch (Exception e2) {
            LogUtils.i(TAG, e2.getMessage());
        }
    }

    public void stopRecord() {
        Runnable runnable;
        try {
            this.mMediaRecorder.setOnErrorListener(null);
            this.mMediaRecorder.setOnInfoListener(null);
            this.mMediaRecorder.setPreviewDisplay(null);
            try {
                this.mMediaRecorder.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (RuntimeException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.mMediaRecorder.release();
            this.mMediaRecorder = null;
            Handler handler = this.mHandler;
            if (handler != null && (runnable = this.mUpdateMicStatusTimer) != null) {
                handler.removeCallbacks(runnable);
            }
            this.filePath = "";
        } catch (RuntimeException e4) {
            e4.getMessage();
        }
    }
}
