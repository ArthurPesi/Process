import java.io.Serializable;

public  class PrintingProcess implements Serializable {
    private static final long serialVersionUID = 0x5072696E;
    private int pid;
    private ProcessQueue queue;

    PrintingProcess() {
        queue = Main.getQueue();
        //TODO: registrar na fila
        System.out.println("Objeto criado com sucesso: " + toString());
    }
    
    public void execute() {
        queue.printState();
    }

    @Override
    public String toString() {
        return "{Pid: " + pid + ", Type: PrintingProcess}";
    }
}
