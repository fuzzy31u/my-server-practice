import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(8001);
				FileOutputStream fos = new FileOutputStream("server_recv.txt");
				FileInputStream fis = new FileInputStream("server_send.txt");) {
			System.out.println("Waiting for connecting from client.");
			Socket socket = server.accept();
			System.out.println("Client connected.");

			int ch;
			// Output contents which gets from client.
			InputStream is = socket.getInputStream();
			// Client adds "0" as an end point.
			while ((ch = is.read()) != 0) {
				fos.write(ch);
			}
			// Sending contents of server_send.txt to the client.
			OutputStream os = socket.getOutputStream();
			while ((ch = fis.read()) != -1) {
				os.write(ch);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
