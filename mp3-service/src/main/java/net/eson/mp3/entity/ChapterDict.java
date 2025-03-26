package net.eson.mp3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("chapter_dict")
public class ChapterDict {
    @TableId(type = IdType.AUTO)
    private Integer chapterId;
    @TableField("scroll_id")
    private Integer ScrollId;  // 外键，关联 scroll_dict 表的 id
    private String chapterName;     // 章名
    private Integer number; // 序号
    private String author;
    private String description;
    private Integer viewCount;
    private String contentUrl;
    private String mp3Url;
    private Integer status; // 0: 下架, 1: 上架, 2: 审核中
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}