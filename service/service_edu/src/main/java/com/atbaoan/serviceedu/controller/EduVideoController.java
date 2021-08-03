package com.atbaoan.serviceedu.controller;


import com.atbaoan.commonutils.R;
import com.atbaoan.serviceedu.entity.EduVideo;
import com.atbaoan.serviceedu.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author emma
 * @since 2021-07-15
 */
@RestController
@RequestMapping("/serviceedu/eduVideo")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
//    @Autowired
//    private VodClient vodClient;
    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public R addChapter(
            @ApiParam(name = "addvideo" ,value = "添加小节",required = true)
            @RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }
    @DeleteMapping("{videoId}")
    @ApiOperation(value = "删除小节")
    public R deleteVideo(@PathVariable String videoId){
        //删除视频，通过feign功能
        //获取视频id即videosourceid
        EduVideo video = videoService.getById(videoId);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){

//            vodClient.deleteVideoById(videoSourceId);
        }

        videoService.removeById(videoId);
        return R.ok();
    }
    @GetMapping("{videoId}")
    @ApiOperation(value = "通过videoid获取小节")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("eduVideo",eduVideo);
    }

    @PostMapping("updateVideo")
    @ApiOperation(value = "修改小节")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }

}

