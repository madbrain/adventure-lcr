package com.github.madbrain.adventure.behaviours;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Behaviour;
import com.github.madbrain.adventure.GameObject;

public class Readable implements Behaviour {

    private final String text;

    public Readable(String text) {
        this.text = text;
    }

    @Override
    public void apply(GameObject object, Adventure adventure) {
        adventure.printf(text + "\n");
    }
}
