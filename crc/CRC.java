import java.util.Scanner;

public class CRC {

    public static void div(int k, int arr[]) {
        int gp[] = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};
        int count = 0;
        for(int i=0;i<k;i++) {
            if(arr[i] == gp[0])
            {
                for(int j=i;j<17+i;j++) {
                    arr[j] = arr[j] ^ gp[count++];
                }
                count = 0;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int a[] = new int[100];
        int b[] = new int[100];
        int len, k, flag=0;

        System.out.println("Enter the length of the data frame: ");
        len = sc.nextInt();

        System.out.println("Enter the data frame: ");
        for(int i=0;i<len;i++) {
            a[i] = sc.nextInt();
        }

        for(int i=0;i<16;i++) {
            a[len++] = 0;
        }
        k = len-16;

        for(int i=0;i<len;i++) {
            b[i] = a[i];
        }

        CRC.div(k, a);

        for(int i=0;i<len;i++) {
            a[i] = a[i] ^ b[i];
        }

        System.out.println("The data to be transmitted");
        for(int i=0;i<len;i++) {
            System.err.print(a[i] + " ");
        }
        System.out.println();

        System.out.println("Enter the data you received: ");
        for(int i=0;i<len;i++) {
            a[i] = sc.nextInt();
        }

        CRC.div(k, a);

        for(int i=0;i<len;i++) {
            if(a[i]!=0) {
                flag = 1;
                break;
            }
        }

        if(flag == 1) {
            System.out.println("Error during transmission");
        } else {
            System.out.println("No error during transmission");
        }

    }

}