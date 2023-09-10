package com.tiep.borrowingservice.config;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream(); // Xstream dùng để hỗ trợ chuyển đổi qua lại giữa đối tượng java <=> XML
        xStream.allowTypesByWildcard(new String[] {
                "com.tiep.**"
        });
        return xStream;
    }
}
