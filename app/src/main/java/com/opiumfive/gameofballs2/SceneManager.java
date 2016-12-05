package com.opiumfive.gameofballs2;

import android.view.KeyEvent;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;



public class SceneManager extends Scene {

    public static MainGameScene _GameScene = new MainGameScene();
    public static int state = 0 ;
    public static final int GAME = 0;

    public SceneManager() {
        attachChild(_GameScene);
        ShowGameScene();
    }

    public static void ShowGameScene() {
        _GameScene.Show();
        state = GAME;
    }

    @Override
    public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
        switch (state) {
            case GAME:
                _GameScene.onSceneTouchEvent(pSceneTouchEvent);
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
    }

}