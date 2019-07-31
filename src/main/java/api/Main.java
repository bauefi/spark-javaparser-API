package api;

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
            if(!validateRequestParamsClassInterface(request)){
                response.status(400);
                return "Incorrect Query Parameters";
            }

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            String name                 = ParameterTranslater.translateTypedString(request.queryParams("name"));
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
            if(!validateRequestParamsClassInterface(request)){
                response.status(400);
                return "Incorrect Query Parameters";
            }

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            String name                 = ParameterTranslater.translateTypedString(request.queryParams("name"));
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
            // 1. Validate request parameters
            if(!validateRequestParamsFunctionVariable(request)){
                response.status(400);
                return "Incorrect Query Parameters";
            }

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            String name                 = ParameterTranslater.translateTypedString(request.queryParams("name"));
            String dataType             = ParameterTranslater.translateTypedString(request.queryParams("dataType"));
            String accessSpecifier      = ParameterTranslater.translateAccessSpecifier(request.queryParams("accessSpecifier"));
            String nonAccessModifier    = ParameterTranslater.translateNonAccessModifier(request.queryParams("nonAccesSpecifier"));
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                FunctionNodeFinder functionNodeFinder = new FunctionNodeFinder(compilationUnit);
                response.status(200);
                return functionNodeFinder.correctAmountOfDeclarations(accessSpecifier, nonAccessModifier, dataType, name, cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/variable", (request, response) -> {
            // 1. Validate request parameters
            if(!validateRequestParamsFunctionVariable(request)){
                response.status(400);
                return "Incorrect Query Parameters";
            }

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            String name                 = ParameterTranslater.translateTypedString(request.queryParams("name"));
            String dataType             = ParameterTranslater.translateTypedString(request.queryParams("dataType"));
            String accessSpecifier      = ParameterTranslater.translateAccessSpecifier(request.queryParams("accessSpecifier"));
            String nonAccessModifier    = ParameterTranslater.translateNonAccessModifier(request.queryParams("nonAccesSpecifier"));
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                VariableNodeFinder variableNodeFinder = new VariableNodeFinder(compilationUnit);
                response.status(200);
                return variableNodeFinder.correctAmountOfDeclarations(accessSpecifier, nonAccessModifier, dataType, name, cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/if", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                IfNodeFinder ifNodeFinder = new IfNodeFinder(compilationUnit);
                response.status(200);
                return ifNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/for", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                ForNodeFinder forNodeFinder = new ForNodeFinder(compilationUnit);
                response.status(200);
                return forNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/while", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                WhileNodeFinder whileNodeFinder = new WhileNodeFinder(compilationUnit);
                response.status(200);
                return whileNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/break", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                BreakNodeFinder breakNodeFinder = new BreakNodeFinder(compilationUnit);
                response.status(200);
                return breakNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/constructor", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                ConstructorNodeFinder constructorNodeFinder = new ConstructorNodeFinder(compilationUnit);
                response.status(200);
                return constructorNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/enum", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                EnumNodeFinder enumNodeFinder = new EnumNodeFinder(compilationUnit);
                response.status(200);
                return enumNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/lambda", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                LambdaNodeFinder lambdaNodeFinder = new LambdaNodeFinder(compilationUnit);
                response.status(200);
                return lambdaNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/switch", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                SwitchNodeFinder switchNodeFinder = new SwitchNodeFinder(compilationUnit);
                response.status(200);
                return switchNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/throw", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                ThrowNodeFinder throwNodeFinder = new ThrowNodeFinder(compilationUnit);
                response.status(200);
                return throwNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
        });

        post("/try", (request, response) -> {
            // 1. Validate request parameters
            validateRequestParamsElement(request);

            // 2. Translate query parameters
            String code                 = request.queryParams("code");
            Integer cardinality         = ParameterTranslater.translateCardinality(request.queryParams("cardinality"));

            // 3. Query code
            try {
                CompilationUnit compilationUnit = StaticJavaParser.parse(code);
                TryNodeFinder tryNodeFinder = new TryNodeFinder(compilationUnit);
                response.status(200);
                return tryNodeFinder.correctAmountOfOccurrences(cardinality);
            } catch(Exception e){
                response.status(400);
                return "Failed to query code, make sure the code snippet sent compiles";
            }
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
    private static boolean validateRequestParamsClassInterface(Request request) {
        String code = request.queryParams("code");
        String accessSpecifier = request.queryParams("accessSpecifier");
        String nonAccesSpecifier = request.queryParams("nonAccesSpecifier");
        String name = request.queryParams("name");
        String cardinality = request.queryParams("cardinality");
        return code != null && accessSpecifier != null && nonAccesSpecifier != null && name != null && cardinality != null;
    }

    //No request parameter can be null
    private static boolean validateRequestParamsFunctionVariable(Request request) {
        String code = request.queryParams("code");
        String accessSpecifier = request.queryParams("accessSpecifier");
        String nonAccesSpecifier = request.queryParams("nonAccesSpecifier");
        String dataType = request.queryParams("dataType");
        String name = request.queryParams("name");
        String cardinality = request.queryParams("cardinality");
        return code != null && accessSpecifier != null && nonAccesSpecifier != null && dataType != null && name != null && cardinality != null;
    }

    private static boolean validateRequestParamsElement(Request request){
        String code = request.queryParams("code");
        String cardinality = request.queryParams("cardinality");
        return code != null && cardinality != null;
    }
}



