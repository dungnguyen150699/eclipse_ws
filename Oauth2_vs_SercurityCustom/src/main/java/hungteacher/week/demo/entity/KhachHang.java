package hungteacher.week.demo.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 255)
    //@NotNull
    private String ten;
    private Date ngaySinh;
    @Length(max = 255)
    //@NotNull
    private String diaChi;
    @Length(max = 255)
    //@NotNull
    private String soCmt;
    //@NotNull
    @Length(max = 255)
    private String sdt;
    //@NotNull
    @Length(max = 255)
    private String email;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<HopDongTraGop> hopDongTraGopList;
}
