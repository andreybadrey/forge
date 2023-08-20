package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.playerdata.GetBalanceS2CPacket;
import com.bodryak.gmod.network.s2c.playerdata.PDSyncS2CPacket;
import com.bodryak.gmod.util.mysql.ModDBHandler;
import com.bodryak.gmod.variables.server.ProviderPDS;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GetBalanceC2SPacket {
    public GetBalanceC2SPacket() {

    }
    public GetBalanceC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //System.out.println("Запрос Баланса");
            ModDBHandler handler = new ModDBHandler();
            try {
                //System.out.println("Готов ответ: " + handler.getBalance(context.getSender().getName().getString()));
                ModMessages.sendToPlayer(new GetBalanceS2CPacket(handler.getBalance(context.getSender().getName().getString())), context.getSender());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        return true;
    }
}
