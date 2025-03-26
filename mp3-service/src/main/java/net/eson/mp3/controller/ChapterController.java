package net.eson.mp3.controller;

import net.eson.mp3.entity.BookDict;
import net.eson.mp3.entity.ChapterDict;
import net.eson.mp3.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getChapter")
    public ResponseEntity<ChapterDict> getChapterByBookScrollAndId(
            @RequestParam Integer bookId,
            @RequestParam Integer scrollId,
            @RequestParam Integer chapterId) {
        ChapterDict chapter = chapterService.getChapterByBookScrollAndId(bookId, scrollId, chapterId);
        if (chapter != null) {
            return ResponseEntity.ok(chapter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 🔹 新增书籍
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody ChapterDict chapter) {
        return chapterService.save(chapter) ?
                ResponseEntity.ok("新增成功") :
                ResponseEntity.status(500).body("新增失败");
    }

    // 🔹 更新书籍
    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody ChapterDict chapter) {
        return chapterService.updateById(chapter) ?
                ResponseEntity.ok("更新成功") :
                ResponseEntity.status(500).body("更新失败");
    }

    // 🔹 删除书籍
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        return chapterService.removeById(id) ?
                ResponseEntity.ok("删除成功") :
                ResponseEntity.status(500).body("删除失败");
    }
}
