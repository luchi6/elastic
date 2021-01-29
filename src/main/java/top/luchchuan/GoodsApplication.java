package top.luchchuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luch
 * @date 2021/1/27 20:59
 */

@SpringBootApplication
@MapperScan("top.luchchuan.mapper")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
        System.out.println("========程序启动=======");
    }
}
