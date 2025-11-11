import java.io.*;

public class ReadingProcess extends Process {
    private static final long serialVersionUID = 0x52656164;
    private String filePath;
    private ProcessQueue queue;

    ReadingProcess(ProcessQueue queue) {
        this.queue = queue;
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
                ComputingProcess cProcess = new ComputingProcess(line);
                queue.registerProcess(cProcess);
                //TODO: adicionar esse daqui na fila
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
        return "{Pid: " + pid + ", Tipo: ReadingProcess}";
    }


}
