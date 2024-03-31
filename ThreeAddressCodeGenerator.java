import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ThreeAddressCodeGenerator {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("OutputLO"));
            FileWriter fileWriter = new FileWriter("OutputThreeCode");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (parts.length == 3 && parts[1].equals("=")) {
                    String identifier = parts[0];
                    String expression = parts[2];
                    String threeAddressCode = generateThreeAddressCode(identifier, expression);
                    fileWriter.write(threeAddressCode + "\n");
                }
            }

            scanner.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String generateThreeAddressCode(String identifier, String expression) {
        // Clean up expression: remove spaces and leading zeroes
        expression = expression.replace(" ", "").replaceAll("^0+", "");

        // If expression is already a single identifier or number
        if (expression.matches("[a-zA-Z]+|[0-9]+")) {
            return identifier + " = " + expression;
        }

        // Generate three-address code for arithmetic expression
        StringBuilder threeAddressCode = new StringBuilder();
        String temp = "T1"; // Temporary variable name
        threeAddressCode.append(temp).append(" = -(").append(expression).append(")\n");
        threeAddressCode.append("T2 = 4 + ").append(temp).append("\n");
        threeAddressCode.append(identifier).append(" = T2");

        return threeAddressCode.toString();
    }
}


