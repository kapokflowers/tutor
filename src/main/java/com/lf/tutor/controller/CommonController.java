package com.lf.tutor.controller;

import com.lf.tutor.Service.CommonService;
import com.lf.tutor.Service.ImageService;
import com.lf.tutor.config.NormalCfg;
import com.lf.tutor.config.ReturnCfg;
import com.lf.tutor.domain.Image;
import com.lf.tutor.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//公用控制器
@RestController
public class CommonController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ImageService imageService;

    /**
     * 获取年级列表
     * @return
     */
    @RequestMapping("/common/grade/list")
    public ResultDTO getGradeList(){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(commonService.getGradeList());
        return resultDTO;
    }

    /**
     * 获取学院列表
     * @return
     */
    @RequestMapping("/common/institute/list")
    public ResultDTO getInstituteList(){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(commonService.getInstituteList());
        return resultDTO;
    }

    /**
     * 获取科目列表
     * @return
     */
    @RequestMapping("/common/subject/list")
    public ResultDTO getSubjectList(){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(commonService.getSubjectList());
        return resultDTO;
    }

    /**
     * 获取教员登记列表
     * @return
     */
    @RequestMapping("/common/techerlevel/list")
    public ResultDTO getTecherLevelList(){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(commonService.getTecherLevelList());
        return resultDTO;
    }

    /**
     * 获取授课时间列表
     * @return
     */
    @RequestMapping("/common/timetype/list")
    public ResultDTO getTimeTypeList(){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(commonService.getTimeTypeList());
        return resultDTO;
    }

    /**
     * 头像上传
     * @return
     */
    @RequestMapping("/common/headpic/upload")
    public ResultDTO picUpload(HttpServletRequest request, MultipartFile file) throws IOException {
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        if (file.isEmpty()) {
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("上传文件失败");
            return resultDTO;
        }
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        if(uId == null || uId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        //获取文件输入流
        InputStream fileInsteam = file.getInputStream();
        byte[] fileByte = new byte[(int) file.getSize()];
        //把文件的内容读进一个字节数据
        fileInsteam.read(fileByte);
        //把文件的内容存到image表，如果已经存在，则更新图片内容，没有则新增一条记录
        Image image = new Image();
        image.setImageType("1");
        image.setUserId(Integer.valueOf(uId));
        image.setImageContent(fileByte);
        image.setIsValid("Y");
        if(imageService.getImage(image) != null){
            imageService.update(image);
        }else{
            imageService.insert(image);
        }

        return resultDTO;
    }

    /**
     * 身份证上传
     * @return
     */
    @RequestMapping("/common/id/upload")
    public ResultDTO idIpload(HttpServletRequest request, MultipartFile file) throws IOException {
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        if (file.isEmpty()) {
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("上传文件失败");
            return resultDTO;
        }
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        if(uId == null || uId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        //获取文件输入流
        InputStream fileInsteam = file.getInputStream();
        //把文件的内容读进一个字节数据
        byte[] fileByte = new byte[(int) file.getSize()];
        fileInsteam.read(fileByte);

        //把文件的内容存到image表，如果已经存在，则更新图片内容，没有则新增一条记录
        Image image = new Image();
        image.setImageType("2");
        image.setUserId(Integer.valueOf(uId));
        image.setImageContent(fileByte);
        image.setIsValid("Y");
        if(imageService.getImage(image) != null){
            imageService.update(image);
        }else{
            imageService.insert(image);
        }


        return resultDTO;
    }

    /**
     * 学生证上传
     * @return
     */
    @RequestMapping("/common/idstudent/upload")
    public ResultDTO idStudentIpload(HttpServletRequest request, MultipartFile file) throws IOException {
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        if (file.isEmpty()) {
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("上传文件失败");
            return resultDTO;
        }
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        if(uId == null || uId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        //获取文件输入流
        InputStream fileInsteam = file.getInputStream();
        byte[] fileByte = new byte[(int) file.getSize()];
        //把文件的内容读进一个字节数据
        fileInsteam.read(fileByte);

        //把文件的内容存到image表，如果已经存在，则更新图片内容，没有则新增一条记录
        Image image = new Image();
        image.setImageType("3");
        image.setUserId(Integer.valueOf(uId));
        image.setImageContent(fileByte);
        image.setIsValid("Y");
        if(imageService.getImage(image) != null){
            imageService.update(image);
        }else{
            imageService.insert(image);
        }

        return resultDTO;
    }
}
