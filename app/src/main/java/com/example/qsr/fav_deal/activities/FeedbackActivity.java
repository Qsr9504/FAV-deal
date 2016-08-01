package com.example.qsr.fav_deal.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qsr.fav_deal.R;
import com.example.qsr.fav_deal.utils.TextUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FeedbackActivity extends AppCompatActivity {

    @Bind(R.id.t_left)
    ImageView tLeft;
    @Bind(R.id.t_text)
    TextView tText;
    @Bind(R.id.t_right)
    ImageView tRight;
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tRight.setVisibility(View.INVISIBLE);
        tText.setText("用户反馈");
        tLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtil.isEmpty(content.getText().toString())) {
                    Toast.makeText(FeedbackActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    sendToServer();
                }
            }
        });
    }

    private void back() {
        finish();
    }

    /**
     * 发送反馈到服务器
     */
    private void sendToServer() {
        //上传到服务器
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("feedback", content.getText().toString());
        client.post("", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if ("OK".equals(content)) {
                    Toast.makeText(FeedbackActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                    //跳转回主界面
                    back();
                }

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                //跳转回主界面
                Toast.makeText(FeedbackActivity.this, "反馈失败", Toast.LENGTH_SHORT).show();
                back();
            }
        });

    }
}
