package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.gui.gui.menu.GuiMenuPlayerStats;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.Objects;
import java.util.function.Supplier;

public class OpenPlayerStatsGuiC2SPacket {
    public OpenPlayerStatsGuiC2SPacket() {

    }
    public OpenPlayerStatsGuiC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender());
        });
        return true;
    }

    public static void pressAction(Player entity) {
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();


            if (entity instanceof ServerPlayer _ent) {
                BlockPos _bpos = new BlockPos(x, y, z);
                NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("PlayerStatsGui");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new GuiMenuPlayerStats(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }


    }

}
