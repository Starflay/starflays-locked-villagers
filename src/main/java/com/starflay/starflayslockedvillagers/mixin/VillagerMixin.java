package com.starflay.starflayslockedvillagers.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Inject(method = "mobInteract", at = @At("HEAD"))
    private void starflays_locked_villagers$lockTradesOnInteract(
            Player player,
            InteractionHand hand,
            CallbackInfoReturnable<InteractionResult> cir
    ) {
        Villager villager = (Villager) (Object) this;

        if (!villager.level().isClientSide() && villager.getVillagerXp() == 0) {
            villager.overrideXp(1);
        }
    }
}
