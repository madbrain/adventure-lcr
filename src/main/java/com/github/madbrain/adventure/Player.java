package com.github.madbrain.adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {
    private Adventure adventure;
    private Room currentRoom;
    private List<GameObject> inventory = new ArrayList<>();

    public Player(Adventure adventure, Room room) {
        this.adventure = adventure;
        this.currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Moves the player around.
     * Determines what direction the player asked for, {
     * determines if that's a valid direction.  if it is, {
     * the player is moved.  if it isn't, no move takes place.
     */
    public void moveUs(Direction direction) {
        Room room = currentRoom.get(direction);
        if (room == null) {
            adventure.printf("You can't go that way.\n");
        } else {
            currentRoom = room;
            getCurrentRoom().show(adventure);
        }
    }

    /**
     * Shows what objects a player is carrying
     */
    public void showInventory() {
        adventure.printf("You are currently carrying:\n");

        for (GameObject j : inventory) {
            adventure.printf("  a %s.\n", j.getName());
        }

        if (inventory.isEmpty()) {
            adventure.printf("Nothing at all\n");
        }
    }

    public Optional<GameObject> checkInInventory(GameObject object) {
        if (! inventory.contains(object)) {
            adventure.printf("You aren't carrying it!\n");
            return Optional.empty();
        }
        return Optional.of(object);
    }

    public Optional<GameObject> checkNotInInventory(GameObject object) {
        if (inventory.contains(object)) {
            adventure.printf("You've already got it!\n");
            return Optional.empty();
        }
        return Optional.of(object);
    }

    /**
     * Takes an object if it's in the room.
     */
    public void takeObject(GameObject obj) {
        if (! currentRoom.getObjects().contains(obj)) {
            adventure.printf("I see no %s here!\n", obj.getName());
        } else {
            currentRoom.pickObject(obj);
            this.inventory.add(obj);
            adventure.printf("A %s, taken.\n", obj.getName());
        }
    }

    public void discardObject(GameObject object, int distance) {
        Room destRoom = currentRoom;
        while (distance-- > 0) {
            destRoom = destRoom.getRoomInThrowDirection();
        }
        destRoom.putObject(object);
        this.inventory.remove(object);
    }
}
