import java.sql.Array;
import java.util.*;

public class CheckEulerCycle {

    int v,e;
    int [][]x;
    int degV[];
    boolean isEulerCycle = true;
    Scanner ca = new Scanner(System.in);

    public void init(){
        v = ca.nextInt();
        e = ca.nextInt();
        x = new int[v+1][v+1];
        degV = new int[v+1];
        for(int i=0 ; i<e ; i++){
            int a = ca.nextInt();
            int b = ca.nextInt();
            x[a][b] = 1;
            x[b][a] = 1;
        }
    }

    public void calDegEachV(){
        for(int i=1; i<=v ; i++){
            int countDeg = 0;
            for(int j=1; j<=v ; j++) {
                if(x[i][j]==1){
                    countDeg++;
                }
            }
            if(countDeg==0 || countDeg%2!=0) isEulerCycle = false;
            degV[i] = countDeg;
        }

        int degNeedAddNode = 0;
        int degCount = 0;
        int degOptimal = 0;
        for(int i=1; i<=v ; i++){
            if(degV[i]==0 || degV[i]%2!=0){
                degNeedAddNode++;
                if(degV[i]==0){
                    degOptimal+=degV[i]+2;
                }else{
                    degOptimal+=degV[i]+1;
                }
                degCount += degV[i];
            }
        }
//        print result;
        if((degOptimal-2-degCount)%2==0){
            System.out.println((degOptimal-degCount)/2);
        }else{
            System.out.println((degOptimal-degCount)/2+1);
        }

    }

    public static void main(String[] args) {
        CheckEulerCycle checkEulerCycle = new CheckEulerCycle();
        checkEulerCycle.init();
        checkEulerCycle.calDegEachV();
    }
}
/*
5 5
1 2
1 3
2 5
1 4
2 3
 */