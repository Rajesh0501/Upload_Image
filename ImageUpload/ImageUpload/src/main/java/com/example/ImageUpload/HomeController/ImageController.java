package com.example.ImageUpload.HomeController;

import com.example.ImageUpload.Model.Image;
import com.example.ImageUpload.Repository.ImgRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ImageController {
    @Autowired
     private ImgRepo imgRepo;
    @GetMapping("/")
    public String index(Model m){
        List<Image> list = imgRepo.findAll();
        m.addAttribute("list",list);
        return "index";
    }
    @PostMapping("/imageUpload")
    public String uploadImage(@RequestParam MultipartFile imgName, HttpSession session) throws IOException {
        Image img = new Image();
        img.setImgName(imgName.getOriginalFilename());
        Image uploadImage = imgRepo.save(img);
       // System.out.println(imgName.getOriginalFilename());
        if (uploadImage!=null){
            File saveFile =  new ClassPathResource("static/images").getFile();
            Path p = Paths.get(saveFile.getAbsolutePath()+File.separator+imgName.getOriginalFilename());
            System.out.println(p);
            Files.copy(imgName.getInputStream(),p, StandardCopyOption.REPLACE_EXISTING);
            session.setAttribute("msg","upload image successfully");
        }
        return "redirect:/";
    }
}
