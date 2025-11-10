import java.util.Dictionary;
import java.util.Hashtable;

public class ProcessQueue {
    private RingBuffer ids;

    private int highestPid; //TODO: atualizar

    private Dictionary<Integer, Process> table = new Hashtable<>();

    ProcessQueue() {
        //TODO: iniciar carregando de um arquivo
    }

    public void registerProcess(Process process) {
        highestPid++;
        table.put(highestPid, process);
        ids.enqueue(highestPid);
    }

    public void executeNextProcess() {
        Process next;

        do {
            int nextPid = ids.deque();
            next = table.get(nextPid);
        } while(next == null);

        next.execute();
    }

    public void printState() {
        int curr = ids.getHead();
        while(curr != ids.getTail()) {
            Process next = table.get(curr);
            System.out.println(next.toString());

            curr = ++curr % ids.getSize();
        }
    }

    public void executeProcessFromPid(int pid) {
        Process next = table.get(pid);
        next.execute();
        table.remove(pid);
    }

    public void save() {
        //TODO: salvar estado em um arquivo
    }
}
