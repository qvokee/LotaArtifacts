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
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            LotaArtifacts.MOD_ID);

    public static final RegistryObject<MobEffect> GLOVE_COOLDOWN = MOB_EFFECTS.register("glove_cooldown",
            () -> new GloveCooldownEffect(MobEffectCategory.HARMFUL, 0x5A5A5A));

    public static final RegistryObject<MobEffect> CRITICAL_ELEVATION = MOB_EFFECTS.register("critical_elevation",
            () -> new CriticalElevationEffect(MobEffectCategory.BENEFICIAL, 0xFFFF00));

    public static final RegistryObject<MobEffect> MANA_ABSORPTION_EFFECT = MOB_EFFECTS.register(
            "mana_absorption_effect",
            () -> new ManaAbsorptionEffect(MobEffectCategory.BENEFICIAL, 0x0000FF));

    public static final RegistryObject<MobEffect> FROZEN_IN_ICE = MOB_EFFECTS.register("frozen_in_ice",
            () -> new FrozenInIceEffect(MobEffectCategory.NEUTRAL, 0xADD8E6));

    public static final RegistryObject<MobEffect> GIFT_AND_CURSE_STASIS = MOB_EFFECTS.register("gift_and_curse_stasis",
            () -> new GiftAndCurseEffect(MobEffectCategory.HARMFUL, 0x800080));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
