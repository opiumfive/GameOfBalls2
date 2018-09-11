package com.opiumfive.gameofballs2.scenes;


import com.opiumfive.gameofballs2.managers.SceneManager;
import com.opiumfive.gameofballs2.units.Ball;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.particle.ParticleSystem;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ScaleParticleModifier;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;
import org.andengine.util.math.MathUtils;

import java.util.Random;


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
            public void reset() {
            }

            @Override
            public void onUpdate(float pSecondsElapsed) {

                secElapsed += pSecondsElapsed;

                if (secElapsed >= 0.25f) {
                    secElapsed = 0.0f;
                    counter++;
                    if (counter == thresh + 1) counter = 0;

                    gameTime++;
                    if ((MathUtils.RANDOM.nextFloat() < d) || (gameTime - lastball > 4)) {
                        addBall();
                        lastball = gameTime;
                    }

                    if (d < 1) {
                        d = d + 0.001f;
                    }
                }
            }
        });

    }

    public void addBall() {
        float width = mResourceManager.mBall.getWidth() / 2.0f;
        float height = mResourceManager.mBall.getHeight() / 2.0f;
        float postX = width + (SCREEN_WIDTH - width * 2.0f) * MathUtils.RANDOM.nextFloat();
        float postY = height + (SCREEN_HEIGHT - height * 2.0f) * MathUtils.RANDOM.nextFloat();
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
            Ball ball = (Ball) pTouchArea;
            makeExplosion(ball.getX() + ball.getWidth() / 2, ball.getY() + ball.getHeight() / 2, 0.2f + new Random().nextFloat() / 0.8f);
            remove((Ball) pTouchArea);
        }
        return false;
    }

    private void makeExplosion(float posX, float posY, float strength) {
        PointParticleEmitter particleEmitter = new PointParticleEmitter(posX, posY);

        final ParticleSystem particleSystem = new SpriteParticleSystem(particleEmitter, 100, 100, 25,
                mResourceManager.mParticle, mVertexBufferObjectManager);

        particleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>( - 150 * strength, 150 * strength, - 150 * strength, 150 * strength));
        particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0f, 1f, 1f, 0f));
        particleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(0f, 1f, 1.5f, 0.8f));

        attachChild(particleSystem);
        registerUpdateHandler(new TimerHandler(1.0f, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler) {
                particleSystem.setIgnoreUpdate(true);
                particleSystem.detachSelf();
                GameScene.this.sortChildren();
                GameScene.this.unregisterUpdateHandler(pTimerHandler);
            }
        }));
    }
}
