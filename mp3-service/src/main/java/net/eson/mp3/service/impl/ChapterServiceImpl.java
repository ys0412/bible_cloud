package net.eson.mp3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.eson.mp3.entity.BookDict;
import net.eson.mp3.entity.ChapterDict;
import net.eson.mp3.entity.ScrollDict;
import net.eson.mp3.mapper.ChapterMapper;
import net.eson.mp3.mapper.ScrollMapper;
import net.eson.mp3.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, ChapterDict> implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private ScrollMapper scrollMapper;

    public List<ChapterDict> getChaptersByBookAndScroll(Integer bookId, Integer scrollId) {
        // 先查 ScrollDict，找到符合 bookId 的 scrollId 列表
        List<Integer> scrollIds = scrollMapper.selectList(
                new LambdaQueryWrapper<ScrollDict>().eq(ScrollDict::getBookId, bookId)
        ).stream().map(ScrollDict::getScrollId).collect(Collectors.toList());

        if (scrollIds.isEmpty()) {
            return Collections.emptyList(); // 没有匹配的卷，直接返回空列表
        }

        // 再查 ChapterDict，找出 scrollId 在列表里的数据
        LambdaQueryWrapper<ChapterDict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ChapterDict::getScrollId, scrollIds);
        if (scrollId != null) {
            queryWrapper.eq(ChapterDict::getScrollId, scrollId);
        }

        return chapterMapper.selectList(queryWrapper);
    }

    @Override
    public ChapterDict getChapterByBookScrollAndId(Integer bookId, Integer scrollId, Integer chapterId) {
        // 1. 先检查 scrollId 是否属于 bookId
        ScrollDict scroll = scrollMapper.selectOne(
                new LambdaQueryWrapper<ScrollDict>()
                        .eq(ScrollDict::getScrollId, scrollId)
                        .eq(ScrollDict::getBookId, bookId)
        );

        if (scroll == null) {
            return null; // 说明这个 scrollId 不属于 bookId
        }

        // 2. 再查询 chapter
        return chapterMapper.selectOne(
                new LambdaQueryWrapper<ChapterDict>()
                        .eq(ChapterDict::getScrollId, scrollId)
                        .eq(ChapterDict::getChapterId, chapterId)
        );
    }

}
