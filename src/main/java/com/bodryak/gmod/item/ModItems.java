package com.bodryak.gmod.item;

import com.bodryak.gmod.GmodMod;
import com.bodryak.gmod.item.weapone.distanse.TestBow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GmodMod.MODID);

    public static final RegistryObject<Item> UNI_ITEM = ITEMS.register("uni_item",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.ADMIN)));

    public static final RegistryObject<Item> TOPAZ_1 = ITEMS.register("topaz_1",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_2 = ITEMS.register("topaz_2",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_3 = ITEMS.register("topaz_3",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_4 = ITEMS.register("topaz_4",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_5 = ITEMS.register("topaz_5",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_6 = ITEMS.register("topaz_6",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_7 = ITEMS.register("topaz_7",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_8 = ITEMS.register("topaz_8",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_9 = ITEMS.register("topaz_9",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_10 = ITEMS.register("topaz_10",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_11 = ITEMS.register("topaz_11",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_12 = ITEMS.register("topaz_12",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));
    public static final RegistryObject<Item> TOPAZ_13 = ITEMS.register("topaz_13",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.GEMS)));

    public static final RegistryObject<Item> TEST_BOW = ITEMS.register("test_bow", () -> new TestBow(new Item.Properties().tab(ModCreativeTabs.WEAPON_DIST).stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
