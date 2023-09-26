package exercise.exercise2;

import java.util.StringTokenizer;
import java.util.TreeSet;

public class CalcSet {
    TreeSet<Integer> A, B;
    String op;

    public CalcSet(String data) {
        A = new TreeSet<Integer>();
        B = new TreeSet<Integer>();
        initData(data);
    }

    private void initData(String data) {
        StringTokenizer stringTokenizer = new StringTokenizer(data);
        boolean turnTrigger = false;
        String token;
        String regExr = "^[0-9]+$";

        try {
            while (stringTokenizer.hasMoreTokens()) {

                token = stringTokenizer.nextToken();
                if (!token.matches(regExr)) {
                    op = token;
                    turnTrigger = true;
                    continue;
                }

                if (!turnTrigger) {
                    A.add(Integer.parseInt(token));
                    continue;
                }

                B.add(Integer.parseInt(token));
            }
        } catch (NumberFormatException exception) {
            System.out.println("잘못된 값이 포함되어 있습니다.");
        }

    }

    private void printResult() {
        TreeSet<Integer> resultSet = new TreeSet<>(A);
        switch (op) {
            case "+":
                Plus.apply(B, resultSet);
                break;
            case "*":
                Muilty.apply(B, resultSet);
                break;
            case "-":
                Minus.apply(B, resultSet);
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 연산자");

        }
    }
}
