package com.bodryak.gmod.network.s2c.playerdata;

import com.bodryak.gmod.gui.gui.screen.GuiScreenGameShop;
import com.bodryak.gmod.variables.client.PDC;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

public class GetBalanceS2CPacket {
    private final int balance ;

    public GetBalanceS2CPacket(int balance) {
        this.balance = balance;
    }

    public GetBalanceS2CPacket(FriendlyByteBuf buf) {
        this.balance = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.balance);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            //System.out.println("Получен ответ: " + this.balance);
            //System.out.println(Minecraft.getInstance().screen.getTitle().getString());
            GuiScreenGameShop.balance = this.balance;
        });
        return true;
    }
}