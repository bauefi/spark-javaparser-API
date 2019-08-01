package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class EnumNodeFinderTest {
    @Test
    public void EnumNodeFinder_correctly_counts_enum_declarations(){
        String testCode = "public class test{\n" +
                "    \n" +
                "    enum Level {\n" +
                "        LOW,\n" +
                "        MEDIUM,\n" +
                "        HIGH\n" +
                "    }\n" +
                "\n" +
                "    enum Temperature {\n" +
                "        Hot,\n" +
                "        Cold,\n" +
                "        Mellow\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        EnumNodeFinder bf = new EnumNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(2));
    }

    @Test
    public void EnumNodeFinder_does_not_wrongfully_count_non_existent_enum_declarations(){
        String testCode = "public class test{\n" +
                "\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        EnumNodeFinder bf = new EnumNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(0));
    }
}

