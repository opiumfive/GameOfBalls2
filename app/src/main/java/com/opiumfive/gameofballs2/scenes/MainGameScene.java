package com.opiumfive.gameofballs2.scenes;


import com.opiumfive.gameofballs2.MainGameActivity;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;
import org.andengine.util.math.MathUtils;
import org.andengine.util.modifier.IModifier;
import static com.opiumfive.gameofballs2.MainGameActivity.CAMERA_HEIGHT;
import static com.opiumfive.gameofballs2.MainGameActivity.CAMERA_WIDTH;

public class MainGameScene extends CameraScene {

    static SequenceEntityModifier scaling;
    static SequenceEntityModifier scaling_copy;
    static IEntityModifier scaling2;
    static IEntityModifier scaling_copy2;
    IEntityModifier.IEntityModifierListener entityModifierListener;
    IEntityModifier.IEntityModifierListener entityModifierListener2;
    static IUpdateHandler timer;
    public Sprite sprite2;

    int counter = 0;
    int lastball = 0;
    int gameTime = 0;
    float d = 0.04f;
    public int thresh = 10;
    boolean first = true;

    public MainGameScene() {

        super(MainGameActivity.camera);
        setBackgroundEnabled(true);

        setBackground(new Background(Color.BLACK));

        scaling = new SequenceEntityModifier(new ScaleModifier(1.5f, 0.1f, 1),new ScaleModifier(1.5f, 1, 0.1f));

        entityModifierListener = new IEntityModifier.IEntityModifierListener() {
            @Override
            public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {}
            @Override
            public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
                pItem.registerEntityModifier(new MoveModifier(0.7f,pItem.getX(),pItem.getX(),pItem.getY(),-100));
                pItem.setCullingEnabled(true);
            }
        };
        entityModifierListener2 = new IEntityModifier.IEntityModifierListener(){
            @Override
            public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {}
            @Override
            public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
                pItem.setCullingEnabled(true);
            }
        };

        //setOnAreaTouchListener();
        //setOnSceneTouchListener(MainGameActivity.mTestActivity);

        scaling.addModifierListener(entityModifierListener);

        timer = new TimerHandler(0.25f,true,new ITimerCallback(){

            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {

                counter++;
                if (counter == thresh+1) counter =0;

                gameTime++;
                if ((MathUtils.RANDOM.nextFloat()<d)||(gameTime-lastball>4)) {
                    loadNewTexture();
                    lastball=gameTime;
                }
                if (d<1) {
                    d=d+0.001f;
                }
            }});

        startGame();
    }

    public void startGame(){
        registerUpdateHandler(timer);
    }

    public void stopGame(){
        detachChildren();
        clearUpdateHandlers();
        unregisterUpdateHandler(timer);
    }

    private void loadNewTexture(){
        float width = MainGameActivity.mCircle.getWidth()/2.0f;
        float height = MainGameActivity.mCircle.getHeight()/2.0f;
        float postX = width +(CAMERA_WIDTH - width*2.0f)*MathUtils.RANDOM.nextFloat();
        float postY = height+(CAMERA_HEIGHT - height*2.0f)*MathUtils.RANDOM.nextFloat();
        Sprite sprite = new Sprite(postX, postY, MainGameActivity.mCircle, MainGameActivity.vertexBufferObjectManager) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.isActionDown()) {
                    remove((Sprite) getParent());
                }
                return true;
            }
        };
        attachChild(sprite);
        if (first) {
            sprite.registerEntityModifier(scaling);
            first=false;
        } else {
            scaling_copy=scaling.deepCopy();
            scaling_copy.addModifierListener(entityModifierListener);
            sprite.registerEntityModifier(scaling_copy);
        }

        registerTouchArea(sprite);
        setTouchAreaBindingOnActionDownEnabled(true);
    }

    /*public static void createExplosion(float pos_x,float pos_y){
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
    }*/

    public void remove(final Sprite face) {

        unregisterTouchArea(face);
        face.unregisterEntityModifier(scaling);
        //createExplosion(face.getX()+50,face.getY()+50);
        face.unregisterEntityModifier(scaling_copy);
        face.setIgnoreUpdate(true);
        detachChild(face);
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