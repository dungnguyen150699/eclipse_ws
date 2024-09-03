package algorithm_.ctdl_gt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DeleteFile {
	int n,count;
	List<Pair> pairs = new ArrayList<>();
	List<Pair> resultList = new ArrayList<>();
	Scanner ca = new Scanner(System.in);
	
	public void init() {
		n = ca.nextInt();
		for(int i=1 ; i<=n ; i++) {
			String key = ca.next();
			int val = ca.nextInt();
			pairs.add(new Pair(key,val,false));
		}
	}
	
	public void backTrack() {
		for(int i=0 ; i<pairs.size() ; i++) {
			int count=0;
			Pair maxFileCantDel = new Pair();
			Pair item = pairs.get(i);
			Pair minPair = item;
			if("y".equals(item.key) && !item.deleted) {
				count++;
				StringJoiner str =  new StringJoiner(",");
				str.add(i+"");
				item.deleted = true;
				List<Pair> resultFile = new ArrayList<DeleteFile.Pair>();
				for(int j=i+1 ; j<pairs.size() ; j++) {
					Pair item1 = pairs.get(j);
					if("y".equals(item1.key) && !item.deleted && item1.val>maxFileCantDel.val) {
						str.add(j+"");
						item1.deleted = true;
						if(item1.val<minPair.val) {
							minPair = item1;
						}
					}else if("n".equals(item1.key)) {
						if(item1.val>maxFileCantDel.val && item1.val>minPair.val){
							resetX(str.toString());
							maxFileCantDel.key=str.toString();
							resultFile.add(maxFileCantDel);
							maxFileCantDel.val=item1.val;
						}
					}
				}
				checkMaxAndCheckedDelete(resultFile);
			}
		}
	}
	
	public void resetX(String str) {
		String keys[] = str.split(",");
		List<Integer> ids = Arrays
				.asList(keys)
				.stream()
				.filter(item-> item!=null && !item.isEmpty())
				.map(item -> Integer.parseInt(item))
				.collect(Collectors.toList());
		ids.remove(0);
		for(int i : ids) {
			this.pairs.get(i).deleted = false;
		}
	}
	
	public void checkMaxAndCheckedDelete(List<Pair> resultFile ) {
		Pair pairSelected = new Pair();
		pairSelected.key = "";
		for(Pair pair : resultFile) {
			if(pair.key.length()>pairSelected.key.length()) {
				pairSelected = pair;
			}
		}
		
		String keys[] = pairSelected.key.split(",");
		List<Integer> ids = Arrays
				.asList(keys)
				.stream()
				.filter(item-> item!=null && !item.isEmpty())
				.map(item -> Integer.parseInt(item))
				.collect(Collectors.toList());
		if(ids.size()>0) {
			for(int i:ids) {
				this.pairs.get(i).deleted = true;
			}
			count ++;
		}
	}
	
	public class Pair{
		String key;
		int val;
		int count;
		boolean deleted;
		
		Pair(String key, int val , boolean deleted){
			this.key = key;
			this.val = val;
			this.deleted = deleted;
		}
		
		Pair(String key, int val){
			this.key = key;
			this.val = val;
		}
		
		Pair(){}
	}
	
	public static void main(String[] args) {
		DeleteFile d = new DeleteFile();
		d.init();
		d.backTrack();
		System.out.println(d.count);
	}
}


//3
//y 3
//y 4
//n 2
//2
