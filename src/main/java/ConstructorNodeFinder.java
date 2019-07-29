import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstructorNodeFinder extends ElementNodeFinder {
    public ConstructorNodeFinder(CompilationUnit compilationUnit) {
        super(compilationUnit);
        this.visitor = new VoidVisitorAdapter<Counter>() {
            @Override
            public void visit(ConstructorDeclaration el, Counter counter) {
                counter.increment();
                super.visit(el, counter);
            }
        };
    }
}
