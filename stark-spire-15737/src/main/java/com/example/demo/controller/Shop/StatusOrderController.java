package com.example.demo.controller.Shop;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping(value="/order")
public class StatusOrderController {
	@Autowired
	private OrderService os;
	
	@RequestMapping(value="")
	public String HandleOrder(Model model,HttpSession session) {
		User u = (User) session.getAttribute("user");
		model.addAttribute("listOrder", os.findAllByUserID(u.getId()));
		System.out.println("Approved" + os.findAllByUserID(u.getId()).get(0).getApproved());
		return "SHOP/statusorder";
	}
	
	@RequestMapping(value="/orderDESC")
	public String orderAccepted(Model model) {
		model.addAttribute("listOrder", os.findDESC());
		return "/order";
	}
	
	@RequestMapping(value="/orderASC")
	public String orderRejected(Model model) {
		model.addAttribute("listOrder", os.findASC());
		return "/order";
	}
	
	@RequestMapping(value="/reject/{id}")
	public String orderReject(@PathVariable(name="id") int id) {
		System.out.println("rejecting");
		Order o = os.findById(id);
		o.setApproved(-1);
		os.save(o);
		return "redirect:/order";
	}
}
