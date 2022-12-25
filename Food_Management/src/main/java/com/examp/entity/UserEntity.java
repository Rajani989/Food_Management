package com.examp.entity;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NamedQuery(name = "UserEntity.findByEmailId",query="select u from UserEntity u where u.email=:email")

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@ToString
@Builder
@DynamicUpdate
@DynamicInsert
@Table(name="Customer")
public class UserEntity implements Serializable{

    private static final long SerialVersionUID=1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    private String gender;
   
    private String contactNumber;
    
    @Column(name = "email", length = 25, unique = true)
    private String email;
    
    @Column(name="Address", length=40)
    private String address;
    
    @Column(name="password")
    private String pass;
    
    @Column(name="Status")
    private String status;
    
    @Column(name="Role")
    private String role;
}
