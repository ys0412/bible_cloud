package net.eson.mp3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.eson.mp3.entity.BibleAudio;
import net.eson.mp3.mapper.BibleAudioMapper;
import net.eson.mp3.service.AudioService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eson
 * @date 2025年03月18日 17:00
 */
@Service
public class AudioServiceImpl extends ServiceImpl<BibleAudioMapper, BibleAudio> implements AudioService {
    @Override
    public List<BibleAudio> getAllChapters() {
        return list();
    }

    @Override
    public BibleAudio getAudioById(Integer id) {
        return getById(id);
    }

    @Override
    public boolean addAudio(BibleAudio audio) {
        return save(audio);
    }

    @Override
    public List<BibleAudio> getAudioByBookAndChapter(String book, Integer chapter) {
        return lambdaQuery()
                .eq(BibleAudio::getBook,book)
                .eq(BibleAudio::getChapter,chapter)
                .list();
    }
}
    