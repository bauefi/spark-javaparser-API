package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class VariableNodeFinderTest {

    @Test
    public void VariableNodeFinder_correctly_identifies_specifiers(){
        String testCode = "public class test{\n" +
                "    public int x;\n" +
                "    public String z;\n" +
                "    private final String y = \"Sage\";\n" +
                "    public static Integer a;\n" +
                "    protected static Integer b;\n" +
                "    String k;\n" +
                "    Integer ö;\n" +
                "    final String l = \"sgesf\";\n" +
                "    protected static Integer p;\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        VariableNodeFinder cf = new VariableNodeFinder(compilationUnit);
        assertTrue(cf.correctAmountOfDeclarations("public", null, null, null, 3));
        assertTrue(cf.correctAmountOfDeclarations("private", null, null, null, 1));
        assertTrue(cf.correctAmountOfDeclarations("", null, null, null, 3));
        assertTrue(cf.correctAmountOfDeclarations("protected", null, null, null, 2));
        assertTrue(cf.correctAmountOfDeclarations(null, "final", null, null, 2));
        assertTrue(cf.correctAmountOfDeclarations(null, "static", null, null, 3));
        assertTrue(cf.correctAmountOfDeclarations(null, "", null, null, 4));
        assertTrue(cf.correctAmountOfDeclarations("private", "final", null, null, 1));
        assertTrue(cf.correctAmountOfDeclarations("public", "static", null, null, 1));
        assertTrue(cf.correctAmountOfDeclarations("protected", "static", null, null, 2));
        assertTrue(cf.correctAmountOfDeclarations("", "final", null, null, 1));
        assertTrue(cf.correctAmountOfDeclarations("private", "final", "String", "y", 1));
        assertTrue(cf.correctAmountOfDeclarations("protected", "static", "Integer", "p", 1));
    }

    @Test
    public void VariableNodeFinder_correctly_identifies_datatypes(){
        String testCode = "public class test{\n" +
                "    private short a;\n" +
                "    private final int b=0;\n" +
                "    public static long c;\n" +
                "    protected final float d=1;\n" +
                "    double t;\n" +
                "    public boolean v;\n" +
                "    private char h;\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        VariableNodeFinder cf = new VariableNodeFinder(compilationUnit);
        assertTrue(cf.correctAmountOfDeclarations(null, null, "short", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "int", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "long", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "float", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "double", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "boolean", null, 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, "char", null, 1));
        assertTrue(cf.correctAmountOfDeclarations("private", "", "short", "a", 1));
        assertTrue(cf.correctAmountOfDeclarations("private", "final", "int", "b", 1));
        assertTrue(cf.correctAmountOfDeclarations("public", "static", "long", "c", 1));
        assertTrue(cf.correctAmountOfDeclarations("protected", "final", "float", "d", 1));
        assertTrue(cf.correctAmountOfDeclarations("", "", "double", "t", 1));
        assertTrue(cf.correctAmountOfDeclarations("public", "", "boolean", "v", 1));
        assertTrue(cf.correctAmountOfDeclarations("private", "", "char", "h", 1));
        assertTrue(cf.correctAmountOfDeclarations(null, null, null, null, 7));
    }
}

