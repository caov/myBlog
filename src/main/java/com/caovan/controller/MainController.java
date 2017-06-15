package com.caovan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.caovan.domain.BlogBean;
import com.caovan.domain.BlogCategoryBean;
import com.caovan.domain.ShareBean;
import com.caovan.domain.ShareCategoryBean;
import com.caovan.domain.UserBean;
import com.caovan.service.BlogService;
import com.caovan.service.ShareService;
import com.caovan.service.UserService;
import com.caovan.utils.CommonUtils;
import com.caovan.utils.JsonUtil;

@Controller
public class MainController {
    
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private ShareService shareService;
    
    @RequestMapping("/")  
    public String index(){  
        return "index";  
    }  
    
    @RequestMapping("/blog")  
    public String blog(Integer id){
        return "blog";
    } 
    
    @RequestMapping("/share")  
    public String share(){  
        return "share";  
    } 
    
    @RequestMapping("/resume")  
    public String resume(){  
        return "resume";  
    } 
    
    @RequestMapping("/doBlog")  
    public ModelAndView doBlog(Integer id){ 
        ModelAndView mav = new ModelAndView("doBlog");
        BlogBean blogBean = null;
        if(id != null){
            blogBean = blogService.getBlogByid(id);
        }
        mav.addObject("blogBean", blogBean);
        return mav;  
    } 
    
    @RequestMapping("/doShare")  
    public String doShare(){  
        return "doShare";  
    } 
    
    /**
    * @MethodName:  login
    * @Description: 登录
    * @author Van
    * @date 2017年6月13日 上午8:46:08
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        UserBean userBean = userService.getUsersByName(username);
        if(userBean != null){
            if(userBean.getPassword().equals(CommonUtils.getMD5(password))){
                Cookie cookie = new Cookie("name", userBean.getName());
                cookie.setPath("/");
                cookie.setMaxAge(604800);
                response.addCookie(cookie);
                return "doBlog";
            }
        }
        return "index";
    }
    
    /**
    * @MethodName:  addBlog
    * @Description: 添加博客
    * @author Van
    * @date 2017年6月13日 上午8:46:59
     */
    @RequestMapping("addBlog")
    @ResponseBody
    public void addBlog(@RequestParam Integer id,@RequestParam String name,@RequestParam String blogContent,
            @RequestParam int categoryId){
        try{
            long blogId;
            if(id != null){
                BlogBean blogBean = blogService.getBlogByid(id);
                blogBean.setCategoryId(categoryId);
                blogBean.setContent(blogContent);
                blogBean.setName(name);
                blogBean.setDatetime(new Date());
                BlogBean blog = blogService.addBlog(blogBean);
                blogId = blog.getId();
            }else{
                BlogBean blogBean = new BlogBean();
                blogBean.setCategoryId(categoryId);
                blogBean.setContent(blogContent);
                blogBean.setName(name);
                blogBean.setDatetime(new Date());
                BlogBean blog = blogService.addBlog(blogBean);
                blogId = blog.getId();
                
                //添加博客的同时，将分类表num数量加1
                BlogCategoryBean blogCategoryBean = blogService.getBlogCategoryByid(categoryId);
                int num = blogCategoryBean.getNum();
                num = num + 1;
                blogCategoryBean.setNum(num);
                blogService.updateBlogCategory(blogCategoryBean);
            }
           
            output(blogId);
        }catch(Exception e){
            output(false);
        }
        
    }
    
    /**
    * @MethodName:  addShare
    * @Description: 添加分享
    * @author Van
    * @date 2017年6月14日 下午5:11:22
     */
    @RequestMapping("addShare")
    @ResponseBody
    public void addShare(@RequestParam String name,@RequestParam String content,
            @RequestParam int categoryId){
        try{
            ShareBean shareBean = new ShareBean();
            shareBean.setCategoryId(categoryId);
            shareBean.setContent(content);
            shareBean.setName(name);
            shareBean.setDatetime(new Date());
            shareService.addShare(shareBean);
            
            //添加分享的同时，将分类表分类num数量加1
            ShareCategoryBean shareCategoryBean = shareService.getShareCategoryByid(categoryId);
            int num = shareCategoryBean.getNum();
            num = num + 1;
            shareCategoryBean.setNum(num);
            shareService.updateShareCategory(shareCategoryBean);
            output(true);
        }catch(Exception e){
            output(false);
        }
        
    }
    
    /**
    * @MethodName:  listCategory
    * @Description: 博客分类列表
    * @author Van
    * @date 2017年6月13日 上午8:49:57
     */
    @RequestMapping("listCategory")
    @ResponseBody
    public void listCategory(){
        List<BlogCategoryBean> list = blogService.listCategory();
        output(JsonUtil.toJson(list));
    }
    
    /**
     * @MethodName:  blogDetails
     * @Description: 跳转到具体博客内容页面
     * @author Van
     * @date 2017年6月13日 上午11:39:04
      */
     @RequestMapping("blogDetails")
     public ModelAndView blogDetails(@RequestParam long id){
         BlogBean blogBean = blogService.getBlogByid(id);
         ModelAndView mav = new ModelAndView("blogDetails");
         mav.addObject("blog", blogBean);
         mav.addObject("category", blogService.getBlogCategoryByid(blogBean.getCategoryId()).getName());
         return mav;
     }
     
     /**
     * @MethodName:  listBlog
     * @Description: 博客列表
     * @author Van
     * @date 2017年6月14日 上午10:47:27
      */
     @RequestMapping("/listBlog")
     @ResponseBody
     public void listBlog(Long categoryId){
         List<BlogBean> list = new ArrayList<>();
         if (categoryId != null) {
             list = blogService.listBlogByCId(categoryId);
        }else{
            list = blogService.listBlog();
        }
         List<Object> li = new ArrayList<>();
         for(BlogBean blogBean : list){
             Map<String, Object> map = new HashMap<>();
             map.put("name", blogBean.getName());
             //map.put("content", blogBean.getContent());
             //map.put("datetime", blogBean.getDatetime());
             map.put("id", blogBean.getId());
             map.put("categoryId", blogBean.getCategoryId());
             map.put("category", blogService.getBlogCategoryByid(blogBean.getCategoryId()).getName());
             map.put("categoryColor", blogService.getBlogCategoryByid(blogBean.getCategoryId()).getColor());
             li.add(map);
         }
         output(JsonUtil.toJson(li));
     }
     
     /**
     * @MethodName:  listShare
     * @Description: 分享列表
     * @author Van
     * @date 2017年6月14日 上午10:48:18
      */
     @RequestMapping("listShare")
     @ResponseBody
     public void listShare(Long categoryId){
         List<ShareBean> list = new ArrayList<>();
         if (categoryId != null) {
             list = shareService.listShareByCId(categoryId);
        }else{
            list = shareService.listShare();
        }
         List<Object> li = new ArrayList<>();
         for(ShareBean shareBean : list){
             Map<String, Object> map = new HashMap<>();
             map.put("name", shareBean.getName());
             map.put("content", shareBean.getContent());
             map.put("datetime", shareBean.getDatetime());
             map.put("id", shareBean.getId());
             map.put("categoryId", shareBean.getCategoryId());
             map.put("category", shareService.getShareCategoryByid(shareBean.getCategoryId()).getName());
             li.add(map);
         }
         output(JsonUtil.toJson(li));
     }
     
     /**
      * @MethodName:  listShareCategory
      * @Description: 分享分类列表
      * @author Van
      * @date 2017年6月14日 上午10:48:18
       */
      @RequestMapping("listShareCategory")
      @ResponseBody
      public void listShareCategory(){
          List<ShareCategoryBean> list = shareService.listShareCategory();
          output(JsonUtil.toJson(list));
      }
      
    /**
    * @MethodName:  output
    * @Description: response响应
    * @author Van
    * @date 2017年6月13日 上午8:46:37
     */
    protected void output(Object object) {
        String outString = "";
        if (object == null) {
            outString = "";
        } else if (object instanceof Integer) {
            outString = object.toString();
        } else if (object instanceof String) {
            outString = object.toString();
        } else {
            outString = JsonUtil.toJson(object);
            if (outString == null) {
                outString = object.toString();
            }
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.print(outString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
