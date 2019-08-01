package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class FunctionNodeFinderTest {
    @Test
    public void FunctionNodeFinder_correctly_identifies_specifiers(){
        String testCode = "public class test{\n" +
                "    public static int add(int a, int b){\n" +
                "        return 1;\n" +
                "    }\n" +
                "    \n" +
                "    private final String subtract(){\n" +
                "        return \"Someting\";\n" +
                "    }\n" +
                "    \n" +
                "    final Integer blob(){\n" +
                "        return 1;\n" +
                "    }\n" +
                "    \n" +
                "    protected void something(){}\n" +
                "    \n" +
                "    void doNothing(){}\n" +
                "    \n" +
                "    protected float arb(){\n" +
                "        return 2;\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        FunctionNodeFinder cf = new FunctionNodeFinder(compilationUnit);
        assertTrue(cf.correctAmountOfDeclarations(null, null,null, null,6));
        assertTrue(cf.correctAmountOfDeclarations("public", null,null, null,1));
        assertTrue(cf.correctAmountOfDeclarations("private", null,null, null,1));
        assertTrue(cf.correctAmountOfDeclarations("protected", null,null, null,2));
        assertTrue(cf.correctAmountOfDeclarations("", null,null, null,2));
        assertTrue(cf.correctAmountOfDeclarations("public", "static",null, null,1));
        assertTrue(cf.correctAmountOfDeclarations("private", "final",null, null,1));
        assertTrue(cf.correctAmountOfDeclarations("", "final",null, null,1));
        assertTrue(cf.correctAmountOfDeclarations("protected", "",null, null,2));
        assertTrue(cf.correctAmountOfDeclarations("", "",null, null,1));
    }

    @Test
    public void FunctionNodeFinder_correctly_identifies_datatypes(){
        String testCode = "public class test{\n" +
                "    private static short add(){\n" +
                "        return 1;\n" +
                "    }\n" +
                "    \n" +
                "    public int som(){\n" +
                "        return 1;\n" +
                "    }\n" +
                "    \n" +
                "    protected final float fdsf(){\n" +
                "        return 1;\n" +
                "    }\n" +
                "    \n" +
                "    public static long somwef(){\n" +
                "        return 2;\n" +
                "    }\n" +
                "    \n" +
                "    double asf(){\n" +
                "        return 1;\n" +
                "    }\n" +
                "    \n" +
                "    public boolean gesge(){\n" +
                "        return true;\n" +
                "    }\n" +
                "    \n" +
                "    private char sefs(){\n" +
                "        return 'a';\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        FunctionNodeFinder cf = new FunctionNodeFinder(compilationUnit);
        assertTrue(cf.correctAmountOfDeclarations(null, null, "short", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "int", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "long", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "float", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "double", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "boolean", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "char", null, 1));
        assertTrue(cf.correctAmountOfDeclarations("private", "static", "short", "add", 1));
        assertTrue(cf.correctAmountOfDeclarations("public", "", "int", "som", 1));
        assertTrue(cf.correctAmountOfDeclarations("protected", "final", "float", "fdsf", 1));
        assertTrue(cf.correctAmountOfDeclarations("public", "static", "long", "somwef", 1));
        assertTrue(cf.correctAmountOfDeclarations("", "", "double", "asf", 1));
        assertTrue(cf.correctAmountOfDeclarations("public", "", "boolean", "gesge", 1));
        assertTrue(cf.correctAmountOfDeclarations("private", "", "char", "sefs", 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, null, null, 7));
    }
}



