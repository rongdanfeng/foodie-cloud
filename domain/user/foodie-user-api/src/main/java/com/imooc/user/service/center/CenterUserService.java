package com.imooc.user.service.center;

import com.imooc.user.pojo.Users;
import com.imooc.user.pojo.bo.center.CenterUserBO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("center-user-api")
public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("profile")
    public Users queryUserInfo(@RequestParam("userId")String userId);

    /**
     * 修改用户信息
     * @param userId
     * @return
     */
    @PutMapping("profile/{userId}")
    Users updateUserInfo(@RequestParam("userId")String userId,
                         @RequestBody CenterUserBO centerUserBO);

    @PostMapping("updatePhoto")
    Users updateUserFace(@RequestParam("userId")String userId,
                         @RequestParam("userId")String finalUserFaceUrl);
}
