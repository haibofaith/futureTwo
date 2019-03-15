package com.haibo.futwo.web.ctrl;

import com.haibo.futwo.web.entity.BaseResponse;
import com.haibo.futwo.web.model.Person;
import com.haibo.futwo.web.model.WeixinBook;
import com.haibo.futwo.web.reptiles.MyBookCrawler;
import com.haibo.futwo.web.service.BookService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/3/14
 * @description:
 */
@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;
    /**
     * 书籍目录抓取
     * @param request
     * @return
     */
    @RequestMapping(value = "/directories")
    @ResponseBody
    public String directories(HttpServletRequest request){
        bookService.insertBooks();
        BaseResponse response = new BaseResponse();
        return response.toString();
    }

    /**
     * 书籍目录分页获取
     * @param request
     * @return
     */
    @RequestMapping(value = "/directory")
    @ResponseBody
    public String directory(HttpServletRequest request){
//        bookService.insertBooks();
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");

        int pageNo = 1;
        if (!TextUtils.isEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }
        int pageSize = 10;
        if (!TextUtils.isEmpty(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        List<WeixinBook> weixinBooks = bookService.selectBookByPage(pageNo,pageSize);
        BaseResponse response = new BaseResponse();
        response.setBody(weixinBooks);
        return response.toString();
    }
}
