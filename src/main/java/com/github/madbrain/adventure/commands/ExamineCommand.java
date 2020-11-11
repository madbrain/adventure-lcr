package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Command;

public class ExamineCommand implements Command {
    @Override
    public boolean execute(Adventure adventure) {
        adventure.nextObject()
                .flatMap(object -> adventure.getPlayer().checkInInventory(object))
                .ifPresent(object -> adventure.printf("%s\n", object.getExamination()));
        return true;
    }
}
