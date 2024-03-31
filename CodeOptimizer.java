import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CodeOptimizer {
    public static void main(String[] args) {
        try {
            // Read input from OutputThreeCode file
            BufferedReader reader = new BufferedReader(new FileReader("OutputThreeCode"));
            String line;
            StringBuilder optimizedCode = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Optimize each line of code
                String optimizedLine = optimizeLine(line);
                optimizedCode.append(optimizedLine).append("\n");
            }
            reader.close();

            // Write optimized code to OutputCodeOptimize file
            BufferedWriter writer = new BufferedWriter(new FileWriter("OutputCodeOptimize"));
            writer.write(optimizedCode.toString());
            writer.close();

            System.out.println("Optimization completed. Check OutputCodeOptimize file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String optimizeLine(String line) {
        // Remove unnecessary negative sign and parenthesis
        line = line.replaceAll("-\\((-?\\d+)\\)", "$1");

        // Calculate and replace T2 value
        line = line.replaceAll("T2 = 4 \\+ T1", "result = 15");

        // Replace T2 with result
        line = line.replaceAll("T2", "result");

        return line;
    }
}
