package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ClassInterfaceNodeFinderTest {

    @Test
    public void classInterfaceNodeFinder_correctly_detects_access_specifiers_for_classes(){
        String testCode = "public class test{\n" +
                "    public class test1{\n" +
                "        int x;\n" +
                "    }\n" +
                "    \n" +
                "    private class test2{\n" +
                "        int x;\n" +
                "    }\n" +
                "    \n" +
                "    protected class test3{\n" +
                "        int x;\n" +
                "    }\n" +
                "    \n" +
                "    private final class test4{\n" +
                "        int x;\n" +
                "    }\n" +
                "    \n" +
                "    class test5{\n" +
                "        int x;\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        ClassInterfaceNodeFinder cf = new ClassInterfaceNodeFinder(compilationUnit);
        assertTrue(cf.correctAmountOfDeclarations(false, null, null , null, 6));
        assertTrue(cf.correctAmountOfDeclarations(false, "protected", null , null, 1));
        assertTrue(cf.correctAmountOfDeclarations(false, "private", null , null, 2));
        assertTrue(cf.correctAmountOfDeclarations(false, "", null , null, 1));
        assertTrue(cf.correctAmountOfDeclarations(false, "public", null , null, 2));
        assertTrue(cf.correctAmountOfDeclarations(false, "public", null , "test", 1));
        assertTrue(cf.correctAmountOfDeclarations(false, "public", null , "test1", 1));
    }

    @Test
    public void classInterfaceNodeFinder_correctly_detects_access_specifiers_for_interfaces(){
        String testCode = "public interface test{\n" +
                "    \n" +
                "    interface test3{\n" +
                "        \n" +
                "    }\n" +
                "    \n" +
                "    interface test4{\n" +
                "        \n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        ClassInterfaceNodeFinder cf = new ClassInterfaceNodeFinder(compilationUnit);
        assertTrue(cf.correctAmountOfDeclarations(true, null, null , null, 3));
        assertTrue(cf.correctAmountOfDeclarations(true, "public", null , null, 1));
        assertTrue(cf.correctAmountOfDeclarations(true, "", null , null, 2));
        assertTrue(cf.correctAmountOfDeclarations(true, null, null , "test4", 1));
    }

    @Test
    public void classInterfaceNodeFinder_correctly_detects_non_access_modifiers(){
        String testCode = "public class test{\n" +
                "    public static class test2{\n" +
                "        \n" +
                "    }\n" +
                "    \n" +
                "    private static class test3{\n" +
                "        \n" +
                "    }\n" +
                "    \n" +
                "    abstract class test4{\n" +
                "        \n" +
                "    }\n" +
                "    \n" +
                "    private final class test5{\n" +
                "        \n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        ClassInterfaceNodeFinder cf = new ClassInterfaceNodeFinder(compilationUnit);
        assertTrue(cf.correctAmountOfDeclarations(false, null, "static" , null, 2));
        assertTrue(cf.correctAmountOfDeclarations(false, null, null , null, 5));
        assertTrue(cf.correctAmountOfDeclarations(false, null, "" , null, 1));
        assertTrue(cf.correctAmountOfDeclarations(false, null, "final" , null, 1));
        assertTrue(cf.correctAmountOfDeclarations(false, null, "static" , "test2", 1));
        assertTrue(cf.correctAmountOfDeclarations(false, null, "static" , "test3", 1));
    }
}


