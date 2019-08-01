package api;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SwitchNodeFinderTest {
    @Test
    public void SwitchNodeFinder_counts_occurences_of_Switch_statements_correctly(){
        String testCode = "public class test{\n" +
                "    \n" +
                "    private void conditional(int i){\n" +
                "        if(true){\n" +
                "            if(false){\n" +
                "                switch (i){\n" +
                "                    case 1: System.out.println(\"Someghing \");\n" +
                "                    case 2: System.out.println(\"Soetmgnint else \");\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    protected static boolean anotherConditional(int a){\n" +
                "     switch (a){\n" +
                "         case 1: System.out.println(\"he,lo\");\n" +
                "         case 2: System.out.println(\"heslflsf\");\n" +
                "     }\n" +
                "     if(a==0){\n" +
                "         return true;\n" +
                "     }\n" +
                "     return false;\n" +
                "    }\n" +
                "}";

        CompilationUnit compilationUnit = StaticJavaParser.parse(testCode);
        SwitchNodeFinder in = new SwitchNodeFinder(compilationUnit);
        assertTrue(in.correctAmountOfOccurrences(2));
    }
}