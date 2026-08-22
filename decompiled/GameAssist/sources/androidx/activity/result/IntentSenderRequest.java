package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class IntentSenderRequest implements Parcelable {

    /* renamed from: c, reason: collision with root package name */
    private final IntentSender f119c;

    /* renamed from: h, reason: collision with root package name */
    private final Intent f120h;

    /* renamed from: i, reason: collision with root package name */
    private final int f121i;

    /* renamed from: j, reason: collision with root package name */
    private final int f122j;

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f118k = new Companion(null);

    @JvmField
    @NotNull
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new Parcelable.Creator<IntentSenderRequest>() { // from class: androidx.activity.result.IntentSenderRequest$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest createFromParcel(Parcel inParcel) {
            Intrinsics.e(inParcel, "inParcel");
            return new IntentSenderRequest(inParcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest[] newArray(int i2) {
            return new IntentSenderRequest[i2];
        }
    };

    @Metadata
    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final IntentSender f123a;

        /* renamed from: b, reason: collision with root package name */
        private Intent f124b;

        /* renamed from: c, reason: collision with root package name */
        private int f125c;

        /* renamed from: d, reason: collision with root package name */
        private int f126d;

        @Metadata
        @Retention(RetentionPolicy.SOURCE)
        @kotlin.annotation.Retention
        private @interface Flag {
        }

        public Builder(IntentSender intentSender) {
            Intrinsics.e(intentSender, "intentSender");
            this.f123a = intentSender;
        }

        public final IntentSenderRequest a() {
            return new IntentSenderRequest(this.f123a, this.f124b, this.f125c, this.f126d);
        }

        public final Builder b(Intent intent) {
            this.f124b = intent;
            return this;
        }

        public final Builder c(int i2, int i3) {
            this.f126d = i2;
            this.f125c = i3;
            return this;
        }
    }

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IntentSenderRequest(IntentSender intentSender, Intent intent, int i2, int i3) {
        Intrinsics.e(intentSender, "intentSender");
        this.f119c = intentSender;
        this.f120h = intent;
        this.f121i = i2;
        this.f122j = i3;
    }

    public final Intent a() {
        return this.f120h;
    }

    public final int b() {
        return this.f121i;
    }

    public final int d() {
        return this.f122j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final IntentSender f() {
        return this.f119c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.e(dest, "dest");
        dest.writeParcelable(this.f119c, i2);
        dest.writeParcelable(this.f120h, i2);
        dest.writeInt(this.f121i);
        dest.writeInt(this.f122j);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public IntentSenderRequest(android.os.Parcel r4) {
        /*
            r3 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.Intrinsics.e(r4, r0)
            java.lang.Class<android.content.IntentSender> r0 = android.content.IntentSender.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r4.readParcelable(r0)
            kotlin.jvm.internal.Intrinsics.b(r0)
            android.content.IntentSender r0 = (android.content.IntentSender) r0
            java.lang.Class<android.content.Intent> r1 = android.content.Intent.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            android.os.Parcelable r1 = r4.readParcelable(r1)
            android.content.Intent r1 = (android.content.Intent) r1
            int r2 = r4.readInt()
            int r4 = r4.readInt()
            r3.<init>(r0, r1, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.result.IntentSenderRequest.<init>(android.os.Parcel):void");
    }
}
