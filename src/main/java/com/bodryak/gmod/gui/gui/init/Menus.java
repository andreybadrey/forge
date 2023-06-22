package com.bodryak.gmod.gui.gui.init;

import com.bodryak.gmod.GmodMod;
import com.bodryak.gmod.gui.gui.menu.GuiMenuPlayerStats;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Menus {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, GmodMod.MODID);
    public static final RegistryObject<MenuType<GuiMenuPlayerStats>> GUI_PLAYER_STATS = REGISTRY.register("gui_player_stats", () -> IForgeMenuType.create(GuiMenuPlayerStats::new));
}
