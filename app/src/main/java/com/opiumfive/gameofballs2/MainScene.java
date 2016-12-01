package com.opiumfive.gameofballs2;

import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import android.view.KeyEvent;

public class MainScene extends Scene {
	
	public static MenuScene _MenuScene = new MenuScene();
	public static SplashScene _SplashScene = new SplashScene();
	public static InfoScene _InfoScene = new InfoScene();
	public static GameScene _GameScene = new GameScene();
	public static GameScene2 _GameScene2 = new GameScene2();
	public static ResultScene _ResultScene = new ResultScene();
	public static int state = 0 ;
	public static final int SPLASH = 0;
	public static final int MENU = 1;
	public static final int INFO = 2;
	public static final int GAME = 3;
	public static final int GAME2 = 4;
	public static final int RESULT = 5;
	
	public MainScene() {
		attachChild(_MenuScene);
		attachChild(_SplashScene);
		attachChild(_InfoScene);
		attachChild(_GameScene);
		attachChild(_GameScene2);
		attachChild(_ResultScene);
		ShowSplashScene();
	}
	
	public static void ShowSplashScene() {
		_SplashScene.Show();
		_MenuScene.Hide();
		_InfoScene.Hide();
		_GameScene.Hide();
		_GameScene2.Hide();
		_ResultScene.Hide();
		state = SPLASH;
	}
	
	public static void ShowMenuScene() {
		_SplashScene.Hide();
		_MenuScene.Show();
		_InfoScene.Hide();
		_GameScene.Hide();
		_GameScene2.Hide();
		_ResultScene.Hide();
		state = MENU;
	}
	
	public static void ShowInfoScene() {
		_SplashScene.Hide();
		_MenuScene.Hide();
		_InfoScene.Show();
		_GameScene.Hide();
		_GameScene2.Hide();
		_ResultScene.Hide();
		state = INFO;
	}
	
	public static void ShowGameScene() {
		_SplashScene.Hide();
		_MenuScene.Hide();
		_InfoScene.Hide();
		_GameScene.Show();
		_GameScene2.Hide();
		_ResultScene.Hide();
		state = GAME;
	}
	
	public static void ShowGameScene2() {
		_SplashScene.Hide();
		_MenuScene.Hide();
		_InfoScene.Hide();
		_GameScene.Hide();
		_GameScene2.Show();
		_ResultScene.Hide();
		state = GAME2;
	}
	
	public static void ShowResultScene() {
		_SplashScene.Hide();
		_MenuScene.Hide();
		_InfoScene.Hide();
		_GameScene.Hide();
		_GameScene2.Hide();
		_ResultScene.Show();
		state = RESULT;
	}
	
	@Override
	public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
		switch (state) {
		case SPLASH:
			_SplashScene.onSceneTouchEvent(pSceneTouchEvent);
			break;
		case MENU:
			_MenuScene.onSceneTouchEvent(pSceneTouchEvent);
			
			break;
		case INFO:
			_InfoScene.onSceneTouchEvent(pSceneTouchEvent);
			break;
		case GAME:
			_GameScene.onSceneTouchEvent(pSceneTouchEvent);
			break;
		case GAME2:
			_GameScene2.onSceneTouchEvent(pSceneTouchEvent);
			break;
		case RESULT:
			_ResultScene.onSceneTouchEvent(pSceneTouchEvent);
			break;
		}
		return super.onSceneTouchEvent(pSceneTouchEvent);
	}

	public void KeyPressed(int keyCode, KeyEvent event) {
		switch (state) {
		case SPLASH:
			
				MainActivity._main.onDestroy();
			break;
		case MENU:
				MainActivity._main.onDestroy();
			break;
		case INFO:
			if (keyCode == KeyEvent.KEYCODE_BACK){
				
				MainScene.ShowMenuScene();
			}	
			break;
		case GAME:
			if (keyCode == KeyEvent.KEYCODE_MENU){
				if (MainActivity._main.p) {
				//	MainActivity._main._MainScene._GameScene.clearChildScene();
				//	MainActivity._main.onResume();
					} else {
						
					//	MainActivity._main.onPause();
					//	MainActivity._main._MainScene._GameScene.setChildScene(MainActivity._main.mPauseScene, false, true, true);
					}
			}
			if (keyCode == KeyEvent.KEYCODE_BACK){
				System.gc();
				MainActivity._main.countTime=false;
				MainActivity._main.pausedSprite.setVisible(true);
				if(MainActivity._main.mMusic != null && MainActivity._main.mMusic.isPlaying()){
					MainActivity._main.mMusic.pause();
					}
				MainScene.ShowMenuScene();
			}	
			break;
		case GAME2:
			if (keyCode == KeyEvent.KEYCODE_MENU){
				//if (MainActivity._main.p) MainActivity._main.onResume(); else
				//	MainActivity._main.onPause();
			}
			if (keyCode == KeyEvent.KEYCODE_BACK){
				System.gc();
				MainActivity._main.countTime2=false;
				MainActivity._main.pausedSprite.setVisible(true);
				if(MainActivity._main.mMusic != null && MainActivity._main.mMusic.isPlaying()){
					MainActivity._main.mMusic.pause();
					}
				MainScene.ShowMenuScene();
			}
			break;
		case RESULT:
			if (keyCode == KeyEvent.KEYCODE_MENU){
				MainScene._InfoScene.refresh();
				MainScene.ShowInfoScene();
			}
			if (keyCode == KeyEvent.KEYCODE_BACK){
				MainActivity._main._MainScene._MenuScene.mMenuItem1.setAlpha(0.7f);
				MainActivity._main._MainScene._MenuScene.mMenuItem2.setAlpha(0.7f);
				System.gc();
				MainActivity._main.refreshGame1();
				if (MainActivity._main.chosenGame==1){
					_GameScene.stopGame();
					MainActivity._main.scoreText.setText("Score: " + MainActivity._main.score);
					MainActivity._main.missedText.setText("Missed: " + MainActivity._main.missed);
				} else 
				if (MainActivity._main.chosenGame==2){
					_GameScene2.stopGame();
					MainActivity._main.scoreText2.setText("Score: " + MainActivity._main.score2);
					MainActivity._main.missedText2.setText("Missed: " + MainActivity._main.missed2);
				}
				MainActivity._main.chosenGame=0;
				
				MainScene._InfoScene.refresh();
				MainScene.ShowInfoScene();
			}	
			break;
		}
		
	}

}

