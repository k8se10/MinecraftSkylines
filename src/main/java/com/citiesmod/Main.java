package com.citiesmod;

import com.citiesmod.city.CityManager;
import com.citiesmod.commands.CityCommand;
import com.citiesmod.network.CityPackets;
import com.citiesmod.registry.BlocksRegistry;
import com.citiesmod.registry.ItemsRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final String MOD_ID = "citiesmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("CitiesMod 0.0.4a initializing...");

        BlocksRegistry.registerBlocks();
        ItemsRegistry.registerItems();
        CityPackets.registerPackets();
        CityCommand.register();

        CityManager.init();
    }
}
