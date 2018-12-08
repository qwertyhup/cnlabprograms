import java.util.Scanner;

public class BellmanFord {

    public static int MAX_VALUE = 999;
    public int nv;
    public int D[];

    public BellmanFord(int n) {
        nv = n;
        D = new int[n+1];
    }

    public void shortestPath(int s, int arr[][]) {

        for(int i=1;i<=nv;i++) {
            D[i] = MAX_VALUE;
        }

        D[s] = 0;

        for(int k=1;k<=nv-1;k++)
        {
            for(int i=1;i<=nv;i++)
            {
                for(int j=1;j<=nv;j++)
                {
                    if(arr[i][j]!=MAX_VALUE)
                    {
                        if(D[j]>D[i]+arr[i][j])
                        {
                            D[j] = D[i] + arr[i][j];
                        }
                    }
                }
            }
        }

        for(int i=1;i<=nv;i++)
        {
            for(int j=1;j<=nv;j++)
            {
                if(arr[i][j]!=MAX_VALUE)
                    {
                        if(D[j]>D[i] + arr[i][j])
                        {
                            System.out.println("The graph contains negative edges");
                            System.exit(0);
                        }
                    }
            }
        }

        for(int i=1;i<=nv;i++)
        {
            System.out.println("Distance from " + s + " to " + i + " is = " + D[i]);
        }

    }

    public static void main(String[] args)
    {
        int n, s, arr[][];
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of nodes");
        n = sc.nextInt();

        System.out.println("Enter the source vertex:");
        s = sc.nextInt();

        arr = new int[n+1][n+1];

        BellmanFord bellmanFord = new BellmanFord(n);
        
        System.out.println("Enter the adjacecy matrix");
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                arr[i][j] = sc.nextInt();

                if(arr[i][j] == 0)
                    arr[i][j] = MAX_VALUE;

                if(i==j)
                    arr[i][j] = 0;   
            }
        }

        bellmanFord.shortestPath(s, arr);
        sc.close();
    }

}