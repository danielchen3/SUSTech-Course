import java.util.ArrayList;

public class Trainer {
    private String name;
    private ArrayList<Pokemon> pokemons;
    private Pokemon activatePokemon;

    public void setName(String name) {
        this.name = name;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void setActivatePokemon(Pokemon activatePokemon) {
        this.activatePokemon = activatePokemon;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public Pokemon getActivatePokemon() {
        return activatePokemon;
    }

    public Trainer(String name, Pokemon... pokemons) {
        this.name = name;
        int t = 0;
        int count = 0;
        ArrayList<Pokemon> temp3 = new ArrayList<>();
        while (count < 6 && t < pokemons.length) {
            boolean flag = false;
            for (int j = 0; j < t; j++) {
                if (pokemons[t].getName().equals(pokemons[j].getName())) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                temp3.add(pokemons[t]);
                count++;
            }
            t++;
        }
        this.pokemons = temp3;
        summon();
    }

    public void addPokemon(Pokemon... poke) {
        int ini = pokemons.size();
        int t = 0;
        int count = ini;
        while (count < 6 && t < poke.length) {
            boolean flag = false;
            for (int j = 0; j < t; j++) {
                if (poke[t].getName().equals(poke[j].getName())) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                pokemons.add(poke[t]);
                count++;
            }
            t++;
        }
    }

    public void removePokemon(String... name) {
        for (int i = 0; i < name.length; i++) {
            for (int j = 0; j < pokemons.size(); j++) {
                if (name[i].equals(pokemons.get(j).getName())) pokemons.remove(j);
            }
        }
    }

    public boolean isAlive(String name) {
        for (int i = 0; i < pokemons.size(); i++) {
            if (name.equals(pokemons.get(i).getName()) && pokemons.get(i).getHP() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isAlive(Pokemon poke) {
        if (poke.getHP() > 0) return true;
        return false;
    }

    public Pokemon getPokemon(String name) {
        Pokemon temp = null;
        for (int i = 0; i < pokemons.size(); i++) {
            if (name.equals(pokemons.get(i).getName())) {
                temp = pokemons.get(i);
                break;
            }
        }
        return temp;
    }

    public Pokemon summon() {
        Pokemon temp = null;
        for (int i = 0; i < this.pokemons.size(); i++) {
            if (this.pokemons.get(i).isAlive()) {
                temp = this.pokemons.get(i);
                this.activatePokemon = temp;
                break;
            }
        }
        return temp;
    }

    public Pokemon summon(String name) {
        Pokemon temp = null;
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getName().equals(name) && pokemons.get(i).isAlive()) {
                temp = pokemons.get(i);
                activatePokemon = temp;
                break;
            }
        }
        return temp;
    }

    /*public static void main(String[] args) {
        Skill attack = new Skill("Beat", Skill.Type.Attack, 100, 10);
        Skill heal = new Skill("Heal", Skill.Type.Heal, 6, 10);

        Pokemon pokemon1 = new Pokemon("p1", 50, 2, 20, attack, heal);
        Pokemon pokemon2 = new Pokemon("p2", 50, 2, 20, attack, heal);
        Pokemon pokemon3 = new Pokemon("p3", 50, 2, 20, attack, heal);
        Pokemon pokemon4 = new Pokemon("p4", 50, 2, 20, attack, heal);

        Trainer trainer = new Trainer("Traveler", pokemon1, pokemon2, pokemon3);
        Trainer trainer2=new Trainer("DD",pokemon4);
        System.out.println(trainer.getPokemons());
        System.out.println(trainer2.getPokemons());
    }*/
}
