import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws Exception
    {
        String address = "", filename, newfilename;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the server address: ");
        address = sc.next();

        Socket s = new Socket(address, 5000);
        System.out.println("Connected to Server");

        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        System.out.println("Enter the filename: ");
        filename = sc.next();
        newfilename = "client-"+filename;

        FileOutputStream fout = new FileOutputStream(new File(newfilename), true);

        System.out.println("Sending filename");
        dout.writeUTF(filename);
        dout.flush();

        System.out.println("Receiving File Size");
        long sz = Long.parseLong(din.readUTF());
        System.out.println("File size is " + sz + " bytes");

        int read = 0;
        byte buffer[] = new byte[1024];

        do {
            read = din.read(buffer, 0, buffer.length);
            fout.write(buffer, 0, read);
        } while(!(read<1024));

        System.out.println("Successfully written to file");

        din.close();
        dout.close();
        s.close();
        fout.close();
    }

}