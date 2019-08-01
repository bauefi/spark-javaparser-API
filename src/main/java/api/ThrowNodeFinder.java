package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ThrowNodeFinder extends ElementNodeFinder{
    public ThrowNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(ThrowStmt el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
