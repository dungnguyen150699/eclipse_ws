package hungteacher.week.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name = "hop_dong_tra_gop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HopDongTraGop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Past
  //@NotNull
    private Date thoiHanVay;
  //@NotNull
    private Date dsThoiDiemTT;
  //@NotNull
    private Float laiXuat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tblHopDongTraGopid")
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Nhanvienid")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Doitacid")
    private DoiTac doiTac;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KhachHangid")
    private KhachHang khachHang;

    @OneToOne(mappedBy = "hopDongTraGop")
    private DSMatHang dsMatHang;

}
