# Lox-Java-Interpreter
Interpreter and compiler for Lox, a dynamically-typed interpreted OOP programming language, made in Java.
To compile the project via the Command panel, place yourself in the "Core" folder and use "javac Core/Lox.java" and execute using "java Core.Lox Runtime/loxTest.txt".

Use the file loxTest.txt to input multiple lines of code in the interpreter. You can omit the file path to enter prompt mode, where you can input one line at a time.

This example prints the first 20 Fibonacci numbers onto the screen:

```
fun fib(n) {
 if (n < 2) return n;
 return fib(n - 1) + fib(n - 2); 
}

var before = clock();
print fib(40);
var after = clock();
print after - before;

```

