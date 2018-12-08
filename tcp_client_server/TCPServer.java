import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws Exception
    {

        ServerSocket ss = new ServerSocket(5000);

        do {
            System.out.println("Waiting for a connection");
            Socket s = ss.accept();
            System.out.println("Accepted incoming connection");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String filename = din.readUTF();
            System.out.println("Received filename");

            FileInputStream fin = new FileInputStream(new File(filename));
            long sz = (long) fin.available();

            System.out.println("Sending size of file");
            dout.writeUTF(Long.toString(sz));
            dout.flush();

            int read;
            byte[] buffer = new byte[1024];

            while((read=fin.read(buffer, 0, buffer.length)) != -1) {
                dout.write(buffer, 0, read);
                dout.flush();
            }

            System.out.println("Send complete");

            s.close();
            fin.close();
            dout.close();
            din.close();

        } while(true);

    }

}