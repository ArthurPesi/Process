import java.util.Scanner;


public class Main {
    public static enum ProcessType {
        COMPUTE,
        WRITE,
        READ,
        PRINT
    }

    private static ProcessQueue processes;
    private static final String MENUOPTIONS = "1: Criar processo\n2: Executar proximo\n3: Executar especifico\n4: Salvar arquivo\n5: Carregar arquivo";

    public static void main(String[] args) {
        try {
            Scanner userInput = new Scanner(System.in);

            while (true) { 
                System.out.println(MENUOPTIONS);
                int answer = userInput.nextInt();
                if (answer == 0) {// Sair se escolher 0
                    break;
                } else if (answer == 1) {//Criar processo
                    //dar opcao de criar writing process pelo pid de um processo existente
                } else if (answer == 2) {// Executar proximo 
                } else if (answer == 3) {// Executar especifico
                } else if (answer == 4) {// Salvar arquivo
                } else if (answer == 5) {// Carregar arquivo
                } else {
                    System.out.println("Opcao invalida. Escolha uma das opcoes apresentadas abaixo. Digite apenas o numero");
                }
            }
            userInput.close();//Fechar scanner
        } catch (Exception e) {
            e.printStackTrace();//Printar stack em caso de erro
        }
    }

    public static ProcessQueue getQueue() {
        return processes;
    }

    public static void createProcess(ProcessType type) {
        switch(type) {
            case READ:
                new ReadingProcess();
            case PRINT:
                new PrintingProcess();
            default:
                System.out.println("Esse tipo nao e conhecido pelo sistema");
        }
        //TODO: criar um objeto de computacao e botar na fila
    }
    public static void createProcess(ProcessType type, String expression) {
        switch(type) {
            case COMPUTE:
            case WRITE:
            default:
                System.out.println("Esse tipo nao e conhecido pelo sistema");
        }
        //TODO: criar um objeto de computacao e botar na fila
    }
}
