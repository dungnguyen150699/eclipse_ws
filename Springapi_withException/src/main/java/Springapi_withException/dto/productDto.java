package Springapi_withException.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class productDto implements Serializable {
	private String nameProduct;
	private float price;
	private int count;
}
