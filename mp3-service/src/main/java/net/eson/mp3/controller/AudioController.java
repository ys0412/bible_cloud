package net.eson.mp3.controller;

import net.eson.mp3.entity.BibleAudio;
import net.eson.mp3.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Eson
 * @date 2025年03月18日 16:36
 */
@RestController
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @Value("${mp3.path}")
    private String mp3Path;

    @GetMapping("/play/{fileName}")
    public Mono<ResponseEntity<InputStreamResource>> playMp3(@PathVariable String fileName) throws IOException {
        System.out.println("请求播放："+fileName);
        ClassPathResource mp3File = new ClassPathResource("static/mp3/" + fileName);
        if(!mp3File.exists()){return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());}
        InputStream inputStream = mp3File.getInputStream();
        return Mono.just(ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline;filename="+fileName)
                .header(HttpHeaders.ACCEPT_RANGES, "bytes") // 添加断点续传支持
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(new InputStreamResource(inputStream)));
    }

    /*
     * 返回所有章节
     * @author Eson
     * @date 2025/3/18 17:11
     * @return org.springframework.http.ResponseEntity<java.util.List<net.eson.mp3.entity.BibleAudio>>
     */
    @GetMapping("/chapters")
    public ResponseEntity<List<BibleAudio>> getAllChapters(){
        return ResponseEntity.ok(audioService.getAllChapters());
    }
    /*
     * 获取单个章节
     * @author Eson
     * @date 2025/3/18 17:14
     * @param id
     * @return org.springframework.http.ResponseEntity<net.eson.mp3.entity.BibleAudio>
     */
    @GetMapping("/audio/{id}")
    public ResponseEntity<BibleAudio> getAudioById(@PathVariable Integer id){
        BibleAudio audio = audioService.getAudioById(id);
        return audio != null? ResponseEntity.ok(audio):ResponseEntity.notFound().build();
    }

    /*
     * 按书名，章节名查音频
     * @author Eson
     * @date 2025/3/18 17:23
     * @param book
     * @param chapter
     * @return org.springframework.http.ResponseEntity<java.util.List<net.eson.mp3.entity.BibleAudio>>
     */
    @GetMapping("/audio")
    public ResponseEntity<List<BibleAudio>> getAudioByBookAndChapter(@RequestParam String book,@RequestParam Integer chapter){
        return ResponseEntity.ok(audioService.getAudioByBookAndChapter(book, chapter));
    }

    /*
     * 新增音频
     * @author Eson
     * @date 2025/3/18 17:28
     * @param audio
     * @return org.springframework.http.ResponseEntity<java.lang.String>
     */
    @PostMapping("/add")
    public ResponseEntity<String> addAudio(BibleAudio audio){
        return audioService.addAudio(audio)? ResponseEntity.ok("新增成功"):ResponseEntity.status(500).body("新增失败");
    }
}
    