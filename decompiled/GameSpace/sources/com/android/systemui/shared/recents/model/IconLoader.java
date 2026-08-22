package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.util.LruCache;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.system.PackageManagerWrapper;

/* loaded from: classes2.dex */
public abstract class IconLoader {
    private static final String TAG = "IconLoader";
    protected final LruCache<ComponentName, ActivityInfo> mActivityInfoCache;
    protected final Context mContext;
    protected final TaskKeyLruCache<Drawable> mIconCache;

    public static class DefaultIconLoader extends IconLoader {
        private final BitmapDrawable mDefaultIcon;
        private final IconDrawableFactory mDrawableFactory;

        public DefaultIconLoader(Context context, TaskKeyLruCache<Drawable> taskKeyLruCache, LruCache<ComponentName, ActivityInfo> lruCache) {
            super(context, taskKeyLruCache, lruCache);
            Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
            createBitmap.eraseColor(0);
            this.mDefaultIcon = new BitmapDrawable(context.getResources(), createBitmap);
            this.mDrawableFactory = IconDrawableFactory.newInstance(context);
        }

        @Override // com.android.systemui.shared.recents.model.IconLoader
        protected Drawable createBadgedDrawable(Drawable drawable, int i, ActivityManager.TaskDescription taskDescription) {
            return i != UserHandle.myUserId() ? this.mContext.getPackageManager().getUserBadgedIcon(drawable, new UserHandle(i)) : drawable;
        }

        @Override // com.android.systemui.shared.recents.model.IconLoader
        protected Drawable getBadgedActivityIcon(ActivityInfo activityInfo, int i, ActivityManager.TaskDescription taskDescription) {
            return this.mDrawableFactory.getBadgedIcon(activityInfo, activityInfo.applicationInfo, i);
        }

        @Override // com.android.systemui.shared.recents.model.IconLoader
        public Drawable getDefaultIcon(int i) {
            return this.mDefaultIcon;
        }
    }

    public IconLoader(Context context, TaskKeyLruCache<Drawable> taskKeyLruCache, LruCache<ComponentName, ActivityInfo> lruCache) {
        this.mContext = context;
        this.mIconCache = taskKeyLruCache;
        this.mActivityInfoCache = lruCache;
    }

    private Drawable createNewIconForTask(Task.TaskKey taskKey, ActivityManager.TaskDescription taskDescription, boolean z) {
        int i = taskKey.userId;
        Bitmap inMemoryIcon = taskDescription.getInMemoryIcon();
        if (inMemoryIcon != null) {
            return createDrawableFromBitmap(inMemoryIcon, i, taskDescription);
        }
        if (taskDescription.getIconResource() != 0) {
            try {
                PackageManager packageManager = this.mContext.getPackageManager();
                return createBadgedDrawable(packageManager.getResourcesForApplication(packageManager.getApplicationInfo(taskKey.getPackageName(), 4194304)).getDrawable(taskDescription.getIconResource(), null), i, taskDescription);
            } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                Log.e(TAG, "Could not find icon drawable from resource", e);
            }
        }
        Bitmap loadTaskDescriptionIcon = ActivityManager.TaskDescription.loadTaskDescriptionIcon(taskDescription.getIconFilename(), i);
        if (loadTaskDescriptionIcon != null) {
            return this.createDrawableFromBitmap(loadTaskDescriptionIcon, i, taskDescription);
        }
        try {
            PackageManager packageManager2 = this.mContext.getPackageManager();
            if (packageManager2 != null) {
                Drawable loadIcon = packageManager2.getActivityInfo(taskKey.getComponent(), 128).loadIcon(packageManager2);
                if (loadIcon != null) {
                    return this.createBadgedDrawable(loadIcon, i, taskDescription);
                }
                Drawable applicationIcon = packageManager2.getApplicationIcon(packageManager2.getApplicationInfo(taskKey.getPackageName(), 4194304));
                if (applicationIcon != null) {
                    return this.createBadgedDrawable(applicationIcon, i, taskDescription);
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "Could not find icon drawable from getApplicationIcon", e2);
        }
        if (z) {
            return this.getDefaultIcon(i);
        }
        return null;
    }

    protected abstract Drawable createBadgedDrawable(Drawable drawable, int i, ActivityManager.TaskDescription taskDescription);

    protected Drawable createDrawableFromBitmap(Bitmap bitmap, int i, ActivityManager.TaskDescription taskDescription) {
        return createBadgedDrawable(new BitmapDrawable(this.mContext.getResources(), bitmap), i, taskDescription);
    }

    public Drawable getAndInvalidateIfModified(Task.TaskKey taskKey, ActivityManager.TaskDescription taskDescription, boolean z) {
        Drawable createNewIconForTask;
        Drawable andInvalidateIfModified = this.mIconCache.getAndInvalidateIfModified(taskKey);
        if (andInvalidateIfModified != null) {
            return andInvalidateIfModified;
        }
        if (!z || (createNewIconForTask = createNewIconForTask(taskKey, taskDescription, false)) == null) {
            return null;
        }
        this.mIconCache.put(taskKey, createNewIconForTask);
        return createNewIconForTask;
    }

    public ActivityInfo getAndUpdateActivityInfo(Task.TaskKey taskKey) {
        ComponentName component = taskKey.getComponent();
        ActivityInfo activityInfo = this.mActivityInfoCache.get(component);
        if (activityInfo == null) {
            activityInfo = PackageManagerWrapper.getInstance().getActivityInfo(component, taskKey.userId);
            if (component == null || activityInfo == null) {
                Log.e(TAG, "Unexpected null component name or activity info: " + component + ", " + activityInfo);
                return null;
            }
            this.mActivityInfoCache.put(component, activityInfo);
        }
        return activityInfo;
    }

    protected abstract Drawable getBadgedActivityIcon(ActivityInfo activityInfo, int i, ActivityManager.TaskDescription taskDescription);

    public abstract Drawable getDefaultIcon(int i);

    public Drawable getIcon(Task task) {
        Drawable drawable = this.mIconCache.get(task.key);
        if (drawable != null) {
            return drawable;
        }
        Drawable createNewIconForTask = createNewIconForTask(task.key, task.taskDescription, true);
        this.mIconCache.put(task.key, createNewIconForTask);
        return createNewIconForTask;
    }
}
