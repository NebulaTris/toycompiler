import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SemanticAnalyzer {

    public static void main(String[] args) {
        String inputFilename = "OutputParser"; // Change this to the filename of the parsed code
        String outputFilename = "OutputSemantic"; // Change this to the desired output filename
        try {
            analyzeSemantics(inputFilename, outputFilename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void analyzeSemantics(String inputFilename, String outputFilename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ", 2);
            if (parts.length < 2) {
                System.out.println("Invalid line format: " + line);
                continue;
            }
            String tokenType = parts[0].trim();
            String tokenValue = parts[1].trim();
            String[] tokenParts = tokenValue.split("\\(Line ");
            if (tokenParts.length != 2) {
                System.out.println("Invalid line format: " + line);
                continue;
            }
            String value = tokenParts[0];
            int lineNumber = Integer.parseInt(tokenParts[1].substring(0, tokenParts[1].length() - 1));

            // Perform semantic analysis based on token type and value
            String output = analyzeToken(tokenType, value, lineNumber);
            writer.write(output);
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

    private static String analyzeToken(String tokenType, String tokenValue, int lineNumber) {
        // Perform semantic analysis based on token type and value
        switch (tokenType) {
            case "PROGRAM":
                return "Program '" + tokenValue + "' declared at line " + lineNumber;
            case "IDENTIFIER":
                return "Identifier '" + tokenValue + "' used at line " + lineNumber;
            case "COMMENT":
                return "Comment '" + tokenValue + "' at line " + lineNumber;
            case "KEYWORD":
                return "Keyword '" + tokenValue + "' used at line " + lineNumber;
            case "OPERATOR":
                return "Operator '" + tokenValue + "' used at line " + lineNumber;
            case "LITERAL":
                return "Literal '" + tokenValue + "' used at line " + lineNumber;
            default:
                return "Unknown token type: " + tokenType + " at line " + lineNumber;
        }
    }
}
