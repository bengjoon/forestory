package com.forestory.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class PlntIlstrSearch {
	
	@Id
	private long plantPilbkNo; //도감번호
	
	@Column
	private String familyKorNm; //과국명
	
	@Column
	private String genusKorNm; //속국명
	
	@Column
	private String plantGnrlNm; //국명
	
	@Column
	private String familyNm; //과명
	
	@Column
	private String genusNm; //속명
	
	@Column
	private String engNm; //영문명
	
	@Column
	private String orplcNm; //원산지
	
	@Column
	private String dstrb; //분포정보
	
	@Column
	private String imgUrl; //이미지
	
	@Column(columnDefinition = "TEXT")
	private String branchDesc; //가지
	
	@Column(columnDefinition = "TEXT")
	private String brdMthdDesc; //번식방법
	
	@Column(columnDefinition = "TEXT")
	private String farmSpftDesc; //재배특성
	
	@Column(columnDefinition = "TEXT")
	private String flwrDesc; //꽃설명
	
	@Column(columnDefinition = "TEXT")
	private String leafDesc; //잎설명
	
	@Column(columnDefinition = "TEXT")
	private String rootDesc; //뿌리설명
	
	@Column(columnDefinition = "TEXT")
	private String shpe; //형태
	
	@Column(columnDefinition = "TEXT")
	private String spft; //특징
	
	@Column(columnDefinition = "TEXT")
	private String sporeDesc; //포자
	
	@Column(columnDefinition = "TEXT")
	private String stemDesc; //줄기설명
	
	@Column(columnDefinition = "TEXT")
	private String sz; //크기설명
	
	@Column(columnDefinition = "TEXT")
	private String useMthdDesc; //사용법
	
	@Column(columnDefinition = "TEXT")
	private String woodDesc; //목재설명
	
	@Column(columnDefinition = "TEXT")
	private String fritDesc; //열매설명
	
	@Column(columnDefinition = "TEXT")
	private String grwEvrntDesc; //생육환경설명
	
	@Column(columnDefinition = "TEXT")
	private String prtcPlnDesc; //보호방안
	
	@Builder
	public PlntIlstrSearch(long plantPilbkNo, String familyKorNm, String genusKorNm, String plantGnrlNm,
			String familyNm, String genusNm, String engNm, String orplcNm, String dstrb, String imgUrl,
			String branchDesc, String brdMthdDesc, String farmSpftDesc, String flwrDesc, String leafDesc,
			String rootDesc, String shpe, String spft, String sporeDesc, String stemDesc, String sz, String useMthdDesc,
			String woodDesc, String fritDesc, String grwEvrntDesc, String prtcPlnDesc) {
		this.plantPilbkNo = plantPilbkNo;
		this.familyKorNm = familyKorNm;
		this.genusKorNm = genusKorNm;
		this.plantGnrlNm = plantGnrlNm;
		this.familyNm = familyNm;
		this.genusNm = genusNm;
		this.engNm = engNm;
		this.orplcNm = orplcNm;
		this.dstrb = dstrb;
		this.imgUrl = imgUrl;
		this.branchDesc = branchDesc;
		this.brdMthdDesc = brdMthdDesc;
		this.farmSpftDesc = farmSpftDesc;
		this.flwrDesc = flwrDesc;
		this.leafDesc = leafDesc;
		this.rootDesc = rootDesc;
		this.shpe = shpe;
		this.spft = spft;
		this.sporeDesc = sporeDesc;
		this.stemDesc = stemDesc;
		this.sz = sz;
		this.useMthdDesc = useMthdDesc;
		this.woodDesc = woodDesc;
		this.fritDesc = fritDesc;
		this.grwEvrntDesc = grwEvrntDesc;
		this.prtcPlnDesc = prtcPlnDesc;
	}
	
	

}
