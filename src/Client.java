import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// Classe que mandará as mensagens
public class Client {

    private ClientSocket clientSocket;
    private static Scanner scan;
    private static String SERVER_IP;
    public Server server;

    public Client() {
        scan = new Scanner(System.in);
    }

    // Inicia a conexão do cliente com o servidor e inicia o loop das mensagens
    public void startClient(String SERVER_IP, int SERVER_PORT) throws  IOException {
        clientSocket = new ClientSocket(
            new Socket(SERVER_IP, SERVER_PORT)
        );
        
        System.out.println("Conexão com a máquina " + SERVER_IP + " estabelecida");
        //messageLoop();
    }

    // Loop para enviar as mensagens até "exit" ser digitado
    public void messageLoop() throws IOException{
        String messageClient;
        do {
            System.out.print("Digite uma mensagem: ");
            messageClient = scan.nextLine();
            System.out.println();
            clientSocket.sendMessage(messageClient); // Saída da mensagem

            System.out.println("Cliente " + SERVER_IP + ": " + clientSocket.getMessage()); // Recebe a mensagem enviada do outro cliente
            
        } while(!messageClient.equalsIgnoreCase("exit")); // independe de estar em caixa alta ou baixa
    }
}
