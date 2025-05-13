package com.emalderete.timesofpeace.empire;

// imports
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.*;

public class Empire {

    public enum State {
        PEACE,
        DEFENSE,
        OFFENSIVE
    }

    private final UUID id;
    private String name;
    private ServerPlayerEntity leader;
    private final Set<Identifier> villages; // Usamos Identifier para referenciar aldeas por coordenadas o nombres únicos.
    private final Map<String, Integer> resources;
    private State currentState;

    public Empire(ServerPlayerEntity leader, String name, Map<String, Integer> startingResources) {
        this.id = UUID.randomUUID();
        this.leader = leader;
        this.name = name;
        this.resources = new HashMap<>(startingResources);
        this.villages = new HashSet<>();
        this.currentState = State.PEACE;
    }

    // Métodos básicos
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ServerPlayerEntity getLeader() {
        return leader;
    }

    public void setLeader(ServerPlayerEntity newLeader) {
        this.leader = newLeader;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState (State state) {
        this.currentState = state;
    }

    public Set<Identifier> getVillages() {
        return Collections.unmodifiableSet(villages);
    }

    public Map<String, Integer> getResources() {
        return Collections.unmodifiableMap(resources);
    }

    public void addVillage(Identifier villageId) {
        villages.add(villageId);
    }

    public void removeVillage(Identifier villageId) {
        villages.remove(villageId);
    }

    public void addResource(String type, int amount) {
        resources.put(type, resources.getOrDefault(type, 0) + amount);
    }

    public boolean consumeResource(String type, int amount) {
        int current = resources.getOrDefault(type, 0);
        if(current >= amount) {
            resources.put(type, current - amount);
            return true;
        }
        return false;
    }

    public int getResourceAmount(String type) {
        return resources.getOrDefault(type, 0);
    }
}