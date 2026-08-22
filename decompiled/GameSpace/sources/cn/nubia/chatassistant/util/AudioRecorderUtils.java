package cn.nubia.chatassistant.util;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Environment;
import androidx.media3.extractor.text.cea.Cea608Decoder;
import com.google.common.base.Ascii;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class AudioRecorderUtils {
    public static final int AUDIO_INPUT = 1;
    private static final String AUDIO_RAW_FILENAME = "RawAudio.raw";
    public static final int AUDIO_SAMPLE_RATE = 16000;
    private static final String AUDIO_WAV_FILENAME = "WavAudio.wav";
    private static final String TAG = "AudioRecorderUtils";
    private static boolean isFirst = false;
    private static AudioRecorderUtils mInstance;
    private AudioRecord audioRecord;
    private OnDecibelListener onDecibelListener;
    private int bufferSizeInBytes = 0;
    private String audioName = "";
    private String newAudioName = "";
    private boolean isRecord = false;
    private Timer mTimer = null;

    class AudioRecordThread implements Runnable {
        AudioRecordThread() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AudioRecorderUtils.this.writeDateTOFile();
            AudioRecorderUtils audioRecorderUtils = AudioRecorderUtils.this;
            audioRecorderUtils.copyWaveFile(audioRecorderUtils.audioName, AudioRecorderUtils.this.newAudioName);
        }
    }

    public interface OnDecibelListener {
        void onRecordDecibelValue(double d);
    }

    private AudioRecorderUtils() {
    }

    private void WriteWaveFileHeader(FileOutputStream fileOutputStream, long j, long j2, long j3, int i, long j4) throws IOException {
        fileOutputStream.write(new byte[]{82, 73, 70, 70, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 87, 65, 86, 69, 102, 109, 116, 32, Ascii.DLE, 0, 0, 0, 1, 0, (byte) i, 0, (byte) (j3 & 255), (byte) ((j3 >> 8) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 24) & 255), (byte) (j4 & 255), (byte) ((j4 >> 8) & 255), (byte) ((j4 >> 16) & 255), (byte) ((j4 >> 24) & 255), 4, 0, Ascii.DLE, 0, 100, 97, 116, 97, (byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)}, 0, 44);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyWaveFile(String str, String str2) {
        long j = 32000;
        byte[] bArr = new byte[this.bufferSizeInBytes];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            long size = fileInputStream.getChannel().size();
            WriteWaveFileHeader(fileOutputStream, size, size + 36, Cea608Decoder.MIN_DATA_CHANNEL_TIMEOUT_MS, 1, j);
            while (fileInputStream.read(bArr) != -1) {
                fileOutputStream.write(bArr);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void creatAudioRecord(Context context) {
        this.audioName = getRawFilePath(context);
        this.newAudioName = getWavFilePath(context);
        this.bufferSizeInBytes = AudioRecord.getMinBufferSize(16000, 16, 2);
        this.audioRecord = new AudioRecord(1, 16000, 16, 2, this.bufferSizeInBytes);
    }

    public static synchronized AudioRecorderUtils getInstance() {
        AudioRecorderUtils audioRecorderUtils;
        synchronized (AudioRecorderUtils.class) {
            if (mInstance == null) {
                mInstance = new AudioRecorderUtils();
            }
            audioRecorderUtils = mInstance;
        }
        return audioRecorderUtils;
    }

    public static String getRawFilePath(Context context) {
        if (!isSdcardExit()) {
            return "";
        }
        return context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/noRenameRawAudio.raw";
    }

    public static String getWavFilePath(Context context) {
        if (!isSdcardExit()) {
            return "";
        }
        return context.getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/noRenameWavAudio.wav";
    }

    public static boolean isSdcardExit() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeDateTOFile() {
        FileOutputStream fileOutputStream;
        final byte[] bArr = new byte[this.bufferSizeInBytes];
        try {
            File file = new File(this.audioName);
            if (file.exists()) {
                file.delete();
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        while (this.isRecord) {
            final int read = this.audioRecord.read(bArr, 0, this.bufferSizeInBytes);
            if (-3 != read && fileOutputStream != null) {
                try {
                    fileOutputStream.write(bArr);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (isFirst) {
                this.mTimer.schedule(new TimerTask() { // from class: cn.nubia.chatassistant.util.AudioRecorderUtils.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        long j = 0;
                        int i = 0;
                        while (true) {
                            byte[] bArr2 = bArr;
                            if (i >= bArr2.length) {
                                break;
                            }
                            byte b = bArr2[i];
                            j += b * b;
                            i++;
                        }
                        double log10 = Math.log10(j / read) * 10.0d;
                        if (AudioRecorderUtils.this.onDecibelListener != null) {
                            AudioRecorderUtils.this.onDecibelListener.onRecordDecibelValue(log10);
                        }
                        LogUtils.d(AudioRecorderUtils.TAG, "volume: " + log10);
                    }
                }, 0L, 100L);
            }
            isFirst = false;
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void setOnDecibelListener(OnDecibelListener onDecibelListener) {
        this.onDecibelListener = onDecibelListener;
    }

    public int startRecordAndFile(Context context) {
        if (isSdcardExit()) {
            if (this.isRecord) {
                return 1;
            }
            if (this.audioRecord == null) {
                creatAudioRecord(context);
            }
            try {
                this.audioRecord.startRecording();
                this.isRecord = true;
                isFirst = true;
                this.mTimer = new Timer();
                new Thread(new AudioRecordThread()).start();
                return 0;
            } catch (IllegalStateException e) {
                LogUtils.e(TAG, "startRecordAndFile audioRecord no init");
                e.printStackTrace();
            }
        }
        return 2;
    }

    public void stopRecordAndFile() {
        LogUtils.i(TAG, "stopRecordAndFile: " + this.audioRecord);
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            this.isRecord = false;
            try {
                audioRecord.stop();
                this.audioRecord.release();
            } catch (IllegalStateException e) {
                LogUtils.e(TAG, "stopRecordAndFile audioRecord no init");
                e.printStackTrace();
            }
            this.audioRecord = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }
}
