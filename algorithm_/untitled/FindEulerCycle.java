import java.util.*;
public class FindEulerCycle {
    int v,e,u;
    int [][]x,c;
    List<Integer> stackV = new ArrayList<>();
    Set<Integer> stackR = new HashSet<>();
    Scanner ca = new Scanner(System.in);
    public void init(){
        v = ca.nextInt();
        e = ca.nextInt();
        x = new int[v+1][v+1];
        c = new int[v+1][v+1];
        for(int i=0 ; i<e ; i++){
            int a = ca.nextInt();
            int b = ca.nextInt();
            x[a][b] = 1;
        }
        System.out.println("Type your begin V: ");
        u = ca.nextInt();
    }

    public void dfs(){
        stackV.add(u);
        stackR.add(u);
        while(!stackV.isEmpty()){
            Integer pop = pop(stackV);
            for(int i=1 ; i<=v ; i++) {
                if (x[pop][i] == 1 && c[pop][i] == 0) {
                    c[pop][i] = 1;
                    c[i][pop] = 1;
                    stackV.add(i);
                    stackR.add(pop);
                }
            }
        }
    }

    public Integer pop(List<Integer> st){
        Integer i = st.get(st.size() - 1);
        st.remove(st.size()-1);
        return i;
    }

    public void printfResult(){
        System.out.println("Euler Cyle is :");
        for(int i:stackR){
            System.out.print(i);
        }
    }

    public static void main(String[] args) {
        FindEulerCycle findEulerCycle = new FindEulerCycle();
        findEulerCycle.init();
        findEulerCycle.dfs();
        findEulerCycle.printfResult();
    }
}
