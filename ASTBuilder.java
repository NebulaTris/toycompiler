import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ASTNode {
    String type;
    String value;
    int lineNumber;
    List<ASTNode> children;

    public ASTNode(String type, String value, int lineNumber) {
        this.type = type;
        this.value = value;
        this.lineNumber = lineNumber;
        this.children = new ArrayList<>();
    }
}

public class ASTBuilder {
    public static void main(String[] args) {
        ASTNode root = new ASTNode("PROGRAM", "Main", 1);

        try (BufferedReader br = new BufferedReader(new FileReader("OutputLexer"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("OutputParser"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(", ");
                String type = tokens[0].substring(1);
                String value = tokens[1];
                int lineNumber = Integer.parseInt(tokens[2].substring(5, tokens[2].length() - 1));

                ASTNode node = new ASTNode(type, value, lineNumber);
                addChildNode(root, node);
            }

            writeASTToFile(root, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addChildNode(ASTNode parent, ASTNode child) {
        parent.children.add(child);
    }

    private static void writeASTToFile(ASTNode node, BufferedWriter bw) throws IOException {
        writeNodeToFile(node, bw, 0);
        bw.close();
    }

    private static void writeNodeToFile(ASTNode node, BufferedWriter bw, int depth) throws IOException {
        for (int i = 0; i < depth; i++) {
            bw.write("  ");
        }
        bw.write(node.type + ": " + node.value + " (Line " + node.lineNumber + ")\n");

        for (ASTNode child : node.children) {
            writeNodeToFile(child, bw, depth + 1);
        }
    }
}