package com.opiumfive.gameofballs2.temp;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveModifier;

import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.particle.ParticleSystem;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.CameraScene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;
import org.andengine.util.math.MathUtils;
import org.andengine.util.modifier.IModifier;

import org.andengine.util.modifier.ease.EaseLinear;


public class GameScene extends CameraScene {
	
	public static SequenceEntityModifier scaling;
	public static SequenceEntityModifier scaling_copy;
	public static IEntityModifier scaling2;
	public static IEntityModifier scaling_copy2;
	public IEntityModifierListener entityModifierListener;
	public IEntityModifierListener entityModifierListener2;
	public static IUpdateHandler timer;
	public Sprite sprite2;
	public int lastball=0;
	public int lastball2=0;
	public Rectangle recc,recc1;
	
	public GameScene() {
		
		super(MainActivity.mCamera);
		setBackgroundEnabled(true);
		recc = new Rectangle(150,10,1,7,MainActivity._main.getVertexBufferObjectManager());
		recc.setColor(Color.WHITE);
		
		//recc.setScaleCenterX(100);
        setBackground(new Background(Color.BLACK));
        
        recc1 = new Rectangle(145,7,110,13,MainActivity._main.getVertexBufferObjectManager());
        recc1.setColor(Color.BLUE);
       
		
        MainActivity._main.scoreText = new Text(1, 1, MainActivity._main.mFont, "Score:", "Score: XXXXX".length(), MainActivity._main.getVertexBufferObjectManager());
        MainActivity._main.missedText = new Text(1, 16, MainActivity._main.mFont, "Missed:", "Missed: XXX".length(), MainActivity._main.getVertexBufferObjectManager());
        MainActivity._main.apmText = new Text(260, 1, MainActivity._main.mFont, "APM:", "APM: XXX".length(), MainActivity._main.getVertexBufferObjectManager());
        MainActivity._main.streakText = new Text(360, 1, MainActivity._main.mFont, "Streak:", "Streak: XXX".length(), MainActivity._main.getVertexBufferObjectManager());
		
		MainActivity._main.scoreText.setText("Score: " + MainActivity._main.score);
		MainActivity._main.missedText.setText("Missed: " + MainActivity._main.missed);
		MainActivity._main.apmText.setText("APM: "+(int)(MainActivity._main.d*240));
		MainActivity._main.streakText.setText("Streak: "+MainActivity._main.streak);
		setOnAreaTouchListener(MainActivity._main);
		setOnSceneTouchListener(MainActivity._main);
		
		scaling = new SequenceEntityModifier(new ScaleModifier(1.5f, 0.1f, 1),new ScaleModifier(1.5f, 1, 0.1f));
		
		entityModifierListener = new
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
					MainActivity._main.missed++;
					MainActivity._main.streak=0;
					MainActivity._main.missedText.setText("Missed: " + MainActivity._main.missed);					
					pItem.registerEntityModifier(new MoveModifier(0.7f,pItem.getX(),pItem.getX(),pItem.getY(),-100));
					pItem.setCullingEnabled(true);
					if (MainActivity._main.missed==10)
						MainScene.ShowResultScene();
				}
				};
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
					pItem.setCullingEnabled(true);
				}
				};
		scaling.addModifierListener(entityModifierListener);
		
		timer = new TimerHandler(0.25f,true,new ITimerCallback(){

			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				//add
				MainActivity._main.counter++;
				if (MainActivity._main.counter == MainActivity._main.thresh+1) MainActivity._main.counter =0;
				
				
				if (MainActivity._main.countTime) MainActivity._main.gameTime++;
				if ((MathUtils.RANDOM.nextFloat()<MainActivity._main.d)||(MainActivity._main.gameTime-lastball>4)) {
					loadNewTexture();
					lastball=MainActivity._main.gameTime;
				}
				if (MainActivity._main.d<1) {
					MainActivity._main.d=MainActivity._main.d+0.001f;
					MainActivity._main.apmText.setText("APM: "+(int)(MainActivity._main.d*240));
				}
				
				recc.setWidth((MainActivity._main.d-0.04f)*100.0f);
				float ff=MathUtils.RANDOM.nextFloat();
				if (((ff>0.5)&&(ff<0.5+MainActivity._main.d2))&&(MainActivity._main.gameTime>10)&&(MainActivity._main.gameTime-lastball2>15)) {
					loadNewTexture2();
					lastball2=MainActivity._main.gameTime;
				}
					
				//(d-0.04f)/1.0f
			}}); 
	}
	
	public void startGame(){
		attachChild(MainActivity._main.scoreText);
		attachChild(MainActivity._main.missedText);
		attachChild(MainActivity._main.apmText);
		attachChild(MainActivity._main.streakText);
		attachChild(recc1);
	    attachChild(recc);
	    
		registerUpdateHandler(timer);
	}
	
	public void stopGame(){
		MainActivity._main.countTime=false;
		detachChildren();
		
		clearUpdateHandlers();
		
		unregisterUpdateHandler(timer);
	}
	
	private void loadNewTexture(){
		final Sprite sprite = new Sprite((480-MainActivity._main.mCircle.getWidth())*MathUtils.RANDOM.nextFloat(),
				(320-MainActivity._main.mCircle.getHeight())*MathUtils.RANDOM.nextFloat(),MainActivity._main.mCircle,
				MainActivity._main.getVertexBufferObjectManager());
		registerTouchArea(sprite);
		if (MainActivity._main.first) {sprite.registerEntityModifier(scaling); MainActivity._main.first=false;} else {
			scaling_copy=scaling.deepCopy();
			
			scaling_copy.addModifierListener(entityModifierListener);
			
			sprite.registerEntityModifier(scaling_copy);
		}
		attachChild(sprite);
		MainActivity._main.k++;
	}
	
	private void loadNewTexture2(){
		sprite2 = new Sprite((480-MainActivity._main.mCircle2.getWidth())*MathUtils.RANDOM.nextFloat(),
				(320-MainActivity._main.mCircle2.getHeight())*MathUtils.RANDOM.nextFloat(),MainActivity._main.mCircle2,
				MainActivity._main.getVertexBufferObjectManager());
		registerTouchArea(sprite2);
		if (MathUtils.RANDOM.nextFloat()<0.5f) 
			scaling2 = new MoveModifier(1+MathUtils.RANDOM.nextFloat(), 480, -100 , 220*MathUtils.RANDOM.nextFloat() ,220*MathUtils.RANDOM.nextFloat(),EaseLinear.getInstance());
		else 
			scaling2 = new MoveModifier(1+MathUtils.RANDOM.nextFloat(), -100, 480 , 220*MathUtils.RANDOM.nextFloat() ,220*MathUtils.RANDOM.nextFloat(),EaseLinear.getInstance());
		sprite2.registerEntityModifier(scaling2); 
		scaling2.addModifierListener(entityModifierListener2);
		attachChild(sprite2);
		MainActivity._main.k++;
	}
	
	public static void createExplosion(float pos_x,float pos_y){
		PointParticleEmitter particleEmitter = new PointParticleEmitter(pos_x,pos_y);
		
		final ParticleSystem particleSystem = new SpriteParticleSystem(particleEmitter,100,100,10,
				MainActivity._main.mExp,MainActivity._main.getVertexBufferObjectManager());
		
		particleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>(-77, 77, -77, 77)); 
		particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0,0.6f * 1.0f, 1, 0));

		MainActivity._MainScene._GameScene.attachChild(particleSystem);
		MainActivity._MainScene._GameScene.registerUpdateHandler(new TimerHandler(1.0f, new ITimerCallback() {
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {
				particleSystem.setIgnoreUpdate(true);
				particleSystem.detachSelf();
				MainActivity._MainScene._GameScene.sortChildren();
				MainActivity._MainScene._GameScene.unregisterUpdateHandler(pTimerHandler);
			}
		}));
	}
	
	 public static void remove(final Sprite face) {
		MainActivity._main.streakText.setText("Streak: "+MainActivity._main.streak);
		MainActivity._MainScene._GameScene.unregisterTouchArea(face);
		face.unregisterEntityModifier(scaling);
		createExplosion(face.getX()+50,face.getY()+50);
		face.unregisterEntityModifier(scaling_copy);
		face.setIgnoreUpdate(true);
		MainActivity._MainScene._GameScene.detachChild(face);
			
		if (MainActivity._main.k==0) System.gc();
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
