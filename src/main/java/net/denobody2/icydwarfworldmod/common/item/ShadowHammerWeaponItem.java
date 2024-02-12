package net.denobody2.icydwarfworldmod.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.denobody2.icydwarfworldmod.common.entity.RiftEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ShadowHammerWeaponItem extends SwordItem {
    private final Multimap<Attribute, AttributeModifier> shadowSwordAttributes;
    public ShadowHammerWeaponItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 9F, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.8F, AttributeModifier.Operation.ADDITION));
        this.shadowSwordAttributes = builder.build();
    }
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.shadowSwordAttributes : super.getDefaultAttributeModifiers(equipmentSlot);
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        Level level = entity.level();
        this.spawnRift(entity.blockPosition(), level, player);
        return super.onLeftClickEntity(stack, player, entity);
    }
    private void spawnRift(BlockPos blockPos, Level level, Player player) {
        if(!player.getCooldowns().isOnCooldown(this)){
            level.addFreshEntity(new RiftEntity(level, blockPos.getX(), blockPos.getY()+1, blockPos.getZ(), player, 100, 1.5F));
            player.getCooldowns().addCooldown(this, 200);
        }
    }
    private void spawnAltRift(BlockPos blockPos, Level level, Player player) {
        level.addFreshEntity(new RiftEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), player, 200, 2.0F));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        this.spawnAltRift(pPlayer.blockPosition(), pLevel, pPlayer);
        if(!pPlayer.getCooldowns().isOnCooldown(this)){
            pPlayer.getCooldowns().addCooldown(this, 400);
            ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
            itemStack.hurtAndBreak(5, pPlayer, (p_279044_) -> {
                p_279044_.broadcastBreakEvent(pUsedHand);
            });
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
