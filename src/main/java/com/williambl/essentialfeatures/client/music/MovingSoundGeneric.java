package com.williambl.essentialfeatures.client.music;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class MovingSoundGeneric extends MovingSound {

    private final Entity entity;

    public MovingSoundGeneric(Entity entityIn, SoundEvent soundIn) {
        super(soundIn, SoundCategory.NEUTRAL);
        this.entity = entityIn;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 1.0F;
    }

    public void tick() {
        if (!this.entity.isAlive()) {
            this.donePlaying = true;
        } else {
            this.x = (float) this.entity.posX;
            this.y = (float) this.entity.posY;
            this.z = (float) this.entity.posZ;
        }
    }
}
