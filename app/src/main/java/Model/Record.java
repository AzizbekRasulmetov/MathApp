package Model;

public class Record {

    private int id;
    private String name;
    private String score;

    public Record(){}

    public Record(String name, String score){
        this.name = name;
        this.score = score;
    }

    public Record(int id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
