package co.com.psl.models;

import java.util.List;

/**
 * Created by jvanegasp on 1/02/2017.
 */

public class Pokemon {

    private long id;
    private String name;
    private List<Type> type;
    private List<Type> weakness;
    private long evolutionId;
    private String image;

    public Pokemon(long id, String name, List<Type> type, List<Type> weakness, long evolutionId, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weakness = weakness;
        this.evolutionId = evolutionId;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Type> getType() {
        return type;
    }

    public List<Type> getWeakness() {
        return weakness;
    }

    public long getEvolutionId() {
        return evolutionId;
    }

    public String getImage() {
        return image;
    }
}
