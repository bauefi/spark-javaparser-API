import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;



public class playground {
    public static void main(String[] args){
        String code = "public class calculator{\n" +
                "    private int add(){\n" +
                "        return 3;\n" +
                "    }\n" +
                "    \n" +
                "    public static int subtract(){\n" +
                "        return 1;   \n" +
                "    }\n" +
                "}";

        String code2 = "puplic interface test{\n" +
                "     void printSomething();\n" +
                "}";

        CompilationUnit cu = StaticJavaParser.parse(code);
        FunctionNodeFinder cf = new FunctionNodeFinder(cu);
        System.out.println(cf.correctAmountOfDeclarations("public", "static", "","", 1));
    }
}


