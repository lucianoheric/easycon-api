package com.tribosoftec.easycon_api.domain;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Builder;

@Entity
@Table(name = "admlogin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 250)
    private String email;

    @Column(nullable = false, length = 150)
    private String passwd;

    @Column(nullable = false)
    private Boolean active = false;

    @Column(nullable = false)
    private Boolean alter_passwd = true;    

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
