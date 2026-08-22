package cn.nubia.gamecenter.settings;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.gamekeylamp.GameKeysLampFragment;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.List;

/* loaded from: classes.dex */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CategoryAdapter";
    public static final String TAG_1 = "fragment_records";
    public static final String TAG_11 = "fragment_gamekeylamp";
    public static final String TAG_2 = "fragment_network";
    public static final String TAG_3 = "fragment_falsetouch";
    public static final String TAG_5 = "fragment_basic";
    private static final String VALUE_TYPE_SUMMARY_KEYWORD_WEEK = "summary_keyword_week";
    private final List<CategoryInfo> mCategories;
    private final Context mContext;
    private OnItemClickListener mListener;
    public boolean mSummaryWeekMode = false;
    private int mCurrentPosition = 0;

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mBackgroundView;
        private final View mItemView;
        private final View mRedEndView;
        private final View mRedLineEndView;
        private final Group mSelectGroup;
        private final TextView mTitleView;

        public CategoryViewHolder(View view) {
            super(view);
            View findViewById = view.findViewById(R.id.category_item_root);
            this.mItemView = findViewById;
            this.mTitleView = (TextView) view.findViewById(R.id.title);
            this.mSelectGroup = (Group) view.findViewById(R.id.select_group);
            this.mBackgroundView = (ImageView) view.findViewById(R.id.background);
            this.mRedEndView = view.findViewById(R.id.red_end);
            this.mRedLineEndView = view.findViewById(R.id.red_line_end);
            findViewById.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition == CategoryAdapter.this.mCurrentPosition) {
                return;
            }
            int i = CategoryAdapter.this.mCurrentPosition;
            CategoryAdapter.this.mCurrentPosition = adapterPosition;
            CategoryAdapter.this.notifyItemChanged(i);
            CategoryAdapter categoryAdapter = CategoryAdapter.this;
            categoryAdapter.notifyItemChanged(categoryAdapter.mCurrentPosition);
            CategoryAdapter categoryAdapter2 = CategoryAdapter.this;
            categoryAdapter2.onItemClick(categoryAdapter2.mCurrentPosition);
        }
    }

    class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(View view) {
            super(view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Class<?> cls);
    }

    public CategoryAdapter(Context context, List<CategoryInfo> list) {
        this.mCategories = list;
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClick(int i) {
        OnItemClickListener onItemClickListener = this.mListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this.mCategories.get(i).getCls());
        }
    }

    private void selectItem(CategoryViewHolder categoryViewHolder, boolean z) {
        categoryViewHolder.mTitleView.setTextColor(this.mContext.getColorStateList(z ? R.color.gcs_gamecenter_menu_text_checked : R.color.gcs_summary_normal_summary));
        categoryViewHolder.mSelectGroup.setVisibility(z ? 0 : 8);
        if (z) {
            startItemAnimation(categoryViewHolder);
        }
    }

    private void startItemAnimation(CategoryViewHolder categoryViewHolder) {
        GcsAnimationUtil.setGcsItemBgTranslationX(categoryViewHolder.mBackgroundView);
        GcsAnimationUtil.setGcsRedItemAlpha(categoryViewHolder.mRedEndView);
        GcsAnimationUtil.setGcsRedItemAlpha(categoryViewHolder.mRedLineEndView);
    }

    public int getCurrentPosition() {
        return this.mCurrentPosition;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mCategories.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mCategories.get(i).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof CategoryViewHolder) {
            CategoryViewHolder categoryViewHolder = (CategoryViewHolder) viewHolder;
            CategoryInfo categoryInfo = this.mCategories.get(i);
            categoryViewHolder.mTitleView.setText(categoryInfo.getTitle());
            categoryViewHolder.mTitleView.setCompoundDrawablesRelativeWithIntrinsicBounds(categoryInfo.getIcon(), 0, 0, 0);
            selectItem(categoryViewHolder, this.mCurrentPosition == i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        return i == 0 ? new DividerViewHolder(from.inflate(R.layout.gcs_divider, viewGroup, false)) : new CategoryViewHolder(from.inflate(R.layout.gcs_category_item, viewGroup, false));
    }

    public void onResume() {
        onItemClick(this.mCurrentPosition);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public boolean setStartType(String str) {
        LogUtil.i(TAG, "setStartType " + str);
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.hashCode();
        switch (str) {
            case "fragment_network":
                str = "NetFragment";
                break;
            case "fragment_gamekeylamp":
                str = GameKeysLampFragment.TAG;
                break;
            case "fragment_basic":
                str = "OtherOptionsFragment";
                break;
            case "fragment_falsetouch":
                str = "FlaseTouchFragment";
                break;
            case "fragment_records":
                str = "NotDisturbFragment";
                break;
            case "summary_keyword_week":
                this.mSummaryWeekMode = true;
                str = "SummaryFragment";
                break;
        }
        int i = 0;
        while (true) {
            if (i < this.mCategories.size()) {
                if (str.equals(this.mCategories.get(i).getSimpleName())) {
                    this.mCurrentPosition = i;
                    z = true;
                } else {
                    i++;
                }
            }
        }
        if (!z) {
            LogUtil.i(TAG, "not support " + str);
        }
        return z;
    }
}
