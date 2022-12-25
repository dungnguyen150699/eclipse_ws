package SBUA.Model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MyFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private MultipartFile multipartFile;
	private String description;
}
