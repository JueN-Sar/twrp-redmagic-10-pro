package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class EventBus implements Subscriber, Publisher {

    /* renamed from: a, reason: collision with root package name */
    private final Map f15835a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private Queue f15836b = new ArrayDeque();

    /* renamed from: c, reason: collision with root package name */
    private final Executor f15837c;

    EventBus(Executor executor) {
        this.f15837c = executor;
    }

    private synchronized Set b(Event event) {
        Map map;
        try {
            map = (Map) this.f15835a.get(event.a());
        } catch (Throwable th) {
            throw th;
        }
        return map == null ? Collections.emptySet() : map.entrySet();
    }

    void a() {
        Queue queue;
        synchronized (this) {
            try {
                queue = this.f15836b;
                if (queue != null) {
                    this.f15836b = null;
                } else {
                    queue = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (queue != null) {
            Iterator it = queue.iterator();
            while (it.hasNext()) {
                d((Event) it.next());
            }
        }
    }

    public void d(Event event) {
        Preconditions.b(event);
        synchronized (this) {
            try {
                Queue queue = this.f15836b;
                if (queue != null) {
                    queue.add(event);
                    return;
                }
                for (Map.Entry entry : b(event)) {
                    ((Executor) entry.getValue()).execute(EventBus$$Lambda$1.a(entry, event));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
