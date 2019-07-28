import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfNodeFinder extends ElementNodeFinder{
    public IfNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(IfStmt el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
