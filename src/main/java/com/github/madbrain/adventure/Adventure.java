package com.github.madbrain.adventure;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    static final int OFF = 0;
    static final int ON = 1;
    static final int NULL = 0;

    static final int NORTH = 1;
    static final int SOUTH = 2;
    static final int EAST  = 3;
    static final int WEST  = 4;
    static final int TAKE  = 5;
    static final int DROP  = 6;
    static final int THROW = 7;
    static final int READ  = 8;
    static final int EXAMINE = 9;
    static final int INVENTORY = 10;
    static final int LOOK      = 11;
    static final int QUIT      = 12;

    /* Game objects */

    static final int BOOK	= 1;
    static final int AXE	= 2;
    static final int COIN	= 3;
    static final int STICK	= 4;

    String rooms[]={
        " ",
                "This is an open section of forest.  Exits lead east, south and north.",
                "This is the edge of the forest.  A path leads west, and another one south.",
                "We're standing in a clearing.  You can go north, or west.",
                "There is a large rock here, with paths leading north, east and south.",
                "The forest becomes very dense here.  The only exit is to the north."
    };

    String obj_exam[]={
        " ",
                "It's a small paperback, titled 'Guide for Adventurers'.",
                "It's a heavy single blade axe, with a very sharp blade.",
                "It's a heavy gold coin.",
                "It's a wooden stick."
    };

    String objct[]={
        " ",
                "book",
                "axe",
                "coin",
                "stick"
    };

    int north[]={ 0,1,0,2,1,4 },
            south[]={ 0,4,3,0,5,0 },
            east[]={  0,2,0,0,3,0 },
            west[]={  0,0,1,4,0,0 };

    int where[]={ 0, 1, 2, 5, 3 };

    String commands= "NORTH*SOUTH*EAST*WEST*TAKE*DROP*THROW*READ*EXAMINE*INVENTORY*LOOK*QUIT*#";

    String objects = "BOOK*AXE*COIN*STICK*#";

    char ast= '*';     /* test array for the character '*' */
    char pnd= '#';     /* test array for the character '#' */
    char spa= ' ';     /* test array for the character ' ' (a space)   */

    int cmd_index, obj_index, position;
    int flags[] = new int[20];
    int inventory[] = new int[10];
    char showword[] = new char[20];
    char objword[] = new char[20];
    char parser[] = new char[120];
    char test_word[] = new char[120];

    /* ------------------------- MAIN() ---------------------------*/

    void main()
    {
        int i, temp1;

        init();         /* setup game variables */
        show_room(position);
        show_objects(position);

        /* Now play the game.  Game is an infinite loop... */

        while (true) {
            get_input();    /* Get input string */
            split_word();   /* seperate first word from string */
            cmd_index = scan_commands();    /* 1st word a command? */
            if (cmd_index == OFF) {
                printf("I don't understand that command.\n");
            } else {
                switch (cmd_index) {
                    case NORTH:
                    case SOUTH:
                    case EAST:
                    case WEST:
                        temp1 = move_us(cmd_index, position);
                        if (temp1 != OFF){
                        position = temp1;
                    }
                    break;

                    case TAKE:
                        split_word();   /* get next word */
                        obj_index = scan_objects();
                        if (obj_index == OFF) {
                            printf("I don't know what that is.\n");
                        } else {
                            take_object(obj_index);
                        }
                        break;

                    case DROP:
                        split_word();   /* get next word */
                        obj_index = scan_objects();
                        if (obj_index == OFF) {
                            printf("I don't know what that is.\n");
                        } else {
                            drop_object(obj_index);
                        }
                        break;

                    case LOOK:
                        show_room(position);
                        show_objects(position);
                        break;

                    case INVENTORY:
                        show_inventory();
                        break;

                    case EXAMINE:
                        split_word();   /* get next word */
                        obj_index = scan_objects();
                        if (obj_index == OFF) {
                            printf("I don't know what that is.\n");
                        } else {
                            exam_object(obj_index);
                        }
                        break;

                    case READ:
                        split_word();   /* get next word */
                        obj_index = scan_objects();
                        if (obj_index == OFF) {
                            printf("I don't know what that is.\n");
                        } else {
                            read_object(obj_index);
                        }
                        break;

                    case THROW:
                        split_word();   /* get next word */
                        obj_index = scan_objects();
                        if (obj_index == OFF) {
                            printf("I don't know what that is.\n");
                        } else {
                            throw_object(obj_index);
                        }
                        break;

                    case QUIT:
                        return;

                    default:
                        break;

                } /* End "switch(cmd_index)"    */
            }
        }
    }

    /*-------------------- SCAN_COMMANDS -----------------------*/

    /* scan_commands():
     *   scan through our list of command words, and find if any
     *   one matches the current input word.
     */

    int scan_commands()
    {
        int i,j,index,result;

        j = 0;
        index = 0;

        for (i = 0; i < 100; i++) {
            showword[j] = commands.charAt(i);
            if (showword[j] == ast) {
                showword[j] = NULL;
                j = 0;
                index++;
                result = strcmp(showword, test_word);
                if (result == 0) {
                    return (index);
                }
            } else if (showword[j] == pnd) {
                index = OFF;
                break;
            } else{
                j++;
            }
        }

        return(index);
    }

    /*--------------------- SCAN_OBJECTS ---------------------*/

    /* scan_objects():
     *   scan through our list of object words, and find if any
     *   one matches the current input word.
     */

    int scan_objects()
    {
        int i,j,index,result;

        j = 0;
        index = 0;

        for (i = 0; i < 100; i++) {
            objword[j] = objects.charAt(i);
            if (objword[j] == ast) {
                objword[j] = NULL;
                j = 0;
                index++;
                result = strcmp(objword, test_word);
                if (result == 0) {
                    return (index);
                }
            } else if (objword[j] == pnd) {
                index = OFF;
                break;
            } else{
                j++;
            }
        }

        return(index);
    }

    /* --------------------- SHOW_ROOM -----------------------*/

    /* show_room()
     *   Shows what room we're currently in.
     */

    void show_room(int what_room)
    {
        printf("%s\n", rooms[what_room]);
    }

    /*---------------------- MOVE_US ---------------------------*/

    /*  move_us()
     *   Moves the player around.
     *   Determines what direction the player asked for, {
     *   determines if that's a valid direction.  if it is, {
     *   the player is moved.  if it isn't, no move takes place.
     */

    int move_us(int direction, int posit)
    {
        int i, j;

        switch (direction){

            case NORTH:
                i = north[posit];
                break;

            case SOUTH:
                i = south[posit];
                break;

            case EAST:
                i = east[posit];
                break;

            case WEST:
                i = west[posit];
                break;

            default:
                i = OFF;
                break;

        } /* End "switch (direction) " */

        if (i == OFF) {
        printf("You can't go that way.\n");
        return(OFF);

        } else {
        show_room(i);
        show_objects(i);
        return(i);
        }

    }

    /* --------------------- SPLIT_WORD --------------------*/

    /* split_word()
     *   Splits the command string up, to find the next word in the
     * string.  A word is defined as any text delimited by a space.
     *
     */

    void split_word()
    {
        int i,j;

        for (i = 0; i < 100; i++) {
            test_word[i] = parser[i + 2];
            if ((test_word[i] == spa) ||  (i == parser[1])){
                test_word[i] = NULL;
                for (j = 0; j < (100 - i); j++) {
                    parser[j] = parser[i + j + 1];
                }
                parser[1] -= (i + 1);
                break;
            }
        }
    }

    /* ------------------------ GET_INPUT ---------------------*/

    /*
     * get_input()
     *  Gets the next command from the player
     *
     */

    void get_input()
    {
        parser[0] = 100;
        parser[1] = 0;
        while (parser[1] == 0) {
            printf("-->");
            Cconrs(parser);
            printf("\n");
            if (parser[1] == 0) {
                printf("Say what?\n");
            }
        }
    }

    /* ----------------------------- INIT ------------------------*/

    /*
     * Initial all global game variables
     *
     */

    void init()
    {
        position = 1;   /* player starts in forest */

        printf("The Mini Adventure, by Alex Leavens\n");
        printf("  In START, the ST Resource\n");
        printf("  from ANTIC magazine.\n");
        printf("  Rev. 1.61686\n");
        printf("\n\n");
        printf("Please press the CAPS LOCK key.\n\n");
    }

    /* --------------------------- SHOW_OBJECTS ------------------*/

    /*
     * Show_objects()
     *  This routine displays any object(s) that are in a room
     *
     */

    void show_objects(int posit)
    {
        int j;

        for (j = 0; j < 5; j++) {
            if (where[j] == posit) {
                printf("There is a %s here.\n", objct[j]);
            }
        }
    }

    /* ---------------------- SHOW_INVENTORY ------------------ */

    /*
     * show_inventory()
     *   Shows what objects a player is carrying
     *
     */

    void show_inventory()
    {
        int i, j, k;

        k = 0;
        printf("You are currently carrying:\n");

        for (j = 0; j < 5; j++) {
            if (inventory[j] == ON) {
                printf("  a %s.\n", objct[j]);
                k = 1;
            }
        }

        if (k == OFF) {
        printf("Nothing at all\n");
        }
    }

    /* ------------------------ TAKE_OBJECT ------------------ */

    /*
     * take_object(obj)
     *  Takes an object if it's in the room.
     *
     */

    void take_object(int obj)
    {

        if (inventory[obj] == ON) {
        printf("You've already got it!\n");

        } else if (where[obj] != position) {
        printf("I see no %s here!\n", objct[obj]);
        return;
        } else {
        where[obj] = OFF;
        inventory[obj] = ON;
        printf("A %s, taken.\n", objct[obj]);
        }
    }

    /* ------------------------ DROP_OBJECT ------------------- */

    /*
     * drop_object(obj)
     *  Drops an object if player is carrying it.
     *
     */

    void drop_object(int obj)
    {

        if (inventory[obj] == OFF) {
        printf("You aren't carrying it!\n");

        } else {
        where[obj] = position;
        inventory[obj] = OFF;
        printf("A %s, dropped.\n", objct[obj]);
        }
    }

    /* ----------------------- EXAM_OBJECT ------------------ */

    /*
     * exam_object()
     *   Looks at an object if the player has it in possesion
     *
     */

    void exam_object(int obj)
    {

        if (inventory[obj] == OFF) {
        printf("You're not carrying it!\n");
        } else {
        printf("%s\n",obj_exam[obj]);
        }
    }

    /* ------------------------ READ_OBJECT ------------------- */

    /*
     * read_obj(obj)
     *  reads an object (if it's readable)
     *
     */
    void read_object(int obj)
    {
        if (inventory[obj] == OFF) {
        printf("You're not carrying it!\n");
        } else {
        switch(obj){

            case BOOK:
                printf("'A good adventurer is prepared for anything.'\n");
                break;

            case AXE:
            case STICK:
            case COIN:
            default:
                printf("You can't read that!\n");
                break;

        } /* End "switch(obj)"  */
        }
    }

    /* ------------------------ THROW_OBJECT ----------------- */

    /*
     * throw_obj(obj)
     *   Throws any throwable object
     *
     */

    void throw_object(int obj)
    {
        if (inventory[obj] == OFF) {
        printf("You're not carrying it!\n");
        } else {
        switch(obj){

            case AXE:
                printf("You throw the axe.\n");
                where[obj] = position + 1;
                inventory[obj] = OFF;
                if (where[obj] == 6) {
                where[obj] = 4;
                }
                break;

            case BOOK:
            case STICK:
            case COIN:
                printf("Ok, you throw it.\n");
                where[obj] = position;
                inventory[obj] = OFF;
                break;

            default:
                printf("You can't throw that!\n");
                break;

        } /* End "switch(obj)"  */
        }
    }

    void printf(String fmt, Object... args) {
        System.out.print(String.format(fmt, args));
    }

    void Cconrs(char[] parser) {
        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        try {
            String line = reader.readLine();
            parser[1] = (char)line.length();
            System.arraycopy(line.toCharArray(), 0, parser, 2, Math.min(line.length(), parser[0]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    int strcmp(char[] a, char[] b) {
        int i = 0;
        while (a[i] != 0 && b[i] != 0) {
            if (a[i] != b[i] || a[i] == 0 || b[i] == 0) {
                return 1;
            }
            ++i;
        }
        return 0;
    }

    public static void main(String[] args) {
        new Adventure().main();
    }

}
