package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.graphics.ColorUtils;

@RestrictTo
/* loaded from: classes.dex */
public final class AppCompatDrawableManager {

    /* renamed from: b, reason: collision with root package name */
    private static final PorterDuff.Mode f746b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c, reason: collision with root package name */
    private static AppCompatDrawableManager f747c;

    /* renamed from: a, reason: collision with root package name */
    private ResourceManagerInternal f748a;

    public static synchronized AppCompatDrawableManager b() {
        AppCompatDrawableManager appCompatDrawableManager;
        synchronized (AppCompatDrawableManager.class) {
            try {
                if (f747c == null) {
                    h();
                }
                appCompatDrawableManager = f747c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return appCompatDrawableManager;
    }

    public static synchronized PorterDuffColorFilter e(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter k2;
        synchronized (AppCompatDrawableManager.class) {
            k2 = ResourceManagerInternal.k(i2, mode);
        }
        return k2;
    }

    public static synchronized void h() {
        synchronized (AppCompatDrawableManager.class) {
            if (f747c == null) {
                AppCompatDrawableManager appCompatDrawableManager = new AppCompatDrawableManager();
                f747c = appCompatDrawableManager;
                appCompatDrawableManager.f748a = ResourceManagerInternal.g();
                f747c.f748a.t(new ResourceManagerInternal.ResourceManagerHooks() { // from class: androidx.appcompat.widget.AppCompatDrawableManager.1

                    /* renamed from: a, reason: collision with root package name */
                    private final int[] f749a = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};

                    /* renamed from: b, reason: collision with root package name */
                    private final int[] f750b = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};

                    /* renamed from: c, reason: collision with root package name */
                    private final int[] f751c = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};

                    /* renamed from: d, reason: collision with root package name */
                    private final int[] f752d = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};

                    /* renamed from: e, reason: collision with root package name */
                    private final int[] f753e = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};

                    /* renamed from: f, reason: collision with root package name */
                    private final int[] f754f = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};

                    private boolean f(int[] iArr, int i2) {
                        for (int i3 : iArr) {
                            if (i3 == i2) {
                                return true;
                            }
                        }
                        return false;
                    }

                    private ColorStateList g(Context context) {
                        return h(context, 0);
                    }

                    private ColorStateList h(Context context, int i2) {
                        int c2 = ThemeUtils.c(context, R.attr.colorControlHighlight);
                        return new ColorStateList(new int[][]{ThemeUtils.f1002b, ThemeUtils.f1005e, ThemeUtils.f1003c, ThemeUtils.f1009i}, new int[]{ThemeUtils.b(context, R.attr.colorButtonNormal), ColorUtils.g(c2, i2), ColorUtils.g(c2, i2), i2});
                    }

                    private ColorStateList i(Context context) {
                        return h(context, ThemeUtils.c(context, R.attr.colorAccent));
                    }

                    private ColorStateList j(Context context) {
                        return h(context, ThemeUtils.c(context, R.attr.colorButtonNormal));
                    }

                    private ColorStateList k(Context context) {
                        int[][] iArr = new int[3][];
                        int[] iArr2 = new int[3];
                        ColorStateList e2 = ThemeUtils.e(context, R.attr.colorSwitchThumbNormal);
                        if (e2 == null || !e2.isStateful()) {
                            iArr[0] = ThemeUtils.f1002b;
                            iArr2[0] = ThemeUtils.b(context, R.attr.colorSwitchThumbNormal);
                            iArr[1] = ThemeUtils.f1006f;
                            iArr2[1] = ThemeUtils.c(context, R.attr.colorControlActivated);
                            iArr[2] = ThemeUtils.f1009i;
                            iArr2[2] = ThemeUtils.c(context, R.attr.colorSwitchThumbNormal);
                        } else {
                            int[] iArr3 = ThemeUtils.f1002b;
                            iArr[0] = iArr3;
                            iArr2[0] = e2.getColorForState(iArr3, 0);
                            iArr[1] = ThemeUtils.f1006f;
                            iArr2[1] = ThemeUtils.c(context, R.attr.colorControlActivated);
                            iArr[2] = ThemeUtils.f1009i;
                            iArr2[2] = e2.getDefaultColor();
                        }
                        return new ColorStateList(iArr, iArr2);
                    }

                    private LayerDrawable l(ResourceManagerInternal resourceManagerInternal, Context context, int i2) {
                        BitmapDrawable bitmapDrawable;
                        BitmapDrawable bitmapDrawable2;
                        BitmapDrawable bitmapDrawable3;
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i2);
                        Drawable i3 = resourceManagerInternal.i(context, R.drawable.abc_star_black_48dp);
                        Drawable i4 = resourceManagerInternal.i(context, R.drawable.abc_star_half_black_48dp);
                        if ((i3 instanceof BitmapDrawable) && i3.getIntrinsicWidth() == dimensionPixelSize && i3.getIntrinsicHeight() == dimensionPixelSize) {
                            bitmapDrawable = (BitmapDrawable) i3;
                            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
                        } else {
                            Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(createBitmap);
                            i3.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                            i3.draw(canvas);
                            bitmapDrawable = new BitmapDrawable(createBitmap);
                            bitmapDrawable2 = new BitmapDrawable(createBitmap);
                        }
                        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
                        if ((i4 instanceof BitmapDrawable) && i4.getIntrinsicWidth() == dimensionPixelSize && i4.getIntrinsicHeight() == dimensionPixelSize) {
                            bitmapDrawable3 = (BitmapDrawable) i4;
                        } else {
                            Bitmap createBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                            Canvas canvas2 = new Canvas(createBitmap2);
                            i4.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                            i4.draw(canvas2);
                            bitmapDrawable3 = new BitmapDrawable(createBitmap2);
                        }
                        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
                        layerDrawable.setId(0, android.R.id.background);
                        layerDrawable.setId(1, android.R.id.secondaryProgress);
                        layerDrawable.setId(2, android.R.id.progress);
                        return layerDrawable;
                    }

                    private void m(Drawable drawable, int i2, PorterDuff.Mode mode) {
                        Drawable mutate = drawable.mutate();
                        if (mode == null) {
                            mode = AppCompatDrawableManager.f746b;
                        }
                        mutate.setColorFilter(AppCompatDrawableManager.e(i2, mode));
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public Drawable a(ResourceManagerInternal resourceManagerInternal, Context context, int i2) {
                        if (i2 == R.drawable.abc_cab_background_top_material) {
                            return new LayerDrawable(new Drawable[]{resourceManagerInternal.i(context, R.drawable.abc_cab_background_internal_bg), resourceManagerInternal.i(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
                        }
                        if (i2 == R.drawable.abc_ratingbar_material) {
                            return l(resourceManagerInternal, context, R.dimen.abc_star_big);
                        }
                        if (i2 == R.drawable.abc_ratingbar_indicator_material) {
                            return l(resourceManagerInternal, context, R.dimen.abc_star_medium);
                        }
                        if (i2 == R.drawable.abc_ratingbar_small_material) {
                            return l(resourceManagerInternal, context, R.dimen.abc_star_small);
                        }
                        return null;
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public ColorStateList b(Context context, int i2) {
                        if (i2 == R.drawable.abc_edit_text_material) {
                            return AppCompatResources.a(context, R.color.abc_tint_edittext);
                        }
                        if (i2 == R.drawable.abc_switch_track_mtrl_alpha) {
                            return AppCompatResources.a(context, R.color.abc_tint_switch_track);
                        }
                        if (i2 == R.drawable.abc_switch_thumb_material) {
                            return k(context);
                        }
                        if (i2 == R.drawable.abc_btn_default_mtrl_shape) {
                            return j(context);
                        }
                        if (i2 == R.drawable.abc_btn_borderless_material) {
                            return g(context);
                        }
                        if (i2 == R.drawable.abc_btn_colored_material) {
                            return i(context);
                        }
                        if (i2 == R.drawable.abc_spinner_mtrl_am_alpha || i2 == R.drawable.abc_spinner_textfield_background_material) {
                            return AppCompatResources.a(context, R.color.abc_tint_spinner);
                        }
                        if (f(this.f750b, i2)) {
                            return ThemeUtils.e(context, R.attr.colorControlNormal);
                        }
                        if (f(this.f753e, i2)) {
                            return AppCompatResources.a(context, R.color.abc_tint_default);
                        }
                        if (f(this.f754f, i2)) {
                            return AppCompatResources.a(context, R.color.abc_tint_btn_checkable);
                        }
                        if (i2 == R.drawable.abc_seekbar_thumb_material) {
                            return AppCompatResources.a(context, R.color.abc_tint_seek_thumb);
                        }
                        return null;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:12:0x0060 A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:7:0x004b  */
                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public boolean c(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
                        /*
                            r6 = this;
                            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.AppCompatDrawableManager.a()
                            int[] r1 = r6.f749a
                            boolean r1 = r6.f(r1, r8)
                            r2 = 1
                            r3 = 0
                            r4 = -1
                            if (r1 == 0) goto L14
                            int r6 = androidx.appcompat.R.attr.colorControlNormal
                        L11:
                            r1 = r2
                        L12:
                            r8 = r4
                            goto L49
                        L14:
                            int[] r1 = r6.f751c
                            boolean r1 = r6.f(r1, r8)
                            if (r1 == 0) goto L1f
                            int r6 = androidx.appcompat.R.attr.colorControlActivated
                            goto L11
                        L1f:
                            int[] r1 = r6.f752d
                            boolean r6 = r6.f(r1, r8)
                            r1 = 16842801(0x1010031, float:2.3693695E-38)
                            if (r6 == 0) goto L2e
                            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                        L2c:
                            r6 = r1
                            goto L11
                        L2e:
                            int r6 = androidx.appcompat.R.drawable.abc_list_divider_mtrl_alpha
                            if (r8 != r6) goto L41
                            r6 = 1109603123(0x42233333, float:40.8)
                            int r6 = java.lang.Math.round(r6)
                            r8 = 16842800(0x1010030, float:2.3693693E-38)
                            r1 = r2
                            r5 = r8
                            r8 = r6
                            r6 = r5
                            goto L49
                        L41:
                            int r6 = androidx.appcompat.R.drawable.abc_dialog_material_background
                            if (r8 != r6) goto L46
                            goto L2c
                        L46:
                            r6 = r3
                            r1 = r6
                            goto L12
                        L49:
                            if (r1 == 0) goto L60
                            android.graphics.drawable.Drawable r9 = r9.mutate()
                            int r6 = androidx.appcompat.widget.ThemeUtils.c(r7, r6)
                            android.graphics.PorterDuffColorFilter r6 = androidx.appcompat.widget.AppCompatDrawableManager.e(r6, r0)
                            r9.setColorFilter(r6)
                            if (r8 == r4) goto L5f
                            r9.setAlpha(r8)
                        L5f:
                            return r2
                        L60:
                            return r3
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatDrawableManager.AnonymousClass1.c(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public PorterDuff.Mode d(int i2) {
                        if (i2 == R.drawable.abc_switch_thumb_material) {
                            return PorterDuff.Mode.MULTIPLY;
                        }
                        return null;
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public boolean e(Context context, int i2, Drawable drawable) {
                        if (i2 == R.drawable.abc_seekbar_track_material) {
                            LayerDrawable layerDrawable = (LayerDrawable) drawable;
                            m(layerDrawable.findDrawableByLayerId(android.R.id.background), ThemeUtils.c(context, R.attr.colorControlNormal), AppCompatDrawableManager.f746b);
                            m(layerDrawable.findDrawableByLayerId(android.R.id.secondaryProgress), ThemeUtils.c(context, R.attr.colorControlNormal), AppCompatDrawableManager.f746b);
                            m(layerDrawable.findDrawableByLayerId(android.R.id.progress), ThemeUtils.c(context, R.attr.colorControlActivated), AppCompatDrawableManager.f746b);
                            return true;
                        }
                        if (i2 != R.drawable.abc_ratingbar_material && i2 != R.drawable.abc_ratingbar_indicator_material && i2 != R.drawable.abc_ratingbar_small_material) {
                            return false;
                        }
                        LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                        m(layerDrawable2.findDrawableByLayerId(android.R.id.background), ThemeUtils.b(context, R.attr.colorControlNormal), AppCompatDrawableManager.f746b);
                        m(layerDrawable2.findDrawableByLayerId(android.R.id.secondaryProgress), ThemeUtils.c(context, R.attr.colorControlActivated), AppCompatDrawableManager.f746b);
                        m(layerDrawable2.findDrawableByLayerId(android.R.id.progress), ThemeUtils.c(context, R.attr.colorControlActivated), AppCompatDrawableManager.f746b);
                        return true;
                    }
                });
            }
        }
    }

    static void i(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        ResourceManagerInternal.v(drawable, tintInfo, iArr);
    }

    public synchronized Drawable c(Context context, int i2) {
        return this.f748a.i(context, i2);
    }

    synchronized Drawable d(Context context, int i2, boolean z) {
        return this.f748a.j(context, i2, z);
    }

    synchronized ColorStateList f(Context context, int i2) {
        return this.f748a.l(context, i2);
    }

    public synchronized void g(Context context) {
        this.f748a.r(context);
    }
}
