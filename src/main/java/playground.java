import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;



public class playground {
    public static void main(String[] args){
        String code = "public class test {\n" +
                "    private int a, d;\n" +
                "    public final int b = 0;\n" +
                "    protected static int c = 0;\n" +
                "    private class test2{}\n" +
                "    \n" +
                "    private int add(int a, int b){\n" +
                "        return 1;\n" +
                "    }\n" +
                "}";

        String code2 = "public class test{\n" +
                "    private int a = 10;\n" +
                "    public String b = 5;\n" +
                "    private final int c = 0;\n" +
                "\n" +
                "    public test(int a) {\n" +
                "        this.a = a;\n" +
                "    }\n" +
                "}";

        CompilationUnit cu = StaticJavaParser.parse(code);
        VariableNodeFinder cf = new VariableNodeFinder(cu);
        System.out.println(cf.correctAmountOfDeclarations("public", "", "","", 0));
    }
}


class test1{
    int a, b = 0;
}