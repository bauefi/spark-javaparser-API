public class FunctionDeclaration {

    private final String accessSpecifier;
    private final String nonAccessModifier;
    private final String datatype;
    private final String name;

    public FunctionDeclaration(String accessSpecifier, String nonAccessModifier, String datatype, String name) {
        this.accessSpecifier = accessSpecifier;
        this.nonAccessModifier = nonAccessModifier;
        this.datatype = datatype;
        this.name = name;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getNonAccessModifier() {
        return nonAccessModifier;
    }

    public String getDatatype() {
        return datatype;
    }

    public String getName() {
        return name;
    }
}
