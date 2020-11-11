package com.github.madbrain.adventure.objects;

import com.github.madbrain.adventure.behaviours.Throwable;
import com.github.madbrain.adventure.commands.ThrowCommand;

import java.util.Collections;

public class CoinObject extends GameObjectImpl {

    public CoinObject() {
        this.behaviours.put(ThrowCommand.KEY, Collections.singletonList(new Throwable(0)));
    }

    @Override
    public String getName() {
        return "coin";
    }

    @Override
    public String getExamination() {
        return "It's a heavy gold coin.";
    }

}
