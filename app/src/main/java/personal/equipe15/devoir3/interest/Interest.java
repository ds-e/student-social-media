package personal.equipe15.devoir3.interest;

public class Interest {

    private String text;
    private int id;


    public Interest(String text, int id) {
        this.text = text;
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

}

