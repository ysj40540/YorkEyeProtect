package com.york.library;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

/**
 * @author : York
 * @time : 2023/5/25
 * @Description:
 */
public class EyeProtectHelper {

    private static final String LAYER_TAG = "EyeProtectTag";

    public static synchronized void controlLayer(Activity activity) {
        if (activity == null) {
            return;
        }
        boolean bulueLight = true;
        FrameLayout rootView = getRootFromActivity(activity);
        View layer = getLayer(rootView);
        //有蒙层，控制图层显示隐藏
        if (layer != null) {
            layer.setVisibility(bulueLight ? View.VISIBLE : View.GONE);
        } else {
            //没有蒙层，创建并添加蒙层
            if (!bulueLight) {
                return;
            }
            layer = new FrameLayout(activity);
            layer.setBackgroundColor(Color.parseColor("#686445"));
            layer.setTag(LAYER_TAG);
            rootView.addView(layer,
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
    }

    public static void removeLayer(Activity activity) {
        if (activity == null) {
            return;
        }
        FrameLayout rootView = getRootFromActivity(activity);
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View childAt = rootView.getChildAt(i);
            if (TextUtils.equals((CharSequence) childAt.getTag(), LAYER_TAG)) {
                rootView.removeView(childAt);
            }
        }
    }

    @Nullable
    private static View getLayer(FrameLayout rootView) {
        View layer = null;
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View childAt = rootView.getChildAt(i);
            Object tag = childAt.getTag();
            if (tag instanceof CharSequence && TextUtils.equals((CharSequence) tag, LAYER_TAG)) {
                layer = childAt;
                break;
            }
        }
        return layer;
    }

    private static FrameLayout getRootFromActivity(Activity activity) {
        return (FrameLayout) activity.getWindow().getDecorView().getRootView();
    }

}
