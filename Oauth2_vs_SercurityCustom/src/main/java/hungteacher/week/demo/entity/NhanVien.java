package hungteacher.week.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
public class NhanVien implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 255)
    private String username;

    @NotNull
    @Length(max = 255)
    private String password;

    @NotNull
    @Length(max = 255)
    private String ten;

    @NotNull
    @Length(max = 255)
    private Date ngaySinh;

    @NotNull
    @Length(max = 255)
    private String diaChi;

    @NotNull
    @Length(max = 255)
    private String email;

    @Length(max = 255)
    private String viTri;

    @ManyToMany(targetEntity = ChucVu.class, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="staff_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<ChucVu> roles;

    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private List<HopDongTraGop> hopDongTraGopList;

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        this.roles.stream().forEach(r -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        return grantedAuthorities;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
