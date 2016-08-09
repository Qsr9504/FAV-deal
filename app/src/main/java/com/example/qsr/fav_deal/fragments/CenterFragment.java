package com.example.qsr.fav_deal.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.MainActivity;
import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.activities.AddressManageActivity;
import com.example.qsr.fav_deal.activities.FeedbackActivity;
import com.example.qsr.fav_deal.activities.OrderListActivity;
import com.example.qsr.fav_deal.base.BaseFragment;
import com.example.qsr.fav_deal.bean.MessageEvent;
import com.example.qsr.fav_deal.bean.User;
import com.example.qsr.fav_deal.bmobUtil.MesEventForBmob;
import com.example.qsr.fav_deal.bmobUtil.OrderTools;
import com.example.qsr.fav_deal.globle.App;
import com.example.qsr.fav_deal.globle.AppConstants;
import com.example.qsr.fav_deal.ui.GlideLoader;
import com.example.qsr.fav_deal.ui.IconFontTextView;
import com.example.qsr.fav_deal.ui.MyAvatarImageView;
import com.example.qsr.fav_deal.utils.ImageUtil;
import com.example.qsr.fav_deal.utils.LogUtil;
import com.example.qsr.fav_deal.utils.MySPUtil;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

/**************************************
 * FileName : com.example.qsr.fav_deal.fragments
 * Author : qsr
 * Time : 2016/7/26 13:28
 * Description : 个人中心界面
 **************************************/
public class CenterFragment extends BaseFragment {
    @Bind(R.id.avatar)
    MyAvatarImageView avatar;
    @Bind(R.id.llcenter_2)
    LinearLayout llcenter2;
    @Bind(R.id.llcenter_1)
    LinearLayout llcenter1;
    @Bind(R.id.llcenter_3)
    LinearLayout llcenter3;
    @Bind(R.id.llcenter_5)
    LinearLayout llcenter5;
    @Bind(R.id.llcenter_4)
    LinearLayout llcenter4;
    @Bind(R.id.llcenter_6_text)
    TextView llcenter6Text;
    @Bind(R.id.llcenter_6)
    LinearLayout llcenter6;
    @Bind(R.id.phone)
    IconFontTextView phone;
    @Bind(R.id.contact)
    TextView contact;
    @Bind(R.id.contactRela)
    RelativeLayout contactRela;
    private ArrayList<String> images;
    private static final int REQUEST_CODE = 10086;
    private Intent intent;
    private Bitmap bitmap;
    private OrderTools orderTools;
    @Override
    protected void initEvent() {

    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @OnClick(R.id.contactRela)
    public void contactRela(View v) {
        //用户反馈
        startActivity(new Intent(getContext(), FeedbackActivity.class));
    }

    @OnClick(R.id.avatar)
    public void avatar(View v) {
        //点击头像之后的处理事件
        //执行选择图片activity并且获取路径
        showChangePic();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAvatarFromRes(MesEventForBmob event) {
        if(event.getStateCode() == MainActivity.AVATAR){
            String avatarUrl = event.getString();
            try {
                FileInputStream fis = new FileInputStream(avatarUrl);
                bitmap = BitmapFactory.decodeStream(fis);
                avatar.setImageBitmap(ImageUtil.comp(bitmap));
//保存到服务器以及本地xml更新
                saveChangeToServer(avatarUrl);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void saveChangeToServer(String url) {
        String picPath = url;
        LogUtil.MyLog_e(getContext(),"本地图片地址:***"+url+"***");
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(getContext(), new UploadFileListener() {

            @Override
            public void onSuccess() {
                //bmobFile.getFileUrl(context)--返回的上传文件的完整地址
                LogUtil.MyLog_e(getContext(),"上传成功"+bmobFile.getFileUrl(getContext()));
                MySPUtil.save(AppConstants.CONFIG.AVATAR_URL,bmobFile.getFileUrl(getContext()));
            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }

            @Override
            public void onFailure(int code, String msg) {
            }
        });
    }

    private void showChangePic() {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(getResources().getColor(R.color.blue))
                .titleBgColor(getResources().getColor(R.color.blue))
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                .titleTextColor(getResources().getColor(R.color.white))
                // 开启单选   （默认为多选）
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/fav/avatar")
                .build();
        ImageSelector.open(getActivity(), imageConfig);   // 开启图片选择器
    }

    @OnClick(R.id.llcenter_5)
    public void llcenter_5(View v) {
        //地址管理
        intent = new Intent(getContext(), AddressManageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.llcenter_1)
    public void llcenter_1(View v) {
        //订单管理
        intent = new Intent(getContext(), OrderListActivity.class);
        orderTools.allOrder();
        startActivity(intent);
    }
    @OnClick(R.id.llcenter_4)
    public void llcenter_4(View v) {
        Toast.makeText(getContext(),"当前的积分为:" + BmobUser.getCurrentUser(getContext(),User.class).getU_integra()+"",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.llcenter_6)
    public void llcenter_6(View v) {
        if(MySPUtil.getBoolean(AppConstants.CONFIG.OPEN_UPDATE) == true) {//当前已开启
            //版本检测关闭
            MySPUtil.save(AppConstants.CONFIG.OPEN_UPDATE, false);
            llcenter6Text.setText("Closed");
        }else {
            //版本检测开启
            MySPUtil.save(AppConstants.CONFIG.OPEN_UPDATE, true);
            llcenter6Text.setText("Opened");
        }
    }

    @Override
    protected void initData(String content, View successView) {
        EventBus.getDefault().register(this);
        orderTools = OrderTools.getInstance(getContext());
        String picUrl = MySPUtil.getString(AppConstants.CONFIG.AVATAR_URL,".");
        if(".".equals(picUrl)){
            //当前不存在头像缓存
            Picasso.with(getContext()).load(R.mipmap.ic_launcher).into(avatar);
        }else {
            //有缓存时
            Picasso.with(getContext()).load(picUrl).error(R.mipmap.ic_launcher).into(avatar);
        }
        //初始化界面的值
        llcenter6Text.setText(MySPUtil.getBoolean(AppConstants.CONFIG.OPEN_UPDATE,false) == false ? "Closed" : "Opened");
    }

    @Override
    protected void initTitle() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
