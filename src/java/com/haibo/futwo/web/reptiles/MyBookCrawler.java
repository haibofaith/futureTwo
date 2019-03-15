package com.haibo.futwo.web.reptiles;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.haibo.futwo.web.mappers.BookMapper;
import com.haibo.futwo.web.model.FileProperties;
import com.haibo.futwo.web.model.WeixinBook;
import org.apache.http.util.TextUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author:haibo.xiong
 * @date:2019/3/6
 * @description: url=https://www.biqutxt.com/70_70085/
 * urlRegex=https://www.biqutxt.com/70_70085/[0-9]+.html
 * https://www.biqutxt.com/70_70085/([0-9]+.html
 * filePath=/tmp/file/
 * fileName=黄金瞳
 */
public class MyBookCrawler extends BreadthCrawler {
    private BookMapper bookMapper;
    private FileProperties fileProperties;

    private Logger logger = LoggerFactory.getLogger(MyBookCrawler.class);

    final String url = "https://www.biqutxt.com/70_70085/";
    final String urlRegex = "https://www.biqutxt.com/70_70085/[0-9]+.html";

    /**
     * @param crawlPath crawlPath is the path of the directory which maintains
     *                  information of this crawler
     * @param autoParse if autoParse is true,BreadthCrawler will auto extract
     *                  links which match regex rules from pag
     */
    public MyBookCrawler(String crawlPath, boolean autoParse,BookMapper bookMapper) {
        super(crawlPath, autoParse);
        this.bookMapper = bookMapper;
        /*start pages*/
        this.addSeed(url);
        /* fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml */
        this.addRegex(urlRegex);
        /* do not fetch jpg|png|gif */
        this.addRegex("-.*\\.(jpg|png|gif).*");
        setThreads(50);
        getConf().setTopN(100);
    }

    public MyBookCrawler(String crawlPath, boolean autoParse,BookMapper bookMapper, FileProperties fileProperties) {
        this(crawlPath, autoParse,bookMapper);
        this.fileProperties = fileProperties;
    }

    public static void main(String[] args) throws Exception {
//        MyBookCrawler crawler = new MyBookCrawler("crawl", true);
//        crawler.start(4);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
    /* if page is news page */
        if (page.matchUrl(urlRegex)) {
        /* we use jsoup to parse page */
            Document doc = page.doc();
            String pageUrl = page.url();
            String str = "https://www.biqutxt.com/70_70085/([0-9]+).html";
            Pattern pattern =Pattern.compile(str);
            Matcher matcher = pattern.matcher(pageUrl);

//        String[] arr = str0.split(str);
            if (matcher.find())
                System.out.println(matcher.group(1));
            Long bookId = Long.parseLong(matcher.group(1));
        /* extract title and content of news by css selector */
            String title = page.select("div[class=bookname]>h1").first().text();
            String content = page.select("div[id=content]").first().text();

            //1、创建文件夹
            //1.1、指定文件上传路径下，当天日期创建文件夹
//            Date date = new Date();
//            SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
            String fileStr = fileProperties.getBookpath();
            File timefile = new File(fileStr);
            if (!timefile.exists()){
                timefile.mkdir();
            }

            //1.2、在日期文件夹下创建文件（根据原始文件名命名）
            String realPathStr = fileStr +title;
            File convertFile = new File(realPathStr);
            FileWriter writer;
            try {
                convertFile.createNewFile();
                writer = new FileWriter(realPathStr);
                writer.write(content);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.debug(title);
            if (!TextUtils.isEmpty(title))
            bookMapper.insertBook(new WeixinBook(title,bookId,realPathStr));
        }
    }

    /***
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /***
     * 删除文件夹
     *
     * @param
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
