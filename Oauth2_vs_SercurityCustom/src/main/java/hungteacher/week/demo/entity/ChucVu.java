package hungteacher.week.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chuc_vu")
@Getter
@Setter
public class ChucVu {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(targetEntity = NhanVien.class,mappedBy = "roles",fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Set<NhanVien> staffs;
}
