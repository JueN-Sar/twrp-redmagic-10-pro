package com.facebook.rebound;

import com.facebook.rebound.ChoreographerCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* loaded from: classes.dex */
public class AnimationQueue {

    /* renamed from: a, reason: collision with root package name */
    private final ChoreographerCompat f9992a;

    /* renamed from: b, reason: collision with root package name */
    private final Queue f9993b;

    /* renamed from: c, reason: collision with root package name */
    private final Queue f9994c;

    /* renamed from: d, reason: collision with root package name */
    private final List f9995d;

    /* renamed from: e, reason: collision with root package name */
    private final ArrayList f9996e;

    /* renamed from: f, reason: collision with root package name */
    private final ChoreographerCompat.FrameCallback f9997f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f9998g;

    /* renamed from: com.facebook.rebound.AnimationQueue$1, reason: invalid class name */
    class AnonymousClass1 extends ChoreographerCompat.FrameCallback {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ AnimationQueue f9999c;

        @Override // com.facebook.rebound.ChoreographerCompat.FrameCallback
        public void a(long j2) {
            this.f9999c.b(j2);
        }
    }

    public interface Callback {
        void a(Double d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j2) {
        int max;
        Double d2 = (Double) this.f9993b.poll();
        if (d2 != null) {
            this.f9994c.offer(d2);
            max = 0;
        } else {
            max = Math.max(this.f9995d.size() - this.f9994c.size(), 0);
        }
        this.f9996e.addAll(this.f9994c);
        int size = this.f9996e.size();
        while (true) {
            size--;
            if (size <= -1) {
                break;
            }
            Double d3 = (Double) this.f9996e.get(size);
            int size2 = ((this.f9996e.size() - 1) - size) + max;
            if (this.f9995d.size() > size2) {
                ((Callback) this.f9995d.get(size2)).a(d3);
            }
        }
        this.f9996e.clear();
        while (this.f9994c.size() + max >= this.f9995d.size()) {
            this.f9994c.poll();
        }
        if (this.f9994c.isEmpty() && this.f9993b.isEmpty()) {
            this.f9998g = false;
        } else {
            this.f9992a.c(this.f9997f);
        }
    }
}
