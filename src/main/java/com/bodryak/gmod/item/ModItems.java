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

    public static final RegistryObject<Item> UNI_ITEM = ITEMS.register("uni_item", () -> new Item(new Item.Properties().tab(ModCreativeTabs.ADMIN)));

//Топазы
    public static final RegistryObject<Item> TOPAZ_1 = ITEMS.register("topaz_1", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().byePrise(500).sellPrise(100).inOutPrise(100, 100).boost(5, 10, 1, 1)
                            .rarity(Gem.Rarity.COMMON).effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).lvl(1)
                            .required(null, 0)));

    public static final RegistryObject<Item> TOPAZ_2 = ITEMS.register("topaz_2",() -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().byePrise(2000).sellPrise(200).inOutPrise(200, 200).boost(10, 20, 2, 2).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).lvl(2)
                            .required(TOPAZ_1.get(), 3)));

    public static final RegistryObject<Item> TOPAZ_3 = ITEMS.register("topaz_3", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(3).byePrise(3500).sellPrise(350).inOutPrise(500, 500).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(15, 30, 3, 3)
                            .required(TOPAZ_2.get(), 3)));

    public static final RegistryObject<Item> TOPAZ_4 = ITEMS.register("topaz_4", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(4).byePrise(5500).sellPrise(550).inOutPrise(1000,1000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(20,40,4,4)
                            .required(TOPAZ_3.get(), 3)));

    public static final RegistryObject<Item> TOPAZ_5 = ITEMS.register("topaz_5",() -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(5).byePrise(57143).sellPrise(800).inOutPrise(2000, 2000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(26, 50, 5, 5)
                            .required(TOPAZ_4.get(), 4)));

    public static final RegistryObject<Item> TOPAZ_6 = ITEMS.register("topaz_6", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(6).byePrise(11000).sellPrise(1100).inOutPrise(5000, 5000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(33, 65, 6, 6)
                            .required(TOPAZ_5.get(), 4)));

    public static final RegistryObject<Item> TOPAZ_7 = ITEMS.register("topaz_7", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(7).byePrise(18000).sellPrise(1800).inOutPrise(10000, 10000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(41, 80, 7, 7)
                            .required(TOPAZ_6.get(), 4)));

    public static final RegistryObject<Item> TOPAZ_8 = ITEMS.register("topaz_8", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(8).byePrise(25000).sellPrise(2500).inOutPrise(20000, 20000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(53, 100, 8, 8)
                            .required(TOPAZ_7.get(), 5)));

    public static final RegistryObject<Item> TOPAZ_9 = ITEMS.register("topaz_9", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(9).byePrise(36000).sellPrise(3600).inOutPrise(50000, 50000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(66, 125, 9, 9)
                            .required(TOPAZ_8.get(), 5)));

    public static final RegistryObject<Item> TOPAZ_10 = ITEMS.register("topaz_10", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(10).byePrise(50000).sellPrise(5000).inOutPrise(100000, 100000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(85, 150, 10, 10)
                            .required(TOPAZ_9.get(), 5)));

    public static final RegistryObject<Item> TOPAZ_11 = ITEMS.register("topaz_11", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(11).byePrise(200000).sellPrise(20000).inOutPrise(500000, 500000).rarity(Gem.Rarity.GOLD)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(125, 225, 11, 11)
                            .required(TOPAZ_10.get(), 6)));

    public static final RegistryObject<Item> TOPAZ_12 = ITEMS.register("topaz_12", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(12).byePrise(400000).sellPrise(40000).inOutPrise(1000000, 1000000).rarity(Gem.Rarity.GOLD)
                            .effects(Gem.Effect.EVASION, Gem.Effect.ACCURACY, Gem.Effect.CHARGE, Gem.Effect.CAST).boost(170, 300, 12, 12)
                            .required(TOPAZ_11.get(), 6)));
//Топазы

//Рубины
    public static final RegistryObject<Item> RUBIN_1 = ITEMS.register("rubin_1", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(1).byePrise(500).sellPrise(100).inOutPrise(100, 100).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(5, 3, 1, 1)
                            .required(null, 0)));

    public static final RegistryObject<Item> RUBIN_2 = ITEMS.register("rubin_2", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(2).byePrise(2000).sellPrise(200).inOutPrise(200, 200).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(10, 6, 2, 2)
                            .required(RUBIN_1.get(), 3)));

    public static final RegistryObject<Item> RUBIN_3 = ITEMS.register("rubin_3", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(3).byePrise(3500).sellPrise(350).inOutPrise(500, 500).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(15, 9, 3, 3)
                            .required(RUBIN_2.get(), 3)));

    public static final RegistryObject<Item> RUBIN_4 = ITEMS.register("rubin_4", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(4).byePrise(5500).sellPrise(550).inOutPrise(1000, 1000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(20, 12, 4, 4)
                            .required(RUBIN_3.get(), 3)));

    public static final RegistryObject<Item> RUBIN_5 = ITEMS.register("rubin_5", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(5).byePrise(57143).sellPrise(800).inOutPrise(2000, 2000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(26, 16, 5, 5)
                            .required(RUBIN_4.get(), 4)));

    public static final RegistryObject<Item> RUBIN_6 = ITEMS.register("rubin_6", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(6).byePrise(11000).sellPrise(1100).inOutPrise(5000, 5000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(33, 20, 6, 6)
                            .required(RUBIN_5.get(), 4)));

    public static final RegistryObject<Item> RUBIN_7 = ITEMS.register("rubin_7", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(7).byePrise(18000).sellPrise(1800).inOutPrise(10000, 10000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(41, 25, 7, 7)
                            .required(RUBIN_6.get(), 4)));

    public static final RegistryObject<Item> RUBIN_8 = ITEMS.register("rubin_8", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(8).byePrise(25000).sellPrise(2500).inOutPrise(20000, 20000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(53, 32, 8, 8)
                            .required(RUBIN_7.get(), 5)));

    public static final RegistryObject<Item> RUBIN_9 = ITEMS.register("rubin_9", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(9).byePrise(36000).sellPrise(3600).inOutPrise(50000, 50000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(66, 40, 9, 9)
                            .required(RUBIN_8.get(), 5)));

    public static final RegistryObject<Item> RUBIN_10 = ITEMS.register("rubin_10", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(10).byePrise(50000).sellPrise(5000).inOutPrise(100000, 100000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(85, 50, 10, 10)
                            .required(RUBIN_9.get(), 5)));

    public static final RegistryObject<Item> RUBIN_11 = ITEMS.register("rubin_11", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(11).byePrise(200000).sellPrise(20000).inOutPrise(500000, 500000).rarity(Gem.Rarity.GOLD)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(125, 75, 11, 11)
                            .required(RUBIN_10.get(), 6)));

    public static final RegistryObject<Item> RUBIN_12 = ITEMS.register("rubin_12", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(12).byePrise(200000).sellPrise(20000).inOutPrise(500000, 500000).rarity(Gem.Rarity.GOLD)
                            .effects(Gem.Effect.PHIS_DEF, Gem.Effect.PHIS_ATTACK, Gem.Effect.PHIS_ATTACK, Gem.Effect.DISTANCE).boost(170, 100, 12, 12)
                            .required(RUBIN_11.get(), 6)));
//Рубины

//Янтари
    public static final RegistryObject<Item> AMBER_1 = ITEMS.register("amber_1", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(1).byePrise(500).sellPrise(100).inOutPrise(100, 100).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(5, 5, 5, 5)
                            .required(null, 0)));

    public static final RegistryObject<Item> AMBER_2 = ITEMS.register("amber_2", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(2).byePrise(2000).sellPrise(200).inOutPrise(200, 200).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(10, 10, 10, 10)
                            .required(AMBER_1.get(), 3)));

    public static final RegistryObject<Item> AMBER_3 = ITEMS.register("amber_3", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(3).byePrise(3500).sellPrise(350).inOutPrise(500, 500).rarity(Gem.Rarity.COMMON)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(15, 15, 15, 15)
                            .required(AMBER_2.get(), 3)));

    public static final RegistryObject<Item> AMBER_4 = ITEMS.register("amber_4", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(4).byePrise(5500).sellPrise(550).inOutPrise(1000, 1000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(20, 20, 20, 20)
                            .required(AMBER_3.get(), 3)));

    public static final RegistryObject<Item> AMBER_5 = ITEMS.register("amber_5", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(5).byePrise(57143).sellPrise(800).inOutPrise(2000, 2000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(25, 25, 25, 25)
                            .required(AMBER_4.get(), 4)));

    public static final RegistryObject<Item> AMBER_6 = ITEMS.register("amber_6", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(6).byePrise(11000).sellPrise(1100).inOutPrise(5000, 5000).rarity(Gem.Rarity.RARE)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(32, 32, 32, 32)
                            .required(AMBER_5.get(), 4)));

    public static final RegistryObject<Item> AMBER_7 = ITEMS.register("amber_7", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(7).byePrise(18000).sellPrise(1800).inOutPrise(10000, 10000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(40, 40, 40, 40)
                            .required(AMBER_6.get(), 4)));

    public static final RegistryObject<Item> AMBER_8 = ITEMS.register("amber_8", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
                    new Gem.GemProperties().lvl(8).byePrise(25000).sellPrise(2500).inOutPrise(20000, 20000).rarity(Gem.Rarity.EPIC)
                            .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(50, 50, 50, 50)
                            .required(AMBER_7.get(), 5)));

    public static final RegistryObject<Item> AMBER_9 = ITEMS.register("amber_9", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(9).byePrise(36000).sellPrise(3600).inOutPrise(50000, 50000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(62, 62, 62, 62)
                    .required(AMBER_8.get(), 5)));

    public static final RegistryObject<Item> AMBER_10 = ITEMS.register("amber_10", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(10).byePrise(50000).sellPrise(5000).inOutPrise(100000, 100000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(75, 75, 75, 75)
                    .required(AMBER_9.get(), 5)));

    public static final RegistryObject<Item> AMBER_11 = ITEMS.register("amber_11", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(11).byePrise(190476190).sellPrise(10000).inOutPrise(200000, 200000).rarity(Gem.Rarity.GOLD)
                    .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(115, 115, 115, 115)
                    .required(AMBER_10.get(), 6)));

    public static final RegistryObject<Item> AMBER_12 = ITEMS.register("amber_12", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(12).byePrise(200000).sellPrise(20000).inOutPrise(500000, 500000).rarity(Gem.Rarity.GOLD)
                    .effects(Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP, Gem.Effect.HP).boost(150, 150, 150, 150)
                    .required(AMBER_11.get(), 6)));
//Янтари

//Сапфиры
public static final RegistryObject<Item> SAPPHIRE_1 = ITEMS.register("sapphire_1", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
        new Gem.GemProperties().lvl(1).byePrise(500).sellPrise(100).inOutPrise(100, 100).rarity(Gem.Rarity.COMMON)
                .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(4, 3, 1, 1)
                .required(null, 0)));

    public static final RegistryObject<Item> SAPPHIRE_2 = ITEMS.register("sapphire_2", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(2).byePrise(2000).sellPrise(200).inOutPrise(200, 200).rarity(Gem.Rarity.COMMON)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(8, 6, 2, 2)
                    .required(SAPPHIRE_1.get(), 3)));

    public static final RegistryObject<Item> SAPPHIRE_3 = ITEMS.register("sapphire_3", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(3).byePrise(3500).sellPrise(350).inOutPrise(500, 500).rarity(Gem.Rarity.COMMON)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(12, 9, 3, 3)
                    .required(SAPPHIRE_2.get(), 3)));

    public static final RegistryObject<Item> SAPPHIRE_4 = ITEMS.register("sapphire_4", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(4).byePrise(5500).sellPrise(550).inOutPrise(1000, 1000).rarity(Gem.Rarity.RARE)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(16, 12, 4, 4)
                    .required(SAPPHIRE_3.get(), 3)));

    public static final RegistryObject<Item> SAPPHIRE_5 = ITEMS.register("sapphire_5", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(5).byePrise(57143).sellPrise(800).inOutPrise(2000, 2000).rarity(Gem.Rarity.RARE)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(21, 16, 5, 5)
                    .required(SAPPHIRE_4.get(), 4)));

    public static final RegistryObject<Item> SAPPHIRE_6 = ITEMS.register("sapphire_6", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(6).byePrise(11000).sellPrise(1100).inOutPrise(5000, 5000).rarity(Gem.Rarity.RARE)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(26, 20, 6, 6)
                    .required(SAPPHIRE_5.get(), 4)));

    public static final RegistryObject<Item> SAPPHIRE_7 = ITEMS.register("sapphire_7", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(7).byePrise(18000).sellPrise(1800).inOutPrise(10000, 10000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(33, 25, 7, 7)
                    .required(SAPPHIRE_6.get(), 4)));

    public static final RegistryObject<Item> SAPPHIRE_8 = ITEMS.register("sapphire_8", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(8).byePrise(25000).sellPrise(2500).inOutPrise(20000, 20000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(42, 32, 8, 8)
                    .required(SAPPHIRE_7.get(), 5)));

    public static final RegistryObject<Item> SAPPHIRE_9 = ITEMS.register("sapphire_9", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(9).byePrise(36000).sellPrise(3600).inOutPrise(50000, 50000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(53, 40, 9, 9)
                    .required(SAPPHIRE_8.get(), 5)));

    public static final RegistryObject<Item> SAPPHIRE_10 = ITEMS.register("sapphire_10", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(10).byePrise(50000).sellPrise(5000).inOutPrise(100000, 100000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(70, 50, 10, 10)
                    .required(SAPPHIRE_9.get(), 5)));

    public static final RegistryObject<Item> SAPPHIRE_11 = ITEMS.register("sapphire_11", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(11).byePrise(190476190).sellPrise(10000).inOutPrise(200000, 200000).rarity(Gem.Rarity.GOLD)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(100, 75, 11, 11)
                    .required(SAPPHIRE_10.get(), 6)));

    public static final RegistryObject<Item> SAPPHIRE_12 = ITEMS.register("sapphire_12", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(12).byePrise(200000).sellPrise(20000).inOutPrise(500000, 500000).rarity(Gem.Rarity.GOLD)
                    .effects(Gem.Effect.MAG_DEF, Gem.Effect.MAG_ATTACK, Gem.Effect.DISTANCE, Gem.Effect.MAG_ATTACK).boost(135, 100, 12, 12)
                    .required(SAPPHIRE_11.get(), 6)));
//Сапфиры

//Изумруды
public static final RegistryObject<Item> EMERALD_1 = ITEMS.register("emerald_1", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
        new Gem.GemProperties().lvl(1).byePrise(500).sellPrise(100).inOutPrise(100, 100).rarity(Gem.Rarity.COMMON)
                .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(8, 8, 8, 8)
                .required(null, 0)));

    public static final RegistryObject<Item> EMERALD_2 = ITEMS.register("emerald_2", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(2).byePrise(2000).sellPrise(200).inOutPrise(200, 200).rarity(Gem.Rarity.COMMON)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(16, 16, 16, 16)
                    .required(EMERALD_1.get(), 3)));

    public static final RegistryObject<Item> EMERALD_3 = ITEMS.register("emerald_3", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(3).byePrise(3500).sellPrise(350).inOutPrise(500, 500).rarity(Gem.Rarity.COMMON)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(24, 24, 24, 24)
                    .required(EMERALD_2.get(), 3)));

    public static final RegistryObject<Item> EMERALD_4 = ITEMS.register("emerald_4", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(4).byePrise(5500).sellPrise(550).inOutPrise(1000, 1000).rarity(Gem.Rarity.RARE)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(32, 32, 32, 32)
                    .required(EMERALD_3.get(), 3)));

    public static final RegistryObject<Item> EMERALD_5 = ITEMS.register("emerald_5", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(5).byePrise(57143).sellPrise(800).inOutPrise(2000, 2000).rarity(Gem.Rarity.RARE)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(40, 40, 40, 40)
                    .required(EMERALD_4.get(), 4)));

    public static final RegistryObject<Item> EMERALD_6 = ITEMS.register("emerald_6", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(6).byePrise(11000).sellPrise(1100).inOutPrise(5000, 5000).rarity(Gem.Rarity.RARE)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(52, 52, 52, 52)
                    .required(EMERALD_5.get(), 4)));

    public static final RegistryObject<Item> EMERALD_7 = ITEMS.register("emerald_7", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(7).byePrise(18000).sellPrise(1800).inOutPrise(10000, 10000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(64, 64, 64, 64)
                    .required(EMERALD_6.get(), 4)));

    public static final RegistryObject<Item> EMERALD_8 = ITEMS.register("emerald_8", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(8).byePrise(25000).sellPrise(2500).inOutPrise(20000, 20000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(80, 80, 80, 80)
                    .required(EMERALD_7.get(), 5)));

    public static final RegistryObject<Item> EMERALD_9 = ITEMS.register("emerald_9", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(9).byePrise(36000).sellPrise(3600).inOutPrise(50000, 50000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(100, 100, 100, 100)
                    .required(EMERALD_8.get(), 5)));

    public static final RegistryObject<Item> EMERALD_10 = ITEMS.register("emerald_10", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(10).byePrise(50000).sellPrise(5000).inOutPrise(100000, 100000).rarity(Gem.Rarity.EPIC)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(120, 120, 120, 120)
                    .required(EMERALD_9.get(), 5)));

    public static final RegistryObject<Item> EMERALD_11 = ITEMS.register("emerald_11", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(11).byePrise(190476190).sellPrise(10000).inOutPrise(200000, 200000).rarity(Gem.Rarity.GOLD)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(180, 180, 180, 180)
                    .required(EMERALD_10.get(),6)));

    public static final RegistryObject<Item> EMERALD_12 = ITEMS.register("emerald_12", () -> new Gem(new Item.Properties().tab(ModCreativeTabs.GEMS),
            new Gem.GemProperties().lvl(12).byePrise(200000).sellPrise(20000).inOutPrise(500000, 500000).rarity(Gem.Rarity.GOLD)
                    .effects(Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP, Gem.Effect.MP).boost(240, 240, 240, 240)
                    .required(EMERALD_11.get(), 6)));
//Изумруды

    public static final RegistryObject<Item> TEST_BOW = ITEMS.register("test_bow", () -> new TestBow(new Item.Properties().tab(ModCreativeTabs.WEAPON_DIST).stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
