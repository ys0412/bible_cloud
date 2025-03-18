package net.eson.mp3.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Eson
 * @date 2025年03月18日 16:53
 */
@Data
@TableName("bible_audio")
public class BibleAudio {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String book;     // 书名
    private Integer chapter; // 章节号
    private Integer verse;   // 节号
    private String url;      // 音频地址
    private Integer duration; // 音频时长（秒）

}
    