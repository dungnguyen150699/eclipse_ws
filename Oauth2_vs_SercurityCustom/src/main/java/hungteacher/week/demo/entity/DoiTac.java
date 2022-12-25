package hungteacher.week.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "doi_tac")
@Data
public class DoiTac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 255)
    @NotNull
    private String tenCTy;
    @Length(max = 255)
    @NotNull
    private String diaChi;
    @Length(max = 255)
    @NotNull
    private String maSoThue;

    @OneToMany(mappedBy="doiTac", cascade = CascadeType.ALL)
    private List<HopDongTraGop> doiTacList;

}
