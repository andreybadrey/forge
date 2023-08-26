package com.bodryak.gmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTabs {
    public static final CreativeModeTab ADMIN = new CreativeModeTab("admin") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND);
        }
    };
    public static final CreativeModeTab WEAPON_DIST = new CreativeModeTab("weapon_dist") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.BOW);
        }
    };
    public static final CreativeModeTab GEMS = new CreativeModeTab("gems") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.LAPIS_LAZULI);
        }
    };

}
