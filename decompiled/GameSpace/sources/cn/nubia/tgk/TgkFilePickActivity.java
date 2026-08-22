package cn.nubia.tgk;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.tgk.imports.TgkCaseImport;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* loaded from: classes2.dex */
public class TgkFilePickActivity extends Activity {
    public static final String ACTIVITY_WEIXIN_MINI_GAME = "com.tencent.mm.plugin.appbrand.ui.AppBrand";
    public static final String PACKAGE_WEIXIN = "com.tencent.mm";
    private static final String TAG = "TgkFilePickActivity";
    private MyAsyncTask mAsyncTask;
    private AlertDialog mDialog = null;
    private Handler mHandler;
    private TextView mTV;

    private static class MyAsyncTask extends AsyncTask {
        private String mAppName;
        Context mContext;
        private AlertDialog mDialog;
        View mTV;

        private MyAsyncTask() {
            this.mTV = null;
            this.mContext = null;
            this.mDialog = null;
            this.mAppName = "";
        }

        private void cleanFolder(File file) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    deleteFolder(file2);
                }
            }
        }

        private int copyFile(Uri uri, String str) {
            try {
                InputStream openInputStream = this.mContext.getContentResolver().openInputStream(uri);
                if (openInputStream == null) {
                    Log.e(TgkFilePickActivity.TAG, "copy file input stream is null!");
                    return -1;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
                byte[] bArr = new byte[4096];
                BufferedInputStream bufferedInputStream = new BufferedInputStream(openInputStream, 4096);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 4096);
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 4096);
                    if (read == -1) {
                        bufferedOutputStream.flush();
                        bufferedInputStream.close();
                        bufferedOutputStream.close();
                        openInputStream.close();
                        fileOutputStream.close();
                        return 0;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return -1;
            } catch (IOException e2) {
                e2.printStackTrace();
                return -1;
            }
        }

        private void deleteFolder(File file) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        deleteFolder(file2);
                    } else {
                        file2.delete();
                    }
                }
            }
            file.delete();
        }

        private static String getAppName(Context context, String str) {
            PackageManager packageManager = context.getPackageManager();
            try {
                return packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString();
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e(TgkFilePickActivity.TAG, "Get app name failed!");
                return "";
            }
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object[] objArr) {
            Uri uri = (Uri) objArr[0];
            String str = this.mContext.getFilesDir().getPath() + "/tgk_dir";
            File file = new File(str);
            if (file.exists()) {
                cleanFolder(file);
            } else {
                file.mkdir();
            }
            String str2 = str + "/tgk_case.zip";
            int copyFile = copyFile(uri, str2);
            if (copyFile == 0) {
                String[] parserTgkDataFile = TgkCaseImport.parserTgkDataFile(this.mContext, str2);
                int intValue = Integer.valueOf(parserTgkDataFile[0]).intValue();
                String appNameByPackageName = TgkFilePickActivity.getAppNameByPackageName(this.mContext, parserTgkDataFile[1]);
                this.mAppName = appNameByPackageName;
                if (!TextUtils.isEmpty(appNameByPackageName)) {
                    this.mAppName += "-";
                }
                copyFile = intValue;
            }
            return Integer.valueOf(copyFile);
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Object obj) {
            this.mTV.setVisibility(8);
            int intValue = ((Integer) obj).intValue();
            Log.d(TgkFilePickActivity.TAG, "PostExecute ret=" + intValue);
            if (intValue == 0) {
                this.mDialog.setMessage(this.mAppName + this.mContext.getResources().getString(R.string.tgk_case_import_success_message));
                this.mDialog.show();
            } else if (intValue == -2) {
                this.mDialog.setTitle(R.string.tgk_case_import_fail_title);
                this.mDialog.setMessage(this.mContext.getResources().getString(R.string.tgk_case_import_fail_message_over_max_count));
                this.mDialog.show();
            } else {
                this.mDialog.setTitle(R.string.tgk_case_import_fail_title);
                this.mDialog.setMessage("");
                this.mDialog.show();
            }
            super.onPostExecute(obj);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            this.mTV.setVisibility(0);
        }

        public void setParas(Context context, View view, AlertDialog alertDialog) {
            this.mContext = context;
            this.mTV = view;
            this.mDialog = alertDialog;
        }
    }

    public static String getAppNameByPackageName(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        if (str.contains("@")) {
            return getTaskDescLabel(context);
        }
        try {
            return packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTaskDescLabel(Context context) {
        ActivityManager activityManager;
        String str = "";
        try {
            activityManager = (ActivityManager) context.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error retrieving task description: " + e.getMessage());
        }
        if (activityManager == null) {
            Log.i(TAG, "getTaskDescLabel am = null");
            return "";
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
        if (runningTasks != null && !runningTasks.isEmpty()) {
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            Log.i(TAG, "running task name : " + runningTaskInfo.topActivity);
            if (runningTaskInfo.topActivity != null && runningTaskInfo.topActivity.getClassName().startsWith("com.tencent.mm.plugin.appbrand.ui.AppBrand")) {
                ActivityManager.TaskDescription taskDescription = runningTaskInfo.taskDescription;
                if (taskDescription != null) {
                    str = taskDescription.getLabel();
                }
            }
            return "";
        }
        Log.i(TAG, "getTaskDescLabel = " + str);
        return str;
    }

    private void initDialog() {
        AlertDialog create = new AlertDialog.Builder(this, 2131952382).setTitle(R.string.tgk_case_import_success_title).setNegativeButton(R.string.nubia_game_performance_super_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.tgk.TgkFilePickActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                TgkFilePickActivity.this.mDialog.dismiss();
                TgkFilePickActivity.this.finish();
            }
        }).create();
        this.mDialog = create;
        create.getWindow().setType(2009);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFilesBrowser() {
        initDialog();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivityForResult(intent, 1);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            Log.d(TAG, "in onActivityResult resultCode= " + i2);
            finish();
            return;
        }
        Uri data = intent.getData();
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        this.mAsyncTask = myAsyncTask;
        myAsyncTask.setParas(getApplicationContext(), this.mTV, this.mDialog);
        this.mAsyncTask.execute(data);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tgk_file_pick_activity_layout);
        this.mTV = (TextView) findViewById(R.id.tgk_parsing_view);
        this.mHandler = new Handler();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        MyAsyncTask myAsyncTask = this.mAsyncTask;
        if (myAsyncTask != null) {
            myAsyncTask.cancel(true);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mDialog == null) {
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.tgk.TgkFilePickActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    TgkFilePickActivity.this.startFilesBrowser();
                }
            }, 10L);
        }
    }
}
