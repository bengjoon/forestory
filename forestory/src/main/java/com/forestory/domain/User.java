package com.forestory.domain;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userNo;
	
	@Column(unique = true)
	private String userEmail, userNick;
	
	@Column
	private String userPw, userPhone;
	
	@Column
	private String userRole;
	
	@Column
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime userLeavedate;
	
	@CreatedDate
	@Convert(converter = LocalDateTimeConverter.class)
	@Column(updatable = false, nullable = false)
	private LocalDateTime userRegdate;
	
	@ColumnDefault("true")
	@Column(columnDefinition = "TINYINT(1)")
	private Boolean userState;
	
	
	@Builder
	public User(String userEmail, String userPw, String userNick, String userRole, String userPhone,
			LocalDateTime userLeavedate,  Boolean userState) {
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userNick = userNick;
		this.userRole = userRole;
		this.userPhone = userPhone;
		this.userLeavedate = userLeavedate;
		this.userState = userState;
	}
	
	
	
}
