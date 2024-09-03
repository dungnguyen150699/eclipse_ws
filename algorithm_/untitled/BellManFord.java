import java.util.*;

public class BellManFord {
    int n,m,u,v;
    int [][]x;
    int []trace,d;
    int max = 1000000000;
    Scanner ca = new Scanner(System.in);
    public void init(){
        n = ca.nextInt();
        m = ca.nextInt();
        x = new int[n+1][n+1];
        d = new int[n+1];
        trace = new int[n+1];
        for(int i=1 ; i<=n ; i++){
            for(int j=1 ; j<=n ; j++) {
                x[i][j]=max;
            }
        }
        for(int i=1 ; i<=m ; i++){
            int a = ca.nextInt();
            int b = ca.nextInt();
            int distance = ca.nextInt();
            x[a][b] = distance;
        }
        for(int i=1 ; i<=n ; i++){
            if(i==1) {
                d[1] = 0;
            }
            else {
                d[i] = x[1][i];
            }
            trace[i] = 1;
        }
        System.out.println("Type begin, end: ");
        u = ca.nextInt();
        v = ca.nextInt();
    }

    public void funcBellManFord(){
        int k = 1;
        while(k<=n-2){
            for(int i=1 ; i<=n ; i++){
                for(int j=1 ; j<=n ; j++){
                    if(d[i] > (d[j] + x[j][i]) ){
                        d[i] = (d[j] + x[j][i]);
                        trace[i] = j;
                    }
                }
            }
            k++;
        }
    }

    public void printResult(){
        boolean isEnd = false;
        int tv = v ;
        StringJoiner stringJoiner = new StringJoiner("<-");
        stringJoiner.add(tv+"");
        while(!isEnd){
            int pv = trace[tv];
            stringJoiner.add(pv+"");
            tv = pv;
            if(d[pv]==max){
                System.out.println("No Result: u -> v");
                isEnd = true;
            }
            if(tv == u){
                isEnd = true;
            }
        }
        System.out.println(stringJoiner.toString());
        System.out.println("The Best Distant is: " + d[v]);
    }

    public void printX(){
        for(int i=1 ; i<=n ; i++){
            for(int j=1 ; j<=n ; j++){
                System.out.print(x[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        BellManFord bellManFord = new BellManFord();
        bellManFord.init();
        bellManFord.printX();
        bellManFord.funcBellManFord();
        bellManFord.printResult();
    }
}
