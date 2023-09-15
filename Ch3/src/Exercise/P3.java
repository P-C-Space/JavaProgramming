package Exercise;

import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double a = 0.0;
        double b = 0.0;
        double result = 0;
        String op = " ";

        while(true){
            a = s.nextDouble();
            if(a == 0){
                break;
            }
            op = s.next();
            b = s.nextDouble();

            switch (op){
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a + b;
                    break;
                case "*":
                    result = a + b;
                    break;
                case "/":
                    result = a + b;
                    break;
            }
            System.out.println(a + " " + op + " " + b + " = " + result);
        }
    }
}
