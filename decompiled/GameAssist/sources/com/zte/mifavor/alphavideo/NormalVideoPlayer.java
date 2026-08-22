package com.zte.mifavor.alphavideo;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* loaded from: classes2.dex */
public class NormalVideoPlayer implements SurfaceHolder.Callback, MediaPlayer.OnCompletionListener {

    /* renamed from: c, reason: collision with root package name */
    private MediaPlayer f17078c;

    /* renamed from: h, reason: collision with root package name */
    private SurfaceView f17079h;

    /* renamed from: i, reason: collision with root package name */
    private IPlayerListener f17080i;

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        IPlayerListener iPlayerListener = this.f17080i;
        if (iPlayerListener != null) {
            iPlayerListener.b();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            this.f17078c = new MediaPlayer();
            AssetFileDescriptor openFd = this.f17079h.getResources().getAssets().openFd("open.mp4");
            this.f17078c.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.f17078c.setSurface(surfaceHolder.getSurface());
            this.f17078c.setOnCompletionListener(this);
            this.f17078c.setLooping(false);
            this.f17078c.prepare();
            this.f17078c.start();
            IPlayerListener iPlayerListener = this.f17080i;
            if (iPlayerListener != null) {
                iPlayerListener.a();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
