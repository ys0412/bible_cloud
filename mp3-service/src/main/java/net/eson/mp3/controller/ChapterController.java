package net.eson.mp3.controller;

import net.eson.mp3.entity.ChapterDict;
import net.eson.mp3.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    /**
     * 通过 bookId 和 scrollId 获取所有章节
     */
    @GetMapping("/list")
    public ResponseEntity<List<ChapterDict>> getChaptersByBookAndScroll(
            @RequestParam Integer bookId,
            @RequestParam Integer scrollId) {
        List<ChapterDict> chapters = chapterService.getChaptersByBookAndScroll(bookId, scrollId);
        return ResponseEntity.ok(chapters);
    }
}
