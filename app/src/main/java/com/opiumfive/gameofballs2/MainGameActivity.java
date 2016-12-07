package com.opiumfive.gameofballs2;


import com.opiumfive.gameofballs2.managers.SceneManager;
import com.opiumfive.gameofballs2.scenes.MainGameScene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class MainGameActivity extends SimpleBaseGameActivity implements IOnAreaTouchListener, IOnSceneTouchListener {

    public static MainGameActivity mTestActivity;
    public static Camera camera;
    public static SceneManager mSceneManager;
    public static final int CAMERA_WIDTH = 800;
    public static final int CAMERA_HEIGHT = 480;
    public static VertexBufferObjectManager vertexBufferObjectManager;

    public BitmapTextureAtlas mBitmapTextureAtlas;
    public static ITextureRegion mCircle;

    @Override
    protected void onCreateResources() {
        mBitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(), 512, 512);
        mCircle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this.getAssets(), "gfx/2.png", 0, 0);
        mBitmapTextureAtlas.load();
    }

    @Override
    protected Scene onCreateScene() {
        mSceneManager = new SceneManager();
        vertexBufferObjectManager = getVertexBufferObjectManager();
        mTestActivity = this;
        return mSceneManager;
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
        //EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(480, 320), camera);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

        return engineOptions;
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionDown()) {
            switch (SceneManager.state) {
                case SceneManager.GAME:
                    // промахи
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        if (pSceneTouchEvent.isActionDown()) {
            switch (SceneManager.state) {
                case SceneManager.GAME:
                        MainGameScene.remove((Sprite) pTouchArea);
                        break;
            }
            return true;
        }
        return false;
    }
}