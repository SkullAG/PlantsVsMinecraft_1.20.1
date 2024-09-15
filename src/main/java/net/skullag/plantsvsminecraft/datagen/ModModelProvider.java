package net.skullag.plantsvsminecraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.skullag.plantsvsminecraft.PlantsVsMinecraft;
import net.skullag.plantsvsminecraft.item.ModItems;

public class ModModelProvider  extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        PlantsVsMinecraft.LOGGER.info("Generating item Models");

        itemModelGenerator.register(ModItems.SUN_POINT, Models.GENERATED);
        itemModelGenerator.register(ModItems.NUTRIENT, Models.GENERATED);

        itemModelGenerator.register(ModItems.PEA, Models.GENERATED);

        itemModelGenerator.register(ModItems.SUNFLOWER_SEED_PACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEASHOOTER_SEED_PACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.WALLNUT_SEED_PACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTATOMINE_SEED_PACK, Models.GENERATED);
    }
}
