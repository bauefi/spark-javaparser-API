import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        /*
        This Route checks for a given code snippet whether a class or interface is declared with a specific
        name and access specifier
        */
        post("/findClass", (request, response) -> {
            String code = request.queryParams("code");
            String accessSpecifier = request.queryParams("accessSpecifier").toUpperCase();
            String name = request.queryParams("name");
            if(!validateCodeNameSpecifier(code, accessSpecifier, name)){
                response.status(400);
                return "Incorrect parameters";
            }
            CompilationUnit compilationUnit = StaticJavaParser.parse(code);
            ClassNodeFinder classNodeFinder = new ClassNodeFinder(compilationUnit);
            response.status(200);
            return classNodeFinder.declarationExists(accessSpecifier, name);
        });

        /*
        This Route checks for a given code snippet whether the correct amount of classes with a specific
        access specified is declared
        */
        post("/amountclass", (request, response) -> {
            String code = request.queryParams("code");
            String accessSpecifier = request.queryParams("accessSpecifier").toUpperCase();
            int amount = Integer.parseInt(request.queryParams("amount"));
            if(!validateCodeSpecifierAmount(code, accessSpecifier, amount)){
                response.status(400);
                return "Incorrect parameters";
            }
            CompilationUnit compilationUnit = StaticJavaParser.parse(code);
            ClassNodeFinder classNodeFinder = new ClassNodeFinder(compilationUnit);
            return classNodeFinder.correctAmountOfDeclarations(accessSpecifier, amount);
        });

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    /*------------------------------------ Helper Functions -----------------------------------------*/
    private static boolean validateCodeNameSpecifier(String code, String name, String accessSpecifier){
        return code != null && name != null && accessSpecifier != null;
    }

    private static boolean validateCodeSpecifierAmount(String code, String accessSpecifier, Integer amount){
        return code != null && accessSpecifier != null && amount != null;
    }

}



