package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;


public class playground {
    public static void main(String[] args){
        String code = "public class test {\n" +
                "    private int a, d;\n" +
                "    public final int b = 0;\n" +
                "    protected static int c = 0;\n" +
                "    private class test2{}\n" +
                "    \n" +
                "    private int add(int a, int b){\n" +
                "        return 1;\n" +
                "    }\n" +
                "}";

        String code2 = "public class MyClass {\n" +
                "  public static void main(String[] args) {\n" +
                "    try {\n" +
                "      int[] myNumbers = {1, 2, 3};\n" +
                "      System.out.println(myNumbers[10]);\n" +
                "    } catch (Exception e) {\n" +
                "      System.out.println(\"Something went wrong.\");\n" +
                "    } finally {\n" +
                "      System.out.println(\"The 'try catch' is finished.\");\n" +
                "    }\n" +
                "  }\n" +
                "}" +
                "";

        CompilationUnit cu = StaticJavaParser.parse(code2);
        TryNodeFinder cf = new TryNodeFinder(cu);
        System.out.println(cf.correctAmountOfOccurrences(1));
    }
}
