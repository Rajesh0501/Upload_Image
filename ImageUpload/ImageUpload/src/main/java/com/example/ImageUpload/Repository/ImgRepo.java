package com.example.ImageUpload.Repository;

import com.example.ImageUpload.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepo extends JpaRepository<Image,Integer> {
}
