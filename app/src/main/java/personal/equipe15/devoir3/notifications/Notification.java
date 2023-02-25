package personal.equipe15.devoir3.notifications;

import personal.equipe15.devoir3.profile.Profile;

public class Notification {

    private String text;
    private String type;
    private int id;


    public Notification(String text, String type, int id) {
        this.text = text;
        this.type = type;
        this.id = id;
    }


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

    public int getId() {

        return id;
    }

}

