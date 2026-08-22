package cn.nubia.gameassist.theme;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import cn.nubia.gameassist.R;
import com.zte.distbus.basetransfer.Status;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class Theme {
    public static final Theme A;
    public static final Theme B;
    public static final Theme C;
    public static final Theme D;
    public static final Theme E;
    public static final Theme F;
    public static final Theme G;
    private static final float[] u;
    public static final ColorFilter v;
    private static final float[] w = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    public static final Theme x;
    public static final Theme y;
    public static final Theme z;

    /* renamed from: a, reason: collision with root package name */
    private final float[] f7434a;

    /* renamed from: b, reason: collision with root package name */
    public ColorFilter f7435b;

    /* renamed from: c, reason: collision with root package name */
    public final int f7436c;

    /* renamed from: d, reason: collision with root package name */
    public final int f7437d;

    /* renamed from: e, reason: collision with root package name */
    public final int f7438e;

    /* renamed from: f, reason: collision with root package name */
    public final int f7439f;

    /* renamed from: g, reason: collision with root package name */
    public final int f7440g;

    /* renamed from: h, reason: collision with root package name */
    public final int f7441h;

    /* renamed from: i, reason: collision with root package name */
    public final int f7442i;

    /* renamed from: j, reason: collision with root package name */
    public final int f7443j;

    /* renamed from: k, reason: collision with root package name */
    public final int f7444k;

    /* renamed from: l, reason: collision with root package name */
    public final int f7445l;

    /* renamed from: m, reason: collision with root package name */
    public final int f7446m;

    /* renamed from: n, reason: collision with root package name */
    public final int f7447n;

    /* renamed from: o, reason: collision with root package name */
    public final int f7448o;

    /* renamed from: p, reason: collision with root package name */
    public float f7449p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f7450q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f7451r;

    /* renamed from: s, reason: collision with root package name */
    public final NeonLampTheme f7452s;
    public final EffectTheme t;

    public static final class EffectTheme {

        /* renamed from: i, reason: collision with root package name */
        public static final int[] f7453i = {R.drawable.game_assist_effect_number_0, R.drawable.game_assist_effect_number_1, R.drawable.game_assist_effect_number_2, R.drawable.game_assist_effect_number_3, R.drawable.game_assist_effect_number_4, R.drawable.game_assist_effect_number_5, R.drawable.game_assist_effect_number_6, R.drawable.game_assist_effect_number_7, R.drawable.game_assist_effect_number_8, R.drawable.game_assist_effect_number_9, R.drawable.game_assist_effect_number_p};

        /* renamed from: j, reason: collision with root package name */
        public static final int f7454j = R.drawable.game_assist_effect_ghz;

        /* renamed from: k, reason: collision with root package name */
        public static final int f7455k = R.drawable.game_assist_effect_mhz;

        /* renamed from: l, reason: collision with root package name */
        public static final int f7456l = R.drawable.game_assist_effect_gpu;

        /* renamed from: m, reason: collision with root package name */
        public static final int f7457m = R.drawable.game_assist_effect_cpu;

        /* renamed from: n, reason: collision with root package name */
        public static final int f7458n = R.drawable.game_assist_effect_bull_horn;

        /* renamed from: o, reason: collision with root package name */
        public static final int f7459o = R.drawable.game_assist_effect_turnplate_mask;

        /* renamed from: a, reason: collision with root package name */
        public final int f7460a;

        /* renamed from: b, reason: collision with root package name */
        public final int f7461b;

        /* renamed from: c, reason: collision with root package name */
        public final int f7462c;

        /* renamed from: d, reason: collision with root package name */
        public final int f7463d;

        /* renamed from: e, reason: collision with root package name */
        public final int f7464e;

        /* renamed from: f, reason: collision with root package name */
        public final int f7465f;

        /* renamed from: g, reason: collision with root package name */
        public final int f7466g;

        /* renamed from: h, reason: collision with root package name */
        public final int[] f7467h;

        public EffectTheme(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr) {
            this.f7460a = i2;
            this.f7461b = i3;
            this.f7462c = i4;
            this.f7464e = i5;
            this.f7465f = i6;
            this.f7466g = i7;
            this.f7463d = i8;
            this.f7467h = iArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Context context, Resources.Theme theme) {
            Resources resources = context.getResources();
            for (int i2 : f7453i) {
                resources.getDrawable(i2, theme);
            }
            resources.getDrawable(f7454j, theme);
            resources.getDrawable(f7455k, theme);
            resources.getDrawable(f7456l, theme);
            resources.getDrawable(f7457m, theme);
            resources.getDrawable(f7458n, theme);
            resources.getDrawable(f7459o, theme);
            resources.getDrawable(this.f7460a, theme);
            resources.getDrawable(this.f7461b, theme);
            resources.getDrawable(this.f7462c, theme);
            resources.getDrawable(this.f7463d, theme);
            resources.getDrawable(this.f7464e, theme);
            resources.getDrawable(this.f7465f, theme);
            resources.getDrawable(this.f7466g, theme);
            for (int i3 : this.f7467h) {
                resources.getDrawable(i3, theme);
            }
        }
    }

    public static final class NeonLampTheme {

        /* renamed from: a, reason: collision with root package name */
        public final int[] f7468a;

        /* renamed from: d, reason: collision with root package name */
        public final int f7471d;

        /* renamed from: e, reason: collision with root package name */
        public final int f7472e;

        /* renamed from: f, reason: collision with root package name */
        public final int f7473f;

        /* renamed from: b, reason: collision with root package name */
        public final int f7469b = R.drawable.game_assist_neon_lamp_light_off;

        /* renamed from: c, reason: collision with root package name */
        public final int f7470c = R.drawable.game_assist_neon_lamp_background;

        /* renamed from: g, reason: collision with root package name */
        public final int f7474g = R.drawable.game_assist_neon_lamp_launch_mask;

        public NeonLampTheme(int[] iArr, int i2, int i3, int i4) {
            this.f7468a = iArr;
            this.f7471d = i2;
            this.f7472e = i3;
            this.f7473f = i4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Context context, Resources.Theme theme) {
            Resources resources = context.getResources();
            resources.getDrawable(this.f7469b, theme);
            resources.getDrawable(this.f7470c, theme);
            resources.getDrawable(this.f7471d, theme);
            resources.getDrawable(this.f7472e, theme);
            resources.getDrawable(this.f7473f, theme);
            resources.getDrawable(this.f7474g, theme);
        }
    }

    static {
        float[] fArr = {0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.213f, 0.715f, 0.072f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        u = fArr;
        v = new ColorMatrixColorFilter(fArr);
        int argb = Color.argb(255, 75, 253, 255);
        int i2 = R.drawable.game_assist_economize_color_bitmap;
        NeonLampTheme neonLampTheme = new NeonLampTheme(new int[]{-10910977, -8912898}, R.drawable.game_assist_neon_lamp_economize_light, R.drawable.game_assist_neon_lamp_economize_light_share, R.drawable.game_assist_neon_lamp_economize_launch);
        int i3 = R.drawable.zte_game_assist_plugin_economize_select;
        Theme theme = new Theme(1, argb, i2, neonLampTheme, i3, i3, new EffectTheme(R.drawable.game_assist_effect_economize_glow_left, R.drawable.game_assist_effect_economize_glow_right, R.drawable.game_assist_effect_economize_dial_plate, R.drawable.game_assist_effect_economize_num_mask, R.drawable.game_assist_effect_economize_turnplate, R.drawable.game_assist_effect_economize_turnplate_light, R.drawable.game_assist_effect_economize_blusher, new int[]{R.drawable.game_assist_effect_economize_fire_00, R.drawable.game_assist_effect_economize_fire_01, R.drawable.game_assist_effect_economize_fire_02, R.drawable.game_assist_effect_economize_fire_03, R.drawable.game_assist_effect_economize_fire_04, R.drawable.game_assist_effect_economize_fire_05, R.drawable.game_assist_effect_economize_fire_06, R.drawable.game_assist_effect_economize_fire_07, R.drawable.game_assist_effect_economize_fire_08, R.drawable.game_assist_effect_economize_fire_09, R.drawable.game_assist_effect_economize_fire_10, R.drawable.game_assist_effect_economize_fire_11, R.drawable.game_assist_effect_economize_fire_12, R.drawable.game_assist_effect_economize_fire_13}), R.drawable.game_assist_diplogen_choice_economize, R.drawable.background_radiobutton_list_on_economize, R.drawable.background_radiobutton_card_on_economize, 4980223, R.drawable.background_radiobutton_list_on_economize_port, R.drawable.background_radiobutton_card_on_economize_port, i() ? R.drawable.ic_card_plugin_sort : R.drawable.selector_swith_mode_plugin_list_economize, R.drawable.selector_swith_mode_plugin_card_economize);
        x = theme;
        int argb2 = Color.argb(255, 249, 186, 0);
        int i4 = R.drawable.game_assist_balance_color_bitmap;
        NeonLampTheme neonLampTheme2 = new NeonLampTheme(new int[]{-23276, -14561}, R.drawable.game_assist_neon_lamp_balance_light, R.drawable.game_assist_neon_lamp_balance_light_share, R.drawable.game_assist_neon_lamp_balance_launch);
        int i5 = R.drawable.zte_game_assist_plugin_balance_select;
        Theme theme2 = new Theme(2, argb2, i4, neonLampTheme2, i5, i5, new EffectTheme(R.drawable.game_assist_effect_balance_glow_left, R.drawable.game_assist_effect_balance_glow_right, R.drawable.game_assist_effect_balance_dial_plate, R.drawable.game_assist_effect_balance_num_mask, R.drawable.game_assist_effect_balance_turnplate, R.drawable.game_assist_effect_balance_turnplate_light, R.drawable.game_assist_effect_balance_blusher, new int[]{R.drawable.game_assist_effect_balance_fire_00, R.drawable.game_assist_effect_balance_fire_01, R.drawable.game_assist_effect_balance_fire_02, R.drawable.game_assist_effect_balance_fire_03, R.drawable.game_assist_effect_balance_fire_04, R.drawable.game_assist_effect_balance_fire_05, R.drawable.game_assist_effect_balance_fire_06, R.drawable.game_assist_effect_balance_fire_07, R.drawable.game_assist_effect_balance_fire_08, R.drawable.game_assist_effect_balance_fire_09, R.drawable.game_assist_effect_balance_fire_10, R.drawable.game_assist_effect_balance_fire_11, R.drawable.game_assist_effect_balance_fire_12, R.drawable.game_assist_effect_balance_fire_13}), R.drawable.game_assist_diplogen_choice_balance, R.drawable.background_radiobutton_list_on_balance, R.drawable.background_radiobutton_card_on_balance, 16366080, R.drawable.background_radiobutton_list_on_balance_port, R.drawable.background_radiobutton_card_on_balance_port, i() ? R.drawable.ic_card_plugin_sort : R.drawable.selector_swith_mode_plugin_list_balance, R.drawable.selector_swith_mode_plugin_card_balance);
        y = theme2;
        int argb3 = Color.argb(255, 224, 32, 32);
        int i6 = R.drawable.game_assist_awakening_color_bitmap;
        NeonLampTheme neonLampTheme3 = new NeonLampTheme(new int[]{-2088928, -44462}, R.drawable.game_assist_neon_lamp_awakening_light, R.drawable.game_assist_neon_lamp_awakening_light_share, R.drawable.game_assist_neon_lamp_awakening_launch);
        int i7 = R.drawable.zte_game_assist_plugin_awakening_select;
        Theme theme3 = new Theme(3, argb3, i6, neonLampTheme3, i7, i7, new EffectTheme(R.drawable.game_assist_effect_awakening_glow_left, R.drawable.game_assist_effect_awakening_glow_right, R.drawable.game_assist_effect_awakening_dial_plate, R.drawable.game_assist_effect_awakening_num_mask, R.drawable.game_assist_effect_awakening_turnplate, R.drawable.game_assist_effect_awakening_turnplate_light, R.drawable.game_assist_effect_awakening_blusher, new int[]{R.drawable.game_assist_effect_awakening_fire_00, R.drawable.game_assist_effect_awakening_fire_01, R.drawable.game_assist_effect_awakening_fire_02, R.drawable.game_assist_effect_awakening_fire_03, R.drawable.game_assist_effect_awakening_fire_04, R.drawable.game_assist_effect_awakening_fire_05, R.drawable.game_assist_effect_awakening_fire_06, R.drawable.game_assist_effect_awakening_fire_07, R.drawable.game_assist_effect_awakening_fire_08, R.drawable.game_assist_effect_awakening_fire_09, R.drawable.game_assist_effect_awakening_fire_10, R.drawable.game_assist_effect_awakening_fire_11, R.drawable.game_assist_effect_awakening_fire_12, R.drawable.game_assist_effect_awakening_fire_13}), R.drawable.game_assist_diplogen_choice_awakening, R.drawable.background_radiobutton_list_on_awakening, R.drawable.background_radiobutton_card_on_awakening, 14688288, R.drawable.background_radiobutton_list_on_awakening_port, R.drawable.background_radiobutton_card_on_awakening_port, i() ? R.drawable.ic_card_plugin_sort : R.drawable.selector_swith_mode_plugin_list_awakening, R.drawable.selector_swith_mode_plugin_card_awakening);
        z = theme3;
        int argb4 = Color.argb(255, 255, 113, 34);
        int i8 = R.drawable.game_assist_biablo_color_bitmap;
        NeonLampTheme neonLampTheme4 = new NeonLampTheme(new int[]{-2082528, -36574}, R.drawable.game_assist_neon_lamp_biablo_light, R.drawable.game_assist_neon_lamp_biablo_light_share, R.drawable.game_assist_neon_lamp_biablo_launch);
        int i9 = R.drawable.zte_game_assist_plugin_biablo_select;
        Theme theme4 = new Theme(4, argb4, i8, neonLampTheme4, i9, i9, new EffectTheme(R.drawable.game_assist_effect_biablo_glow_left, R.drawable.game_assist_effect_biablo_glow_right, R.drawable.game_assist_effect_biablo_dial_plate, R.drawable.game_assist_effect_biablo_num_mask, R.drawable.game_assist_effect_biablo_turnplate, R.drawable.game_assist_effect_biablo_turnplate_light, R.drawable.game_assist_effect_biablo_blusher, new int[]{R.drawable.game_assist_effect_biablo_fire_00, R.drawable.game_assist_effect_biablo_fire_01, R.drawable.game_assist_effect_biablo_fire_02, R.drawable.game_assist_effect_biablo_fire_03, R.drawable.game_assist_effect_biablo_fire_04, R.drawable.game_assist_effect_biablo_fire_05, R.drawable.game_assist_effect_biablo_fire_06, R.drawable.game_assist_effect_biablo_fire_07, R.drawable.game_assist_effect_biablo_fire_08, R.drawable.game_assist_effect_biablo_fire_09, R.drawable.game_assist_effect_biablo_fire_10, R.drawable.game_assist_effect_biablo_fire_11, R.drawable.game_assist_effect_biablo_fire_12, R.drawable.game_assist_effect_biablo_fire_13}), R.drawable.game_assist_diplogen_choice_biablo, R.drawable.background_radiobutton_list_on_biablo, R.drawable.background_radiobutton_card_on_biablo, 16740642, R.drawable.background_radiobutton_list_on_biablo_port, R.drawable.background_radiobutton_card_on_biablo_port, i() ? R.drawable.ic_card_plugin_sort : R.drawable.selector_swith_mode_plugin_list_biablo, R.drawable.selector_swith_mode_plugin_card_biablo);
        A = theme4;
        int argb5 = Color.argb(255, Status.BLE_ERROR, 114, 144);
        int i10 = R.drawable.game_assist_custome_color_bitmap;
        NeonLampTheme neonLampTheme5 = new NeonLampTheme(new int[]{-23276, -14561}, R.drawable.game_assist_neon_lamp_custome_light, R.drawable.game_assist_neon_lamp_custome_light_share, R.drawable.game_assist_neon_lamp_custome_launch);
        int i11 = R.drawable.zte_game_assist_plugin_custome_select;
        Theme theme5 = new Theme(5, argb5, i10, neonLampTheme5, i11, i11, new EffectTheme(R.drawable.game_assist_effect_custome_glow_left, R.drawable.game_assist_effect_custome_glow_right, R.drawable.game_assist_effect_custome_dial_plate, R.drawable.game_assist_effect_custome_num_mask, R.drawable.game_assist_effect_custome_turnplate, R.drawable.game_assist_effect_custome_turnplate_light, R.drawable.game_assist_effect_custome_blusher, new int[]{R.drawable.game_assist_effect_custome_fire_00, R.drawable.game_assist_effect_custome_fire_01, R.drawable.game_assist_effect_custome_fire_02, R.drawable.game_assist_effect_custome_fire_03, R.drawable.game_assist_effect_custome_fire_04, R.drawable.game_assist_effect_custome_fire_05, R.drawable.game_assist_effect_custome_fire_06, R.drawable.game_assist_effect_custome_fire_07, R.drawable.game_assist_effect_custome_fire_08, R.drawable.game_assist_effect_custome_fire_09, R.drawable.game_assist_effect_custome_fire_10, R.drawable.game_assist_effect_custome_fire_11, R.drawable.game_assist_effect_custome_fire_12, R.drawable.game_assist_effect_custome_fire_13}), R.drawable.game_assist_diplogen_choice_custome, R.drawable.background_radiobutton_list_on_custome, R.drawable.background_radiobutton_card_on_custome, 11387903, R.drawable.background_radiobutton_list_on_custome_port, R.drawable.background_radiobutton_card_on_custome_port, R.drawable.selector_swith_mode_plugin_list_custome, i() ? R.drawable.ic_card_plugin_sort : R.drawable.selector_swith_mode_plugin_card_custome);
        B = theme5;
        C = new Theme(theme);
        D = new Theme(theme2);
        E = new Theme(theme3);
        F = new Theme(theme4);
        G = new Theme(theme5);
    }

    public Theme(Theme theme) {
        this.f7434a = new float[20];
        this.f7435b = new ColorMatrixColorFilter(u);
        this.f7436c = theme.f7436c;
        NeonLampTheme neonLampTheme = theme.f7452s;
        this.f7452s = new NeonLampTheme(neonLampTheme.f7468a, neonLampTheme.f7471d, neonLampTheme.f7472e, neonLampTheme.f7473f);
        this.f7437d = theme.f7437d;
        this.f7438e = theme.f7438e;
        this.f7439f = theme.f7439f;
        this.f7440g = theme.f7440g;
        EffectTheme effectTheme = theme.t;
        this.t = new EffectTheme(effectTheme.f7460a, effectTheme.f7461b, effectTheme.f7462c, effectTheme.f7464e, effectTheme.f7465f, effectTheme.f7466g, effectTheme.f7463d, effectTheme.f7467h);
        this.f7448o = theme.f7448o;
        this.f7441h = theme.f7441h;
        this.f7443j = theme.f7443j;
        this.f7447n = theme.f7447n;
        this.f7445l = theme.f7445l;
        this.f7446m = theme.f7446m;
        this.f7442i = theme.f7442i;
        this.f7444k = theme.f7444k;
    }

    public static boolean i() {
        return ZteFeature.isSupportSort();
    }

    public int a(int i2, boolean z2) {
        int i3;
        int i4;
        if (z2) {
            i3 = this.f7447n;
            i4 = -1728053248;
        } else if (i2 == 2) {
            i3 = this.f7447n;
            i4 = -16777216;
        } else {
            i3 = this.f7447n;
            i4 = 1711276032;
        }
        return i3 | i4;
    }

    public int b(int i2, boolean z2) {
        return i2 != 2 ? z2 ? R.drawable.background_radiobutton_card_off : R.drawable.background_radiobutton_card_off_port : z2 ? this.f7443j : this.f7446m;
    }

    public int c(boolean z2) {
        return this.f7444k;
    }

    public int d(int i2) {
        return i2 != 2 ? i2 != 3 ? R.drawable.zte_game_assist_plugin_normal : R.drawable.game_assist_button_dessert_unenable : this.f7439f;
    }

    public int e(int i2, boolean z2) {
        return i2 != 2 ? z2 ? R.drawable.background_radiobutton_list_off : R.drawable.background_radiobutton_list_off_port : z2 ? this.f7441h : this.f7445l;
    }

    public int f(boolean z2) {
        return this.f7442i;
    }

    public int g(int i2) {
        return i2 != 2 ? R.drawable.zte_game_assist_plugin_normal : this.f7440g;
    }

    public boolean h() {
        float f2 = this.f7449p;
        return f2 > 0.0f && f2 < 1.0f;
    }

    public void j(Context context) {
        Resources resources = context.getResources();
        Resources.Theme theme = context.getTheme();
        resources.getDrawable(this.f7438e, theme);
        resources.getDrawable(this.f7439f, theme);
        resources.getDrawable(this.f7440g, theme);
        resources.getDrawable(this.f7441h, theme);
        resources.getDrawable(this.f7442i, theme);
        resources.getDrawable(this.f7443j, theme);
        resources.getDrawable(this.f7444k, theme);
        resources.getDrawable(this.f7445l, theme);
        resources.getDrawable(this.f7446m, theme);
        resources.getDrawable(this.f7448o, theme);
        NeonLampTheme neonLampTheme = this.f7452s;
        if (neonLampTheme != null) {
            neonLampTheme.b(context, theme);
        }
        EffectTheme effectTheme = this.t;
        if (effectTheme != null) {
            effectTheme.b(context, theme);
        }
    }

    public void k(float f2) {
        int i2 = 0;
        if (f2 == 1.0f) {
            this.f7451r = false;
            this.f7450q = false;
        } else if (f2 > this.f7449p) {
            this.f7451r = true;
            this.f7450q = false;
        } else {
            this.f7451r = false;
            this.f7450q = true;
        }
        this.f7449p = f2;
        while (true) {
            float[] fArr = this.f7434a;
            if (i2 >= fArr.length) {
                this.f7435b = new ColorMatrixColorFilter(this.f7434a);
                return;
            }
            float f3 = w[i2];
            float f4 = u[i2];
            fArr[i2] = ((f3 - f4) * f2) + f4;
            i2++;
        }
    }

    public Theme(int i2, int i3, int i4, NeonLampTheme neonLampTheme, int i5, int i6, EffectTheme effectTheme, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        this.f7434a = new float[20];
        this.f7435b = new ColorMatrixColorFilter(u);
        this.f7436c = i2;
        this.f7452s = neonLampTheme;
        this.f7437d = i3;
        this.f7438e = i4;
        this.f7439f = i5;
        this.f7440g = i6;
        this.t = effectTheme;
        this.f7448o = i7;
        this.f7441h = i8;
        this.f7443j = i9;
        this.f7447n = i10;
        this.f7445l = i11;
        this.f7446m = i12;
        this.f7442i = i13;
        this.f7444k = i14;
    }
}
