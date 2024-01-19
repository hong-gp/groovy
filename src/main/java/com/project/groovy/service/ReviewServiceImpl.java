package com.project.groovy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groovy.dao.ReviewDao;
import com.project.groovy.model.Review;
import com.project.groovy.model.SearchCondition;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public List<Review> selectAllReview() throws Exception {
		return reviewDao.selectAllReview();
	}
	
	@Override
	public int count(String album_id) throws Exception {
		return reviewDao.count(album_id);
	}
	
	@Override
	public List<Review> selectAll(String album_id) throws Exception {
		return reviewDao.selectAll(album_id);
	}
	
	@Override
	public List<Review> selectRate(String album_id, String order) throws Exception {
		return reviewDao.selectRate(album_id, order);
	}
	
	@Override
	public List<Review> selectLike(String album_id, String order) throws Exception {
		return reviewDao.selectLike(album_id, order);
	}
	
	@Override
	public int insert(Review review) throws Exception {
		return reviewDao.insert(review);
	}
	
	@Override
	public int update(Review review) throws Exception {
		return reviewDao.update(review);
	}
	
	@Override
	public int delete(Integer num, String user_id) throws Exception {
		return reviewDao.delete(num, user_id);
	}
	
	@Override
	public int deleteAll(String album_id) throws Exception {
		return reviewDao.deleteAll(album_id);
	}
	
	@Override
	public int updateLikeCnt(Integer like_cnt, Integer num) throws Exception {
		return reviewDao.updateLikeCnt(like_cnt, num);
	}
	
	@Override
	public int insertReviewLike(String user_id, Integer review_num) throws Exception {
		return reviewDao.insertReviewLike(user_id, review_num);
	}
	
	@Override
	public List<Review> selectPage(String album_id, SearchCondition sc) throws Exception {
		return reviewDao.selectPage(album_id, sc);
	}
	
	@Override
	public List<Review> searchSelectPage(String album_id, SearchCondition sc) throws Exception {
		return reviewDao.searchSelectPage(album_id, sc);
	}
	
	@Override
	public int searchResultCnt(String album_id) throws Exception {
		return reviewDao.searchResultCnt(album_id);
	}

	@Override
	public int deleteReviewLike(String user_id, Integer num) throws Exception {
		return reviewDao.deleteReviewLike(user_id, num);
	}

	@Override
	public Review selectReviewLike(String user_id, Integer review_num) throws Exception {
		return reviewDao.selectReviewLike(user_id, review_num);
	}

	@Override
	public int countReviewCnt(Integer revire_num) throws Exception {
		return reviewDao.countReviewCnt(revire_num);
	}

	@Override
	public Map selectReviewAvg(String album_id) throws Exception {
		return reviewDao.selectReviewAvg(album_id);
	}

	@Override
	public Review selectRandomReview() throws Exception {
		return reviewDao.selectRandomReview();
	}

	@Override
	public List<Review> selectAllRate() throws Exception {
		return reviewDao.selectAllRate();
	}

	@Override
	public List<Review> getReviewUser(String user_id) throws Exception {
		return reviewDao.selectReviewUser(user_id);
	}

	@Override
	public List<Review> selectA() throws Exception {
		return reviewDao.selectA();
	}

	@Override
	public Review select(Integer num) throws Exception {
		return reviewDao.select(num);
	}
}
