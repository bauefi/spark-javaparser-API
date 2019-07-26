public class ClassInterfaceDeclaration{
    private final boolean isInterface;
    private final String accessSpecifier;
    private final String name;
    private final String nonAccessModifier;

    public ClassInterfaceDeclaration(boolean isInterface, String accessSpecifier, String nonAccessModifier, String name){
        this.isInterface = isInterface;
        this.accessSpecifier = accessSpecifier;
        this.nonAccessModifier = nonAccessModifier;
        this.name = name;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getName() {
        return name;
    }

    public String getNonAccessModifier(){ return nonAccessModifier;}

    public boolean isInterface(){ return isInterface;}
}
