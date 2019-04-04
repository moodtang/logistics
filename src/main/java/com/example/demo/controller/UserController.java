package com.example.demo.controller;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by computer on 2019/1/8.
 */

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    /*
    * 测试
    * */
    @ApiOperation(value = "")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView HelloWorld() {
        return new ModelAndView("user/hello");
    }
    /*
    *  注册
    * */
    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HashMap addUser(UserEntity userEntity) {
        HashMap result = new HashMap();
        result.put("uid",userService.addUser(userEntity));
        return  result;
    }
    //登录
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public HashMap login(UserEntity user) {
        UserEntity userForBase=userService.findByUsername(user);
        HashMap tokenUser = new HashMap();
        if(userForBase==null){
            tokenUser.put("message", "登录失败,用户不存在");
            return tokenUser;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                tokenUser.put("message", "登录失败,密码错误");
                return tokenUser;
            }else {
                String token = tokenService.getToken(userForBase);
                tokenUser.put("token", token);
                tokenUser.put("user", userForBase);
                return tokenUser;
            }
        }
    }
    /*
     *删除用户
     * */
//    @UserLoginToken
    @ApiOperation(value = "")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HashMap deleteUser(@RequestParam("username")String username) {
        HashMap result = new HashMap();
        result.put("msg",userService.deleteUser(username));
        return result;
    }
    /*
     *获取用户列表
     * */
//    @UserLoginToken
    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public HashMap list() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<UserEntity> list = new ArrayList<>();
        list = userService.getUserList();
        result.put("userList",list);
        return result;
    }

    /*
    * 带token值测试
    * */
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public HashMap upload(@RequestParam("fileName") MultipartFile file,@RequestParam("uid") String uid){
//        头像上传
        return userService.uploadPicture(file,uid);
    }
    /*
    * 文件下载
    * */
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public String downloadFile(String imageName,HttpServletRequest request, HttpServletResponse response) {
       // String fileName = "test.jpg";// 设置文件名，根据业务需要替换成要下载的文件名
        if (imageName != null) {
            //设置文件路径
            String realPath = "C:\\Users\\computer\\Pictures\\Camera Roll";
   //         String realPath = "/home/images/pic";
            File file = new File(realPath , imageName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                   // System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
        /*
     *根据id获取用户个人信息
     * */
    //    @UserLoginToken
        @ApiOperation(value = "根据id获取用户个人信息")
        @RequestMapping(value = "/getInfoById", method = RequestMethod.GET)
        public HashMap getInfoById(@RequestParam String userId) {
            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("userInfo",userService.getInfoById(userId));
            return result;
        }
    /*
*根据用户名获取用户个人信息
* */
    //    @UserLoginToken
    @ApiOperation(value = "根据用户名获取用户个人信息")
    @RequestMapping(value = "/getInfoByUsername", method = RequestMethod.GET)
    public HashMap getInfoByUsername(@RequestParam String username) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("userInfo",userService.getInfoByUsername(username));
        return result;
    }
    //    @UserLoginToken
    @ApiOperation(value = "web端根据用户名查找用户")
    @RequestMapping(value = "/getUserInfoByUsername", method = RequestMethod.GET)
    public HashMap getUserInfoByUsername(@RequestParam String username) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("userInfo",userService.getUserInfoByUsername(username));
        return result;
    }

    /*根据用户名修改密码*/
    //    @UserLoginToken
    @ApiOperation(value = "根据用户名修改密码")
    @RequestMapping(value = "/changePasswordByUsername", method = RequestMethod.POST)
    public HashMap changePasswordByUsername(@RequestParam String username,String password) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("changePasswordByUsername",userService.changePasswordByUsername(username,password));
        return result;
    }
    /*
      *升级用户
      * */
//    @UserLoginToken
    @ApiOperation(value = "")
    @RequestMapping(value = "/upgrade", method = RequestMethod.POST)
    public HashMap upgradeUser(@RequestParam("userId")String userId) {
        HashMap result = new HashMap();
        result.put("msg",userService.upgradeUser(userId));
        return result;
    }
    /*
     *降级用户
     * */
//    @UserLoginToken
    @ApiOperation(value = "")
    @RequestMapping(value = "/downgrade", method = RequestMethod.POST)
    public HashMap downgradeUser(@RequestParam("userId")String userId) {
        HashMap result = new HashMap();
        result.put("msg",userService.downgradeUser(userId));
        return result;
    }
    /*
  *发单者remark和complain
  * */
    //    @UserLoginToken
    @ApiOperation(value = "")
    @RequestMapping(value = "/getFromUser", method = RequestMethod.GET)
    public HashMap getComplain() {
        HashMap result = new HashMap();
        result.put("msg",userService.getComplain());
        return result;
    }
    /*
*接单者remark和complain
* */
//    @UserLoginToken
    @ApiOperation(value = "")
    @RequestMapping(value = "/getToUser", method = RequestMethod.GET)
    public HashMap getComplain1() {
        HashMap result = new HashMap();
        result.put("msg",userService.getComplain1());
        return result;
    }
}
