package net.skullag.plantsvsminecraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;

public class ModItemGroups {
    public static final ItemGroup PLANTS_GROUP = Registry.register(Registries.ITEM_GROUP
            , new Identifier(PlantsVsMinecraft.MOD_ID, "pvm_plants"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.pvm_plants")).icon(() -> new ItemStack(ModItems.SunPoint)).entries((displayContext, entries) -> {
                entries.add(ModItems.SunPoint);
            }).build());

    public static void registerItemGroups () {
        PlantsVsMinecraft.LOGGER.info("Registering Item Groups for " + PlantsVsMinecraft.MOD_ID);

    }
}
