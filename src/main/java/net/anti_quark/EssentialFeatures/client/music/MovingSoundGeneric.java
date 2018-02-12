package net.anti_quark.EssentialFeatures.client.music;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class MovingSoundGeneric extends MovingSound {

    private final Entity entity;

    public MovingSoundGeneric(Entity entityIn, SoundEvent soundIn)
    {
        super(soundIn, SoundCategory.NEUTRAL);
        this.entity = entityIn;
        this.repeat = true;
        this.repeatDelay = 0;
        this.volume = 1.0F;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        if (this.entity.isDead)
        {
            this.donePlaying = true;
        } else {
            this.xPosF = (float)this.entity.posX;
            this.yPosF = (float)this.entity.posY;
            this.zPosF = (float)this.entity.posZ;
        }
    }
}
