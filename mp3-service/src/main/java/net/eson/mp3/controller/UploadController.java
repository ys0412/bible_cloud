package net.eson.mp3.controller;

import net.eson.mp3.entity.BookDict;
import net.eson.mp3.entity.ChapterDict;
import net.eson.mp3.entity.ScrollDict;
import net.eson.mp3.service.BookDictService;
import net.eson.mp3.service.ChapterService;
import net.eson.mp3.service.ScrollService;
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
    private static final String IMAGES_UPLOAD_DIR = "D:/bible/upload/images/"; // 图片存储路径
    private static final String MP3_UPLOAD_DIR = "D:/bible/upload/mp3/"; // mp3存储路径
    private static final String TXT_UPLOAD_DIR = "D:/bible/upload/txt/"; // txt存储路径
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private BookDictService bookDictService;

    @Autowired
    private ScrollService scrollService;

    @Autowired
    private ChapterService chapterService;

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
        File destinationFile = new File(IMAGES_UPLOAD_DIR + newFilename);
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

    @PostMapping("/scrollCover")
    public ResponseEntity<?> uploadScrollCover(@RequestParam("file") MultipartFile file,
                                        @RequestParam(value = "id", required = false) Integer scrollId) throws IOException {
        logger.info("请求上传图片, scrollId={}", scrollId);

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件不能为空");
        }

        // 生成唯一文件名
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        // 保存文件
        File destinationFile = new File(IMAGES_UPLOAD_DIR + newFilename);
        if (!destinationFile.getParentFile().exists()) {
            destinationFile.getParentFile().mkdirs(); // 创建上传目录
        }
        file.transferTo(destinationFile);

        // **新增操作：仅返回 `coverUrl`，不绑定到卷**
        if (scrollId == null || scrollId <= 0) {
            return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
        }

        // **更新操作：绑定 `coverUrl` 到已有卷**
        ScrollDict scroll = scrollService.getById(scrollId);
        if (scroll != null) {
            scroll.setCoverUrl(newFilename);
            scrollService.saveOrUpdate(scroll);
        }

        return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
    }

    @PostMapping("/chapterMp3")
    public ResponseEntity<?> uploadMp3(@RequestParam("file") MultipartFile file,
                                               @RequestParam(value = "chapterId", required = false) Integer chapterId) throws IOException {
        logger.info("请求上传MP3, chapterId={}", chapterId);

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件不能为空");
        }

        // 生成唯一文件名
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        // 保存文件
        File destinationFile = new File(MP3_UPLOAD_DIR + newFilename);
        if (!destinationFile.getParentFile().exists()) {
            destinationFile.getParentFile().mkdirs(); // 创建上传目录
        }
        file.transferTo(destinationFile);

        // **新增操作：仅返回 `coverUrl`，不绑定到书籍**
        if (chapterId == null || chapterId <= 0) {
            return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
        }

        // **更新操作：绑定 `coverUrl` 到已有书籍**
        ChapterDict chapterDict = chapterService.getById(chapterId);
        if (chapterDict != null) {
            chapterDict.setMp3Url(newFilename);
            chapterService.saveOrUpdate(chapterDict);
        }

        return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
    }

    @PostMapping("/chapterTxt")
    public ResponseEntity<?> uploadTxt(@RequestParam("file") MultipartFile file,
                                       @RequestParam(value = "chapterId", required = false) Integer chapterId) throws IOException {
        logger.info("请求上传TXT, scrollId={}", chapterId);

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件不能为空");
        }

        // 生成唯一文件名
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        // 保存文件
        File destinationFile = new File(TXT_UPLOAD_DIR + newFilename);
        if (!destinationFile.getParentFile().exists()) {
            destinationFile.getParentFile().mkdirs(); // 创建上传目录
        }
        file.transferTo(destinationFile);

        // **新增操作：仅返回 `coverUrl`，不绑定到书籍**
        if (chapterId == null || chapterId <= 0) {
            return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
        }

        // **更新操作：绑定 `coverUrl` 到已有书籍**
        ChapterDict chapterDict = chapterService.getById(chapterId);
        if (chapterDict != null) {
            chapterDict.setMp3Url(newFilename);
            chapterService.saveOrUpdate(chapterDict);
        }

        return ResponseEntity.ok().body("{\"url\": \"" + newFilename + "\"}");
    }


}
    