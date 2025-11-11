public class TranslationUnit {
    enum Language {
        EN,
        BR
    }
    private static Translation[] translations;
    private static Language currentLanguage = Language.EN;

    TranslationUnit() {
        translations = new Translation[Language.values().length];
        Language[] languages = Language.values();
        for(int i = 0; i < languages.length; i++) {
            translations[i] = new Translation();
        }
        translations[0].setText("MENU", HELPMENU);
        translations[0].setText("FLAGERROR", FLAGERROR[0]);
        translations[0].setText("PROCESSTYPEERROR", PROCESSTYPEERROR[0]);
        translations[0].setText("MISSINGEXPRESSION", MISSINGEXPRESSION[0]);
        translations[0].setText("INVALIDCOMMAND", INVALIDCOMMAND[0]);
        translations[0].setText("PRINTQUEUE", PRINTQUEUE[0]);
        translations[0].setText("TYPE", TYPE[0]);
        translations[0].setText("READSUCCESS", READSUCCESS[0]);
        translations[0].setText("CREATESUCCESS", CREATESUCCESS[0]);
        translations[0].setText("WRITESUCCESS", WRITESUCCESS[0]);
        translations[0].setText("CEMPUTESUCCESS", COMPUTESUCCESS[0]);
        translations[0].setText("OPERATION", OPERATION[0]);
        translations[0].setText("INVALIDEXPRESSION", INVALIDEXPRESSION[0]);
        translations[1].setText("MENU", MENUDEAJUDA);
        translations[1].setText("FLAGERROR", FLAGERROR[1]);
        translations[1].setText("PROCESSTYPEERROR", PROCESSTYPEERROR[1]);
        translations[1].setText("MISSINGEXPRESSION", MISSINGEXPRESSION[1]);
        translations[1].setText("INVALIDCOMMAND", INVALIDCOMMAND[1]);
        translations[1].setText("PRINTQUEUE", PRINTQUEUE[1]);
        translations[1].setText("TYPE", TYPE[1]);
        translations[1].setText("READSUCCESS", READSUCCESS[1]);
        translations[1].setText("CREATESUCCESS", CREATESUCCESS[1]);
        translations[1].setText("WRITESUCCESS", WRITESUCCESS[1]);
        translations[1].setText("COMPUTESUCCESS", COMPUTESUCCESS[1]);
        translations[1].setText("OPERATION", OPERATION[1]);
        translations[1].setText("INVALIDEXPRESSION", INVALIDEXPRESSION[1]);
    }

    public static void setLanguage(Language language) {
        currentLanguage = language;
    }

    public static String grab(String text) {
        return translations[currentLanguage.ordinal()].getText(text);
    }

            
    private final static String HELPMENU = "help\n  show this menu\n\n" +
                                            "create\n" +
                                            "   Adds a new process to the queue. You must use a flag to define the type of process:\n" +
                                            "   -w \"expression\" (write the quoted expression to a file)\n" +
                                            "   -c \"expression\" (compute the quoted expression and print it to stdout)\n" +
                                            "       example: create -w \"5 + 10\"\n" +
                                            "   -r (read all values currently stored)\n" +
                                            "   -p (print the current state of the queue)\n\n" +
                                            "exec, execute\n" +
                                            "   Executes a process. by default, executes the next queued process\n" +
                                            "   -n, -next (default behaviour)\n" +
                                            "   -pid, -p (choose process to be executed)\n" +
                                            "       example: exec -p 21\n\n" +
                                            "save\n" +
                                            "   Save the current state of the queue to a file\n\n" +
                                            "load\n" +
                                            "   Load the last state saved with the save option\n\n" +
                                            "exit\n" +
                                            "   Quit. As simple as it sounds.";

    private static final String MENUDEAJUDA = "help\n  mostra este menu\n\n" +
                                       "create\n" +
                                       "   Adiciona um novo processo a fila. Voce deve usar uma flag para definir o tipo de processo:\n" +
                                       "   -w \"expressao\" (escreve a expressao entre aspas em um arquivo)\n" +
                                       "   -c \"expressao\" (calcula a expressao entre aspas e imprime no stdout)\n" +
                                       "       exemplo: criar -w \"5 + 10\"\n" +
                                       "   -r (le todos os valores atualmente armazenados)\n" +
                                       "   -p (imprime o estado atual da fila)\n\n" +
                                       "exec, execute\n" +
                                       "   Executa um processo. Por padrao, executa o proximo processo na fila\n" +
                                       "   -n, -next (comportamento padrão)\n" +
                                       "   -pid, -p (escolhe o processo a ser executado)\n" +
                                       "       exemplo: exec -p 21\n\n" +
                                       "save\n" +
                                       "   Salva o estado atual da fila em um arquivo\n\n" +
                                       "load\n" +
                                       "   Carrega o ultimo estado salvo com a opcao save\n\n" +
                                       "exit\n" +
                                       "   Encerra o programa. Simples assim.";
    private static final String[] FLAGERROR = {"Invalid flag!", "Flag invalida!"};

    private static final String[] TYPE = {"Type", "Tipo"};

    private static final String[] READSUCCESS = {" processes created", " processos criados com sucesso"};

    private static final String[] CREATESUCCESS= {"Process added to the queue: ", "Processo adicionado na fila: "};

    private static final String[] WRITESUCCESS = {"Expresson written to file: ", "Expressao salva no arquivo: "};

    private static final String[] COMPUTESUCCESS = {"Final result: ", "Resultado da conta: "};

    private static final String[] OPERATION = {"Operation", "Operacao"};

    private static final String[] INVALIDEXPRESSION = {"Process could not resolve due to malformed expression: ", "A expressao do processo estava mal formulada e ele nao foi resolvido: "};
    
    private static final String[] PROCESSTYPEERROR = {"Please choose a valid type of process", "Escolha um tipo de processo valido"};
    private static final String[] MISSINGEXPRESSION = {"Please insert an expression to create this type of process.", "Esse tipo de processo precisa de uma expressao para ser criado"};

    private static final String[] INVALIDCOMMAND = {"Invalid command. Type help to list available commands.", "Comando invalido. Digite help para listar os comandos disponiveis."};

    private static final String[] PRINTQUEUE = {"Current queue state: ", "Estado atual da fila: "};
}
