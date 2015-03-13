package com.xgc1986.ripplebutton.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;

public class RippleDrawableHelper {

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable createRippleDrawable(View v, int color, int rippleColor) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new RippleDrawable(new ColorStateList(
                    new int[][]{new int[]{android.R.attr.state_pressed}},
                    new int[]{rippleColor}
            ), v.getBackground(), v.getBackground());
        } else {

            Drawable d = v.getBackground();

            if (d == null) {
                d = new ColorDrawable(rippleColor);

                StateListDrawable sld = new StateListDrawable();
                sld.addState(
                        new int[] {
                                android.R.attr.state_pressed,
                        },
                        d
                );

                d = v.getBackground();

                if (d == null) {
                    d = new ColorDrawable(color);
                }
                d = d.getConstantState().newDrawable();

                sld.addState(
                        new int[] {
                                0
                        },
                        d
                );

                return sld;

            } else {


                StateListDrawable sld = new StateListDrawable();
                sld.addState(
                        new int[] {
                                android.R.attr.state_pressed,
                        },
                        new ColorDrawable(rippleColor)
                );
                sld.addState(
                        new int[] {
                                0,
                        },
                        new ColorDrawable(color)
                );

                return sld;
            }
        }
    }
}
