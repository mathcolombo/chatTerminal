import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

// Classe responsável por manter métodos comuns
public class ClientSocket {

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ClientSocket(Socket socket) throws IOException{
        this.socket = socket;

        System.out.println("Cliente " + socket.getRemoteSocketAddress() + " conectou:");

        this.in = new BufferedReader(
            new InputStreamReader(
                socket.getInputStream()
            )
        ); // Tratamento de uma Stream para conseguir receber uma String
        this.out = new PrintWriter(socket.getOutputStream(), true)  ; // Tratamento de uma Stream para conseguir passar uma String e não precisar ficar quebrando linha
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public String getMessage() {
        try {
            return in.readLine();

        } catch (IOException e) {
            return null;
        }
    }

    public boolean sendMessage(String messageClient) {
        out.println(messageClient);
        return !out.checkError();
    }
}
