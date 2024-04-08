import java.io.IOException;
import java.net.ServerSocket;

// Classe que receberá as mensagens   
public class Server {

    public static final int PORT = 8080;
    private ServerSocket serverSocket;
    private ClientSocket otherPointClient;

    // Inicia o servidor e a porta de uso, e inicia o loop da leitura das mensagens
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT);
        connectionLoop();
    }

    // Loop para ler as mensagens recebidas
    private void connectionLoop() throws IOException {      
        ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
        otherPointClient = clientSocket;
              
        while (true) {
            new Thread(() -> clientMessageLoop(clientSocket)).start(); // Thread para rodar o envio de mensagens recebidas para o Local Host de forma assíncrona
        }
    }

    // Envia as mensagens recebidas para o Local Host
    private void clientMessageLoop(ClientSocket clientSocket) {
        String messageClient;
        while((messageClient = clientSocket.getMessage()) != null) {

            if(messageClient.equalsIgnoreCase("exit"))
                return;

            System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + ": " + messageClient); // Exibição da mensagem recebida
            sendMessageToMe(clientSocket, messageClient); 
        }
    }

    // Manda a mensagem recebida para o Local Host
    private void sendMessageToMe(ClientSocket sender, String messageClient) {
        otherPointClient.sendMessage(messageClient);
    }

    // Método de teste
    public void startClientServer() {
        Server server = new Server();
        try {
            server.startServer();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Servidor finalizado!!");
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Servidor finalizado!!");
    }
}
