package api;

import com.github.javaparser.ast.CompilationUnit;

import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BreakNodeFinder extends ElementNodeFinder {
    public BreakNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(BreakStmt el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
