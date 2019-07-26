import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        /*
        This Route checks for a given code snippet whether the correct amount of classes with the specified characteristics exists
        */
        post("/class", (request, response) -> {
            // 1. Validate query parameters
            if(!validateQueryParams(
                    request.queryParams("code"),
                    request.queryParams("accessSpecifier"),
                    request.queryParams("nonAccesSpecifier"),
                    request.queryParams("name"),
                    request.queryParams("cardinality"))){
                response.status(400);
                return "Incorrect Query Parameters";
            }

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            String accessSpecifier      = parameterTranslater.translateAccessSpecifier(request.queryParams("accessSpecifier"));
            String nonAccessModifier    = parameterTranslater.translateNonAccessModifier(request.queryParams("nonAccesSpecifier"));
            String name                 = request.queryParams("name");
            Integer cardinality         = parameterTranslater.translateCardinality(request.queryParams("cardinality"));
            System.out.println(accessSpecifier);
            System.out.println(nonAccessModifier);
            System.out.println(name);
            System.out.println(cardinality);
            // 3. Query code
            CompilationUnit compilationUnit = StaticJavaParser.parse(code);
            ClassNodeFinder classNodeFinder = new ClassNodeFinder(compilationUnit);
            response.status(200);
            return classNodeFinder.correctAmountOfDeclarations(accessSpecifier, nonAccessModifier, name, cardinality);
        });

        post("/interface", (request, response) -> {
            response.status(200);
            return true;
        });

        post("/method", (request, response) -> {
            response.status(200);
            return true;
        });

        post("/variable", (request, response) -> {
            response.status(200);
            return true;
        });

        post("/if", (request, response) -> {
            response.status(200);
            return true;
        });

        post("/for", (request, response) -> {
            response.status(200);
            return true;
        });

        post("/while", (request, response) -> {
            response.status(200);
            return true;
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
    //No query parameters can be null
    private static boolean validateQueryParams(String code, String accessSpecifier, String nonAccessModifier, String name, String cardinality) {
        return code != null && accessSpecifier != null && nonAccessModifier != null && name != null && cardinality != null;
    }
}



