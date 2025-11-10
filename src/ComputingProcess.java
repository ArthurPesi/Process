import java.io.Serializable;

public class ComputingProcess implements Serializable {
    private static final long serialVersionUID = 0x436F6D70;
    
    private char type;
    private float number1;
    private float number2;
    private String stringForm;
    private int pid;

    ComputingProcess(int pid, String operation) { //TODO: initialize pid
        operation = operation.replaceAll("\\s+", "");
        String[] splitOperation = operation.split("\\+\\-\\/\\*");

        this.number1 = Integer.parseInt(splitOperation[0]);
        this.type = splitOperation[1].charAt(0); //TODO: check for valid operation
        this.number2 = Integer.parseInt(splitOperation[2]);

        this.pid = pid;
        this.stringForm = operation;
    }
    
    public void execute() {
        float result;
        switch(type) {
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

        System.out.println(stringForm + " = " + result);
    }

    @Override
    public String toString() {//TODO: print pid
        return "{Pid: " + pid + ", Type: ComputingProcess, Operation: " + stringForm + "}";
    }
}
