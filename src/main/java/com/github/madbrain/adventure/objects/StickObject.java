package com.github.madbrain.adventure.objects;

import com.github.madbrain.adventure.behaviours.Throwable;
import com.github.madbrain.adventure.commands.ThrowCommand;

import java.util.Collections;

public class StickObject extends GameObjectImpl {

    public StickObject() {
        this.behaviours.put(ThrowCommand.KEY, Collections.singletonList(new Throwable(0)));
    }

    @Override
    public String getName() {
        return "stick";
    }

    @Override
    public String getExamination() {
        return "It's a wooden stick.";
    }

}
