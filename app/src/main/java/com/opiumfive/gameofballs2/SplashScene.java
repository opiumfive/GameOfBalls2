package com.opiumfive.gameofballs2;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;

import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;

import org.andengine.util.adt.color.Color;
import org.andengine.util.modifier.ease.EaseBounceOut;

public class SplashScene extends CameraScene {
	public Sprite splashText;

	public SplashScene() {
		super(MainActivity.mCamera);
		setBackgroundEnabled(true);
		setBackground(new Background(Color.BLACK));
		//splashText = new Text(-100, -110, MainActivity._main.mFont2, "Game of Balls", MainActivity._main.getVertexBufferObjectManager());
		splashText = new Sprite(-300,140,MainActivity._main.mSplash,MainActivity._main.getVertexBufferObjectManager());
		splashText.registerEntityModifier(new SequenceEntityModifier(new DelayModifier(2),new ParallelEntityModifier(new ScaleModifier(2,0.7f,1.2f),new AlphaModifier(2,0,1),new MoveModifier(2,-100,110,100,100,EaseBounceOut.getInstance()))));
		Sprite badg = new Sprite(190,200,MainActivity._main.mBadge,MainActivity._main.getVertexBufferObjectManager());
		
		attachChild(badg);
		attachChild(splashText);
		
		DelayModifier dMod = new DelayModifier(6f){
		    @Override
		    protected void onModifierFinished(IEntity pItem) {
		    	MainScene.ShowMenuScene();
		    }
		};
		registerEntityModifier(dMod);
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
