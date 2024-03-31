import java.util.Scanner;

public class BUthreeac {
    static int i, ch, j, l, addr = 100;
    static String ex;
    static char[] exp = new char[10], exp1 = new char[10], exp2 = new char[10], id1 = new char[5], op = new char[5], id2 = new char[5];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Assignment\n2. Arithmetic\n3. Relational\n4. Exit\nEnter the choice:");
            ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter the expression with assignment operator:");
                    exp = scanner.next().toCharArray();
                    l = exp.length;
                    exp2 = new char[10];
                    i = 0;
                    while (exp[i] != '=') {
                        i++;
                    }
                    System.arraycopy(exp, 0, exp2, 0, i);
                    reverse(exp);
                    exp1 = new char[10];
                    System.arraycopy(exp, l - (i + 1), exp1, 0, l - (i + 1));
                    reverse(exp1);
                    System.out.println("Three address code:\ntemp=" + new String(exp1) + "\n" + new String(exp2) + "=temp");
                    break;
                case 2:
                    System.out.println("Enter the expression with arithmetic operator:");
                    ex = scanner.next();
                    exp = ex.toCharArray();
                    l = exp.length;
                    exp1 = new char[10];
                    for (i = 0; i < l; i++) {
                        if (exp[i] == '+' || exp[i] == '-') {
                            if (exp[i + 2] == '/' || exp[i + 2] == '*') {
                                pm();
                                break;
                            } else {
                                plus();
                                break;
                            }
                        } else if (exp[i] == '/' || exp[i] == '*') {
                            div();
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter the expression with relational operator:");
                    id1 = scanner.next().toCharArray();
                    op = scanner.next().toCharArray();
                    id2 = scanner.next().toCharArray();
                    if (!(new String(op).equals("<") || new String(op).equals(">") || new String(op).equals("<=") ||
                            new String(op).equals(">=") || new String(op).equals("==") || new String(op).equals("!="))) {
                        System.out.println("Expression is error");
                    } else {
                        System.out.printf("\n%d\tif %s%s%s goto %d", addr, new String(id1), new String(op), new String(id2), addr + 3);
                        addr++;
                        System.out.printf("\n%d\t T:=0", addr);
                        addr++;
                        System.out.printf("\n%d\t goto %d", addr, addr + 2);
                        addr++;
                        System.out.printf("\n%d\t T:=1", addr);
                    }
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

    static void pm() {
        reverse(exp);
        j = l - i - 1;
        exp1 = new char[10];
        System.arraycopy(exp, 0, exp1, 0, j);
        reverse(exp1);
        System.out.println("Three address code:\ntemp=" + new String(exp1) + "\ntemp1=" + exp[j + 1] + exp[j]);
    }

    static void div() {
        exp1 = new char[10];
        System.arraycopy(exp, i + 2, exp1, 0, exp.length - (i + 2));
        System.out.println("Three address code:\ntemp=" + new String(exp1) + "\ntemp1=temp" + exp[i + 2] + exp[i + 3]);
    }

    static void plus() {
        exp1 = new char[10];
        System.arraycopy(exp, i + 2, exp1, 0, exp.length - (i + 2));
        System.out.println("Three address code:\ntemp=" + new String(exp1) + "\ntemp1=temp" + exp[i + 2] + exp[i + 3]);
    }

    static void reverse(char[] array) {
        int start = 0;
        int end = array.length - 1;
        char temp;
        while (start < end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}
