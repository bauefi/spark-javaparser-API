public class ParameterTranslater {

    static String translateAccessSpecifier(String accessSpecifier) {
        switch(accessSpecifier) {
            case "1": return "public";
            case "2": return "private";
            case "3": return "protected";
            default: return "";
        }
    }

    public static String translateNonAccessModifier(String nonAccessModifier) {
        switch(nonAccessModifier) {
            case "1": return "static";
            case "2": return "final";
            case "3": return "abstract";
            default: return "";
        }
    }

    public static Integer translateCardinality(String cardinality) {
        switch(cardinality) {
            case "1": return 0;
            case "2": return 1;
            case "3": return 2;
            case "4": return 3;
            case "5": return 4;
            case "6": return 5;
            case "7": return 6;
            case "8": return 7;
            case "9": return 8;
            case "10": return 9;
            default: return -1;
        }
    }
}
