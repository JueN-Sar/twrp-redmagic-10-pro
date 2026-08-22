package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.LocusId;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo
/* loaded from: classes.dex */
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {

    /* renamed from: a, reason: collision with root package name */
    private final Context f2769a;

    /* renamed from: b, reason: collision with root package name */
    private final Notification.Builder f2770b;

    /* renamed from: c, reason: collision with root package name */
    private final NotificationCompat.Builder f2771c;

    /* renamed from: d, reason: collision with root package name */
    private RemoteViews f2772d;

    /* renamed from: e, reason: collision with root package name */
    private RemoteViews f2773e;

    /* renamed from: f, reason: collision with root package name */
    private final List f2774f = new ArrayList();

    /* renamed from: g, reason: collision with root package name */
    private final Bundle f2775g = new Bundle();

    /* renamed from: h, reason: collision with root package name */
    private int f2776h;

    /* renamed from: i, reason: collision with root package name */
    private RemoteViews f2777i;

    @RequiresApi
    static class Api20Impl {
        @DoNotInline
        static Notification.Builder a(Notification.Builder builder, Notification.Action action) {
            return builder.addAction(action);
        }

        @DoNotInline
        static Notification.Action.Builder b(Notification.Action.Builder builder, Bundle bundle) {
            return builder.addExtras(bundle);
        }

        @DoNotInline
        static Notification.Action.Builder c(Notification.Action.Builder builder, android.app.RemoteInput remoteInput) {
            return builder.addRemoteInput(remoteInput);
        }

        @DoNotInline
        static Notification.Action d(Notification.Action.Builder builder) {
            return builder.build();
        }

        @DoNotInline
        static Notification.Action.Builder e(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            return new Notification.Action.Builder(i2, charSequence, pendingIntent);
        }

        @DoNotInline
        static String f(Notification notification) {
            return notification.getGroup();
        }

        @DoNotInline
        static Notification.Builder g(Notification.Builder builder, String str) {
            return builder.setGroup(str);
        }

        @DoNotInline
        static Notification.Builder h(Notification.Builder builder, boolean z) {
            return builder.setGroupSummary(z);
        }

        @DoNotInline
        static Notification.Builder i(Notification.Builder builder, boolean z) {
            return builder.setLocalOnly(z);
        }

        @DoNotInline
        static Notification.Builder j(Notification.Builder builder, String str) {
            return builder.setSortKey(str);
        }
    }

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static Notification.Builder a(Notification.Builder builder, String str) {
            return builder.addPerson(str);
        }

        @DoNotInline
        static Notification.Builder b(Notification.Builder builder, String str) {
            return builder.setCategory(str);
        }

        @DoNotInline
        static Notification.Builder c(Notification.Builder builder, int i2) {
            return builder.setColor(i2);
        }

        @DoNotInline
        static Notification.Builder d(Notification.Builder builder, Notification notification) {
            return builder.setPublicVersion(notification);
        }

        @DoNotInline
        static Notification.Builder e(Notification.Builder builder, Uri uri, Object obj) {
            return builder.setSound(uri, (AudioAttributes) obj);
        }

        @DoNotInline
        static Notification.Builder f(Notification.Builder builder, int i2) {
            return builder.setVisibility(i2);
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static Notification.Action.Builder a(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
            return new Notification.Action.Builder(icon, charSequence, pendingIntent);
        }

        @DoNotInline
        static Notification.Builder b(Notification.Builder builder, Icon icon) {
            return builder.setLargeIcon(icon);
        }

        @DoNotInline
        static Notification.Builder c(Notification.Builder builder, Object obj) {
            return builder.setSmallIcon((Icon) obj);
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z) {
            return builder.setAllowGeneratedReplies(z);
        }

        @DoNotInline
        static Notification.Builder b(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomBigContentView(remoteViews);
        }

        @DoNotInline
        static Notification.Builder c(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomContentView(remoteViews);
        }

        @DoNotInline
        static Notification.Builder d(Notification.Builder builder, RemoteViews remoteViews) {
            return builder.setCustomHeadsUpContentView(remoteViews);
        }

        @DoNotInline
        static Notification.Builder e(Notification.Builder builder, CharSequence[] charSequenceArr) {
            return builder.setRemoteInputHistory(charSequenceArr);
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static Notification.Builder a(Context context, String str) {
            return new Notification.Builder(context, str);
        }

        @DoNotInline
        static Notification.Builder b(Notification.Builder builder, int i2) {
            return builder.setBadgeIconType(i2);
        }

        @DoNotInline
        static Notification.Builder c(Notification.Builder builder, boolean z) {
            return builder.setColorized(z);
        }

        @DoNotInline
        static Notification.Builder d(Notification.Builder builder, int i2) {
            return builder.setGroupAlertBehavior(i2);
        }

        @DoNotInline
        static Notification.Builder e(Notification.Builder builder, CharSequence charSequence) {
            return builder.setSettingsText(charSequence);
        }

        @DoNotInline
        static Notification.Builder f(Notification.Builder builder, String str) {
            return builder.setShortcutId(str);
        }

        @DoNotInline
        static Notification.Builder g(Notification.Builder builder, long j2) {
            return builder.setTimeoutAfter(j2);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static Notification.Builder a(Notification.Builder builder, android.app.Person person) {
            return builder.addPerson(person);
        }

        @DoNotInline
        static Notification.Action.Builder b(Notification.Action.Builder builder, int i2) {
            return builder.setSemanticAction(i2);
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static Notification.Builder a(Notification.Builder builder, boolean z) {
            return builder.setAllowSystemGeneratedContextualActions(z);
        }

        @DoNotInline
        static Notification.Builder b(Notification.Builder builder, Notification.BubbleMetadata bubbleMetadata) {
            return builder.setBubbleMetadata(bubbleMetadata);
        }

        @DoNotInline
        static Notification.Action.Builder c(Notification.Action.Builder builder, boolean z) {
            return builder.setContextual(z);
        }

        @DoNotInline
        static Notification.Builder d(Notification.Builder builder, Object obj) {
            return builder.setLocusId((LocusId) obj);
        }
    }

    @RequiresApi
    static class Api31Impl {
        @DoNotInline
        static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z) {
            return builder.setAuthenticationRequired(z);
        }

        @DoNotInline
        static Notification.Builder b(Notification.Builder builder, int i2) {
            return builder.setForegroundServiceBehavior(i2);
        }
    }

    NotificationCompatBuilder(NotificationCompat.Builder builder) {
        this.f2771c = builder;
        Context context = builder.f2710a;
        this.f2769a = context;
        Notification.Builder a2 = Api26Impl.a(context, builder.K);
        this.f2770b = a2;
        Notification notification = builder.T;
        a2.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.f2718i).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(builder.f2714e).setContentText(builder.f2715f).setContentInfo(builder.f2720k).setContentIntent(builder.f2716g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(builder.f2717h, (notification.flags & 128) != 0).setNumber(builder.f2721l).setProgress(builder.t, builder.u, builder.v);
        IconCompat iconCompat = builder.f2719j;
        Api23Impl.b(a2, iconCompat == null ? null : iconCompat.t(context));
        a2.setSubText(builder.f2726q).setUsesChronometer(builder.f2724o).setPriority(builder.f2722m);
        NotificationCompat.Style style = builder.f2725p;
        if (style instanceof NotificationCompat.CallStyle) {
            Iterator it = ((NotificationCompat.CallStyle) style).h().iterator();
            while (it.hasNext()) {
                b((NotificationCompat.Action) it.next());
            }
        } else {
            Iterator it2 = builder.f2711b.iterator();
            while (it2.hasNext()) {
                b((NotificationCompat.Action) it2.next());
            }
        }
        Bundle bundle = builder.D;
        if (bundle != null) {
            this.f2775g.putAll(bundle);
        }
        this.f2772d = builder.H;
        this.f2773e = builder.I;
        this.f2770b.setShowWhen(builder.f2723n);
        Api20Impl.i(this.f2770b, builder.z);
        Api20Impl.g(this.f2770b, builder.w);
        Api20Impl.j(this.f2770b, builder.y);
        Api20Impl.h(this.f2770b, builder.x);
        this.f2776h = builder.P;
        Api21Impl.b(this.f2770b, builder.C);
        Api21Impl.c(this.f2770b, builder.E);
        Api21Impl.f(this.f2770b, builder.F);
        Api21Impl.d(this.f2770b, builder.G);
        Api21Impl.e(this.f2770b, notification.sound, notification.audioAttributes);
        ArrayList arrayList = builder.W;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                Api21Impl.a(this.f2770b, (String) it3.next());
            }
        }
        this.f2777i = builder.J;
        if (builder.f2713d.size() > 0) {
            Bundle bundle2 = builder.c().getBundle("android.car.EXTENSIONS");
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle(bundle2);
            Bundle bundle4 = new Bundle();
            for (int i2 = 0; i2 < builder.f2713d.size(); i2++) {
                bundle4.putBundle(Integer.toString(i2), NotificationCompatJellybean.a((NotificationCompat.Action) builder.f2713d.get(i2)));
            }
            bundle2.putBundle("invisible_actions", bundle4);
            bundle3.putBundle("invisible_actions", bundle4);
            builder.c().putBundle("android.car.EXTENSIONS", bundle2);
            this.f2775g.putBundle("android.car.EXTENSIONS", bundle3);
        }
        Object obj = builder.V;
        if (obj != null) {
            Api23Impl.c(this.f2770b, obj);
        }
        this.f2770b.setExtras(builder.D);
        Api24Impl.e(this.f2770b, builder.f2728s);
        RemoteViews remoteViews = builder.H;
        if (remoteViews != null) {
            Api24Impl.c(this.f2770b, remoteViews);
        }
        RemoteViews remoteViews2 = builder.I;
        if (remoteViews2 != null) {
            Api24Impl.b(this.f2770b, remoteViews2);
        }
        RemoteViews remoteViews3 = builder.J;
        if (remoteViews3 != null) {
            Api24Impl.d(this.f2770b, remoteViews3);
        }
        Api26Impl.b(this.f2770b, builder.L);
        Api26Impl.e(this.f2770b, builder.f2727r);
        Api26Impl.f(this.f2770b, builder.M);
        Api26Impl.g(this.f2770b, builder.O);
        Api26Impl.d(this.f2770b, builder.P);
        if (builder.B) {
            Api26Impl.c(this.f2770b, builder.A);
        }
        if (!TextUtils.isEmpty(builder.K)) {
            this.f2770b.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        Iterator it4 = builder.f2712c.iterator();
        while (it4.hasNext()) {
            Api28Impl.a(this.f2770b, ((Person) it4.next()).h());
        }
        Api29Impl.a(this.f2770b, builder.R);
        Api29Impl.b(this.f2770b, NotificationCompat.BubbleMetadata.i(builder.S));
        LocusIdCompat locusIdCompat = builder.N;
        if (locusIdCompat != null) {
            Api29Impl.d(this.f2770b, locusIdCompat.b());
        }
        int i3 = builder.Q;
        if (i3 != 0) {
            Api31Impl.b(this.f2770b, i3);
        }
        if (builder.U) {
            if (this.f2771c.x) {
                this.f2776h = 2;
            } else {
                this.f2776h = 1;
            }
            this.f2770b.setVibrate(null);
            this.f2770b.setSound(null);
            int i4 = notification.defaults & (-4);
            notification.defaults = i4;
            this.f2770b.setDefaults(i4);
            if (TextUtils.isEmpty(this.f2771c.w)) {
                Api20Impl.g(this.f2770b, "silent");
            }
            Api26Impl.d(this.f2770b, this.f2776h);
        }
    }

    private void b(NotificationCompat.Action action) {
        IconCompat d2 = action.d();
        Notification.Action.Builder a2 = Api23Impl.a(d2 != null ? d2.s() : null, action.h(), action.a());
        if (action.e() != null) {
            for (android.app.RemoteInput remoteInput : RemoteInput.b(action.e())) {
                Api20Impl.c(a2, remoteInput);
            }
        }
        Bundle bundle = action.c() != null ? new Bundle(action.c()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", action.b());
        Api24Impl.a(a2, action.b());
        bundle.putInt("android.support.action.semanticAction", action.f());
        Api28Impl.b(a2, action.f());
        Api29Impl.c(a2, action.j());
        Api31Impl.a(a2, action.i());
        bundle.putBoolean("android.support.action.showsUserInterface", action.g());
        Api20Impl.b(a2, bundle);
        Api20Impl.a(this.f2770b, Api20Impl.d(a2));
    }

    @Override // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder a() {
        return this.f2770b;
    }

    public Notification c() {
        Bundle b2;
        RemoteViews f2;
        RemoteViews d2;
        NotificationCompat.Style style = this.f2771c.f2725p;
        if (style != null) {
            style.b(this);
        }
        RemoteViews e2 = style != null ? style.e(this) : null;
        Notification d3 = d();
        if (e2 != null) {
            d3.contentView = e2;
        } else {
            RemoteViews remoteViews = this.f2771c.H;
            if (remoteViews != null) {
                d3.contentView = remoteViews;
            }
        }
        if (style != null && (d2 = style.d(this)) != null) {
            d3.bigContentView = d2;
        }
        if (style != null && (f2 = this.f2771c.f2725p.f(this)) != null) {
            d3.headsUpContentView = f2;
        }
        if (style != null && (b2 = NotificationCompat.b(d3)) != null) {
            style.a(b2);
        }
        return d3;
    }

    protected Notification d() {
        return this.f2770b.build();
    }

    Context e() {
        return this.f2769a;
    }
}
