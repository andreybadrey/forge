package com.bodryak.gmod;

import com.bodryak.gmod.gui.gui.init.Menus;
import com.bodryak.gmod.item.ModItems;
import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.util.ModItemProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GmodMod.MODID)
public class GmodMod {
    public static final String MODID = "gmod";

    public GmodMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);


        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

        Menus.REGISTRY.register(bus);


    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });

        //NWMess.register();
        ModMessages.register();
    }
}
