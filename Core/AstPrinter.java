//!If we want to print the expressions before they're parsed,
//! uncomment this class.
//! However, the interface will need to implement the new expressions if any.

// package Core;

// import Core.Expr.Binary;
// import Core.Expr.Grouping;
// import Core.Expr.Literal;
// import Core.Expr.Unary;

// class AstPrinter implements Expr.Visitor<String> {

//     String print(Expr expr) {
//         return expr.accept(this);
//     }

//     @Override
//     public String visitBinaryExpr(Expr.Binary expr) {
//         return parenthesize(expr.operator.lexeme,
//                 expr.left, expr.right);
//     }
 
//     @Override
//     public String visitGroupingExpr(Expr.Grouping expr) {
//         return parenthesize("group", expr.expression);
//     }
    
//     @Override
//     public String visitLiteralExpr(Expr.Literal expr) {
//         if (expr.value == null)
//             return "nil";
//         return expr.value.toString();
//     }
    
//     @Override
//     public String visitUnaryExpr(Expr.Unary expr) {
//         return parenthesize(expr.operator.lexeme, expr.right);
//     }
    

//     private String parenthesize(String name, Expr... exprs) {
//         StringBuilder builder = new StringBuilder();
//         builder.append("(").append(name);

//         for (Expr expr : exprs) {
//             builder.append(" ");
//             builder.append(expr.accept(this));
//         }

//         builder.append(")");
//         return builder.toString();
//     }
 
// }

