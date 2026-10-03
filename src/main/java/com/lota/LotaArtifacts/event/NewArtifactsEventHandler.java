package com.lota.LotaArtifacts.event;

import com.lota.LotaArtifacts.effect.ModEffects;
import com.lota.LotaArtifacts.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "lotaartifacts", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NewArtifactsEventHandler {
    private static final Random RANDOM = new Random();
    private static final String LOTA_CRIT_ROLL_EVENT = "com.lota.lotacrit.api.event.CriticalHitRollEvent";
    private static final String LOTA_CRIT_TRIGGERED_EVENT = "com.lota.lotacrit.api.event.CriticalHitTriggeredEvent";

    private static final TagKey<EntityType<?>> GREED_LOOT_BLACKLIST = TagKey.create(Registries.ENTITY_TYPE,
            ResourceLocation.tryParse("lotaartifacts:greed_loot_blacklist"));
    private static final TagKey<Item> SPELLBOOK_STAFF_TAG = TagKey.create(Registries.ITEM,
            ResourceLocation.tryParse("irons_spellbooks:staff"));

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            LivingEntity target = event.getEntity();
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                handler.findFirstCurio(ModItems.ORVIUS_UNBREAKABILITY_RING.get()).ifPresent(slot -> {
                    event.setAmount(event.getAmount() * 1.15f);
                });

                handler.findFirstCurio(ModItems.POISONOUS_BITE_RING.get()).ifPresent(slot -> {
                    if (target.hasEffect(MobEffects.POISON) && RANDOM.nextFloat() < 0.10f) {
                        applyEffect(target, "dungeons_and_combat:toxin", 100, 0);
                    }
                });

                handler.findFirstCurio(ModItems.HIDDEN_POTENTIAL_RING.get()).ifPresent(slot -> {
                    AttributeInstance armorAttr = player.getAttribute(Attributes.ARMOR);
                    if (armorAttr != null && armorAttr.getValue() <= 14) {
                        event.setAmount(event.getAmount() * 1.30f);
                    }
                });

                handler.findFirstCurio(ModItems.DEATH_CULT_RING.get()).ifPresent(slot -> {
                    if (RANDOM.nextFloat() < 0.10f) {
                        applyEffect(player, "born_in_chaos_v1:bone_barrier", 100, 0);
                    }
                });

                handler.findFirstCurio(ModItems.GIFT_AND_CURSE_AMULET.get()).ifPresent(slot -> {
                    if (!player.hasEffect(ModEffects.GIFT_AND_CURSE_STASIS.get())) {
                        if (RANDOM.nextBoolean()) {
                            event.setAmount(event.getAmount() * 0.20f);
                        } else {
                            event.setAmount(event.getAmount() * 3.0f);
                        }

                        ItemStack stack = slot.stack();
                        int attacks = stack.getOrCreateTag().getInt("LotaAttacks") + 1;
                        if (attacks >= 2) {
                            player.addEffect(new MobEffectInstance(ModEffects.GIFT_AND_CURSE_STASIS.get(), 50, 0));
                            stack.getOrCreateTag().putInt("LotaAttacks", 0);
                        } else {
                            stack.getOrCreateTag().putInt("LotaAttacks", attacks);
                        }
                    }
                });

                handler.findFirstCurio(ModItems.VENOMOUS_HATRED_RING.get()).ifPresent(slot -> {
                    float bonus = 0;
                    if (target.hasEffect(MobEffects.POISON))
                        bonus += 0.05f;
                    if (hasEffect(target, "dungeons_and_combat:toxin"))
                        bonus += 0.05f;
                    if (target.hasEffect(MobEffects.CONFUSION))
                        bonus += 0.05f;
                    if (hasEffect(target, "dungeons_and_combat:dizziness"))
                        bonus += 0.05f;
                    event.setAmount(event.getAmount() * (1 + bonus));
                });

                handler.findFirstCurio(ModItems.ASURA_RING.get()).ifPresent(slot -> {
                    float bonus = 0;
                    if (target.hasEffect(MobEffects.LEVITATION))
                        bonus += 0.10f;
                    if (hasEffect(target, "lota:electrization"))
                        bonus += 0.10f;
                    if (hasEffect(target, "dungeons_and_combat:conductive"))
                        bonus += 0.10f;
                    event.setAmount(event.getAmount() * (1 + bonus));
                });

                handler.findFirstCurio(ModItems.SHATTER_STRENGTHENING_GAUNTLET.get()).ifPresent(slot -> {
                    if (hasEffect(target, "cataclysm:stun")) {
                        event.setAmount(event.getAmount() * 1.25f);
                    }
                });

                handler.findFirstCurio(ModItems.LIVING_CURSE_AMULET.get()).ifPresent(slot -> {
                    spreadAllHarmfulEffects(player, target);
                });
                handler.findFirstCurio(ModItems.CRYOLITE_KNIGHT_RING.get()).ifPresent(slot -> {
                    float bonus = 0;
                    if (hasEffect(target, "born_in_chaos_v1:bone_chilling"))
                        bonus += 0.05f;
                    if (hasEffect(target, "lotaeffect:explosive_freeze"))
                        bonus += 0.05f;
                    if (hasEffect(player, "born_in_chaos_v1:snow_storm"))
                        bonus += 0.05f;
                    event.setAmount(event.getAmount() * (1 + bonus));
                });
                handler.findFirstCurio(ModItems.COMBAT_RESONANCE_AMULET.get()).ifPresent(slot -> {
                    ItemStack stack = slot.stack();
                    long lastAttack = stack.getOrCreateTag().getLong("LotaLastSeriesTime");
                    long currentTime = player.level().getGameTime();
                    int series = stack.getOrCreateTag().getInt("LotaSeries");

                    if (currentTime - lastAttack <= 10) {
                        series = Math.min(series + 1, 5);
                    } else {
                        series = 1;
                    }

                    event.setAmount(event.getAmount() * (1 + (series * 0.04f)));
                    stack.getOrCreateTag().putInt("LotaSeries", series);
                    stack.getOrCreateTag().putLong("LotaLastSeriesTime", currentTime);
                });
                handler.findFirstCurio(ModItems.NEGATIVE_BLESSING_RING.get()).ifPresent(slot -> {
                    ItemStack stack = slot.stack();
                    if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                        java.util.List<MobEffectInstance> harmful = target.getActiveEffects().stream()
                                .filter(inst -> inst.getEffect().getCategory() == MobEffectCategory.HARMFUL)
                                .toList();
                        if (!harmful.isEmpty()) {
                            float bonus = harmful.size() * 0.03f;
                            event.setAmount(event.getAmount() * (1 + bonus));
                            for (MobEffectInstance inst : harmful) {
                                target.removeEffect(inst.getEffect());
                            }
                            player.getCooldowns().addCooldown(stack.getItem(), 500);
                        }
                    }
                });
                handler.findFirstCurio(ModItems.BLOOD_CULT_AMULET.get()).ifPresent(slot -> {
                    float bonus = 0;
                    if (hasEffect(target, "dungeons_and_combat:bleeding"))
                        bonus += 0.05f;
                    if (hasEffect(target, "dungeons_and_combat:fatal_oath") ||
                            hasEffect(target, "dungeons_and_combat:fatal_oath_ii") ||
                            hasEffect(target, "dungeons_and_combat:fatal_oath_iii"))
                        bonus += 0.05f;
                    if (hasEffect(player, "dungeons_and_combat:bleeding") || player.hasEffect(MobEffects.WITHER))
                        bonus += 0.15f;
                    event.setAmount(event.getAmount() * (1 + bonus));
                });
                handler.findFirstCurio(ModItems.DRUNK_MASTER_RING.get()).ifPresent(slot -> {
                    if (player.hasEffect(MobEffects.CONFUSION)) {
                        event.setAmount(event.getAmount() * 1.50f);
                    }
                });
                handler.findFirstCurio(ModItems.SIN_OF_GREED_RING.get()).ifPresent(slot -> {
                    if (RANDOM.nextFloat() < 0.40f) {
                        if (isValidGreedLootTarget(target)) {
                            dropGreedLoot(target);
                        }
                    }
                });
                handler.findFirstCurio(ModItems.DARK_SWORDSMAN_RING.get()).ifPresent(slot -> {
                    if (RANDOM.nextFloat() < 0.15f) {
                        target.addEffect(new MobEffectInstance(ModEffects.DARK_MARK.get(), 100, 0));
                    }
                });
                handler.findFirstCurio(ModItems.ROTTEN_WOUND_RING.get()).ifPresent(slot -> {
                    if (hasEffect(target, "dungeons_and_combat:toxin") && RANDOM.nextFloat() < 0.10f) {
                        applyEffect(target, "dungeons_and_combat:bleeding", 100, 0);
                    }
                });
                if (hasEffect(target, "lotaartifacts:phoenix_wrath")) {
                    handler.findFirstCurio(ModItems.PHOENIX_RING.get()).ifPresent(slot -> {
                        player.heal(event.getAmount() * 0.05f);
                    });
                }
                handler.findFirstCurio(ModItems.VAMPIRE_RING.get()).ifPresent(slot -> {
                    if (!player.level().isClientSide && event.getAmount() > 0) {
                        player.heal(event.getAmount() * 0.02f);
                    }
                });
            });
        }

        if (event.getEntity() instanceof Player player && !player.level().isClientSide) {
            if (player.getHealth() / player.getMaxHealth() <= 0.25f) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    handler.findFirstCurio(ModItems.FROZEN_IN_ICE_AMULET.get()).ifPresent(slot -> {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 9));
                        player.addEffect(new MobEffectInstance(ModEffects.FROZEN_IN_ICE.get(), 100, 0));
                    });
                });
            }
        }

        if (event.getEntity() instanceof Player player && !player.level().isClientSide
                && event.getAmount() >= player.getHealth()) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                handler.findFirstCurio(ModItems.LICH_AMULET.get()).ifPresent(slot -> {
                    ItemStack stack = slot.stack();
                    if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                        event.setAmount(player.getHealth() - 1.0f);
                        player.getCooldowns().addCooldown(stack.getItem(), 2400);
                    }
                });
            });
        }
    }

    @SubscribeEvent
    public static void onLotaCritEvent(Event event) {
        String eventClassName = event.getClass().getName();
        if (LOTA_CRIT_ROLL_EVENT.equals(eventClassName)) {
            handleLotaCritRoll(event);
            return;
        }

        if (LOTA_CRIT_TRIGGERED_EVENT.equals(eventClassName)) {
            handleLotaCritTriggered(event);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player && !player.level().isClientSide) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                handler.findFirstCurio(ModItems.NIGHTMARE_SLAYER_RING.get()).ifPresent(slot -> {
                    ItemStack stack = slot.stack();
                    if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                        applyEffect(player, "monsterexpansion:cloaked", 200, 0);
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0));
                        player.getCooldowns().addCooldown(stack.getItem(), 800);
                    }
                });

                handler.findFirstCurio(ModItems.MANA_ABSORPTION_AMULET.get()).ifPresent(slot -> {
                    if (RANDOM.nextFloat() < 0.15f) {
                        player.addEffect(new MobEffectInstance(ModEffects.MANA_ABSORPTION_EFFECT.get(), 160, 0));
                    }
                });
            });
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                handler.findFirstCurio(ModItems.ORVIUS_UNBREAKABILITY_RING.get()).ifPresent(slot -> {
                    AttributeInstance speedAttr = player.getAttribute(Attributes.ATTACK_SPEED);
                    if (speedAttr != null && speedAttr.getValue() <= 0.90) {
                        applyEffect(player, "epicfight:stun_immunity", 20, 0);
                    }
                });
                handler.findFirstCurio(ModItems.ENDLESS_LIFE_AMULET.get()).ifPresent(slot -> {
                    if (player.tickCount % 80 == 0) {
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
                    }
                });
                handler.findFirstCurio(ModItems.SON_OF_NIGHT_AMULET.get()).ifPresent(slot -> {
                    if (player.isShiftKeyDown()) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 29));
                        player.removeEffect(MobEffects.INVISIBILITY);
                        removeEffect(player, "monsterexpansion:cloaked");
                        removeEffect(player, "irons_spellbooks:true_invisibility");
                    } else {
                        MobEffectInstance speedInstance = player.getEffect(MobEffects.MOVEMENT_SPEED);
                        if (speedInstance != null && speedInstance.getAmplifier() == 29
                                && speedInstance.getDuration() <= 20) {
                            player.removeEffect(MobEffects.MOVEMENT_SPEED);
                        }
                    }
                });
                handler.findFirstCurio(ModItems.PERFECTION_RING.get()).ifPresent(slot -> {
                    ItemStack stack = slot.stack();
                    long lastAttack = stack.getOrCreateTag().getLong("LotaLastAttackTime");
                    if (player.level().getGameTime() - lastAttack > 50) {
                        stack.getOrCreateTag().putInt("LotaPerfection", 0);
                    }
                });
                handler.findFirstCurio(ModItems.FREE_SPELLCASTING_GLOVE.get()).ifPresentOrElse(slot -> {
                    if (!isHoldingStaff(player)) {
                        applyAttributeBonus(player, "irons_spellbooks:max_mana", 0.10, "Free Sorcery Mana");
                        applyAttributeBonus(player, "irons_spellbooks:spell_power", 0.10, "Free Sorcery Power");
                        applyAttributeBonus(player, "irons_spellbooks:cast_time_reduction", 0.10, "Free Sorcery Speed");
                    } else {
                        removeAttributeBonus(player, "irons_spellbooks:max_mana", "Free Sorcery Mana");
                        removeAttributeBonus(player, "irons_spellbooks:spell_power", "Free Sorcery Power");
                        removeAttributeBonus(player, "irons_spellbooks:cast_time_reduction", "Free Sorcery Speed");
                    }
                }, () -> {
                    removeAttributeBonus(player, "irons_spellbooks:max_mana", "Free Sorcery Mana");
                    removeAttributeBonus(player, "irons_spellbooks:spell_power", "Free Sorcery Power");
                    removeAttributeBonus(player, "irons_spellbooks:cast_time_reduction", "Free Sorcery Speed");
                });
            });
        }
    }

    @SubscribeEvent
    public static void onLivingHurtPost(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                if (player.getHealth() / player.getMaxHealth() <= 0.50f) {
                    handler.findFirstCurio(ModItems.PHOENIX_RING.get()).ifPresent(slot -> {
                        if (!player.getCooldowns().isOnCooldown(slot.stack().getItem())) {
                            triggerPhoenixBlow(player);
                            player.getCooldowns().addCooldown(slot.stack().getItem(), 1000);
                        }
                    });
                }
                handler.findFirstCurio(ModItems.PERFECTION_RING.get()).ifPresent(slot -> {
                    slot.stack().getOrCreateTag().putInt("LotaPerfection", 0);
                });
            });
        }

        if (event.getSource().getEntity() instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                handler.findFirstCurio(ModItems.ENDLESS_SURGE_GLOVE.get()).ifPresent(slot -> {
                    if (!player.getCooldowns().isOnCooldown(slot.stack().getItem())) {
                        if (player.isSprinting() || player.getDeltaMovement().horizontalDistance() > 0.1) {
                            restoreStamina(player, 4.0f);
                            player.getCooldowns().addCooldown(slot.stack().getItem(), 80);
                        }
                    }
                });
                handler.findFirstCurio(ModItems.PERFECTION_RING.get()).ifPresent(slot -> {
                    ItemStack stack = slot.stack();
                    if (stack.getOrCreateTag().getBoolean("LotaPerfectionTriggered")) {
                        stack.getOrCreateTag().putInt("LotaPerfection", 0);
                        stack.getOrCreateTag().putBoolean("LotaPerfectionTriggered", false);
                    } else {
                        int perfection = stack.getOrCreateTag().getInt("LotaPerfection");
                        stack.getOrCreateTag().putInt("LotaPerfection", perfection + 1);
                    }
                    stack.getOrCreateTag().putLong("LotaLastAttackTime", player.level().getGameTime());
                });
                handler.findFirstCurio(ModItems.FREE_SPELLCASTING_GLOVE.get()).ifPresent(slot -> {
                    boolean hasStaff = isHoldingStaff(player);
                    applyFreeSpellcastingBonuses(player, !hasStaff);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onItemUse(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player) {
            CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                handler.findFirstCurio(ModItems.ENDLESS_LIFE_AMULET.get()).ifPresent(slot -> {
                    ItemStack item = event.getItem();
                    String name = item.getDescriptionId();
                    if (name.contains("estus") || name.contains("elixir")) {
                        event.setCanceled(true);
                    }
                });
            });
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.GIFT_AND_CURSE_STASIS.get())) {
                event.setCanceled(true);
                return;
            }
        }

        if (event.getEntity() instanceof Player player && player.isBlocking() && !player.level().isClientSide) {
            if (RANDOM.nextFloat() < 0.35f) {
                CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
                    handler.findFirstCurio(ModItems.MAGIC_REFLECTION_AMULET.get()).ifPresent(slot -> {
                        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                            transferAllNegativeEffects(player, attacker);
                        }
                    });
                });
            }
        }
    }

    private static void transferAllNegativeEffects(Player player, LivingEntity target) {
        java.util.List<MobEffectInstance> toTransfer = player.getActiveEffects().stream()
                .filter(inst -> inst.getEffect().getCategory() == MobEffectCategory.HARMFUL)
                .toList();

        for (MobEffectInstance inst : toTransfer) {
            target.addEffect(new MobEffectInstance(inst));
            player.removeEffect(inst.getEffect());
        }
    }

    private static void triggerPhoenixBlow(Player player) {
        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5.0))
                .stream().filter(e -> e != player).forEach(e -> {
                    e.addEffect(new MobEffectInstance(ModEffects.PHOENIX_WRATH.get(), 200, 0));
                    e.setSecondsOnFire(5);
                });
    }

    private static final UUID FREE_SPE_UUID = UUID.fromString("7c8d9e0f-1a2b-3c4d-5e6f-7a8b9c0d1e2f");

    private static boolean isHoldingStaff(Player player) {
        return isStaff(player.getMainHandItem()) || isStaff(player.getOffhandItem());
    }

    private static boolean isStaff(ItemStack stack) {
        if (stack.isEmpty())
            return false;
        String id = ForgeRegistries.ITEMS.getKey(stack.getItem()).toString();
        return stack.is(SPELLBOOK_STAFF_TAG) || id.contains("staff") || id.contains("wand");
    }

    private static void applyFreeSpellcastingBonuses(Player player, boolean apply) {
        try {
            Attribute spellPower = ForgeRegistries.ATTRIBUTES
                    .getValue(ResourceLocation.tryParse("irons_spellbooks:spell_power"));
            Attribute castSpeed = ForgeRegistries.ATTRIBUTES
                    .getValue(ResourceLocation.tryParse("irons_spellbooks:cast_time_reduction"));
            Attribute maxMana = ForgeRegistries.ATTRIBUTES
                    .getValue(ResourceLocation.tryParse("irons_spellbooks:max_mana"));

            Attribute[] attrs = { spellPower, castSpeed, maxMana };
            double[] values = { 0.10, -0.10, 0.10 };

            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i] != null) {
                    var instance = player.getAttribute(attrs[i]);
                    if (instance != null) {
                        instance.removeModifier(FREE_SPE_UUID);
                        if (apply) {
                            instance.addTransientModifier(new AttributeModifier(FREE_SPE_UUID,
                                    "Free Spellcasting Bonus", values[i], AttributeModifier.Operation.MULTIPLY_TOTAL));
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private static void restoreStamina(Player player, float amount) {
        if (player instanceof ServerPlayer sp) {
            String name = sp.getGameProfile().getName();
            sp.server.getCommands().performPrefixedCommand(sp.createCommandSourceStack().withSuppressedOutput(),
                    "epicfight stamina " + name + " add " + amount);
        }
    }

    private static void dropGreedLoot(net.minecraft.world.entity.Entity entity) {
        if (!entity.level().isClientSide) {
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();

            float chance = RANDOM.nextFloat();
            net.minecraft.world.item.Item drop;

            if (chance < 0.25f)
                drop = Items.DIAMOND;
            else if (chance < 0.50f)
                drop = Items.EMERALD;
            else if (chance < 0.75f)
                drop = Items.AMETHYST_SHARD;
            else
                drop = Items.GOLD_INGOT;

            entity.level().addFreshEntity(new ItemEntity(entity.level(), x, y, z, new ItemStack(drop)));
        }
    }

    private static boolean isValidGreedLootTarget(LivingEntity target) {
        if (target instanceof Player) {
            return false;
        }
        if (target instanceof ArmorStand) {
            return false;
        }
        if (target.getType().is(GREED_LOOT_BLACKLIST)) {
            return false;
        }
        String descriptionId = target.getType().getDescriptionId();
        if (descriptionId != null) {
            String lowered = descriptionId.toLowerCase(Locale.ROOT);
            if (lowered.contains("dummy") || lowered.contains("mannequin") || lowered.contains("training")) {
                return false;
            }
        }

        ResourceLocation typeKey = ForgeRegistries.ENTITY_TYPES.getKey(target.getType());
        if (typeKey == null) {
            return false;
        }

        if ("dummmmmmy".equalsIgnoreCase(typeKey.getNamespace())) {
            return false;
        }

        String id = typeKey.toString().toLowerCase(Locale.ROOT);
        return !(id.contains("dummy") || id.contains("mannequin") || id.contains("training"));
    }

    private static void spreadAllHarmfulEffects(Player player, LivingEntity target) {
        player.getActiveEffects().stream()
                .filter(inst -> inst.getEffect().getCategory() == MobEffectCategory.HARMFUL)
                .forEach(inst -> target.addEffect(new MobEffectInstance(inst)));
    }

    private static void applyEffect(LivingEntity entity, String effectId, int duration, int amplifier) {
        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.tryParse(effectId));
        if (effect != null) {
            entity.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }
    }

    private static boolean hasEffect(LivingEntity entity, String effectId) {
        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.tryParse(effectId));
        return effect != null && entity.hasEffect(effect);
    }

    private static void removeEffect(LivingEntity entity, String effectId) {
        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.tryParse(effectId));
        if (effect != null && entity.hasEffect(effect)) {
            entity.removeEffect(effect);
        }
    }

    private static void applyAttributeBonus(Player player, String attrId, double amount, String name) {
        Attribute attr = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.tryParse(attrId));
        if (attr != null) {
            AttributeInstance inst = player.getAttribute(attr);
            if (inst != null) {
                UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
                if (inst.getModifier(uuid) == null) {
                    inst.addTransientModifier(
                            new AttributeModifier(uuid, name, amount, AttributeModifier.Operation.MULTIPLY_TOTAL));
                }
            }
        }
    }

    private static void removeAttributeBonus(Player player, String attrId, String name) {
        Attribute attr = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.tryParse(attrId));
        if (attr != null) {
            AttributeInstance inst = player.getAttribute(attr);
            if (inst != null) {
                UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
                if (inst.getModifier(uuid) != null) {
                    inst.removeModifier(uuid);
                }
            }
        }
    }

    private static void handleLotaCritRoll(Event event) {
        Player player = extractPlayer(event);
        LivingEntity target = extractTarget(event);
        if (player == null || target == null || player.level().isClientSide) {
            return;
        }

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            handler.findFirstCurio(ModItems.STEALTH_ASSASSIN_GLOVE.get()).ifPresent(slot -> {
                if (player.getHealth() >= player.getMaxHealth() && target.getHealth() >= target.getMaxHealth()) {
                    invokeDoubleMethod(event, "addCriticalChance", 25.0D);
                    invokeDoubleMethod(event, "addCriticalDamagePercent", 35.0D);
                }
            });

            handler.findFirstCurio(ModItems.MOON_FOLLOWERS_RING.get()).ifPresent(slot -> {
                if (!player.level().isDay() && player.getHealth() >= player.getMaxHealth()) {
                    invokeDoubleMethod(event, "addCriticalChance", 40.0D);
                    invokeDoubleMethod(event, "addCriticalDamagePercent", 15.0D);
                }
            });

            handler.findFirstCurio(ModItems.PERFECTION_RING.get()).ifPresent(slot -> {
                ItemStack stack = slot.stack();
                int perfection = stack.getOrCreateTag().getInt("LotaPerfection");
                if (perfection >= 3) {
                    invokeBooleanMethod(event, "setGuaranteed", true);
                    invokeDoubleMethod(event, "addCriticalDamagePercent", 20.0D);
                    stack.getOrCreateTag().putInt("LotaPerfection", 0);
                    stack.getOrCreateTag().putBoolean("LotaPerfectionTriggered", true);
                }
            });
        });
    }

    private static void handleLotaCritTriggered(Event event) {
        Player player = extractPlayer(event);
        LivingEntity target = extractTarget(event);
        if (player == null || player.level().isClientSide) {
            return;
        }

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            handler.findFirstCurio(ModItems.CRITICAL_ELEVATION_RING.get()).ifPresent(slot ->
                    player.addEffect(new MobEffectInstance(ModEffects.CRITICAL_ELEVATION.get(), 100, 0))
            );

            handler.findFirstCurio(ModItems.EARTHQUAKE_RING.get()).ifPresent(slot -> {
                if (player instanceof ServerPlayer serverPlayer
                        && !player.getCooldowns().isOnCooldown(slot.stack().getItem())) {
                    serverPlayer.server.getCommands().performPrefixedCommand(
                            serverPlayer.createCommandSourceStack().withSuppressedOutput(),
                            "invincible groundSlam @p 7 false false true"
                    );
                    player.getCooldowns().addCooldown(slot.stack().getItem(), 600);
                }
            });

            handler.findFirstCurio(ModItems.NEXAGON_RING.get()).ifPresent(slot -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.server.getCommands().performPrefixedCommand(
                            serverPlayer.createCommandSourceStack().withSuppressedOutput(),
                            "invincible @p setStack 1"
                    );
                }
            });

            handler.findFirstCurio(ModItems.BLOOD_MONARCH_RING.get()).ifPresent(slot -> {
                if (target != null && RANDOM.nextFloat() < 0.25F) {
                    applyEffect(target, "dungeons_and_combat:fatal_oath", 60, 0);
                }
            });
        });
    }

    private static Player extractPlayer(Event event) {
        Object value = invokeMethod(event, "getPlayer");
        return value instanceof Player player ? player : null;
    }

    private static LivingEntity extractTarget(Event event) {
        Object value = invokeMethod(event, "getTarget");
        return value instanceof LivingEntity livingEntity ? livingEntity : null;
    }

    private static void invokeDoubleMethod(Event event, String methodName, double value) {
        try {
            event.getClass().getMethod(methodName, double.class).invoke(event, value);
        } catch (ReflectiveOperationException ignored) {
        }
    }

    private static void invokeBooleanMethod(Event event, String methodName, boolean value) {
        try {
            event.getClass().getMethod(methodName, boolean.class).invoke(event, value);
        } catch (ReflectiveOperationException ignored) {
        }
    }

    private static Object invokeMethod(Event event, String methodName) {
        try {
            return event.getClass().getMethod(methodName).invoke(event);
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

}

