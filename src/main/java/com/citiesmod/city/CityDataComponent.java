package com.citiesmod.city;

import net.minecraft.util.math.BlockPos;
import java.util.UUID;

/**
 * Holds persistent data for a single city.
 */
public class CityDataComponent {
    private final UUID id;
    private final String name;
    private final BlockPos centerPos;
    private final int chunkRadius;

    // Population categories
    private int residents;
    private int workers;
    private int shoppers;
    private int tourists;

    // Transport demand
    private int passengerDemand;
    private int freightDemand;

    // NPC simulation
    private int simulatedNPCCount;

    // Activity multiplier based on time of day
    private float activityIndex;

    public CityDataComponent(String name, BlockPos centerPos, int chunkRadius) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.centerPos = centerPos;
        this.chunkRadius = chunkRadius;

        // Init defaults
        this.residents = 0;
        this.workers = 0;
        this.shoppers = 0;
        this.tourists = 0;
        this.passengerDemand = 0;
        this.freightDemand = 0;
        this.simulatedNPCCount = 0;
        this.activityIndex = 1.0f;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BlockPos getCenterPos() { return centerPos; }
    public int getChunkRadius() { return chunkRadius; }

    public int getResidents() { return residents; }
    public void setResidents(int val) { residents = val; }

    public int getWorkers() { return workers; }
    public void setWorkers(int val) { workers = val; }

    public int getShoppers() { return shoppers; }
    public void setShoppers(int val) { shoppers = val; }

    public int getTourists() { return tourists; }
    public void setTourists(int val) { tourists = val; }

    public int getPassengerDemand() { return passengerDemand; }
    public void setPassengerDemand(int val) { passengerDemand = val; }

    public int getFreightDemand() { return freightDemand; }
    public void setFreightDemand(int val) { freightDemand = val; }

    public int getSimulatedNPCCount() { return simulatedNPCCount; }
    public void setSimulatedNPCCount(int val) { simulatedNPCCount = val; }

    public float getActivityIndex() { return activityIndex; }
    public void setActivityIndex(float val) { activityIndex = val; }
}
