package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes.dex */
final class AutoValue_PersistedEvent extends PersistedEvent {

    /* renamed from: a, reason: collision with root package name */
    private final long f10375a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10376b;

    /* renamed from: c, reason: collision with root package name */
    private final EventInternal f10377c;

    AutoValue_PersistedEvent(long j2, TransportContext transportContext, EventInternal eventInternal) {
        this.f10375a = j2;
        if (transportContext == null) {
            throw new NullPointerException("Null transportContext");
        }
        this.f10376b = transportContext;
        if (eventInternal == null) {
            throw new NullPointerException("Null event");
        }
        this.f10377c = eventInternal;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public EventInternal b() {
        return this.f10377c;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public long c() {
        return this.f10375a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent
    public TransportContext d() {
        return this.f10376b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PersistedEvent)) {
            return false;
        }
        PersistedEvent persistedEvent = (PersistedEvent) obj;
        return this.f10375a == persistedEvent.c() && this.f10376b.equals(persistedEvent.d()) && this.f10377c.equals(persistedEvent.b());
    }

    public int hashCode() {
        long j2 = this.f10375a;
        return this.f10377c.hashCode() ^ ((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.f10376b.hashCode()) * 1000003);
    }

    public String toString() {
        return "PersistedEvent{id=" + this.f10375a + ", transportContext=" + this.f10376b + ", event=" + this.f10377c + "}";
    }
}
