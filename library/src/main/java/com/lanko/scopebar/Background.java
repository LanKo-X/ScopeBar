package com.lanko.scopebar;

import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.ColorInt;

/**
 * Method class that provide background drawable for {@link ScopeBar}
 * Created by Lex on 16/7/23.
 */
public class Background {
    /**
     * Scope Bar 背景线框
     *
     * @param outerRad    外围圆角矩形的圆角弧度
     * @param strokeWidth 圆角矩形线框宽度
     * @param color       线框的颜色
     * @return 线框对象
     */
    public static ShapeDrawable getBackgroundRect(float outerRad, float strokeWidth, @ColorInt int color) {

        final float[] outerRadii = new float[]{outerRad, outerRad, outerRad, outerRad, outerRad, outerRad, outerRad, outerRad};
        final RectF inset = new RectF(strokeWidth, strokeWidth, strokeWidth, strokeWidth);

        final float innerRad = outerRad - strokeWidth;
        final float[] innerRadii = new float[]{innerRad, innerRad, innerRad, innerRad, innerRad, innerRad, innerRad, innerRad};

        ShapeDrawable drawable = new ShapeDrawable(new RoundRectShape(outerRadii, inset, innerRadii));

        setPaint(drawable, color);

        return drawable;
    }

    /**
     * 仅左边圆角实心矩形
     *
     * @param outerRad 左边圆角的弧度
     * @param color    圆角矩形颜色
     * @return 圆角矩形对象
     */
    public static ShapeDrawable getLeftRoundRect(float outerRad, @ColorInt int color) {
        final float[] outerRadii = new float[]{outerRad, outerRad, 0, 0, 0, 0, outerRad, outerRad};
        return getRoundRect(outerRadii, color);
    }

    /**
     * 仅右边圆角实心矩形
     *
     * @param outerRad 右边圆角的弧度
     * @param color    圆角矩形颜色
     * @return 圆角矩形对象
     */
    public static ShapeDrawable getRightRoundRect(float outerRad, @ColorInt int color) {
        final float[] outerRadii = new float[]{0, 0, outerRad, outerRad, outerRad, outerRad, 0, 0};
        return getRoundRect(outerRadii, color);
    }

    /**
     * 获得指定弧度数的圆角矩形
     *
     * @param outerRadii 圆角数据
     * @param color      圆角矩形颜色
     * @return 圆角矩形对象
     */
    private static ShapeDrawable getRoundRect(float[] outerRadii, @ColorInt int color) {
        ShapeDrawable drawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));

        setPaint(drawable, color);

        return drawable;
    }

    /**
     * 获得一个矩形
     *
     * @param color 矩形颜色
     * @return 矩形对象
     */
    public static ShapeDrawable getRectangle(@ColorInt int color) {
        ShapeDrawable drawable = new ShapeDrawable(new RectShape());

        setPaint(drawable, color);

        return drawable;
    }

    private static void setPaint(ShapeDrawable drawable, @ColorInt int color) {
        drawable.getPaint().setColor(color);
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setStyle(Paint.Style.FILL);
    }
}
