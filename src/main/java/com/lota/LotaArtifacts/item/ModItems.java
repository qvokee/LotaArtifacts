package com.lota.LotaArtifacts.item;

import com.lota.LotaArtifacts.item.curios.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        "lotaartifacts");

        public static final RegistryObject<Item> EXPLOSIVE_FLAME_RING = ITEMS.register("explosive_flame_ring",
                        () -> new ExplosiveFlameRing(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> THUNDER_STRENGTH_RING = ITEMS.register("thunder_strength_ring",
                        () -> new ThunderStrengthRing(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> MANA_RESTORATION_NECKLACE = ITEMS.register("mana_restoration_necklace",
                        () -> new ManaRestorationNecklace(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> ARMOR_PIERCING_GLOVE = ITEMS.register("armor_piercing_glove",
                        () -> new ArmorPiercingGlove(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> MAGIC_RESIST_NECKLACE = ITEMS.register("magic_resist_necklace",
                        () -> new MagicResistNecklace(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> REGEN_ON_POISON_GLOVE = ITEMS.register("regen_on_poison_glove",
                        () -> new RegenOnPoisonGlove(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> IMPACT_GLOVE = ITEMS.register("impact_glove",
                        () -> new ImpactGlove(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> BERSERKER_NECKLACE = ITEMS.register("berserker_necklace",
                        () -> new BerserkerNecklace(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> VAMPIRE_RING = ITEMS.register("vampire_ring",
                        () -> new VampireRing(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> GUARDIAN_NECKLACE = ITEMS.register("guardian_necklace",
                        () -> new GuardianNecklace(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> LOOT_RING = ITEMS.register("loot_ring",
                        () -> new LootRing(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> FIRE_QUIVER = ITEMS.register("fire_quiver",
                        () -> new FireQuiver(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> POISON_QUIVER = ITEMS.register("poison_quiver",
                        () -> new PoisonQuiver(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> HASTE_QUIVER = ITEMS.register("haste_quiver",
                        () -> new HasteQuiver(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> CRUSHER_QUIVER = ITEMS.register("crusher_quiver",
                        () -> new CrusherQuiver(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> HEALTH_QUIVER = ITEMS.register("health_quiver",
                        () -> new HealthQuiver(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> ORDER_QUIVER = ITEMS.register("order_quiver",
                        () -> new OrderQuiver(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> HATRED_QUIVER = ITEMS.register("hatred_quiver",
                        () -> new HatredQuiver(new Item.Properties()
                                        .stacksTo(1)
                                        .rarity(Rarity.EPIC)
                                        .fireResistant()));

        public static final RegistryObject<Item> CRITICAL_ELEVATION_RING = ITEMS.register("critical_elevation_ring",
                        () -> new CriticalElevationRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> ORVIUS_UNBREAKABILITY_RING = ITEMS.register(
                        "orvius_unbreakability_ring",
                        () -> new OrviusUnbreakabilityRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> POISONOUS_BITE_RING = ITEMS.register("poisonous_bite_ring",
                        () -> new PoisonousBiteRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> FROZEN_IN_ICE_AMULET = ITEMS.register("frozen_in_ice_amulet",
                        () -> new FrozenInIceAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> NIGHTMARE_SLAYER_RING = ITEMS.register("nightmare_slayer_ring",
                        () -> new NightmareSlayerRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> HIDDEN_POTENTIAL_RING = ITEMS.register("hidden_potential_ring",
                        () -> new HiddenPotentialRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> DEATH_CULT_RING = ITEMS.register("death_cult_ring",
                        () -> new DeathCultRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> GIFT_AND_CURSE_AMULET = ITEMS.register("gift_and_curse_amulet",
                        () -> new GiftAndCurseAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> EARTHQUAKE_RING = ITEMS.register("earthquake_ring",
                        () -> new EarthquakeRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> NEXAGON_RING = ITEMS.register("nexagon_ring",
                        () -> new NexagonRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> VENOMOUS_HATRED_RING = ITEMS.register("venomous_hatred_ring",
                        () -> new VenomousHatredRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> ASURA_RING = ITEMS.register("asura_ring",
                        () -> new AsuraRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> SHATTER_STRENGTHENING_GAUNTLET = ITEMS.register(
                        "shatter_strengthening_gauntlet",
                        () -> new ShatterStrengtheningGauntlet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> MAGIC_REFLECTION_AMULET = ITEMS.register("magic_reflection_amulet",
                        () -> new MagicReflectionAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> LICH_AMULET = ITEMS.register("lich_amulet",
                        () -> new LichAmulet(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> MANA_ABSORPTION_AMULET = ITEMS.register("mana_absorption_amulet",
                        () -> new ManaAbsorptionAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> LIVING_CURSE_AMULET = ITEMS.register("living_curse_amulet",
                        () -> new LivingCurseAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> WEIGHTLESS_ARMOR_AMULET = ITEMS.register("weightless_armor_amulet",
                        () -> new WeightlessArmorAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 0.80f,
                                        0.35f, 2));

        public static final RegistryObject<Item> CRYOLITE_KNIGHT_RING = ITEMS.register("cryolite_knight_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> PHOENIX_RING = ITEMS.register("phoenix_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> SIN_OF_GREED_RING = ITEMS.register("sin_of_greed_ring",
                        () -> new SinOfGreedRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> COMBAT_RESONANCE_AMULET = ITEMS.register("combat_resonance_amulet",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(),
                                        "necklace", 2));

        public static final RegistryObject<Item> NEGATIVE_BLESSING_RING = ITEMS.register("negative_blessing_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> MANA_LIFE_RING = ITEMS.register("mana_life_ring",
                        () -> new ManaLifeRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> INFALLIBLE_SWORDSMAN_GLOVE = ITEMS.register(
                        "infallible_swordsman_glove",
                        () -> new InfallibleSwordsmanGlove(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> ENDLESS_LIFE_AMULET = ITEMS.register("endless_life_amulet",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(),
                                        "necklace", 2));

        public static final RegistryObject<Item> DARK_SWORDSMAN_RING = ITEMS.register("dark_swordsman_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> HEAVYWEIGHT_AMULET = ITEMS.register("heavyweight_amulet",
                        () -> new WeightlessArmorAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), 0.15f,
                                        0.0f, 1));

        public static final RegistryObject<Item> BLOOD_MONARCH_RING = ITEMS.register("blood_monarch_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> BLOOD_CULT_AMULET = ITEMS.register("blood_cult_amulet",
                        () -> new BloodCultAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> STEALTH_ASSASSIN_GLOVE = ITEMS.register("stealth_assassin_glove",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "hands",
                                        2));

        public static final RegistryObject<Item> MELDRIN_RING = ITEMS.register("meldrin_ring",
                        () -> new MeldrinRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> DUAL_WIELD_GLOVE = ITEMS.register("dual_wield_glove",
                        () -> new DualWieldGlove(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> FREE_SPELLCASTING_GLOVE = ITEMS.register("free_spellcasting_glove",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "hands",
                                        2));

        public static final RegistryObject<Item> TRUE_MAGE_RING = ITEMS.register("true_mage_ring",
                        () -> new TrueMageRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> OVER_TENSION_RING = ITEMS.register("over_tension_ring",
                        () -> new OverTensionRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> ROTTEN_WOUND_RING = ITEMS.register("rotten_wound_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> SON_OF_NIGHT_AMULET = ITEMS.register("son_of_night_amulet",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(),
                                        "necklace", 2));

        public static final RegistryObject<Item> ENDLESS_SURGE_GLOVE = ITEMS.register("endless_surge_glove",
                        () -> new EndlessSurgeGlove(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> LIVING_CORPSE_AMULET = ITEMS.register("living_corpse_amulet",
                        () -> new LivingCorpseAmulet(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> HEAVY_STEP_RING = ITEMS.register("heavy_step_ring",
                        () -> new HeavyStepRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> PERFECTION_RING = ITEMS.register("perfection_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> MOON_FOLLOWERS_RING = ITEMS.register("moon_followers_ring",
                        () -> new BaseArtifactItem(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant(), "ring",
                                        2));

        public static final RegistryObject<Item> ZELSREY_WARD_RING = ITEMS.register("zelsrey_ward_ring",
                        () -> new ZelsreyWardRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static final RegistryObject<Item> DRUNK_MASTER_RING = ITEMS.register("drunk_master_ring",
                        () -> new DrunkMasterRing(
                                        new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant()));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
