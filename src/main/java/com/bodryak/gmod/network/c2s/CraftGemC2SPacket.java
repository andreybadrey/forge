package com.bodryak.gmod.network.c2s;

import com.bodryak.gmod.item.Gem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class CraftGemC2SPacket {
    private ItemStack target;
    private int amount;

    public CraftGemC2SPacket(ItemStack target, int amount) {
        this.target = target;
        this.amount = amount;
    }
    public CraftGemC2SPacket(FriendlyByteBuf buf) {
        target = buf.readItem();
        amount = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(target);
        buf.writeInt(amount);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            Gem item =(Gem) this.target.getItem();
            Item required_item = item.required_item;
            int required_amo = item.required_amount;
            int item_count = 0;
            AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
            player.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
            if (_iitemhandlerref.get() != null) {
                for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
                    ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
                    if (required_item == itemstackiterator.getItem()) {
                        item_count = item_count + (itemstackiterator).getCount();
                    }
                }
            }
            if ((required_amo * this.amount) <= item_count) {
                ItemStack _stktoremove = new ItemStack(required_item);
                player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (required_amo * this.amount), player.inventoryMenu.getCraftSlots());
                ItemStack _setstack = new ItemStack(this.target.getItem());
                _setstack.setCount(this.amount);
                ItemHandlerHelper.giveItemToPlayer(player, _setstack);
            }else {
                System.out.println("Недостаточно предметов");
            }

        });
        return true;
    }
}
