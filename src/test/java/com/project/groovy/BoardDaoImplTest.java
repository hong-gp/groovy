package com.project.groovy;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.groovy.dao.BoardDao;
import com.project.groovy.model.Board;
import com.project.groovy.model.SearchCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoImplTest {

	@Autowired
	BoardDao boardDao;
	
//	@Test
	public void testInsert220() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		for (int i=1; i<=220; i++) {
			Board board = new Board("music", "제목은 제목"+i, "내용"+i, "asdf", "의적", null);
			assertTrue(boardDao.insert(board) == 1);
		}
	}
	
	//@Test
	public void testCount() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		assertTrue(boardDao.count() == 1);
		
		assertTrue(boardDao.insert(board) == 1);
		assertTrue(boardDao.count() == 2);
	}

	//@Test
	public void testSelectAll() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		List<Board> list = boardDao.selectAll();
		assertTrue(list.size() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		
		list = boardDao.selectAll();
		assertTrue(list.size() == 1);
		
		assertTrue(boardDao.insert(board) == 1);
		list = boardDao.selectAll();
		assertTrue(list.size() == 2);
	}

	//@Test
	public void testSelect() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		
		Integer num = boardDao.selectAll().get(0).getNum();
		board.setNum(num);
		Board board2 = boardDao.select(num);
		assertTrue(board.equals(board2));
	}

	//@Test
	public void testDeleteAll() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		assertTrue(boardDao.deleteAll() == 1);
		assertTrue(boardDao.count() == 0);
		
		board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		assertTrue(boardDao.insert(board) == 1);
		assertTrue(boardDao.deleteAll() == 2);
		assertTrue(boardDao.count() == 0);
	}

//	@Test
	public void testDelete() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		Integer num = (boardDao.selectAll().get(0).getNum());
		assertTrue(boardDao.delete(num, board.getWriter()) == 1);
		assertTrue(boardDao.count() == 0);
		
		assertTrue(boardDao.insert(board) == 1);
		num = boardDao.selectAll().get(0).getNum();
		assertTrue(boardDao.delete(num, board.getWriter() + "222") == 0);
		assertTrue(boardDao.count() == 1);
		
		assertTrue(boardDao.delete(num, board.getWriter()) == 1);
		assertTrue(boardDao.count() == 0);
		
		assertTrue(boardDao.insert(board) == 1);
		num = boardDao.selectAll().get(0).getNum();
		assertTrue(boardDao.delete(num + 1, board.getWriter()) == 0);
		assertTrue(boardDao.count() == 1);
	}

//	@Test
	public void testInsert() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		
		board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		assertTrue(boardDao.count() == 2);
		
		boardDao.deleteAll();
		board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		assertTrue(boardDao.count() == 1);
	}

//	@Test
	public void testIncreaseViewCnt() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		
		Integer num = boardDao.selectAll().get(0).getNum();
		assertTrue(boardDao.increaseViewCnt(num) == 1);
		assertTrue(boardDao.selectAll().get(0).getView_cnt() == 1);
	}

//	@Test
	public void testUpdate() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("음악", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		
		Integer num = boardDao.selectAll().get(0).getNum();
		board.setNum(num);
		board.setTitle("제목2");
		assertTrue(boardDao.update(board) == 1);
		
		Board board2 = boardDao.select(num);
		assertTrue(board.equals(board2));
	}

	//@Test
	public void testSelectPage() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSearchSelectPage() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		for (int i=1; i<=20; i++) {
			Board board = new Board("music", "제목"+i, "내용"+i, "asdf", "의적", null);
			assertTrue(boardDao.insert(board) == 1);
		}
		SearchCondition sc = new SearchCondition(1, 10, "제목2", "T", "M");
		List<Board> list = boardDao.searchSelectPage(sc);
		System.out.println("list= " + list);
		assertTrue(list.size() == 2);
	}

//	@Test
	public void testSearchResultCnt() throws Exception {
		boardDao.deleteAll();
		for (int i=1; i<=20; i++) {
			Board board = new Board("music", "제목"+i, "내용"+i, "asdf", "의적", null);
			assertTrue(boardDao.insert(board) == 1);
		}
		SearchCondition sc = new SearchCondition(1, 10, "제목2", "T", "M");
		System.out.println(sc);
		int cnt = boardDao.searchResultCnt(sc);
		System.out.println(cnt);
		assertTrue(cnt == 2);
	}

//	@Test
	public void testUpdateLikeCnt() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("music", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		
		Integer num = boardDao.selectAll().get(0).getNum();
		board.setNum(num);
		assertTrue(boardDao.updateLikeCnt(num, 1) == 1);
		assertTrue(boardDao.selectAll().get(0).getLike_cnt() == 1);
		
		assertTrue(boardDao.updateLikeCnt(num, -1) == 1);
		assertTrue(boardDao.selectAll().get(0).getLike_cnt() == 0);
	}

//	@Test
	public void testUpdateCommentCnt() throws Exception {
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		Board board = new Board("music", "제목1", "내용1", "asdf", "의적", null);
		assertTrue(boardDao.insert(board) == 1);
		
		Integer num = boardDao.selectAll().get(0).getNum();
		board.setNum(num);
		assertTrue(boardDao.updateCommentCnt(num, 1) == 1);
		assertTrue(boardDao.selectAll().get(0).getComment_cnt() == 1);
		
		assertTrue(boardDao.updateCommentCnt(num, -1) == 1);
		assertTrue(boardDao.selectAll().get(0).getComment_cnt() == 0);
	}

//	@Test
	public void testInsertLike() throws Exception {
		boardDao.deleteLikeAll();
		
		assertTrue(boardDao.insertLike(370, "asdf") == 1);
	}
	
	@Test
	public void testBestBoard() throws Exception {
		List<Map> list = boardDao.selectBestBoard();
		assertTrue((Integer)list.get(0).get("bno") == 372);
		
		Board board = boardDao.select((Integer)list.get(0).get("bno"));
		System.out.println(board);
	}
}
