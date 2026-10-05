package com.starflay.starflayslockedvillagers.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    private static final String STARFLAYS_LOCKED_VILLAGERS_TAG = "starflays_locked_villagers.locked";

    @Inject(method = "mobInteract", at = @At("HEAD"))
    private void starflays_locked_villagers$lockTradesOnInteract(
            Player player,
            InteractionHand hand,
            CallbackInfoReturnable<InteractionResult> cir
    ) {
        Villager villager = (Villager) (Object) this;

        if (!villager.level().isClientSide()
                && villager.getVillagerData().profession().value() != VillagerProfession.NONE) {
            villager.addTag(STARFLAYS_LOCKED_VILLAGERS_TAG);
        }
    }

    @Inject(method = "setVillagerData", at = @At("HEAD"), cancellable = true)
    private void starflays_locked_villagers$preventProfessionReset(
            VillagerData newData,
            CallbackInfo ci
    ) {
        Villager villager = (Villager) (Object) this;

        if (villager.getTags().contains(STARFLAYS_LOCKED_VILLAGERS_TAG)
                && villager.getVillagerData().profession().value() != VillagerProfession.NONE
                && newData.profession().value() == VillagerProfession.NONE) {
            ci.cancel();
        }
    }
}
