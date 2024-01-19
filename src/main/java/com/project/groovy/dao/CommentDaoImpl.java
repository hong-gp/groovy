package com.project.groovy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.groovy.model.Comment;
import com.project.groovy.model.SearchCondition;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.Comment.";
	
	@Override
	public int deleteAll(Integer bno) throws Exception {
		return session.delete(namespace + "deleteAll", bno);
	}
	
	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + "count", bno);
	}
	
	@Override
	public int delete(Integer cno) throws Exception {
		return session.delete(namespace + "delete", cno);
	}
	
	@Override
	public int insert(Comment comment) {
		return session.insert(namespace + "insert", comment);
	}
	
	@Override
	public List<Comment> selectAll(Integer bno) throws Exception {
		return session.selectList(namespace + "selectAll", bno);
	}
	
	@Override
	public Comment select(Integer cno) {
		return session.selectOne(namespace + "select", cno);
	}
	
	@Override
	public int update(Comment comment) {
		return session.update(namespace + "update", comment);
	}

	@Override
	public int maxStep(Integer ref) throws Exception {
		return session.selectOne(namespace + "maxStep", ref);
	}

	@Override
	public int updateStep(Comment comment) throws Exception {
		return session.update(namespace + "updateStep", comment);
	}

	@Override
	public int selectLast() throws Exception {
		return session.selectOne(namespace + "selectLast");
	}

	@Override
	public int maxRef(Integer bno) throws Exception {
		return session.selectOne(namespace + "maxRef", bno);
	}

	@Override
	public Integer step(Integer pcno, Integer re_level) throws Exception {
		Map map = new HashMap();
		map.put("pcno", pcno);
		map.put("re_level", re_level);
		return session.selectOne(namespace + "step", map);
	}

	@Override
	public int isdel(Integer cno, String commenter) throws Exception {
		Map map = new HashMap();
		map.put("cno", cno);
		map.put("commenter", commenter);
		return session.update(namespace + "isdel", map);
	}

	@Override
	public List<Comment> selectPcno(Integer pcno) throws Exception {
		return session.selectList(namespace + "selectPcno", pcno);
	}

	@Override
	public List<Comment> searchSelectPage(Integer bno, SearchCondition sc) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("offset", sc.getOffset());
		map.put("pageSize", sc.getPageSize());
		return session.selectList(namespace + "searchSelectPage", map);
	}

	
}
