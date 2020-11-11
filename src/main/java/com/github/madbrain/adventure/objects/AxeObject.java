package com.github.madbrain.adventure.objects;

import com.github.madbrain.adventure.behaviours.Throwable;
import com.github.madbrain.adventure.commands.ThrowCommand;

import java.util.Collections;

public class AxeObject extends GameObjectImpl {

    public AxeObject() {
        behaviours.put(ThrowCommand.KEY, Collections.singletonList(new Throwable(1, "You throw the axe.")));
    }

    @Override
    public String getName() {
        return "axe";
    }

    @Override
    public String getExamination() {
        return "It's a heavy single blade axe, with a very sharp blade.";
    }

}
