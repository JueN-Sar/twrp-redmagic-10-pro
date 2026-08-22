package cn.nubia.screensaver.card;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class GameWallpaperCard extends BaseCard {
    private Drawable A;
    private int B;
    private int C;
    private TextView v;
    private TextView w;
    private ImageView x;
    private FrameLayout y;
    private FrameLayout z;

    private Drawable z(String str) {
        try {
            Resources resourcesForApplication = this.f8994h.getPackageManager().getResourcesForApplication("cn.nubia.inspiredwallpaper");
            return resourcesForApplication.getDrawable(resourcesForApplication.getIdentifier(str, "mipmap", "cn.nubia.inspiredwallpaper"), null);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.v = (TextView) view.findViewById(R.id.tv_wallpaper_name);
        this.w = (TextView) view.findViewById(R.id.tv_wallpaper_ranking);
        this.x = (ImageView) view.findViewById(R.id.iv_wallpaper_icon);
        this.y = (FrameLayout) view.findViewById(R.id.fl_wallpaper_icon);
        this.z = (FrameLayout) view.findViewById(R.id.fl_wallpaper_name);
        this.w.setText(this.f8994h.getString(R.string.game_wallpaper_card_ranking, 12));
        this.v.setText(this.f8994h.getString(R.string.game_wallpaper_card_title));
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.game_wallpaper_card_title);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_game_wallpaper};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void e() {
        super.e();
        this.B = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.wallpaper_card_content_pe);
        this.C = this.f8994h.getResources().getDimensionPixelOffset(R.dimen.wallpaper_card_icon_me);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        Drawable z = z("inspired_wall_broken_blue");
        this.A = z;
        s(z == null);
        r(this.A == null);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z) {
        super.w(viewGroup, z);
        Drawable drawable = this.A;
        if (drawable != null) {
            this.x.setImageDrawable(drawable);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.z.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.y.getLayoutParams();
        if (z) {
            viewGroup.setPadding(this.B, viewGroup.getPaddingTop(), viewGroup.getPaddingEnd(), viewGroup.getPaddingBottom());
            layoutParams.addRule(20);
            layoutParams2.addRule(20);
            layoutParams3.addRule(1, R.id.fl_wallpaper_name);
            layoutParams3.leftMargin = this.C;
        } else {
            viewGroup.setPadding(viewGroup.getPaddingStart(), viewGroup.getPaddingTop(), this.B, viewGroup.getPaddingBottom());
            layoutParams.addRule(21);
            layoutParams2.addRule(21);
            layoutParams3.addRule(16, R.id.fl_wallpaper_name);
            layoutParams3.rightMargin = this.C;
        }
        this.z.setLayoutParams(layoutParams);
        this.w.setLayoutParams(layoutParams2);
        this.y.setLayoutParams(layoutParams3);
    }
}
