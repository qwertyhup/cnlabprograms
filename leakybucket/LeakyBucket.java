import java.util.Scanner;

public class LeakyBucket {

    public static void main(String[] args) {

        int n, outgoing=0, source=0, bucketSize=0;
        int incoming[];
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of incoming packets");
        n = sc.nextInt();

        System.out.println("Enter the size of the bucket");
        bucketSize = sc.nextInt();

        System.out.println("Enter the outgoing rate: ");
        outgoing = sc.nextInt();

        incoming = new int[n];

        System.out.println("Enter the incoming data");
        for(int i=0;i<n;i++) {
            incoming[i] = sc.nextInt();
        }

        System.out.println("Incoming Packet| Packets Dropped| Packets Sent| Packets Left");
        int i=0;
        do {
            int pktReceived = 0, pktSent = 0, pktDropped = 0;

            if(i<n) {
                pktReceived = incoming[i];
                if(pktReceived<(bucketSize-source)) {
                    source+=pktReceived;
                }
                else {
                    pktDropped = pktReceived - (bucketSize-source);
                    source = bucketSize;
                }
            }

            if(source<outgoing) {
                pktSent = source;
                source = 0;
            }
            else {
                source -= outgoing;
                pktSent = outgoing;
            }

            System.out.println(pktReceived + "\t\t" + pktDropped + "\t\t" + pktSent + "\t\t" + source);

            try {
                Thread.sleep(2000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            i++;

        } while(i<n || source!=0);

    }

}