package Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private int id;
	private String name;
	private Date birth;
	private List<Bill> bills;
	
	public static void main(String []args) {
//		System.out.println(new User().getName());
	}
}
