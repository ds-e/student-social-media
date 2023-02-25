package personal.equipe15.devoir3.chats;

public class Chat {

    private String from;
    private String text;

    public Chat(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}