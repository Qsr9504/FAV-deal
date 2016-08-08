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
import com.example.qsr.fav_deal.bmobUtil.bean.Feedback;
import com.example.qsr.fav_deal.utils.TextUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.SaveListener;

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
        Feedback feedback = new Feedback();
        feedback.setFb_content(content.getText().toString());
        feedback.save(FeedbackActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(FeedbackActivity.this,"谢谢您的反馈！",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(FeedbackActivity.this,"反馈失败！" + s,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
