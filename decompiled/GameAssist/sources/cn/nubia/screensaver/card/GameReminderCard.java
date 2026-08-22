package cn.nubia.screensaver.card;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.bean.GameReminderBean;
import cn.nubia.screensaver.util.DefaultUtil;
import cn.nubia.screensaver.util.ShortCutUtil;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.SystemMgr;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameReminderCard extends BaseCard {
    public static final Uri B = Uri.parse("content://com.zte.plugin.reminder");
    private int A;
    private final List v = new ArrayList();
    private int w;
    private int x;
    private int y;
    private int z;

    private String A(long j2) {
        return new SimpleDateFormat(this.f8994h.getString(R.string.game_reminder_card_time), Locale.getDefault()).format(new Date(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(ViewGroup viewGroup, boolean z, GameReminderBean gameReminderBean) {
        String charSequence;
        int childCount = viewGroup.getChildCount();
        Drawable drawable = null;
        View inflate = View.inflate(this.f8994h, R.layout.card_game_reminder_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_app_name);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_reminder_date);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_app_icon);
        try {
            if (SystemMgr.M(gameReminderBean.f8982a)) {
                GameCheck.WechatMiniAppInfo wechatMiniAppInfo = (GameCheck.WechatMiniAppInfo) GameCheck.e(gameReminderBean.f8982a);
                if (wechatMiniAppInfo != null) {
                    String g2 = wechatMiniAppInfo.g();
                    Drawable c2 = ShortCutUtil.b().c(wechatMiniAppInfo);
                    charSequence = g2;
                    drawable = c2;
                } else {
                    charSequence = null;
                }
            } else {
                PackageManager packageManager = this.f8994h.getPackageManager();
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(gameReminderBean.f8982a, 128);
                Drawable loadIcon = applicationInfo.loadIcon(packageManager);
                charSequence = applicationInfo.loadLabel(packageManager).toString();
                drawable = loadIcon;
            }
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
            if (!TextUtils.isEmpty(charSequence)) {
                textView.setText(charSequence);
            }
            String A = A(gameReminderBean.f8983b);
            if (!TextUtils.isEmpty(A)) {
                textView2.setText(A);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.w, this.x);
            layoutParams.topMargin = childCount == 0 ? 0 : this.A;
            if (z) {
                layoutParams.gravity = 8388611;
                layoutParams.leftMargin = ((2 - childCount) * this.z) + this.y;
            } else {
                layoutParams.gravity = 8388613;
                layoutParams.rightMargin = (childCount * this.z) + this.y;
            }
            viewGroup.addView(inflate, layoutParams);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.ic_qs_game_reminder);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_game_reminder};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void e() {
        super.e();
        this.w = this.f8994h.getResources().getDimensionPixelSize(R.dimen.reminder_card_item_width);
        this.x = this.f8994h.getResources().getDimensionPixelSize(R.dimen.reminder_card_item_height);
        this.y = this.f8994h.getResources().getDimensionPixelSize(R.dimen.reminder_card_item_one_padding);
        this.z = this.f8994h.getResources().getDimensionPixelSize(R.dimen.reminder_card_item_one_padding_plus);
        this.A = this.f8994h.getResources().getDimensionPixelSize(R.dimen.reminder_card_item_spacing);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        this.v.clear();
        try {
            Cursor query = this.f8994h.getContentResolver().query(B, null, "time>?", new String[]{Long.toString(System.currentTimeMillis())}, "time asc limit 3");
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        int columnIndex = query.getColumnIndex("time");
                        int columnIndex2 = query.getColumnIndex("package");
                        do {
                            long j2 = query.getLong(columnIndex);
                            this.v.add(new GameReminderBean(query.getString(columnIndex2), j2));
                        } while (query.moveToNext());
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        s(this.v.isEmpty());
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(final ViewGroup viewGroup, final boolean z) {
        super.w(viewGroup, z);
        viewGroup.removeAllViews();
        DefaultUtil.d(this.f8994h);
        this.v.forEach(new Consumer() { // from class: cn.nubia.screensaver.card.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GameReminderCard.this.B(viewGroup, z, (GameReminderBean) obj);
            }
        });
    }
}
