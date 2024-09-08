package net.skullag.plantsvsminecraft.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.entity.ModEntities;
import net.skullag.plantsvsminecraft.item.custom.PeaItem;

public class ModItems {
    public static final Item SUN_POINT = registerItem("sunpoint", new Item(new FabricItemSettings()));
    public static final Item NUTRIENT = registerItem("nutrient", new Item(new FabricItemSettings()));
    public static final Item PEA = registerItem("pea", new PeaItem(new FabricItemSettings()));

    public static final Item SUNFLOWER_SEED_PACK = registerItem("sunflower_seed_pack",
            new SeedPack(ModEntities.SUNFLOWER, new FabricItemSettings()));
    public static final Item PEASHOOTER_SEED_PACK = registerItem("peashooter_seed_pack",
            new SeedPack(ModEntities.PEASHOOTER, new FabricItemSettings()));
    public static final Item WALLNUT_SEED_PACK = registerItem("wallnut_seed_pack",
            new SeedPack(ModEntities.WALLNUT, new FabricItemSettings()));

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
