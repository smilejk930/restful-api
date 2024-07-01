package kr.app.restfulapi.uga.file.util;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import jakarta.annotation.PostConstruct;

@Component
public class FileUtils {

  private static String[] allowedExtensions;

  @Value("${file.allowed-extensions}")
  private String[] allowedExtensionsFromProperties;

  @PostConstruct
  public void init() {
    allowedExtensions = allowedExtensionsFromProperties;
  }

  public static boolean isAllowedExtension(MultipartFile file) {
    String fileExtension = getFileExtension(file);
    return Arrays.stream(allowedExtensions).anyMatch(ext -> ext.equalsIgnoreCase(fileExtension));
  }

  // 파일에서 확장자 추출
  public static String getFileExtension(MultipartFile file) {
    String originalFileName = file.getOriginalFilename();
    return originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
  }

  // 파일명에서 확장자 추출
  public static String getFileExtension(String fileName) {
    return fileName.substring(fileName.lastIndexOf('.') + 1);
  }

  // 이미지 파일 확장자를 기준으로 MIME 타입을 결정
  public static String determineImageContentType(String fileName) {
    String extension = getFileExtension(fileName);
    switch (extension.toLowerCase()) {
      case "jpg":
      case "jpeg":
        return MediaType.IMAGE_JPEG_VALUE;
      case "png":
        return MediaType.IMAGE_PNG_VALUE;
      case "gif":
        return MediaType.IMAGE_GIF_VALUE;
      // 추가적으로 필요한 이미지 포맷을 여기에 추가할 수 있습니다.
      default:
        return null; // 기타 파일 형식일 경우 null 반환
    }
  }
}
