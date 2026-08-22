package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.app.AlertDialogCenter;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.TgkCaseRenameDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.util.ToastUtil;
import cn.nubia.resourcelibrary.ResourceLibProvider;
import cn.nubia.tgk.TgkHelper;
import cn.nubia.tgk.data.TgkData;
import cn.nubia.tgk.data.TgkDataContract;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: classes.dex */
public class ResourceSettings extends FrameLayout implements GameControlDialog.ISetViewAnimation {
    private static final String TAG = "ResourceSettings";
    private Context mContext;
    private AlertDialogCenter mDeleteDialog;
    private AlertDialog mHelperDialog;
    private ListView mListView;
    private String mPackageName;
    TgkCaseAdapter mVaTitleAd;
    private TgkProcessListener tgkProcessListener;

    private static class DeleteAsyncTask extends AsyncTask<Long, Integer, Integer> {
        private String mShotPicture = "";
        private WeakReference<ResourceSettings> resourceSettingsWeakReference;

        protected DeleteAsyncTask(ResourceSettings resourceSettings) {
            this.resourceSettingsWeakReference = new WeakReference<>(resourceSettings);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Long... lArr) {
            ResourceSettings resourceSettings = this.resourceSettingsWeakReference.get();
            if (resourceSettings != null) {
                if ((((int) lArr[0].longValue()) & 1) > 0) {
                    TgkHelper.deleteSelectedTgkCase(resourceSettings.mContext, 1, lArr[1].longValue(), resourceSettings.mPackageName, this.mShotPicture);
                } else {
                    TgkHelper.deleteTgkCase(resourceSettings.mContext.getContentResolver(), 1, lArr[1].longValue(), resourceSettings.mContext, this.mShotPicture);
                }
            }
            return 0;
        }

        public void setShotPicture(String str) {
            this.mShotPicture = str;
        }
    }

    private static class RenameAsyncTask extends AsyncTask<String, Integer, Integer> {
        private long mCaseId;
        private int mTableId;
        private WeakReference<ResourceSettings> resourceSettingsWeakReference;

        protected RenameAsyncTask(ResourceSettings resourceSettings, int i, long j) {
            this.mTableId = 0;
            this.mCaseId = 0L;
            this.resourceSettingsWeakReference = new WeakReference<>(resourceSettings);
            this.mTableId = i;
            this.mCaseId = j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(String... strArr) {
            ResourceSettings resourceSettings = this.resourceSettingsWeakReference.get();
            if (resourceSettings != null) {
                TgkHelper.updateTgkCase(resourceSettings.mContext.getContentResolver(), this.mTableId, this.mCaseId, TgkDataContract.TgkEntry.TGK_CASE_SHOW_NAME, strArr[0]);
            }
            return 0;
        }
    }

    private static class ShareTgkAsyncTask extends AsyncTask<Integer, Integer, Intent> {
        private WeakReference<ResourceSettings> resourceSettingsWeakReference;

        protected ShareTgkAsyncTask(ResourceSettings resourceSettings) {
            this.resourceSettingsWeakReference = new WeakReference<>(resourceSettings);
        }

        private void cleanTgkDirFolder(File file) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    deleteFolder(file2);
                }
            }
        }

        private String createFile(Context context, TgkData tgkData) {
            String str = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + "/tgk_case";
            File file = new File(str);
            if (file.exists()) {
                cleanTgkDirFolder(file);
            } else {
                file.mkdir();
            }
            String[][] strArr = {new String[]{"<tgk_case_info>", "\r\n"}, new String[]{"<state>", "</state>\r\n"}, new String[]{"<original_name>", "</original_name>\r\n"}, new String[]{"<show_name>", "</show_name>\r\n"}, new String[]{"<package_name>", "</package_name>\r\n"}, new String[]{"<main_sw>", "</main_sw>\r\n"}, new String[]{"<left_sw>", "</left_sw>\r\n"}, new String[]{"<right_sw>", "</right_sw>\r\n"}, new String[]{"<middle_sw>", "</middle_sw>\r\n"}, new String[]{"<vibrate_sw>", "</vibrate_sw>\r\n"}, new String[]{"<left_sensitivity>", "</left_sensitivity>\r\n"}, new String[]{"<right_sensitivity>", "</right_sensitivity>\r\n"}, new String[]{"<left_points>", "</left_points>\r\n"}, new String[]{"<right_points>", "</right_points>\r\n"}, new String[]{"<middle_points>", "</middle_points>\r\n"}, new String[]{"<left_option>", "</left_option>\r\n"}, new String[]{"<right_option>", "</right_option>\r\n"}, new String[]{"<middle_option>", "</middle_option>\r\n"}, new String[]{"<picture>", "</picture>\r\n"}, new String[]{"</", "tgk_case_info>"}};
            String[] queryTgkCaseToShare = TgkHelper.queryTgkCaseToShare(context.getContentResolver(), TgkData.getTableId(tgkData.state), tgkData.packageName, "_id", String.valueOf(tgkData.ID));
            if (queryTgkCaseToShare == null) {
                return null;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str + "/tgk_case_info.xml"));
                for (int i = 0; i < 20; i++) {
                    fileOutputStream.write(strArr[i][0].getBytes());
                    if (i < queryTgkCaseToShare.length - 1) {
                        fileOutputStream.write(queryTgkCaseToShare[i].getBytes());
                    }
                    fileOutputStream.write(strArr[i][1].getBytes());
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (queryTgkCaseToShare[queryTgkCaseToShare.length - 1] != null) {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(str + "/preview_img.png"));
                    fileOutputStream2.write(queryTgkCaseToShare[queryTgkCaseToShare.length - 1].getBytes());
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return zipFolder(context, str, str + ".zip");
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

        private void gtantUriPremission(Context context, Uri uri, Intent intent) {
            Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
            while (it.hasNext()) {
                context.grantUriPermission(it.next().activityInfo.packageName, uri, 3);
            }
        }

        private int zipFiles(Context context, String str, String str2, ZipOutputStream zipOutputStream) {
            FileInputStream fileInputStream;
            if (zipOutputStream == null) {
                return -1;
            }
            File file = new File(str + str2);
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry(str2);
                FileInputStream fileInputStream2 = null;
                try {
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (FileNotFoundException e) {
                    e = e;
                } catch (IOException e2) {
                    e = e2;
                }
                try {
                    zipOutputStream.putNextEntry(zipEntry);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.closeEntry();
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                        LogUtil.d(ResourceSettings.TAG, " zipFiles --- IOException ");
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException unused2) {
                            LogUtil.d(ResourceSettings.TAG, " zipFiles --- IOException ");
                        }
                    }
                    return -1;
                } catch (IOException e4) {
                    e = e4;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException unused3) {
                            LogUtil.d(ResourceSettings.TAG, " zipFiles --- IOException ");
                        }
                    }
                    return -1;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException unused4) {
                            LogUtil.d(ResourceSettings.TAG, " zipFiles --- IOException ");
                        }
                    }
                    throw th;
                }
            } else {
                String[] list = file.list();
                if (list == null || list.length <= 0) {
                    try {
                        zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                        zipOutputStream.closeEntry();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return -1;
                    }
                }
                for (String str3 : list) {
                    zipFiles(context, str + str2 + "/", str3, zipOutputStream);
                }
            }
            return 0;
        }

        /* JADX WARN: Not initialized variable reg: 4, insn: 0x009e: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:40:0x009e */
        /* JADX WARN: Removed duplicated region for block: B:12:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private java.lang.String zipFolder(android.content.Context r8, java.lang.String r9, java.lang.String r10) {
            /*
                r7 = this;
                java.lang.String r0 = " zipFolder IOException "
                java.lang.String r1 = "ResourceSettings"
                java.io.File r2 = new java.io.File
                r2.<init>(r10)
                boolean r3 = r2.exists()
                if (r3 == 0) goto L12
                r2.delete()
            L12:
                r2 = 0
                r3 = -1
                java.util.zip.ZipOutputStream r4 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65 java.io.FileNotFoundException -> L77
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65 java.io.FileNotFoundException -> L77
                r5.<init>(r10)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65 java.io.FileNotFoundException -> L77
                r4.<init>(r5)     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L65 java.io.FileNotFoundException -> L77
                java.io.File r5 = new java.io.File     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                r5.<init>(r9)     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                r9.<init>()     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                java.lang.String r6 = r5.getParent()     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                java.lang.StringBuilder r9 = r9.append(r6)     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                java.lang.String r6 = java.io.File.separator     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                java.lang.StringBuilder r9 = r9.append(r6)     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                java.lang.String r9 = r9.toString()     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                java.lang.String r5 = r5.getName()     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                int r3 = r7.zipFiles(r8, r9, r5, r4)     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                r4.finish()     // Catch: java.io.IOException -> L5f java.io.FileNotFoundException -> L61 java.lang.Throwable -> L9d
                r4.close()     // Catch: java.io.IOException -> L49
                goto L98
            L49:
                r7 = move-exception
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r8.<init>(r0)
                java.lang.String r7 = r7.toString()
                java.lang.StringBuilder r7 = r8.append(r7)
                java.lang.String r7 = r7.toString()
                cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r1, r7)
                goto L98
            L5f:
                r7 = move-exception
                goto L67
            L61:
                r7 = move-exception
                goto L79
            L63:
                r7 = move-exception
                goto L9f
            L65:
                r7 = move-exception
                r4 = r2
            L67:
                r7.printStackTrace()     // Catch: java.lang.Throwable -> L9d
                if (r4 == 0) goto L97
                r4.close()     // Catch: java.io.IOException -> L70
                goto L97
            L70:
                r7 = move-exception
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r8.<init>(r0)
                goto L88
            L77:
                r7 = move-exception
                r4 = r2
            L79:
                r7.printStackTrace()     // Catch: java.lang.Throwable -> L9d
                if (r4 == 0) goto L97
                r4.close()     // Catch: java.io.IOException -> L82
                goto L97
            L82:
                r7 = move-exception
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                r8.<init>(r0)
            L88:
                java.lang.String r7 = r7.toString()
                java.lang.StringBuilder r7 = r8.append(r7)
                java.lang.String r7 = r7.toString()
                cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r1, r7)
            L97:
                r10 = r2
            L98:
                if (r3 >= 0) goto L9b
                goto L9c
            L9b:
                r2 = r10
            L9c:
                return r2
            L9d:
                r7 = move-exception
                r2 = r4
            L9f:
                if (r2 == 0) goto Lba
                r2.close()     // Catch: java.io.IOException -> La5
                goto Lba
            La5:
                r8 = move-exception
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r9.<init>(r0)
                java.lang.String r8 = r8.toString()
                java.lang.StringBuilder r8 = r9.append(r8)
                java.lang.String r8 = r8.toString()
                cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil.d(r1, r8)
            Lba:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.ShareTgkAsyncTask.zipFolder(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Intent doInBackground(Integer... numArr) {
            Context context;
            String createFile;
            ResourceSettings resourceSettings = this.resourceSettingsWeakReference.get();
            if (resourceSettings == null || (createFile = createFile((context = resourceSettings.mContext), (TgkData) resourceSettings.mVaTitleAd.getItem(numArr[0].intValue()))) == null) {
                return null;
            }
            Intent intent = new Intent();
            Uri zipFileUri = getZipFileUri(context, createFile);
            intent.setAction("android.intent.action.SEND");
            intent.addFlags(268435456);
            intent.setType("application/zip");
            intent.putExtra("android.intent.extra.STREAM", zipFileUri);
            gtantUriPremission(context, zipFileUri, intent);
            Intent createChooser = Intent.createChooser(intent, null);
            createChooser.addFlags(268435456);
            gtantUriPremission(context, zipFileUri, createChooser);
            return createChooser;
        }

        public Uri getZipFileUri(Context context, String str) {
            return ResourceLibProvider.getUriForFile(context, "cn.nubia.gamelauncher.resourcelib.FileProvider", new File(str));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Intent intent) {
            ResourceSettings resourceSettings = this.resourceSettingsWeakReference.get();
            if (resourceSettings == null || intent == null) {
                return;
            }
            resourceSettings.mContext.startActivity(intent);
            resourceSettings.tgkProcessListener.starTgkShareFile();
        }
    }

    class TgkCaseAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mLayoutInflater;
        private List<TgkData> mList;

        class OnItemBtnClickListener implements View.OnClickListener {
            private int mPosition;

            public OnItemBtnClickListener(int i) {
                this.mPosition = i;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tgk_case_delete_ctl_panel /* 2131363442 */:
                        TgkCaseAdapter.this.deleteCase(this.mPosition);
                        LogUtil.i(ResourceSettings.TAG, "tgk_case_delete_ctl_panel");
                        break;
                    case R.id.tgk_case_rename_ctl_panel /* 2131363461 */:
                        TgkCaseAdapter.this.renameCase(this.mPosition);
                        LogUtil.i(ResourceSettings.TAG, "tgk_case_rename_ctl_panel");
                        break;
                    case R.id.tgk_case_share_ctl_panel /* 2131363462 */:
                        TgkCaseAdapter.this.shareCase(this.mPosition);
                        LogUtil.i(ResourceSettings.TAG, "tgk_case_share_ctl_panel");
                        break;
                }
            }
        }

        private final class ViewHolder {
            public ImageButton caseDelete;
            public ImageButton caseRename;
            public ImageButton caseShare;
            public TextView showName;
            public ImageButton typeIcon;

            private ViewHolder() {
            }
        }

        public TgkCaseAdapter(Context context, List<TgkData> list) {
            this.mContext = context;
            this.mList = list;
            this.mLayoutInflater = LayoutInflater.from(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void renameCase(int i) {
            showGuide(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void shareCase(int i) {
            new ShareTgkAsyncTask(ResourceSettings.this).execute(Integer.valueOf(i));
        }

        private void showGuide(int i) {
            final TgkData tgkData = this.mList.get(i);
            TgkCaseRenameDialog tgkCaseRenameDialog = new TgkCaseRenameDialog(this.mContext, tgkData.showName);
            tgkCaseRenameDialog.setOnDismissListener(new TgkCaseRenameDialog.OnDismissListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.TgkCaseAdapter.1
                @Override // cn.nubia.gamelauncher.gamecontrolpanel.TgkCaseRenameDialog.OnDismissListener
                public int onDismiss(String str) {
                    int i2;
                    int tableId = TgkData.getTableId(tgkData.state);
                    if (TgkHelper.hasSameTgkCaseName(TgkCaseAdapter.this.mContext.getContentResolver(), Long.valueOf(tgkData.ID), tgkData.packageName, str)) {
                        ToastUtil.showGamemodeToast(TgkCaseAdapter.this.mContext.getResources().getString(R.string.tgk_rename_repeat_prompt));
                        i2 = -1;
                    } else {
                        new RenameAsyncTask(ResourceSettings.this, tableId, tgkData.ID).execute(str);
                        tgkData.showName = str;
                        TgkCaseAdapter.this.notifyDataSetChanged();
                        i2 = 0;
                    }
                    LogUtil.i(ResourceSettings.TAG, "ondismisslistener ret = " + i2);
                    return i2;
                }
            });
            tgkCaseRenameDialog.show();
        }

        public void deleteActionFuc(int i) {
            TgkData tgkData = this.mList.get(i);
            long j = tgkData.ID;
            int i2 = tgkData.state;
            DeleteAsyncTask deleteAsyncTask = new DeleteAsyncTask(ResourceSettings.this);
            deleteAsyncTask.setShotPicture(tgkData.shotPicture);
            deleteAsyncTask.execute(Long.valueOf(i2), Long.valueOf(j));
            this.mList.remove(i);
            notifyDataSetChanged();
        }

        public void deleteCase(int i) {
            ResourceSettings.this.showDeleteDialog(i);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.mList.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.mLayoutInflater.inflate(R.layout.control_panel_tgk_case_list_item_layout, (ViewGroup) null);
                viewHolder.typeIcon = (ImageButton) view2.findViewById(R.id.tgk_case_type_icon);
                viewHolder.showName = (TextView) view2.findViewById(R.id.tgk_case_showname);
                viewHolder.caseRename = (ImageButton) view2.findViewById(R.id.tgk_case_rename_ctl_panel);
                viewHolder.caseShare = (ImageButton) view2.findViewById(R.id.tgk_case_share_ctl_panel);
                viewHolder.caseDelete = (ImageButton) view2.findViewById(R.id.tgk_case_delete_ctl_panel);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.showName.setText(this.mList.get(i).showName);
            if ((this.mList.get(i).state & 4) > 0) {
                viewHolder.typeIcon.setBackground(this.mContext.getResources().getDrawable(R.drawable.tgk_case_type_preset_icon, null));
                viewHolder.caseDelete.setVisibility(4);
            } else {
                viewHolder.typeIcon.setBackground(this.mContext.getResources().getDrawable(R.drawable.tgk_case_type_import_icon, null));
                viewHolder.caseDelete.setVisibility(0);
                viewHolder.caseDelete.setOnClickListener(new OnItemBtnClickListener(i));
            }
            viewHolder.caseRename.setOnClickListener(new OnItemBtnClickListener(i));
            viewHolder.caseShare.setOnClickListener(new OnItemBtnClickListener(i));
            return view2;
        }
    }

    public ResourceSettings(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ResourceSettings(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHelperDialog = null;
        this.mDeleteDialog = null;
        this.mContext = context;
    }

    private ArrayList<TgkData> getData() {
        ArrayList<TgkData> queryTgkCasesByResourceSettings = TgkHelper.queryTgkCasesByResourceSettings(this.mContext.getContentResolver(), this.mPackageName);
        return queryTgkCasesByResourceSettings == null ? new ArrayList<>() : queryTgkCasesByResourceSettings;
    }

    private void initView(ArrayList<TgkData> arrayList) {
        LayoutInflater.from(getContext()).inflate(R.layout.nubia_game_strengthen_view_resource, this);
        this.mListView = (ListView) findViewById(R.id.control_panel_tgk_case_list_view);
        TgkCaseAdapter tgkCaseAdapter = new TgkCaseAdapter(this.mContext, arrayList);
        this.mVaTitleAd = tgkCaseAdapter;
        this.mListView.setAdapter((ListAdapter) tgkCaseAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            }
        });
        ImageButton imageButton = (ImageButton) findViewById(R.id.tgk_case_import_helper_btn);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.tgk_case_import_btn);
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ResourceSettings.this.showHelperInfo();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.i(ResourceSettings.TAG, "start PICK_TGK_FILE");
                Intent intent = new Intent("cn.nubia.tgk.PICK_TGK_FILE");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setFlags(268435456);
                ResourceSettings.this.mContext.startActivity(intent);
                if (ResourceSettings.this.tgkProcessListener != null) {
                    ResourceSettings.this.tgkProcessListener.starTgkPickFile();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeleteDialog(final int i) {
        try {
            if (this.mDeleteDialog == null) {
                AlertDialogCenter create = new AlertDialogCenter.Builder(this.mContext, 2131952382).setTitle(R.string.tgk_delete_prompt).setPositiveButton(R.string.nubia_game_performance_super_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ResourceSettings.this.mVaTitleAd.deleteActionFuc(i);
                        ResourceSettings.this.mDeleteDialog.dismiss();
                        ResourceSettings.this.mDeleteDialog = null;
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.6
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        ResourceSettings.this.mDeleteDialog.dismiss();
                        ResourceSettings.this.mDeleteDialog = null;
                    }
                }).create();
                this.mDeleteDialog = create;
                create.getWindow().setType(2038);
            }
            this.mDeleteDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHelperInfo() {
        try {
            if (this.mHelperDialog == null) {
                AlertDialog create = new AlertDialog.Builder(this.mContext, 2131952382).setTitle(R.string.tgk_import_helper_title).setMessage(R.string.tgk_import_helper_message).setNegativeButton(R.string.nubia_game_performance_super_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ResourceSettings.this.mHelperDialog.dismiss();
                        ResourceSettings.this.mHelperDialog = null;
                    }
                }).create();
                this.mHelperDialog = create;
                create.getWindow().setType(2038);
            }
            this.mHelperDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.ResourceSettings.1
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    ResourceSettings.this.mListView.setAlpha(0.0f);
                    return;
                }
                LogUtil.d(ResourceSettings.TAG, "checked = " + z);
                for (int i = 0; i < ResourceSettings.this.mListView.getCount(); i++) {
                    AnimationUtil.setResourceItemTranslationX(ResourceSettings.this.mListView.getChildAt(i), i);
                    AnimationUtil.setGcsRedItemAlpha(ResourceSettings.this.mListView);
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AlertDialogCenter alertDialogCenter = this.mDeleteDialog;
        if (alertDialogCenter != null) {
            alertDialogCenter.dismiss();
            this.mDeleteDialog = null;
        }
        AlertDialog alertDialog = this.mHelperDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mHelperDialog = null;
        }
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
        initView(getData());
    }

    public void setTgkProcessListener(TgkProcessListener tgkProcessListener) {
        this.tgkProcessListener = tgkProcessListener;
    }
}
