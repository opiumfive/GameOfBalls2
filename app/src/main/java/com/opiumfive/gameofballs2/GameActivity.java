package com.opiumfive.gameofballs2;

import android.os.Bundle;
import android.view.View;

import com.opiumfive.gameofballs2.managers.ResourceManager;
import com.opiumfive.gameofballs2.managers.SceneManager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;


public class GameActivity extends SimpleBaseGameActivity {

    public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;

    private Camera mCamera;
    private ResourceManager mResourceManager;
    private SceneManager mSceneManager;

    @Override
    public EngineOptions onCreateEngineOptions() {

        mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), mCamera);
        //engineOptions.getAudioOptions().setNeedsSound(true).setNeedsMusic(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }

    @Override
    protected void onCreateResources() {
        mResourceManager = ResourceManager.getInstance();
        mResourceManager.prepare(this);
        mResourceManager.loadSplashResources();
        mSceneManager = SceneManager.getInstance();
    }

    @Override
    protected Scene onCreateScene() {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                mResourceManager.loadGameResources();
                mSceneManager.setScene(SceneManager.SceneType.SCENE_MENU);
                mResourceManager.unloadSplashResources();
            }
        }));

        return mSceneManager.createSplashScene();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //System.exit(0);
    }

    @Override
    public void onBackPressed() {
        if (mSceneManager.getCurrentScene() != null) {
            mSceneManager.getCurrentScene().onBackKeyPressed();
            return;
        }
        super.onBackPressed();
    }
}