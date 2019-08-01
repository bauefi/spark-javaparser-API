package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class LambdaNodeFinderTest {
    @Test
    public void LambdaNodeFinder_correctly_counts_occurences_of_lambda_expressions(){
        String testCode = "public class Java8Tester {\n" +
                "\n" +
                "   public static void main(String args[]) {\n" +
                "      Java8Tester tester = new Java8Tester();\n" +
                "\t\t\n" +
                "      //with type declaration\n" +
                "      MathOperation addition = (int a, int b) -> a + b;\n" +
                "\t\t\n" +
                "      //with out type declaration\n" +
                "      MathOperation subtraction = (a, b) -> a - b;\n" +
                "\t\t\n" +
                "      //with return statement along with curly braces\n" +
                "      MathOperation multiplication = (int a, int b) -> { return a * b; };\n" +
                "\t\t\n" +
                "      //without return statement and without curly braces\n" +
                "      MathOperation division = (int a, int b) -> a / b;\n" +
                "\t\t\n" +
                "      System.out.println(\"10 + 5 = \" + tester.operate(10, 5, addition));\n" +
                "      System.out.println(\"10 - 5 = \" + tester.operate(10, 5, subtraction));\n" +
                "      System.out.println(\"10 x 5 = \" + tester.operate(10, 5, multiplication));\n" +
                "      System.out.println(\"10 / 5 = \" + tester.operate(10, 5, division));\n" +
                "\t\t\n" +
                "      //without parenthesis\n" +
                "      GreetingService greetService1 = message ->\n" +
                "      System.out.println(\"Hello \" + message);\n" +
                "\t\t\n" +
                "      //with parenthesis\n" +
                "      GreetingService greetService2 = (message) ->\n" +
                "      System.out.println(\"Hello \" + message);\n" +
                "\t\t\n" +
                "      greetService1.sayMessage(\"Mahesh\");\n" +
                "      greetService2.sayMessage(\"Suresh\");\n" +
                "   }\n" +
                "\t\n" +
                "   interface MathOperation {\n" +
                "      int operation(int a, int b);\n" +
                "   }\n" +
                "\t\n" +
                "   interface GreetingService {\n" +
                "      void sayMessage(String message);\n" +
                "   }\n" +
                "\t\n" +
                "   private int operate(int a, int b, MathOperation mathOperation) {\n" +
                "      return mathOperation.operation(a, b);\n" +
                "   }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        LambdaNodeFinder bf = new LambdaNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(6));
    }
}