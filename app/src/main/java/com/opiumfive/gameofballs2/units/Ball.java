package com.opiumfive.gameofballs2.units;


import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

public class Ball extends Sprite {

    private SequenceEntityModifier mScaling;

    public Ball(final float x, final float y, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(x, y, pTextureRegion, pVertexBufferObjectManager);
        mScaling = new SequenceEntityModifier(new ScaleModifier(1.5f, 0.1f, 1), new ScaleModifier(1.5f, 1, 0.1f));
        mScaling.addModifierListener(new IModifier.IModifierListener<IEntity>() {
            @Override
            public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {}

            @Override
            public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
                setVisible(false);
                setPosition(x, -150);
                setCullingEnabled(true);
            }
        });
        registerEntityModifier(mScaling);
    }

    public void prepareRemove() {

        unregisterEntityModifier(mScaling);
        setIgnoreUpdate(true);

    }
}
