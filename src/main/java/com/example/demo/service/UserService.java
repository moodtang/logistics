package com.example.demo.service;

import com.example.demo.assist.FileUtils;
import com.example.demo.assist.GetUid;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by computer on 2019/1/9.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    /*注册用户*/
    @Transactional
    public String addUser(UserEntity userEntity){
        //      设置uid
        GetUid getUid =new GetUid();
        String uid = getUid.getUid();
        userEntity.setUserId(uid);
        if(userRepository.findUserEntityByUsername(userEntity.getUsername())!=null){
            return "the username have exited ";
        }
        if(userRepository.findUserEntityByUserPhone(userEntity.getUserPhone())!=null){
            return "the phone have exited ";
        }
        else{
            if(userEntity.getUsername() != null && userEntity.getPassword() != null && userEntity.getUserPhone() != null) {
                userRepository.save(userEntity);
                return uid;
            }
        }
            return "register fail";

    }
    /*登录*/
    public UserEntity login(String username){
       return userRepository.findUserEntityByUsername(username);

    }
    /*
    * 删除用户
    * */
    @Transactional
    public String deleteUser(String msg){
        if (userRepository.deleteUserEntityByUsername(msg)>0 || userRepository.deleteUserEntityByUserId(msg)>0)
            return "删除用户成功";
        else
            return "删除用户失败";
    }
    /*
  * 升级用户
  * */
    @Transactional
    public String upgradeUser(String id){
        if (userRepository.upgradeUser(id)>0 )
            return "升级用户成功";
        else
            return "升级用户失败";
    }
//    降级
    @Transactional
    public String downgradeUser(String id){
        if (userRepository.downgradeUser(id)>0 )
            return "降级用户成功";
        else
            return "降级用户失败";
    }
    /*通过用户名查找userEntity
    * */
    @Transactional
    public UserEntity findByUsername(UserEntity userEntity){
       return userRepository.findUserEntityByUsername(userEntity.getUsername());
    }
    /*
    * 获取用户列表
    * */
    @Transactional
    public List<UserEntity> getUserList(){
        return userRepository.getUserList();
    }
    /*
    * 头像上传*/
    public HashMap uploadPicture(MultipartFile file,String uid){
        HashMap resultMsg =new HashMap();
        // 要上传的目标文件存放路径
//        String localPath = "C:/Users/computer/Pictures/Camera Roll";
        String localPath = "/home/images/pic";
        // 上传成功或者失败的提示
    //    String type= file.getContentType();
        String  tmp = file.getOriginalFilename();
        //System.out.print("文件类型"+tmp);
        String filename = uid + "."+tmp.split("\\.")[1];
        System.out.print("文件"+filename);
//        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
        if (FileUtils.upload(file, localPath, filename)){
            // 上传成功，给出页面提示
            userRepository.saveHeadPic(uid,filename);
            resultMsg.put("msg", "upload success");

        }else {
            resultMsg.put("msg", "upload fail");

        }
        return resultMsg;
    }
    /*
    * 根据用户id获取用户信息
    * */
    public UserEntity getInfoById(String userId){
        return userRepository.getUserEntitiesByUserId(userId);
    }
    /*
  * 根据用户名获取用户信息
  * */
    public UserEntity getInfoByUsername(String username){
        return userRepository.getUserEntitiesByUsername(username);
    }
     /* web根据用户名获取用户信息
  * */
    public List getUserInfoByUsername(String username){
        return userRepository.getUserInfoByUsername(username);
    }
    /*
     * 根据用户名修改密码
     * */
    public String changePasswordByUsername(String username,String password) {
        if (password != null) {
            if (userRepository.changePasswordByUsername(username, password) > 0) {
                return "success change password";
            } else {
                return "fail change password";
            }
        } else {
                return "密码不能为空";
        }
    }
    /*获取下单者的remark和complain*/
    public List getComplain() {
        return userRepository.getComplain();
    }
    /*获取接单者的remark和complain*/
    public List getComplain1() {
        return userRepository.getComplain1();
    }
}
