/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016All Rights Reserved.
 */
package user;

import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.usrmgmt.api.CaptchaService;
import com.hrocloud.usrmgmt.api.NodeAgwService;
import com.hrocloud.usrmgmt.api.UserAgwService;
import com.hrocloud.usrmgmt.api.UserService;
import com.hrocloud.usrmgmt.dto.Menu;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.UserDTO;
import com.hrocloud.usrmgmt.dto.ParamValueDTO;
import com.hrocloud.usrmgmt.dto.UserPermissionDTO;

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
public class UserTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private UserAgwService userAgwService;
    
    @Resource
    private UserService userService;
   
    @Test
    public void testUserPage() {
    	//PageDTO pageDTO = userAgwService.getUserList("{\"nameOrMobile\":\"tony\",\"status\":\"01draft\"}", 10, 1);
    	//System.out.println(pageDTO);
    }
    @Test
    public void testDeleteUser() {
    	boolean deleteUser = userAgwService.deleteUser("1010");
    	System.out.println(deleteUser);
    }
    @Test
    public void testModifyStatus() {
    	boolean deleteUser = userAgwService.modifyStatus("2", "03invalid");
    	System.out.println(deleteUser);
    }
    
    @Test
	public void testGetuserPermis() {
		// UserPermissionDTO usPobj = userService.getUserPerm(1007,"");
		UserPermissionDTO usPobj = userService.getUserPerm(1008, "");
		if (usPobj != null) {
			logger.info("------------------>not null");
		} else {
			logger.info("------------------>is null");
		}
	}

	@Test
	public void testGetuserPermisByAgw() {
		UserPermissionDTO usPobj = userAgwService.getUserPerm(1007, "0root");
		if (usPobj != null) {
			logger.info("------------------>not null");
		} else {
			logger.info("------------------>is null");
		}
	}
	
    @Test
    public void testUpdateFianlRoleTpye() {
    	userAgwService.updateFianlRoleTpye(2, "0root");
    }
    
    @Test
	public void testGetuserMenu() {
		List<Menu> nodelist = userService.getUserMenu(1008, "0root");
		if (nodelist != null) {
			logger.info("------------------>not null");
		} else {
			logger.info("------------------>is null");
		}
	}

	@Test
	public void testGetuserMenuByAgw() {
		// List<Menu> nodelist = userAgwService.getUserMenu(1008, "0root");
		List<Menu> nodelist = userAgwService.getUserMenu(1042, "0root");
		if (nodelist != null) {
			logger.info("------------------>not null");
		} else {
			logger.info("------------------>is null");
		}
	}
}
