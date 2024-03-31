import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeGeneratorWithRegisterAllocation {
    public static void main(String[] args) {
        try {
            // Read optimized code from OutputCodeOptimize file
            BufferedReader reader = new BufferedReader(new FileReader("OutputCodeOptimize"));
            String line;
            StringBuilder generatedCode = new StringBuilder();
            int registerIndex = 1;
            Map<String, String> registerMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                // Generate code based on the optimized line with register allocation
                String generatedLine = generateCodeWithRegisterAllocation(line, registerMap, registerIndex);
                generatedCode.append(generatedLine).append("\n");
            }
            reader.close();

            // Write generated code to FinalOutput file
            BufferedWriter writer = new BufferedWriter(new FileWriter("FinalOutput"));
            writer.write(generatedCode.toString());
            writer.close();

            System.out.println("Code generation completed. Check FinalOutput file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateCodeWithRegisterAllocation(String line, Map<String, String> registerMap, int registerIndex) {
        String[] parts = line.split(" = ");
        String variable = parts[0].trim();
        String value = parts[1].trim();

        // Check if the value is already in a register
        String register = registerMap.get(value);
        if (register == null) {
            register = "R" + registerIndex;
            registerMap.put(value, register);
            registerIndex++;
        }

        // Generate code with register allocation
        return "MOV " + register + ", " + value + "\n" +
               "MOV " + variable + ", " + register;
    }
}
