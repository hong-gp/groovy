package com.project.groovy;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.groovy.dao.BoardDao;
import com.project.groovy.dao.CommentDao;
import com.project.groovy.model.Board;
import com.project.groovy.model.Comment;
import com.project.groovy.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentServiceImplTest {

	@Autowired
	CommentService commentService;
	@Autowired
	CommentDao commentDao;
	@Autowired
	BoardDao boardDao;
	
	@Before
	public void before() {
		boardDao.deleteAll();
	}
	
	@Test
	public void remove() throws Exception {
		Board board = new Board("����", "����1", "����1", "asdf", "����", null);
		assertTrue(boardDao.insert(board) == 1);
		
		Integer num = boardDao.selectAll().get(0).getNum();
		System.out.println("num = " + num);
		commentDao.deleteAll(num);
		assertTrue(boardDao.select(num).getComment_cnt() == 0);
		
		Comment comment = new Comment(num, "�ȳ��ϼ���", "asdf", "����", 0, 358, 0, 0);
		assertTrue(commentService.write(comment) == 1);
		System.out.println(boardDao.select(num).getComment_cnt());
		
		assertTrue(boardDao.select(num).getComment_cnt() == 1);
		Integer cno = commentDao.selectAll(num).get(0).getCno();
		int rowCnt = commentService.remove(cno, num);
		assertTrue(boardDao.select(num).getComment_cnt() == 0);
	}
	
//	@Test
	public void write() throws Exception {
		Board board = new Board("����", "����1", "����1", "asdf", "����", null);
		assertTrue(boardDao.insert(board) == 1);
		
		Integer num = boardDao.selectAll().get(0).getNum();
		System.out.println("num = " + num);
		commentDao.deleteAll(num);
		
		Comment comment = new Comment(num, "�ȳ��ϼ���", "asdf", "����", 0, 358, 0, 0);
		assertTrue(boardDao.select(num).getComment_cnt() == 0);
		assertTrue(commentService.write(comment) == 1);
		
		Integer cno = commentDao.selectAll(num).get(0).getCno();
		assertTrue(boardDao.select(num).getComment_cnt() == 1);
	}
}
