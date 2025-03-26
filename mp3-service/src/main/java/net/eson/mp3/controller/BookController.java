package net.eson.mp3.controller;

import net.eson.mp3.entity.BookDict;
import net.eson.mp3.service.BookDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Eson
 * @date 2025年03月24日 11:08
 */

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookDictService bookDictService;

    // 🔹 查询所有书籍
    @GetMapping("/list")
    public ResponseEntity<List<BookDict>> listBooks() {
        return ResponseEntity.ok(bookDictService.list());
    }

    // 🔹 根据 ID 查询书籍
    @GetMapping("/{id}")
    public ResponseEntity<BookDict> getBookById(@PathVariable Integer id) {
        BookDict book = bookDictService.getById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    // 🔹 新增书籍
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookDict book) {
        return bookDictService.save(book) ?
                ResponseEntity.ok("新增成功") :
                ResponseEntity.status(500).body("新增失败");
    }

    // 🔹 更新书籍
    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody BookDict book) {
        return bookDictService.updateById(book) ?
                ResponseEntity.ok("更新成功") :
                ResponseEntity.status(500).body("更新失败");
    }

    // 🔹 删除书籍
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        return bookDictService.removeById(id) ?
                ResponseEntity.ok("删除成功") :
                ResponseEntity.status(500).body("删除失败");
    }


}
