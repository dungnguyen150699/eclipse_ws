package com.example.demo.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping(value="/admin/order")
public class MOrderController {
	@Autowired
	private OrderService os;
//	Show
	@RequestMapping(value="")
	public String HandleOrder(Model model) {
		model.addAttribute("listOrder", os.findAll());
		return "ADMIN/order";
	}
	
	@RequestMapping(value="/orderDESC")
	public String orderAccepted(Model model) {
		model.addAttribute("listOrder", os.findDESC());
		return "ADMIN/order";
	}
	
	@RequestMapping(value="/orderASC")
	public String orderRejected(Model model) {
		model.addAttribute("listOrder", os.findASC());
		return "ADMIN/order";
	}
	
//	Handle
	@RequestMapping(value="/accept/{id}")
	public String orderAccept(@PathVariable(name="id") int id) {
		System.out.println("accepting");
		Order o = os.findById(id);
		o.setApproved(1);
		os.save(o);
		return "redirect:/admin/order";
	}
	
	@RequestMapping(value="/reject/{id}")
	public String orderReject(@PathVariable(name="id") int id) {
		System.out.println("rejecting");
		Order o = os.findById(id);
		o.setApproved(-1);
		os.save(o);
		return "redirect:/admin/order";
	}
}
