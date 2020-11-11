package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Command;

public class QuitCommand implements Command {
    @Override
    public void execute(Adventure adventure) {
        adventure.doQuit();
    }
}
