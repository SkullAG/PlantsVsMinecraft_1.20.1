package net.skullag.plantsvsminecraft.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;

public class ModItems {

    public static final Item SunPoint = registerItem("sunpoint", new Item(new FabricItemSettings()));

    private static void addItemsToItemGroup(FabricItemGroupEntries entries, Item... items) {
        for(Item item: items)
        {
            entries.add(item);
        }
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PlantsVsMinecraft.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PlantsVsMinecraft.LOGGER.info("Registering Mod Items for " + PlantsVsMinecraft.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> addItemsToItemGroup(entries, /*list of items*/));
    }
}
