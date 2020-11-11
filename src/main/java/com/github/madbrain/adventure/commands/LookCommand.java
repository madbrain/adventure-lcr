package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Command;

public class LookCommand implements Command {
    @Override
    public boolean execute(Adventure adventure) {
        adventure.getPlayer().getCurrentRoom().show(adventure);
        return true;
    }
}
