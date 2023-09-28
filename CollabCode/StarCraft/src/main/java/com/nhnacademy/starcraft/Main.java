package com.nhnacademy.starcraft;


import com.nhnacademy.starcraft.player.Player;
import com.nhnacademy.starcraft.unit.protos.*;
import com.nhnacademy.starcraft.unit.terran.*;
import com.nhnacademy.starcraft.unit.tribe.Tribe;
import com.nhnacademy.starcraft.unit.zerg.*;

import java.util.InputMismatchException;
import java.util.Scanner;

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
            System.out.print("굥격을 수행할 아군 유닛 공격할 적군 유닛을 선택하세요 (ex)1 3)\n > ");
            try {
                userUnit = scanner.nextInt();
                enemyUnit = scanner.nextInt();
                playerAttackToComputer(userUnit, enemyUnit);
                computerAttackToPlayer();
            } catch (InputMismatchException exception) {
                System.out.println("\n두 개의정수를 입력하십시오 !");
                scanner.nextLine();
            }
        }


    }

    /*
    플레이어가 공격
     */
    private static void playerAttackToComputer(int userUnit, int enemyUnit) {
        if((userUnit >= player.getUnitListSize() && userUnit < 0)
                || (enemyUnit < 0 && enemyUnit >= computer.getUnitListSize())){
            throw new IllegalArgumentException("인덱스 오류");
        }
        computer.getDamageMyUnit(enemyUnit, player.getUnit(userUnit).getAttack());

    }

    /*
    컴퓨터가 랜덤 공격
     */
    private static void computerAttackToPlayer(){
        int randomComputerUnit;
        int randomUserUnit;
        randomComputerUnit = (int)(Math.random() * computer.getUnitListSize());
        randomUserUnit = (int)(Math.random() * player.getUnitListSize());
        player.getDamageMyUnit(randomUserUnit, computer.getUnit(randomComputerUnit).getAttack());
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
            random = (int) (Math.random() * 5 + 1);
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
            random = (int) (Math.random() * 5 + 1);
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
            random = (int) (Math.random() * 5 + 1);
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
                default:
                    throw new IllegalArgumentException("저그 유닛 생성 오류");
            }
        }
    }
}