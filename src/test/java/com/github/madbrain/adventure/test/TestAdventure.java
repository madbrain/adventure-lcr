package com.github.madbrain.adventure.test;

import com.github.madbrain.adventure.Adventure;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class TestAdventure {

    @Test
    public void testQuitCommand() {
        TestConsole console = new TestConsole("QUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->"));
    }

    @Test
    public void testUnknownCommand() {
        TestConsole console = new TestConsole("ZAP\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "I don't understand that command.",
                "-->"
        ));
    }

    @Test
    public void testSayNothing() {
        TestConsole console = new TestConsole("\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "Say what?",
                "-->"
        ));
    }

    // "READ*EXAMINE*INVENTORY*LOOK*#";

    @Test
    public void testNorthCommand() {
        TestConsole console = new TestConsole("NORTH\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->"));
    }

    @Test
    public void testSouthCommand() {
        TestConsole console = new TestConsole("SOUTH\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "There is a large rock here, with paths leading north, east and south.",
                "-->"));
    }

    @Test
    public void testEastCommand() {
        TestConsole console = new TestConsole("EAST\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "This is the edge of the forest.  A path leads west, and another one south.",
                "There is a axe here.",
                "-->"));
    }

    @Test
    public void testWestCommand() {
        TestConsole console = new TestConsole("WEST\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "You can't go that way.",
                "-->"));
    }

    @Test
    public void testTakeCommand() {
        TestConsole console = new TestConsole("TAKE BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "A book, taken.",
                "-->"));
    }

    @Test
    public void testTakeObjectNotThere() {
        TestConsole console = new TestConsole("TAKE AXE\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "I see no axe here!",
                "-->"));
    }

    @Test
    public void testTakeObjectAlreadyCarried() {
        TestConsole console = new TestConsole("TAKE BOOK\nTAKE BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "A book, taken.",
                "-->",
                "You've already got it!",
                "-->"));
    }

    @Test
    public void testTakeUnknownObject() {
        TestConsole console = new TestConsole("TAKE CAMEMBERT\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "I don't know what that is.",
                "-->"));
    }

    @Test
    public void testDropCommand() {
        TestConsole console = new TestConsole("TAKE BOOK\nDROP BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "A book, taken.",
                "-->",
                "A book, dropped.",
                "-->"));
    }

    @Test
    public void testDropUnkownObject() {
        TestConsole console = new TestConsole("DROP CHEMISE\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "I don't know what that is.",
                "-->"));
    }

    @Test
    public void testDropObjectNotCarried() {
        TestConsole console = new TestConsole("DROP BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "You aren't carrying it!",
                "-->"));
    }

    @Test
    public void testThrowCommand() {
        TestConsole console = new TestConsole("TAKE BOOK\nTHROW BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "A book, taken.",
                "-->",
                "Ok, you throw it.",
                "-->"));
    }

    @Test
    public void testThrowUnknownObject() {
        TestConsole console = new TestConsole("THROW CARAMEL\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "I don't know what that is.",
                "-->"));
    }

    @Test
    public void testThrowObjectNotCarried() {
        TestConsole console = new TestConsole("THROW BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "You aren't carrying it!",
                "-->"));
    }

    @Test
    public void testThrowAxe() {
        TestConsole console = new TestConsole("EAST\nTAKE AXE\nTHROW AXE\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "This is the edge of the forest.  A path leads west, and another one south.",
                "There is a axe here.",
                "-->",
                "A axe, taken.",
                "-->",
                "You throw the axe.",
                "-->"));
    }

    @Test
    public void testThrowAxeAtSpecialPosition() {
        TestConsole console = new TestConsole("EAST\nTAKE AXE\nSOUTH\nWEST\nSOUTH\nTHROW AXE\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "This is the edge of the forest.  A path leads west, and another one south.",
                "There is a axe here.",
                "-->",
                "A axe, taken.",
                "-->",
                "We're standing in a clearing.  You can go north, or west.",
                "There is a stick here.",
                "-->",
                "There is a large rock here, with paths leading north, east and south.",
                "-->",
                "The forest becomes very dense here.  The only exit is to the north.",
                "There is a coin here.",
                "-->",
                "You throw the axe.",
                "-->"));
    }

    @Test
    public void testReadCommand() {
        TestConsole console = new TestConsole("TAKE BOOK\nREAD BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "A book, taken.",
                "-->",
                "'A good adventurer is prepared for anything.'",
                "-->"));
    }

    @Test
    public void testReadUnknownObject() {
        TestConsole console = new TestConsole("READ JOKE\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "I don't know what that is.",
                "-->"));
    }

    @Test
    public void testReadObjectWhenNotCarrying() {
        TestConsole console = new TestConsole("READ BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "You aren't carrying it!",
                "-->"));
    }

    @Test
    public void testReadUnreadableObject() {
        TestConsole console = new TestConsole("EAST\nTAKE AXE\nREAD AXE\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "This is the edge of the forest.  A path leads west, and another one south.",
                "There is a axe here.",
                "-->",
                "A axe, taken.",
                "-->",
                "You can't read that!",
                "-->"));
    }

    @Test
    public void testExamineCommand() {
        TestConsole console = new TestConsole("TAKE BOOK\nEXAMINE BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "A book, taken.",
                "-->",
                "It's a small paperback, titled 'Guide for Adventurers'.",
                "-->"));
    }

    @Test
    public void testExamineUnknwonObject() {
        TestConsole console = new TestConsole("EXAMINE CHOU\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "I don't know what that is.",
                "-->"));
    }

    @Test
    public void testExamineWhenNotCarying() {
        TestConsole console = new TestConsole("EXAMINE BOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "You aren't carrying it!",
                "-->"));
    }

    @Test
    public void testLookCommand() {
        TestConsole console = new TestConsole("LOOK\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->"));
    }

    @Test
    public void testInventoryCommandWithoutObject() {
        TestConsole console = new TestConsole("INVENTORY\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "You are currently carrying:",
                "Nothing at all",
                "-->"));
    }

    @Test
    public void testInventoryCommandWithObject() {
        TestConsole console = new TestConsole("TAKE BOOK\nINVENTORY\nQUIT\n");
        Adventure adventure = new Adventure(console);

        adventure.main();

        Assertions.assertThat(console.lines).isEqualTo(Arrays.asList(
                "The Mini Adventure, by Alex Leavens",
                "  In START, the ST Resource",
                "  from ANTIC magazine.",
                "  Rev. 1.61686",
                "Please press the CAPS LOCK key.",
                "This is an open section of forest.  Exits lead east, south and north.",
                "There is a book here.",
                "-->",
                "A book, taken.",
                "-->",
                "You are currently carrying:",
                "  a book.",
                "-->"));
    }
}
