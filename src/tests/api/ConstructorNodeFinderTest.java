package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class ConstructorNodeFinderTest {
    @Test
    public void ConstructorNodeFinder_correctly_counts_the_occurences_of_constructors(){
        String testCode = "public class test{\n" +
                "    private final int a;\n" +
                "    private final int b;\n" +
                "\n" +
                "    public test(int a, int b){\n" +
                "\n" +
                "        this.a = a;\n" +
                "        this.b = b;\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        ConstructorNodeFinder bf = new ConstructorNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(1));
    }

    @Test
    public void ConstructorNodeFinder_does_not_wrongfully_identify_constructors(){
        String testCode = "public class test{\n" +
                "    int y;\n" +
                "    int x;\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        ConstructorNodeFinder bf = new ConstructorNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(0));
    }
}

