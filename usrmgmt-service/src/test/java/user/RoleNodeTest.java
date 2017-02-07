/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016All Rights Reserved.
 */
package user;

import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.usrmgmt.api.CaptchaService;
import com.hrocloud.usrmgmt.api.NodeAgwService;
import com.hrocloud.usrmgmt.api.RoleNodeAgwService;
import com.hrocloud.usrmgmt.api.UserAgwService;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.NodeTreeDTO;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.UserDTO;
import com.hrocloud.usrmgmt.dto.ParamValueDTO;

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
public class RoleNodeTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private RoleNodeAgwService rnas;
 
    @Test
    public void testGetNodeTree() {
    	
    	rnas.addOrModifyRoleNode(103, 103, "100,101");
    }
}
