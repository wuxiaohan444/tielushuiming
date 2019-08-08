package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.UserBean;
import com.jiangkai.framework.admin.bean.UserInsertBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.config.UploadFileProperties;
import com.jiangkai.framework.admin.service.UserService;
import com.jiangkai.framework.admin.util.FileUtils;
import com.jiangkai.framework.source.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * User: zvbb
 * Date: 2019/5/10
 * Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final MultipartProperties properties;
    private final HttpServletRequest request;
    private final UploadFileProperties uploadFileProperties;

    @GetMapping("/page")
    public Result page(UserBean userBean) {
        return userService.page(userBean);
    }

    @PutMapping("/insert")
    public Result insert(UserInsertBean userInsertBean) {
        return userService.insert(userInsertBean, request);
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Long id) {
        return userService.select(id);
    }

    @GetMapping("/self")
    public Result self(HttpServletRequest request) {
        return userService.self(request);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return userService.delete(id, request);
    }

    @PostMapping("/update")
    public Result update(UserInsertBean userInsertBean, HttpServletRequest request) {
        return userService.update(userInsertBean, request);
    }

    @PostMapping("/updateSelf")
    public Result updateSelf(User user) {
        return userService.updateSelf(user, request);
    }

    /**
     * @param
     * @return 头像的网络位置
     * @Description 上传头像
     * @date 2019/6/11
     * @auther zvbb
     */
    @PostMapping("/uploadPortrait")
    public Result uploadPortrait(MultipartFile file) {
        String fileNetUrl = FileUtils.writeFile(file, properties, uploadFileProperties.getPrefix());
        return Result.success(fileNetUrl);
    }

    /**
     * @Description 控制页面的菜单
     */
    @GetMapping("/menuControl")
    public Result menuControl() {
        return userService.menuControl(request);
    }
}
