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

import com.project.groovy.dao.ReviewDao;
import com.project.groovy.model.Review;
import com.project.groovy.model.SearchCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReviewDaoImplTest {
	
	@Autowired
	ReviewDao reviewDao;
	
	String album_id = "1234";
	
//	@Before
	public void before() throws Exception {
		reviewDao.deleteAll(album_id);
	}

//	@Test
	public void testSelectAll() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment1", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		List<Review> list = reviewDao.selectAll(album_id);
		assertTrue(list.size() == 1);
		
		Review review2 = new Review(album_id, "asdf", "����", "comment2", 0);
		assertTrue(reviewDao.insert(review2) == 1);
		
		list = reviewDao.selectAll(album_id);
		assertTrue(list.size() == 2);
	}
	
//	@Test
	public void testSelect() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment1", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		Integer num = reviewDao.selectAll(album_id).get(0).getNum();
		review.setNum(num);
		
		Review review2 = reviewDao.select(num);
		assertTrue(review.equals(review2));
	}

//	@Test
	public void testSelectRate() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment1", 1);
		assertTrue(reviewDao.insert(review) == 1);
		
		Review review2 = new Review(album_id, "asdf", "����", "comment2", 2);
		assertTrue(reviewDao.insert(review2) == 1);
		
		String order = "desc";
		List<Review> list = reviewDao.selectRate(album_id, order);
		assertTrue(list.get(0).getRate() == 2);
		
		order = "asc";
		list = reviewDao.selectRate(album_id, order);
		assertTrue(list.get(0).getRate() == 1);
	}

//	@Test
	public void testSelectLike() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment1", 0);
		assertTrue(reviewDao.insert(review) == 1);
		Integer num = reviewDao.selectAll(album_id).get(0).getNum();
		reviewDao.updateLikeCnt(1, num);
		
		Review review2 = new Review(album_id, "asdf", "����", "comment1", 0);
		assertTrue(reviewDao.insert(review2) == 1);
		
		assertTrue(reviewDao.selectLike(album_id, "asc").get(0).getLike_cnt() == 0);
		assertTrue(reviewDao.selectLike(album_id, "desc").get(0).getLike_cnt() == 1);
	}

//	@Test
	public void testInsert() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		assertTrue(reviewDao.count(album_id) == 1);
	}

//	@Test
	public void testUpdate() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		review.setNum(reviewDao.selectAll(album_id).get(0).getNum());
		review.setComment("comment2");
		assertTrue(reviewDao.update(review) == 1);
		assertTrue(reviewDao.selectAll(album_id).get(0).getComment().equals(review.getComment()));
	}

//	@Test
	public void testDelete() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		Integer num = reviewDao.selectAll(album_id).get(0).getNum();
		assertTrue(reviewDao.delete(num, "asdf") == 1);
		assertTrue(reviewDao.count(album_id) == 0);
	}

//	@Test
	public void testDeleteAll() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment", 0);
		assertTrue(reviewDao.insert(review) == 1);
		Review review2 = new Review(album_id, "asdf", "����", "comment1", 0);
		assertTrue(reviewDao.insert(review2) == 1);
		
		assertTrue(reviewDao.deleteAll(album_id) == 2);
		assertTrue(reviewDao.count(album_id) == 0);
	}

//	@Test
	public void testUpdateLikeCnt() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		Integer num = reviewDao.selectAll(album_id).get(0).getNum();
		
		assertTrue(reviewDao.updateLikeCnt(1, num) == 1);
		assertTrue(reviewDao.selectAll(album_id).get(0).getLike_cnt() == 1);
		
		assertTrue(reviewDao.updateLikeCnt(-1, num) == 1);
		assertTrue(reviewDao.selectAll(album_id).get(0).getLike_cnt() == 0);
	}

//	@Test
	public void testInsertReviewLike() throws Exception {
		
		Review review = new Review(album_id, "asdf", "����", "comment", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		Integer num = reviewDao.selectAll(album_id).get(0).getNum();
		assertTrue(reviewDao.insertReviewLike("asdf", num) == 1);
	}
	
//	@Test
	public void testDeleteReviewLike() throws Exception {
		Review review = new Review(album_id, "asdf", "����", "comment", 0);
		assertTrue(reviewDao.insert(review) == 1);
		
		Integer num = reviewDao.selectAll(album_id).get(0).getNum();
		assertTrue(reviewDao.insertReviewLike("asdf", num) == 1);
		
		assertTrue(reviewDao.deleteReviewLike("asdf", num) == 1);
	}

//	@Test
	public void testSelectPage() throws Exception {
		for (int i=1; i<=20; i++) {
			Review review = new Review(album_id, "asdf", "����", "comment" + i, 0);
			assertTrue(reviewDao.insert(review) == 1);
		}
		SearchCondition sc = new SearchCondition(1, 10);
		System.out.println(sc.getOffset());
		System.out.println(sc.getPageSize());
		List<Review> list = reviewDao.searchSelectPage(album_id, sc);
		System.out.println("list= " + list);
		assertTrue(list.size() == 2);
	}

//	@Test
	public void testSearchSelectPage() {
		fail("Not yet implemented");
	}

//	@Test
	public void testSearchResultCnt() {
		fail("Not yet implemented");
	}
	
}

