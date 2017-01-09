package com.opiumfive.gameofballs2.managers;


import android.graphics.Color;
import android.graphics.Typeface;

import com.opiumfive.gameofballs2.GameActivity;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

public class ResourceManager {
    private static final ResourceManager INSTANCE = new ResourceManager();
    public GameActivity mActivity;
    private BitmapTextureAtlas mSplashTextureAtlas;
    public ITextureRegion mSplashTextureRegion;
    private BitmapTextureAtlas mBitmapTextureAtlas;
    public ITextureRegion mBall;
    public ITextureRegion mParticle;
    public Font mFont1;
    public Font mFont2;
    public Font mFont3;


    private ResourceManager() {}

    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    public void prepare(GameActivity activity) {
        INSTANCE.mActivity = activity;
    }

    public void loadSplashResources() {
        mFont1 = FontFactory.create(mActivity.getFontManager(), mActivity.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 16, Color.WHITE);
        mFont1.load();

        FontFactory.setAssetBasePath("fnt/");
        ITexture fontTexture2 = new BitmapTextureAtlas(mActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        mFont2 = FontFactory.createStrokeFromAsset(mActivity.getFontManager(), fontTexture2, mActivity.getAssets(), "font2.otf", 66, true, Color.BLACK, 2, Color.YELLOW);
        mFont2.load();

        ITexture fontTexture3 = new BitmapTextureAtlas(mActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        mFont3 = FontFactory.createFromAsset(mActivity.getFontManager(), fontTexture3, mActivity.getAssets(), "font2.otf", 46, true, Color.WHITE);
        mFont3.load();

        //BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/splash/");
        //mSplashTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
        //mSplashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mSplashTextureAtlas, mActivity, "logo.png", 0, 0);
        //mSplashTextureAtlas.load();
    }

    public void unloadSplashResources() {
        //mFont2.unload();
        //mFont2 = null;
        //mFont3.unload();
        //mFont3 = null;
        //mSplashTextureAtlas.unload();
        //mSplashTextureRegion = null;
    }

    public void loadGameResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        mBitmapTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        mBall = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, mActivity, "2.png", 0, 0);
        mParticle = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, mActivity, "exp.png", 0, 200);
        mBitmapTextureAtlas.load();
    }

    public void unloadGameResources() {
        mBitmapTextureAtlas.unload();
        mBall = null;
        mParticle = null;
    }
}
