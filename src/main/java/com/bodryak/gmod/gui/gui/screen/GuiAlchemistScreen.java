package com.bodryak.gmod.gui.gui.screen;

import com.bodryak.gmod.gui.gui.menu.GuiAlchemistMenu;
import com.bodryak.gmod.item.Gem;
import com.bodryak.gmod.item.ModItems;
import com.bodryak.gmod.network.ModMessages;
import com.bodryak.gmod.network.c2s.CraftGemC2SPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;

import java.util.concurrent.atomic.AtomicReference;

public class GuiAlchemistScreen extends AbstractContainerScreen<GuiAlchemistMenu> {
    private static final ResourceLocation texture = new ResourceLocation("gmod:textures/gui/stats_bg.png");
    private static final ResourceLocation topaz   = new ResourceLocation("gmod:textures/gui/topaz.png");
    private static final ResourceLocation topaz11   = new ResourceLocation("gmod:textures/gui/topaz11.png");
    private static final ResourceLocation topaz12   = new ResourceLocation("gmod:textures/gui/topaz12.png");
    private static final ResourceLocation rubin   = new ResourceLocation("gmod:textures/gui/rubin.png");
    private static final ResourceLocation rubin11   = new ResourceLocation("gmod:textures/gui/rubin11.png");
    private static final ResourceLocation rubin12   = new ResourceLocation("gmod:textures/gui/rubin12.png");
    private static final ResourceLocation rubin13   = new ResourceLocation("gmod:textures/gui/rubin13.png");
    private static final ResourceLocation amber   = new ResourceLocation("gmod:textures/gui/amber.png");
    private static final ResourceLocation amber11   = new ResourceLocation("gmod:textures/gui/amber11.png");
    private static final ResourceLocation amber12   = new ResourceLocation("gmod:textures/gui/amber12.png");
    private static final ResourceLocation amber13   = new ResourceLocation("gmod:textures/gui/amber13.png");
    private static final ResourceLocation sapphire   = new ResourceLocation("gmod:textures/gui/sapphire.png");
    private static final ResourceLocation sapphire11   = new ResourceLocation("gmod:textures/gui/sapphire11.png");
    private static final ResourceLocation sapphire12   = new ResourceLocation("gmod:textures/gui/sapphire12.png");
    private static final ResourceLocation sapphire13   = new ResourceLocation("gmod:textures/gui/sapphire13.png");
    private static final ResourceLocation emerald   = new ResourceLocation("gmod:textures/gui/emerald.png");
    private static final ResourceLocation emerald11   = new ResourceLocation("gmod:textures/gui/emerald11.png");
    private static final ResourceLocation emerald12   = new ResourceLocation("gmod:textures/gui/emerald12.png");
    private static final ResourceLocation selected   = new ResourceLocation("gmod:textures/gui/selected.png");
    private static ResourceLocation required_image   = new ResourceLocation("");
    private static ResourceLocation selected_image   = new ResourceLocation("");

    private Item select;
    private Item required_item;
    private int required_amount;
    private int selectPosX;
    private int selectPosY;
    private int craft_count;
    private int item_count;
    private int required_count;

    Font font = Minecraft.getInstance().font;

    public GuiAlchemistScreen(GuiAlchemistMenu container, Inventory inventory, Component component) {
        super(container, inventory, component);
        this.imageWidth = 400;
        this.imageHeight = 300;
        this.select = null;
    }

    @Override
    protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);
        blit(ms, this.leftPos, this.topPos, 0, 0,  this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        if(select != null) {
            RenderSystem.setShaderTexture(0, selected);
            blit(ms, selectPosX, selectPosY, 0, 0,  36, 36, 36, 36);
            drawString(ms, font, "§lКол-во: ", this.leftPos + 6, this.topPos + 270, -1);
            drawString(ms, font, "§l" + craft_count, this.leftPos + 4 + (font.width("§lКол-во:") / 2) - 1, this.topPos + 280, -1);
            drawString(ms, font, "§lТребуется: ", this.leftPos + 4 + 200, this.topPos + 270, -1);
            drawString(ms, font, "§l" + required_count + "/" + item_count, this.leftPos + 4 + 200, this.topPos + 280, -1);

        }
        RenderSystem.disableBlend();
    }
    @Override
    public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            assert this.minecraft != null;
            assert this.minecraft.player != null;
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderLabels( PoseStack poseStack, int mouseX, int mouseY) {
    }

    @Override
    public void onClose() {
        super.onClose();
        //this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
    }

    @Override
    public void init() {
        super.init();
        assert this.minecraft != null;
        //this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        int i = this.leftPos + 4;
        int j = this.topPos + 4;
        renderButton(i, j, topaz, ModItems.TOPAZ_2.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_3.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_4.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_5.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_6.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_7.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_8.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_9.get());    i += 36;
        renderButton(i, j, topaz, ModItems.TOPAZ_10.get());   i += 36;
        renderButton(i, j, topaz11, ModItems.TOPAZ_11.get()); i += 36;
        renderButton(i, j, topaz12, ModItems.TOPAZ_12.get()); i  = this.leftPos + 4; j += 36;

        renderButton(i, j, rubin, ModItems.RUBIN_2.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_3.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_4.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_5.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_6.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_7.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_8.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_9.get());    i += 36;
        renderButton(i, j, rubin, ModItems.RUBIN_10.get());   i += 36;
        renderButton(i, j, rubin11, ModItems.RUBIN_11.get()); i += 36;
        renderButton(i, j, rubin12, ModItems.RUBIN_12.get()); i  = this.leftPos + 4; j += 36;

        renderButton(i, j, amber, ModItems.AMBER_2.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_3.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_4.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_5.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_6.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_7.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_8.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_9.get());    i += 36;
        renderButton(i, j, amber, ModItems.AMBER_10.get());   i += 36;
        renderButton(i, j, amber11, ModItems.AMBER_11.get()); i += 36;
        renderButton(i, j, amber12, ModItems.AMBER_12.get()); i  = this.leftPos + 4; j += 36;

        renderButton(i, j, sapphire, ModItems.SAPPHIRE_2.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_3.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_4.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_5.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_6.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_7.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_8.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_9.get());    i += 36;
        renderButton(i, j, sapphire, ModItems.SAPPHIRE_10.get());   i += 36;
        renderButton(i, j, sapphire11, ModItems.SAPPHIRE_11.get()); i += 36;
        renderButton(i, j, sapphire12, ModItems.SAPPHIRE_12.get()); i  = this.leftPos + 4; j += 36;

        renderButton(i, j, emerald, ModItems.EMERALD_2.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_3.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_4.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_5.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_6.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_7.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_8.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_9.get());    i += 36;
        renderButton(i, j, emerald, ModItems.EMERALD_10.get());   i += 36;
        renderButton(i, j, emerald11, ModItems.EMERALD_11.get()); i += 36;
        renderButton(i, j, emerald12, ModItems.EMERALD_12.get()); i  = this.leftPos + 4; j += 36;

        renderButton(i, j, rubin13, ModItems.RUBIN_13.get());       i += 36;
        renderButton(i, j, sapphire13, ModItems.SAPPHIRE_13.get()); i += 36;
        renderButton(i, j, amber13, ModItems.AMBER_13.get());       i += 36;


        if(select != null) {
            this.addRenderableOnly(new ImageButton(this.leftPos + 80 , this.topPos + 264 , 32, 32, 0, 0, 0, selected_image, 32, 32,
                    e -> {}, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(select), x, y), Component.literal("")));
            this.addRenderableOnly(new ImageButton(this.leftPos + 80 + 200, this.topPos + 264 , 32, 32, 0, 0, 0, required_image, 32, 32,
                    e -> {}, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(required_item), x, y), Component.literal("")));
            this.addRenderableWidget(new Button(this.leftPos + 4, this.topPos + 279, 9, 8, Component.literal("-"), e ->{
                if (craft_count > 1) {
                    craft_count--;
                    required_count = required_amount * craft_count;
                }
            }));
            this.addRenderableWidget(new Button(this.leftPos + 45, this.topPos + 279, 9, 8, Component.literal("+"), e ->{
                if(required_amount * (craft_count + 1) <= item_count) {
                    craft_count++;
                    required_count = required_amount * craft_count;
                }
            }));
            this.addRenderableWidget(new Button(this.leftPos + 320, this.topPos + 264 +6, 76, 20, Component.literal("§lИзготовить"), e -> {
                if(required_count <= item_count) {
                    ModMessages.sendToServer(new CraftGemC2SPacket(new ItemStack(select), craft_count));
                    item_count -= required_count;
                    craft_count = 1;
                    required_count = required_amount * craft_count;
                }
            }));
        }
    }

    private void renderButton(int bx, int by, ResourceLocation res, Item item) {
        this.addRenderableWidget(new ImageButton(bx, by, 32, 32, 0, 0, 0, res, 32, 32,
                e -> {
                    selectPosX = bx - 2;
                    selectPosY = by - 2;
                    select = item;
                    selected_image = res;
                    Gem it = (Gem) item;
                    required_item = it.required_item;
                    required_amount = it.required_amount;
                    craft_count = 1;
                    required_count = required_amount * craft_count;
                    if(res == topaz || res == topaz11) {
                        required_image = topaz;
                    }
                    if (res == topaz12) {
                        required_image = topaz11;
                    }
                    if(res == rubin || res == rubin11) {
                        required_image = rubin;
                    }
                    if(res == rubin12) {
                        required_image = rubin11;
                    }
                    if(res == rubin13) {
                        required_image = rubin12;
                    }
                    if(res == amber || res == amber11) {
                        required_image = amber;
                    }
                    if(res == amber12) {
                        required_image = amber11;
                    }
                    if(res == amber13) {
                        required_image = amber12;
                    }
                    if(res == sapphire || res == sapphire11) {
                        required_image = sapphire;
                    }
                    if(res == sapphire12) {
                        required_image = sapphire11;
                    }
                    if(res == sapphire13) {
                        required_image = sapphire12;
                    }
                    if(res == emerald || res == emerald11) {
                        required_image = emerald;
                    }
                    if(res == emerald12) {
                        required_image = emerald11;
                    }

                    item_count = 0;
                    AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
                    Minecraft.getInstance().player.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _iitemhandlerref.set(capability));
                    if (_iitemhandlerref.get() != null) {
                        for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
                            ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
                            if (required_item == itemstackiterator.getItem()) {
                                item_count = item_count + (itemstackiterator).getCount();
                            }
                        }
                    }


                    this.clearWidgets();
                    this.init();
                }, (button, poseStack, x, y) -> renderTooltip(poseStack, new ItemStack(item), x, y), Component.literal("")));
    }
}

/*
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos -15, 15, 15, Component.literal("+"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() + 0.1);
        }));
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos +15, 15, 15, Component.literal("-"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() - 0.1);
        }));
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos -30, 15, 15, Component.literal("+"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() + 0.01);
        }));
        this.addRenderableWidget(new Button(this.leftPos - 15, this.topPos +30, 15, 15, Component.literal("-"), e ->{
            minecraft.getWindow().setGuiScale(Minecraft.getInstance().getWindow().getGuiScale() - 0.01);
        }));*/
