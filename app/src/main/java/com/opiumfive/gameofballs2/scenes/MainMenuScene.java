package com.opiumfive.gameofballs2.scenes;


import android.opengl.GLES20;

import com.opiumfive.gameofballs2.managers.SceneManager;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSLogger;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    protected static final int MENU_PLAY = 0;
    protected static final int MENU_RATE = 1;
    protected static final int MENU_EXTRAS = 2;
    protected static final int MENU_QUIT = 3;

    protected MenuScene mMenuScene;
    private MenuScene mSubMenuScene;

    @Override
    public void createScene() {

        this.mEngine.registerUpdateHandler(new FPSLogger());
        setBackground(new Background(Color.BLACK));

        Text nameText = new Text(0, 0, mResourceManager.mFont2, "Game of Balls 2", new TextOptions(HorizontalAlign.LEFT), mVertexBufferObjectManager);
        nameText.setPosition((SCREEN_WIDTH - nameText.getWidth())/2f, 65);
        attachChild(nameText);

        mMenuScene = createMenuScene();

        setChildScene(mMenuScene, false, true, true);
    }

    @Override
    public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
        switch(pMenuItem.getID()) {
            case MENU_PLAY:
                mMenuScene.closeMenuScene();
                mSceneManager.setScene(SceneManager.SceneType.SCENE_GAME);
                return true;

            case MENU_RATE:
                //TODO implement
                return true;

            case MENU_EXTRAS:
                pMenuScene.setChildSceneModal(mSubMenuScene);
                return true;

            case MENU_QUIT:
            /* End Activity. */
                mActivity.finish();
                return true;

            default:
                return false;
        }
    }

    protected MenuScene createMenuScene() {
        final MenuScene menuScene = new MenuScene(mCamera);

        IMenuItem playMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_PLAY, mResourceManager.mFont3, "Play", mVertexBufferObjectManager), new Color(1,1,1), new Color(0.0f, 0.2f, 0.4f));
        playMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        menuScene.addMenuItem(playMenuItem);

        IMenuItem rateMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_RATE, mResourceManager.mFont3, "Rate", mVertexBufferObjectManager), new Color(1,1,1), new Color(0.0f, 0.2f, 0.4f));
        rateMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        menuScene.addMenuItem(rateMenuItem);

        IMenuItem extrasMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_EXTRAS, mResourceManager.mFont3, "Extras", mVertexBufferObjectManager), new Color(1,1,1), new Color(0.0f, 0.2f, 0.4f));
        extrasMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        menuScene.addMenuItem(extrasMenuItem);

        IMenuItem quitMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_QUIT, mResourceManager.mFont3, "Quit", mVertexBufferObjectManager), new Color(1,1,1), new Color(0.0f, 0.2f, 0.4f));
        quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        menuScene.addMenuItem(quitMenuItem);

        menuScene.buildAnimations();

        menuScene.setBackgroundEnabled(false);

        menuScene.setOnMenuItemClickListener(this);
        return menuScene;
    }

    protected MenuScene createSubMenuScene() {
        //TODO implement
        return null;
    }

    @Override
    public void onBackKeyPressed() {
        if (mMenuScene.hasChildScene())
            mSubMenuScene.back();
        else
            mActivity.finish();
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene() {
        //TODO
    }
}
