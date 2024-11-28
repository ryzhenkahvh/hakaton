package ry.tech.speedban;

public class Card {
    private String text;
    private boolean isFlipped;

    public Card(String text) {
        this.text = text;
        this.isFlipped = false;
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
}
