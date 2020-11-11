package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Command;

public class TakeCommand implements Command {
    @Override
    public void execute(Adventure adventure) {
        adventure.nextObject()
                .flatMap(object -> adventure.getPlayer().checkNotInInventory(object))
                .ifPresent(object -> adventure.getPlayer().takeObject(object));
    }
}
