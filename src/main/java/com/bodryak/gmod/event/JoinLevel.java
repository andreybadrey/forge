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
    public static EntityJoinLevelEvent val;
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event){
            if(!event.getLevel().isClientSide()){
                if(event.getEntity() instanceof ServerPlayer player){
                    val = event;
                    RegisterPlayer registerPlayer = new RegisterPlayer();
                    registerPlayer.start();
                    SetupQuestCoreForPlayer setupQuestCoreForPlayer = new SetupQuestCoreForPlayer();
                    setupQuestCoreForPlayer.start();
                }
            }

    }
}
class RegisterPlayer extends Thread {
    @Override
    public void run() {
        ModDBHandler handler = new ModDBHandler();
        try {
            handler.registerPlayer(JoinLevel.val.getEntity().getName().getString());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
class SetupQuestCoreForPlayer extends Thread {
    @Override
    public void run() {
        ModDBHandler handler = new ModDBHandler();
        try {
            handler.setupQuestCoreForPlayer(JoinLevel.val.getEntity().getName().getString());
            if(!handler.checkQuest(JoinLevel.val.getEntity().getName().getString(), 1)){
                handler.setQuest(JoinLevel.val.getEntity().getName().getString(), 1, "new");
                JoinLevel.val.getEntity().sendSystemMessage(Component.literal("Получено задание: §6Начало пути"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
