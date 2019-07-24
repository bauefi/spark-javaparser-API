import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClassNodeFinder {
    CompilationUnit compilationUnit;
    VoidVisitor<List<ClassInterfaceDeclaration>> visitor;

    public ClassNodeFinder(CompilationUnit compilationUnit){
        this.compilationUnit = compilationUnit;
        this.visitor = new VoidVisitorAdapter<List<ClassInterfaceDeclaration>>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration cd, List<ClassInterfaceDeclaration> collector) {
                super.visit(cd, collector);
                String accessSpecifier = cd.getAccessSpecifier().toString();
                String name = cd.getNameAsString();
                ClassInterfaceDeclaration dec = new ClassInterfaceDeclaration(accessSpecifier, name);
                collector.add(dec);
            }
        };
    }

    //Crawls the AST and returns all class declarations together with their access specifier
    private List<ClassInterfaceDeclaration> crawlAST(){
        List<ClassInterfaceDeclaration> matches = new ArrayList<>();
        this.visitor.visit(this.compilationUnit, matches);
        return matches;
    }

    public boolean declarationExists(String accessSpecifier, String name) {
        List<ClassInterfaceDeclaration> matches = this.crawlAST();
        for (int i = 0; i < matches.size(); i++) {
            ClassInterfaceDeclaration temp = matches.get(i);
            if (temp.getAccessSpecifier().equals(accessSpecifier) && temp.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean correctAmountOfDeclarations(String accessSpecifier, int amount){
        List<ClassInterfaceDeclaration> matches = this.crawlAST();
        int count = 0;
        for (int i = 0; i < matches.size(); i++) {
            ClassInterfaceDeclaration temp = matches.get(i);
            if(temp.getAccessSpecifier().equals(accessSpecifier)){
                ++count;
            }
        }
        return count == amount;
    }
}

class ClassInterfaceDeclaration{
    private final String accessSpecifier;
    private final String name;

    public ClassInterfaceDeclaration(String accessSpecifier, String name){
        this.accessSpecifier = accessSpecifier;
        this.name = name;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getName() {
        return name;
    }
}
