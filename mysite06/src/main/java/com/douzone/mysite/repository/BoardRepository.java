package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(BoardVo vo) {
		return sqlSession.insert("board.insert", vo)==1;
	}

	public Integer listCount(String keyword) {
		return sqlSession.selectOne("board.listCount", keyword);
	}

	public List<BoardVo> findAll(Integer page, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("page", (page-1)*5);
		map.put("keyword", keyword);
		return sqlSession.selectList("board.findAll", map);
	}

	public BoardVo findByNo(long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public boolean delete(long no) {
		return sqlSession.delete("board.delete", no)==1;
	}

	public boolean update(BoardVo vo) {
		return sqlSession.update("board.update", vo)==1;
	}

	public boolean replyUpdate(BoardVo vo) {
		return sqlSession.update("board.replyUpdate", vo)==1;
	}

	public boolean updateHit(long no) {
		return sqlSession.update("board.updateHit", no)==1;
	}

}