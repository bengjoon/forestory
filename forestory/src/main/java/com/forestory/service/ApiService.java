package com.forestory.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.forestory.domain.PlntIlstrSearch;
import com.forestory.repository.PlntIlstrSearchRepository;

import jakarta.transaction.Transactional;

@Service
public class ApiService {
	
	@Autowired
	private PlntIlstrSearchRepository psRepository;
	
	@Transactional
	public Page<PlntIlstrSearch> plntList(String keyword, Pageable pageable) {
		
		Page<PlntIlstrSearch> plnData;
		
		if(keyword == null) {
			plnData = psRepository.findAll(pageable);
		}else {
			boolean lan= Pattern.matches("^[ㄱ-ㅎ가-힣]*$", keyword); //한글이면 true
			if(lan) {
				plnData = psRepository.findByPlantGnrlNmContaining(keyword, pageable); //한글명 검색
			}else {
				plnData = psRepository.findByEngNmContaining(keyword, pageable); //영문명 검색
			}
		}
		
		return plnData;
	}
	
	@Transactional
	public PlntIlstrSearch plntDetail(long plantPilbkNo) {
		PlntIlstrSearch pln = psRepository.findByPlantPilbkNo(plantPilbkNo);
		return pln;
	}
	
	public String plntIlstrSearchUpdate() throws Exception {
		
		final String SEARCH_URL = "http://openapi.nature.go.kr/openapi/service/rest/PlantService/plntIlstrSearch";
		final String INFO_URL = "http://openapi.nature.go.kr/openapi/service/rest/PlantService/plntIlstrInfo";
		final String SUERVICE_KEY = "4TNaRbWvrkhc0m1QoK9DXROdpO8GcmzIrTE4xnpXo8lJ0pie8T0eH7kcPEEKPz4cOOkGUs7pIE%2B35B82fW3m9g%3D%3D";
		final int NUM_Of_ROWS = 100;
		
		int pageLength = pageLength(SEARCH_URL, SUERVICE_KEY, "totalCount", NUM_Of_ROWS); /* 페이지 수 */
		
		try{
			List<PlntIlstrSearch> psData = new ArrayList<>();
			for(int k=1; k<2; k++) { // for test
//			for(int k=1; k<pageLength+1; k++) { //페이지 반복
				
				StringBuilder SearchUrlBuilder = new StringBuilder(SEARCH_URL); 	/*URL*/
				SearchUrlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SUERVICE_KEY); /*Service Key*/
				SearchUrlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + NUM_Of_ROWS); /*한 페이지 결과 수*/
				SearchUrlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + k);	/*현재 페이지*/
				
				
				URL SearchUrl = new URL(SearchUrlBuilder.toString());
				
//				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
//				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
//				Document document = dBuilder.parse(SearchUrl.toString());
				Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(SearchUrl.toString());
				document.getDocumentElement().normalize();
				
				Element SearchRoot = document.getDocumentElement(); //
				
			    NodeList noList = SearchRoot.getElementsByTagName("plantPilbkNo"); //도감번호
			    NodeList ynList = SearchRoot.getElementsByTagName("detailYn"); // 상세정보유무
			    
			    
		        for(int i =0; i<noList.getLength(); i++) {
		        	
		        	if(ynList.item(i).getTextContent().equals("Y")) {
		        		StringBuilder InfoUrlBuilder = new StringBuilder(INFO_URL); /*URL*/
		        		InfoUrlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SUERVICE_KEY); /*Service Key*/
		        		InfoUrlBuilder.append("&" + URLEncoder.encode("q1", "UTF-8") + "=" + noList.item(i).getTextContent()); /*도감번호로 검색*/
						
						URL InfoUrl = new URL(InfoUrlBuilder.toString());
						
						Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(InfoUrl.toString());
						doc.getDocumentElement().normalize();
						
						Element InfoRoot = doc.getDocumentElement(); 
						
						PlntIlstrSearch ps = PlntIlstrSearch.builder()
												.plantPilbkNo(Long.parseLong(noList.item(i).getTextContent()))
												.familyKorNm(InfoRoot.getElementsByTagName("familyKorNm").item(0).getTextContent())
												.genusKorNm(InfoRoot.getElementsByTagName("genusKorNm").item(0).getTextContent())
												.plantGnrlNm(InfoRoot.getElementsByTagName("plantGnrlNm").item(0).getTextContent())
												.familyNm(InfoRoot.getElementsByTagName("familyNm").item(0).getTextContent())
												.genusNm(InfoRoot.getElementsByTagName("genusNm").item(0).getTextContent())
												.engNm(InfoRoot.getElementsByTagName("engNm").item(0).getTextContent())
												.orplcNm(InfoRoot.getElementsByTagName("orplcNm").item(0).getTextContent())
												.dstrb(InfoRoot.getElementsByTagName("dstrb").item(0).getTextContent())
												.imgUrl(InfoRoot.getElementsByTagName("imgUrl").item(0).getTextContent())
												.branchDesc(InfoRoot.getElementsByTagName("branchDesc").item(0).getTextContent())
												.brdMthdDesc(InfoRoot.getElementsByTagName("brdMthdDesc").item(0).getTextContent())
												.farmSpftDesc(InfoRoot.getElementsByTagName("farmSpftDesc").item(0).getTextContent())
												.flwrDesc(InfoRoot.getElementsByTagName("flwrDesc").item(0).getTextContent())
												.leafDesc(InfoRoot.getElementsByTagName("leafDesc").item(0).getTextContent())
												.rootDesc(InfoRoot.getElementsByTagName("rootDesc").item(0).getTextContent())
												.shpe(InfoRoot.getElementsByTagName("shpe").item(0).getTextContent())
												.spft(InfoRoot.getElementsByTagName("spft").item(0).getTextContent())
												.sporeDesc(InfoRoot.getElementsByTagName("sporeDesc").item(0).getTextContent())
												.stemDesc(InfoRoot.getElementsByTagName("stemDesc").item(0).getTextContent())
												.sz(InfoRoot.getElementsByTagName("sz").item(0).getTextContent())
												.useMthdDesc(InfoRoot.getElementsByTagName("useMthdDesc").item(0).getTextContent())
												.woodDesc(InfoRoot.getElementsByTagName("woodDesc").item(0).getTextContent())
												.fritDesc(InfoRoot.getElementsByTagName("fritDesc").item(0).getTextContent())
												.grwEvrntDesc(InfoRoot.getElementsByTagName("grwEvrntDesc").item(0).getTextContent())
												.prtcPlnDesc(InfoRoot.getElementsByTagName("prtcPlnDesc").item(0).getTextContent())
												.build();
						
						psData.add(ps);
						
						System.out.println( psData.size()+ ": " + psData.get(i).getPlantPilbkNo());
		        	}
		        }
			}
			
			psRepository.saveAll(psData);
				
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "db입력성공";

	}
	
	public int pageLength(String siteName, String serviceKey, String tagName, int numOfRows) throws Exception {
		
		StringBuilder urlBuilder = new StringBuilder(siteName); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + tagName); 
		
		URL url = new URL(urlBuilder.toString());
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
		Document document = dBuilder.parse(url.toString());
		document.getDocumentElement().normalize();
		
		Element root = document.getDocumentElement();
		
		Node totalCount = root.getElementsByTagName(tagName).item(0);
		int pageLength = Integer.parseInt(totalCount.getTextContent()) /numOfRows +1; /*페이지수*/
		
		return pageLength;
	}
	
	
	

}
