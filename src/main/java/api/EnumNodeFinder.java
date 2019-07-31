package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EnumNodeFinder extends ElementNodeFinder {
    public EnumNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(EnumDeclaration el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
