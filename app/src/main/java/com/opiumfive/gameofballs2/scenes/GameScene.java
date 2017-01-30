package com.opiumfive.gameofballs2.scenes;


import com.opiumfive.gameofballs2.managers.SceneManager;
import com.opiumfive.gameofballs2.units.Ball;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
import org.andengine.util.math.MathUtils;


public class GameScene extends BaseScene implements IOnSceneTouchListener, IOnAreaTouchListener {

    int counter = 0;
    int lastball = 0;
    int gameTime = 0;
    float d = 0.04f;
    public int thresh = 10;
    float lastSec = 0.0f;
    float secElapsed = 0.0f;

    @Override
    public void createScene() {
        mEngine.registerUpdateHandler(new FPSLogger());

        setOnSceneTouchListener(this);
        setOnAreaTouchListener(this);
        setTouchAreaBindingOnActionDownEnabled(true);

        setBackgroundEnabled(true);
        setBackground(new Background(new Color(0.22f, 0.22f, 0.22f, 1f)));



        registerUpdateHandler(new IUpdateHandler() {

            @Override
            public void reset() {}

            @Override
            public void onUpdate(float pSecondsElapsed) {

                secElapsed+=pSecondsElapsed;
                if (secElapsed >= 0.25f) {
                    secElapsed = 0.0f;
                    counter++;
                    if (counter == thresh+1) counter =0;

                    gameTime++;
                    if ((MathUtils.RANDOM.nextFloat() < d)||(gameTime-lastball>4)) {
                        addBall();
                        lastball=gameTime;
                    }
                    if (d<1) {
                        d=d+0.001f;
                    }
                }


            }
        });

    }

    public void addBall() {
        float width = mResourceManager.mBall.getWidth()/2.0f;
        float height = mResourceManager.mBall.getHeight()/2.0f;
        float postX = width +(SCREEN_WIDTH - width*2.0f)* MathUtils.RANDOM.nextFloat();
        float postY = height+(SCREEN_HEIGHT - height*2.0f)*MathUtils.RANDOM.nextFloat();
        Ball ball = new Ball(postX, postY, mResourceManager.mBall, mVertexBufferObjectManager);
        registerTouchArea(ball);
        attachChild(ball);
    }

    public void remove(final Ball ball) {
        unregisterTouchArea(ball);
        ball.prepareRemove();
        detachChild(ball);
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionDown()) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackKeyPressed() {
        mSceneManager.setScene(SceneManager.SceneType.SCENE_MENU);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        if (pSceneTouchEvent.isActionDown()) {
            remove((Ball) pTouchArea);
        }
        return false;
    }
}
