package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TryNodeFinder extends ElementNodeFinder{
    public TryNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(TryStmt el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
