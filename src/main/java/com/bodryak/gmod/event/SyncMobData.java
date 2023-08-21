package com.bodryak.gmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
// TODO: 21.08.2023 delete class 
public class SyncMobData {
    @SubscribeEvent
    public static void syncRenderMob(RenderLivingEvent.Pre event) {
        //System.out.println("рендер");
    }
}
