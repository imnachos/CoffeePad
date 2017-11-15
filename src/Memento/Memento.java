package Memento;

public class Memento {

    private String content;

    public Memento(String savedContent){
        content = savedContent;
    }

    public String getSavedContent(){
        return content;
    }
}
