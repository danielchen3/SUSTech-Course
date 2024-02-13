public class BattleField {
    private Trainer trainerA;
    private Trainer trainerB;
    private int turn = 1;

    public BattleField(Trainer trainerA, Trainer trainerB) {
        this.trainerA = trainerA;
        this.trainerB = trainerB;
    }

    public void battle(String skillOfA, String skillOfB) {
        if (trainerA.getActivatePokemon().getSpeed() >= trainerB.getActivatePokemon().getSpeed()) {
            if (trainerA.getActivatePokemon().getHP() > 0) {
                for (int i = 0; i < trainerA.getActivatePokemon().getSkills().size(); i++) {
                    if (skillOfA.equals(trainerA.getActivatePokemon().getSkills().get(i).getName()) && trainerA.getActivatePokemon().getSkills().get(i).getPP() > 0) {
                        if (trainerA.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Attack)) {
                            trainerA.getActivatePokemon().useSkillTo(trainerB.getActivatePokemon(), trainerA.getActivatePokemon().getSkills().get(i));
                            break;
                        } else if (trainerA.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Heal)) {
                            trainerA.getActivatePokemon().useSkillTo(trainerA.getActivatePokemon(), trainerA.getActivatePokemon().getSkills().get(i));
                            break;
                        }
                    }
                }
            }
            if (trainerB.getActivatePokemon().getHP() > 0) {
                for (int i = 0; i < trainerB.getActivatePokemon().getSkills().size(); i++) {
                    if (skillOfB.equals(trainerB.getActivatePokemon().getSkills().get(i).getName()) && trainerB.getActivatePokemon().getSkills().get(i).getPP() > 0) {
                        if (trainerB.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Attack)) {
                            trainerB.getActivatePokemon().useSkillTo(trainerA.getActivatePokemon(), trainerB.getActivatePokemon().getSkills().get(i));
                            break;
                        } else if (trainerB.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Heal)) {
                            trainerB.getActivatePokemon().useSkillTo(trainerB.getActivatePokemon(), trainerB.getActivatePokemon().getSkills().get(i));
                            break;
                        }
                    }
                }
            }
        } else if (trainerB.getActivatePokemon().getSpeed() > trainerA.getActivatePokemon().getSpeed()) {
            if (trainerB.getActivatePokemon().getHP() > 0) {
                for (int i = 0; i < trainerB.getActivatePokemon().getSkills().size(); i++) {
                    if (skillOfB.equals(trainerB.getActivatePokemon().getSkills().get(i).getName()) && trainerB.getActivatePokemon().getSkills().get(i).getPP() > 0) {
                        if (trainerB.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Attack)) {
                            trainerB.getActivatePokemon().useSkillTo(trainerA.getActivatePokemon(), trainerB.getActivatePokemon().getSkills().get(i));
                            break;
                        } else if (trainerB.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Heal)) {
                            trainerB.getActivatePokemon().useSkillTo(trainerB.getActivatePokemon(), trainerB.getActivatePokemon().getSkills().get(i));
                            break;
                        }
                    }
                }
            }
            if (trainerA.getActivatePokemon().getHP() > 0) {
                for (int i = 0; i < trainerA.getActivatePokemon().getSkills().size(); i++) {
                    if (skillOfA.equals(trainerA.getActivatePokemon().getSkills().get(i).getName()) && trainerA.getActivatePokemon().getSkills().get(i).getPP() > 0) {
                        if (trainerA.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Attack)) {
                            trainerA.getActivatePokemon().useSkillTo(trainerB.getActivatePokemon(), trainerA.getActivatePokemon().getSkills().get(i));
                            break;
                        } else if (trainerA.getActivatePokemon().getSkills().get(i).getType().equals(Skill.Type.Heal)) {
                            trainerA.getActivatePokemon().useSkillTo(trainerA.getActivatePokemon(), trainerA.getActivatePokemon().getSkills().get(i));
                            break;
                        }
                    }
                }
            }
        }
    }

    public int checkWin() {
        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < trainerA.getPokemons().size(); i++) {
            if (trainerA.getPokemons().get(i).isAlive() == true) {
                flag1 = true;
                break;
            }
        }
        for (int i = 0; i < trainerB.getPokemons().size(); i++) {
            if (trainerB.getPokemons().get(i).isAlive() == true) {
                flag2 = true;
                break;
            }
        }
        if (flag1 == false && flag2 == true) return 2;
        else if (flag1 == true && flag2 == false) return 1;
        else return 0;
    }

    public String toString() {
        turn++;
        if (!trainerA.getActivatePokemon().isAlive()) trainerA.summon();
        if (!trainerB.getActivatePokemon().isAlive()) trainerB.summon();
        if (checkWin() == 0) {
            return "Turn " + (turn - 1) + ":" + "\n" + "Trainer " + trainerA.getName() + "'s " + "Pokemon " + trainerA.getActivatePokemon().toString() + "\n" + "Trainer " + trainerB.getName() + "'s " + "Pokemon " + trainerB.getActivatePokemon().toString() + "\n";
        } else if (checkWin() == 1) {
            return "Turn " + (turn - 1) + ":" + "\n" + "Trainer " + trainerA.getName() + "'s " + "Pokemon " + trainerA.getActivatePokemon().toString() + "\n" + "Trainer " + trainerB.getName() + "'s " + "Pokemon " + trainerB.getActivatePokemon().toString() + "\n" + "Winner: " + trainerA.getName();
        } else {
            return "Turn " + (turn - 1) + ":" + "\n" + "Trainer " + trainerA.getName() + "'s " + "Pokemon " + trainerA.getActivatePokemon().toString() + "\n" + "Trainer " + trainerB.getName() + "'s " + "Pokemon " + trainerB.getActivatePokemon().toString() + "\n" + "Winner: " + trainerB.getName();
        }
    }
}
