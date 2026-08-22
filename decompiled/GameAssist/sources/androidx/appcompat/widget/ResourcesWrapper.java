package androidx.appcompat.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.core.content.res.ResourcesCompat;
import java.io.InputStream;

/* loaded from: classes.dex */
class ResourcesWrapper extends Resources {

    /* renamed from: a, reason: collision with root package name */
    private final Resources f934a;

    public ResourcesWrapper(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f934a = resources;
    }

    final Drawable a(int i2) {
        return super.getDrawable(i2);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int i2) {
        return this.f934a.getAnimation(i2);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int i2) {
        return this.f934a.getBoolean(i2);
    }

    @Override // android.content.res.Resources
    public int getColor(int i2) {
        return this.f934a.getColor(i2);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i2) {
        return this.f934a.getColorStateList(i2);
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.f934a.getConfiguration();
    }

    @Override // android.content.res.Resources
    public float getDimension(int i2) {
        return this.f934a.getDimension(i2);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int i2) {
        return this.f934a.getDimensionPixelOffset(i2);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int i2) {
        return this.f934a.getDimensionPixelSize(i2);
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.f934a.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i2) {
        return this.f934a.getDrawable(i2);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i2, int i3) {
        return ResourcesCompat.f(this.f934a, i2, i3, null);
    }

    @Override // android.content.res.Resources
    public float getFraction(int i2, int i3, int i4) {
        return this.f934a.getFraction(i2, i3, i4);
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.f934a.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int i2) {
        return this.f934a.getIntArray(i2);
    }

    @Override // android.content.res.Resources
    public int getInteger(int i2) {
        return this.f934a.getInteger(i2);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i2) {
        return this.f934a.getLayout(i2);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int i2) {
        return this.f934a.getMovie(i2);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i2, int i3, Object... objArr) {
        return this.f934a.getQuantityString(i2, i3, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int i2, int i3) {
        return this.f934a.getQuantityText(i2, i3);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int i2) {
        return this.f934a.getResourceEntryName(i2);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int i2) {
        return this.f934a.getResourceName(i2);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int i2) {
        return this.f934a.getResourcePackageName(i2);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int i2) {
        return this.f934a.getResourceTypeName(i2);
    }

    @Override // android.content.res.Resources
    public String getString(int i2) {
        return this.f934a.getString(i2);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int i2) {
        return this.f934a.getStringArray(i2);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i2) {
        return this.f934a.getText(i2);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int i2) {
        return this.f934a.getTextArray(i2);
    }

    @Override // android.content.res.Resources
    public void getValue(int i2, TypedValue typedValue, boolean z) {
        this.f934a.getValue(i2, typedValue, z);
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int i2, int i3, TypedValue typedValue, boolean z) {
        this.f934a.getValueForDensity(i2, i3, typedValue, z);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int i2) {
        return this.f934a.getXml(i2);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] iArr) {
        return this.f934a.obtainAttributes(attributeSet, iArr);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int i2) {
        return this.f934a.obtainTypedArray(i2);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i2) {
        return this.f934a.openRawResource(i2);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i2) {
        return this.f934a.openRawResourceFd(i2);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) {
        this.f934a.parseBundleExtra(str, attributeSet, bundle);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) {
        this.f934a.parseBundleExtras(xmlResourceParser, bundle);
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.f934a;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i2, Resources.Theme theme) {
        return ResourcesCompat.e(this.f934a, i2, theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i2, int i3, Resources.Theme theme) {
        return ResourcesCompat.f(this.f934a, i2, i3, theme);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int i2, int i3) {
        return this.f934a.getQuantityString(i2, i3);
    }

    @Override // android.content.res.Resources
    public String getString(int i2, Object... objArr) {
        return this.f934a.getString(i2, objArr);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int i2, CharSequence charSequence) {
        return this.f934a.getText(i2, charSequence);
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z) {
        this.f934a.getValue(str, typedValue, z);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int i2, TypedValue typedValue) {
        return this.f934a.openRawResource(i2, typedValue);
    }
}
