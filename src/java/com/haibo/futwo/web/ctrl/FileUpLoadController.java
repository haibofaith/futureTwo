package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.constant.WeixinConstant;
import com.haibo.futwo.web.model.*;
import com.haibo.futwo.web.service.FileService;
import com.haibo.futwo.web.service.RelationService;
import com.haibo.futwo.web.service.UserService;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/2/13
 * @description:
 */
@Controller
@RequestMapping(value = "/b")
public class FileUpLoadController {
    private static Logger log = LoggerFactory.getLogger(FileUpLoadController.class);
    @RequestMapping(value="/upload",method= RequestMethod.GET)
    public ModelAndView fileUpload() {
        return new ModelAndView("freemarker/upload");
    }
    @Autowired
    private FileProperties fileProperties;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    /**
     * 上传接口
     * @param file
     * @return
     */
    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
    @ResponseBody
    public String doUpload(@RequestParam("file")MultipartFile file,HttpServletRequest request) {
//        String uuid = request.getParameter("uuid");
//        String openid = userService.getOpenIdByUuid(uuid);
        WeixinSession weixinSession = (WeixinSession) request.getAttribute(WeixinConstant.weixinUser.toString());
        String openid = weixinSession.getOpenid();
        //1、创建文件夹
        //1.1、指定文件上传路径下，当天日期创建文件夹
        Date date = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String fileStr = fileProperties.getUploadpath()+dateFormat.format(date);
        String smallFileStr = fileProperties.getUploadpath()+dateFormat.format(date)+"small";
        File timefile = new File(fileStr);
        if (!timefile.exists()){
            timefile.mkdir();
        }
        File smallTimefile = new File(smallFileStr);
        if (!smallTimefile.exists()){
            smallTimefile.mkdir();
        }

        //1.2、在日期文件夹下创建文件（根据原始文件名命名）
        String realPathStr = fileStr +"/"+file.getOriginalFilename();
        String smallRealPathStr =  smallFileStr+"/"+"small_"+file.getOriginalFilename();
        File convertFile = new File(realPathStr);
        WeixinImg weixinImg = new WeixinImg();
        int i = 0;
        try {
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
            Thumbnails.of(file.getInputStream()).scale(0.382f).outputQuality(0.382f).toFile(smallRealPathStr);
            //1.3、数据库插入记录
            weixinImg.setImgDate(date);
            weixinImg.setOpen_id(openid);
            weixinImg.setImgName(file.getOriginalFilename());
            weixinImg.setImgPath(fileStr+"/");
            weixinImg.setImgFile(dateFormat.format(date));
            weixinImg.setImgUrl("https://haibofaith.xyz/futureTwo/b/downloadPic/"+dateFormat.format(date)+"/"+file.getOriginalFilename());
            weixinImg.setSmallImgName("small_"+file.getOriginalFilename());
            weixinImg.setSmallImgPath(smallFileStr+"/");
            weixinImg.setSmallImgFile(dateFormat.format(date)+"small");
            weixinImg.setSmallImgUrl("https://haibofaith.xyz/futureTwo/b/downloadPic/"+dateFormat.format(date)+"small"+"/"+"small_"+file.getOriginalFilename());
            i = fileService.uploadImg(weixinImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseResponse response = new BaseResponse();
        if (i>0){
            response.setRetcode(0);
            response.setRetdesc("上传成功");
        }else {
            response.setRetcode(-99);
            response.setRetdesc("上传失败");
        }
        return response.toString();
    }
    Logger logger = LoggerFactory.getLogger(FileUpLoadController.class);
    /**
     * 获取图片父路径接口
     * @return
     */
    @RequestMapping(value="/picParentPathList",method=RequestMethod.POST)
    @ResponseBody
    public String picParentPathList(HttpServletRequest request){
//        String uuid = request.getParameter("uuid");
//        String openid = userService.getOpenIdByUuid(uuid);
        WeixinSession weixinSession = (WeixinSession) request.getAttribute(WeixinConstant.weixinUser.toString());
        String openid = weixinSession.getOpenid();
        List<String> list = fileService.selectImgParentDistinct(openid);
        BaseResponse response = new BaseResponse();
        response.setBody(list);
        return response.toString();
    }

    @Autowired
    private RelationService relationService;

    /**
     * 获取朋友图片父路径接口
     * @return
     */
    @RequestMapping(value="/friendPicParentPathList",method=RequestMethod.POST)
    @ResponseBody
    public String friendPicParentPathList(HttpServletRequest request){
//        String uuid = request.getParameter("uuid");
//        String openid = userService.getOpenIdByUuid(uuid);
        WeixinSession weixinSession = (WeixinSession) request.getAttribute(WeixinConstant.weixinUser.toString());
        String openid = weixinSession.getOpenid();
        //通过自己的openid获得自己的关系-》获得朋友的openid
        List<WeixinRelation> weixinRelations = relationService.getRelationInfoByOpenid(openid);
        FriendsPicParentList friendsPicParentList = new FriendsPicParentList();
        List<FriendPicParent> friendPicParents = new ArrayList<>();
        for (WeixinRelation weixinRelation:weixinRelations){
            //通过朋友openid获得朋友的图片父路径
            List<String> list = fileService.selectImgParentDistinct(weixinRelation.getOpenid());
            FriendPicParent friendPicParent = new FriendPicParent();
            //朋友的所有图片父路径
            friendPicParent.setPicParentPaths(list);
            //朋友的昵称
            friendPicParent.setNickName(weixinRelation.getNickName());
            friendPicParent.setOpenid(weixinRelation.getOpenid());
            friendPicParents.add(friendPicParent);
        }
        //塞入返回对象
        friendsPicParentList.setFriendPicParents(friendPicParents);
        BaseResponse response = new BaseResponse();
        response.setBody(friendsPicParentList);
        return response.toString();
    }


    /**
     * 从数据库获取朋友图片路径
     * @param picDate
     * @return
     */
    @RequestMapping(value="/friendPicUrlList/{picDate}",method=RequestMethod.POST)
    @ResponseBody
    public String friendPicUrlList(@PathVariable("picDate") String picDate,String uuid,String friendOpenid){
        List<WeixinImg> list = fileService.selectFriendImgByImgFile(picDate,uuid,friendOpenid);
        BaseResponse response = new BaseResponse();
        response.setBody(list);
        return response.toString();
    }


    /**
     * 从数据库获取图片路径
     * @param picDate
     * @return
     */
    @RequestMapping(value="/picUrlList/{picDate}",method=RequestMethod.POST)
    @ResponseBody
    public String picUrlList(@PathVariable("picDate") String picDate,String uuid){
        List<WeixinImg> list = fileService.selectImgByImgFile(picDate,uuid);
        BaseResponse response = new BaseResponse();
        response.setBody(list);
        return response.toString();
    }


    @RequestMapping(value="/list/{picDate}",method=RequestMethod.POST)
    @ResponseBody
    public String list(@PathVariable("picDate") String picDate){
        String pathStr = fileProperties.getUploadpath()+picDate;
        File file = new File(fileProperties.getUploadpath()+picDate);
        List<String> fileLists = new ArrayList<>();
        if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(pathStr+"/"+ filelist[i]);
                if (!readfile.isDirectory()) {
                    fileLists.add("https://haibofaith.xyz/futureTwo/b/downloadPic/"+picDate+"/"+readfile.getName());
                }
            }
        }
        BaseResponse response = new BaseResponse();
        response.setBody(fileLists);
        return response.toString();
    }



    /**
     * 处理图片下载请求
     * @param fileName
     * @param response
     */
    @RequestMapping("/downloadPic/{picDate}/{fileName}.{suffix}")
    public void downloadPicture(@PathVariable("picDate") String picDate,
                                @PathVariable("fileName") String fileName,
                                @PathVariable("suffix") String suffix,
                                HttpServletResponse response){
        // 设置下载的响应头信息
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + fileName + suffix);
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        File imgFile = new File(fileProperties.getUploadpath()+picDate+"/" + fileName + "." + suffix);
        responseFile(response, imgFile);
    }

    /**
     * 响应输出图片文件
     * @param response
     * @param imgFile
     */
    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }



}
