package com.google.android.datatransport;

import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes.dex */
public abstract class Event<T> {
    public static Event d(Object obj) {
        return new AutoValue_Event(null, obj, Priority.DEFAULT);
    }

    public static Event e(Object obj) {
        return new AutoValue_Event(null, obj, Priority.VERY_LOW);
    }

    public abstract Integer a();

    public abstract Object b();

    public abstract Priority c();
}
