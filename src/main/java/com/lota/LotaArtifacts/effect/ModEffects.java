package com.lota.LotaArtifacts.effect;

import com.lota.LotaArtifacts.LotaArtifacts;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LotaArtifacts.MOD_ID);

    public static final RegistryObject<MobEffect> GLOVE_COOLDOWN = MOB_EFFECTS.register("glove_cooldown",
            () -> new GloveCooldownEffect(MobEffectCategory.HARMFUL, 0x5A5A5A));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
