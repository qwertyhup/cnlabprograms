import java.util.Scanner;

public class RSA {

    public static int gcd(int m, int n) {
        int r;
        while(n!=0) {
            r = m%n;
            m = n;
            n = r;
        }
        return m;
    }

    public static void main(String[] args) {

        int p,q,n,e,d,phi=0,i=0;
        int len;
        String msg;
        int nummes[] = new int[100];
        int encrypted[] = new int[100];
        int decrypted[] = new int[100];
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the values of P and Q:");
        p = scan.nextInt();
        q = scan.nextInt();

        System.out.println("Enter the message to be encrypted: ");
        msg = scan.next();

        n = p*q;
        phi = (p-1)*(q-1);

        for(i=2;i<phi;i++) {
            if(gcd(i,phi)==1)
                break;
        }
        e = i;

        for(i=2;i<phi;i++) {
            if((e*i-1)%phi == 0)
                break;
        }
        d = i;
        len = msg.length();

        for(i=0;i<len;i++) {
            char c = msg.charAt(i);
            nummes[i] = c-96;
        }

        for(i=0;i<len;i++) {
            encrypted[i] = 1;
            for(int j=0;j<e;j++)
                encrypted[i] = (encrypted[i]*nummes[i])%n; 
        }

        System.out.println("Encrypted message: ");
        for(i=0;i<len;i++)
            System.out.print(encrypted[i] + " ");
        System.out.println();

        for(i=0;i<len;i++) {
            decrypted[i] = 1;
            for(int j=0;j<d;j++) {
                decrypted[i] = (decrypted[i]*encrypted[i])%n;
            }
        }

        System.out.println("Decrypted message: ");
        for(i=0;i<len;i++)
            System.out.print((char)(decrypted[i]+96) + " ");
        System.err.println();

    }

}