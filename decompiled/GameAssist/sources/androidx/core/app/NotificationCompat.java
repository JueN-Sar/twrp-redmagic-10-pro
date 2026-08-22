package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.LocusId;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.annotation.ColorInt;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.content.ContextCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class NotificationCompat {

    public static class Action {

        /* renamed from: a, reason: collision with root package name */
        final Bundle f2671a;

        /* renamed from: b, reason: collision with root package name */
        private IconCompat f2672b;

        /* renamed from: c, reason: collision with root package name */
        private final RemoteInput[] f2673c;

        /* renamed from: d, reason: collision with root package name */
        private final RemoteInput[] f2674d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f2675e;

        /* renamed from: f, reason: collision with root package name */
        boolean f2676f;

        /* renamed from: g, reason: collision with root package name */
        private final int f2677g;

        /* renamed from: h, reason: collision with root package name */
        private final boolean f2678h;

        /* renamed from: i, reason: collision with root package name */
        public int f2679i;

        /* renamed from: j, reason: collision with root package name */
        public CharSequence f2680j;

        /* renamed from: k, reason: collision with root package name */
        public PendingIntent f2681k;

        /* renamed from: l, reason: collision with root package name */
        private boolean f2682l;

        public static final class Builder {

            /* renamed from: a, reason: collision with root package name */
            private final IconCompat f2683a;

            /* renamed from: b, reason: collision with root package name */
            private final CharSequence f2684b;

            /* renamed from: c, reason: collision with root package name */
            private final PendingIntent f2685c;

            /* renamed from: d, reason: collision with root package name */
            private boolean f2686d;

            /* renamed from: e, reason: collision with root package name */
            private final Bundle f2687e;

            /* renamed from: f, reason: collision with root package name */
            private ArrayList f2688f;

            /* renamed from: g, reason: collision with root package name */
            private int f2689g;

            /* renamed from: h, reason: collision with root package name */
            private boolean f2690h;

            /* renamed from: i, reason: collision with root package name */
            private boolean f2691i;

            /* renamed from: j, reason: collision with root package name */
            private boolean f2692j;

            @RequiresApi
            static class Api20Impl {
                @DoNotInline
                static Bundle a(Notification.Action action) {
                    return action.getExtras();
                }

                @DoNotInline
                static android.app.RemoteInput[] b(Notification.Action action) {
                    return action.getRemoteInputs();
                }
            }

            @RequiresApi
            static class Api23Impl {
                @DoNotInline
                static Icon a(Notification.Action action) {
                    return action.getIcon();
                }
            }

            @RequiresApi
            static class Api24Impl {
                @DoNotInline
                static boolean a(Notification.Action action) {
                    return action.getAllowGeneratedReplies();
                }
            }

            @RequiresApi
            static class Api28Impl {
                @DoNotInline
                static int a(Notification.Action action) {
                    return action.getSemanticAction();
                }
            }

            @RequiresApi
            static class Api29Impl {
                @DoNotInline
                static boolean a(Notification.Action action) {
                    return action.isContextual();
                }
            }

            @RequiresApi
            static class Api31Impl {
                @DoNotInline
                static boolean a(Notification.Action action) {
                    return action.isAuthenticationRequired();
                }
            }

            public Builder(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
                this(iconCompat, charSequence, pendingIntent, new Bundle(), null, true, 0, true, false, false);
            }

            private void b() {
                if (this.f2691i && this.f2685c == null) {
                    throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
                }
            }

            public Action a() {
                b();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = this.f2688f;
                if (arrayList3 != null) {
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        RemoteInput remoteInput = (RemoteInput) it.next();
                        if (remoteInput.j()) {
                            arrayList.add(remoteInput);
                        } else {
                            arrayList2.add(remoteInput);
                        }
                    }
                }
                return new Action(this.f2683a, this.f2684b, this.f2685c, this.f2687e, arrayList2.isEmpty() ? null : (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]), arrayList.isEmpty() ? null : (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]), this.f2686d, this.f2689g, this.f2690h, this.f2691i, this.f2692j);
            }

            private Builder(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
                this.f2686d = true;
                this.f2690h = true;
                this.f2683a = iconCompat;
                this.f2684b = Builder.d(charSequence);
                this.f2685c = pendingIntent;
                this.f2687e = bundle;
                this.f2688f = remoteInputArr == null ? null : new ArrayList(Arrays.asList(remoteInputArr));
                this.f2686d = z;
                this.f2689g = i2;
                this.f2690h = z2;
                this.f2691i = z3;
                this.f2692j = z4;
            }
        }

        public interface Extender {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public static final class WearableExtender implements Extender {

            /* renamed from: a, reason: collision with root package name */
            private int f2693a = 1;

            /* renamed from: b, reason: collision with root package name */
            private CharSequence f2694b;

            /* renamed from: c, reason: collision with root package name */
            private CharSequence f2695c;

            /* renamed from: d, reason: collision with root package name */
            private CharSequence f2696d;

            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public WearableExtender clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.f2693a = this.f2693a;
                wearableExtender.f2694b = this.f2694b;
                wearableExtender.f2695c = this.f2695c;
                wearableExtender.f2696d = this.f2696d;
                return wearableExtender;
            }
        }

        public Action(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i2 != 0 ? IconCompat.j(null, "", i2) : null, charSequence, pendingIntent);
        }

        public PendingIntent a() {
            return this.f2681k;
        }

        public boolean b() {
            return this.f2675e;
        }

        public Bundle c() {
            return this.f2671a;
        }

        public IconCompat d() {
            int i2;
            if (this.f2672b == null && (i2 = this.f2679i) != 0) {
                this.f2672b = IconCompat.j(null, "", i2);
            }
            return this.f2672b;
        }

        public RemoteInput[] e() {
            return this.f2673c;
        }

        public int f() {
            return this.f2677g;
        }

        public boolean g() {
            return this.f2676f;
        }

        public CharSequence h() {
            return this.f2680j;
        }

        public boolean i() {
            return this.f2682l;
        }

        public boolean j() {
            return this.f2678h;
        }

        public Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true, false, false);
        }

        Action(int i2, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i3, boolean z2, boolean z3, boolean z4) {
            this(i2 != 0 ? IconCompat.j(null, "", i2) : null, charSequence, pendingIntent, bundle, remoteInputArr, remoteInputArr2, z, i3, z2, z3, z4);
        }

        Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
            this.f2676f = true;
            this.f2672b = iconCompat;
            if (iconCompat != null && iconCompat.m() == 2) {
                this.f2679i = iconCompat.k();
            }
            this.f2680j = Builder.d(charSequence);
            this.f2681k = pendingIntent;
            this.f2671a = bundle == null ? new Bundle() : bundle;
            this.f2673c = remoteInputArr;
            this.f2674d = remoteInputArr2;
            this.f2675e = z;
            this.f2677g = i2;
            this.f2676f = z2;
            this.f2678h = z3;
            this.f2682l = z4;
        }
    }

    @RequiresApi
    static class Api20Impl {
        @DoNotInline
        static boolean a(android.app.RemoteInput remoteInput) {
            return remoteInput.getAllowFreeFormInput();
        }

        @DoNotInline
        static CharSequence[] b(android.app.RemoteInput remoteInput) {
            return remoteInput.getChoices();
        }

        @DoNotInline
        static Bundle c(Notification.Action action) {
            return action.getExtras();
        }

        @DoNotInline
        static Bundle d(android.app.RemoteInput remoteInput) {
            return remoteInput.getExtras();
        }

        @DoNotInline
        static String e(Notification notification) {
            return notification.getGroup();
        }

        @DoNotInline
        static CharSequence f(android.app.RemoteInput remoteInput) {
            return remoteInput.getLabel();
        }

        @DoNotInline
        static android.app.RemoteInput[] g(Notification.Action action) {
            return action.getRemoteInputs();
        }

        @DoNotInline
        static String h(android.app.RemoteInput remoteInput) {
            return remoteInput.getResultKey();
        }

        @DoNotInline
        static String i(Notification notification) {
            return notification.getSortKey();
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static Icon a(Notification.Action action) {
            return action.getIcon();
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static boolean a(Notification.Action action) {
            return action.getAllowGeneratedReplies();
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static int a(Notification notification) {
            return notification.getBadgeIconType();
        }

        @DoNotInline
        static String b(Notification notification) {
            return notification.getChannelId();
        }

        @DoNotInline
        static int c(Notification notification) {
            return notification.getGroupAlertBehavior();
        }

        @DoNotInline
        static CharSequence d(Notification notification) {
            return notification.getSettingsText();
        }

        @DoNotInline
        static String e(Notification notification) {
            return notification.getShortcutId();
        }

        @DoNotInline
        static long f(Notification notification) {
            return notification.getTimeoutAfter();
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static int a(Notification.Action action) {
            return action.getSemanticAction();
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static boolean a(Notification notification) {
            return notification.getAllowSystemGeneratedContextualActions();
        }

        @DoNotInline
        static Notification.BubbleMetadata b(Notification notification) {
            return notification.getBubbleMetadata();
        }

        @DoNotInline
        static int c(android.app.RemoteInput remoteInput) {
            return remoteInput.getEditChoicesBeforeSending();
        }

        @DoNotInline
        static LocusId d(Notification notification) {
            return notification.getLocusId();
        }

        @DoNotInline
        static boolean e(Notification.Action action) {
            return action.isContextual();
        }
    }

    @RequiresApi
    static class Api31Impl {
        @DoNotInline
        static boolean a(Notification.Action action) {
            return action.isAuthenticationRequired();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface BadgeIconType {
    }

    public static class BigPictureStyle extends Style {

        /* renamed from: e, reason: collision with root package name */
        private IconCompat f2697e;

        /* renamed from: f, reason: collision with root package name */
        private IconCompat f2698f;

        /* renamed from: g, reason: collision with root package name */
        private boolean f2699g;

        /* renamed from: h, reason: collision with root package name */
        private CharSequence f2700h;

        /* renamed from: i, reason: collision with root package name */
        private boolean f2701i;

        @RequiresApi
        private static class Api23Impl {
            @RequiresApi
            static void a(Notification.BigPictureStyle bigPictureStyle, Icon icon) {
                bigPictureStyle.bigLargeIcon(icon);
            }
        }

        @RequiresApi
        private static class Api31Impl {
            @RequiresApi
            static void a(Notification.BigPictureStyle bigPictureStyle, Icon icon) {
                bigPictureStyle.bigPicture(icon);
            }

            @RequiresApi
            static void b(Notification.BigPictureStyle bigPictureStyle, CharSequence charSequence) {
                bigPictureStyle.setContentDescription(charSequence);
            }

            @RequiresApi
            static void c(Notification.BigPictureStyle bigPictureStyle, boolean z) {
                bigPictureStyle.showBigPictureWhenCollapsed(z);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigPictureStyle bigContentTitle = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.a()).setBigContentTitle(this.f2752b);
            if (this.f2697e != null) {
                Api31Impl.a(bigContentTitle, this.f2697e.t(notificationBuilderWithBuilderAccessor instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).e() : null));
            }
            if (this.f2699g) {
                if (this.f2698f == null) {
                    bigContentTitle.bigLargeIcon((Bitmap) null);
                } else {
                    Api23Impl.a(bigContentTitle, this.f2698f.t(notificationBuilderWithBuilderAccessor instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).e() : null));
                }
            }
            if (this.f2754d) {
                bigContentTitle.setSummaryText(this.f2753c);
            }
            Api31Impl.c(bigContentTitle, this.f2701i);
            Api31Impl.b(bigContentTitle, this.f2700h);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        protected String c() {
            return "androidx.core.app.NotificationCompat$BigPictureStyle";
        }
    }

    public static class BigTextStyle extends Style {

        /* renamed from: e, reason: collision with root package name */
        private CharSequence f2702e;

        @Override // androidx.core.app.NotificationCompat.Style
        public void a(Bundle bundle) {
            super.a(bundle);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigTextStyle bigText = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.a()).setBigContentTitle(this.f2752b).bigText(this.f2702e);
            if (this.f2754d) {
                bigText.setSummaryText(this.f2753c);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        protected String c() {
            return "androidx.core.app.NotificationCompat$BigTextStyle";
        }

        public BigTextStyle h(CharSequence charSequence) {
            this.f2702e = Builder.d(charSequence);
            return this;
        }
    }

    public static final class BubbleMetadata {

        /* renamed from: a, reason: collision with root package name */
        private PendingIntent f2703a;

        /* renamed from: b, reason: collision with root package name */
        private PendingIntent f2704b;

        /* renamed from: c, reason: collision with root package name */
        private IconCompat f2705c;

        /* renamed from: d, reason: collision with root package name */
        private int f2706d;

        /* renamed from: e, reason: collision with root package name */
        private int f2707e;

        /* renamed from: f, reason: collision with root package name */
        private int f2708f;

        /* renamed from: g, reason: collision with root package name */
        private String f2709g;

        @RequiresApi
        private static class Api29Impl {
        }

        @RequiresApi
        private static class Api30Impl {
            @Nullable
            @RequiresApi
            static Notification.BubbleMetadata a(@Nullable BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder builder = bubbleMetadata.g() != null ? new Notification.BubbleMetadata.Builder(bubbleMetadata.g()) : new Notification.BubbleMetadata.Builder(bubbleMetadata.f(), bubbleMetadata.e().s());
                builder.setDeleteIntent(bubbleMetadata.b()).setAutoExpandBubble(bubbleMetadata.a()).setSuppressNotification(bubbleMetadata.h());
                if (bubbleMetadata.c() != 0) {
                    builder.setDesiredHeight(bubbleMetadata.c());
                }
                if (bubbleMetadata.d() != 0) {
                    builder.setDesiredHeightResId(bubbleMetadata.d());
                }
                return builder.build();
            }
        }

        public static final class Builder {
        }

        public static Notification.BubbleMetadata i(BubbleMetadata bubbleMetadata) {
            if (bubbleMetadata == null) {
                return null;
            }
            return Api30Impl.a(bubbleMetadata);
        }

        public boolean a() {
            return (this.f2708f & 1) != 0;
        }

        public PendingIntent b() {
            return this.f2704b;
        }

        public int c() {
            return this.f2706d;
        }

        public int d() {
            return this.f2707e;
        }

        public IconCompat e() {
            return this.f2705c;
        }

        public PendingIntent f() {
            return this.f2703a;
        }

        public String g() {
            return this.f2709g;
        }

        public boolean h() {
            return (this.f2708f & 2) != 0;
        }
    }

    public static class CallStyle extends Style {

        /* renamed from: e, reason: collision with root package name */
        private int f2729e;

        /* renamed from: f, reason: collision with root package name */
        private Person f2730f;

        /* renamed from: g, reason: collision with root package name */
        private PendingIntent f2731g;

        /* renamed from: h, reason: collision with root package name */
        private PendingIntent f2732h;

        /* renamed from: i, reason: collision with root package name */
        private PendingIntent f2733i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f2734j;

        /* renamed from: k, reason: collision with root package name */
        private Integer f2735k;

        /* renamed from: l, reason: collision with root package name */
        private Integer f2736l;

        /* renamed from: m, reason: collision with root package name */
        private IconCompat f2737m;

        /* renamed from: n, reason: collision with root package name */
        private CharSequence f2738n;

        @RequiresApi
        static class Api20Impl {
            @DoNotInline
            static Notification.Action.Builder a(Notification.Action.Builder builder, Bundle bundle) {
                return builder.addExtras(bundle);
            }

            @DoNotInline
            static Notification.Action.Builder b(Notification.Action.Builder builder, android.app.RemoteInput remoteInput) {
                return builder.addRemoteInput(remoteInput);
            }

            @DoNotInline
            static Notification.Action c(Notification.Action.Builder builder) {
                return builder.build();
            }

            @DoNotInline
            static Notification.Action.Builder d(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(i2, charSequence, pendingIntent);
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
        }

        @RequiresApi
        static class Api23Impl {
            @DoNotInline
            static Parcelable a(Icon icon) {
                return icon;
            }

            @DoNotInline
            static Notification.Action.Builder b(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(icon, charSequence, pendingIntent);
            }

            @DoNotInline
            static void c(Notification.Builder builder, Icon icon) {
                builder.setLargeIcon(icon);
            }
        }

        @RequiresApi
        static class Api24Impl {
            @DoNotInline
            static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z) {
                return builder.setAllowGeneratedReplies(z);
            }
        }

        @RequiresApi
        static class Api28Impl {
            @DoNotInline
            static Notification.Builder a(Notification.Builder builder, android.app.Person person) {
                return builder.addPerson(person);
            }

            @DoNotInline
            static Parcelable b(android.app.Person person) {
                return person;
            }
        }

        @RequiresApi
        static class Api31Impl {
            @DoNotInline
            static Notification.CallStyle a(@NonNull android.app.Person person, @NonNull PendingIntent pendingIntent, @NonNull PendingIntent pendingIntent2) {
                return Notification.CallStyle.forIncomingCall(person, pendingIntent, pendingIntent2);
            }

            @DoNotInline
            static Notification.CallStyle b(@NonNull android.app.Person person, @NonNull PendingIntent pendingIntent) {
                return Notification.CallStyle.forOngoingCall(person, pendingIntent);
            }

            @DoNotInline
            static Notification.CallStyle c(@NonNull android.app.Person person, @NonNull PendingIntent pendingIntent, @NonNull PendingIntent pendingIntent2) {
                return Notification.CallStyle.forScreeningCall(person, pendingIntent, pendingIntent2);
            }

            @DoNotInline
            static Notification.CallStyle d(Notification.CallStyle callStyle, @ColorInt int i2) {
                return callStyle.setAnswerButtonColorHint(i2);
            }

            @DoNotInline
            static Notification.Action.Builder e(Notification.Action.Builder builder, boolean z) {
                return builder.setAuthenticationRequired(z);
            }

            @DoNotInline
            static Notification.CallStyle f(Notification.CallStyle callStyle, @ColorInt int i2) {
                return callStyle.setDeclineButtonColorHint(i2);
            }

            @DoNotInline
            static Notification.CallStyle g(Notification.CallStyle callStyle, boolean z) {
                return callStyle.setIsVideo(z);
            }

            @DoNotInline
            static Notification.CallStyle h(Notification.CallStyle callStyle, @Nullable Icon icon) {
                return callStyle.setVerificationIcon(icon);
            }

            @DoNotInline
            static Notification.CallStyle i(Notification.CallStyle callStyle, @Nullable CharSequence charSequence) {
                return callStyle.setVerificationText(charSequence);
            }
        }

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface CallType {
        }

        private boolean i(Action action) {
            return action != null && action.c().getBoolean("key_action_priority");
        }

        private Action j(int i2, int i3, Integer num, int i4, PendingIntent pendingIntent) {
            if (num == null) {
                num = Integer.valueOf(ContextCompat.c(this.f2751a.f2710a, i4));
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) this.f2751a.f2710a.getResources().getString(i3));
            spannableStringBuilder.setSpan(new ForegroundColorSpan(num.intValue()), 0, spannableStringBuilder.length(), 18);
            Action a2 = new Action.Builder(IconCompat.i(this.f2751a.f2710a, i2), spannableStringBuilder, pendingIntent).a();
            a2.c().putBoolean("key_action_priority", true);
            return a2;
        }

        private Action k() {
            int i2 = R.drawable.ic_call_answer_video;
            int i3 = R.drawable.ic_call_answer;
            PendingIntent pendingIntent = this.f2731g;
            if (pendingIntent == null) {
                return null;
            }
            boolean z = this.f2734j;
            return j(z ? i2 : i3, z ? R.string.call_notification_answer_video_action : R.string.call_notification_answer_action, this.f2735k, R.color.call_notification_answer_color, pendingIntent);
        }

        private Action l() {
            int i2 = R.drawable.ic_call_decline;
            PendingIntent pendingIntent = this.f2732h;
            return pendingIntent == null ? j(i2, R.string.call_notification_hang_up_action, this.f2736l, R.color.call_notification_decline_color, this.f2733i) : j(i2, R.string.call_notification_decline_action, this.f2736l, R.color.call_notification_decline_color, pendingIntent);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void a(Bundle bundle) {
            super.a(bundle);
            bundle.putInt("android.callType", this.f2729e);
            bundle.putBoolean("android.callIsVideo", this.f2734j);
            Person person = this.f2730f;
            if (person != null) {
                bundle.putParcelable("android.callPerson", Api28Impl.b(person.h()));
            }
            IconCompat iconCompat = this.f2737m;
            if (iconCompat != null) {
                bundle.putParcelable("android.verificationIcon", Api23Impl.a(iconCompat.t(this.f2751a.f2710a)));
            }
            bundle.putCharSequence("android.verificationText", this.f2738n);
            bundle.putParcelable("android.answerIntent", this.f2731g);
            bundle.putParcelable("android.declineIntent", this.f2732h);
            bundle.putParcelable("android.hangUpIntent", this.f2733i);
            Integer num = this.f2735k;
            if (num != null) {
                bundle.putInt("android.answerColor", num.intValue());
            }
            Integer num2 = this.f2736l;
            if (num2 != null) {
                bundle.putInt("android.declineColor", num2.intValue());
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.CallStyle a2;
            int i2 = this.f2729e;
            if (i2 == 1) {
                a2 = Api31Impl.a(this.f2730f.h(), this.f2732h, this.f2731g);
            } else if (i2 == 2) {
                a2 = Api31Impl.b(this.f2730f.h(), this.f2733i);
            } else if (i2 != 3) {
                if (Log.isLoggable("NotifCompat", 3)) {
                    Log.d("NotifCompat", "Unrecognized call type in CallStyle: " + String.valueOf(this.f2729e));
                }
                a2 = null;
            } else {
                a2 = Api31Impl.c(this.f2730f.h(), this.f2733i, this.f2731g);
            }
            if (a2 != null) {
                a2.setBuilder(notificationBuilderWithBuilderAccessor.a());
                Integer num = this.f2735k;
                if (num != null) {
                    Api31Impl.d(a2, num.intValue());
                }
                Integer num2 = this.f2736l;
                if (num2 != null) {
                    Api31Impl.f(a2, num2.intValue());
                }
                Api31Impl.i(a2, this.f2738n);
                IconCompat iconCompat = this.f2737m;
                if (iconCompat != null) {
                    Api31Impl.h(a2, iconCompat.t(this.f2751a.f2710a));
                }
                Api31Impl.g(a2, this.f2734j);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        protected String c() {
            return "androidx.core.app.NotificationCompat$CallStyle";
        }

        public ArrayList h() {
            Action l2 = l();
            Action k2 = k();
            ArrayList arrayList = new ArrayList(3);
            arrayList.add(l2);
            ArrayList<Action> arrayList2 = this.f2751a.f2711b;
            int i2 = 2;
            if (arrayList2 != null) {
                for (Action action : arrayList2) {
                    if (action.j()) {
                        arrayList.add(action);
                    } else if (!i(action) && i2 > 1) {
                        arrayList.add(action);
                        i2--;
                    }
                    if (k2 != null && i2 == 1) {
                        arrayList.add(k2);
                        i2--;
                    }
                }
            }
            if (k2 != null && i2 >= 1) {
                arrayList.add(k2);
            }
            return arrayList;
        }
    }

    public static final class CarExtender implements Extender {

        @RequiresApi
        static class Api20Impl {
            @DoNotInline
            static RemoteInput.Builder a(RemoteInput.Builder builder, Bundle bundle) {
                return builder.addExtras(bundle);
            }

            @DoNotInline
            static android.app.RemoteInput b(RemoteInput.Builder builder) {
                return builder.build();
            }

            @DoNotInline
            static Parcelable c(android.app.RemoteInput remoteInput) {
                return remoteInput;
            }

            @DoNotInline
            static RemoteInput.Builder d(String str) {
                return new RemoteInput.Builder(str);
            }

            @DoNotInline
            static boolean e(android.app.RemoteInput remoteInput) {
                return remoteInput.getAllowFreeFormInput();
            }

            @DoNotInline
            static CharSequence[] f(android.app.RemoteInput remoteInput) {
                return remoteInput.getChoices();
            }

            @DoNotInline
            static Bundle g(android.app.RemoteInput remoteInput) {
                return remoteInput.getExtras();
            }

            @DoNotInline
            static CharSequence h(android.app.RemoteInput remoteInput) {
                return remoteInput.getLabel();
            }

            @DoNotInline
            static String i(android.app.RemoteInput remoteInput) {
                return remoteInput.getResultKey();
            }

            @DoNotInline
            static RemoteInput.Builder j(RemoteInput.Builder builder, boolean z) {
                return builder.setAllowFreeFormInput(z);
            }

            @DoNotInline
            static RemoteInput.Builder k(RemoteInput.Builder builder, CharSequence[] charSequenceArr) {
                return builder.setChoices(charSequenceArr);
            }

            @DoNotInline
            static RemoteInput.Builder l(RemoteInput.Builder builder, CharSequence charSequence) {
                return builder.setLabel(charSequence);
            }
        }

        @RequiresApi
        static class Api29Impl {
            @DoNotInline
            static int a(android.app.RemoteInput remoteInput) {
                return remoteInput.getEditChoicesBeforeSending();
            }
        }

        @Deprecated
        public static class UnreadConversation {

            public static class Builder {
            }
        }
    }

    public static class DecoratedCustomViewStyle extends Style {

        @RequiresApi
        static class Api24Impl {
            @DoNotInline
            static Notification.Style a() {
                return new Notification.DecoratedCustomViewStyle();
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            notificationBuilderWithBuilderAccessor.a().setStyle(Api24Impl.a());
        }

        @Override // androidx.core.app.NotificationCompat.Style
        protected String c() {
            return "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews d(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews e(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews f(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }
    }

    public interface Extender {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface GroupAlertBehavior {
    }

    public static class InboxStyle extends Style {

        /* renamed from: e, reason: collision with root package name */
        private ArrayList f2739e;

        @Override // androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor.a()).setBigContentTitle(this.f2752b);
            if (this.f2754d) {
                bigContentTitle.setSummaryText(this.f2753c);
            }
            Iterator it = this.f2739e.iterator();
            while (it.hasNext()) {
                bigContentTitle.addLine((CharSequence) it.next());
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        protected String c() {
            return "androidx.core.app.NotificationCompat$InboxStyle";
        }
    }

    public static class MessagingStyle extends Style {

        /* renamed from: e, reason: collision with root package name */
        private final List f2740e;

        /* renamed from: f, reason: collision with root package name */
        private final List f2741f;

        /* renamed from: g, reason: collision with root package name */
        private Person f2742g;

        /* renamed from: h, reason: collision with root package name */
        private CharSequence f2743h;

        /* renamed from: i, reason: collision with root package name */
        private Boolean f2744i;

        @RequiresApi
        static class Api24Impl {
            @DoNotInline
            static Notification.MessagingStyle a(Notification.MessagingStyle messagingStyle, Notification.MessagingStyle.Message message) {
                return messagingStyle.addMessage(message);
            }

            @DoNotInline
            static Notification.MessagingStyle b(CharSequence charSequence) {
                return new Notification.MessagingStyle(charSequence);
            }

            @DoNotInline
            static Notification.MessagingStyle c(Notification.MessagingStyle messagingStyle, CharSequence charSequence) {
                return messagingStyle.setConversationTitle(charSequence);
            }
        }

        @RequiresApi
        static class Api26Impl {
            @DoNotInline
            static Notification.MessagingStyle a(Notification.MessagingStyle messagingStyle, Notification.MessagingStyle.Message message) {
                return messagingStyle.addHistoricMessage(message);
            }
        }

        @RequiresApi
        static class Api28Impl {
            @DoNotInline
            static Notification.MessagingStyle a(android.app.Person person) {
                return new Notification.MessagingStyle(person);
            }

            @DoNotInline
            static Notification.MessagingStyle b(Notification.MessagingStyle messagingStyle, boolean z) {
                return messagingStyle.setGroupConversation(z);
            }
        }

        public static final class Message {

            /* renamed from: a, reason: collision with root package name */
            private final CharSequence f2745a;

            /* renamed from: b, reason: collision with root package name */
            private final long f2746b;

            /* renamed from: c, reason: collision with root package name */
            private final Person f2747c;

            /* renamed from: d, reason: collision with root package name */
            private Bundle f2748d;

            /* renamed from: e, reason: collision with root package name */
            private String f2749e;

            /* renamed from: f, reason: collision with root package name */
            private Uri f2750f;

            @RequiresApi
            static class Api24Impl {
                @DoNotInline
                static Notification.MessagingStyle.Message a(CharSequence charSequence, long j2, CharSequence charSequence2) {
                    return new Notification.MessagingStyle.Message(charSequence, j2, charSequence2);
                }

                @DoNotInline
                static Notification.MessagingStyle.Message b(Notification.MessagingStyle.Message message, String str, Uri uri) {
                    return message.setData(str, uri);
                }
            }

            @RequiresApi
            static class Api28Impl {
                @DoNotInline
                static Parcelable a(android.app.Person person) {
                    return person;
                }

                @DoNotInline
                static Notification.MessagingStyle.Message b(CharSequence charSequence, long j2, android.app.Person person) {
                    return new Notification.MessagingStyle.Message(charSequence, j2, person);
                }
            }

            static Bundle[] a(List list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    bundleArr[i2] = ((Message) list.get(i2)).h();
                }
                return bundleArr;
            }

            private Bundle h() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.f2745a;
                if (charSequence != null) {
                    bundle.putCharSequence("text", charSequence);
                }
                bundle.putLong("time", this.f2746b);
                Person person = this.f2747c;
                if (person != null) {
                    bundle.putCharSequence("sender", person.d());
                    bundle.putParcelable("sender_person", Api28Impl.a(this.f2747c.h()));
                }
                String str = this.f2749e;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.f2750f;
                if (uri != null) {
                    bundle.putParcelable("uri", uri);
                }
                Bundle bundle2 = this.f2748d;
                if (bundle2 != null) {
                    bundle.putBundle("extras", bundle2);
                }
                return bundle;
            }

            public String b() {
                return this.f2749e;
            }

            public Uri c() {
                return this.f2750f;
            }

            public Person d() {
                return this.f2747c;
            }

            public CharSequence e() {
                return this.f2745a;
            }

            public long f() {
                return this.f2746b;
            }

            Notification.MessagingStyle.Message g() {
                Person d2 = d();
                Notification.MessagingStyle.Message b2 = Api28Impl.b(e(), f(), d2 == null ? null : d2.h());
                if (b() != null) {
                    Api24Impl.b(b2, b(), c());
                }
                return b2;
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void a(Bundle bundle) {
            super.a(bundle);
            bundle.putCharSequence("android.selfDisplayName", this.f2742g.d());
            bundle.putBundle("android.messagingStyleUser", this.f2742g.i());
            bundle.putCharSequence("android.hiddenConversationTitle", this.f2743h);
            if (this.f2743h != null && this.f2744i.booleanValue()) {
                bundle.putCharSequence("android.conversationTitle", this.f2743h);
            }
            if (!this.f2740e.isEmpty()) {
                bundle.putParcelableArray("android.messages", Message.a(this.f2740e));
            }
            if (!this.f2741f.isEmpty()) {
                bundle.putParcelableArray("android.messages.historic", Message.a(this.f2741f));
            }
            Boolean bool = this.f2744i;
            if (bool != null) {
                bundle.putBoolean("android.isGroupConversation", bool.booleanValue());
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            i(h());
            Notification.MessagingStyle a2 = Api28Impl.a(this.f2742g.h());
            Iterator it = this.f2740e.iterator();
            while (it.hasNext()) {
                Api24Impl.a(a2, ((Message) it.next()).g());
            }
            Iterator it2 = this.f2741f.iterator();
            while (it2.hasNext()) {
                Api26Impl.a(a2, ((Message) it2.next()).g());
            }
            this.f2744i.booleanValue();
            Api24Impl.c(a2, this.f2743h);
            Api28Impl.b(a2, this.f2744i.booleanValue());
            a2.setBuilder(notificationBuilderWithBuilderAccessor.a());
        }

        @Override // androidx.core.app.NotificationCompat.Style
        protected String c() {
            return "androidx.core.app.NotificationCompat$MessagingStyle";
        }

        public boolean h() {
            Builder builder = this.f2751a;
            if (builder != null && builder.f2710a.getApplicationInfo().targetSdkVersion < 28 && this.f2744i == null) {
                return this.f2743h != null;
            }
            Boolean bool = this.f2744i;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        public MessagingStyle i(boolean z) {
            this.f2744i = Boolean.valueOf(z);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface NotificationVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ServiceNotificationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface StreamType {
    }

    public static abstract class Style {

        /* renamed from: a, reason: collision with root package name */
        protected Builder f2751a;

        /* renamed from: b, reason: collision with root package name */
        CharSequence f2752b;

        /* renamed from: c, reason: collision with root package name */
        CharSequence f2753c;

        /* renamed from: d, reason: collision with root package name */
        boolean f2754d = false;

        @RequiresApi
        static class Api24Impl {
            @DoNotInline
            static void a(RemoteViews remoteViews, int i2, boolean z) {
                remoteViews.setChronometerCountDown(i2, z);
            }
        }

        public void a(Bundle bundle) {
            if (this.f2754d) {
                bundle.putCharSequence("android.summaryText", this.f2753c);
            }
            CharSequence charSequence = this.f2752b;
            if (charSequence != null) {
                bundle.putCharSequence("android.title.big", charSequence);
            }
            String c2 = c();
            if (c2 != null) {
                bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", c2);
            }
        }

        public void b(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        protected String c() {
            return null;
        }

        public RemoteViews d(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews e(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews f(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public void g(Builder builder) {
            if (this.f2751a != builder) {
                this.f2751a = builder;
                if (builder != null) {
                    builder.n(this);
                }
            }
        }
    }

    public static final class TvExtender implements Extender {
    }

    public static final class WearableExtender implements Extender {

        /* renamed from: c, reason: collision with root package name */
        private PendingIntent f2757c;

        /* renamed from: e, reason: collision with root package name */
        private Bitmap f2759e;

        /* renamed from: f, reason: collision with root package name */
        private int f2760f;

        /* renamed from: j, reason: collision with root package name */
        private int f2764j;

        /* renamed from: l, reason: collision with root package name */
        private int f2766l;

        /* renamed from: m, reason: collision with root package name */
        private String f2767m;

        /* renamed from: n, reason: collision with root package name */
        private String f2768n;

        /* renamed from: a, reason: collision with root package name */
        private ArrayList f2755a = new ArrayList();

        /* renamed from: b, reason: collision with root package name */
        private int f2756b = 1;

        /* renamed from: d, reason: collision with root package name */
        private ArrayList f2758d = new ArrayList();

        /* renamed from: g, reason: collision with root package name */
        private int f2761g = 8388613;

        /* renamed from: h, reason: collision with root package name */
        private int f2762h = -1;

        /* renamed from: i, reason: collision with root package name */
        private int f2763i = 0;

        /* renamed from: k, reason: collision with root package name */
        private int f2765k = 80;

        @RequiresApi
        static class Api20Impl {
            @DoNotInline
            static Notification.Action.Builder a(Notification.Action.Builder builder, Bundle bundle) {
                return builder.addExtras(bundle);
            }

            @DoNotInline
            static Notification.Action.Builder b(Notification.Action.Builder builder, android.app.RemoteInput remoteInput) {
                return builder.addRemoteInput(remoteInput);
            }

            @DoNotInline
            static Notification.Action c(Notification.Action.Builder builder) {
                return builder.build();
            }

            @DoNotInline
            static Notification.Action.Builder d(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(i2, charSequence, pendingIntent);
            }

            @DoNotInline
            public static Action e(ArrayList<Parcelable> arrayList, int i2) {
                return NotificationCompat.a((Notification.Action) arrayList.get(i2));
            }
        }

        @RequiresApi
        static class Api23Impl {
            @DoNotInline
            static Notification.Action.Builder a(Icon icon, CharSequence charSequence, PendingIntent pendingIntent) {
                return new Notification.Action.Builder(icon, charSequence, pendingIntent);
            }
        }

        @RequiresApi
        static class Api24Impl {
            @DoNotInline
            static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z) {
                return builder.setAllowGeneratedReplies(z);
            }
        }

        @RequiresApi
        static class Api31Impl {
            @DoNotInline
            static Notification.Action.Builder a(Notification.Action.Builder builder, boolean z) {
                return builder.setAuthenticationRequired(z);
            }
        }

        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public WearableExtender clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.f2755a = new ArrayList(this.f2755a);
            wearableExtender.f2756b = this.f2756b;
            wearableExtender.f2757c = this.f2757c;
            wearableExtender.f2758d = new ArrayList(this.f2758d);
            wearableExtender.f2759e = this.f2759e;
            wearableExtender.f2760f = this.f2760f;
            wearableExtender.f2761g = this.f2761g;
            wearableExtender.f2762h = this.f2762h;
            wearableExtender.f2763i = this.f2763i;
            wearableExtender.f2764j = this.f2764j;
            wearableExtender.f2765k = this.f2765k;
            wearableExtender.f2766l = this.f2766l;
            wearableExtender.f2767m = this.f2767m;
            wearableExtender.f2768n = this.f2768n;
            return wearableExtender;
        }
    }

    static Action a(Notification.Action action) {
        RemoteInput[] remoteInputArr;
        int i2;
        android.app.RemoteInput[] g2 = Api20Impl.g(action);
        if (g2 == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[g2.length];
            for (int i3 = 0; i3 < g2.length; i3++) {
                android.app.RemoteInput remoteInput = g2[i3];
                remoteInputArr2[i3] = new RemoteInput(Api20Impl.h(remoteInput), Api20Impl.f(remoteInput), Api20Impl.b(remoteInput), Api20Impl.a(remoteInput), Api29Impl.c(remoteInput), Api20Impl.d(remoteInput), null);
            }
            remoteInputArr = remoteInputArr2;
        }
        boolean z = Api20Impl.c(action).getBoolean("android.support.allowGeneratedReplies") || Api24Impl.a(action);
        boolean z2 = Api20Impl.c(action).getBoolean("android.support.action.showsUserInterface", true);
        int a2 = Api28Impl.a(action);
        boolean e2 = Api29Impl.e(action);
        boolean a3 = Api31Impl.a(action);
        if (Api23Impl.a(action) != null || (i2 = action.icon) == 0) {
            return new Action(Api23Impl.a(action) != null ? IconCompat.b(Api23Impl.a(action)) : null, action.title, action.actionIntent, Api20Impl.c(action), remoteInputArr, (RemoteInput[]) null, z, a2, z2, e2, a3);
        }
        return new Action(i2, action.title, action.actionIntent, Api20Impl.c(action), remoteInputArr, (RemoteInput[]) null, z, a2, z2, e2, a3);
    }

    public static Bundle b(Notification notification) {
        return notification.extras;
    }

    public static class Builder {
        boolean A;
        boolean B;
        String C;
        Bundle D;
        int E;
        int F;
        Notification G;
        RemoteViews H;
        RemoteViews I;
        RemoteViews J;
        String K;
        int L;
        String M;
        LocusIdCompat N;
        long O;
        int P;
        int Q;
        boolean R;
        BubbleMetadata S;
        Notification T;
        boolean U;
        Object V;
        public ArrayList W;

        /* renamed from: a, reason: collision with root package name */
        public Context f2710a;

        /* renamed from: b, reason: collision with root package name */
        public ArrayList f2711b;

        /* renamed from: c, reason: collision with root package name */
        public ArrayList f2712c;

        /* renamed from: d, reason: collision with root package name */
        ArrayList f2713d;

        /* renamed from: e, reason: collision with root package name */
        CharSequence f2714e;

        /* renamed from: f, reason: collision with root package name */
        CharSequence f2715f;

        /* renamed from: g, reason: collision with root package name */
        PendingIntent f2716g;

        /* renamed from: h, reason: collision with root package name */
        PendingIntent f2717h;

        /* renamed from: i, reason: collision with root package name */
        RemoteViews f2718i;

        /* renamed from: j, reason: collision with root package name */
        IconCompat f2719j;

        /* renamed from: k, reason: collision with root package name */
        CharSequence f2720k;

        /* renamed from: l, reason: collision with root package name */
        int f2721l;

        /* renamed from: m, reason: collision with root package name */
        int f2722m;

        /* renamed from: n, reason: collision with root package name */
        boolean f2723n;

        /* renamed from: o, reason: collision with root package name */
        boolean f2724o;

        /* renamed from: p, reason: collision with root package name */
        Style f2725p;

        /* renamed from: q, reason: collision with root package name */
        CharSequence f2726q;

        /* renamed from: r, reason: collision with root package name */
        CharSequence f2727r;

        /* renamed from: s, reason: collision with root package name */
        CharSequence[] f2728s;
        int t;
        int u;
        boolean v;
        String w;
        boolean x;
        String y;
        boolean z;

        @RequiresApi
        static class Api21Impl {
            @DoNotInline
            static AudioAttributes a(AudioAttributes.Builder builder) {
                return builder.build();
            }

            @DoNotInline
            static AudioAttributes.Builder b() {
                return new AudioAttributes.Builder();
            }

            @DoNotInline
            static AudioAttributes.Builder c(AudioAttributes.Builder builder, int i2) {
                return builder.setContentType(i2);
            }

            @DoNotInline
            static AudioAttributes.Builder d(AudioAttributes.Builder builder, int i2) {
                return builder.setLegacyStreamType(i2);
            }

            @DoNotInline
            static AudioAttributes.Builder e(AudioAttributes.Builder builder, int i2) {
                return builder.setUsage(i2);
            }
        }

        @RequiresApi
        static class Api23Impl {
            @DoNotInline
            static Icon a(Notification notification) {
                return notification.getLargeIcon();
            }

            @DoNotInline
            static Icon b(Notification notification) {
                return notification.getSmallIcon();
            }
        }

        @RequiresApi
        static class Api24Impl {
            @DoNotInline
            static RemoteViews a(Notification.Builder builder) {
                return builder.createHeadsUpContentView();
            }

            @DoNotInline
            static RemoteViews b(Notification.Builder builder) {
                return builder.createContentView();
            }

            @DoNotInline
            static RemoteViews c(Notification.Builder builder) {
                return builder.createHeadsUpContentView();
            }

            @DoNotInline
            static Notification.Builder d(Context context, Notification notification) {
                return Notification.Builder.recoverBuilder(context, notification);
            }
        }

        public Builder(Context context, String str) {
            this.f2711b = new ArrayList();
            this.f2712c = new ArrayList();
            this.f2713d = new ArrayList();
            this.f2723n = true;
            this.z = false;
            this.E = 0;
            this.F = 0;
            this.L = 0;
            this.P = 0;
            this.Q = 0;
            Notification notification = new Notification();
            this.T = notification;
            this.f2710a = context;
            this.K = str;
            notification.when = System.currentTimeMillis();
            this.T.audioStreamType = -1;
            this.f2722m = 0;
            this.W = new ArrayList();
            this.R = true;
        }

        protected static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        private void j(int i2, boolean z) {
            if (z) {
                Notification notification = this.T;
                notification.flags = i2 | notification.flags;
            } else {
                Notification notification2 = this.T;
                notification2.flags = (~i2) & notification2.flags;
            }
        }

        public Builder a(int i2, CharSequence charSequence, PendingIntent pendingIntent) {
            this.f2711b.add(new Action(i2, charSequence, pendingIntent));
            return this;
        }

        public Notification b() {
            return new NotificationCompatBuilder(this).c();
        }

        public Bundle c() {
            if (this.D == null) {
                this.D = new Bundle();
            }
            return this.D;
        }

        public Builder e(boolean z) {
            j(16, z);
            return this;
        }

        public Builder f(String str) {
            this.K = str;
            return this;
        }

        public Builder g(PendingIntent pendingIntent) {
            this.f2716g = pendingIntent;
            return this;
        }

        public Builder h(CharSequence charSequence) {
            this.f2715f = d(charSequence);
            return this;
        }

        public Builder i(CharSequence charSequence) {
            this.f2714e = d(charSequence);
            return this;
        }

        public Builder k(boolean z) {
            this.z = z;
            return this;
        }

        public Builder l(int i2) {
            this.f2722m = i2;
            return this;
        }

        public Builder m(int i2) {
            this.T.icon = i2;
            return this;
        }

        public Builder n(Style style) {
            if (this.f2725p != style) {
                this.f2725p = style;
                if (style != null) {
                    style.g(this);
                }
            }
            return this;
        }

        public Builder o(CharSequence charSequence) {
            this.T.tickerText = d(charSequence);
            return this;
        }

        public Builder p(long j2) {
            this.T.when = j2;
            return this;
        }

        public Builder(Context context) {
            this(context, null);
        }
    }
}
