package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class WhileNodeFinder extends ElementNodeFinder {
    public WhileNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(WhileStmt el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
