package net.eson.mp3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.eson.mp3.entity.ChapterDict;

import java.util.List;

public interface ChapterService extends IService<ChapterDict> {
    public List<ChapterDict> getChaptersByBookAndScroll(Integer bookId, Integer scrollId);
}
