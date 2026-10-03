package com.lota.LotaArtifacts;

import com.lota.LotaArtifacts.curios.CuriosSlotRegistry;
import com.lota.LotaArtifacts.item.ModCreativeTabs;
import com.lota.LotaArtifacts.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.ModList;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;

@Mod("lotaartifacts")
public class LotaArtifacts {

    public static final String MOD_ID = "lotaartifacts";

    private static final Logger LOGGER = LogUtils.getLogger();

    public LotaArtifacts() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        ModCreativeTabs.register(modEventBus);

        com.lota.LotaArtifacts.effect.ModEffects.register(modEventBus);

        modEventBus.addListener(CuriosSlotRegistry::registerSlots);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Lota Artifacts is loading!");
        if (ModList.get().isLoaded(CuriosApi.MODID)) {
             LOGGER.info("Curios API detected and used!");
        }
        if (ModList.get().isLoaded("irons_spellbooks")) {
             LOGGER.info("Iron's Spells 'n Spellbooks detected and used!");
        }
    }
}