package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClassInterfaceNodeFinder {
    CompilationUnit compilationUnit;
    VoidVisitor<List<ClassInterfaceDeclaration>> visitor;

    public ClassInterfaceNodeFinder(CompilationUnit compilationUnit){
        this.compilationUnit = compilationUnit;
        this.visitor = new VoidVisitorAdapter<List<ClassInterfaceDeclaration>>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration cd, List<ClassInterfaceDeclaration> collector) {
            super.visit(cd, collector);
            NodeList<Modifier> specifiers = cd.getModifiers();
            String accessSpecifier;
            String nonAccessModifier;
            //If there is no access specifier, the non access specifier is the first specifier
            if(cd.getAccessSpecifier().toString().replaceAll("\\s+","").equals("PACKAGE_PRIVATE")){
                accessSpecifier = "";
                nonAccessModifier = specifiers.size() >= 1 ? specifiers.get(0).toString(): "";
            } else {
                accessSpecifier = specifiers.size() >= 1 ? specifiers.get(0).toString(): "";
                nonAccessModifier = specifiers.size() >= 2 ? specifiers.get(1).toString(): "";
            }
            String name = cd.getNameAsString();
            boolean isInterface = cd.isInterface();
            ClassInterfaceDeclaration dec = new ClassInterfaceDeclaration(isInterface, accessSpecifier, nonAccessModifier, name);
            collector.add(dec);
            }
        };
    }

    //Crawls the AST and returns all class and interface declarations
    private List<ClassInterfaceDeclaration> crawlAST(){
        List<ClassInterfaceDeclaration> matches = new ArrayList<>();
        this.visitor.visit(this.compilationUnit, matches);
        return matches;
    }

    //For a given class or Interface specification checks whether exactly the desired cardinality is present in the AST
    public boolean correctAmountOfDeclarations(Boolean isInterface, String accessSpecifier, String nonAccessSpecifier, String name, int cardinality){
        List<ClassInterfaceDeclaration> matches = this.crawlAST();

        int count = 0;
        for (int i = 0; i < matches.size(); i++) {
            ClassInterfaceDeclaration temp = matches.get(i);
            String acs = temp.getAccessSpecifier().replaceAll("\\s+","");
            String nam = temp.getNonAccessModifier().replaceAll("\\s+","");
            String n = temp.getName().replaceAll("\\s+","");

            if(
                (acs.equals(accessSpecifier) || accessSpecifier == null) &&
                (nam.equals(nonAccessSpecifier) || nonAccessSpecifier == null) &&
                (n.equals(name) || name == null) &&
                (temp.isInterface() == isInterface)
            ){
                ++count;
            }
        }
        return count == cardinality;
    }

}
