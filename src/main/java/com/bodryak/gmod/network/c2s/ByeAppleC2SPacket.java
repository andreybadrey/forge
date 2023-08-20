package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.s2c.playerdata.GetBalanceS2CPacket;
import com.bodryak.gmod.util.mysql.ModDBHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.NetworkEvent;

import java.sql.SQLException;
import java.util.function.Supplier;

public class ByeAppleC2SPacket {
    public static NetworkEvent.Context val;
    public ByeAppleC2SPacket() {

    }
    public ByeAppleC2SPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        //sup = (Supplier<NetworkEvent.Context>) context;
        context.enqueueWork(() -> {
            val = context;
            MyThread myThread = new MyThread();
            myThread.start();




            //System.out.println("Запрос Баланса");
            //            ModDBHandler handler = new ModDBHandler();
            //            int balance;
            //            try {
            //                balance = handler.getBalance(context.getSender().getName().getString());
            //                if(balance > 120){
            //                    ItemStack itemStack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE);
            //                    itemStack.setCount(1);
            //                    ItemHandlerHelper.giveItemToPlayer(context.getSender(), itemStack);
            //                    balance -= 120;
            //                    handler.dropBalance(context.getSender().getName().getString(), balance);
            //                    ModMessages.sendToPlayer(new GetBalanceS2CPacket(balance), context.getSender());
            //                    context.getSender().sendSystemMessage(Component.literal("Выполнена покупка в магазине"), false);
            //                }
            //                //System.out.println("Готов ответ: " + handler.getBalance(context.getSender().getName().getString()));
            //                //ModMessages.sendToPlayer(new GetBalanceS2CPacket(handler.getBalance(context.getSender().getName().getString())), context.getSender());
            //            } catch (SQLException e) {
            //                throw new RuntimeException(e);
            //            } catch (ClassNotFoundException e) {
            //                throw new RuntimeException(e);
            //            }
            //
        });
        return true;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Новый поток");
        System.out.println("Прогрузка евента в новый поток");
        NetworkEvent.Context context = ByeAppleC2SPacket.val;
        System.out.println("Прогружен игрок " + context.getSender().getName().getString());
        System.out.println("Запрос баланса");
        ModDBHandler handler = new ModDBHandler();
        try {
            int balance = handler.getBalance(context.getSender().getName().getString());
            System.out.println("Баланс: " + balance);
            if(balance > 120){
                GiveItem giveItem = new GiveItem();
                DropBalance dropBalance = new DropBalance();
                giveItem.start();
                dropBalance.start();
            }else {
                context.getSender().sendSystemMessage(Component.literal("Недостаточно денег"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
class GiveItem extends Thread {
    @Override
    public void run() {
        NetworkEvent.Context context = ByeAppleC2SPacket.val;
        ItemStack itemStack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE);
        itemStack.setCount(1);
        ItemHandlerHelper.giveItemToPlayer(context.getSender(), itemStack);
        System.out.println("Поток выдачи предмета");
    }
}
class DropBalance extends Thread {
    @Override
    public void run() {
        NetworkEvent.Context context = ByeAppleC2SPacket.val;
        ModDBHandler handler = new ModDBHandler();
        try {
            handler.dropBalance(context.getSender().getName().getString(), 120);
            System.out.println("Поток смены баланса");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}