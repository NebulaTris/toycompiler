import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IdentiferLO {
    public static void main(String[] args) {
        String inputFileName = "OutputSemantic";
        String outputFileName = "OutputLO";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    String type = parts[0];
                    String value = parts[1];
                    if (type.equals("Literal")) {
                        writer.write(value + " ");
                    } else if (type.equals("Operator")) {
                        writer.write(value + "\n");
                    }
                }
            }

            reader.close();
            writer.close();
            System.out.println("Literal and Operator were identified and written to " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
