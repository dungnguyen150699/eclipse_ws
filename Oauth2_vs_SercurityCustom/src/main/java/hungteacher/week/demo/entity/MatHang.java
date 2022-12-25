package hungteacher.week.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name="mat_hang")
@Data
public class MatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(max = 10)
    private int ChungLoai;
    @NotNull
    @Length(max = 10)
    private String ten;
    @NotNull
    @Length(max = 10)
    private String donViTinh;
    @NotNull
    private String giaNiemYet;
    @NotNull
    private String chietKhau;

    @OneToMany(mappedBy = "matHang", cascade = CascadeType.ALL)
    private List<DSMatHang> dsMatHangList;
}
