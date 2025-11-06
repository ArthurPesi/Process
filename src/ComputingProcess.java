import java.io.Serializable;

public class ComputingProcess implements Serializable {
    private static final long serialVersionUID = 0x436F6D70;
    
    private char operation;
    private float number1;
    private float number2;

    ComputingProcess(float number1, char operation, float number2) { //TODO: initialize pid
        this.number1 = number1;
        this.operation = operation; //TODO: check for valid operation
        this.number2 = number2;
    }
    
    public void execute() {
        float result;
        switch(operation) {
        case '+':
            result = number1 + number2;
        case '-':
            result = number1 - number2;
        case '*':
            result = number1 * number2;
        case '/':
            result = number1 / number2;
        default:
            System.out.println("Operation invalid"); //TODO: throw error
            result = 0;
        }
        System.out.println(number1 + " " + operation + " " + number2 + " = " + result);
    }

    @Override
    public String toString() {//TODO: print pid
        return number1 + " " + operation + " " + number2;
    }
}
