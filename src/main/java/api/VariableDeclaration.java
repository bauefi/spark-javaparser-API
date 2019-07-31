package api;

public class VariableDeclaration {

    private final String accessSpecifier;
    private String nonAccessModifier;
    private String dataType;
    private String name;

    public VariableDeclaration(String accessSpecifier, String nonAccessModifier, String dataType, String name) {

        this.accessSpecifier = accessSpecifier;
        this.nonAccessModifier = nonAccessModifier;
        this.dataType = dataType;
        this.name = name;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getNonAccessModifier() {
        return nonAccessModifier;
    }

    public String getDataType() {
        return dataType;
    }

    public String getName() {
        return name;
    }
}
