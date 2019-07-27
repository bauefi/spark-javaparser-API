import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class playground {
    public static void main(String[] args){
        String code = "class test{\n" +
                "    private final class test2{\n" +
                "        int x;\n" +
                "    }\n" +
                "    \n" +
                "    protected static class test3{\n" +
                "        int x;\n" +
                "    }\n" +
                "    \n" +
                "    private class something{\n" +
                "        \n" +
                "    }\n" +
                "}";
        String code2 = "pulic interface test{\n" +
                "     void printSomething();\n" +
                "}";

        CompilationUnit cu = StaticJavaParser.parse(code2);
        ClassInterfaceNodeFinder cf = new ClassInterfaceNodeFinder(cu);
        System.out.println(cf.correctAmountOfDeclarations(true,"public", "", "",1));
    }
}

