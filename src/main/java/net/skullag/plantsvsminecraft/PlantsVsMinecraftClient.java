package net.skullag.plantsvsminecraft;

import net.fabricmc.api.ClientModInitializer;
import net.skullag.plantsvsminecraft.entity.client.ModModelLayers;

public class PlantsVsMinecraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelLayers.registerModelLayers();
    }
}
