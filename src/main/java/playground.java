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

        String code2 = "class test1{\n" +
                "    int a, b = 0;\n" +
                "    private int looping(){\n" +
                "        for(int i=0;i <10; i++){\n" +
                "            for(int j=0; j<10; i++){\n" +
                "                if(true){\n" +
                "                    System.out.print(i);\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return 1;\n" +
                "    }\n" +
                "}";

        CompilationUnit cu = StaticJavaParser.parse(code2);
        IfNodeFinder cf = new IfNodeFinder(cu);
        System.out.println(cf.correctAmountOfOccurrences(1));
    }
}


class test1{
    int a, b = 0;
    private int looping(){
        for(int i=0;i <10; i++){
            for(int j=0; j<10; i++){
                if(true){
                    System.out.print(i);
                }
            }
        }
        return 1;
    }
}