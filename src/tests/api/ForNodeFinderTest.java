package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ForNodeFinderTest {
    @Test
    public void ForNodeFinder_correctly_counts_the_instances_of_for_statements(){
        String testCode = "public class test{\n" +
                "    private void looping(int a){\n" +
                "        for(int i=0; i<a; i++){\n" +
                "            int j = 0;\n" +
                "            for(; j<a; j++){\n" +
                "                int b= 5;\n" +
                "                while(b<10){   \n" +
                "                    ++b;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        ForNodeFinder bf = new ForNodeFinder(compilationUnit);
        assertTrue(bf.correctAmountOfOccurrences(2));
    }
}

