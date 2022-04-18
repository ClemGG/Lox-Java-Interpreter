package Core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {

    //#region Public Fields

    
    //#endregion

    //#region Private Fields

    static boolean hadError = false;
    
    //#endregion


    //#region Public Methods

    public static void main(String[] args) throws IOException {

        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } 
        else if (args.length == 1) {
            runFile(args[0]);
        } 
        else {
            runPrompt();
        }
    }

    
    //#endregion

    //#region Private Methods

    // Reads the code in the file at the specific path.
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        // Indicate an error in the exit code.
        if (hadError) System.exit(65);
    }

    // If no files are passed, it will create an interactive prompt
    // where the user can execute one line at a time.
    // To kill the prompt, press Ctrl-D.
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();

            if (line == null)
                break;

            run(line);
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();
        Parser parser = new Parser(tokens);
        Expr expression = parser.parse();
        // Stop if there was a syntax error.
        if (hadError) return;
        System.out.println(new AstPrinter().print(expression));


        // For now, just print the tokens.
        // for (Token token : tokens) {
        //     System.out.println(token);
        // }
    }

    // error handling
    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }
 
    //#endregion
}