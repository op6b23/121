package com.ssd.petMate.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.petMate.domain.Review;
import com.ssd.petMate.service.PetsitterFacade;
import com.ssd.petMate.service.ReviewFacade;

@Controller
public class ReviewFormController {
	
	@Autowired
	private ReviewFacade reviewFacade;
	
	@Value("review/reviewForm")
	private String formViewName;
	
	@Value("redirect:/review")
	private String successViewName;
	
	@ModelAttribute("review")
	public Review formBacking(HttpServletRequest request, Model model) {
		String userID = (String) request.getSession().getAttribute("userID");
		List<HashMap<String, Object>> petsitterList = new ArrayList<HashMap<String, Object>>();
		petsitterList.addAll(reviewFacade.petsitterChoice(userID));
		for (int i = 0; i < petsitterList.size(); i++)
			System.out.println(petsitterList.get(i).get("USERID"));
		
		model.addAttribute("petsitterList", petsitterList);
		if (request.getMethod().equalsIgnoreCase("GET")) {
			Review review;
			if (request.getParameter("boardNum") != null) {
				review = reviewFacade.boardDetail(Integer.valueOf(request.getParameter("boardNum")));
			}
			else {
				review = new Review();
			}
			return review;
		}
		else return new Review();
	}
	
	@PostMapping("/reviewForm")
	public String reviewInsert(@Valid @ModelAttribute("review") Review review, BindingResult result, 
	         SessionStatus sessionStatus, HttpServletRequest request) {
	      sessionStatus.setComplete();
	      review.setUserID(request.getSession().getAttribute("userID").toString());
	      
	      if (review.getBoardTitle().length() > 25) {
	    	  result.rejectValue("boardTitle", "long");
	      }	
	 
	      if (result.hasErrors()) {
	         return formViewName;
	      }
	      
	      if (request.getParameter("boardNum") == null) {
	    	  review.setUserID(request.getSession().getAttribute("userID").toString());
	    	  reviewFacade.insertBoard(review);
	      }
	      else {
	    	 reviewFacade.updateBoard(review);
	      }
	      
	      return successViewName;
	   }

	@GetMapping("/reviewForm")
	public String reviewUpdateForm() {
		return formViewName;
	}
}
