package cn.nubia.screensaver.card;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.bean.MomentBean;
import cn.nubia.screensaver.view.MomentBanner;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class RedMagicMomentCard extends BaseCard {
    public static final String[] A;
    public static final String[] B;
    public static final String z;
    private TextView v;
    private MomentBanner w;
    private List x;
    private final MomentBanner.PageChangeListener y = new MomentBanner.PageChangeListener() { // from class: cn.nubia.screensaver.card.RedMagicMomentCard.1
        @Override // cn.nubia.screensaver.view.MomentBanner.PageChangeListener
        public void a(String str) {
            if (TextUtils.isEmpty(str) || RedMagicMomentCard.this.v == null) {
                return;
            }
            RedMagicMomentCard.this.v.setText(str);
        }
    };

    static {
        String string = ContextWrapper.getContext().getString(R.string.red_magic_time_path);
        z = string;
        A = new String[]{string + "/%", "/storage/emulated/0/Red Magic Moment/%"};
        B = new String[]{"/storage/emulated/0/Pictures/Game Space Screenshot/%", "/storage/emulated/0/Pictures/Redmagic Time Screenshot/%"};
    }

    private List A(Context context) {
        List D = D(context, true);
        D.addAll(D(context, false));
        return (List) D.stream().sorted(Comparator.comparing(new Function() { // from class: cn.nubia.screensaver.card.d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((MomentBean) obj).c());
            }
        }).reversed()).collect(Collectors.toList());
    }

    private MomentBanner B(View view) {
        return (MomentBanner) view.findViewById(R.id.mv_banner);
    }

    private boolean C(String str) {
        if (TextUtils.isEmpty(str) || str.contains("../") || str.contains("./") || str.contains("~/")) {
            return false;
        }
        return str.startsWith("/storage") || str.startsWith("/data") || str.startsWith("file:") || str.startsWith("content:");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x007b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0078, code lost:
    
        if (r1 == null) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List D(android.content.Context r14, boolean r15) {
        /*
            r13 = this;
            android.net.Uri r0 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            java.lang.String[] r1 = cn.nubia.screensaver.card.RedMagicMomentCard.A
            r2 = 1
            if (r15 != 0) goto Lf
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            java.lang.String[] r1 = cn.nubia.screensaver.card.RedMagicMomentCard.B
            r5 = r0
            r8 = r1
            r3 = r2
            goto L12
        Lf:
            r3 = 0
            r5 = r0
            r8 = r1
        L12:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = "_id"
            java.lang.String r10 = "date_added"
            java.lang.String r11 = "_data"
            java.lang.String[] r6 = new java.lang.String[]{r1, r10, r11}
            java.lang.String r7 = "_data like ? or _data like ?"
            r1 = 0
            android.content.ContentResolver r4 = r14.getContentResolver()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.String r9 = "date_added DESC"
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            if (r1 == 0) goto L6f
            int r14 = r1.getColumnIndex(r11)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            int r4 = r1.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
        L38:
            boolean r5 = r1.moveToNext()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            if (r5 == 0) goto L6f
            java.lang.String r7 = r1.getString(r14)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            boolean r5 = r13.C(r7)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            if (r5 != 0) goto L49
            goto L38
        L49:
            java.lang.String r5 = "/"
            java.lang.String[] r5 = r7.split(r5)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            int r6 = r5.length     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            int r6 = r6 - r2
            r5 = r5[r6]     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            java.lang.String r6 = "_"
            java.lang.String[] r5 = r5.split(r6)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r9 = r5[r3]     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            long r10 = r1.getLong(r4)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            cn.nubia.screensaver.bean.MomentBean r5 = new cn.nubia.screensaver.bean.MomentBean     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r8 = 0
            r6 = r5
            r12 = r15
            r6.<init>(r7, r8, r9, r10, r12)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            r0.add(r5)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d
            goto L38
        L6b:
            r13 = move-exception
            goto L7c
        L6d:
            r13 = move-exception
            goto L75
        L6f:
            if (r1 == 0) goto L7b
        L71:
            r1.close()
            goto L7b
        L75:
            r13.printStackTrace()     // Catch: java.lang.Throwable -> L6b
            if (r1 == 0) goto L7b
            goto L71
        L7b:
            return r0
        L7c:
            if (r1 == 0) goto L81
            r1.close()
        L81:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.screensaver.card.RedMagicMomentCard.D(android.content.Context, boolean):java.util.List");
    }

    public void E(ViewGroup viewGroup) {
        if (viewGroup != null) {
            a(viewGroup);
            this.w.o();
        }
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.v = (TextView) view.findViewById(R.id.tv_app_name);
        MomentBanner B2 = B(view);
        this.w = B2;
        B2.setPageChangeListener(this.y);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.mode_redmiagic_time);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_red_magic_moment};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        List A2 = A(this.f8994h);
        this.x = A2;
        r(A2.isEmpty());
        s(this.x.isEmpty());
        GaLog.a(this.f8993c, "s " + this.x.size());
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void m() {
        super.m();
        MomentBanner momentBanner = this.w;
        if (momentBanner != null) {
            momentBanner.r();
        }
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void n(ViewGroup viewGroup, boolean z2, boolean z3) {
        super.n(viewGroup, z2, z3);
        a(viewGroup);
        this.w.r();
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void o() {
        super.o();
        E(this.f8996j);
        E(this.f8997k);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void p(ViewGroup viewGroup, boolean z2, boolean z3) {
        super.p(viewGroup, z2, z3);
        a(viewGroup);
        this.w.s();
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void q() {
        super.q();
        MomentBanner momentBanner = this.w;
        if (momentBanner != null) {
            momentBanner.s();
        }
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z2) {
        super.w(viewGroup, z2);
        this.w.setData(this.x);
        if (this.f9002p) {
            this.w.s();
        } else {
            this.w.r();
        }
    }
}
