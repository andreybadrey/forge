package com.bodryak.gmod.event;

import com.bodryak.gmod.util.mysql.ModDBHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.sql.SQLException;

@Mod.EventBusSubscriber
public class JoinLevel {
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event){
            if(!event.getLevel().isClientSide()){
                if(event.getEntity() instanceof ServerPlayer player){
                    String name = player.getName().getString();
                    ModDBHandler handler = new ModDBHandler();
                    try {
                        handler.registerPlayer(name);
                        handler.setupQuestCoreForPlayer(name);
                        if(!handler.checkQuest(name, 1)){
                            handler.setQuest(name, 1, "new");
                            player.sendSystemMessage(Component.literal("Получено задание: §6Начало пути"));
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

    }
}
