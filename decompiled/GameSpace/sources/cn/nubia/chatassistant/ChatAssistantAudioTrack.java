package cn.nubia.chatassistant;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.media3.common.MimeTypes;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.ReportUtils;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class ChatAssistantAudioTrack {
    private static final int AUDIO_BIT_PERSAMPLE_POSITION = 34;
    private static final int AUDIO_FORMAT_POSITION = 20;
    private static final int AUDIO_NUM_CHANNEL_POSITION = 22;
    private static final int AUDIO_SAMPLE_RATE_POSITION = 24;
    private static final int COUNT_EIGHT = 8;
    private static final int COUNT_SIXTEEN = 16;
    private static final int COUNT_TWO = 2;
    private static final int MSG_DEEPBUFF_PLAY_FINISHED = 0;
    private static final int MSG_DEEP_BUFF_PLAY = 0;
    private static final int MSG_LOOPBACK_PLAY_FINISHED = 1;
    private static final int MSG_LOOP_BACK_PLAY = 1;
    private static final int MSG_PLAY_FINISHED = 1000;
    private static final String TAG = "ChatAssistantAudioTrack";
    private static Context mContext;
    private int mFileType;
    private MainHandler mHandler = new MainHandler(this);
    private MsgData mMsgData;
    private String mPath;

    public interface AudioTrackCallback {
        void onNextPlayAudioStart();

        void onPlayApplauseAudio();

        void onPlayAudioStop();
    }

    private class ChildCallback implements Handler.Callback {
        private ChildCallback() {
        }

        private void playChatImpl(AudioTrack audioTrack, byte[] bArr) {
            Process.setThreadPriority(-19);
            if (ChatAssistantAudioTrack.this.mMsgData == null || ChatAssistantAudioTrack.this.mMsgData.data == null) {
                return;
            }
            int length = ChatAssistantAudioTrack.this.mMsgData.data.length;
            if (audioTrack != null) {
                audioTrack.play();
                audioTrack.write(bArr, 44, length - 44);
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            int i2 = 1;
            if (i == 0) {
                LogUtils.i(ChatAssistantAudioTrack.TAG, "mATDeepBuff start");
                ChatAssistantAudioTrack.this.mMsgData.mWorkTrackCount++;
                playChatImpl(ChatAssistantAudioTrack.this.mMsgData.mATDeepBuff, ChatAssistantAudioTrack.this.mMsgData.data);
                i2 = 0;
            } else if (i != 1) {
                i2 = 1000;
            } else {
                LogUtils.i(ChatAssistantAudioTrack.TAG, "mATLoopBack start");
                ChatAssistantAudioTrack.this.mMsgData.mWorkTrackCount++;
                playChatImpl(ChatAssistantAudioTrack.this.mMsgData.mATLoopBack, ChatAssistantAudioTrack.this.mMsgData.data);
            }
            ChatAssistantAudioTrack.this.sendMessage(i2, 0L);
            return false;
        }
    }

    private static class MainHandler extends Handler {
        WeakReference<ChatAssistantAudioTrack> mTrack;

        MainHandler(ChatAssistantAudioTrack chatAssistantAudioTrack) {
            this.mTrack = new WeakReference<>(chatAssistantAudioTrack);
        }

        private static void releaseDeepBuffAudioTrack(MsgData msgData) {
            if (msgData.mATDeepBuff == null || msgData.mATDeepBuff.getState() != 1) {
                return;
            }
            try {
                try {
                    msgData.mATDeepBuff.stop();
                    msgData.mATDeepBuff.release();
                    LogUtils.i(ChatAssistantAudioTrack.TAG, " in release mATDeepBuff");
                } catch (IllegalStateException e) {
                    LogUtils.e(ChatAssistantAudioTrack.TAG, "releaseDeepBuffAudioTrack no init");
                    e.printStackTrace();
                }
            } finally {
                msgData.mATDeepBuff = null;
            }
        }

        private static void releaseDeepBuffThread(MsgData msgData) {
            if (msgData.mChildHandlerDeepBuff != null) {
                msgData.mChildHandlerDeepBuff.removeMessages(0);
            }
            LogUtils.i(ChatAssistantAudioTrack.TAG, "releaseDeepBuffThread: " + msgData.mThreadDeepBuff);
            if (msgData.mThreadDeepBuff != null) {
                msgData.mThreadDeepBuff.quitSafely();
            }
        }

        private static void releaseLoopBackAudioTrack(MsgData msgData) {
            if (msgData.mATLoopBack == null || msgData.mATLoopBack.getState() != 1) {
                return;
            }
            LogUtils.i(ChatAssistantAudioTrack.TAG, "in release mATLoopBack");
            try {
                try {
                    msgData.mATLoopBack.stop();
                    msgData.mATLoopBack.release();
                    LogUtils.i(ChatAssistantAudioTrack.TAG, " in release mATDeepBuff");
                } catch (IllegalStateException e) {
                    LogUtils.e(ChatAssistantAudioTrack.TAG, "releaseLoopBackAudioTrack no init");
                    e.printStackTrace();
                }
            } finally {
                msgData.mATLoopBack = null;
            }
        }

        private static void releaseLoopBackThread(MsgData msgData) {
            if (msgData.mChildHandlerLoopBack != null) {
                msgData.mChildHandlerLoopBack.removeMessages(1);
            }
            LogUtils.i(ChatAssistantAudioTrack.TAG, "releaseLoopBackThread: " + msgData.mThreadLoopBack);
            if (msgData.mThreadLoopBack != null) {
                msgData.mThreadLoopBack.quitSafely();
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MsgData msgData = (MsgData) message.obj;
            if (msgData == null) {
                LogUtils.e(ChatAssistantAudioTrack.TAG, "msgData is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                LogUtils.e(ChatAssistantAudioTrack.TAG, " chat track MainHandler deepbuff finished");
                releaseDeepBuffAudioTrack(msgData);
                releaseDeepBuffThread(msgData);
                msgData.mWorkTrackCount--;
            } else if (i == 1) {
                LogUtils.e(ChatAssistantAudioTrack.TAG, "chat track MainHandler loopback finished");
                releaseLoopBackAudioTrack(msgData);
                releaseLoopBackThread(msgData);
                msgData.mWorkTrackCount--;
            } else if (i == 1000) {
                LogUtils.e(ChatAssistantAudioTrack.TAG, " chat track MainHandler finished");
                releaseLoopBackAudioTrack(msgData);
                releaseDeepBuffAudioTrack(msgData);
                releaseLoopBackThread(msgData);
                releaseDeepBuffThread(msgData);
                msgData.mWorkTrackCount = 0;
            }
            LogUtils.e(ChatAssistantAudioTrack.TAG, "mainHandler mWorkTrackCount=" + msgData.mWorkTrackCount);
            int i2 = Settings.Global.getInt(ChatAssistantAudioTrack.mContext.getContentResolver(), "play_mode_send_three_message", 1);
            int i3 = Settings.Global.getInt(ChatAssistantAudioTrack.mContext.getContentResolver(), "play_mode_send_applause_message", 1);
            if (i3 == 1 && i2 == 1 && msgData.mWorkTrackCount == 0) {
                if (msgData.mCallBack != null) {
                    LogUtils.e(ChatAssistantAudioTrack.TAG, "mainHandler onPlayAudioStop");
                    msgData.data = null;
                    msgData.mCallBack.onPlayAudioStop();
                }
            } else if (i2 == 0 && msgData.mWorkTrackCount == 0) {
                msgData.mCallBack.onNextPlayAudioStart();
            } else if (i3 == 0 && msgData.mWorkTrackCount == 0) {
                msgData.mCallBack.onPlayApplauseAudio();
            }
            super.handleMessage(message);
        }
    }

    private class MsgData {
        public AudioTrack mATDeepBuff;
        public AudioTrack mATLoopBack;
        public AudioTrackCallback mCallBack;
        public Handler mChildHandlerDeepBuff;
        public Handler mChildHandlerLoopBack;
        public HandlerThread mThreadDeepBuff;
        public HandlerThread mThreadLoopBack;
        public byte[] data = null;
        public int mWorkTrackCount = 0;

        public MsgData(AudioTrackCallback audioTrackCallback, AudioTrack audioTrack, AudioTrack audioTrack2) {
            this.mCallBack = audioTrackCallback;
            this.mATDeepBuff = audioTrack;
            this.mATLoopBack = audioTrack2;
        }
    }

    public class WavData {
        int channelMask = 4;
        int sampleRate = 16000;
        int audioFormatEncodeing = 2;

        public WavData() {
        }
    }

    public ChatAssistantAudioTrack(Context context, String str, int i, AudioTrackCallback audioTrackCallback) {
        this.mPath = null;
        this.mFileType = 0;
        mContext = context;
        this.mFileType = i;
        if (str == null || TextUtils.isEmpty(str)) {
            LogUtils.e(TAG, "path is null!");
            return;
        }
        this.mMsgData = new MsgData(audioTrackCallback, null, null);
        this.mPath = str;
        WavData wavData = new WavData();
        if (this.mFileType == 0) {
            initAudioTrack(wavData);
            return;
        }
        WavData info = getInfo(str);
        if (info == null) {
            return;
        }
        initAudioTrack(info);
    }

    private void initAudioTrack(WavData wavData) {
        int minBufferSize = AudioTrack.getMinBufferSize(wavData.sampleRate, wavData.channelMask, wavData.audioFormatEncodeing);
        AudioFormat.Builder builder = new AudioFormat.Builder();
        builder.setChannelMask(wavData.channelMask).setEncoding(wavData.audioFormatEncodeing).setSampleRate(wavData.sampleRate);
        AudioFormat build = builder.build();
        AudioManager audioManager = (AudioManager) mContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        int generateAudioSessionId = audioManager.generateAudioSessionId();
        audioManager.generateAudioSessionId();
        initLoopBackAudioTrack(minBufferSize, build, generateAudioSessionId);
        if (Settings.Global.getInt(mContext.getContentResolver(), "play_mode_send_slowly_message", 1) == 0) {
            setPlaySpeed();
            LogUtils.i(TAG, "initAudioTrack: isSpeed");
        }
    }

    private void initDeepBuffAudioTrack(int i, AudioFormat audioFormat, int i2) {
        AudioAttributes build = new AudioAttributes.Builder().setUsage(1).setContentType(2).setLegacyStreamType(3).setFlags(512).build();
        this.mMsgData.mATDeepBuff = new AudioTrack(build, audioFormat, i, 1, i2);
    }

    private void initLoopBackAudioTrack(int i, AudioFormat audioFormat, int i2) {
        AudioAttributes build = new AudioAttributes.Builder().setUsage(1).setContentType(2).setLegacyStreamType(3).setFlags(1073741824).build();
        this.mMsgData.mATLoopBack = new AudioTrack(build, audioFormat, i, 1, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v13, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.DataInputStream] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.DataInputStream] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public void readFile() {
        FileInputStream fileInputStream;
        IOException e;
        FileNotFoundException e2;
        long length;
        DataInputStream dataInputStream;
        DataInputStream dataInputStream2 = 0;
        dataInputStream2 = 0;
        dataInputStream2 = 0;
        dataInputStream2 = 0;
        dataInputStream2 = 0;
        dataInputStream2 = 0;
        dataInputStream2 = 0;
        try {
            try {
                try {
                    if (this.mFileType == 0) {
                        AssetFileDescriptor openFd = mContext.getAssets().openFd(this.mPath);
                        length = mContext.getAssets().openFd(this.mPath).getLength();
                        fileInputStream = openFd.createInputStream();
                    } else {
                        File file = new File(this.mPath);
                        length = file.length();
                        fileInputStream = new FileInputStream(file);
                    }
                    try {
                        dataInputStream = new DataInputStream(fileInputStream);
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    this.mMsgData.data = new byte[(int) length];
                    dataInputStream2 = this.mMsgData.data;
                    dataInputStream.read(dataInputStream2);
                    dataInputStream.close();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (FileNotFoundException e5) {
                    e2 = e5;
                    dataInputStream2 = dataInputStream;
                    e2.printStackTrace();
                    LogUtils.e(TAG, this.mPath + " opened failed!");
                    if (dataInputStream2 != 0) {
                        dataInputStream2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (IOException e6) {
                    e = e6;
                    dataInputStream2 = dataInputStream;
                    e.printStackTrace();
                    LogUtils.e(TAG, this.mPath + " read failed!");
                    if (dataInputStream2 != 0) {
                        dataInputStream2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    dataInputStream2 = dataInputStream;
                    if (dataInputStream2 != 0) {
                        try {
                            dataInputStream2.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                            throw th;
                        }
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                e2 = e8;
                fileInputStream = null;
            } catch (IOException e9) {
                e = e9;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
        } catch (IOException e10) {
            e10.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(int i, long j) {
        Message obtainMessage = this.mHandler.obtainMessage(i);
        obtainMessage.obj = this.mMsgData;
        this.mHandler.sendMessageDelayed(obtainMessage, 0L);
    }

    private void setPlaySpeed() {
        try {
            if (this.mMsgData.mATDeepBuff != null) {
                PlaybackParams playbackParams = this.mMsgData.mATDeepBuff.getPlaybackParams();
                playbackParams.setSpeed(0.5f);
                this.mMsgData.mATDeepBuff.setPlaybackParams(playbackParams);
            }
            if (this.mMsgData.mATLoopBack != null) {
                PlaybackParams playbackParams2 = this.mMsgData.mATLoopBack.getPlaybackParams();
                playbackParams2.setSpeed(0.5f);
                this.mMsgData.mATLoopBack.setPlaybackParams(playbackParams2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0104 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public cn.nubia.chatassistant.ChatAssistantAudioTrack.WavData getInfo(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.chatassistant.ChatAssistantAudioTrack.getInfo(java.lang.String):cn.nubia.chatassistant.ChatAssistantAudioTrack$WavData");
    }

    public void startPlay() {
        ReportUtils.onReportChatAssistantUsed(mContext);
        ReportUtils.onReportChatAssistantUsedAndAccountLogin(mContext);
        new Thread() { // from class: cn.nubia.chatassistant.ChatAssistantAudioTrack.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                ChatAssistantAudioTrack.this.readFile();
                if (ChatAssistantAudioTrack.this.mMsgData.data == null) {
                    ChatAssistantAudioTrack.this.sendMessage(1000, 0L);
                    return;
                }
                ChatAssistantAudioTrack.this.mMsgData.mThreadDeepBuff = new HandlerThread("chat_assistant_deepbuff_thread");
                ChatAssistantAudioTrack.this.mMsgData.mThreadDeepBuff.start();
                ChatAssistantAudioTrack.this.mMsgData.mChildHandlerDeepBuff = new Handler(ChatAssistantAudioTrack.this.mMsgData.mThreadDeepBuff.getLooper(), new ChildCallback());
                ChatAssistantAudioTrack.this.mMsgData.mThreadLoopBack = new HandlerThread("chat_assistant_loopback_thread");
                ChatAssistantAudioTrack.this.mMsgData.mThreadLoopBack.start();
                ChatAssistantAudioTrack.this.mMsgData.mChildHandlerLoopBack = new Handler(ChatAssistantAudioTrack.this.mMsgData.mThreadLoopBack.getLooper(), new ChildCallback());
                ChatAssistantAudioTrack.this.mMsgData.mChildHandlerDeepBuff.sendEmptyMessage(0);
                ChatAssistantAudioTrack.this.mMsgData.mChildHandlerLoopBack.sendEmptyMessage(1);
            }
        }.start();
    }

    public void stop() {
        MsgData msgData = this.mMsgData;
        if (msgData != null) {
            if (msgData.mATLoopBack != null) {
                this.mMsgData.mATLoopBack.pause();
                this.mMsgData.mATLoopBack.release();
                this.mMsgData.mATLoopBack = null;
                LogUtils.i(TAG, "in release mATLoopBack");
            }
            if (this.mMsgData.mATDeepBuff != null) {
                this.mMsgData.mATDeepBuff.pause();
                this.mMsgData.mATDeepBuff.release();
                this.mMsgData.mATDeepBuff = null;
                LogUtils.i(TAG, " in release mATDeepBuff");
            }
            if (this.mMsgData.mChildHandlerLoopBack != null) {
                this.mMsgData.mChildHandlerLoopBack.removeMessages(1);
            }
            LogUtils.i(TAG, "releaseLoopBackThread: " + this.mMsgData.mThreadLoopBack);
            if (this.mMsgData.mThreadLoopBack != null) {
                this.mMsgData.mThreadLoopBack.quitSafely();
            }
            if (this.mMsgData.mChildHandlerDeepBuff != null) {
                this.mMsgData.mChildHandlerDeepBuff.removeMessages(0);
            }
            LogUtils.i(TAG, "releaseDeepBuffThread: " + this.mMsgData.mThreadDeepBuff);
            if (this.mMsgData.mThreadDeepBuff != null) {
                this.mMsgData.mThreadDeepBuff.quitSafely();
            }
        }
    }
}
