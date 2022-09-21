package cn.cachalot.intelligentattendancesystem.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 存储当前线程信息
 */
@Slf4j
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setId(Long id) {
        log.info("存储当前ID:{}", id.toString());
        threadLocal.set(id);
    }

    public static Long getId() {
        log.info("放出当前ID:{}", threadLocal.get().toString());
        return threadLocal.get();
    }

    public static void removeId() {
        threadLocal.remove();
    }
}
