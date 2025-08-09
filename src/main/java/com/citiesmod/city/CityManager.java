package com.citiesmod.city;

import com.citiesmod.Main;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages all cities in the world. Server-side only.
 */
public class CityManager {
    private static final Map<UUID, CityDataComponent> CITIES = new HashMap<>();
    private static MinecraftServer serverRef;
    private static int tickCounter = 0;

    public static void init() {
        Main.LOGGER.info("CityManager initialized.");

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            serverRef = server;
            tickCounter++;

            if (tickCounter % 20 == 0) { // Once per second
                updateDailyCycles();
            }
        });
    }

    public static void setServer(MinecraftServer server) {
        serverRef = server;
    }

    public static void addCity(CityDataComponent city) {
        CITIES.put(city.getId(), city);
        Main.LOGGER.info("City added: {}", city.getName());
    }

    public static CityDataComponent getCity(UUID id) {
        return CITIES.get(id);
    }

    public static Map<UUID, CityDataComponent> getCities() {
        return CITIES;
    }

    private static void updateDailyCycles() {
        long time = serverRef.getOverworld().getTimeOfDay() % 24000L; // MC day cycle
        float activityMultiplier = 1.0f;

        // Morning rush: 0–3000 ticks
        if (time >= 0 && time < 3000) activityMultiplier = 1.5f;
        // Midday: 3000–9000 ticks
        else if (time >= 3000 && time < 9000) activityMultiplier = 1.2f;
        // Evening rush: 9000–12000 ticks
        else if (time >= 9000 && time < 12000) activityMultiplier = 1.4f;
        // Night: 12000–24000 ticks
        else activityMultiplier = 0.6f;

        for (CityDataComponent city : CITIES.values()) {
            city.setActivityIndex(activityMultiplier);
            // Passenger demand is residents + tourists scaled by activity
            int passengers = (int) ((city.getResidents() + city.getTourists()) / 50f * activityMultiplier);
            city.setPassengerDemand(passengers);

            // Freight demand from workers + industrial output (placeholder ratio)
            int freight = (int) (city.getWorkers() / 30f * activityMultiplier);
            city.setFreightDemand(freight);

            // Simulated NPC count = active portion of total residents
            int activeNPCs = (int) (city.getResidents() * 0.05f * activityMultiplier);
            city.setSimulatedNPCCount(activeNPCs);
        }
    }
}
