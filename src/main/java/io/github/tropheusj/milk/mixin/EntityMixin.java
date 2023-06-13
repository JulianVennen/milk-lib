package io.github.tropheusj.milk.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.tropheusj.milk.Milk;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.TagKey;

@Mixin(Entity.class)
public abstract class EntityMixin {
	@Inject(method = "updateMovementInFluid", at = @At("HEAD"))
	public void milk$updateMovementInFluid(TagKey<Fluid> tag, double speed, CallbackInfoReturnable<Boolean> cir) {
		if ((Object) this instanceof LivingEntity entity && Milk.STILL_MILK != null) {
			FluidState state = entity.getWorld().getFluidState(entity.getBlockPos());
			if (Milk.isMilk(state)) {
				if (entity.getStatusEffects().size() > 0) {
					entity.clearStatusEffects();
				}
			}
		}
	}
}
