import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// Classe que receberá as mensagens   
public class Server {

    public static final int PORT = 8080;
    private ServerSocket serverSocket;
    private BufferedReader in;

    // Inicia o servidor e a porta de uso, e inicia o loop da leitura das mensagens
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta " + PORT);
        connectionLoop();
    }

    // Loop para ler as mensagens recebidas
    public void connectionLoop() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " conectou:");

            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Tratamento de uma Stream para conseguir receber uma String
            String messageCliente = in.readLine();
            System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + ": " + messageCliente); // Exibição da mensagem recebida
        }
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
