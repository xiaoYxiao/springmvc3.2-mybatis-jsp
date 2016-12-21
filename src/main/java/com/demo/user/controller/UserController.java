package com.demo.user.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.TableConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.demo.common.page.context.ExtremeContext;
import com.demo.user.model.User;
import com.demo.user.service.AddressService;
import com.demo.user.service.UserService;

@Controller
@RequestMapping("demo/user/")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private AddressService addressService;

    @RequestMapping("listUser")
    public String listUser(HttpServletRequest request,  @ModelAttribute User user) {
        //判断是否导出功能，是导出查询所有数据，不分页
        String flag=null;
        Object obj=request.getParameterMap().get("ec_ev");
        if(obj instanceof String[]){  
            String[] param = (String [])obj;  
            flag=param[0];  
        }  
        if(StringUtils.isBlank(flag)||(!"xls".equals(flag)&&!"csv".equals(flag)&&!"pdf".equals(flag))){
            user.setCurrentPage((ExtremeContext.getCurrentPage(request) - 1) * ExtremeContext.getPageSize(request));
            user.setPageSize(ExtremeContext.getPageSize(request));
        }
        List<User> users = userService.getAllUser(user);
        System.out.println("user count : " + users.size());
        request.setAttribute("users", users);
        //另外,JSP的中,eXtremeTable的table标签要retrieveRowsCallback属性,将该属性设为 org.extremecomponents.table.callback.LimitCallback,
       //这是它默认的分页回调类,你也可以写自已的回调类.设置了该默认的回调类以后,你在Action里就需要向request加入totalRows属性,值为你的查询结果总记录数,这样才会产生翻页的效果.
        request.setAttribute(TableConstants.TOTAL_ROWS, new Integer(userService.getAllUserCount(user)));
        return "listUser";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public String addUser() {
        System.out.println("addUser method was invoked...");
        return "addUser";
    }

    @RequestMapping(value = "addUserFirstStep", method = RequestMethod.POST)
    public String addUserFirstStep(HttpServletRequest req) {
        // System.out.println(req.getParameter("user").getClass().getName()+"~~~~~~~~~~~");
        // System.out.println(user.getName()+"======"+user.getPasswd());
        User user = initUser(req);
        req.getSession().setAttribute("user", user);
        // System.out.println(user.getName()+"---"+user.getPhone());
        return "addAddress";
    }

    @RequestMapping(value = "toUpdateUser", method = RequestMethod.GET)
    public String toUpdateUser(@RequestParam Long uid, HttpServletRequest req) {
        User user = userService.getOneUser(uid);
        req.setAttribute("user", user);
        return "updateUser";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest req) {
        User user = initUser(req);
        userService.updateUser(user);
        return "redirect:listUser.do";
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam Long uid) {
        userService.deleteUser(uid);
        return "redirect:listUser.do";// 重定向到listUser
    }

    /**
     * 获得Ajax异步请求，并将请求数据以JSON格式响应。
     * 
     * @param name
     *            Reuqest中的name参数。
     * @param age
     *            Reuqest中的age参数。
     * @param phone
     *            Reuqest中的phone参数。
     * @return List<User> 返回的包含User对象的集合对象在标注的作用下生成JSON字符串响应。
     */
    @RequestMapping(value = "ajaxGetUser", method = RequestMethod.GET)
    @ResponseBody  
    public List<User> ajaxGetUser(@RequestParam String name, @RequestParam Integer age, @RequestParam String phone) {
        User user = new User();
        if (name != null && name.length() > 0) {
            user.setName(name);
        }
        if (age != null) {
           user.setAge(age);
        }
        if (phone != null && phone.length() > 0) {
            user.setPhone(phone);
        }
        List<User> users = userService.getUserNeeded(user);
        return users;
    }

    // 由于在applicationContext-mvc.xml中配置了SimpleMappingExceptionResolver，则此处不再起作用。
    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler() {
        System.out.println("The UserController throws an Exception...");
        return "errorPage";
    }

    private User initUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String age = req.getParameter("age");
        String passwd = req.getParameter("passwd");
        System.out.println(name + "---" + phone + "---" + age + "---" + passwd);
        User user = new User(name, Integer.parseInt(age), phone, passwd);
        String uid = req.getParameter("uid");
        if (uid != null) {
            user.setUid(Long.parseLong(uid));
        }
        return user;
    }

}
