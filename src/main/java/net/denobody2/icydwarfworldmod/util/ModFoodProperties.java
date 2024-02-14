package net.denobody2.icydwarfworldmod.util;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties MYSTERY_MEAT = new FoodProperties.Builder().nutrition(4).saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(getRandomEffect(), getRandomTime(), getRandomAmplifier()), 1.0f).alwaysEat().build();

    private static int getRandomTime() {
        int rand = (int) (Math.random()*2);
        if(rand == 0){
            return 200;
        }
        return 300;
    }

    private static int getRandomAmplifier() {
        return (int) (Math.random()*2);
    }

    private static MobEffect getRandomEffect() {
        int rand = (int) (Math.random()*10);
        return switch (rand) {
            case 0 -> MobEffects.ABSORPTION;
            case 1 -> MobEffects.REGENERATION;
            case 2 -> MobEffects.DAMAGE_RESISTANCE;
            case 3 -> MobEffects.DAMAGE_BOOST;
            case 4 -> MobEffects.MOVEMENT_SPEED;
            case 5 -> MobEffects.POISON;
            case 6 -> MobEffects.CONFUSION;
            case 7 -> MobEffects.DARKNESS;
            case 8 -> MobEffects.WEAKNESS;
            case 9 -> MobEffects.MOVEMENT_SLOWDOWN;
            default -> MobEffects.DIG_SPEED;
        };
    }
}
