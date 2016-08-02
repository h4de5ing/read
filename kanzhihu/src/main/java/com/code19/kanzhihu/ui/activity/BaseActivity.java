package com.code19.kanzhihu.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.code19.kanzhihu.utils.BaseAppManager;

/**
 * Created by Administrator on 2016/8/2.
 * <p>
 * 编程规范
 * 1.源文件编码为UTF-8
 * 2.java代码中不要出现中文,除非注释
 * 3.服务端可以实现的,就不要放到客户端
 * 4.应用第三方库要慎重,避免映入大容量的第三方库,第三方尽量通过compile,aar,jar应用
 * 5.处理全局异常和错误,将错误发送到服务器端
 * 6.图片.9处理
 * 7.使用静态变量方式实现界面间共享要慎重
 * 8.单元测试(逻辑测试,界面测试)
 * 9.不要重用父类的Handler,也不应该让子类用到,否则message.what 冲突
 * 10.activity中在一个View.onClickListener处理所有逻辑
 * 11.String.xml 使用%1$s实现字符串通配
 * 12.数据要校验类型
 * 13.未完成方法是用TODO标记
 * 14.功能完成,存在效率问题，采用TOReWrite标记
 * 15.代码存在问题或仅仅用于调试TOFixMe标记
 * 16.不随意更改Value目录下文件的名称
 */

public class BaseActivity extends AppCompatActivity {
    private Context mContext;
    private static String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        TAG = this.getClass().getSimpleName();
        BaseAppManager.getInstance().addActivity(this);
    }
}
