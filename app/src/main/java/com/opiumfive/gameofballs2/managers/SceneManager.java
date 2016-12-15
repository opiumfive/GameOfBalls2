package com.opiumfive.gameofballs2.managers;

import android.view.KeyEvent;

import com.opiumfive.gameofballs2.MainGameActivity;
import com.opiumfive.gameofballs2.scenes.MainGameScene;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;



public class SceneManager extends Scene {

    public static MainGameScene mMainGameScene = new MainGameScene();
    public static int state = 0 ;
    public static final int GAME = 0;

    public SceneManager() {
        attachChild(mMainGameScene);
        ShowMainGameScene();
    }

    public static void ShowMainGameScene() {
        mMainGameScene.Show();
        state = GAME;
    }

    /*@Override
    public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
        switch (state) {
            case GAME:
                MainGameActivity.mTestActivity.onSceneTouchEvent(mMainGameScene, pSceneTouchEvent);
                break;
        }
        return super.onSceneTouchEvent(pSceneTouchEvent);
    }

    public void KeyPressed(int keyCode, KeyEvent event) {
        switch (state) {
            case GAME:
                if (keyCode == KeyEvent.KEYCODE_MENU){

                }
                if (keyCode == KeyEvent.KEYCODE_BACK){

                }
                break;
        }
    }*/
}