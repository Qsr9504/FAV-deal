package com.example.qsr.fav_deal.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.bean.ResultState;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**************************************
 * FileName : com.example.qsr.fav_deal.ui
 * Author : qsr
 * Time : 2016/7/26 11:57
 * Description : 数据加载中间界面
 **************************************/
public abstract class LoadingPage extends FrameLayout{
    private static final int PAGE_LOADING_STATE = 1;
    private static final int PAGE_ERROR_STATE = 2;
    private static final int PAGE_EMPTY_STATE = 3;
    private static final int PAGE_SUCCESS_STATE = 4;
    private Context mConext;

    private int PAGE_CURRENT_STATE = 1;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;
    private LayoutParams lp;
    public ResultState resultState = new ResultState();
    AsyncHttpClient client = new AsyncHttpClient();
    private MyAsyncHttpResponseHandler myAsyncHttpResponseHandler = new MyAsyncHttpResponseHandler();
    public LoadingPage(Context context) {
        this(context,null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mConext = context;//获取fragment布局中的上下文对象
        init();
    }

    private void init() {
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = UIUtils.getXmlView(R.layout.page_loading);
            addView(loadingView, lp);
        }
        if (errorView == null) {
            errorView = UIUtils.getXmlView(R.layout.page_error);
            addView(errorView, lp);
        }
        if (emptyView == null) {
            emptyView = UIUtils.getXmlView(R.layout.page_empty);
            addView(emptyView, lp);
        }
        showSafePage();
    }
    //保证该更新在主线程中执行
    private void showSafePage() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }
    //展示加载界面
    private void showPage(){
        loadingView.setVisibility(PAGE_CURRENT_STATE == PAGE_LOADING_STATE ? View.VISIBLE : View.GONE);
        errorView.setVisibility(PAGE_CURRENT_STATE == PAGE_ERROR_STATE ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(PAGE_CURRENT_STATE == PAGE_EMPTY_STATE ? View.VISIBLE : View.GONE);
        if (successView == null) {
            successView = View.inflate(mConext, LayoutId(), null);
            addView(successView, lp);
        }
        successView.setVisibility(PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE ? View.VISIBLE : View.GONE);
    }
    public abstract int LayoutId();
    public void show(){
        //状态归位
        if (PAGE_CURRENT_STATE != PAGE_LOADING_STATE) {
            PAGE_CURRENT_STATE = PAGE_LOADING_STATE;
        }
        //处理不需要发送请求来决定界面的情况
        String url = url();
        if (TextUtils.isEmpty(url)) {
            resultState.setState(PAGE_SUCCESS_STATE);
            resultState.setContent("");
            loadPage();
        } else {
            LogUtil.MyLog_i(mConext,"url：" + url);
            LogUtil.MyLog_i(mConext,"参数：" + params());
            //发送网络请求
            client.post(url,params(),myAsyncHttpResponseHandler);
        }
    }
    private class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
        @Override
        public void onSuccess(String content) {
            LogUtil.MyLog_e(mConext,"最初返回的json" + content);
            if (TextUtils.isEmpty(content)) {
                resultState.setState(PAGE_EMPTY_STATE);
                resultState.setContent("");
                LogUtil.MyLog_e(mConext,"空的：" + content +"---");
            } else {
                resultState.setState(PAGE_SUCCESS_STATE);
                resultState.setContent(content);
                LogUtil.MyLog_e(mConext,"content：" + content +"---");
            }
            loadPage();
        }

        @Override
        public void onFailure(Throwable error, String content) {
            error.printStackTrace();
            resultState.setState(PAGE_ERROR_STATE);
            resultState.setContent("");
            loadPage();
        }
    }
    private void loadPage() {
        switch (resultState.getState()) {
            case PAGE_ERROR_STATE:
                //显示错误界面
                PAGE_CURRENT_STATE = PAGE_ERROR_STATE;
                break;
            case PAGE_EMPTY_STATE:
                //显示空界面
                PAGE_CURRENT_STATE = PAGE_EMPTY_STATE;
                break;
            case PAGE_SUCCESS_STATE:
                //显示成功界面
                PAGE_CURRENT_STATE = PAGE_SUCCESS_STATE;
                break;
        }
        showSafePage();
        if (PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE) {
            OnSuccess(resultState, successView);
        }
    }

    /**
     * 网络访问成功
     * @param resultState 结果状态
     * @param successView 成功之后要显示的View
     */
    protected abstract void OnSuccess(ResultState resultState, View successView);

    protected abstract RequestParams params();

    /**
     * 要访问的url地址
     * @return
     */
    protected abstract String url();
}
