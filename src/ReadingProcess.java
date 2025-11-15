import java.io.*;

public class ReadingProcess extends Process {
    private static final long serialVersionUID = 0x52656164;
    private String filePath;

    //Referencia para a fila de processos
    private ProcessQueue queue;

    ReadingProcess(ProcessQueue queue) {
        this.queue = queue;

        //Registrar caminho do arquivo a ser escrito
        String projectPath = System.getProperty("java.class.path");
        filePath = projectPath + "/computation.txt";
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    
    public void execute() {
        int processCount = 0;//Contagem de quantos processos sao registrados ao ler o arquivo
        try {
            //Abrir e ler arquivo
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = reader.readLine()) != null) {
                ComputingProcess cProcess = new ComputingProcess(line);
                //Adicionar processos na fila
                queue.registerProcess(cProcess);
                ++processCount;
            }

            reader.close();//Fechar arquivo

            //Botar um texto no plural ou no singular dependendo do numero de processos
            String created;
            if (processCount == 1) {
                created = TranslationUnit.grab("READSINGLE");
            } else {
                created = TranslationUnit.grab("READMULTIPLE");
            }
                
            //mostrar mensagem ao usuario
            System.out.println(TranslationUnit.grab("READSUCCESS") + processCount + created);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "{Pid: " + pid + ", " + TranslationUnit.grab("TYPE") + ": ReadingProcess}";
    }


}
