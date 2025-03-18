package net.eson.mp3.controller;

import net.eson.mp3.entity.BibleAudio;
import net.eson.mp3.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BibleAudio> getAudioById(Integer id){
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
    