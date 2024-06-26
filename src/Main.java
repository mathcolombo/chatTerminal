
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {

    private static Server server;
    private static Client client;
    private static Scanner scan;
    private static String SERVER_IP;
    private static int SERVER_PORT;

    public static void main(String[] args) {
        try {
            server = new Server();
            client = new Client();
            scan = new Scanner(System.in);
    
            String LOCAL_IP = InetAddress.getLocalHost().getHostAddress(); // Armazena o IP local
            System.out.println("O seu IP é " + LOCAL_IP); // Mostra o IP local para o cliente
            
            System.out.print("Digite a porta na qual seu servidor será criado: ");
            int PORT = scan.nextInt();

            server.startServer(PORT);

            System.out.print("Digite o endereço IP da máquina ao qual você deseja se conectar: ");
            SERVER_IP = scan.next();
            //SERVER-_IP = "127.0.0.1"
            System.out.print("Digite a porta do servidor ao qual você deseja se conectar: ");
            SERVER_PORT = scan.nextInt();

            client.startClient(SERVER_IP, SERVER_PORT);
            
            new Thread(() -> {
                try {
                    server.connectionLoop();
                } catch (IOException e) {
                    System.err.println("O servidor não conseguiu se conectar como cliente. Por favor reinicie");
                    e.printStackTrace();
                }
            }).start();
            client.messageLoop();

        } catch (Exception e) {
            System.err.println("O programa não conseguiu subir o servidor. Por favor reinicie");
        }

    }
}
