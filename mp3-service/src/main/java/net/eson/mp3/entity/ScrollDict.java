package net.eson.mp3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Eson
 * @date 2025年03月24日 20:23
 */
@Data
@TableName("scroll_dict")
public class ScrollDict {
    @TableId(type = IdType.AUTO)
    private Integer scrollId;
    @TableField("book_id")
    private Integer bookId;  // 外键，关联 book 表的 id
    private String scrollName;     // 书名
    private String author;
    private String coverUrl; // 封面url
    private String contentUrl;   // 内容url
    private String category;
    private String description;
    private String isbn;
    private Integer viewCount;
    private Integer likeCount;
    private Double rating;
    private Integer status; // 0: 下架, 1: 上架, 2: 审核中
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String language;
    private String tags;
}
    