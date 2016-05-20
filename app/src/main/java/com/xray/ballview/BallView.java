package com.xray.ballview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfp on 16-5-20.
 */
public class BallView extends View {

    private int ballCount = 3;
    private int[] ballColor = {Color.RED, Color.BLUE, Color.GREEN};
    private int duration = 3000;
    private List<Ball> balls;
    private BallPathAnimator ballPathAnimator;


    public BallView(Context context) {
        super(context);
        init();
    }


    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        createBalls();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Path path = new Path();
//        path.lineTo(500, 500);
        path.addCircle(500, 500, 200, Path.Direction.CCW);
        initBallPathAnimator(path);
        start();
    }

    private void initBallPathAnimator(Path path) {
        ballPathAnimator = new BallPathAnimator( path, duration);
        ballPathAnimator.setBallPathAnimatorListener(new AnimatorListener());
        ballPathAnimator.addBalls(balls);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < ballCount; i++) {

            Ball ball = balls.get(i);
            ball.render(canvas);

        }

    }

    private void createBalls() {

        balls = new ArrayList<>();
        for (int i = 0; i < ballCount; i++) {
            balls.add(new Ball(20, ballColor[i]));
        }
    }

    private class AnimatorListener implements AbsBallAnimator.BallAnimatorListener {

        @Override
        public void onUpdate() {
            invalidate();
        }
    }

    public void start() {
        ballPathAnimator.start();
    }
}
