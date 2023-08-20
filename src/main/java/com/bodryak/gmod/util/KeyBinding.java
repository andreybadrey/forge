package com.bodryak.gmod.util;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.c2s.OpenGameShopGuiC2SPacket;
import com.bodryak.gmod.network.c2s.OpenPlayerStatsGuiC2SPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class KeyBinding {
    public static final KeyMapping KEY_GUI_PLAYER_STATS = new KeyMapping("key.gmod.key_gui_player_stats", GLFW.GLFW_KEY_C, "key.categories.gmod_gui"){
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if(isDownOld != isDown && isDown){
                ModMessages.sendToServer(new OpenPlayerStatsGuiC2SPacket());
                //OpenPlayerStatsGuiC2SPacket.pressAction(Minecraft.getInstance().player);
                //GmodMod.PACKET_HANDLER.sendToServer(new KeyGuiPlayerStatsMessage(0, 0));
                //KeyGuiPlayerStatsMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };

    public static final KeyMapping KEY_GUI_SHOP = new KeyMapping("key.gmod.key_gui_shop", GLFW.GLFW_KEY_O, "key.categories.gmod_gui"){
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if(isDownOld != isDown && isDown){
                ModMessages.sendToServer(new OpenGameShopGuiC2SPacket());
                //OpenPlayerStatsGuiC2SPacket.pressAction(Minecraft.getInstance().player);
                //GmodMod.PACKET_HANDLER.sendToServer(new KeyGuiPlayerStatsMessage(0, 0));
                //KeyGuiPlayerStatsMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event){
        event.register(KEY_GUI_PLAYER_STATS);
        event.register(KEY_GUI_SHOP);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event){
            if(Minecraft.getInstance().screen == null){
                KEY_GUI_PLAYER_STATS.consumeClick();
                KEY_GUI_SHOP.consumeClick();
            }
        }
    }
}
