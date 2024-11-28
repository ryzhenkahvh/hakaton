package ry.tech.speedban;

public class Card {
    private String text;
    private boolean isFlipped;
    private int id;

    public Card(String text, int id) {
        this.text = text;
        this.isFlipped = false;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flip() {
        isFlipped = !isFlipped;
    }

    public int getId() {
        return id;
    }
}
