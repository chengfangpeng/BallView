package com.xray.ballview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfp on 16-5-20.
 */
public abstract class AbsBallAnimator {



    protected List<Ball> balls;

    protected BallAnimatorListener ballAnimatorListener;

    public AbsBallAnimator(){
       balls = new ArrayList<>();

    }

    public void addBalls(List<Ball> balls){

        this.balls.addAll(balls);

    }
    public void setBallPathAnimatorListener(BallAnimatorListener ballPathAnimatorListener){

        this.ballAnimatorListener = ballPathAnimatorListener;
    }

    public abstract void start();
    public abstract void stop();
    public abstract void restart();



    public interface BallAnimatorListener{

        void onUpdate();
    }
}
