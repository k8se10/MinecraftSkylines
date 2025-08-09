package com.citiesmod.network;

import com.citiesmod.Main;
import com.citiesmod.city.CityDataComponent;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class CityPackets {
    public static final Identifier SYNC_CITY_DATA = new Identifier(Main.MOD_ID, "sync_city_data");

    public static void registerPackets() {
        Main.LOGGER.info("Registering network packets for CitiesMod...");

        ServerPlayNetworking.registerGlobalReceiver(SYNC_CITY_DATA, (server, player, handler, buf, responseSender) -> {
            // Handle incoming city data request
        });
    }

    public static void sendCityDataToAll(CityDataComponent city, MinecraftServer server) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeUuid(city.getId());
        buf.writeString(city.getName());
        buf.writeBlockPos(city.getCenterPos());
        buf.writeInt(city.getChunkRadius());

        buf.writeInt(city.getResidents());
        buf.writeInt(city.getWorkers());
        buf.writeInt(city.getShoppers());
        buf.writeInt(city.getTourists());
        buf.writeInt(city.getPassengerDemand());
        buf.writeInt(city.getFreightDemand());
        buf.writeInt(city.getSimulatedNPCCount());
        buf.writeFloat(city.getActivityIndex());

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            ServerPlayNetworking.send(player, SYNC_CITY_DATA, buf);
        }
    }
}
