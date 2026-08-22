package androidx.appcompat.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import androidx.appcompat.R;

/* loaded from: classes.dex */
public class ContextThemeWrapper extends ContextWrapper {

    /* renamed from: f, reason: collision with root package name */
    private static Configuration f423f;

    /* renamed from: a, reason: collision with root package name */
    private int f424a;

    /* renamed from: b, reason: collision with root package name */
    private Resources.Theme f425b;

    /* renamed from: c, reason: collision with root package name */
    private LayoutInflater f426c;

    /* renamed from: d, reason: collision with root package name */
    private Configuration f427d;

    /* renamed from: e, reason: collision with root package name */
    private Resources f428e;

    public ContextThemeWrapper(Context context, int i2) {
        super(context);
        this.f424a = i2;
    }

    private Resources b() {
        if (this.f428e == null) {
            Configuration configuration = this.f427d;
            if (configuration == null || d(configuration)) {
                this.f428e = super.getResources();
            } else {
                this.f428e = createConfigurationContext(this.f427d).getResources();
            }
        }
        return this.f428e;
    }

    private void c() {
        boolean z = this.f425b == null;
        if (z) {
            this.f425b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f425b.setTo(theme);
            }
        }
        e(this.f425b, this.f424a, z);
    }

    private static boolean d(Configuration configuration) {
        if (configuration == null) {
            return true;
        }
        if (f423f == null) {
            Configuration configuration2 = new Configuration();
            configuration2.fontScale = 0.0f;
            f423f = configuration2;
        }
        return configuration.equals(f423f);
    }

    public void a(Configuration configuration) {
        if (this.f428e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f427d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f427d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    protected void e(Resources.Theme theme, int i2, boolean z) {
        theme.applyStyle(i2, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return b();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f426c == null) {
            this.f426c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f426c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f425b;
        if (theme != null) {
            return theme;
        }
        if (this.f424a == 0) {
            this.f424a = R.style.Theme_AppCompat_Light;
        }
        c();
        return this.f425b;
    }

    public int getThemeResId() {
        return this.f424a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        if (this.f424a != i2) {
            this.f424a = i2;
            c();
        }
    }

    public ContextThemeWrapper(Context context, Resources.Theme theme) {
        super(context);
        this.f425b = theme;
    }
}
