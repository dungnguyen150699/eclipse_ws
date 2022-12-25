package hungteacher.week.demo.controller;

import hungteacher.week.demo.entity.DSMatHang;
import hungteacher.week.demo.entity.HoaDon;
import hungteacher.week.demo.entity.HopDongTraGop;
import hungteacher.week.demo.entity.KhachHang;
import hungteacher.week.demo.repository.DSMatHangRepository;
import hungteacher.week.demo.repository.HoaDonRepository;
import hungteacher.week.demo.repository.HopDongTraGopRepository;
import hungteacher.week.demo.repository.KhachHangRepository;
import hungteacher.week.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class HomeController {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private DSMatHangRepository dsMatHangRepository;
    @Autowired
    private HopDongTraGopRepository hopDongTraGopRepository;
//    @Autowired
//    private

    @RequestMapping(value = "/")
    public String showTemplate (Model model) {
        List<KhachHang> list = khachHangRepository.findByCondition1(new KhachHang());
        // Nomal
        String x = khachHangRepository.getStringHello("Hello Dung");

        // list Object
        List<Object> x1 = khachHangRepository.selectObject();
        // Object []
        Object[] x2 = khachHangRepository.selectObject2();

        // List Map
        List<List<Map<String, Object>>> x3 = khachHangRepository.selectObject3();

        System.out.println(list.toString());
        model.addAttribute("khach_hang",new KhachHang());
        model.addAttribute("list_hdtragop",new HopDongTraGop());
        return "company_invoice";
    }

    @RequestMapping(value = "/findHDTG")
    public String findHDTG(Model model, @ModelAttribute("khach_hang") KhachHang khachHang, HttpServletRequest request) {
//        HoaDon hoaDon1 = hoaDonRepository.findById(hoaDon.getId()).get();
//        model.addAttribute("hoaDon",hoaDon1);
//        model.addAttribute("list_HdTraGop",hoaDon1.getHopDongTraGop());
        KhachHang khachHang1 = khachHangRepository.findById(khachHang.getId()).get();
        List<HopDongTraGop> hopDongTraGopList =  khachHang1.getHopDongTraGopList();
        model.addAttribute("khach_hang",khachHang1);
        model.addAttribute("list_hdtragop",hopDongTraGopList);
        return "company_invoice";
    }

    @RequestMapping(value = "/findDSMH/{id}")
    public String findDSMH(Model model, @PathVariable("id") Long id) {
        model.addAttribute("khach_hang",new KhachHang());
        model.addAttribute("list_hdtragop",new HopDongTraGop());
        model.addAttribute("dsMatHang",dsMatHangRepository.findDSMatHangByIdHdtg(id));
        return "company_invoice";
    }
}
