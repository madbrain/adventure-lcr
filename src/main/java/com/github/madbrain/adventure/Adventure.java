package com.github.madbrain.adventure;


import com.github.madbrain.adventure.commands.*;
import com.github.madbrain.adventure.objects.AxeObject;
import com.github.madbrain.adventure.objects.BookObject;
import com.github.madbrain.adventure.objects.CoinObject;
import com.github.madbrain.adventure.objects.StickObject;

import java.io.IOException;
import java.util.*;

/***************************************************************************/
/*                                                                         */
/*  Sample Adventure.  Written by Alex Leavins.  Rev. 1.61586              */
/*                                                                         */
/*  START magazine, Fall 1986.      Copyright 1986 by Antic Publishing     */
/*                                                                         */

/***************************************************************************/
/*
 * Demonstration adventure game and
 * parsing language system
 * Rev. 1.61586
 *
 * https://github.com/ggnkua/Atari_ST_Sources/tree/master/C/Alex%20Leavins/ADVENTUR.STQ
 */
public class Adventure {

    public static final int OFF = 0;

    /*
          __*__ _____
         |     |     |
         |  1  |  2  |
         |_____|_____|
         |     |     |
         |  4  |  3  |
         |_____|_____|
         |     |
         |  5  |
         |_____|

     */

    private Console console;
    private List<Room> rooms = new ArrayList<>();
    private Map<String, Command> commands = new HashMap<>();
    private Map<String, GameObject> objects = new HashMap<>();
    private Player player;
    private InputBuffer input;

    public Adventure(Console console) {
        this.console = console;
        commands.put("QUIT", new QuitCommand());
        commands.put("EAST", new MoveCommand(Direction.EAST));
        commands.put("WEST", new MoveCommand(Direction.WEST));
        commands.put("NORTH", new MoveCommand(Direction.NORTH));
        commands.put("SOUTH", new MoveCommand(Direction.SOUTH));
        commands.put("TAKE", new TakeCommand());
        commands.put("DROP", new DropCommand());
        commands.put("LOOK", new LookCommand());
        commands.put("INVENTORY", new InventoryCommand());
        commands.put("EXAMINE", new ExamineCommand());
        commands.put("READ", new ReadCommand());
        commands.put("THROW", new ThrowCommand());

        Room room1, room2, room3, room4, room5;
        rooms.add(room1 = new Room("This is an open section of forest.  Exits lead east, south and north.", Direction.EAST));
        rooms.add(room2 = new Room("This is the edge of the forest.  A path leads west, and another one south.", Direction.SOUTH));
        rooms.add(room3 = new Room("We're standing in a clearing.  You can go north, or west.", Direction.WEST));
        rooms.add(room4 = new Room("There is a large rock here, with paths leading north, east and south.", Direction.SOUTH));
        rooms.add(room5 = new Room("The forest becomes very dense here.  The only exit is to the north.", Direction.NORTH));

        room1.set(Direction.NORTH, room1);
        room1.set(Direction.EAST, room2);
        room1.set(Direction.SOUTH, room4);
        room2.set(Direction.WEST, room1);
        room2.set(Direction.SOUTH, room3);
        room3.set(Direction.NORTH, room2);
        room3.set(Direction.WEST, room4);
        room4.set(Direction.EAST, room3);
        room4.set(Direction.SOUTH, room5);
        room5.set(Direction.NORTH, room4);

        GameObject book, axe, coin, stick;
        objects.put("BOOK", book = new BookObject());
        objects.put("AXE", axe = new AxeObject());
        objects.put("COIN", coin = new CoinObject());
        objects.put("STICK", stick = new StickObject());

        room1.putObject(book);
        room2.putObject(axe);
        room3.putObject(stick);
        room5.putObject(coin);
    }

    /* ------------------------- MAIN() ---------------------------*/

    public void main() {
        init();
        player.getCurrentRoom().show(this);

        /* Now play the game.  Game is an infinite loop... */

        while (true) {
            input = getInput();
            Command command = commands.get(input.nextWord());
            if (command == null) {
                printf("I don't understand that command.\n");
            } else if (!command.execute(this)) {
                break;
            }
        }
    }

    /**
     * Initialize all global game variables
     */
    private void init() {
        // player starts in forest
        player = new Player(this, rooms.get(0));

        printf("The Mini Adventure, by Alex Leavens\n");
        printf("  In START, the ST Resource\n");
        printf("  from ANTIC magazine.\n");
        printf("  Rev. 1.61686\n");
        printf("\n\n");
        printf("Please press the CAPS LOCK key.\n\n");
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the next command from the player
     *
     * @return
     */
    private InputBuffer getInput() {
        while (true) {
            printf("-->");
            String line = Cconrs();
            printf("\n");
            if (line.isEmpty()) {
                printf("Say what?\n");
            } else {
                return new InputBuffer(line);
            }
        }
    }

    /**
     * scan through our list of object words, and find if any
     * one matches the current input word.
     *
     * @return
     */
    public Optional<GameObject> nextObject() {
        GameObject object = objects.get(input.nextWord());
        if (object == null) {
            printf("I don't know what that is.\n");
        }
        return Optional.ofNullable(object);
    }

    public void printf(String fmt, Object... args) {
        console.print(String.format(fmt, args));
    }

    private String Cconrs() {
        try {
            return console.getInput().readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
