public class Main {
    public static void main(String[] args) {
        var pokemon11 = new Pokemon(
                "11", 50, 2, 9,
                new Skill("attack", Skill.Type.Attack, 8, 5),
                new Skill("heal", Skill.Type.Heal, 30, 1)
        );
        var pokemon12 = new Pokemon(
                "12", 65, 3, 7,
                new Skill("attack", Skill.Type.Attack, 9, 5),
                new Skill("thunderbolt", Skill.Type.Attack, 14, 1),
                new Skill("heal", Skill.Type.Heal, 25, 2)
        );
        var pokemon13 = new Pokemon(
                "13", 90, 1, 13,
                new Skill("attack", Skill.Type.Attack, 12, 10),
                new Skill("heal", Skill.Type.Heal, 15, 3)
        );
        var trainer1 = new Trainer("XiaoZhi", pokemon11);
        trainer1.addPokemon(pokemon12);
        trainer1.addPokemon(pokemon13);

        var pokemon21 = new Pokemon(
                "21", 80, 1, 11,
                new Skill("attack", Skill.Type.Attack, 20, 5),
                new Skill("strong attack", Skill.Type.Attack, 28, 2),
                new Skill("heal", Skill.Type.Heal, 40, 1)
        );
        var pokemon22 = new Pokemon(
                "22", 75, 2, 10,
                new Skill("attack", Skill.Type.Attack, 9, 8),
                new Skill("heal", Skill.Type.Heal, 20, 2)
        );
        var trainer2 = new Trainer("Paimon", pokemon21, pokemon22);
        var bf = new BattleField(trainer1, trainer2);

        System.out.println("---------------------------------");

        bf.battle("attack", "strong attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 1:\n" +
                "Trainer XiaoZhi's Pokemon 11: 22/50\n" +
                "Trainer Paimon's Pokemon 21: 64/80\n");

        System.out.println("---------------------------------");

        bf.battle("heal", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 2:\n" +
                "Trainer XiaoZhi's Pokemon 11: 32/50\n" +
                "Trainer Paimon's Pokemon 21: 64/80\n");

        System.out.println("---------------------------------");

        bf.battle("heal", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 3:\n" +
                "Trainer XiaoZhi's Pokemon 11: 12/50\n" +
                "Trainer Paimon's Pokemon 21: 64/80\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 4:\n" +
                "Trainer XiaoZhi's Pokemon 12: 65/65\n" +
                "Trainer Paimon's Pokemon 21: 64/80\n");

        System.out.println("---------------------------------");

        bf.battle("thunderbolt", "strong attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 5:\n" +
                "Trainer XiaoZhi's Pokemon 12: 37/65\n" +
                "Trainer Paimon's Pokemon 21: 22/80\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "heal");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 6:\n" +
                "Trainer XiaoZhi's Pokemon 12: 37/65\n" +
                "Trainer Paimon's Pokemon 21: 35/80\n");

        System.out.println("---------------------------------");

        bf.battle("heal", "heal");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 7:\n" +
                "Trainer XiaoZhi's Pokemon 12: 62/65\n" +
                "Trainer Paimon's Pokemon 21: 35/80\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 8:\n" +
                "Trainer XiaoZhi's Pokemon 12: 42/65\n" +
                "Trainer Paimon's Pokemon 21: 8/80\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 9:\n" +
                "Trainer XiaoZhi's Pokemon 12: 22/65\n" +
                "Trainer Paimon's Pokemon 22: 75/75\n");

        System.out.println("---------------------------------");

        bf.battle("heal", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 10:\n" +
                "Trainer XiaoZhi's Pokemon 12: 29/65\n" +
                "Trainer Paimon's Pokemon 22: 75/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 11:\n" +
                "Trainer XiaoZhi's Pokemon 12: 11/65\n" +
                "Trainer Paimon's Pokemon 22: 48/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 12:\n" +
                "Trainer XiaoZhi's Pokemon 13: 90/90\n" +
                "Trainer Paimon's Pokemon 22: 48/75\n");

        System.out.println("---------------------------------");

        bf.battle("heal", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 13:\n" +
                "Trainer XiaoZhi's Pokemon 13: 72/90\n" +
                "Trainer Paimon's Pokemon 22: 48/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 14:\n" +
                "Trainer XiaoZhi's Pokemon 13: 54/90\n" +
                "Trainer Paimon's Pokemon 22: 36/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 15:\n" +
                "Trainer XiaoZhi's Pokemon 13: 36/90\n" +
                "Trainer Paimon's Pokemon 22: 24/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 16:\n" +
                "Trainer XiaoZhi's Pokemon 13: 18/90\n" +
                "Trainer Paimon's Pokemon 22: 12/75\n");

        System.out.println("---------------------------------");

        bf.battle("heal", "heal");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 17:\n" +
                "Trainer XiaoZhi's Pokemon 13: 33/90\n" +
                "Trainer Paimon's Pokemon 22: 32/75\n");

        System.out.println("---------------------------------");

        bf.battle("heal", "heal");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 18:\n" +
                "Trainer XiaoZhi's Pokemon 13: 48/90\n" +
                "Trainer Paimon's Pokemon 22: 52/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 19:\n" +
                "Trainer XiaoZhi's Pokemon 13: 30/90\n" +
                "Trainer Paimon's Pokemon 22: 40/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 20:\n" +
                "Trainer XiaoZhi's Pokemon 13: 30/90\n" +
                "Trainer Paimon's Pokemon 22: 28/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 21:\n" +
                "Trainer XiaoZhi's Pokemon 13: 30/90\n" +
                "Trainer Paimon's Pokemon 22: 16/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 22:\n" +
                "Trainer XiaoZhi's Pokemon 13: 30/90\n" +
                "Trainer Paimon's Pokemon 22: 4/75\n");

        System.out.println("---------------------------------");

        bf.battle("attack", "attack");

        System.out.println("你的答案：");
        System.out.println(bf.toString());

        System.out.println("正确答案：");
        System.out.println("Turn 23:\n" +
                "Trainer XiaoZhi's Pokemon 13: 30/90\n" +
                "Trainer Paimon's Pokemon 22: 0/75\n" +
                "Winner: XiaoZhi\n");

        System.out.println("---------------------------------");

    }
}