package com.bodryak.gmod.item;

import com.bodryak.gmod.gui.gui.screen.GuiAlchemistScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Gem extends Item {
    enum Effect {ACCURACY, EVASION, CHARGE, CAST, PHIS_DEF, PHIS_ATTACK, DISTANCE, HP, MAG_DEF, MAG_ATTACK, MP}
    enum Rarity {COMMON, RARE, EPIC, GREEN, GOLD, ORANGE, RED}
    private final int bye_prise;
    private final int sell_prise;
    private final int in_prise;
    private final int out_prise;
    private final int armor_boost;
    private final int military_boost;
    private final int magic_boost;
    private final int distance_boost;
    private final Rarity rarity;
    private final Effect armor_effect;
    private final Effect military_effect;
    private final Effect distance_effect;
    private final Effect magic_effect;
    private final int lvl;
    public final int required_amount;
    public final Item required_item;

    public Gem(Properties properties, GemProperties gemProp) {
        super(properties);
        this.bye_prise = gemProp.bye_prise;
        this.sell_prise = gemProp.sell_prise;
        this.in_prise = gemProp.in_prise;
        this.out_prise = gemProp.out_prise;
        this.armor_boost = gemProp.armor_boost;
        this.magic_boost = gemProp.magic_boost;
        this.military_boost = gemProp.military_boost;
        this.distance_boost = gemProp.distance_boost;
        this.rarity = gemProp.rarity;
        this.armor_effect = gemProp.armor_effect;
        this.military_effect = gemProp.military_effect;
        this.distance_effect = gemProp.distance_effect;
        this.magic_effect = gemProp.magic_effect;
        this.lvl = gemProp.lvl;
        this.required_amount = gemProp.required_amount;
        this.required_item = gemProp.required_item;
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        Gem item = (Gem) itemStack.getItem();
        if(item.rarity == Rarity.RARE){
            components.add(Component.literal("§9" + components.get(0).getString()));
            components.remove(0);
        }
        if(item.rarity == Rarity.EPIC){
            components.add(Component.literal("§d" + components.get(0).getString()));
            components.remove(0);
        }
        if(item.rarity == Rarity.GOLD){
            components.add(Component.literal("§6" + components.get(0).getString()));
            components.remove(0);
        }

        components.add(Component.literal("Минимальный уровень снаряжения: " + item.lvl +"ур"));
        components.add(Component.empty());
        components.add(Component.literal("Эффекты при инкустрации в снаряжение:"));
        components.add(Component.literal("Броня: " + item.effectToString(item.armor_effect) + " +" + item.armor_boost));
        components.add(Component.literal("Оружие: " + item.effectToString(item.military_effect) + " +" + item.military_boost));
        components.add(Component.literal("Лук: " + item.effectToString(item.distance_effect) + " +" + item.distance_boost));
        components.add(Component.literal("Маг. посох: " + item.effectToString(item.magic_effect) + " +" + item.magic_boost));

        if(Minecraft.getInstance().screen instanceof GuiAlchemistScreen){
            //components.add(Component.literal("Цена: " + item.sell_prise + "/" + item.bye_prise));
        }else {
            components.add(Component.empty());
            components.add(Component.literal("Цена: " + item.sell_prise + "/" + item.bye_prise));
        }
        super.appendHoverText(itemStack, level, components, flag);
    }

    public String effectToString (Effect effect) {
        switch (effect) {
            case ACCURACY: return "Меткость";
            case EVASION: return "Уклонение";
            case CHARGE: return "Натяжение";
            case CAST: return "Подготовка";
            case PHIS_ATTACK: return "Физ атака";
            case PHIS_DEF: return "Физ защита";
            case HP: return "Здоровье";
            case DISTANCE: return "Дальность";
            case MAG_DEF: return "Маг защита";
            case MAG_ATTACK: return "Маг атака";
            case MP: return "Мана";
            default: return null;
        }
    }

    public static class GemProperties {
        int bye_prise, sell_prise;
        int in_prise, out_prise;
        int armor_boost, military_boost, magic_boost, distance_boost;
        Rarity rarity;
        Effect armor_effect;
        Effect military_effect;
        Effect distance_effect;
        Effect magic_effect;
        int lvl;
        int required_amount;
        Item required_item;

        public Gem.GemProperties byePrise(int v) {
            bye_prise = v;
            return this;
        }
        public Gem.GemProperties sellPrise(int v) {
            sell_prise = v;
            return this;
        }
        public Gem.GemProperties inOutPrise(int in, int out) {
            in_prise = in;
            out_prise = out;
            return this;
        }
        public Gem.GemProperties boost(int arm, int mil, int mag, int dis) {
            armor_boost = arm;
            military_boost = mil;
            magic_boost = mag;
            distance_boost = dis;
            return this;
        }
        public Gem.GemProperties rarity(Rarity rar) {
            rarity = rar;
            return this;
        }
        public Gem.GemProperties effects(Effect armor, Effect military, Effect distance, Effect magic) {
            armor_effect =armor;
            military_effect = military;
            distance_effect = distance;
            magic_effect = magic;
            return this;
        }
        public Gem.GemProperties lvl(int lvl) {
            this.lvl = lvl;
            return this;
        }
        public Gem.GemProperties required(Item item, int amo) {
            this.required_item = item;
            this.required_amount = amo;
            return this;
        }
    }
}
