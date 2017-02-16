/**
 * hrocloud.com Inc.
 * Copyright (c) 2015-2016All Rights Reserved.
 */
package user;

import com.hrocloud.common.api.model.CaptchaTemplate;
import com.hrocloud.usrmgmt.api.CaptchaService;
import com.hrocloud.usrmgmt.api.NodeAgwService;
import com.hrocloud.usrmgmt.api.NodeService;
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

import java.util.ArrayList;
import java.util.List;

/*@ContextConfiguration(locations = { "/com/spring-mvc.xml",
        "/com/dubbo-demo-consumer.xml" })*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/java/user/spring-mybatis.xml"})
public class NodeTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private NodeAgwService nodeService;
    
    @Resource
    private NodeService ns;
   
    @Test
    public void testNodePage() {

    	PageDTO pageDTO = nodeService.nodePage("管理", "", "N", 10, 1);
    	System.out.println(pageDTO);
    }
    @Test
    public void testDeleteNode() {
    	
    	boolean node = nodeService.deleteNode("1094");
    	System.out.println(node);
    }
    @Test
    public void testMenu() {
    	
    	List<Menu> menu = nodeService.getMenu(101);
    	System.out.println(menu);
    }
    @Test
    public void testGetNodeTree() {
    	
    	List<NodeTreeDTO> tree = nodeService.getNodeTree(100);
    	System.out.println(tree);
    }
    @Test
    public void testNodeService() {
    	
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	arr.add(100);
    	arr.add(101);
    	arr.add(104);
    	List<Menu> menuByRoleIds = ns.getMenuByRoleIds(arr);
    	
    	System.out.println(menuByRoleIds);
    	
    }
}
