package cn.cachalot.intelligentattendancesystem.common;

import cn.cachalot.intelligentattendancesystem.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 存储当前线程信息
 */
@Slf4j
public class BaseContext {
    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        log.info("存储当前User:{}", user.toString());
        threadLocal.set(user);
    }

    public static User getUser() {
        log.info("放出当前User:{}", threadLocal.get().toString());
        return threadLocal.get();
    }


}
