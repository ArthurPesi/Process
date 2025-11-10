import java.io.*;
import java.io.Serializable;

public class WritingProcess implements Serializable {
    private static final long serialVersionUID = 0x57726974;
    private String operation;
    private String filePath;
    private int pid;

    WritingProcess(String operation, int pid) {
        this.operation = operation;
        this.pid = pid;
    
        String projectPath = System.getProperty("java.class.path");
        filePath = projectPath + "/computation.txt";
    }

    public void execute() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            writer.append(operation + '\n');

            writer.close();
            System.out.println("Operacao escrita com sucesso: " + operation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "{Pid: " + pid + ", Type: WritingProcess, Operation: " + operation + "}";
    }
}
