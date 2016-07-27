package com.example.qsr.fav_deal.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.example.qsr.fav_deal.utils.LogUtil;


/**************************************
 * FileName : com.example.qsr.fav_deal.ui
 * Author : qsr
 * Time : 2016/6/11 9:37
 * Description : 仿ios的下拉回弹
 **************************************/
public class MyScrollView extends ScrollView {
    //要操作的布局
    private View innerView;
    private float y;
    private Rect normal = new Rect();
    private boolean animationFinish = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if(childCount > 0){
            //如果内部有很多的子控件，那么要操作的子控件确定为ScrollView中第一个
            innerView = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(innerView == null){
            //如果布局内部没有任何控件
            return super.onTouchEvent(ev);
        }else{
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 自定义Touch事件的处理
     *
     * @param ev
     */
    private void commonTouchEvent(MotionEvent ev) {
        if (animationFinish) {//当动画结束时候
            int action = ev.getAction();//获取动作标识
            switch (action) {
                case MotionEvent.ACTION_DOWN://手指按下
                    //需要记录按下点y坐标
                    y = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE://手指移动
                    float preY = y == 0 ? ev.getY() : y;//获取到最原始的起点
                    float nowY = ev.getY();//获取当前y轴坐标
                    int detailY = (int) (preY - nowY);//之前的减去现在的，具体的y轴位移偏移量
                    y = nowY;//将当前点y轴坐标记录
                    if (isNeedMove())
                        //布局改变之前，记录一下正常状态的位置
                        if(normal.isEmpty()){
                            normal.set(innerView.getLeft(),innerView.getTop(),innerView.getRight(),innerView.getBottom());
                        }
                        //开始移动布局
                        innerView.layout(innerView.getLeft(), innerView.getTop() - detailY / 2,
                            innerView.getRight(), innerView.getBottom() - detailY / 2);
                        break;
                case MotionEvent.ACTION_UP://手指抬起
                    y = 0;
                    //布局回滚到原来的位置
                    if (isNeedAnimation()) {
                        animation();
                    }
                    break;
            }
        }
    }

    /**
     * 动画回滚效果
     */
    private void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top - innerView.getTop());
        ta.setDuration(200);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束后清除控件上的动画效果
                innerView.clearAnimation();
                innerView.layout(normal.left, normal.top, normal.right, normal.bottom);
                //清除正常的位置
                normal.setEmpty();
                animationFinish = true;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerView.startAnimation(ta);
    }

    /**
     * 判断是否需要动画回滚
     * @return
     */
    private boolean isNeedAnimation() {
        //正常情况下的点的位置不为空
        return !normal.isEmpty();
    }

    /**
     * 判断是否需要移动
     * @return
     */
    public boolean isNeedMove() {
        //将内部控件的原始高度（与屏幕无关）减去屏幕显示高度
        /**
         * 首先我们要知道我们拖动的是ScrollView的内部控件
         * 当内部控件没有显示区域那么大的时候，我们往下拖动，是不会造成ScrollView的高度变化的
         * 当内部控件比显示区域大，我们往下拖动，会导致ScrollView的高度变大（顶部不动，底部拉伸）
         *
         * 但是当我们往上拖动时候，不管比显示区域大还是小，都会改变ScrollView的高度改变（顶部往上拉伸）
         *
         * offset指的是屏幕显示区域左上角的点和ScrollView显示区域左上角的高度差值
         */
        int offset = innerView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();//获取y轴的拖动量
        LogUtil.MyLog_e(getContext(), "getMeasuredHeight:" + innerView.getMeasuredHeight() + "----getHeight:" + getHeight());
        LogUtil.MyLog_e(getContext(), "offset:" + offset + "----scrollY:" + scrollY);
        if (scrollY == 0 || scrollY == offset) {//判断下拉时候处理，上推时候处理
            return true;
        }
        return true;
    }
}
