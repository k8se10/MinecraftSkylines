package com.minecraftskylines.client;

import net.fabricmc.api.ClientModInitializer;

public class MinecraftSkylinesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Minecraft Skylines client initialized!");
    }
}
