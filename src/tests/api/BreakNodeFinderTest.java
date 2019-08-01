package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BreakNodeFinderTest {
    @Test
    public void BreakNodeFinder_correctly_counts_the_occurences_of_break_statements(){
        String testCode = "public class test{\n" +
                "    public void looping(){\n" +
                "        for(int i=0; i<10; i++){\n" +
                "            if(i==5){\n" +
                "                break;\n" +
                "            }\n" +
                "            for(int j=0; j<10; j++){\n" +
                "                if(j==5){\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        BreakNodeFinder bf = new BreakNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(2));
    }
}

