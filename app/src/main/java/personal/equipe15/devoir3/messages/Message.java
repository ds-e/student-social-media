package personal.equipe15.devoir3.messages;

public class Message {

    private String from;
    private int fromId;
    private String text;
    private String type;


    public Message(String from, String text, String type, int fromId) {
        this.from = from;
        this.text = text;
        this.type = type;
        this.fromId = fromId;
    }

    public String getFrom() { return from; }

    public void setFrom(String from) { this.from = from; }

    public int getFromId() { return fromId; }

    public void setFrom(int fromId) { this.fromId = fromId; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}