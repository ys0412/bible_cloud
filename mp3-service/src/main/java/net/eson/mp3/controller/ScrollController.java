package net.eson.mp3.controller;

import net.eson.mp3.entity.ScrollDict;
import net.eson.mp3.service.impl.ScrollServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Eson
 * @date 2025年03月24日 20:43
 */
@RestController
@RequestMapping("/scroll")
public class ScrollController {

    @Autowired
    private ScrollServiceImpl scrollerService;

    @GetMapping("/list/{bookId}")
    public List<ScrollDict> getScrollsByBook(@PathVariable Integer bookId) {
        return scrollerService.getScrollsWithBook(bookId);
    }

    // 🔹 根据 ID 查询书籍
    @GetMapping("/{id}")
    public ResponseEntity<ScrollDict> getScrollById(@PathVariable Integer id) {
        ScrollDict scroll = scrollerService.getById(id);
        return scroll != null ? ResponseEntity.ok(scroll) : ResponseEntity.notFound().build();
    }

    // 🔹 新增卷
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody ScrollDict scroll) {
        return scrollerService.save(scroll) ?
                ResponseEntity.ok("新增成功") :
                ResponseEntity.status(500).body("新增失败");
    }

    // 🔹 更新卷
    @PutMapping("/update")
    public ResponseEntity<String> updateScroll(@RequestBody ScrollDict scroll) {
        return scrollerService.updateById(scroll) ?
                ResponseEntity.ok("更新成功") :
                ResponseEntity.status(500).body("更新失败");
    }

    // 🔹 删除卷
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScroll(@PathVariable Integer id) {
        return scrollerService.removeById(id) ?
                ResponseEntity.ok("删除成功") :
                ResponseEntity.status(500).body("删除失败");
    }
}
    