/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016All Rights Reserved.
 */
package user;

import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.usrmgmt.api.ButtonAgwService;
import com.hrocloud.usrmgmt.api.CaptchaService;
import com.hrocloud.usrmgmt.api.NodeAgwService;
import com.hrocloud.usrmgmt.api.UserAgwService;
import com.hrocloud.usrmgmt.dto.ButtonDTO;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.UserDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/*@ContextConfiguration(locations = { "/com/spring-mvc.xml",
        "/com/dubbo-demo-consumer.xml" })*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/user/spring-mybatis.xml"})
public class ButtonTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private ButtonAgwService buttonService;
   
    @Test
    public void testButtonPage() {

    	PageDTO pageDTO = buttonService.buttonPage("测试", 10, 1);
    	System.out.println(pageDTO);
    }
    @Test
    public void testGetButtonById() {
    	
    	ButtonDTO buttonById = buttonService.getButtonById(3);
    	System.out.println(buttonById);
    }
   
    @Test
    public void testGetButtonByNodeId() {
     List<ButtonDTO> list = buttonService.getButtonByNodeId(100,"136");
     System.out.println(list);
    }
    
}
