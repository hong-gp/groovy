package com.project.groovy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.groovy.model.Review;
import com.project.groovy.model.SearchCondition;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.Review.";
	
	@Override
	public List<Review> selectAllReview() throws Exception {
		return session.selectList(namespace + "selectAllReview");
	}
	
	@Override
	public List<Review> selectAll(String album_id) throws Exception {
		return session.selectList(namespace + "selectAll", album_id);
	}
	
	@Override
	public List<Review> selectRate(String album_id, String order) throws Exception {
		Map map = new HashMap<>();
		map.put("album_id", album_id);
		map.put("order", order);
		return session.selectList(namespace + "selectRate", map);
	}
	
	@Override
	public List<Review> selectLike(String album_id, String order) throws Exception {
		Map map = new HashMap<>();
		map.put("album_id", album_id);
		map.put("order", order);
		return session.selectList(namespace + "selectLike", map);
	}
	
	@Override
	public int insert(Review review) throws Exception {
		return session.insert(namespace + "insert", review);
	}
	
	@Override
	public int update(Review review) throws Exception {
		return session.update(namespace + "update", review);
	}
	
	@Override
	public int delete(Integer num, String user_id) throws Exception {
		Map map = new HashMap<>();
		map.put("num", num);
		map.put("user_id", user_id);
		return session.delete(namespace + "delete", map);
	}
	
	@Override
	public int deleteAll(String album_id) throws Exception {
		return session.delete(namespace + "deleteAll", album_id);
	}
	
	@Override
	public int updateLikeCnt(Integer like_cnt, Integer num) throws Exception {
		Map map = new HashMap();
		map.put("like_cnt", like_cnt);
		map.put("num", num);
		return session.update(namespace + "updateLikeCnt", map);
	}
	
	@Override
	public int insertReviewLike(String user_id, Integer review_num) throws Exception {
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("review_num", review_num);
		return session.insert(namespace + "insertReviewLike", map);
	}
	
	@Override
	public List<Review> selectPage(String album_id, SearchCondition sc) throws Exception {
		Map map = new HashMap();
		map.put("ambum_id", album_id);
		map.put("offset", sc.getOffset());
		map.put("pageSize", sc.getPageSize());
		return session.selectList(namespace + "selectPage", map);
	}
	
	@Override
	public List<Review> searchSelectPage(String album_id, SearchCondition sc) throws Exception {
		Map map = new HashMap();
		map.put("ambum_id", album_id);
		map.put("offset", sc.getOffset());
		map.put("pageSize", sc.getPageSize());
		return session.selectList(namespace + "searchSelectPage", map);
	}
	
	@Override
	public int searchResultCnt(String album_id) throws Exception {
		return session.selectOne(namespace + "searchResultCnt", album_id);
	}

	@Override
	public int count(String album_id) throws Exception {
		return session.selectOne(namespace + "count", album_id);
	}

	@Override
	public Review select(Integer num) throws Exception {
		return session.selectOne(namespace + "select", num);
	}

	@Override
	public int deleteReviewLike(String user_id, Integer num) throws Exception {
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("num", num);
		return session.delete(namespace + "deleteReviewLike", map);
	}

	@Override
	public Review selectReviewLike(String user_id, Integer review_num) throws Exception {
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("review_num", review_num);
		return session.selectOne(namespace + "selectReviewLike", map);
	}

	@Override
	public int countReviewCnt(Integer review_num) throws Exception {
		return session.selectOne(namespace + "countReviewCnt", review_num);
	}

	@Override
	public Map selectReviewAvg(String album_id) throws Exception {
		return session.selectOne(namespace + "selectReviewAvg", album_id);
	}

	@Override
	public Review selectRandomReview() throws Exception {
		return session.selectOne(namespace + "selectRandomReview");
	}
	
	@Override
	public List<Review> selectAllRate() throws Exception {
		return session.selectList(namespace + "selectAllRate");
	}

	@Override
	public List<Review> selectReviewUser(String user_id) throws Exception {
		return session.selectList(namespace + "selectReviewUser", user_id);
	}

	@Override
	public List<Review> selectA() throws Exception {
		return session.selectList(namespace + "selectA");
	}
}
