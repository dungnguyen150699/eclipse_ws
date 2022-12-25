package Model;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
	private int id;
	private float totalPrice;
	private User user;
	private List<BillDetail> billDetails;
}
