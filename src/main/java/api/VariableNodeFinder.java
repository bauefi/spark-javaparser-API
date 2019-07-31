package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class VariableNodeFinder {
    CompilationUnit compilationUnit;
    VoidVisitor<List<VariableDeclaration>> visitor;

    public VariableNodeFinder(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
        this.visitor = new VoidVisitorAdapter<List<VariableDeclaration>>() {
            @Override
            public void visit(FieldDeclaration vd, List<VariableDeclaration> collector) {
                super.visit(vd, collector);
                NodeList<Modifier> specifiers = vd.getModifiers();
                NodeList<VariableDeclarator> variables = vd.getVariables();
                String dataType = vd.getElementType().toString();
                String accessSpecifier = specifiers.size() >= 1 ? specifiers.get(0).toString() : "";
                String nonAccessModifier = specifiers.size() >= 2 ? specifiers.get(1).toString() : "";
                for(int i=0; i<variables.size(); i++){
                    String name = variables.get(i).getName().toString();
                    VariableDeclaration dec = new VariableDeclaration(accessSpecifier, nonAccessModifier, dataType, name);
                    collector.add(dec);
                }
            }
        };
    }

    //Crawls the AST and returns all variable declarations
    private List<VariableDeclaration> crawlAST() {
        List<VariableDeclaration> matches = new ArrayList<>();
        this.visitor.visit(this.compilationUnit, matches);
        return matches;
    }

    public boolean correctAmountOfDeclarations(String accessSpecifier, String nonAccessSpecifier, String dataType, String name, int cardinality) {
        List<VariableDeclaration> matches = this.crawlAST();
        int count = 0;
        for (int i = 0; i < matches.size(); i++) {
            VariableDeclaration temp = matches.get(i);
            String acs = temp.getAccessSpecifier().replaceAll("\\s+","");
            String nam = temp.getNonAccessModifier().replaceAll("\\s+","");
            String type = temp.getDataType().replaceAll("\\s+","");
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
