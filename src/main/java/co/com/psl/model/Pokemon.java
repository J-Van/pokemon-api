package co.com.psl.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private List<Type> type;

    @ManyToMany
    private List<Type> weakness;

    @OneToOne
    private Pokemon evolution;

    @Column(nullable = false)
    private String image;

    protected Pokemon() {}

    public Pokemon(String name, List<Type> type, List<Type> weakness, Pokemon evolution, String image) {
        this.name = name;
        this.type = type;
        this.weakness = weakness;
        this.evolution = evolution;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Type> getType() { return type; }

    public List<Type> getWeakness() {
        return weakness;
    }

    public Pokemon getEvolution() {
        return evolution;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setType(List<Type> type) { this.type = type; }

    public void setWeakness(List<Type> weakness) { this.weakness = weakness; }

    public void setEvolution(Pokemon evolution) { this.evolution = evolution; }

    public void setImage(String image) { this.image = image; }
}
