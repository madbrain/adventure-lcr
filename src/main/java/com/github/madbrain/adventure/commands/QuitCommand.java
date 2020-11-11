package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Command;

public class QuitCommand implements Command {
    @Override
    public boolean execute(Adventure adventure) {
        return false;
    }
}
