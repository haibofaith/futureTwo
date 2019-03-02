package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.model.BaseResponse;
import com.haibo.futwo.web.model.FileProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
    @ResponseBody
    public String doUpload(@RequestParam("file")MultipartFile file) throws IOException {
        //1、创建文件夹
        Date date = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
        String fileStr = fileProperties.getUploadpath()+dateFormat.format(date);
        File timefile = new File(fileStr);
        if (!timefile.exists()){
            timefile.mkdir();
        }
        //2、创建文件
        String realPathStr = fileProperties.getUploadpath()+dateFormat.format(date)+"/"+file.getOriginalFilename();
        File convertFile = new File(realPathStr);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        BaseResponse response = new BaseResponse();
        response.setBody(realPathStr);
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
