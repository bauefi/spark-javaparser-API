package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ThrowNodeFinderTest {
    @Test
    public void ThrowNodeFinder_correctly_counts_occurences_of_exceptions_trhown(){
        String testCode = "class ThrowExcep \n" +
                "{ \n" +
                "    static void fun() \n" +
                "    { \n" +
                "        try\n" +
                "        { \n" +
                "            throw new NullPointerException(\"demo\"); \n" +
                "        } \n" +
                "        catch(NullPointerException e) \n" +
                "        { \n" +
                "            System.out.println(\"Caught inside fun().\"); \n" +
                "            throw e; // rethrowing the exception \n" +
                "        } \n" +
                "    } \n" +
                "  \n" +
                "    public static void main(String args[]) \n" +
                "    { \n" +
                "        try\n" +
                "        { \n" +
                "            fun(); \n" +
                "        } \n" +
                "        catch(NullPointerException e) \n" +
                "        { \n" +
                "            System.out.println(\"Caught in main.\"); \n" +
                "        } \n" +
                "    } \n" +
                "} ";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        ThrowNodeFinder bf = new ThrowNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(2));
    }
}