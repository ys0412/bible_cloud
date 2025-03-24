package net.eson.mp3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import net.eson.mp3.entity.ScrollDict;
import net.eson.mp3.mapper.ScrollMapper;
import net.eson.mp3.service.ScrollService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eson
 * @date 2025年03月24日 20:50
 */
@Service
public class ScrollerServiceImpl extends ServiceImpl<ScrollMapper,ScrollDict> implements ScrollService {
    @Override
    public List<ScrollDict> getScrollsWithBook(Integer bookId) {
        return lambdaQuery()
                .eq(ScrollDict::getBookId,bookId)
                .list();
    }
}
    