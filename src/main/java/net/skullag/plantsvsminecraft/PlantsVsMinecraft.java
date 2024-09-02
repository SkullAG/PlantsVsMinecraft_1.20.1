package net.skullag.plantsvsminecraft;

import net.fabricmc.api.ModInitializer;

import net.skullag.plantsvsminecraft.entity.ModEntities;
import net.skullag.plantsvsminecraft.item.ModItemGroups;
import net.skullag.plantsvsminecraft.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlantsVsMinecraft implements ModInitializer {
	public static final String MOD_ID = "plantsvsminecraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModEntities.registerEntities();
	}
}