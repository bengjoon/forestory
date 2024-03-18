package com.forestory.domain;

import jakarta.persistence.EnumType;

public class User {
//	   @Id
//	   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
//	   private long userNo;			// 회원번호 		
//	   
//	   @Column(nullable = false, unique = true)
//	   private String userEmail;	// 회원이메일
//	   
//	   @Column(nullable = false)
//	   private String userPw;		// 회원비밀번호
//	   
//	   @Column(nullable = false)
//	   private String userName;		// 회원이름
//	   
//	   @Column(nullable = false, unique = true)
//	   private String userNick;		// 회원별명
//	   
//	   @Column(nullable = false)
//	   private int userPhone;		// 회원전화번호
//
//	   @Column(nullable = false, columnDefinition = "date default current_date")
//	   @Temporal(TemporalType.TIMESTAMP)
//	   private LocalDateTime userRegdate = new Date();	// 가입날짜
//	   
//	   @Column(nullable = false)
//	   private String userleavedate;// 탈퇴날짜
//	   
//	   @Column(nullable = false)
//	   @ColumnDefault("1") // default
//	   @Enumerated(EnumType.STRING)
//	   private int userStatus;		// 회원상태
	   
//	   @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//	   private List<Forestory> forestory;
}
