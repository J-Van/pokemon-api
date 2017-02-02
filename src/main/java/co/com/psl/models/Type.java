package co.com.psl.models;

/**
 * Created by jvanegasp on 1/02/2017.
 */
public class Type {

    private long id;
    private String name;

    public Type(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
