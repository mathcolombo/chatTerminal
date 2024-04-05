import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// Classe que mandará as mensagens
public class Client {

    private Socket clientSocket;
    private static Scanner scan;
    private PrintWriter out;

    public Client() {
        scan = new Scanner(System.in);
    }

    // Inicia a conexão do cliente com o servidor, seta o tipo que a mensagem será enviada e inicia o loop das mensagens
    public void startClient(String SERVER_IP) throws  IOException {
        clientSocket = new Socket(SERVER_IP, Server.PORT);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true)  ; // Tratamento de uma Stream para conseguir passar uma String e não precisar ficar quebrando linha

        System.out.println("Conexão com a máquina " + SERVER_IP + " estabelecida");
        messageLoop();
    }

    // Loop para enviar as mensagens até "exit" ser digitado
    public void messageLoop() throws IOException{
        String messageClient;
        do {
            System.out.print("Digite uma mensagem: ");
            messageClient = scan.nextLine();
            out.println(messageClient); // Saída da mensagem
            
        } while(!messageClient.equalsIgnoreCase("exit")); // independe de estar em caixa alta ou baixa
    }
    public static void main(String[] args) {
        try {
            Client client = new Client();
            
            System.out.print("Digite o endereço IP da máquina ao qual você deseja se conectar: ");
            final String SERVER_IP = scan.nextLine();
 
            client.startClient(SERVER_IP);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
