package com.opiumfive.gameofballs2;


import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class TestActivity extends SimpleBaseGameActivity {
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
        mCircle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this.getAssets(), "gfx/1.png", 0, 0);
        mBitmapTextureAtlas.load();
    }

    @Override
    protected Scene onCreateScene() {
        mSceneManager = new SceneManager();
        //scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
        vertexBufferObjectManager = getVertexBufferObjectManager();
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
}