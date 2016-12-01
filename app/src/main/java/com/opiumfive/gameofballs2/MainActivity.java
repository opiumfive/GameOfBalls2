package com.opiumfive.gameofballs2;

import android.graphics.Typeface;
import android.view.KeyEvent;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;

import java.io.IOException;


public class MainActivity extends BaseGameActivity implements IOnAreaTouchListener, IOnSceneTouchListener {
    public static MainActivity _main;
    public static MainScene _MainScene;
    public static Camera mCamera;
    public boolean gameStarted = false;
    public int chosenGame = 0;
    public int counter = 0;
    public int thresh = 10;
    public boolean musicState = true;
    public boolean soundState = true;

    public Sprite pausedSprite;

    public boolean countTime = false;
    public boolean countTime2 = false;

    public BitmapTextureAtlas mBitmapTextureAtlas;
    public BitmapTextureAtlas mBitmapTextureAtlas2;
    public BitmapTextureAtlas mBitmapTextureAtlas3;
    public BitmapTextureAtlas mBitmapTextureAtlas5;
    public BuildableBitmapTextureAtlas mBitmapTextureAtlas4;
    public ITextureRegion mCircle;
    public ITextureRegion mCircle2;
    public ITextureRegion mMenu1;
    public ITextureRegion mMenu2;
    public ITextureRegion mMenu3;
    public ITextureRegion mMenu4;
    public ITextureRegion mSplash;
    public ITiledTextureRegion mMBut;
    public ITiledTextureRegion mSBut;
    public ITextureRegion mExp;
    public ITextureRegion mBg;
    public ITextureRegion mBadge;
    public ITextureRegion mPaused;
    public Sprite mSprite;
    public Sprite mSprite2;

    public Font mFont;
    public Font mFont2;
    public Font mFont3;

    public Text scoreText;
    public Text missedText;
    public Text apmText;
    public Text streakText;

    public Text scoreText2;
    public Text missedText2;
    public boolean GameLoaded = false;
    public boolean first = true;
    public boolean first2 = true;
    public int lev = 1;
    public boolean p = false;
    int score2 = 0;
    int missed2 = 0;
    int score = 0;
    int missed = 0;
    int taps = 0;
    int gameTime = 0;
    int gameTime2 = 0;
    int k = 0;
    int streak = 0;
    int streak2 = 0;
    int targets = 0;
    float d = 0.04f;
    float d2 = 0.01f;
    int kk = 1;
    Music mMusic;
    Sound mSound1;
    Sound mSound2;

    @Override
    public Engine onCreateEngine(final EngineOptions pEngineOptions) {
        return new FixedStepEngine(pEngineOptions, 60);
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        _main = this;
        mCamera = new Camera(0, 0, 480, 320);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(480, 320), mCamera);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        engineOptions.getAudioOptions().setNeedsMusic(true);
        engineOptions.getAudioOptions().setNeedsSound(true);
        return engineOptions;
    }

    @Override
    public void onCreateResources(
            OnCreateResourcesCallback pOnCreateResourcesCallback) {
        mBitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(), 512, 512);
        mBitmapTextureAtlas2 = new BitmapTextureAtlas(mEngine.getTextureManager(), 512, 512);
        mBitmapTextureAtlas3 = new BitmapTextureAtlas(mEngine.getTextureManager(), 512, 512);
        mBitmapTextureAtlas4 = new BuildableBitmapTextureAtlas(getTextureManager(), 256, 256);
        mBitmapTextureAtlas5 = new BitmapTextureAtlas(mEngine.getTextureManager(), 512, 512);
        mCircle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this.getAssets(), "gfx/1.png", 0, 0);
        mCircle2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this.getAssets(), "gfx/ball1.png", 200, 200);
        mMenu1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas3, this.getAssets(), "gfx/menu1.png", 0, 0);
        mMenu2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas3, this.getAssets(), "gfx/menu2.png", 205, 205);
        mMenu3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this.getAssets(), "gfx/menu_opts.png", 0, 203);
        mMenu4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this.getAssets(), "gfx/menu_info.png", 101, 0);
        mExp = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this.getAssets(), "gfx/exp.png", 302, 302);
        mBg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas2, this.getAssets(), "gfx/bg.png", 0, 0);
        mBadge = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas5, this.getAssets(), "gfx/badge.png", 0, 200);
        mPaused = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas2, this.getAssets(), "gfx/paused.png", 0, 400);
        mSplash = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas5, this.getAssets(), "gfx/splash.png", 0, 0);
        mMBut = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas4, this.getAssets(), "gfx/music.png", 2, 1);
        mSBut = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas4, this.getAssets(), "gfx/sound.png", 2, 1);
        mBitmapTextureAtlas.load();
        mBitmapTextureAtlas2.load();
        mBitmapTextureAtlas3.load();
        try {
            mBitmapTextureAtlas4.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
        } catch (Exception e) {
        }
        mBitmapTextureAtlas4.load();
        mBitmapTextureAtlas5.load();
        this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, TextureOptions.BILINEAR, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 20, false, Color.ABGR_PACKED_RED_CLEAR);
        this.mFont.load();
        this.mFont2 = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, TextureOptions.BILINEAR, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 40, false, Color.ABGR_PACKED_RED_CLEAR);
        this.mFont2.load();
        this.mFont3 = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, TextureOptions.BILINEAR, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 42, false, Color.ARGB_PACKED_RED_CLEAR);
        this.mFont3.load();
        SoundFactory.setAssetBasePath("sfx/");
        MusicFactory.setAssetBasePath("sfx/");
        try {
            mSound1 = SoundFactory.createSoundFromAsset(getSoundManager(), this, "1.mp3");
            mSound2 = SoundFactory.createSoundFromAsset(getSoundManager(), this, "2.mp3");
            mMusic = MusicFactory.createMusicFromAsset(getMusicManager(), this, "music.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {

        final float centerX = (480 - this.mPaused.getWidth()) / 2;
        final float centerY = (320 - this.mPaused.getHeight()) / 2 + 40;
        pausedSprite = new Sprite(centerX, centerY, this.mPaused, this.getVertexBufferObjectManager());

		/* Makes the paused Game look through. */

        _MainScene = new MainScene();
        GameLoaded = true;

        mMusic.setLooping(true);
        pOnCreateSceneCallback.onCreateSceneFinished(_MainScene);
    }

    @Override
    public void onPopulateScene(Scene pScene,
                                OnPopulateSceneCallback pOnPopulateSceneCallback) {

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
                                 ITouchArea pTouchArea, float pTouchAreaLocalX,
                                 float pTouchAreaLocalY) {
        if (pSceneTouchEvent.isActionDown()) {
            switch (MainScene.state) {
                case MainScene.SPLASH:

                    break;
                case MainScene.MENU:


                    break;
                case MainScene.INFO:

                    break;
                case MainScene.GAME:
                    if (soundState) mSound1.play();
                    MainActivity._main.taps++;
                    targets++;
                    if ((Sprite) pTouchArea == MainScene._GameScene.sprite2) {
                        GameScene.remove((Sprite) pTouchArea);
                        MainActivity._main.score += 10;
                        streak++;
                        if (streak > 100) {
                            MainActivity._main.score += 10;
                        } else if (streak > 50) {
                            MainActivity._main.score += 5;
                        } else if (streak > 10) {
                            MainActivity._main.score += 2;
                        } else if (streak > 3) {
                            MainActivity._main.score++;
                        }

                        scoreText.setText("Score: " + MainActivity._main.score);
                    } else {
                        GameScene.remove((Sprite) pTouchArea);
                        MainActivity._main.k--;
                        MainActivity._main.score++;
                        streak++;
                        if (streak > 100) {
                            MainActivity._main.score += 10;
                        } else if (streak > 50) {
                            MainActivity._main.score += 5;
                        } else if (streak > 10) {
                            MainActivity._main.score += 2;
                        } else if (streak > 3) {
                            MainActivity._main.score++;
                        }
                        scoreText.setText("Score: " + MainActivity._main.score);
                    }
                    break;
                case MainScene.GAME2:
                    if (soundState) mSound1.play();
                    MainActivity._main.taps++;
                    targets++;
                    GameScene2.remove((Sprite) pTouchArea);
                    MainActivity._main.k--;
                    MainActivity._main.score2++;
                    streak2++;
                    if (streak2 > 100) {
                        MainActivity._main.score2 += 10;
                    } else if (streak2 > 50) {
                        MainActivity._main.score2 += 5;
                    } else if (streak2 > 10) {
                        MainActivity._main.score2 += 2;
                    } else if (streak2 > 3) {
                        MainActivity._main.score2++;
                    }
                    scoreText2.setText("Score: " + MainActivity._main.score2);
                    break;
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionDown()) {
            switch (MainScene.state) {
                case MainScene.SPLASH:

                    break;
                case MainScene.MENU:


                    break;
                case MainScene.INFO:
                    MainScene.ShowMenuScene();
                    break;
                case MainScene.GAME:
                    if (soundState) mSound2.play();
                    MainActivity._main.taps++;
                    MainActivity._main.missed++;
                    MainActivity._main.streak = 0;
                    missedText.setText("Missed: " + MainActivity._main.missed);
                    if (MainActivity._main.missed == 10) {
                        kk = 1;
                        MainActivity._main.mMusic.pause();
                        MainScene._ResultScene.setTaps(taps, missed, targets, gameTime, score, 0.25f);
                        MainScene._InfoScene.Save_score();
                        MainScene.ShowResultScene();

                    }

                    break;
                case MainScene.GAME2:
                    if (soundState) mSound2.play();
                    MainActivity._main.taps++;
                    MainActivity._main.missed2++;
                    MainActivity._main.streak2 = 0;
                    missedText2.setText("Missed: " + MainActivity._main.missed2);
                    if (MainActivity._main.missed2 == 10) {
                        kk = 2;
                        MainActivity._main.mMusic.pause();
                        MainScene._ResultScene.setTaps(taps, missed2, targets, gameTime2, score2, 0.3f);
                        MainScene._InfoScene.Save_score();
                        MainScene.ShowResultScene();

                    }
                    break;
                case MainScene.RESULT:
                    //MainScene.ShowMenuScene();
                    break;

            }

            return true;
        }
        return false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onPause() {

        super.onPause();
        p = true;
        if (this.isGameLoaded() && this.mEngine.isRunning()) {

            this.mEngine.stop();

        }
    }

    @Override
    protected synchronized void onResume() {

        super.onResume();
        System.gc();

        p = false;
        if (!this.isGameLoaded()) {
            this.mEngine.start();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!GameLoaded) return true;
            if (_MainScene != null && GameLoaded) {
                _MainScene.KeyPressed(keyCode, event);
                return true;
            }
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (!GameLoaded) return true;
            if (_MainScene != null && GameLoaded) {
                _MainScene.KeyPressed(keyCode, event);
                return true;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void refreshGame1() {
        lev = 1;

        score2 = 0;
        missed2 = 0;
        score = 0;
        missed = 0;
        taps = 0;
        gameTime = 0;
        gameTime2 = 0;
        k = 0;
        streak = 0;
        streak2 = 0;
        targets = 0;
        d = 0.09f;
        d2 = 0.01f;
        kk = 1;
        MainActivity._main._MainScene._GameScene.lastball = 0;
        MainActivity._main._MainScene._GameScene.lastball2 = 0;
    }


}
