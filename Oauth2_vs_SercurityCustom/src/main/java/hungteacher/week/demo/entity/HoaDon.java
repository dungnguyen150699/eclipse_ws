package hungteacher.week.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(name ="hoa_don")
@Data
public class HoaDon implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Past
    @NotNull
    private Date dsThoiDiemTTConLai;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<HopDongTraGop> hopDongTraGop;
}
