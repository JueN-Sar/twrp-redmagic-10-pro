package cn.nubia.gamelauncher.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.adapter.HasAddAdapter;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack;
import cn.nubia.gamelauncher.commoninterface.OnSelectedCountChangeListener;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class UninstallController implements OnSelectedCountChangeListener {
    private static final int DELETE_ALL_USERS = 2;
    private TextView mMiddleTitle;
    private Context mContext = null;
    private AppAddModel mAppAddModel = null;
    private RecyclerView mUninstallList = null;
    private HasAddAdapter mAdapter = null;
    private TextView mUninstallBtn = null;
    private ArrayList<AppListItemBean> mList = new ArrayList<>();
    private IGetAppStatusDataCallBack mCallBack = null;
    private TextView mCancelText = null;
    private TextView mSelectAllText = null;
    private String mSelectAllStr = null;
    private String mSelectNoneStr = null;

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<AppListItemBean> getUninstallList() {
        ArrayList<AppListItemBean> arrayList = this.mList;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        ArrayList<AppListItemBean> arrayList2 = new ArrayList<>();
        Iterator<AppListItemBean> it = this.mList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next.isSelect()) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAdapter(int i) {
        HasAddAdapter hasAddAdapter = new HasAddAdapter();
        this.mAdapter = hasAddAdapter;
        hasAddAdapter.setOnAppAddedListener(this.mAppAddModel);
        this.mAdapter.setType(1);
        this.mAdapter.setDataList(this.mList);
        this.mAdapter.setOnSelectCountChangeListener(this);
        this.mUninstallList.setAdapter(this.mAdapter);
        this.mAdapter.setHasAddCount(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showConfirmDialog(Context context) {
        new AlertDialog.Builder(context, 2131952381).setMessage(this.mContext.getString(R.string.sure_uninstall)).setPositiveButton(this.mContext.getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.UninstallController.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                UninstallController uninstallController = UninstallController.this;
                uninstallController.uninstallApps(uninstallController.getUninstallList());
            }
        }).setNegativeButton(this.mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.UninstallController.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [cn.nubia.gamelauncher.controller.UninstallController$9] */
    public void uninstallApps(final ArrayList<AppListItemBean> arrayList) {
        new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.gamelauncher.controller.UninstallController.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    AppListItemBean appListItemBean = (AppListItemBean) it.next();
                    if (appListItemBean.getComponentName() != null) {
                        UninstallController uninstallController = UninstallController.this;
                        Log.i("lsm", "uninstall package name = " + appListItemBean.getComponentName() + "  sucess == " + uninstallController.doUninstallApp(uninstallController.mContext, CommonUtil.createComponentName(appListItemBean.getComponentName()).getPackageName()));
                    } else {
                        Log.i("lsm", "uninstall package name = null");
                    }
                }
                return null;
            }
        }.execute(new Void[0]);
    }

    public boolean doUninstallApp(Context context, String str) {
        try {
            UserHandle myUserHandle = Process.myUserHandle();
            IBinder iBinder = (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "package");
            Class<?> cls = Class.forName("android.content.pm.IPackageManager$Stub");
            Object invoke = cls.getDeclaredMethod("asInterface", IBinder.class).invoke(cls, iBinder);
            invoke.getClass().getDeclaredMethod("deletePackageAsUser", String.class, Integer.TYPE, Class.forName("android.content.pm.IPackageDeleteObserver"), Integer.TYPE, Integer.TYPE).invoke(invoke, str, -1, null, Integer.valueOf(((Integer) UserHandle.class.getMethod("getIdentifier", new Class[0]).invoke(myUserHandle, new Object[0])).intValue()), 2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void init(final Activity activity) {
        this.mContext = activity.getApplicationContext();
        this.mContext = activity.getApplicationContext();
        TextView textView = (TextView) activity.findViewById(R.id.cancel);
        this.mCancelText = textView;
        textView.setClickable(true);
        this.mCancelText.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.UninstallController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                activity.finish();
            }
        });
        this.mSelectAllText = (TextView) activity.findViewById(R.id.all_select);
        this.mSelectAllStr = this.mContext.getString(R.string.all_select);
        this.mSelectNoneStr = this.mContext.getString(R.string.select_none);
        this.mSelectAllText.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.UninstallController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UninstallController.this.mList != null && UninstallController.this.mList.size() > 0) {
                    Iterator it = UninstallController.this.mList.iterator();
                    while (it.hasNext()) {
                        ((AppListItemBean) it.next()).setSelect(UninstallController.this.mSelectAllText.getText().equals(UninstallController.this.mSelectAllStr));
                    }
                    UninstallController.this.mAdapter.notifyDataSetChanged();
                }
                if (!UninstallController.this.mSelectAllText.getText().equals(UninstallController.this.mSelectAllStr)) {
                    UninstallController.this.onSelectedCountChangeListener(0);
                } else {
                    UninstallController uninstallController = UninstallController.this;
                    uninstallController.onSelectedCountChangeListener(uninstallController.mList.size());
                }
            }
        });
        this.mMiddleTitle = (TextView) activity.findViewById(R.id.middle_name);
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.unintsall_list);
        this.mUninstallList = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        AppAddModel appAddModel = AppAddModel.getInstance();
        this.mAppAddModel = appAddModel;
        if (appAddModel.getAppAddedList() != null) {
            this.mList.clear();
            this.mList.addAll(this.mAppAddModel.getAppAddedList());
            initAdapter(this.mAppAddModel.getAppAddedList().size());
        }
        ArrayList<AppListItemBean> arrayList = this.mList;
        if (arrayList == null || arrayList.size() <= 0) {
            this.mUninstallBtn = (TextView) activity.findViewById(R.id.uninstall_btn);
        } else {
            TextView textView2 = (TextView) activity.findViewById(R.id.uninstall_btn);
            this.mUninstallBtn = textView2;
            textView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.UninstallController.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ArrayList uninstallList = UninstallController.this.getUninstallList();
                    if (uninstallList == null || uninstallList.size() <= 0) {
                        return;
                    }
                    UninstallController.this.showConfirmDialog(activity);
                }
            });
        }
        updateButtonView(false);
        AppAddModel appAddModel2 = this.mAppAddModel;
        IGetAppStatusDataCallBack iGetAppStatusDataCallBack = new IGetAppStatusDataCallBack() { // from class: cn.nubia.gamelauncher.controller.UninstallController.4
            @Override // cn.nubia.gamelauncher.commoninterface.IGetAppStatusDataCallBack
            public void onLoadAddAppListDone(ArrayList<AppListItemBean> arrayList2, int i) {
                UninstallController.this.mList.clear();
                if (arrayList2 != null && arrayList2.size() > 0) {
                    for (int i2 = 0; i2 < i; i2++) {
                        UninstallController.this.mList.add(arrayList2.get(i2));
                    }
                }
                if (UninstallController.this.mAdapter == null) {
                    UninstallController.this.initAdapter(i);
                    return;
                }
                if (UninstallController.this.mList.isEmpty()) {
                    UninstallController.this.updateButtonView(false);
                } else {
                    UninstallController.this.updateButtonView(true);
                }
                UninstallController.this.onSelectedCountChangeListener(0);
                UninstallController.this.mAdapter.notifyDataSetChanged();
                UninstallController.this.mAdapter.setHasAddCount(i);
            }
        };
        this.mCallBack = iGetAppStatusDataCallBack;
        appAddModel2.resisterGetAppStatusDataCallBack(iGetAppStatusDataCallBack);
    }

    public void onDestory() {
        AppAddModel.getInstance().unResisterGetAppStatusDataCallBack(this.mCallBack);
        this.mCallBack = null;
    }

    public void onPasue() {
    }

    public void onResume() {
    }

    @Override // cn.nubia.gamelauncher.commoninterface.OnSelectedCountChangeListener
    public void onSelectedCountChangeListener(int i) {
        if (i < this.mList.size() || this.mList.isEmpty()) {
            this.mSelectAllText.setText(this.mSelectAllStr);
        } else if (i == this.mList.size()) {
            this.mSelectAllText.setText(this.mSelectNoneStr);
        }
        this.mMiddleTitle.setText(this.mContext.getString(R.string.has_slect_num) + "（" + i + "）");
        if (getUninstallList() == null || (getUninstallList() != null && getUninstallList().size() == 0)) {
            updateButtonView(false);
        } else {
            updateButtonView(true);
        }
    }

    public void showConfirmDialog(final Context context, final String str) {
        new AlertDialog.Builder(context, 2131952381).setMessage(context.getString(R.string.sure_uninstall)).setPositiveButton(context.getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.UninstallController.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                UninstallController.this.doUninstallApp(context, str);
            }
        }).setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.UninstallController.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create().show();
    }

    void updateButtonView(boolean z) {
        this.mUninstallBtn.setClickable(z);
        this.mUninstallBtn.setAlpha(z ? 1.0f : 0.36f);
        this.mUninstallBtn.setBackgroundResource(z ? R.drawable.delete_btn : R.mipmap.delete_unpress);
        ArrayList<AppListItemBean> arrayList = this.mList;
        if (arrayList == null || arrayList.size() <= 0) {
            this.mSelectAllText.setClickable(false);
            this.mSelectAllText.setAlpha(0.36f);
        } else {
            this.mSelectAllText.setClickable(true);
            this.mSelectAllText.setAlpha(1.0f);
        }
    }
}
