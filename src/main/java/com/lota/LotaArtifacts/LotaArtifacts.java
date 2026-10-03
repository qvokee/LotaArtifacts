package com.lota.LotaArtifacts;

import com.lota.LotaArtifacts.curios.CuriosSlotRegistry;
import com.lota.LotaArtifacts.item.ModCreativeTabs;
import com.lota.LotaArtifacts.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("lotaartifacts")
public class LotaArtifacts {

     public static final String MOD_ID = "lotaartifacts";

     public LotaArtifacts() {
          IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

          ModItems.register(modEventBus);

          ModCreativeTabs.register(modEventBus);

          com.lota.LotaArtifacts.effect.ModEffects.register(modEventBus);

          modEventBus.addListener(CuriosSlotRegistry::registerSlots);

          MinecraftForge.EVENT_BUS.register(this);
          MinecraftForge.EVENT_BUS.register(com.lota.LotaArtifacts.event.QuiverEventHandler.class);
          MinecraftForge.EVENT_BUS.register(com.lota.LotaArtifacts.event.NewArtifactsEventHandler.class);
     }
}
