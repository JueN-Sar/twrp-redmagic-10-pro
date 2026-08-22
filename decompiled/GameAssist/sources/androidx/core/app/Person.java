package androidx.core.app;

import android.app.Person;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.IconCompat;
import java.util.Objects;

/* loaded from: classes.dex */
public class Person {

    /* renamed from: a, reason: collision with root package name */
    CharSequence f2808a;

    /* renamed from: b, reason: collision with root package name */
    IconCompat f2809b;

    /* renamed from: c, reason: collision with root package name */
    String f2810c;

    /* renamed from: d, reason: collision with root package name */
    String f2811d;

    /* renamed from: e, reason: collision with root package name */
    boolean f2812e;

    /* renamed from: f, reason: collision with root package name */
    boolean f2813f;

    @RequiresApi
    static class Api22Impl {
        @DoNotInline
        static Person a(PersistableBundle persistableBundle) {
            return new Builder().f(persistableBundle.getString("name")).g(persistableBundle.getString("uri")).e(persistableBundle.getString("key")).b(persistableBundle.getBoolean("isBot")).d(persistableBundle.getBoolean("isImportant")).a();
        }

        @DoNotInline
        static PersistableBundle b(Person person) {
            PersistableBundle persistableBundle = new PersistableBundle();
            CharSequence charSequence = person.f2808a;
            persistableBundle.putString("name", charSequence != null ? charSequence.toString() : null);
            persistableBundle.putString("uri", person.f2810c);
            persistableBundle.putString("key", person.f2811d);
            persistableBundle.putBoolean("isBot", person.f2812e);
            persistableBundle.putBoolean("isImportant", person.f2813f);
            return persistableBundle;
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static Person a(android.app.Person person) {
            return new Builder().f(person.getName()).c(person.getIcon() != null ? IconCompat.a(person.getIcon()) : null).g(person.getUri()).e(person.getKey()).b(person.isBot()).d(person.isImportant()).a();
        }

        @DoNotInline
        static android.app.Person b(Person person) {
            return new Person.Builder().setName(person.d()).setIcon(person.b() != null ? person.b().s() : null).setUri(person.e()).setKey(person.c()).setBot(person.f()).setImportant(person.g()).build();
        }
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        CharSequence f2814a;

        /* renamed from: b, reason: collision with root package name */
        IconCompat f2815b;

        /* renamed from: c, reason: collision with root package name */
        String f2816c;

        /* renamed from: d, reason: collision with root package name */
        String f2817d;

        /* renamed from: e, reason: collision with root package name */
        boolean f2818e;

        /* renamed from: f, reason: collision with root package name */
        boolean f2819f;

        public Person a() {
            return new Person(this);
        }

        public Builder b(boolean z) {
            this.f2818e = z;
            return this;
        }

        public Builder c(IconCompat iconCompat) {
            this.f2815b = iconCompat;
            return this;
        }

        public Builder d(boolean z) {
            this.f2819f = z;
            return this;
        }

        public Builder e(String str) {
            this.f2817d = str;
            return this;
        }

        public Builder f(CharSequence charSequence) {
            this.f2814a = charSequence;
            return this;
        }

        public Builder g(String str) {
            this.f2816c = str;
            return this;
        }
    }

    Person(Builder builder) {
        this.f2808a = builder.f2814a;
        this.f2809b = builder.f2815b;
        this.f2810c = builder.f2816c;
        this.f2811d = builder.f2817d;
        this.f2812e = builder.f2818e;
        this.f2813f = builder.f2819f;
    }

    public static Person a(PersistableBundle persistableBundle) {
        return Api22Impl.a(persistableBundle);
    }

    public IconCompat b() {
        return this.f2809b;
    }

    public String c() {
        return this.f2811d;
    }

    public CharSequence d() {
        return this.f2808a;
    }

    public String e() {
        return this.f2810c;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Person)) {
            return false;
        }
        Person person = (Person) obj;
        String c2 = c();
        String c3 = person.c();
        return (c2 == null && c3 == null) ? Objects.equals(Objects.toString(d()), Objects.toString(person.d())) && Objects.equals(e(), person.e()) && Boolean.valueOf(f()).equals(Boolean.valueOf(person.f())) && Boolean.valueOf(g()).equals(Boolean.valueOf(person.g())) : Objects.equals(c2, c3);
    }

    public boolean f() {
        return this.f2812e;
    }

    public boolean g() {
        return this.f2813f;
    }

    public android.app.Person h() {
        return Api28Impl.b(this);
    }

    public int hashCode() {
        String c2 = c();
        return c2 != null ? c2.hashCode() : Objects.hash(d(), e(), Boolean.valueOf(f()), Boolean.valueOf(g()));
    }

    public Bundle i() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("name", this.f2808a);
        IconCompat iconCompat = this.f2809b;
        bundle.putBundle("icon", iconCompat != null ? iconCompat.r() : null);
        bundle.putString("uri", this.f2810c);
        bundle.putString("key", this.f2811d);
        bundle.putBoolean("isBot", this.f2812e);
        bundle.putBoolean("isImportant", this.f2813f);
        return bundle;
    }
}
