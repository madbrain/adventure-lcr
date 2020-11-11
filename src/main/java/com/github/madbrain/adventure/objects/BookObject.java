package com.github.madbrain.adventure.objects;

import com.github.madbrain.adventure.behaviours.Readable;
import com.github.madbrain.adventure.behaviours.Throwable;
import com.github.madbrain.adventure.commands.ReadCommand;
import com.github.madbrain.adventure.commands.ThrowCommand;

import java.util.Arrays;
import java.util.Collections;

public class BookObject extends GameObjectImpl {

    public BookObject() {
        this.behaviours.put(ReadCommand.KEY, Arrays.asList(new Readable("'A good adventurer is prepared for anything.'")));
        this.behaviours.put(ThrowCommand.KEY, Collections.singletonList(new Throwable(0)));
    }

    @Override
    public String getName() {
        return "book";
    }

    @Override
    public String getExamination() {
        return "It's a small paperback, titled 'Guide for Adventurers'.";
    }

}
