package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Behaviour;
import com.github.madbrain.adventure.Command;
import com.github.madbrain.adventure.ActionKey;

import java.util.List;

public class ReadCommand implements Command {

    public static final ActionKey KEY = new ActionKey() {};

    @Override
    public boolean execute(Adventure adventure) {
        adventure.nextObject()
                .flatMap(object -> adventure.getPlayer().checkInInventory(object))
                .ifPresent(object -> {
                    List<Behaviour> behaviours = object.getBehaviour(KEY);
                    if (behaviours.isEmpty()) {
                        adventure.printf("You can't read that!\n");
                    } else {
                        behaviours.forEach(behaviour -> {
                            behaviour.apply(object, adventure);
                        });
                    }
                });
        return true;
    }
}
