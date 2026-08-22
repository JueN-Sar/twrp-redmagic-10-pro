package com.google.firebase.events;

/* loaded from: classes.dex */
public class Event<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Class f15886a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f15887b;

    public Class a() {
        return this.f15886a;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", this.f15886a, this.f15887b);
    }
}
