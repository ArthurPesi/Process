public class ComputingProcess extends Process {
    private static final long serialVersionUID = 0x436F6D70;
    
    private char operation;
    private float number1;
    private float number2;
    private String stringForm;

    ComputingProcess(String expression) { //TODO: initialize pid
        expression = expression.replaceAll("\\s+", "");
        String[] splitExpression = expression.split("(?<=[+\\-*/])|(?=[+\\-*/])");
        if(splitExpression.length != 3) {
            System.out.println("Ops");
            //TODO: throw exception
        }

        this.number1 = Integer.parseInt(splitExpression[0]);
        this.operation = splitExpression[1].charAt(0); //TODO: check for valid operation
        this.number2 = Integer.parseInt(splitExpression[2]);

        this.stringForm = expression;
    }
    
    public void execute() {
        float result;
        switch(operation) {
        case '+':
            result = number1 + number2;
            break;
        case '-':
            result = number1 - number2;
            break;
        case '*':
            result = number1 * number2;
            break;
        case '/':
            result = number1 / number2;
            break;
        default:
            System.out.println("Operation invalid"); //TODO: throw error
            result = 0;
        }

        System.out.println("Resultado da operacao: " + stringForm + " = " + result);
    }

    @Override
    public String toString() {//TODO: print pid
        return "{Pid: " + pid + ", Tipo: ComputingProcess, Operacao: " + stringForm + "}";
    }
}
