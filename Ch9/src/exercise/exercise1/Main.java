package exercise.exercise1;

import java.math.BigInteger;

//package Ch9.src.exercise.exercise1;

import java.util.Scanner;

public class Main {
    private static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("0보다 작은 값은 허용하지 않습니다.");
        }
        if (n > 16) {
            throw new IllegalArgumentException("16보다 큰 값은 자료형 int가 감당하지 못합니다.");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    private static BigInteger factorialBigInteger(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("0보다 작은 값은 허용하지 않습니다.");
        }

        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }

        return factorialBigInteger(n - 1).multiply(BigInteger.valueOf(n));
    }

    private static void factorialTest(Scanner scanner) {

        int input = scanner.nextInt();
        int result = factorial(input);
        System.out.println(input + " factorial 값 : " + result);
    }

    private static void factorialBigIntegerTest(Scanner scanner) {
        long input = scanner.nextLong();
        System.out.println(input + " factorial 값 : " +  factorialBigInteger(input););
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        factorialTest(scanner);
        factorialBigIntegerTest(scanner);
        scanner.close();
    }
}
