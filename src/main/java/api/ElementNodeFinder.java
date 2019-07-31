package api;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public abstract class ElementNodeFinder {
    CompilationUnit compilationUnit;
    VoidVisitor<Counter> visitor;

    public ElementNodeFinder(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public int crawlAST(){
        Counter counter = new Counter();
        this.visitor.visit(this.compilationUnit, counter);
        return counter.getCount();
    }

    public boolean correctAmountOfOccurrences(int cardinality){
        return this.crawlAST() == cardinality;
    }
}

