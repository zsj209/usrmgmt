/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016All Rights Reserved.
 */
package user;

import com.hrocloud.usrmgmt.api.RoleAgwService;
import com.hrocloud.usrmgmt.dto.PageDTO;
import com.hrocloud.usrmgmt.dto.RoleDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/*@ContextConfiguration(locations = { "/com/spring-mvc.xml",
        "/com/dubbo-demo-consumer.xml" })*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/user/spring-mybatis.xml"})
public class RoleTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private RoleAgwService roleService;
   
    @Test
    public void testAddOrModifyRole() {

    	boolean role = roleService.addOrModifyRole(2, "{\"roleName\":\"测试节点\",\"companyId\":100,\"type\":\"0root\",\"enabled\":1}");
    	System.out.println(role);
    }
    @Test
    public void testGetRoleList() {
    	/*
    	PageDTO list = roleService.getRoleList(null, 10, 1);
    	System.out.println(list);*/
    }
    @Test
    public void testDeleteRole() {
    	
    	boolean role = roleService.deleteRole("1009");
    	System.out.println(role);
    	
    }
    @Test
    public void testGetRoleById() {
    	
    	RoleDTO roleDTO = roleService.getRoleById(1010);
    	System.out.println(roleDTO);
    }
   
}
