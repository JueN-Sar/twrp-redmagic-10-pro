package cn.nubia.screensaver.card;

import android.content.ContentProviderClient;
import android.graphics.BitmapFactory;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import cn.nubia.gameassist.R;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class GameSpaceInfoCard extends BaseCard {
    private static final Uri E = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider");
    private int A;
    private BitmapDrawable B;
    private final int[] C = {R.drawable.screen_saver_game_space_atmosphere_mode_icon, R.drawable.screen_saver_game_space_card_mode_icon, R.drawable.screen_saver_game_space_handheld_mode_icon};
    private String[] D;
    private ImageView v;
    private ImageView w;
    private TextView x;
    private TextView y;
    private int z;

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void a(View view) {
        this.x = (TextView) view.findViewById(R.id.tv_mode_name);
        this.y = (TextView) view.findViewById(R.id.tv_game_num);
        this.w = (ImageView) view.findViewById(R.id.iv_mode_icon);
        this.v = (ImageView) view.findViewById(R.id.iv_atmosphere_bg);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected String b() {
        return this.f8994h.getString(R.string.game_space_info_card_title);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected int[] d() {
        return new int[]{R.layout.card_game_space_info};
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void e() {
        super.e();
        this.D = this.f8994h.getResources().getStringArray(R.array.game_space_mode);
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    public void f() {
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.f8994h.getContentResolver().acquireUnstableContentProviderClient(E);
            try {
                Bundle call = acquireUnstableContentProviderClient.call("getLobbyContents", null, null);
                if (call == null) {
                    s(true);
                    acquireUnstableContentProviderClient.close();
                    return;
                }
                this.z = call.getInt("count");
                this.A = call.getInt("mode");
                byte[] byteArray = call.getByteArray("banner");
                if (byteArray != null) {
                    this.B = new BitmapDrawable(this.f8994h.getResources(), BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
                }
                GaLog.a(this.f8993c, "num " + this.z + ",m " + this.A + ",b " + this.B);
                r(false);
                acquireUnstableContentProviderClient.close();
            } finally {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            s(true);
        }
    }

    @Override // cn.nubia.screensaver.card.BaseCard
    protected void w(ViewGroup viewGroup, boolean z) {
        super.w(viewGroup, z);
        this.y.setText(this.f8994h.getString(R.string.game_space_info_install_app_num, Integer.valueOf(this.z)));
        if (this.B != null) {
            this.v.setRenderEffect(RenderEffect.createBlurEffect(6.0f, 6.0f, Shader.TileMode.CLAMP));
            this.v.setImageDrawable(this.B);
        }
        int i2 = this.A;
        if (i2 >= 0) {
            String[] strArr = this.D;
            if (i2 < strArr.length) {
                this.x.setText(strArr[i2]);
                this.w.setImageDrawable(ContextCompat.e(this.f8994h, this.C[this.A]));
            }
        }
    }
}
