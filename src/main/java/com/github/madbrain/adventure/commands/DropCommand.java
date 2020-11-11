package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Command;

public class DropCommand implements Command {

    @Override
    public boolean execute(Adventure adventure) {
        adventure.nextObject()
                .flatMap(object -> adventure.getPlayer().checkInInventory(object))
                .ifPresent(object -> {
                    adventure.getPlayer().discardObject(object, 0);
                    adventure.printf("A %s, dropped.\n", object.getName());
                });
        return true;
    }

}
