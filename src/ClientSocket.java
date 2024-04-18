import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

// Classe responsável por manter métodos comuns as classes Server e Client
public class ClientSocket {

    private final Socket socket; // Conexão
    private final BufferedReader in; // Entrada
    private final PrintWriter out; // Saída

    // Construtor que trata os entradas e saídas do socket
    public ClientSocket(Socket socket) throws IOException{
        this.socket = socket;

        System.out.println();
        System.out.println("Cliente " + socket.getRemoteSocketAddress() + " conectou:");

        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Tratamento de uma Stream para conseguir receber uma String
        this.out = new PrintWriter(socket.getOutputStream(), true)  ; // Tratamento de uma Stream para conseguir passar uma String e não precisar ficar quebrando linha
    }

    // Pegar o endereço IP
    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    // Receber mensagem
    public String getMessage() {
        try {
            return in.readLine();

        } catch (IOException e) {
            return null;
        }
    }

    // Mandar mensagem
    public boolean sendMessage(String messageClient) {
        out.println(messageClient);
        return !out.checkError();
    }
}