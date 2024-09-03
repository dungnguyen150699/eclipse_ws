import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteFile2 {
 /*
   Xử lý bài này bằng backtrack _ DeleteFile kia đúng rồi
  */

    public static class Pair{
        String key;
        int val;

        Pair(){
        }

        public Pair(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    int n;
    List<DeleteFile2.Pair> files = new ArrayList<>();
    Scanner ca = new Scanner(System.in);
    List<List<Integer>> results = new ArrayList<>();
    int count=0;
    public void init(){
        n = ca.nextInt();
        for(int i=0 ; i<n ; i++){
            Pair pair = new Pair();
            pair.key = ca.next();
            pair.val = ca.nextInt();
            this.files.add(pair);
        }
    }

    public List<Integer> backTrack(int i, Pair minDelFile, List<Integer> ids,
                          int minLenghtFile){
        if(i==n-1) return new ArrayList<>();
        for(int j=i+1 ; j<n ; j++){
            Pair jFile = files.get(j);
            if("y".equals(jFile.key) && !ids.contains(j)){
                if(jFile.val > minDelFile.val){
                    ids.add(j);
                }
                if(jFile.val<minLenghtFile){
                    minLenghtFile = jFile.val;
                }
            }else {
                if("n".equals(jFile.key)){
                    if(jFile.val>minDelFile.val){
                        if(jFile.val>minLenghtFile){
                            List<Integer> resetIds = new ArrayList<>();
                            resetIds.add(i);
                            backTrack(i, jFile, resetIds, minLenghtFile);
                        }else{
                            minDelFile = jFile;
                        }
                    }
                }
            }
        }
        results.add(ids);
        return ids;
    }

    public List<Integer> extractIdMax(){
        if(results.size()==0)return  new ArrayList<>();
        List<Integer> maxId = new ArrayList<>();
        int max=0;
        for(List<Integer> item : this.results){
            if(item.size()>max){
                max = item.size();
                maxId = item;
            }
        }
        this.results = new ArrayList<>();
        this.count++;
        return maxId;
    }

    public static void main(String[] args) {
        DeleteFile2 deleteFile2 = new DeleteFile2();
        deleteFile2.init();
        Pair initItem = new Pair("x",0);
        List<Integer> ids = new ArrayList<>();
        boolean meetYFile = false;
        int i=0;
        while(!meetYFile && i<deleteFile2.n){
            if("y".equals(deleteFile2.files.get(i).key)){
                meetYFile = true;
                ids.add(i);
                continue;
            }
            i++;
        }
        deleteFile2.backTrack(i,initItem,ids,deleteFile2.files.get(i).val);
        ids = deleteFile2.extractIdMax();
        for(int j=i+1 ; j<deleteFile2.n ; j++){
            if(!ids.contains(j) && "y".equals(deleteFile2.files.get(j).key)){
                ids.add(j);
                deleteFile2.backTrack(j,initItem,ids,deleteFile2.files.get(j).val);
                ids = deleteFile2.extractIdMax();
            }
        }
        System.out.println(deleteFile2.count);
    }

//    7
//        y 5
//        n 4
//        y 3
//        n 2
//        y 3
//        n 4
//        y 5
}
