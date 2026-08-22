package cn.nubia.gamecenter.settings.gamekeylamp;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public class LampItemDecoration extends RecyclerView.ItemDecoration {
    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.colorful_light_color_view_start);
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            if (CommonUtil.isLayoutRTL(view.getContext())) {
                rect.right = dimensionPixelSize;
            } else {
                rect.left = dimensionPixelSize;
            }
        }
    }
}
