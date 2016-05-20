package com.xray.ballview;

import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.BaseInterpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //自定义插值器
    class MyInterpolator implements TimeInterpolator{


        @Override
        public float getInterpolation(float input) {
            //自定义的规则
            return 0;
        }
    }

    //自定义TypeEvaluator
    class MyTypeEvaluator implements TypeEvaluator<Ball>{


        @Override
        public Ball evaluate(float fraction, Ball startValue, Ball endValue) {
            //自己的规则
            return null;
        }
    }
}
