package com.ttc.bookmeetingroom.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Data
@Entity
@Table(name="user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "token_reset")
    private String tokenReset;

    @Column(name = "token_reset_expried", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenResetExpried;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
