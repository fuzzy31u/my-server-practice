import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client01 {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 8001);
				FileInputStream fis = new FileInputStream("client_send.txt");
				FileOutputStream fos = new FileOutputStream("client_recv.txt");) {

			int ch;
			// Sending contents of client_send.txt to the server.
			OutputStream os = socket.getOutputStream();
			while ((ch = fis.read()) != -1) {
				os.write(ch);
			}
			// Sending "0" as showing the end point.
			os.write(0);
			// Output reply from the server to client_recv.txt.
			InputStream is = socket.getInputStream();
			while ((ch = is.read()) != -1) {
				fos.write(ch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
