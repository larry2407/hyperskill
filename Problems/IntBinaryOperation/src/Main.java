abstract class IntBinaryOperation {

    protected int firstArg;
    protected int secondArg;

    public IntBinaryOperation(int firstArg, int secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    public abstract int perform();
}

class Addition extends IntBinaryOperation{

    Addition(int first, int second){
        super(first, second);
    }

    public int perform(){
        return super.firstArg + super.secondArg;
    }
}

class Multiplication extends IntBinaryOperation{

    Multiplication(int first, int second){
        super(first, second);
    }

    public int perform(){
        return super.firstArg * super.secondArg;
    }
}
