import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import spark.Request;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        /*
        This Route checks for a given code snippet whether the correct amount of classes with the specified characteristics exists
        */
        post("/class", (request, response) -> {
            // 1. Validate request parameters
            if(!validateRequestParams(request)){
                response.status(400);
                return "Incorrect Query Parameters";
            }

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            String name                 = request.queryParams("name");
            String accessSpecifier      = ParameterTranslater.translateAccessSpecifier(request.queryParams("accessSpecifier"));
            String nonAccessModifier    = ParameterTranslater.translateNonAccessModifier(request.queryParams("nonAccesSpecifier"));
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                ClassInterfaceNodeFinder classNodeFinder = new ClassInterfaceNodeFinder(compilationUnit);
                response.status(200);
                return classNodeFinder.correctAmountOfDeclarations(false, accessSpecifier, nonAccessModifier, name, cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        /*
        This Route checks for a given code snippet whether the correct amount of interfaces with the specified characteristics exists
        */
        post("/interface", (request, response) -> {
            // 1. Validate request parameters
            if(!validateRequestParams(request)){
                response.status(400);
                return "Incorrect Query Parameters";
            }

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            String name                 = request.queryParams("name");
            String accessSpecifier      = ParameterTranslater.translateAccessSpecifier(request.queryParams("accessSpecifier"));
            String nonAccessModifier    = ParameterTranslater.translateNonAccessModifier(request.queryParams("nonAccesSpecifier"));
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                ClassInterfaceNodeFinder classNodeFinder = new ClassInterfaceNodeFinder(compilationUnit);
                response.status(200);
                return classNodeFinder.correctAmountOfDeclarations(true, accessSpecifier, nonAccessModifier, name, cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }

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
    //No request parameter can be null
    private static boolean validateRequestParams(Request request) {
        String code = request.queryParams("code");
        String accessSpecifier = request.queryParams("accessSpecifier");
        String nonAccesSpecifier = request.queryParams("nonAccesSpecifier");
        String name = request.queryParams("name");
        String cardinality = request.queryParams("cardinality");
        return code != null && accessSpecifier != null && nonAccesSpecifier != null && name != null && cardinality != null;
    }
}



