package com.project.groovy;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.groovy.dao.CommentDao;
import com.project.groovy.model.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentDaoImplTest {
	
	@Autowired
	CommentDao commentDao;
	
	int bno = 370;
	
	@Before
	public void before() throws Exception {
		commentDao.deleteAll(bno);
		System.out.println("before() ����");
	}
	
//	@Test
	public void count() throws Exception {
		assertTrue(commentDao.count(1) == 0);
	}
	
//	@Test
	public void delete() throws Exception {
		// int bno, String comment, String commenter, String commenter_nickname, 
		// Integer pcno, int re_step, int re_level
		Comment comment = new Comment(bno, "�ȳ��ϼ���", "asdf", "����", 0, 369, 0, 0);
		assertTrue(commentDao.insert(comment) == 1);
		assertTrue(commentDao.count(369) == 1);
		comment.setCno(commentDao.selectAll(369).get(0).getCno());
		
		assertTrue(commentDao.isdel(comment.getCno(), "asdf") == 1);
	}
	
//	@Test
	public void insert() throws Exception {
		Comment comment = new Comment(bno, "�ȳ��ϼ���", "asdf", "����", 0, 369, 0, 0);
		assertTrue(commentDao.insert(comment) == 1);
		assertTrue(commentDao.count(369) == 1);
		
		List<Comment> list = commentDao.selectAll(369);
		assertTrue(list.size() == 1);
		
		comment = new Comment(bno, "�ȳ��ϼ���", "asdf", "����", 0, 369, 0, 0);
		assertTrue(commentDao.insert(comment) == 1);
		assertTrue(commentDao.count(369) == 2);
		list = commentDao.selectAll(369);
		assertTrue(list.size() == 2);
	}
	
//	@Test
	public void select() throws Exception {
		Comment comment = new Comment(bno, "�ȳ��ϼ���", "asdf", "����", 0, 369, 0, 0);
		assertTrue(commentDao.insert(comment) == 1);
		assertTrue(commentDao.count(369) == 1);
		
		List<Comment> list = commentDao.selectAll(369);
		String commentStr = list.get(0).getComment();
		String commenter = list.get(0).getCommenter();
		assertTrue(commentStr.equals(comment.getComment()));
		assertTrue(commenter.equals(comment.getCommenter()));
	}
	
//	@Test
	public void update() throws Exception {
		Comment comment = new Comment(bno, "�ȳ��ϼ���", "asdf", "����", 0, 369, 0, 0);
		assertTrue(commentDao.insert(comment) == 1);
		assertTrue(commentDao.count(369) == 1);
		
		List<Comment> list = commentDao.selectAll(369);
		comment.setCno(list.get(0).getCno());
		comment.setComment("�ȳ��ϼ���2");
		assertTrue(commentDao.update(comment) == 1);
		list = commentDao.selectAll(369);
		
		String commentStr = list.get(0).getComment();
		String commenter = list.get(0).getCommenter();
		assertTrue(commentStr.equals(comment.getComment()));
		assertTrue(commenter.equals(comment.getCommenter()));
	}
	
//	@Test
	public void maxStep() throws Exception {
		Comment comment = new Comment(bno, "�ȳ��ϼ���", "asdf", "����", 0, 43, 0, 0);
		assertTrue(commentDao.insert(comment) == 1);
	}
	
	@Test
	public void lastIndex() throws Exception {
		Comment comment = new Comment(bno, "�ȳ��ϼ���", "asdf", "����", 0, 0, 0, 0);
		assertTrue(commentDao.insert(comment) == 1);
		
		comment.setRef(commentDao.selectLast());
		System.out.println(comment);
		assertTrue(comment.getRef() == commentDao.selectLast());
	}
}
