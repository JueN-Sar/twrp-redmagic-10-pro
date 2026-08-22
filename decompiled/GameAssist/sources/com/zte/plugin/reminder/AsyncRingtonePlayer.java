package com.zte.plugin.reminder;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.PowerManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.zte.gameassist.reminder.R;

/* loaded from: classes2.dex */
public class AsyncRingtonePlayer {
    private static final long[] t = {500, 500};

    /* renamed from: a, reason: collision with root package name */
    private final Context f17999a;

    /* renamed from: b, reason: collision with root package name */
    private Handler f18000b;

    /* renamed from: c, reason: collision with root package name */
    private Uri f18001c;

    /* renamed from: e, reason: collision with root package name */
    private MediaPlayer f18003e;

    /* renamed from: f, reason: collision with root package name */
    private AudioManager f18004f;

    /* renamed from: g, reason: collision with root package name */
    private TelephonyManager f18005g;

    /* renamed from: i, reason: collision with root package name */
    private Vibrator f18007i;

    /* renamed from: j, reason: collision with root package name */
    private PowerManager f18008j;

    /* renamed from: k, reason: collision with root package name */
    private PowerManager.WakeLock f18009k;

    /* renamed from: l, reason: collision with root package name */
    private boolean f18010l;

    /* renamed from: m, reason: collision with root package name */
    private AudioFocusRequest f18011m;

    /* renamed from: p, reason: collision with root package name */
    private boolean f18014p;

    /* renamed from: q, reason: collision with root package name */
    private AudioManager.OnAudioFocusChangeListener f18015q;

    /* renamed from: s, reason: collision with root package name */
    private int f18017s;

    /* renamed from: d, reason: collision with root package name */
    private int f18002d = 0;

    /* renamed from: h, reason: collision with root package name */
    private int f18006h = 1;

    /* renamed from: n, reason: collision with root package name */
    private int f18012n = 0;

    /* renamed from: o, reason: collision with root package name */
    private int f18013o = 0;

    /* renamed from: r, reason: collision with root package name */
    private AudioAttributes f18016r = u();

    public AsyncRingtonePlayer(Context context, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.f18017s = 0;
        this.f17999a = context;
        this.f18004f = (AudioManager) context.getSystemService("audio");
        this.f18008j = (PowerManager) context.getSystemService("power");
        this.f18005g = (TelephonyManager) context.getSystemService("phone");
        this.f18007i = (Vibrator) context.getSystemService("vibrator");
        this.f18015q = onAudioFocusChangeListener;
        this.f18011m = new AudioFocusRequest.Builder(2).setAudioAttributes(this.f18016r).setAcceptsDelayedFocusGain(true).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(this.f18015q).build();
        AudioManager audioManager = this.f18004f;
        if (audioManager != null) {
            this.f18017s = audioManager.getStreamVolume(4);
        }
    }

    private void A(int i2, long j2) {
        synchronized (this) {
            try {
                if (this.f18000b == null) {
                    this.f18000b = w();
                }
                this.f18000b.sendMessageDelayed(this.f18000b.obtainMessage(i2), j2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        AudioFocusRequest audioFocusRequest;
        GameReminderUtils.e("AsyncRingtonePlayer", "releaseAudioFocus: ");
        AudioManager audioManager = this.f18004f;
        if (audioManager == null || (audioFocusRequest = this.f18011m) == null) {
            return;
        }
        GameReminderUtils.e("AsyncRingtonePlayer", "releaseAudioFocus: result : " + audioManager.abandonAudioFocusRequest(audioFocusRequest));
        this.f18015q = null;
    }

    private void C(Resources resources, MediaPlayer mediaPlayer, int i2) {
        AssetFileDescriptor openRawResourceFd = resources.openRawResourceFd(i2);
        if (openRawResourceFd != null) {
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        int i2 = this.f18012n;
        if (i2 == 0) {
            this.f18012n = 499;
            this.f18013o = 499;
        } else {
            this.f18013o = i2;
            this.f18012n = 501;
        }
        int streamVolume = this.f18004f.getStreamVolume(4);
        MediaPlayer mediaPlayer = this.f18003e;
        if (mediaPlayer != null) {
            try {
                if (streamVolume > 1) {
                    float f2 = (float) ((this.f18013o * 1.0d) / (streamVolume * 500));
                    mediaPlayer.setVolume(f2, f2);
                } else {
                    mediaPlayer.setVolume(0.0f, 0.0f);
                }
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void E(MediaPlayer mediaPlayer, boolean z, int i2, boolean z2) {
        PowerManager.WakeLock newWakeLock = this.f18008j.newWakeLock(805306369, "AsyncRingtonePlayer");
        this.f18009k = newWakeLock;
        newWakeLock.acquire();
        GameReminderUtils.e("AsyncRingtonePlayer", " [startAlarm] mCurVolume : " + this.f18002d + " ;; requestResult : " + this.f18004f.requestAudioFocus(this.f18011m));
        if (this.f18002d != 0) {
            GameReminderUtils.e("AsyncRingtonePlayer", "no no no isAlertIncreasing");
            this.f18004f.setStreamVolume(4, i2, 0);
            Handler handler = this.f18000b;
            if (handler != null) {
                handler.removeMessages(10);
            }
            A(10, 0L);
            mediaPlayer.setLooping(z);
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
    }

    private void F() {
        if (this.f18005g.getCallState() == 0) {
            GameReminderUtils.e("AsyncRingtonePlayer", "startVibrate: vibrate : true");
            this.f18007i.vibrate(VibrationEffect.createWaveform(t, 0), new AudioAttributes.Builder().setUsage(4).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i2) {
        GameReminderUtils.e("AsyncRingtonePlayer", "changeVolume: audioFocusState : " + i2);
        if (i2 == -3) {
            Handler handler = this.f18000b;
            if (handler != null) {
                handler.removeMessages(6);
            }
            A(7, 0L);
            return;
        }
        if (i2 == -2) {
            Handler handler2 = this.f18000b;
            if (handler2 != null) {
                handler2.removeMessages(6);
            }
            A(5, 0L);
            return;
        }
        if (i2 == -1) {
            Handler handler3 = this.f18000b;
            if (handler3 != null) {
                handler3.removeMessages(6);
            }
            A(5, 0L);
            return;
        }
        if (i2 != 1) {
            GameReminderUtils.e("AsyncRingtonePlayer", "Unknown audio focus change code");
            return;
        }
        Handler handler4 = this.f18000b;
        if (handler4 != null) {
            handler4.removeMessages(5);
        }
        A(6, 400L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        MediaPlayer mediaPlayer = this.f18003e;
        if (mediaPlayer != null) {
            try {
                if (this.f18017s > 0) {
                    mediaPlayer.setVolume(1.0f, 1.0f);
                } else {
                    mediaPlayer.setVolume(0.0f, 0.0f);
                }
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            }
        }
        Handler handler = this.f18000b;
        if (handler != null && handler.hasMessages(10)) {
            this.f18000b.removeMessages(10);
        }
        A(10, 60L);
    }

    private void t() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f18003e = mediaPlayer;
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.zte.plugin.reminder.AsyncRingtonePlayer.3
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer2, int i2, int i3) {
                GameReminderUtils.b("AsyncRingtonePlayer", "Error occurred while playing audio.");
                if (AsyncRingtonePlayer.this.f18003e == null) {
                    return true;
                }
                if (AsyncRingtonePlayer.this.f18003e.isPlaying()) {
                    AsyncRingtonePlayer.this.f18003e.stop();
                }
                if (AsyncRingtonePlayer.this.f18003e != null) {
                    AsyncRingtonePlayer.this.f18003e.release();
                }
                AsyncRingtonePlayer.this.f18003e = null;
                return true;
            }
        });
        this.f18003e.setAudioAttributes(this.f18016r);
    }

    private AudioAttributes u() {
        return new AudioAttributes.Builder().setUsage(4).setContentType(4).build();
    }

    private Handler w() {
        HandlerThread handlerThread = new HandlerThread("ringtone-player");
        handlerThread.start();
        return new Handler(handlerThread.getLooper()) { // from class: com.zte.plugin.reminder.AsyncRingtonePlayer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 != 8 && i2 != 10) {
                    GameReminderUtils.e("AsyncRingtonePlayer", " [ringtone] msg.what : " + message.what);
                }
                switch (message.what) {
                    case 1:
                        AsyncRingtonePlayer.this.y();
                        break;
                    case 2:
                        AsyncRingtonePlayer.this.G();
                        break;
                    case 4:
                        AsyncRingtonePlayer.this.r(message.arg1);
                        break;
                    case 5:
                        AsyncRingtonePlayer.this.G();
                        break;
                    case 6:
                        if (AsyncRingtonePlayer.this.f18012n == 0) {
                            AsyncRingtonePlayer asyncRingtonePlayer = AsyncRingtonePlayer.this;
                            asyncRingtonePlayer.f18012n = asyncRingtonePlayer.f18013o;
                        }
                        AsyncRingtonePlayer asyncRingtonePlayer2 = AsyncRingtonePlayer.this;
                        asyncRingtonePlayer2.z(asyncRingtonePlayer2.f18001c, AsyncRingtonePlayer.this.f18014p);
                        break;
                    case 7:
                        AsyncRingtonePlayer.this.D();
                        break;
                    case 8:
                        AsyncRingtonePlayer.this.x();
                        break;
                    case 9:
                        AsyncRingtonePlayer.this.B();
                        break;
                    case 10:
                        AsyncRingtonePlayer.this.s();
                        break;
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        int i2 = this.f18012n;
        int i3 = i2 + 1;
        this.f18012n = i3;
        if (i2 < 500 && this.f18010l) {
            MediaPlayer mediaPlayer = this.f18003e;
            if (mediaPlayer != null) {
                try {
                    if (this.f18017s > 0) {
                        mediaPlayer.setVolume((float) (i3 * 0.002d), (float) (i3 * 0.002d));
                    } else {
                        mediaPlayer.setVolume(0.0f, 0.0f);
                    }
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                }
                Handler handler = this.f18000b;
                if (handler != null) {
                    handler.removeMessages(8);
                }
                A(8, 60L);
                return;
            }
            return;
        }
        if (i3 == 502) {
            GameReminderUtils.e("AsyncRingtonePlayer", "increaseVolume: mCurrentAlarmVolume : " + this.f18017s + " ;; mAlertIncreasingNum : " + this.f18012n + " ;; mTempIncreasingNum : " + this.f18013o);
            MediaPlayer mediaPlayer2 = this.f18003e;
            if (mediaPlayer2 != null) {
                try {
                    if (this.f18017s > 0) {
                        int i4 = this.f18013o;
                        mediaPlayer2.setVolume((float) (i4 * 0.002d), (float) (i4 * 0.002d));
                    } else {
                        mediaPlayer2.setVolume(0.0f, 0.0f);
                    }
                } catch (IllegalStateException e3) {
                    e3.printStackTrace();
                }
            }
        } else {
            GameReminderUtils.e("AsyncRingtonePlayer", "increaseVolume: INCREASINGALERT_COMPLETE ");
            A(10, 60L);
        }
        this.f18012n = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        G();
        this.f18002d = this.f18004f.getStreamVolume(4);
        GameReminderUtils.e("AsyncRingtonePlayer", "mCurVolume=" + this.f18002d);
        GameReminderUtils.e("AsyncRingtonePlayer", "play = " + this.f18004f.getStreamVolume(4));
        GameReminderUtils.e("AsyncRingtonePlayer", "play STREAM_SYSTEM = " + this.f18004f.getStreamVolume(1));
        if (this.f18001c == null) {
            this.f18001c = RingtoneManager.getDefaultUri(4);
            GameReminderUtils.e("AsyncRingtonePlayer", "Using default alarm: " + this.f18001c.toString());
        }
        t();
        try {
            if (this.f18005g.getCallState() == 2) {
                if (GameReminderUtils.f(this.f17999a) && this.f18006h == 1) {
                    this.f18003e.reset();
                    C(this.f17999a.getResources(), this.f18003e, R.raw.call_waiting);
                    E(this.f18003e, false, this.f18002d, false);
                } else {
                    this.f18007i.cancel();
                }
            } else if (this.f18005g.getCallState() == 1) {
                this.f18003e.reset();
                C(this.f17999a.getResources(), this.f18003e, R.raw.call_waiting);
                E(this.f18003e, false, 0, false);
                this.f18003e.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.zte.plugin.reminder.AsyncRingtonePlayer.2
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        AsyncRingtonePlayer.this.f18004f.setStreamVolume(4, AsyncRingtonePlayer.this.f18002d, 0);
                    }
                });
            } else {
                GameReminderUtils.e("AsyncRingtonePlayer", " normal mIsGameMode : " + this.f18014p);
                if (this.f18014p) {
                    GameReminderUtils.h(this.f17999a);
                    this.f18003e.reset();
                    this.f18003e.setDataSource(this.f17999a, this.f18001c);
                } else {
                    this.f18003e.reset();
                    this.f18003e.setDataSource(this.f17999a, this.f18001c);
                    E(this.f18003e, true, this.f18002d, true);
                }
            }
        } catch (Exception e2) {
            GameReminderUtils.c("AsyncRingtonePlayer", " error ", e2);
            if (this.f18014p) {
                GameReminderUtils.e("AsyncRingtonePlayer", "mIsGameMode");
                return;
            }
            try {
                if (this.f18005g.getCallState() == 0) {
                    try {
                        GameReminderUtils.e("AsyncRingtonePlayer", " error is CALL_STATE_IDLE ");
                        this.f18003e.reset();
                        C(this.f17999a.getResources(), this.f18003e, R.raw.peaceful_wave);
                        E(this.f18003e, true, this.f18002d, true);
                    } catch (Exception e3) {
                        GameReminderUtils.c("AsyncRingtonePlayer", " error ", e3);
                        this.f18003e.reset();
                        C(this.f17999a.getResources(), this.f18003e, R.raw.fallbackring);
                        E(this.f18003e, true, this.f18002d, true);
                    }
                } else if (GameReminderUtils.f(this.f17999a) && this.f18006h == 1) {
                    GameReminderUtils.e("AsyncRingtonePlayer", " error is not CALL_STATE_IDLE ");
                    try {
                        this.f18003e.reset();
                        C(this.f17999a.getResources(), this.f18003e, R.raw.call_waiting);
                        E(this.f18003e, false, 6, false);
                    } catch (Exception unused) {
                        this.f18003e.reset();
                        C(this.f17999a.getResources(), this.f18003e, R.raw.fallbackring);
                        E(this.f18003e, true, this.f18002d, true);
                    }
                } else {
                    this.f18007i.cancel();
                }
            } catch (Exception e4) {
                GameReminderUtils.c("AsyncRingtonePlayer", "Failed to play fallback ringtone", e4);
            }
            GameReminderUtils.c("AsyncRingtonePlayer", "Failed to play fallback ringtone", e4);
        }
        this.f18010l = true;
        if (this.f18014p) {
            return;
        }
        F();
    }

    public void G() {
        GameReminderUtils.e("AsyncRingtonePlayer", "stop() mPlaying : " + this.f18010l);
        if (this.f18010l) {
            this.f18010l = false;
            if (this.f18003e != null) {
                GameReminderUtils.e("AsyncRingtonePlayer", " stop() mMediaPlayer.isPlaying(): " + this.f18003e.isPlaying());
                if (this.f18000b.hasMessages(8)) {
                    GameReminderUtils.e("AsyncRingtonePlayer", " has message INCREASINGALERT ");
                    this.f18000b.removeMessages(8);
                }
                Handler handler = this.f18000b;
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
                if (this.f18003e.isPlaying()) {
                    this.f18003e.stop();
                }
                MediaPlayer mediaPlayer = this.f18003e;
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    this.f18003e = null;
                }
            }
            PowerManager.WakeLock wakeLock = this.f18009k;
            if (wakeLock != null) {
                wakeLock.release();
                this.f18009k = null;
            }
            GameReminderUtils.e("AsyncRingtonePlayer", "stop() end");
        }
        Vibrator vibrator = this.f18007i;
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    public void H() {
        GameReminderUtils.e("AsyncRingtonePlayer", "Posting stop.");
        A(2, 0L);
    }

    public void p() {
        GameReminderUtils.e("AsyncRingtonePlayer", "abandonAudioFocus: ");
        B();
    }

    public void q(int i2) {
        v().obtainMessage(4, i2, 0).sendToTarget();
    }

    public Handler v() {
        if (this.f18000b == null) {
            this.f18000b = w();
        }
        return this.f18000b;
    }

    public void z(Uri uri, boolean z) {
        GameReminderUtils.e("AsyncRingtonePlayer", "playAlarm isGameMode: " + z);
        this.f18001c = uri;
        this.f18014p = z;
        try {
            this.f18006h = Settings.System.getInt(this.f17999a.getContentResolver(), "message_prompts");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        A(1, 0L);
    }
}
