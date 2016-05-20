package com.xray.ballview;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.BaseInterpolator;

/**
 * Created by cfp on 16-5-20.
 */
public class BallPathAnimator extends AbsBallAnimator implements
        ValueAnimator.AnimatorUpdateListener {

    private BaseInterpolator interpolator;
    private Path path;
    private int duration;
    private ValueAnimator valueAnimator;
    private float offset;


    public BallPathAnimator(BaseInterpolator interpolator, Path path, int duration) {

        this.interpolator = interpolator;
        this.path = path;
        this.duration = duration;
        init();
    }

    public BallPathAnimator( Path path, int duration) {

        this.path = path;
        this.duration = duration;
        init();
    }
    private void init() {
        initValueAnimation();
    }

    private void initValueAnimation() {

        valueAnimator = new ValueAnimator();
        valueAnimator.setRepeatMode(ValueAnimator.INFINITE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(duration);
        valueAnimator.setFloatValues(0.0f, 1f);
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.addUpdateListener(this);
    }


    @Override
    public void start() {
        float size = balls.size();
        offset = 1 / size;
        valueAnimator.start();
    }

    @Override
    public void stop() {
        valueAnimator.cancel();
    }

    @Override
    public void restart() {
        stop();
        start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float value = animation.getAnimatedFraction();
        for (int i = 0; i < balls.size(); i++) {

            updateBall(balls.get(i), value, i, path);
        }

        if (ballAnimatorListener != null) {
            ballAnimatorListener.onUpdate();
        }

    }

    private void updateBall(Ball ball, float fraction, int position, Path path) {
        float ballFraction = fraction + position * offset;

        if (ballFraction > 1) {

            ballFraction = ballFraction - 1;
        }
        float[] coordinates = getPathCoordinates(path, ballFraction);
        ball.setPosition((int) coordinates[0], (int) coordinates[1]);


    }

    private float[] getPathCoordinates(Path path, float fraction) {

        float aCoordinates[] = {0f, 0f};
        PathMeasure pm = new PathMeasure(path, false);
        pm.getPosTan(pm.getLength() * fraction, aCoordinates, null);
        return aCoordinates;

    }


}
