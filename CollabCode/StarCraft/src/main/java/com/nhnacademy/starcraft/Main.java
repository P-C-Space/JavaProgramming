package com.nhnacademy.starcraft;


import com.nhnacademy.starcraft.player.Player;
import com.nhnacademy.starcraft.unit.protos.Carrier;
import com.nhnacademy.starcraft.unit.protos.Corsair;
import com.nhnacademy.starcraft.unit.protos.Dragoon;
import com.nhnacademy.starcraft.unit.protos.HighTempler;
import com.nhnacademy.starcraft.unit.protos.Scout;
import com.nhnacademy.starcraft.unit.protos.Zealot;
import com.nhnacademy.starcraft.unit.terran.BattleCruzer;
import com.nhnacademy.starcraft.unit.terran.Goliath;
import com.nhnacademy.starcraft.unit.terran.Marine;
import com.nhnacademy.starcraft.unit.terran.Tank;
import com.nhnacademy.starcraft.unit.terran.Valkyrie;
import com.nhnacademy.starcraft.unit.terran.Wraith;
import com.nhnacademy.starcraft.unit.tribe.Tribe;
import com.nhnacademy.starcraft.unit.zerg.Guardian;
import com.nhnacademy.starcraft.unit.zerg.Hydralisk;
import com.nhnacademy.starcraft.unit.zerg.Mutalisk;
import com.nhnacademy.starcraft.unit.zerg.Queen;
import com.nhnacademy.starcraft.unit.zerg.Ultralisk;
import com.nhnacademy.starcraft.unit.zerg.Zergling;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
1. 게임에는 Terran, Protos, Zerg 등 3개의 종족이 존재합니다
2. 각 종족들은 각각의 유닛들을 가지며 유닛은 날수 있는 유닛과 날수 없는 유닛으로 나뉩니다.
3. 날 수 있는 유닛은 날 수 있는 유닛과 날 수 없는 유닛 모두를 공격할 수 있습니다.
4. 날 수 없는 유닛은 날 수 있는 유닛을 공격할 수 없습니다.
5. 미사일, 레이저 포 또는 침을 가진 유닛은 날 수 있는 유닛을 공격할 수 있습니다.
6. 각 유닛은 공격력과 방어력을 가집니다.
7. 한 유닛은 한 유닛을 공격할 수 있고, 공격 받은 유닛은 공격한 유닛이 가진 공격력만큼 방어력이 감소됩니다.
방어력이 0이된 유닛은 소멸됩니다.
 */
public class Main {

    private static Player player, computer;

    public static void main(String[] args) {
        GameManagement();
    }

    /*
    Game관리
     */
    private static void GameManagement() {
        Scanner scanner = new Scanner(System.in);
        createPlayers(scanner);
        createUnit();

        GameStart(scanner);

        scanner.close();
    }

    private static void GameStart(Scanner scanner) {
        int userUnit;
        int enemyUnit;
        while (true) {
            printUsersState();
            if (whoIsWinner()) {
                break;
            }
            System.out.print("굥격을 수행할 아군 유닛 공격할 적군 유닛을 선택하세요 (ex)1 3)\n > ");
            try {
                userUnit = scanner.nextInt();
                enemyUnit = scanner.nextInt();
                playerAttackToComputer(userUnit, enemyUnit);
                if (whoIsWinner()) {
                    break;
                }

                computerAttackToPlayer();
            } catch (InputMismatchException exception) {
                System.out.println("\n두 개의정수를 입력하십시오 !");
                scanner.nextLine();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }


    }

    /*
    who is winner
     */
    private static boolean whoIsWinner() {
        if (player.getUnitListSize() <= 0 || unableAttack(player, computer)) {
            System.out.println("패배...");
            return true;
        } else if (computer.getUnitListSize() <= 0 || unableAttack(computer,player)) {
            printUsersState();
            System.out.println("승리 !");
            return true;
        } else {
            return false;
        }
    }

    /*
    지상 유닛뿐인데 공중공격을 못한다면.. 패배
     */
    private static boolean unableAttack(Player player, Player enemy) {
        if(!hasAttackToFlyUnit(player) && hasAttackToFlyUnit(enemy)){
            return true;
        }
        return false;
    }

    private static boolean hasAttackToFlyUnit(Player player) {
        for (int i = 0; i < player.getUnitListSize(); i++) {
            if (player.getUnit(i).getFlyAttackAble()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasFlyUnit(Player player) {
        for (int i = 0; i < player.getUnitListSize(); i++) {
            if (player.getUnit(i).getIsFly()) {
                return true;
            }
        }
        return false;
    }

    /*
    플레이어가 공격
     */
    private static void playerAttackToComputer(int userUnit, int enemyUnit) {
        if ((userUnit >= player.getUnitListSize() || userUnit < 0)
                || (enemyUnit < 0 || enemyUnit >= computer.getUnitListSize())) {
            throw new IllegalArgumentException("인덱스 오류");
        }

        if (!attackAble(player, userUnit, computer, enemyUnit)) {
            throw new IllegalArgumentException("특수한 지상 유닛이 아니면 공중 공격을 못합니다.");
        }

        System.out.println("아군의 공격 ! : "
                + player.getUnit(userUnit).getName()
                + " => " + computer.getUnit(enemyUnit).getName());

        computer.getDamageMyUnit(enemyUnit, player.getUnit(userUnit).getAttack());
    }

    /*
    컴퓨터가 랜덤 공격
     */
    private static void computerAttackToPlayer() {
        int randomComputerUnit;
        int randomUserUnit;

        while (true) {
            randomComputerUnit = (int) (Math.random() * computer.getUnitListSize());
            randomUserUnit = (int) (Math.random() * player.getUnitListSize());
            if (attackAble(computer, randomComputerUnit, player, randomUserUnit)) {
                break;
            }
        }

        System.out.println("적군의 공격 ! : "
                + computer.getUnit(randomComputerUnit).getName()
                + " => " + player.getUnit(randomUserUnit).getName());

        player.getDamageMyUnit(randomUserUnit, computer.getUnit(randomComputerUnit).getAttack());
    }

    /*
    날 수 있는 적을 공격가능한지 여부
     */
    private static boolean attackAble(Player user, int userUnit, Player enemy, int enemyUnit) {
        if (enemy.getUnit(enemyUnit).getIsFly()) {
            if (!user.getUnit(userUnit).getFlyAttackAble()) {
                return false;
            }
        }
        return true;
    }

    /*
    유저 상태들
     */
    private static void printUsersState() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***********************************\n");
        stringBuilder.append("적군: ");
        stringBuilder.append(computer + "\n");

        stringBuilder.append("아군: ");
        stringBuilder.append(player);
        stringBuilder.append("***********************************");
        System.out.println(stringBuilder);
    }


    /*
    일반 유저와 컴퓨터 유저 생성
     */
    private static void createPlayers(Scanner scanner) {
        choiceTribeUser(scanner);
        randomTribeComputer();
    }

    /*
    Player 종족 선택
     */
    private static void choiceTribeUser(Scanner scanner) {

        StringBuilder stringBuilder = new StringBuilder();
        String userTribe;
        stringBuilder.append("***********************************\n\n")
                .append(" 콘솔 스타크래프트에 오신것을 환영합니다.\n\n")
                .append("***********************************\n")
                .append("               종족\n")
                .append("           1. Terran\n")
                .append("           2. Protos\n")
                .append("            3. Zerg\n")
                .append("***********************************\n")
                .append("종족을 선택하세요 (숫자)> ");

        while (true) {
            System.out.print(stringBuilder);
            userTribe = scanner.nextLine();
            try {
                switch (userTribe) {
                    case "1":
                        player = new Player(Tribe.TERRARN);
                        return;
                    case "2":
                        player = new Player(Tribe.PROTOS);
                        return;
                    case "3":
                        player = new Player(Tribe.ZERG);
                        return;
                    default:
                        throw new IllegalArgumentException("잘못된 선택입니다.");
                }

            } catch (IllegalArgumentException exception) {
                System.out.println("\n Error in input : " + exception.getMessage());
            }

        }

    }

    /*
    컴퓨터의 종족 선택
     */
    private static void randomTribeComputer() {
        int random = (int) (Math.random() * 3 + 1);
        switch (random) {
            case 1:
                computer = new Player(Tribe.TERRARN);
                break;
            case 2:
                computer = new Player(Tribe.PROTOS);
                break;
            case 3:
                computer = new Player(Tribe.ZERG);
                break;

        }
    }

    /*
    종족에 따른 유닛 생성
     */
    private static void createUnit() {
        createPlayerUnit(player);
        createPlayerUnit(computer);
    }

    /*
    유저 종족 생성
     */
    private static void createPlayerUnit(Player player) {
        switch (player.getType()) {
            case TERRARN:
                createTerran(player);
                break;
            case PROTOS:
                createProtos(player);
                break;
            case ZERG:
                createZerg(player);
                break;
            default:
                throw new IllegalArgumentException("종족에 잘못된 값이 존재");
        }
    }


    /*
    무작위 테란 유닛 생성
     */
    private static void createTerran(Player player) {
        int random;
        for (int i = 0; i < 5; i++) {
            random = (int) (Math.random() * 6 + 1);
            switch (random) {
                case 1:
                    player.addUnit(new Marine());
                    break;
                case 2:
                    player.addUnit(new Tank());
                    break;
                case 3:
                    player.addUnit(new Goliath());
                    break;
                case 4:
                    player.addUnit(new Wraith());
                    break;
                case 5:
                    player.addUnit(new Valkyrie());
                    break;
                case 6:
                    player.addUnit(new BattleCruzer());
                    break;
                default:
                    throw new IllegalArgumentException("테란 유닛 생성 오류");
            }
        }
    }

    /*
    무작위 프로토스 유닛 생성
     */
    private static void createProtos(Player player) {
        int random;
        for (int i = 0; i < 4; i++) {
            random = (int) (Math.random() * 6 + 1);
            switch (random) {
                case 1:
                    player.addUnit(new Zealot());
                    break;
                case 2:
                    player.addUnit(new Dragoon());
                    break;
                case 3:
                    player.addUnit(new HighTempler());
                    break;
                case 4:
                    player.addUnit(new Scout());
                    break;
                case 5:
                    player.addUnit(new Corsair());
                    break;
                case 6:
                    player.addUnit(new Carrier());
                    break;
                default:
                    throw new IllegalArgumentException("프로토스 유닛 생성 오류");
            }
        }
    }

    /*
    무작위 저그 유닛 생성
     */
    private static void createZerg(Player player) {
        int random;
        for (int i = 0; i < 8; i++) {
            random = (int) (Math.random() * 6 + 1);
            switch (random) {
                case 1:
                    player.addUnit(new Zergling());
                    break;
                case 2:
                    player.addUnit(new Hydralisk());
                    break;
                case 3:
                    player.addUnit(new Ultralisk());
                    break;
                case 4:
                    player.addUnit(new Mutalisk());
                    break;
                case 5:
                    player.addUnit(new Guardian());
                    break;
                case 6:
                    player.addUnit(new Queen());
                    break;
                default:
                    throw new IllegalArgumentException("저그 유닛 생성 오류");
            }
        }
    }
}