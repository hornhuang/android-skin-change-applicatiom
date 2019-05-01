package com.example.android_skinpeeler_application;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android_skinpeeler_application.activities.BaseActivity;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.app.SkinMaterialViewInflater;
import skin.support.widget.SkinCompatSupportable;

public class MainActivity extends BaseActivity implements SkinCompatSupportable {
    private boolean flag = true;
    private TextView mTextMessage;
    private BottomNavigationView navView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        SkinCompatManager.withoutActivity(getApplication())                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();

        mTextMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag){
                    SkinCompatManager.getInstance().loadSkin("red.skin");
                    flag = !flag;
                }else {
                    SkinCompatManager.getInstance().loadSkin("blue.skin");
                    flag = !flag;
                }
            }
        });
        SkinCompatManager.getInstance().loadSkin("red.skin");
// 指定皮肤插件
//        SkinCompatManager.getInstance().loadSkin("new.skin"[, SkinLoaderListener], int strategy);
//        SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_PREFIX_BUILD_IN); // 前缀加载
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);// 恢复应用默认皮肤
//        SkinCompatManager.getInstance().restoreDefaultTheme();
        // 指定皮肤插件, 并且监听加载状态
    }

    @Override
    public void applySkin() {
        mTextMessage.setBackgroundDrawable(SkinCompatResources.getInstance().getDrawable(R.drawable.textview_bac));
        navView.setBackgroundDrawable(SkinCompatResources.getInstance().getDrawable(R.drawable.bottom_navigation_view_bac));
    }

//    private void changeSkinApk(final String highZhuTiName, String themeId) {
//        rl_use_zhuti.setEnabled(false);
//        if (highZhuTiName == null)
//            return;
//        if ("default".equals(highZhuTiName)) {
//            skinCompatManager.restoreDefaultTheme();
//            spUtils.saveMyZhuTi(themeBeanId);
//            tv_use_zhuti.setBackgroundResource(R.drawable.layer_zhuti_preview_press_tv);
//            tv_use_zhuti.setText(getResources().getString(R.string.wo_have_use));
//            tv_use_zhuti.setTextColor(ContextCompat.getColor(mActivity, R.color.preview_b_press_ft));
//            rl_use_zhuti.setEnabled(false);
//
//        } else {
//            loadDialog.show();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    skinCompatManager.loadSkin(highZhuTiName, new SkinCompatManager.SkinLoaderListener() {
//                        @Override
//                        public void onStart() {
//                        }
//
//                        @Override
//                        public void onSuccess() {
//                            loadDialog.dismiss();
//                            spUtils.saveMyZhuTi(themeBeanId);
//                            tv_use_zhuti.setBackgroundResource(R.drawable.layer_zhuti_preview_press_tv);
//                            tv_use_zhuti.setText(getResources().getString(R.string.wo_have_use));
//                            tv_use_zhuti.setTextColor(ContextCompat.getColor(mActivity, R.color.preview_b_press_ft));
//
//                        }
//
//                        @Override
//                        public void onFailed(String s) {
//                            loadDialog.dismiss();
//                            rl_use_zhuti.setEnabled(true);
//                        }
//                    });
//                }
//            }, 700);
//
//        }
//    }
}
