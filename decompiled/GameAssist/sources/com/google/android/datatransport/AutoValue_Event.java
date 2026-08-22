package com.google.android.datatransport;

/* loaded from: classes.dex */
final class AutoValue_Event<T> extends Event<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Integer f10052a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f10053b;

    /* renamed from: c, reason: collision with root package name */
    private final Priority f10054c;

    AutoValue_Event(Integer num, Object obj, Priority priority) {
        this.f10052a = num;
        if (obj == null) {
            throw new NullPointerException("Null payload");
        }
        this.f10053b = obj;
        if (priority == null) {
            throw new NullPointerException("Null priority");
        }
        this.f10054c = priority;
    }

    @Override // com.google.android.datatransport.Event
    public Integer a() {
        return this.f10052a;
    }

    @Override // com.google.android.datatransport.Event
    public Object b() {
        return this.f10053b;
    }

    @Override // com.google.android.datatransport.Event
    public Priority c() {
        return this.f10054c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        Integer num = this.f10052a;
        if (num != null ? num.equals(event.a()) : event.a() == null) {
            if (this.f10053b.equals(event.b()) && this.f10054c.equals(event.c())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.f10052a;
        return this.f10054c.hashCode() ^ (((((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.f10053b.hashCode()) * 1000003);
    }

    public String toString() {
        return "Event{code=" + this.f10052a + ", payload=" + this.f10053b + ", priority=" + this.f10054c + "}";
    }
}
