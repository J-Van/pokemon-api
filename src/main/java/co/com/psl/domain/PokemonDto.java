package co.com.psl.domain;

import java.util.List;
import java.util.Objects;

public class PokemonDto {

    private String id;
    private String name;
    private List<String> type;
    private List<String> weakness;
    private String evolutionId;
    private String image;

    public PokemonDto(String id, String name, List<String> type, List<String> weakness, String evolutionId, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weakness = weakness;
        this.evolutionId = evolutionId;
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PokemonDto)) return false;
        PokemonDto other = (PokemonDto) obj;
        if (!Objects.equals(this.getId(), other.getId())) return false;
        if (!Objects.equals(this.getName(), other.getName())) return false;
        if (!Objects.equals(this.getEvolutionId(), other.getEvolutionId())) return false;
        if (!Objects.equals(this.getImage(), other.getImage())) return false;
        for (int i = 0; i < this.getType().size(); i++) {
            if (!Objects.equals(this.getType().get(i), other.getType().get(i))) return false;
        }
        for (int i = 0; i < this.getWeakness().size(); i++) {
            if (!Objects.equals(this.getWeakness().get(i), other.getWeakness().get(i))) return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getWeakness() {
        return weakness;
    }

    public void setWeakness(List<String> weakness) {
        this.weakness = weakness;
    }

    public String getEvolutionId() {
        return evolutionId;
    }

    public void setEvolutionId(String evolutionId) {
        this.evolutionId = evolutionId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
