import java.io.*;
import java.io.Serializable;

public class WritingProcess extends Process {
    private static final long serialVersionUID = 0x57726974;
    private String operation;
    private String filePath;

    WritingProcess(String expression) {
        this.operation = expression;
    
        String projectPath = System.getProperty("java.class.path");
        filePath = projectPath + "/computation.txt";
    }

    public void execute() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.append(operation + '\n');

            writer.close();
            System.out.println(TranslationUnit.grab("WRITESUCCESS") + operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String type = TranslationUnit.grab("TYPE");
        String sOperation = TranslationUnit.grab("OPERATION");
        return "{Pid: " + pid + ", " + type +  ": WritingProcess, " + sOperation  + ": " + operation + "}";
    }
}
