package com.opiumfive.gameofballs2;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;

import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;

import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.particle.ParticleSystem;
import org.andengine.entity.particle.SpriteParticleSystem;

import org.andengine.entity.particle.emitter.PointParticleEmitter;

import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;
import org.andengine.util.math.MathUtils;
import org.andengine.util.modifier.IModifier;



public class GameScene2 extends CameraScene {
	public static IEntityModifier modi;
	public static IEntityModifier modi2;
	public int lastTime=0;
	public static int i=1;
	public static Sprite sprite3;
	public static Sprite sprite4;
	public IEntityModifierListener entityModifierListener2;
	public IEntityModifierListener entityModifierListener3;
	public IUpdateHandler timer;
	
	public GameScene2() {
		super(MainActivity.mCamera);
		setBackgroundEnabled(true);
		setBackground(new Background(Color.BLACK));
		MainActivity._main.scoreText2 = new Text(5, 5, MainActivity._main.mFont, "Score:", "Score: XXX".length(), MainActivity._main.getVertexBufferObjectManager());
        MainActivity._main.missedText2 = new Text(5, 20, MainActivity._main.mFont, "Missed:", "Missed: XXX".length(), MainActivity._main.getVertexBufferObjectManager());
        
        MainActivity._main.scoreText2.setText("Score: " + MainActivity._main.score2);
		MainActivity._main.missedText2.setText("Missed: " + MainActivity._main.missed2);
		setOnSceneTouchListener(MainActivity._main);
		setOnAreaTouchListener(MainActivity._main);
		timer = new TimerHandler(0.3f,true,new ITimerCallback(){

			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				//add
				if (MainActivity._main.countTime2) MainActivity._main.gameTime2++;
				if (i<=MainActivity._main.lev)
				{
					sprite3 = new Sprite(430,110,
							MainActivity._main.mCircle,
							MainActivity._main.getVertexBufferObjectManager());
					registerTouchArea(sprite3);
					modi = new ParallelEntityModifier(new ScaleModifier(3.5f, 0.4f, 1),new MoveModifier(6.5f, 480, -100 , 220*MathUtils.RANDOM.nextFloat() ,220*MathUtils.RANDOM.nextFloat()));
					entityModifierListener2 = new
							IEntityModifierListener(){
							
							@Override
							public void onModifierStarted(IModifier<IEntity> pModifier,
									IEntity pItem) {
								// TODO Auto-generated method stub
								
							}
							@Override
							public void onModifierFinished(IModifier<IEntity> pModifier,
									IEntity pItem) {
								// TODO Auto-generated method stub
								MainActivity._main.missed2++;
								MainActivity._main.streak2=0;
								MainActivity._main.missedText2.setText("Missed: " + MainActivity._main.missed2);					
								pItem.setCullingEnabled(true);
							}
							};
					modi.addModifierListener(entityModifierListener2);
					sprite3.registerEntityModifier(modi);
					attachChild(sprite3);
					
					sprite4 = new Sprite(-100,110,
							MainActivity._main.mCircle,
							MainActivity._main.getVertexBufferObjectManager());
					registerTouchArea(sprite4);
					modi2 = new ParallelEntityModifier(new ScaleModifier(3.5f, 0.4f, 1),new MoveModifier(6.5f, -100, 480 , 220*MathUtils.RANDOM.nextFloat() ,220*MathUtils.RANDOM.nextFloat()));
					entityModifierListener3 = new
							IEntityModifierListener(){
							
							@Override
							public void onModifierStarted(IModifier<IEntity> pModifier,
									IEntity pItem) {
								// TODO Auto-generated method stub
								
							}
							@Override
							public void onModifierFinished(IModifier<IEntity> pModifier,
									IEntity pItem) {
								// TODO Auto-generated method stub
								MainActivity._main.missed2++;
								MainActivity._main.streak2=0;
								MainActivity._main.missedText2.setText("Missed: " + MainActivity._main.missed2);					
								pItem.setCullingEnabled(true);
							}
							};
					modi2.addModifierListener(entityModifierListener3);
					sprite4.registerEntityModifier(modi2);
					attachChild(sprite4);
					i++;	
					
				} else 
				{ 
					lastTime++;
					if (lastTime==22) {System.gc();}
					if (lastTime>23){
						MainActivity._main.lev++;
						lastTime=0;
						i=1;
					}
				}}});
		//registerUpdateHandler(timer);
	}
	
	public void startGame(){
		attachChild(MainActivity._main.scoreText2);
		
		attachChild(MainActivity._main.missedText2);
		registerUpdateHandler(timer);
	}
	
	public void stopGame(){
		MainActivity._main.countTime2=false;
		detachChildren();
		clearUpdateHandlers();
		unregisterUpdateHandler(timer);
	}
	
	public static void createExplosion(float pos_x,float pos_y){
		PointParticleEmitter particleEmitter = new PointParticleEmitter(pos_x,pos_y);
		
		final ParticleSystem particleSystem = new SpriteParticleSystem(particleEmitter,100,100,10,
				MainActivity._main.mExp,MainActivity._main.getVertexBufferObjectManager());
		
		particleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>(-77, 77, -77, 77));
		//particleSystem.addParticleInitializer(new ExpireParticleInitializer<Sprite>(2)); 
		particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0,0.6f * 1.0f, 1, 0));

		MainActivity._MainScene._GameScene2.attachChild(particleSystem);
		MainActivity._MainScene._GameScene2.registerUpdateHandler(new TimerHandler(1.0f, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				particleSystem.setIgnoreUpdate(true);
				particleSystem.detachSelf();
				MainActivity._MainScene._GameScene2.sortChildren();
				MainActivity._MainScene._GameScene2.unregisterUpdateHandler(pTimerHandler);
			}
		}));
	}
	
	public static void remove(final Sprite face) {
		MainActivity._MainScene._GameScene2.unregisterTouchArea(face);
		createExplosion(face.getX()+50,face.getY()+50);
		face.unregisterEntityModifier(modi);
		face.unregisterEntityModifier(modi2);
		face.setIgnoreUpdate(true);
		MainActivity._MainScene._GameScene2.detachChild(face);
			
		
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