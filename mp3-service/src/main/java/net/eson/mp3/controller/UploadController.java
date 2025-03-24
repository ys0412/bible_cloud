package net.eson.mp3.controller;

import net.eson.mp3.entity.BookDict;
import net.eson.mp3.service.BookDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Eson
 * @date 2025年03月24日 13:29
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private static final String UPLOAD_DIR = "D:/bible/upload/images/"; // 你的文件存储路径
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private BookDictService bookDictService;

    @PostMapping("/bookCover")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam(value = "id", required = false) Integer bookId) throws IOException {
        logger.info("请求上传图片, bookId={}", bookId);

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件不能为空");
        }

        // 生成唯一文件名
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        // 保存文件
        File destinationFile = new File(UPLOAD_DIR + newFilename);
        if (!destinationFile.getParentFile().exists()) {
            destinationFile.getParentFile().mkdirs(); // 创建上传目录
        }
        file.transferTo(destinationFile);

        // **新增操作：仅返回 `coverUrl`，不绑定到书籍**
        if (bookId == null || bookId <= 0) {
            return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
        }

        // **更新操作：绑定 `coverUrl` 到已有书籍**
        BookDict book = bookDictService.getById(bookId);
        if (book != null) {
            book.setCoverUrl(newFilename);
            bookDictService.saveOrUpdate(book);
        }

        return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
    }


}
    