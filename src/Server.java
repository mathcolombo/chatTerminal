import java.io.IOException;
import java.net.ServerSocket;

// Classe que receberá as mensagens   
public class Server {

    private ServerSocket serverSocket;
    private ClientSocket otherPointClient;

    // Inicia o servidor e a porta de uso, e inicia o loop da leitura das mensagens
    public void startServer(int PORT) throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT);
    }

    // Loop para ler as mensagens recebidas
    public void connectionLoop() throws IOException {      
        ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
        otherPointClient = clientSocket;
              
        clientMessageLoop(clientSocket); // Envio de mensagens recebidas para o Local Host de forma assíncrona
    }

    // Envia as mensagens recebidas para o Local Host
    private void clientMessageLoop(ClientSocket clientSocket) {
        String messageClient;
        while((messageClient = clientSocket.getMessage()) != null) {

            if(messageClient.equalsIgnoreCase("exit"))
                return;

            System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + ": " + messageClient); // Exibição da mensagem recebida
            sendMessageToMe(clientSocket, messageClient); //Envia a mensagem para o Local Host de forma oculta
        }

        System.out.println("");
        System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " se desconectou");
    }

    // Manda a mensagem recebida para o Local Host
    private void sendMessageToMe(ClientSocket sender, String messageClient) {
        otherPointClient.sendMessage(messageClient);
    }
}
