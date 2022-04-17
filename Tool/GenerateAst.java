package Tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

//Instead of creating each Expr class by hand,
//which would be tedious as we add more functionalities to Lox,
//we use a command-line app to generate them for us.

//! Dans l'invite de commandes, toujours se placer dans le dossier contenant le projet,
//! pas celui contenant la classe à charger.

public class GenerateAst {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java Tool.GenerateAst <output directory>");
            System.exit(64);
        }
        String outputDir = args[0];
        defineAst(outputDir, "Expr", Arrays.asList(
                "Binary : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal : Object value",
                "Unary : Token operator, Expr right"));

    }
    

    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException {

        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        //The Base class.
        writer.println("package Core;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName + " {");

        defineVisitor(writer, baseName, types);

        // The AST classes.
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
            writer.println();
        }
        // The base accept() method.
        writer.println(" abstract <R> R accept(Visitor<R> visitor);");
        writer.println();
        writer.println("}");
        writer.close();
    }
 
    
    private static void defineType(PrintWriter writer, String baseName, String className, String fieldList) {
        writer.println(" static class " + className + " extends " +
                baseName + " {");

        // Fields.
        writer.println();
        String[] fields = fieldList.split(", ");
        for (String field : fields) {
            writer.println("    final " + field + ";");
        }

        // Constructor.
        writer.println();
        writer.println("    " + className + "(" + fieldList + ") {");

        // Store parameters in fields.
        for (String field : fields) {
            String name = field.split(" ")[1];
            writer.println("        this." + name + " = " + name + ";");
        }
        writer.println("    }");



        // Visitor pattern.
        writer.println();
        writer.println("    @Override");
        writer.println("    <R> R accept(Visitor<R> visitor) {");
        writer.println("        return visitor.visit" +
        className + baseName + "(this);");
        writer.println("    }");

        writer.println(" }");

    }
 
    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {

        writer.println("    interface Visitor<R> {");

        for (String type : types) {
            String typeName = type.split(":")[0].trim();
            writer.println("        R visit" + typeName + baseName + "(" +
                    typeName + " " + baseName.toLowerCase() + ");");
        }
        
        writer.println("    }");
    }
 
}
