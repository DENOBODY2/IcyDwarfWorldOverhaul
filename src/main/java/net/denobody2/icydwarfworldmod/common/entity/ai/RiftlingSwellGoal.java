package net.denobody2.icydwarfworldmod.common.entity.ai;

import net.denobody2.icydwarfworldmod.common.entity.Riftling;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Creeper;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class RiftlingSwellGoal extends Goal{
    private final Riftling riftling;
    @Nullable
    private LivingEntity target;

    public RiftlingSwellGoal(Riftling riftling) {
        this.riftling = riftling;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        LivingEntity livingentity = this.riftling.getTarget();
        return this.riftling.getSwellDir() > 0 || livingentity != null && this.riftling.distanceToSqr(livingentity) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.riftling.getNavigation().stop();
        this.target = this.riftling.getTarget();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.target == null) {
            this.riftling.setSwellDir(-1);
        } else if (this.riftling.distanceToSqr(this.target) > 49.0D) {
            this.riftling.setSwellDir(-1);
        } else if (!this.riftling.getSensing().hasLineOfSight(this.target)) {
            this.riftling.setSwellDir(-1);
        } else {
            this.riftling.setSwellDir(1);
        }
    }
}
