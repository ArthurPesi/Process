public class Text {
    enum Language {
        EN,
        BR
    }
    private Translation[] translations;
    private Language currentLanguage = Language.EN;

    Text() {
        translations = new Translation[Language.values().length];
        Language[] languages = Language.values();
        for(int i = 0; i < languages.length; i++) {
            translations[i] = new Translation();
        }
        translations[0].setText("MENU", HELPMENU);
        translations[1].setText("MENU", MENUDEAJUDA);
    }

    public void setLanguage(Language language) {
        currentLanguage = language;
    }

    public String grab(String text) {
        return translations[currentLanguage.ordinal()].getText(text);
    }

            
    private final String HELPMENU = "help\n  show this menu\n\n" +
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

    private final String MENUDEAJUDA = "help\n  mostra este menu\n\n" +
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
}
