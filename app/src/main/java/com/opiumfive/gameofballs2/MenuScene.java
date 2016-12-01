package com.opiumfive.gameofballs2;

import org.andengine.entity.modifier.AlphaModifier;

import org.andengine.entity.scene.CameraScene;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;


import android.content.Intent;
import android.net.Uri;


public class MenuScene extends CameraScene {
	public Sprite mMenuItem1;
	public Sprite mMenuItem2;
	public TiledSprite mSItem;
	public TiledSprite mMItem;
	
	public MenuScene() {
		super(MainActivity.mCamera);
		setBackgroundEnabled(false);
		Sprite sp = new Sprite(0,0,MainActivity._main.mBg,MainActivity._main.getVertexBufferObjectManager());
		sp.registerEntityModifier(new AlphaModifier(1,0,1));
		//SpriteBackground sbg = new SpriteBackground(0,0,0,sp);
		attachChild(sp);
		MainActivity._main.pausedSprite.setVisible(false);
		attachChild(MainActivity._main.pausedSprite);
		//setBackground(sbg);
		mSItem = new TiledSprite(190, 260, MainActivity._main.mSBut, MainActivity._main.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (pSceneTouchEvent.isActionDown()){
					
				
				if (MainActivity._main.soundState) {
					this.setCurrentTileIndex(1);
					MainActivity._main.soundState=!MainActivity._main.soundState;
				}
				else {
					this.setCurrentTileIndex(0);
					MainActivity._main.soundState=!MainActivity._main.soundState;
				}
				return true;
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		mMItem = new TiledSprite(255, 260, MainActivity._main.mMBut, MainActivity._main.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (pSceneTouchEvent.isActionDown()){
					
				
				if (MainActivity._main.musicState) {
					this.setCurrentTileIndex(1);
					MainActivity._main.musicState=!MainActivity._main.musicState;
				}
				else {
					this.setCurrentTileIndex(0);
					MainActivity._main.musicState=!MainActivity._main.musicState;
				}
				return true;
				}
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		mMItem.setCurrentTileIndex(0);
		mSItem.setCurrentTileIndex(0);
		
		mMenuItem1 = new Sprite(40,50,MainActivity._main.mMenu1,MainActivity._main.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (MainActivity._main.chosenGame==0){
					MainActivity._main.chosenGame=1;
					mMenuItem2.setAlpha(0.4f);
					if (MainActivity._main.musicState) MainActivity._main.mMusic.play();
					
				}
				if (MainActivity._main.chosenGame==1){
					MainActivity._main.pausedSprite.setVisible(false);
					MainActivity._main.countTime=true;
					if ((MainActivity._main.mMusic!=null)&&(!MainActivity._main.mMusic.isPlaying())) {
						if (MainActivity._main.musicState) MainActivity._main.mMusic.play();
					}
						
							
					MainScene.ShowGameScene();
					if (MainActivity._main.gameTime==0)
						MainActivity._main._MainScene._GameScene.startGame();
				}
				if (MainActivity._main.chosenGame==2){
					//hint
					
					
				}
				return true;
			}
		};
		mMenuItem2 = new Sprite(250,50,MainActivity._main.mMenu2,MainActivity._main.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (MainActivity._main.chosenGame==0){
					MainActivity._main.chosenGame=2;
					mMenuItem1.setAlpha(0.4f);
					if (MainActivity._main.musicState) MainActivity._main.mMusic.play();
					
				}
				if (MainActivity._main.chosenGame==2){
				MainActivity._main.countTime2=true;
				if ((MainActivity._main.mMusic!=null)&&(!MainActivity._main.mMusic.isPlaying())) {
					if (MainActivity._main.musicState) MainActivity._main.mMusic.play();
				}
				MainScene.ShowGameScene2();
				MainActivity._main.pausedSprite.setVisible(false);
				if (MainActivity._main.gameTime2==0)
					MainActivity._main._MainScene._GameScene2.startGame();
				}
				if (MainActivity._main.chosenGame==1){
					//hint
					//Toast.makeText(MainActivity._main.getBaseContext(), "Finish previous game.", Toast.LENGTH_LONG).show();

				}
				return true;
			}
		};
		
		final Sprite mMenuItem3 = new Sprite(10,240,MainActivity._main.mMenu3,MainActivity._main.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				MainScene.ShowInfoScene();
				return true;
			}
		};
		final Sprite mMenuItem4 = new Sprite(320,240,MainActivity._main.mMenu4,MainActivity._main.getVertexBufferObjectManager()){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				String str ="https://play.google.com/store/apps/details?id=com.opiumfive.gameofballs";
				MainActivity._main.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
				return true;
			}
		};
		mMenuItem1.registerEntityModifier(new AlphaModifier(1,0,0.7f));
		mMenuItem2.registerEntityModifier(new AlphaModifier(1,0,0.7f));
		mMenuItem3.registerEntityModifier(new AlphaModifier(1,0,0.7f));
		mMenuItem4.registerEntityModifier(new AlphaModifier(1,0,0.7f));
		attachChild(mMenuItem1);
		attachChild(mMenuItem2);
		attachChild(mMenuItem3);
		attachChild(mMenuItem4);
		
		attachChild(mSItem);
		registerTouchArea(mSItem);
		attachChild(mMItem);
		registerTouchArea(mMItem);
		registerTouchArea(mMenuItem1);
		registerTouchArea(mMenuItem2);
		registerTouchArea(mMenuItem3);
		registerTouchArea(mMenuItem4);
		
	}
	
	public void Show() {
		setVisible(true);
		setIgnoreUpdate(false);
	}
	
	public void Hide() {
		setVisible(false);
		setIgnoreUpdate(true);
	}
}
