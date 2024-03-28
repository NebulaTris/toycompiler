package CppLexer;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class CppLexer {
    
    private static final Set<String> OPERATORS = new HashSet<>();
    
    static {
        OPERATORS.add("+");
        OPERATORS.add("-");
        OPERATORS.add("*");
        OPERATORS.add("/");
        OPERATORS.add("%");
        OPERATORS.add("=");
        OPERATORS.add("<");
        OPERATORS.add(">");
        OPERATORS.add("&");
        OPERATORS.add("|");
        OPERATORS.add("^");
        OPERATORS.add("~");
        OPERATORS.add("!");
    }
    
    public static void main(String[] args) {
        String filename = "input.cpp"; // Change this to the filename of your input C++ file
        try {
            List<Token> tokens = tokenize(filename);
            writeTokensToFile(tokens);
            System.out.println("Tokens written to OutputLexer");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    } 

    private static List<Token> tokenize(String filename) throws IOException {
        List<Token> tokens = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int lineNumber = 1;
        while ((line = reader.readLine()) != null) {
            tokens.addAll(tokenizeLine(line, lineNumber));
            lineNumber++;
        }
        reader.close();
        return tokens;
    }

    private static List<Token> tokenizeLine(String line, int lineNumber) {
        List<Token> tokens = new ArrayList<>();
        String[] words = line.split("\\s+");
        for (String word : words) {
            if (isKeyword(word)) {
                tokens.add(new Token(TokenType.KEYWORD, word, lineNumber));
            } else if (isIdentifier(word)) {
                tokens.add(new Token(TokenType.IDENTIFIER, word, lineNumber));
            } else if (isOperator(word)) {
                tokens.add(new Token(TokenType.OPERATOR, word, lineNumber));
            } else if (isLiteral(word)) {
                tokens.add(new Token(TokenType.LITERAL, word, lineNumber));
            } else if (isComment(word)) {
                tokens.add(new Token(TokenType.COMMENT, word, lineNumber));
            } else {
                tokens.add(new Token(TokenType.UNKNOWN, word, lineNumber));
            }
        }
        return tokens;
    }

    private static boolean isKeyword(String word) {
        // Simplified list of C++ keywords
        String[] keywords = {"int", "float", "if", "else", "while", "return"}; // Add more keywords as needed
        for (String keyword : keywords) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIdentifier(String token) {
        // An identifier in C++ starts with a letter or underscore, followed by letters, digits, or underscores
        return token.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }
    
    private static boolean isOperator(String token) {
        // Check if the token is one of the defined operators
        return OPERATORS.contains(token);
    }
    
    private static boolean isLiteral(String token) {
        // A literal in C++ can be an integer, floating-point number, or character constant
        return token.matches("-?\\d+") || token.matches("-?\\d+\\.\\d+") || token.matches("'.'");
    }
    
    private static boolean isComment(String token) {
        // Check if the token starts with the comment symbols "//" or "/*"
        return token.startsWith("//") || token.startsWith("/*");
    }
    
    private static class Token {
        private final TokenType type;
        private final String value;
        private final int lineNumber;

        public Token(TokenType type, String value, int lineNumber) {
            this.type = type;
            this.value = value;
            this.lineNumber = lineNumber;
        }

        @Override
        public String toString() {
            return "[" + type + ", " + value + ", Line " + lineNumber + "]";
        }
    }

    private enum TokenType {
        KEYWORD, IDENTIFIER, OPERATOR, LITERAL, COMMENT, UNKNOWN
    }

    private static void writeTokensToFile(List<Token> tokens) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("OutputLexer"));
        for (Token token : tokens) {
            writer.write(token.toString());
            writer.newLine();
        }
        writer.close();
    }
}
