package com.ssd.petMate.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssd.petMate.dao.PetsitterDao;
import com.ssd.petMate.dao.mybatis.mapper.PetsitterMapper;
import com.ssd.petMate.domain.Petsitter;
import com.ssd.petMate.page.BoardSearch;

@Repository
public class MybatisPetsitterDao implements PetsitterDao{
	
	@Autowired
	private PetsitterMapper petsitterMapper;
	
	public List<Petsitter> getAllBoard(BoardSearch boardSearch) throws DataAccessException {
		return petsitterMapper.getAllBoard(boardSearch);
	}
	
	public int boardPageCount(HashMap<String, Object> map) throws DataAccessException {
		return petsitterMapper.boardCount();
	}
	
	public Petsitter boardDetail(int boardNum) throws DataAccessException {
		return petsitterMapper.boardDetail(boardNum);
	}

	public void insertBoard(Petsitter petsitter) {
		petsitterMapper.insertBoard(petsitter);
	}
	
	public void deleteBoard(int boardNum) {
		petsitterMapper.deleteBoard(boardNum);
	} 
}