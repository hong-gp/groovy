package com.project.groovy.dao;

import java.util.List;
import java.util.Map;

import com.project.groovy.model.Review;
import com.project.groovy.model.SearchCondition;

public interface ReviewDao {
	
	List<Review> selectA() throws Exception;
	
	List<Review> selectAllReview() throws Exception;
	
	Map selectReviewAvg(String album_id) throws Exception;

	List<Review> selectAll(String album_id) throws Exception;
	
	List<Review> selectReviewUser(String user_id) throws Exception;

	List<Review> selectRate(String album_id, String order) throws Exception;

	List<Review> selectLike(String album_id, String order) throws Exception;

	int insert(Review review) throws Exception;

	int update(Review review) throws Exception;

	int delete(Integer num, String user_id) throws Exception;

	int deleteAll(String album_id) throws Exception;

	int updateLikeCnt(Integer like_cnt, Integer num) throws Exception;

	int insertReviewLike(String user_id, Integer review_num) throws Exception;
	
	int deleteReviewLike(String user_id, Integer review_num) throws Exception;

	List<Review> selectPage(String album_id, SearchCondition sc) throws Exception;

	List<Review> searchSelectPage(String album_id, SearchCondition sc) throws Exception;

	int searchResultCnt(String album_id) throws Exception;
	
	int count(String album_id) throws Exception;
	
	Review select(Integer num) throws Exception;
	
	Review selectReviewLike(String user_id, Integer review_num) throws Exception;
	
	int countReviewCnt(Integer review_num) throws Exception;
	
	Review selectRandomReview() throws Exception;
	
	List<Review> selectAllRate() throws Exception;

}