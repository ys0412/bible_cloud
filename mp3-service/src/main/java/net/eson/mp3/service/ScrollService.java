package net.eson.mp3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.eson.mp3.entity.ScrollDict;

import java.util.List;

/**
 * @author Eson
 * @date 2025年03月24日 20:49
 */
public interface ScrollService extends IService<ScrollDict> {
    public List<ScrollDict> getScrollsWithBook(Integer bookId);
}
    