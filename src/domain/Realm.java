package domain;

import domain.monsters.lvl1.Goblin;
import domain.monsters.lvl1.Skeleton;
import domain.player.Hero;
import entity.FantasyCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader br;
    private static FantasyCharacter player = null;
    private static BattleScene battleScene = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.println("Please enter your gamer's name:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("%s will be saving our world from dragons. Wish your hero good luck!", player.getName()));
            printNavigation();
        }

        switch (string) {
            case "1": {
                System.out.println("The merchant hasn't arrived yet.");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "yes":
                command("2");
                break;
            case "no": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s won! Now you have %d units of skills and %d units of gold. You also have %d units of health.", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints()));
                System.out.println("Would you like to continue your adventures or return to the town? (yes/no)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    private static void printNavigation() {
        System.out.println("Where would you like to go?");
        System.out.println("1. To the merchant.");
        System.out.println("2. To the dark forest.");
        System.out.println("3. To the exit.");
    }

    private static FantasyCharacter createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(
                "Goblin",
                50,
                10,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Skeleton",
                25,
                20,
                20,
                100,
                10
        );
    }

    interface FightCallback {
        void fightWin();
        void fightLost();
    }
}
