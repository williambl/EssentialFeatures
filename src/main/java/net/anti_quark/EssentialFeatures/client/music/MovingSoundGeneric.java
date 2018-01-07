package net.anti_quark.EssentialFeatures.client.music;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class MovingSoundGeneric extends MovingSound {

    private final Entity entity;
    private float distance = 0.0F;

    public MovingSoundGeneric(Entity entityIn, SoundEvent soundIn)
    {
        super(soundIn, SoundCategory.NEUTRAL);
        this.entity = entityIn;
        this.repeat = true;
        this.repeatDelay = 0;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        if (this.entity.isDead)
        {
            this.donePlaying = true;
        }
        else
        {
            this.xPosF = (float)this.entity.posX;
            this.yPosF = (float)this.entity.posY;
            this.zPosF = (float)this.entity.posZ;
            float f = MathHelper.sqrt(this.entity.motionX * this.entity.motionX + this.entity.motionZ * this.entity.motionZ);

            if ((double)f >= 0.01D)
            {
                this.distance = MathHelper.clamp(this.distance + 0.0025F, 0.0F, 1.0F);
                this.volume = 0.0F + MathHelper.clamp(f, 0.0F, 0.5F) * 0.7F;
            }
            else
            {
                this.distance = 0.0F;
                this.volume = 0.0F;
            }
        }
    }
}
