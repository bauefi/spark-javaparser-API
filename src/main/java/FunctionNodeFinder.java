import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class FunctionNodeFinder {
    CompilationUnit compilationUnit;
    VoidVisitor<List<FunctionDeclaration>> visitor;

    public FunctionNodeFinder(CompilationUnit compilationUnit){
        this.compilationUnit = compilationUnit;
        this.visitor = new VoidVisitorAdapter<List<FunctionDeclaration>>() {
            @Override
            public void visit(MethodDeclaration md, List<FunctionDeclaration> collector) {
                super.visit(md, collector);
                NodeList<Modifier> specifiers = md.getModifiers();
                String name = md.getNameAsString();
                String dataType = md.getTypeAsString();
                String accessSpecifier = specifiers.size() >= 1 ? specifiers.get(0).toString(): "";
                String nonAccessModifier = specifiers.size() >= 2 ? specifiers.get(1).toString(): "";
                FunctionDeclaration dec = new FunctionDeclaration(accessSpecifier, nonAccessModifier, dataType, name);
                collector.add(dec);
            }
        };
    }

    //Crawls the AST and returns all function declarations
    private List<FunctionDeclaration> crawlAST(){
        List<FunctionDeclaration> matches = new ArrayList<>();
        this.visitor.visit(this.compilationUnit, matches);
        return matches;
    }

    //For a given function specification checks whether exactly the desired cardinality is present in the AST
    public boolean correctAmountOfDeclarations(String accessSpecifier, String nonAccessSpecifier, String dataType, String name, int cardinality){
        List<FunctionDeclaration> matches = this.crawlAST();
        int count = 0;
        for (int i = 0; i < matches.size(); i++) {
            FunctionDeclaration temp = matches.get(i);
            String acs = temp.getAccessSpecifier().replaceAll("\\s+","");
            String nam = temp.getNonAccessModifier().replaceAll("\\s+","");
            String type = temp.getDatatype().replaceAll("\\s+","");
            String n = temp.getName().replaceAll("\\s+","");

            if(
                    (acs.equals(accessSpecifier) || accessSpecifier == null) &&
                    (nam.equals(nonAccessSpecifier) || nonAccessSpecifier == null) &&
                    (type.equals(dataType) || dataType == null) &&
                    (n.equals(name) || name == null)
            ){
                ++count;
            }
        }
        return count == cardinality;
    }
}
