import java.io.*;

public class ReadingProcess implements Serializable {
    private static final long serialVersionUID = 0x52656164;
    private int pid;
    private String filePath;

    ReadingProcess() {
        String projectPath = System.getProperty("java.class.path");
        filePath = projectPath + "/computation.txt";
        //TODO: se colocar na fila
        //retornar pid quando entra e botar aqui
        //printar o objeto criado
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    
    public void execute() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = reader.readLine()) != null) {
                Main.createProcess(Main.ProcessType.COMPUTE, line);
                ++count;
            }

            reader.close();
            System.out.println(count + " operacoes criadas com sucesso");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "{Pid: " + pid + "Type: ReadingProcess}";
    }


}
