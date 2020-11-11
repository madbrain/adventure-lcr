package com.github.madbrain.adventure.behaviours;

import com.github.madbrain.adventure.Adventure;
import com.github.madbrain.adventure.Behaviour;
import com.github.madbrain.adventure.GameObject;

public class Throwable implements Behaviour {

    private final int distance;
    private String specialMessage;

    public Throwable(int distance) {
        this(distance, "Ok, you throw it.");
    }

    public Throwable(int distance, String specialMessage) {
        this.distance = distance;
        this.specialMessage = specialMessage;
    }

    @Override
    public void apply(GameObject object, Adventure adventure) {
        adventure.getPlayer().discardObject(object, distance);
        adventure.printf(specialMessage + "\n");
    }
}
