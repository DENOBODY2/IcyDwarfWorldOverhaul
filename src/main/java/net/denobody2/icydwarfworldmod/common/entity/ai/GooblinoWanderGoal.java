package net.denobody2.icydwarfworldmod.common.entity.ai;

import net.denobody2.icydwarfworldmod.common.entity.Gooblino;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GooblinoWanderGoal extends RandomStrollGoal {
    private Gooblino gooblino;

    public GooblinoWanderGoal(Gooblino gooblino, double speed, int rate) {
        super(gooblino, speed, rate);
        this.gooblino = gooblino;
    }

    @Nullable
    protected Vec3 getPosition() {
        return DefaultRandomPos.getPos(this.mob, 10, 7);
    }
}
