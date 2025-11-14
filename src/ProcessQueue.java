import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;

public class ProcessQueue implements Serializable {
    private static final long serialVersionUID = 0x7FFFFFFF;
    private LinkedList<Process> processList = new LinkedList<Process>();

    private int highestPid = 0; //TODO: atualizar

    private Dictionary<Integer, Process> processMap = new Hashtable<>();

    public void registerProcess(Process teste) {
        highestPid += 1;
        processMap.put(highestPid, teste);
        teste.setPid(highestPid);

        processList.addLast(teste);
    }

    public void executeNextProcess() {
        Process next = processList.poll();

        int pid = next.getPid();
        processMap.remove(pid);

        next.execute();
    }

    public void printState() {
        System.out.println("Estado da fila:");
        processList.forEach( (process) -> {System.out.println(process.toString()); } );
    }

    public void executeProcessFromPid(int pid) {
        Process next = processMap.get(pid);
        if (next == null) {
            System.out.println(TranslationUnit.grab("INVALIDPID"));
            return;
        }
        processMap.remove(pid);
        processList.remove(next);
        next.execute();
    }
}
