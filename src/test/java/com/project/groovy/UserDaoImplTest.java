package com.project.groovy;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.groovy.dao.UserDao;
import com.project.groovy.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {

	@Autowired
	private UserDao userDao;
	
//	@Test
	public void test() throws Exception {
		User user = userDao.select("asdf");
		assertTrue(user != null);
		System.out.println(user.getNickname());
		
	}
	
//	@Test
	public void testInsert() throws Exception {
		userDao.deleteAll();
		
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
	}

//	@Test
	public void testUpdate() throws Exception {
		userDao.deleteAll();
		
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
		
		String id = userDao.selectAll().get(0).getId();
		System.out.println("id = " + id);
		user.setId(id);
		user.setNickname("档利");
		assertTrue(userDao.update(user) == 1);
		
		User user2 = userDao.select(id);
		assertTrue(user.equals(user2));
	}

//	@Test
	public void testDelete() throws Exception {
		userDao.deleteAll();
		
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
		
		String id = user.getId();
		assertTrue(userDao.delete(id) == 1);
	}

//	@Test
	public void testSelect() throws Exception {
		userDao.deleteAll();
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
		User user2 = userDao.select(user.getId());
		assertTrue(user.equals(user2));
	}

//	@Test
	public void testSelectAll() throws Exception {
		userDao.deleteAll();
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
		
		List<User> list = userDao.selectAll();
		list = userDao.selectAll();
		assertTrue(list.size() == 1);
		
		User user2 = new User("qwer", "1234", "全辨悼", "狼利2", "01011111111", "asdf@exam.com", "20101010");
		
		assertTrue(userDao.insert(user2) == 1);
		list = userDao.selectAll();
		assertTrue(list.size() == 2);
	}

//	@Test
	public void testDeleteAll() throws Exception {
		userDao.deleteAll();
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		User user2 = new User("qwer", "1234", "全辨悼", "狼利2", "01011111111", "asdf@exam.com", "20101010");
		
		assertTrue(userDao.insert(user) == 1);
		assertTrue(userDao.insert(user2) == 1);
		assertTrue(userDao.deleteAll() == 2);
	}
	
//	@Test
	public void testFindByNickname() throws Exception {
		userDao.deleteAll();
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
		User user2 = userDao.findByNickname(user.getNickname());
		assertTrue(user.equals(user2));
	}
	
	@Test
	public void testFindUserId() throws Exception {
		userDao.deleteAll();
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
		User user2 = userDao.findUserId(new User(user.getName(), user.getTel(), user.getEmail()));
		assertTrue(user.equals(user2));
	}
	
//	@Test
	public void testFindUserPw() throws Exception {
		userDao.deleteAll();
		User user = new User("asdf", "1234", "全辨悼", "狼利", "01011111111", "asdf@exam.com", "20101010");
		assertTrue(userDao.insert(user) == 1);
		User user2 = userDao.findUserId(new User(user.getId(), user.getName(), user.getTel(), user.getEmail()));
		assertTrue(user.equals(user2));
	}
}