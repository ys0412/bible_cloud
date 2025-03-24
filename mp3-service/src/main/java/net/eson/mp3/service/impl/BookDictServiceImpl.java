package net.eson.mp3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.eson.mp3.entity.BookDict;
import net.eson.mp3.mapper.BookDictMapper;
import net.eson.mp3.service.BookDictService;
import org.springframework.stereotype.Service;

/**
 * @author Eson
 * @date 2025年03月24日 11:32
 */
@Service
public class BookDictServiceImpl extends ServiceImpl<BookDictMapper, BookDict> implements BookDictService {
    @Override
    public boolean updateById(BookDict book) {

        return super.updateById(book);
    }
}
    