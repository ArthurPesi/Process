import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;

public class ProcessQueue implements Serializable {
    private static final long serialVersionUID = 0x7FFFFFFF;
    private RingBuffer ids = new RingBuffer();

    private int highestPid = 0; //TODO: atualizar

    private Dictionary<Integer, Process> table = new Hashtable<>();

    ProcessQueue() {
        //TODO: iniciar carregando de um arquivo
    }

    public void registerProcess(Process teste) {
        highestPid += 1;
        table.put(highestPid, teste);
        ids.enqueue(highestPid);
        teste.setPid(highestPid);
        //TODO: setpid
    }

    public void executeNextProcess() {
        Object next;

        int pid = ids.deque();
        next = table.get(pid);

        while (next == null) {
            pid = ids.deque();
            next = table.get(pid);
        }

        ((Process) next).execute();

        table.remove(pid);
    }

    public void printState() {
        System.out.println("Estado da fila:");
        int currIdx = ids.getHead();
        while(currIdx != ids.getTail()) {
            int currPid = ids.getAtIdx(currIdx);
            Object next = table.get(currPid);
            System.out.println(((Process) next).toString());

            currIdx = ++currIdx % ids.getSize();
        }
    }

    public void executeProcessFromPid(int pid) {
        Object next = table.get(pid);
        //TODO: verificar se existe
        ((Process) next).execute();
        table.remove(pid);
    }

    public void save() {
        //TODO: salvar estado em um arquivo
    }
}
