import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;

public class ProcessQueue implements Serializable {
    private static final long serialVersionUID = 0x7FFFFFFF;

    //Registro do pid mais alto
    private int highestPid = 0; 

    //Lista ligada com os processos
    private LinkedList<Process> processList = new LinkedList<Process>();

    //dicionario para conseguir acessar qualquer processo rapidamente atraves do pid
    private Dictionary<Integer, Process> processMap = new Hashtable<>();

    //Registrar processo na fila
    public void registerProcess(Process teste) {
        highestPid += 1;
        teste.setPid(highestPid);

        //Adicionar no fim da fila e no dicionario
        processList.addLast(teste);
        processMap.put(highestPid, teste);
    }

    //Executar proximo processo na fila
    public void executeNextProcess() {
        //Pegar proximo processo e tirar ele da fila
        Process next = processList.poll();

        if(next == null) {
            System.out.println(TranslationUnit.grab("EMPTYQUEUE"));
            return;
        }
        //Remover processo do dicionario
        int pid = next.getPid();
        processMap.remove(pid);

        next.execute();
    }

    //Imprimir fila
    public void printState() {
        System.out.println(TranslationUnit.grab("PRINTQUEUE"));

        //Executar o tostring em cada um dos processos da fila em ordem
        processList.forEach( (process) -> {System.out.println(process.toString()); } );
    }

    //Executar processo especifico
    public void executeProcessFromPid(int pid) {

        //Encontrar o processo pelo dicionario
        Process next = processMap.get(pid);

        //Dar erro se nao encontrar nenhum processo
        if (next == null) {
            System.out.println(TranslationUnit.grab("INVALIDPID"));
            return;
        }

        //Remover processo da lista e do dicionario
        processMap.remove(pid);
        processList.remove(next);

        next.execute();
    }
}
