import java.io.Serializable;

public  class PrintingProcess extends Process {
    private static final long serialVersionUID = 0x5072696E;
    private ProcessQueue queue;

    PrintingProcess(ProcessQueue queue) {
        this.queue = queue;
        //TODO: registrar na fila
    }
    
    public void execute() {
        queue.printState();
    }

    @Override
    public String toString() {
        return "{Pid: " + pid + ", Tipo: PrintingProcess}";
    }
}
