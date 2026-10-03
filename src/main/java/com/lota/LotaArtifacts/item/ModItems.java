package com.lota.LotaArtifacts.item;

import com.lota.LotaArtifacts.item.curios.ExplosiveFlameRing;
import com.lota.LotaArtifacts.item.curios.ThunderStrengthRing;
import com.lota.LotaArtifacts.item.curios.ManaRestorationNecklace;
import com.lota.LotaArtifacts.item.curios.ArmorPiercingGlove;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "lotaartifacts");

    public static final RegistryObject<Item> EXPLOSIVE_FLAME_RING = ITEMS.register("explosive_flame_ring",
            () -> new ExplosiveFlameRing(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .fireResistant()
            ));

    public static final RegistryObject<Item> THUNDER_STRENGTH_RING = ITEMS.register("thunder_strength_ring",
            () -> new ThunderStrengthRing(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .fireResistant()
            ));

    public static final RegistryObject<Item> MANA_RESTORATION_NECKLACE = ITEMS.register("mana_restoration_necklace",
            () -> new ManaRestorationNecklace(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .fireResistant()
            ));

    public static final RegistryObject<Item> ARMOR_PIERCING_GLOVE = ITEMS.register("armor_piercing_glove",
            () -> new ArmorPiercingGlove(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .fireResistant()
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
