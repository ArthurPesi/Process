public class ComputingProcess extends Process {
    private static final long serialVersionUID = 0x436F6D70;
    
    private char operation;
    private float number1;
    private float number2;
    private String stringForm;

    ComputingProcess(String expression) { 
        expression = expression.replaceAll("\\s+", "");

        //Dividir a expressao no sinal de +,-,/ ou *
        String[] splitExpression = expression.split("(?<=[+\\-*/])|(?=[+\\-*/])");

        this.number1 = Integer.parseInt(splitExpression[0]);
        this.operation = splitExpression[1].charAt(0); 
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
            System.out.println(TranslationUnit.grab("INVALIDEXPRESSION") + stringForm);
            return;
        }

        System.out.println(TranslationUnit.grab("COMPUTESUCCESS") + stringForm + " = " + result);
    }

    @Override
    public String toString() {//TODO: print pid
        String type = TranslationUnit.grab("TYPE");
        String sOperation = TranslationUnit.grab("OPERATION");
        return "{Pid: " + pid + ", " + type + ": ComputingProcess, " + sOperation + ": " + stringForm + "}";
    }
}
