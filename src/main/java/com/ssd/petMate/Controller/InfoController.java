package com.ssd.petMate.Controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.petMate.domain.Info;
import com.ssd.petMate.domain.InfoLike;
import com.ssd.petMate.page.BoardSearch;
import com.ssd.petMate.service.InfoFacade;
import com.ssd.petMate.service.InfoLikeFacade;

@Controller
public class InfoController {
	
	@Autowired
	private InfoFacade infoFacade;
	
	@Autowired
	private InfoLikeFacade infoLikeFacade;
	
	@ModelAttribute("info")
	public Info formBacking(HttpServletRequest request) {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			Info info;
			if (request.getParameter("boardNum") != null) {
				info = infoFacade.boardDetail(Integer.valueOf(request.getParameter("boardNum")));
			}
			else {
				info = new Info();
			}
			return info;
		}
		else return new Info();
	}
	
	@PostMapping("/infoInsert")
	public String infoInsert(@ModelAttribute("info") Info info, SessionStatus sessionStatus, HttpServletRequest request) {
		sessionStatus.setComplete();
		info.setUserID("test1");
		infoFacade.insertBoard(info);
		return "redirect:/info";
	}
	
	@RequestMapping(value = "/info", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView infoBoard(ModelAndView mv, 
			@RequestParam(required = false, defaultValue = "1") int pageNum,
			@RequestParam(required = false, defaultValue = "10") int contentNum,
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String keyword) {
		
		BoardSearch boardSearch = new BoardSearch();
		boardSearch.setSearchType(searchType);
		boardSearch.setKeyword(keyword);
		
//		검색한 결과값을 가져오기 위해 map에 키워드와 검색 타입 저장 후 sql 쿼리에 삽입
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("searchType", searchType);

		int totalCount = infoFacade.boardPageCount(map);

//		페이징과 검색 기능이 적용된 후의 리스트를 가지고 옴
		boardSearch.pageInfo(pageNum, contentNum, totalCount);
		List<Info> infoList = infoFacade.getAllBoard(boardSearch);

		mv.addObject("infoList", infoList);
		mv.addObject("boardSearch", boardSearch);
		mv.setViewName("info/infoList");
		return mv;
	}
	
	@RequestMapping(value = "/infoDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView infoDetail(ModelAndView mv, 
			@RequestParam("boardNum") int boardNum) {
		Info view = infoFacade.boardDetail(boardNum);
		System.out.println(view);
		mv.addObject("info", infoFacade.boardDetail(boardNum));
		mv.setViewName("info/infoDetail");
		return mv;
	}
	
	
	@RequestMapping(value = "/infoForm", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView InfoForm(ModelAndView mv) {
		mv.setViewName("info/infoForm");
		return mv;
	}
	
//	게시글 추천 기능
	@RequestMapping(value="/infoLike", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public HashMap<String, Integer> infoLike(ModelAndView mv, HttpServletRequest request,
			@RequestParam(required = false) int boardNum) {

		String userID = (String) request.getSession().getAttribute("userID");
		Info info = infoFacade.boardDetail(boardNum);
		InfoLike infoLike = new InfoLike(userID, boardNum);

//		이미 사용자가 게시글에 좋아요를 눌렀는지 누르지 않았는지 판별하기 위해 호출
		int count = infoLikeFacade.isLike(infoLike);
		
//		만약 이전에 좋아요를 누르지 않았을 때
//		게시글의 좋아요 개수가 증가하고, like 테이블에 좋아요를 누른 userID와 게시글의 ID가 삽입됨
		if (count == 0) {
			infoLikeFacade.insertLike(infoLike);
		}
		else {
			infoLikeFacade.deleteLike(infoLike);
		}
		
//		좋아요 개수 가지고 오기
		int boardLike = infoLikeFacade.countLike(boardNum);
		
//		좋아요 개수 update
		info.setBoardLike(boardLike);
		infoFacade.updateLike(info);
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("count", count);
		map.put("boardLike", boardLike);
		System.out.println("infolike : " + mv.getViewName());
		
		return map;
	}
	
	@GetMapping("/infoUpdate") 
	public String infoUpdateForm() {
		return "info/infoForm";
	}
	
	@PostMapping("/infoUpdate")
	public String infoUpdate(@ModelAttribute("info") Info info) {
		infoFacade.updateBoard(info);
		return "redirect:/info";
	}
}
