package com.github.madbrain.adventure;

public class InputBuffer {

    private static final char SPA = ' ';

    private int index = 0;
    private String content;

    public InputBuffer(String content) {
        this.content = content;
    }

    public char get() {
        return this.content.charAt(index++);
    }

    public boolean atEnd() {
        return this.index >= this.content.length();
    }

    /**
     * Splits the command string up, to find the next word in the
     * string.  A word is defined as any text delimited by a space.
     *
     * @return
     */
    public String nextWord() {
        StringBuilder builder = new StringBuilder();
        while (!atEnd()) {
            char c = get();
            if (c == SPA) {
                break;
            }
            builder.append(c);
        }
        return builder.toString();
    }
}
