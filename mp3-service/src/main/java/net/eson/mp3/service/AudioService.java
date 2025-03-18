package net.eson.mp3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.eson.mp3.entity.BibleAudio;
import net.eson.mp3.mapper.BibleAudioMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Eson
 * @date 2025年03月18日 16:37
 */
public interface AudioService extends IService<BibleAudio> {

    List<BibleAudio> getAllChapters();

    BibleAudio getAudioById(Integer id);

    boolean addAudio(BibleAudio bibleAudio);

    public List<BibleAudio> getAudioByBookAndChapter(String book, Integer chapter);

}
    