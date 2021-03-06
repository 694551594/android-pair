package cn.yhq.pair.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.yhq.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onConfig(Config config) {
        super.onConfig(config);
        config.setSwipeBackWrapper(false);
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        Button btn1 = this.getView(R.id.button);
        Button btn2 = this.getView(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecyclerActivity.class);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PairLayoutActivity.class);
            }
        });
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }
}
