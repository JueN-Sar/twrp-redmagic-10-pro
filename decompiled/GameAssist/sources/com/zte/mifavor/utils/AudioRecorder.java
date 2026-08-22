package com.zte.mifavor.utils;

import android.media.AudioRecord;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public class AudioRecorder {

    /* renamed from: f, reason: collision with root package name */
    private static final int f17392f = AudioRecord.getMinBufferSize(16000, 16, 2);

    /* renamed from: a, reason: collision with root package name */
    private AudioRecord f17393a;

    /* renamed from: b, reason: collision with root package name */
    private Thread f17394b;

    /* renamed from: c, reason: collision with root package name */
    private volatile boolean f17395c = false;

    /* renamed from: d, reason: collision with root package name */
    ByteArrayOutputStream f17396d = new ByteArrayOutputStream();

    /* renamed from: e, reason: collision with root package name */
    private byte[] f17397e;

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
            while (this.f17395c) {
                try {
                    int i2 = f17392f;
                    byte[] bArr = new byte[i2];
                    if (-3 != this.f17393a.read(bArr, 0, i2)) {
                        Log.d("Z#AudioRecorder", "isRecording,  write file...");
                        fileOutputStream.write(bArr);
                    }
                } finally {
                }
            }
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void b(final ParcelFileDescriptor parcelFileDescriptor) {
        Log.d("Z#AudioRecorder", "startRecording audioRecord:" + this.f17393a);
        if (this.f17393a == null) {
            this.f17393a = new AudioRecord(1, 16000, 16, 2, f17392f);
        }
        Log.d("Z#AudioRecorder", "start recording .");
        this.f17393a.startRecording();
        this.f17395c = true;
        Log.d("Z#AudioRecorder", "writeEnd:" + parcelFileDescriptor);
        if (parcelFileDescriptor != null) {
            Thread thread = new Thread(new Runnable() { // from class: com.zte.mifavor.utils.AudioRecorder.1
                @Override // java.lang.Runnable
                public void run() {
                    AudioRecorder.this.d(parcelFileDescriptor);
                }
            }, "AudioRecorder");
            this.f17394b = thread;
            thread.start();
        }
    }

    public byte[] c(boolean z) {
        if (this.f17395c) {
            this.f17395c = false;
            Thread thread = this.f17394b;
            if (thread != null) {
                thread.interrupt();
                this.f17394b = null;
            }
            AudioRecord audioRecord = this.f17393a;
            if (audioRecord != null) {
                if (z) {
                    if (audioRecord != null) {
                        try {
                            audioRecord.stop();
                            this.f17393a.release();
                            this.f17393a = null;
                        } catch (Exception e2) {
                            Log.d("Z#AudioRecorder", "read buffer error :" + e2);
                        }
                    }
                    Log.d("Z#AudioRecorder", "return recorddata size :" + this.f17397e.length);
                    return this.f17397e;
                }
                if (audioRecord != null) {
                    audioRecord.stop();
                    this.f17393a.release();
                    this.f17393a = null;
                }
            }
        }
        return null;
    }
}
