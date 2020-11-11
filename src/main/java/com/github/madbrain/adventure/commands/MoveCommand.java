package com.github.madbrain.adventure.commands;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Command;
import com.github.madbrain.adventure.Direction;

import static com.github.madbrain.adventure.Adventure.OFF;

public class MoveCommand implements Command {

    private Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean execute(Adventure adventure) {
        adventure.getPlayer().moveUs(this.direction);
        return true;
    }
}
