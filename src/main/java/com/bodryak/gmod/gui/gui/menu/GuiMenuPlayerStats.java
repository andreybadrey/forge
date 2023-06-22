package com.bodryak.gmod.gui.gui.menu;

import com.bodryak.gmod.gui.gui.init.Menus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GuiMenuPlayerStats extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    private final Map<Integer, Slot> customSlot = new HashMap<>();

    public GuiMenuPlayerStats(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(Menus.GUI_PLAYER_STATS.get(), id);

    }

    @Override
    public Map<Integer, Slot> get() {
        return customSlot;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
