import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class SwitchNodeFinder extends ElementNodeFinder{
    public SwitchNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(SwitchStmt el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
