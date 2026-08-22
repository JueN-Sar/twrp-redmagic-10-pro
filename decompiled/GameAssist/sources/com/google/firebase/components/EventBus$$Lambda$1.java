package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import java.util.Map;

/* loaded from: classes.dex */
final /* synthetic */ class EventBus$$Lambda$1 implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final Map.Entry f15838c;

    /* renamed from: h, reason: collision with root package name */
    private final Event f15839h;

    private EventBus$$Lambda$1(Map.Entry entry, Event event) {
        this.f15838c = entry;
        this.f15839h = event;
    }

    public static Runnable a(Map.Entry entry, Event event) {
        return new EventBus$$Lambda$1(entry, event);
    }

    @Override // java.lang.Runnable
    public void run() {
        ((EventHandler) this.f15838c.getKey()).a(this.f15839h);
    }
}
