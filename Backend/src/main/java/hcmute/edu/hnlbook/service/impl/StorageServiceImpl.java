package hcmute.edu.hnlbook.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hcmute.edu.hnlbook.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class StorageServiceImpl implements StorageService {

  // Info to connect to cloudinary
  @Override
  public Cloudinary cloudinary() {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "drwc3s5id",
        "api_key", "932855992229293",
        "api_secret", "XvZJzKUrgbttxZepD5mR4oXvM-U"));
    return cloudinary;
  }
  
  // Check file type: image png, jpg, jpeg, bmp
  @Override
  public boolean isImage(MultipartFile file) {
    return Arrays.asList(new String[] {"image/png","image/jpg","image/jpeg","image/bmp"})
        .contains(Objects.requireNonNull(file.getContentType()).trim().toLowerCase());
  }

  // Upload image to cloudinary
  @Override
  public String uploadImage(MultipartFile file, String filePath) {
    Map r;
    try {
      // "resource_type","auto"  : auto define upload file type
      // "public_id",filePath  : file path of image
      r = this.cloudinary().uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type","auto","public_id",filePath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // Return image url
    return (String) r.get("secure_url");
  }
}
