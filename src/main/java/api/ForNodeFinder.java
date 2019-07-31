package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ForNodeFinder extends ElementNodeFinder{

    public ForNodeFinder(CompilationUnit compilationUnit){
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(ForStmt el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
