package jfh.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Jiao Fuhao(阿焦)
 * @date 2016/11/29 下午 5:23
 * @email jiaofuhao2012@163.com
 */
public class CoverLayout extends ViewGroup {

    public CoverLayout(Context context) {
        super(context);
    }

    public CoverLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoverLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        int maxWidth = 0;
        int maxHeight = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
//                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            }
        }

        maxWidth += getPaddingLeft() + getPaddingRight();
        maxHeight += getPaddingTop() + getPaddingBottom();

        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0), resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                int width, height, left, top;
                width = child.getMeasuredWidth();
                height = child.getMeasuredHeight();
                switch (layoutParams.gravity) {
                    case Gravity.LEFT:
                        top = (int) ((getMeasuredHeight() - height) / 2 + 0.5);
                        child.layout(getPaddingLeft(), top, getPaddingLeft() + width, getPaddingTop() + height + top);
                        break;
                    case Gravity.RIGHT:
                        top = (int) ((getMeasuredHeight() - height) / 2 + 0.5);
                        left = getMeasuredWidth() - width;
                        child.layout(getPaddingLeft() + left, top, getPaddingLeft() + left + width, getPaddingTop() + height + top);
                        break;
                    case Gravity.TOP:
                        left = (int) ((getMeasuredWidth() - width) / 2 + 0.5);
                        child.layout(getPaddingLeft() + left, getPaddingTop(), getPaddingLeft() + left + width, getPaddingTop() + height);
                        break;
                    case Gravity.BOTTOM:
                        top = getMeasuredHeight() - height;
                        left = (int) ((getMeasuredWidth() - width) / 2 + 0.5);
                        child.layout(getPaddingLeft() + left, getPaddingTop() + top, getPaddingLeft() + left + width, getPaddingTop() + top + height);
                        break;
                    case Gravity.LEFTTOP:
                        child.layout(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + width, getPaddingTop() + height);
                        break;
                    case Gravity.RIGHTTOP:
                        left = getMeasuredWidth() - width;
                        child.layout(getPaddingLeft() + left, getPaddingTop(), getPaddingLeft() + left + width, getPaddingTop() + height);
                        break;
                    case Gravity.LEFTBOTTOM:
                        top = getMeasuredHeight() - height;
                        child.layout(getPaddingLeft(), getPaddingTop() + top, getPaddingLeft() + width, getPaddingTop() + height + top);
                        break;
                    case Gravity.RIGHTBOTTOM:
                        top = getMeasuredHeight() - height;
                        left = getMeasuredWidth() - width;
                        child.layout(getPaddingLeft() + left, getPaddingTop() + top, getPaddingLeft() + left + width, getPaddingTop() + top + height);
                        break;
                    case Gravity.CENTER:
                        top = (int) ((getMeasuredHeight() - height) / 2 + 0.5);
                        left = (int) ((getMeasuredWidth() - width) / 2 + 0.5);
                        child.layout(getPaddingLeft() + left, getPaddingTop() + top, getPaddingLeft() + left + width, getPaddingTop() + top + height);
                        break;
                }
            }
        }
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        public int gravity = Gravity.CENTER;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CoverLayout_Style);
            gravity = a.getInt(R.styleable.CoverLayout_Style_gravity, Gravity.CENTER);
            a.recycle();
        }

        public LayoutParams(int width, int height, int gravity) {
            super(width, height);
            this.gravity = gravity;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    public static class Gravity {

        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int TOP = 2;
        public static final int BOTTOM = 3;

        public static final int LEFTTOP = 4;
        public static final int RIGHTTOP = 5;
        public static final int LEFTBOTTOM = 6;
        public static final int RIGHTBOTTOM = 7;

        public static final int CENTER = 8;
    }
}

