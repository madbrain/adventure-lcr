package com.github.madbrain.adventure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Room {
    private String description;
    private Direction throwDirection;
    private Map<Direction, Room> neighbours = new HashMap<>();
    private Set<GameObject> objects = new HashSet<>();

    public Room(String description, Direction throwDirection) {
        this.description = description;
        this.throwDirection = throwDirection;
    }

    public void set(Direction direction, Room room) {
        neighbours.put(direction, room);
    }

    public String getDescription() {
        return description;
    }

    public Room get(Direction direction) {
        return neighbours.get(direction);
    }

    public Set<GameObject> getObjects() {
        return objects;
    }

    public void putObject(GameObject object) {
        this.objects.add(object);
    }

    public void pickObject(GameObject object) {
        this.objects.remove(object);
    }

    public Room getRoomInThrowDirection() {
        return this.get(this.throwDirection);
    }

    public void show(Adventure adventure) {
        adventure.printf("%s\n", description);
        for (GameObject object : objects) {
            adventure.printf("There is a %s here.\n", object.getName());
        }
    }
}
