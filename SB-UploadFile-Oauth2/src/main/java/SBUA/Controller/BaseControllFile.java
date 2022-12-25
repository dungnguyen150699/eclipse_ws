package SBUA.Controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import SBUA.Model.MyFile;

@Controller
public class BaseControllFile {
	private static Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("myFile", new MyFile());
		System.out.println(CURRENT_FOLDER + "Xx");
		return "index";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(MyFile myFile, Model model) {
		model.addAttribute("message", "Upload success");
		model.addAttribute("description", myFile.getDescription());
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			String fileName = multipartFile.getOriginalFilename();
			File file = new File(this.getFolderUpload(), fileName);
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Upload failed");
		}
		return "result";
	}

	public File getFolderUpload() {
		Path staticPath = Paths.get("src\\main\\resources\\static\\img");
//        Path imagePath = Paths.get("img");
		CURRENT_FOLDER = CURRENT_FOLDER.resolve(staticPath);
		System.out.println(CURRENT_FOLDER + "______________2__");
		File folderUpload = new File(CURRENT_FOLDER.toString());
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}
}
