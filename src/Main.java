import java.io.*;
import java.util.Scanner;
import java.util.regex.*;


public class Main {
    private static ProcessQueue queue = new ProcessQueue();
    private static TranslationUnit translationUnit = new TranslationUnit();
    private static String lastAnswer;

    public static void main(String[] args) {
        try {
            Scanner userInput = new Scanner(System.in);

            System.out.println("Type \"help\" to see all the available options. Digite \"br\" para colocar em portugues");
            while (true) { 
                System.out.print("process machine> ");
                String answer = userInput.nextLine();

                if(answer.equals("!!")) {
                    answer = lastAnswer;
                    System.out.println(answer);
                }

                String[] parts = answer.toLowerCase().split(" ");
                String command = parts[0];
                if (command.equals("exit")) {// Sair se escolher 0
                    break;
                } else if (command.equals("clear")){
                    System.out.print("\033[2J\033[1;1H");
                } else if (command.equals("br")){
                    TranslationUnit.setLanguage(TranslationUnit.Language.BR);
                } else if (command.equals("en")){
                    TranslationUnit.setLanguage(TranslationUnit.Language.EN);
                } else if (command.equals("help")){
                    System.out.println(TranslationUnit.grab("MENU"));
                }else if (command.equals("create")) {//Criar processo
                    if(parts[1].charAt(0) != '-') {
                        System.out.println(TranslationUnit.grab("FLAGERROR"));
                    }

                    char flag = parts[1].charAt(1);

                    Pattern surroundedByQuotes = Pattern.compile("\"([^\"]*)\""); 
                    Matcher match = surroundedByQuotes.matcher(answer);
                    
                    String expression = "69/420";

                    if(match.find()) {
                        expression = match.group(1);
                    } else if (flag == 'c' || flag == 'w') {
                        System.out.println(TranslationUnit.grab("INVALIDEXPRESSION"));
                        continue;
                    }

                    Process toCreate = null;
                    switch(flag) {
                        case 'c':
                            toCreate = new ComputingProcess(expression);
                            break;
                        case 'w':
                            toCreate = new WritingProcess(expression);
                            break;
                        case 'r':
                            toCreate = new ReadingProcess(queue);
                            break;
                        case 'p':
                            toCreate = new PrintingProcess(queue);
                            break;
                        default:
                            System.out.println(TranslationUnit.grab("PROCESSTYPEERROR"));
                    }

                    if(toCreate != null) {
                        queue.registerProcess(toCreate);
                        System.out.println(TranslationUnit.grab("CREATESUCCESS") + toCreate.toString());
                    }

                } else if (command.equals("exec") || command.equals("execute")) {// Executar proximo 
                    if(parts.length > 1) {
                        if(parts[1].charAt(0) != '-') {
                            System.out.println(TranslationUnit.grab("FLAGERROR"));
                            continue;
                        }
                        int pid = Integer.parseInt(parts[2]);
                        //TODO: se n for um numero
                        //
                        queue.executeProcessFromPid(pid);
                    } else {
                        queue.executeNextProcess();
                    }
                } else if (command.equals("save")) {// Salvar arquivo
                    try {
                        String projectPath = System.getProperty("java.class.path");
                        String filePath = projectPath + "/save.txt";
                        ObjectOutputStream queueStream = new ObjectOutputStream(new FileOutputStream(filePath));
                        queueStream.writeObject(queue);
                        queueStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (command.equals("load")) {// Carregar arquivo
                    try {
                        String projectPath = System.getProperty("java.class.path");
                        String filePath = projectPath + "/save.txt";
                        ObjectInputStream queueStream = new ObjectInputStream(new FileInputStream(filePath));
                        queue = (ProcessQueue) queueStream.readObject();
                        queueStream.close();
                    } catch (EOFException e) {
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (command.equals("print")) {
                    queue.printState();
                    //TODO: dar opcao de printar por pid
                }else {
                    System.out.println(TranslationUnit.grab("INVALIDCOMMAND"));
                }

                lastAnswer = answer;
            }
            userInput.close();//Fechar scanner
        } catch (Exception e) {
            e.printStackTrace();//Printar stack em caso de erro
        }
    }
}
