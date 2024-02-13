import java.util.ArrayList;

public class Pokemon {
    private String name;
    private int HP;
    private int maxHP;
    private int attack;
    private int speed;
    private ArrayList<Skill> skills;

    public void setName(String name) {
        this.name = name;
    }

    public void setHP(int HP) {
        if (HP < 0) HP = 0;
        if (HP > this.maxHP) HP = maxHP;
        this.HP = HP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public Pokemon(String name, int maxhp, int attack, int speed, Skill... skills) {
        this.name = name;
        this.maxHP = maxhp;
        this.HP = maxhp;
        this.attack = attack;
        this.speed = speed;
        // Make a copy from the argument "skills" into your own "this.skills"
        ArrayList<Skill> temp2 = new ArrayList<>();
        for (int i = 0; i < skills.length; i++) {
            Skill sk = new Skill(skills[i].getName(), skills[i].getType(), skills[i].getPower(), skills[i].getPP());
            temp2.add(sk);
        }
        this.skills = temp2;
    }

    public boolean isAlive() {
        if (this.getHP() > 0) return true;
        return false;
    }

    public void useSkillTo(Pokemon target, Skill skill) {
        if (skill.getType().equals(Skill.Type.Heal)) {
            target.HP += skill.getPower();
            skill.setPP(skill.getPP() - 1);
            if (target.HP > target.maxHP) target.HP = target.maxHP;
        }
        if (skill.getType().equals(Skill.Type.Attack)) {
            target.HP -= (skill.getPower() * this.getAttack());
            skill.setPP(skill.getPP() - 1);
            if (target.HP < 0) target.HP = 0;
        }
    }

    //@Override
    public String toString() {
        return this.getName() + ":" + " " + this.getHP() + "/" + this.getMaxHP();
    }

    /*public static void main(String[] args) {
        Skill attack = new Skill("Beat", Skill.Type.Attack, 5, 10);
        Skill heal = new Skill("Heal", Skill.Type.Heal, 6, 10);
        Pokemon Pikachu = new Pokemon("Pikachu", 50, 2, 20, attack, heal);
        Pokemon Charmander = new Pokemon("Charmander", 50, 3, 10, attack, heal);

        System.out.println(Pikachu.skills);
        System.out.println(Charmander.skills);
    }*/
}
