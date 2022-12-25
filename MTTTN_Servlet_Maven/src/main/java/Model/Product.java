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
public class Product implements Serializable {
	
	private int id,count;
	private float price;
	private List<BillDetail> billDetails;
	
}
