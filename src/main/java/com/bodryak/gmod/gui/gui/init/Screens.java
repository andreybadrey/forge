package com.bodryak.gmod.gui.gui.init;

import com.bodryak.gmod.gui.gui.screen.GuiScreenGameShop;
import com.bodryak.gmod.gui.gui.screen.GuiScreenPlayerStats;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Screens {
    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Menus.GUI_PLAYER_STATS.get(), GuiScreenPlayerStats::new);
            MenuScreens.register(Menus.GUI_SHOP.get(), GuiScreenGameShop::new);
        });
    }
}
