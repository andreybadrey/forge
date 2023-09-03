package com.bodryak.gmod.network;

import com.bodryak.gmod.GmodMod;
import com.bodryak.gmod.network.c2s.*;
import com.bodryak.gmod.network.s2c.mobdata.MDSyncS2CPacket;
import com.bodryak.gmod.network.s2c.playerdata.GetBalanceS2CPacket;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(GmodMod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        //to client packs
        net.messageBuilder(PDSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PDSyncS2CPacket::new)
                .encoder(PDSyncS2CPacket::toBytes)
                .consumerMainThread(PDSyncS2CPacket::handle)
                .add();

        net.messageBuilder(MDSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(MDSyncS2CPacket::new)
                .encoder(MDSyncS2CPacket::toBytes)
                .consumerMainThread(MDSyncS2CPacket::handle)
                .add();

        net.messageBuilder(GetBalanceS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(GetBalanceS2CPacket::new)
                        .encoder(GetBalanceS2CPacket::toBytes)
                                .consumerMainThread(GetBalanceS2CPacket::handle)
                                        .add();

        //to server packs
        net.messageBuilder(OpenPlayerStatsGuiC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenPlayerStatsGuiC2SPacket::new)
                .encoder(OpenPlayerStatsGuiC2SPacket::toBytes)
                .consumerMainThread(OpenPlayerStatsGuiC2SPacket::handle)
                .add();
        net.messageBuilder(PDSStaminaUpC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PDSStaminaUpC2SPacket::new)
                .encoder(PDSStaminaUpC2SPacket::toBytes)
                .consumerMainThread(PDSStaminaUpC2SPacket::handle)
                .add();
        net.messageBuilder(PDSStrengthUpC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PDSStrengthUpC2SPacket::new)
                .encoder(PDSStrengthUpC2SPacket::toBytes)
                .consumerMainThread(PDSStrengthUpC2SPacket::handle)
                .add();
        net.messageBuilder(PDSDexterityUpC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PDSDexterityUpC2SPacket::new)
                .encoder(PDSDexterityUpC2SPacket::toBytes)
                .consumerMainThread(PDSDexterityUpC2SPacket::handle)
                .add();
        net.messageBuilder(PDSIntellectUpC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PDSIntellectUpC2SPacket::new)
                .encoder(PDSIntellectUpC2SPacket::toBytes)
                .consumerMainThread(PDSIntellectUpC2SPacket::handle)
                .add();
        net.messageBuilder(MobSyncC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(MobSyncC2SPacket::new)
                .encoder(MobSyncC2SPacket::toBytes)
                .consumerMainThread(MobSyncC2SPacket::handle)
                .add();
        net.messageBuilder(OpenGameShopGuiC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(OpenGameShopGuiC2SPacket::new)
                .encoder(OpenGameShopGuiC2SPacket::toBytes)
                .consumerMainThread(OpenGameShopGuiC2SPacket::handle)
                .add();
        net.messageBuilder(GetBalanceC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(GetBalanceC2SPacket::new)
                .encoder(GetBalanceC2SPacket::toBytes)
                .consumerMainThread(GetBalanceC2SPacket::handle)
                .add();
        net.messageBuilder(ByeAppleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ByeAppleC2SPacket::new)
                .encoder(ByeAppleC2SPacket::toBytes)
                .consumerMainThread(ByeAppleC2SPacket::handle)
                .add();
        net.messageBuilder(CraftGemC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CraftGemC2SPacket::new)
                .encoder(CraftGemC2SPacket::toBytes)
                .consumerMainThread(CraftGemC2SPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToTracking(MSG message, LivingEntity track) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> track), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}