import java.io.IOException;
import java.net.InetAddress;
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
    public void startClient(String SERVER_IP) throws  IOException {
        clientSocket = new ClientSocket(
            new Socket(SERVER_IP, Server.PORT)
        );
        
        System.out.println("Conexão com a máquina " + SERVER_IP + " estabelecida");
        messageLoop();
    }

    // Loop para enviar as mensagens até "exit" ser digitado
    public void messageLoop() throws IOException{
        String messageClient;
        do {
            System.out.print("Digite uma mensagem: ");
            messageClient = scan.nextLine();
            clientSocket.sendMessage(messageClient); // Saída da mensagem

            System.out.println("Cliente " + SERVER_IP + ": " + clientSocket.getMessage()); // Recebe a mensagem enviada do outro cliente
            
        } while(!messageClient.equalsIgnoreCase("exit")); // independe de estar em caixa alta ou baixa
    }
    public static void main(String[] args) {
        try {
            //Server server = new Server();
            //new Thread(() -> server.startClientServer()); 

            Client client = new Client();
            String LOCAL_IP = InetAddress.getLocalHost().getHostAddress(); // Armazena o IP local
            System.out.println("O seu IP é " + LOCAL_IP); // Mostra o IP local para o cliente
            
            
            System.out.print("Digite o endereço IP da máquina ao qual você deseja se conectar: ");
            //SERVER_IP = scan.nextLine();
            SERVER_IP = "127.0.0.1";
 

            client.startClient(SERVER_IP);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
