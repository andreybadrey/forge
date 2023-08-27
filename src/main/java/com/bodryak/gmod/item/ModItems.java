package com.bodryak.gmod.item;

import com.bodryak.gmod.GmodMod;
import com.bodryak.gmod.item.weapone.distanse.TestBow;
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
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().byePrise(500)
                            .sellPrise(100)
                            .inOutPrise(100, 100)
                            .boost(5, 10, 1, 1)
                            .rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .lvl(1)));
    public static final RegistryObject<Item> TOPAZ_2 = ITEMS.register("topaz_2",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().byePrise(2000).sellPrise(200).inOutPrise(200, 200)
                            .boost(10, 20, 2, 1)
                            .rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .lvl(2)));
    public static final RegistryObject<Item> TOPAZ_3 = ITEMS.register("topaz_3",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(3).byePrise(3500).sellPrise(350).inOutPrise(500, 500).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(15, 30, 3, 3)));
    public static final RegistryObject<Item> TOPAZ_4 = ITEMS.register("topaz_4",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(4).byePrise(5500).sellPrise(550).inOutPrise(1000,1000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(20,40,4,4)));
    public static final RegistryObject<Item> TOPAZ_5 = ITEMS.register("topaz_5",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(5).byePrise(57143).sellPrise(800).inOutPrise(2000, 2000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(26, 50, 5, 5)));
    public static final RegistryObject<Item> TOPAZ_6 = ITEMS.register("topaz_6",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(6).byePrise(11000).sellPrise(1100).inOutPrise(5000, 5000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(33, 65, 6, 6)));
    public static final RegistryObject<Item> TOPAZ_7 = ITEMS.register("topaz_7",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(7).byePrise(18000).sellPrise(1800).inOutPrise(10000, 10000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(41, 80, 7, 7)));
    public static final RegistryObject<Item> TOPAZ_8 = ITEMS.register("topaz_8",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(8).byePrise(25000).sellPrise(2500).inOutPrise(20000, 20000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(53, 100, 8, 8)));
    public static final RegistryObject<Item> TOPAZ_9 = ITEMS.register("topaz_9",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(9).byePrise(36000).sellPrise(3600).inOutPrise(50000, 50000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(66, 125, 9, 9)));
    public static final RegistryObject<Item> TOPAZ_10 = ITEMS.register("topaz_10",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(10).byePrise(50000).sellPrise(5000).inOutPrise(100000, 100000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(85, 150, 10, 10)));
    public static final RegistryObject<Item> TOPAZ_11 = ITEMS.register("topaz_11",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(11).byePrise(200000).sellPrise(20000).inOutPrise(500000, 500000).rarity(Gem.Rarity.GOLD)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(125, 225, 11, 11)));
    public static final RegistryObject<Item> TOPAZ_12 = ITEMS.register("topaz_12",
            () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(12).byePrise(400000).sellPrise(40000).inOutPrise(1000000, 1000000).rarity(Gem.Rarity.GOLD)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST)
                            .boost(170, 300, 12, 12)));

    public static final RegistryObject<Item> TEST_BOW = ITEMS.register("test_bow", () -> new TestBow(new Item.Properties().tab(ModCreativeTabs.WEAPON_DIST).stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
