package com.starflay.starflayslockedvillagers.mixin;

import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Inject(method = "startTradingScreen", at = @At("HEAD"))
    private void starflays_locked_villagers$lockTradesOnOpen(Player player, CallbackInfo ci) {
        Villager villager = (Villager) (Object) this;

        if (villager.getVillagerXp() == 0) {
            villager.setVillagerXp(1);
        }
    }
}
