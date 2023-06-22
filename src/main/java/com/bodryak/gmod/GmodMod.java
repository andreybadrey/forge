package com.bodryak.gmod;

import com.bodryak.gmod.gui.gui.init.Menus;
import com.bodryak.gmod.network.ModMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GmodMod.MODID)
public class GmodMod {
    public static final String MODID = "gmod";

    public GmodMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        Menus.REGISTRY.register(bus);


    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });

        //NWMess.register();
        ModMessages.register();
    }
}
