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

        CompilationUnit cu = StaticJavaParser.parse(code);
        ClassNodeFinder cf = new ClassNodeFinder(cu);
        System.out.println(cf.correctAmountOfDeclarations("public", "static", "",0));
    }
}

