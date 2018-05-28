package com.example.administrator.weishi.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.weishi.BaseActivity;
import com.example.administrator.weishi.R;
import com.example.administrator.weishi.TopView;
import com.example.administrator.weishi.net.RestClient;
import com.example.administrator.weishi.utils.ToastUtils;
import com.example.administrator.weishi.utils.UrlUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class LoginActivity extends BaseActivity {

    private Button btn_reg;
    private ImageView img_logo;
    private EditText et_username;
    private EditText et_password;
    private TextView tv_find;
    private Button btn_login;

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected BaseActivity getAct() {
        return this;
    }

    @Override
    protected TopView getTopViews() {
        return null;
    }

    @Override
    protected void findViewById() {
        btn_reg = findViewById(R.id.btn_reg);
        img_logo = findViewById(R.id.img_logo);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        tv_find = findViewById(R.id.tv_find);
        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    protected void setListener() {
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void onClickEvent(View paramView) {
        switch (paramView.getId()){
            case R.id.btn_login:
                login();
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (username.length() != 11){
            ToastUtils.show(mCurrentAct,"请输入正确的11位手机号");
            return;
        }
        if (password.length() == 0){
            ToastUtils.show(mCurrentAct,"密码不能为空");
            return;
        }
        RequestParams params = new RequestParams();
        params.add("mobile",username);
        params.add("passwoed",password);
        RestClient.post(UrlUtils.login(), params, mCurrentAct, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d("登录", result);
                try {
                    JSONObject data = new JSONObject(result);
                    int code = data.getInt("code");
                    if (code == 200){
                        ToastUtils.show(LoginActivity.this, "登录成功");
                    }else {
                        ToastUtils.show(LoginActivity.this, "登录失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}
