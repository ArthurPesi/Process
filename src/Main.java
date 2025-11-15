import java.io.*;
import java.util.Scanner;
import java.util.regex.*;


public class Main {
    private static ProcessQueue queue = new ProcessQueue();
    private static String lastUserInput;

    public static void main(String[] args) {

        new TranslationUnit();//Inicializar traducoes

        try {
            Scanner userInputScanner = new Scanner(System.in);//Inicializar scanner

            System.out.println("Type \"help\" to see all the available options. Digite \"br\" para colocar em portugues");

            while (true) { //Loop principal
                System.out.print("process machine> ");

                //Pegar input do usuario em forma de string
                String userInput = userInputScanner.nextLine();

                //Repetir ultimo comando
                if(userInput.equals("!!")) {

                    //Dar erro se nao tiver comando para repetir
                    if(lastUserInput == null) {
                        System.out.println(TranslationUnit.grab("REPEATERROR"));
                        continue;
                    }

                    //Replicar o ultimo comando executado
                    userInput = lastUserInput;
                    System.out.println(userInput);
                }

                //Dividir o input por palavras
                String[] parts = userInput.toLowerCase().trim().split(" ");

                String command = parts[0];
                String flagWord = null;
                if(parts.length > 1) {
                    flagWord = parts[1];
                }

                //Sair do loop principal
                if (command.equals("exit")) {
                    break;
                } 

                //Exibir menu de ajuda
                else if (command.equals("help")){
                    System.out.println(TranslationUnit.grab("MENU"));
                }

                //Limpar a tela
                else if (command.equals("clear")){
                    System.out.print("\033[2J\033[1;1H");
                } 

                //Mudar lingua pra pt-br
                else if (command.equals("br")){
                    TranslationUnit.setLanguage(TranslationUnit.Language.BR);
                } 

                //Mudar lingua pra ingles
                else if (command.equals("en")){
                    TranslationUnit.setLanguage(TranslationUnit.Language.EN);
                } 

                //Criar novo processo
                else if (command.equals("create")) {

                    //Erro de flag
                    if(flagWord.charAt(0) != '-') {
                        System.out.println(TranslationUnit.grab("FLAGERROR"));
                    }

                    char flag = flagWord.charAt(1);
                    Process newProcess = null;
                    String expression;

                    switch(flag) {
                        case 'c'://ComputingProcess
                            expression = findQuotedExpression(userInput);//Encontrar expressao
                            if(expression == null) {
                                continue;
                            }

                            newProcess = new ComputingProcess(expression);
                            break;
                        case 'w'://WritingProcess
                            expression = findQuotedExpression(userInput);//Encontrar expressao
                            if(expression == null) {
                                continue;
                            }

                            newProcess = new WritingProcess(expression);
                            break;
                        case 'r'://ReadingProcess
                            newProcess = new ReadingProcess(queue);
                            break;
                        case 'p'://PrintingProcess
                            newProcess = new PrintingProcess(queue);
                            break;
                        default:
                            //Erro de tipo de processo invalido
                            System.out.println(TranslationUnit.grab("PROCESSTYPEERROR"));
                    }

                    //Adicionar processo na fila
                    if(newProcess != null) {
                        queue.registerProcess(newProcess);
                        System.out.println(TranslationUnit.grab("CREATESUCCESS") + newProcess.toString());
                    }
                } 

                //Executar comando
                else if (command.equals("exec") || command.equals("execute")) {

                    if(flagWord != null) {
                        if(flagWord.charAt(0) != '-') {
                            System.out.println(TranslationUnit.grab("FLAGERROR"));
                        }

                        //executar proximo se for passada a flag n ou next
                        else if(flagWord.charAt(1) == 'n') {
                            queue.executeNextProcess();
                        }

                        //executar por pid se for passada a flag p ou pid
                        else if(flagWord.charAt(1) == 'p') {
                            try {
                                int pid = Integer.parseInt(parts[2]);
                                queue.executeProcessFromPid(pid);

                            } catch (NumberFormatException e) {//Erro se o argumento nao for um numero valido
                                System.out.println(TranslationUnit.grab("INVALIDNUMBER"));
                            }
                        }

                    } else {//Executar proximo processo da fila se o usuario nao escolher por pid
                        queue.executeNextProcess();
                    }
                } 

                //Salvar fila em um arquivo
                else if (command.equals("save")) {
                    try {
                        //Encontrar caminho do projeto
                        String projectPath = System.getProperty("java.class.path");
                        String filePath = projectPath + "/save.txt";

                        //Abrir arquivo
                        ObjectOutputStream queueStream = new ObjectOutputStream(new FileOutputStream(filePath));

                        //Escrever arquivo
                        queueStream.writeObject(queue);
                        
                        //Fechar arquivo
                        queueStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } 

                //Carregar fila de um arquivo
                else if (command.equals("load")) {
                    try {

                        //Encontrar caminho do projeto
                        String projectPath = System.getProperty("java.class.path");
                        String filePath = projectPath + "/save.txt";
                        
                        //Abrir arquivo
                        ObjectInputStream queueStream = new ObjectInputStream(new FileInputStream(filePath));

                        //Ler arquivo
                        queue = (ProcessQueue) queueStream.readObject();

                        //Fechar arquivo
                        queueStream.close();
                    } catch (EOFException e) {
                        //Nada
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } 

                //Imprimir a fila
                else if (command.equals("print")) {
                    queue.printState();
                }

                //Erro se o comando nao for nenhum dos disponiveis
                else {
                    System.out.println(TranslationUnit.grab("INVALIDCOMMAND"));
                }

                //Registrar o comando para poder replicar no futuro
                lastUserInput = userInput;
            }

            userInputScanner.close();//Fechar scanner
            
        } catch (Exception e) {
            e.printStackTrace();//Printar stack em caso de erro
        }
    }


    //Funcao para encontrar expressoes entre aspas duplas
    public static String findQuotedExpression(String input) {

        //Regex que encontra qualquer quantidade de caracteres entre aspas duplas
        Pattern surroundedByQuotes = Pattern.compile("\"([^\"]*)\""); 
        Matcher match = surroundedByQuotes.matcher(input);
        
        if(match.find()) {
            return match.group(1);
        } else {
            System.out.println(TranslationUnit.grab("INVALIDEXPRESSION"));
            return null;
        }
    }
}
