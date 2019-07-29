import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LambdaNodeFinder extends ElementNodeFinder {
    public LambdaNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(LambdaExpr el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
