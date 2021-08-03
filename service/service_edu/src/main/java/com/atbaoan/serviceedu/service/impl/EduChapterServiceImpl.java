package com.atbaoan.serviceedu.service.impl;

import com.atbaoan.common.handler.EmmaException;
import com.atbaoan.serviceedu.entity.EduChapter;
import com.atbaoan.serviceedu.entity.EduVideo;
import com.atbaoan.serviceedu.entity.vo.ChapterVo;
import com.atbaoan.serviceedu.entity.vo.VideoVo;
import com.atbaoan.serviceedu.mapper.EduChapterMapper;
import com.atbaoan.serviceedu.service.EduChapterService;
import com.atbaoan.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author emma
 * @since 2021-07-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoById(String id) {
        //根据课程id获取章节集合
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",id);
        List<EduChapter> eduChapters = baseMapper.selectList(chapterWrapper);
        if (CollectionUtils.isEmpty(eduChapters)){
            return null;
        }
        List<ChapterVo> result = new ArrayList<>();
        for (EduChapter eduChapter : eduChapters) {
            //封装最终的结果
            ChapterVo chapterVo = new ChapterVo();
            chapterVo.setId(eduChapter.getId());
            chapterVo.setTitle(eduChapter.getTitle());
            //根据章节id和课程id获取章节信息
            QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("course_id",id);
            videoQueryWrapper.eq("chapter_id",eduChapter.getId());
            List<EduVideo> list = eduVideoService.list(videoQueryWrapper);
            List<VideoVo> childList = new ArrayList<>();

            if (!CollectionUtils.isEmpty(list)){
                for (EduVideo eduVideo : list) {
                    VideoVo videoVo = new VideoVo();
                    videoVo.setId(eduVideo.getId());
                    videoVo.setTitle(eduVideo.getTitle());
                    childList.add(videoVo);
                }
                chapterVo.setChildren(childList);
            }
            result.add(chapterVo);
        }
        return result;
    }

    /**
     * 根据课程id删除章节
     * @param chapterid
     * @return
     */
    @Override
    public int deleteChapterByCourseId(String chapterid) {
        QueryWrapper<EduVideo>wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterid);
        int count = eduVideoService.count(wrapper);
        if(count==0){
            return baseMapper.deleteById(chapterid);
        }else{
            throw new EmmaException(20001,"该章节中有小节，不能删除");
        }
    }
}
