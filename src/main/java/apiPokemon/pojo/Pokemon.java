package apiPokemon.pojo;

import java.util.List;

public class Pokemon {
    private String name;
    private int base_experience;
    private List<String> abilities;
    private String cryUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public String getCryUrl() {
        return cryUrl;
    }

    public void setCryUrl(String cryUrl) {
        this.cryUrl = cryUrl;
    }

    @Override
    public String toString() {
        return "=== Pokémon Info ===\n" +
                "Name: " + name + "\n" +
                "Base Experience: " + base_experience + "\n" +
                "Abilities: " + String.join(", ", abilities) + "\n" +
                "Cry: " + cryUrl + "\n";
    }
}
