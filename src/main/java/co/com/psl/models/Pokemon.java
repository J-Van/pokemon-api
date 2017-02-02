package co.com.psl.models;

import java.util.List;

/**
 * Created by jvanegasp on 1/02/2017.
 */

public class Pokemon {

    private String id;
    private String name;
    private List<Type> type;
    private List<Type> weakness;
    private String evolutionId;
    private String image;

    public Pokemon(String id, String name, List<Type> type, List<Type> weakness, String evolutionId, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weakness = weakness;
        this.evolutionId = evolutionId;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Type> getType() { return type; }

    public List<Type> getWeakness() {
        return weakness;
    }

    public String getEvolutionId() {
        return evolutionId;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setType(List<Type> type) { this.type = type; }

    public void setWeakness(List<Type> weakness) { this.weakness = weakness; }

    public void setEvolutionId(String evolutionId) { this.evolutionId = evolutionId; }

    public void setImage(String image) { this.image = image; }
}
