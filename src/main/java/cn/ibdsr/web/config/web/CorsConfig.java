package cn.ibdsr.web.config.web;

import cn.ibdsr.web.core.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 跨域
 *
 * @author fengshuonan
 * @date 2016年11月14日 下午3:03:44
 */
@Configuration
public class CorsConfig {
    /**
     * 跨域问题
     */
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
