package cn.cachalot.intelligentattendancesystem;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDateTime;

public class testtest {
    @Test
    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.of(2019, 1, 1, 1, 1, 1);
        Date date = Date.valueOf("2019-01-11");
        System.out.println(date.toString());

    }
}
