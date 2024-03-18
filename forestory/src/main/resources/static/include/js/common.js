// 빈칸 유효성 검사
// 함수명: chkData(유효성 체크 대상, 메시지 내용)
function chkData(item, msg) {
	if ($(item).val().replace(/\s/g, "") == "") {
		alert(msg + " 입력해주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	} else {
		return true;
	}
};

function formCheck(item, msgArea, msg){
	if ($(item).val().replace(/\s/g, "") == "") {
		$(msgArea).text(msg + " 입력해주세요.").css("color","red");
		$(item).val("");
		$(item).focus();
		return false;
	} else {
		return true;
	}
};

/* 함수명: chkFile(파일명 객체)
 * 설명 : 이미지 파일 여부를 확인하기 위해 확장자 확인 함수.
 * if(!chkFile($("#file"))) return;
 */
function chkFile(item){
	/* 참고사항
	 * jQuery.inArray(찾을 값, 검색 대상의 배열) : 배열 내의 값을 찾아서 인덱스를 반환(요소가 없을 경우 -1 반환)
	 * pop() : 배열의 마지막 요소를 제거한 후, 제거한 요소를 반환
	 */
	let ext = item.val().split('.').pop().toLowerCase();
	if(jQuery.inArray(ext, ['gif', 'png', 'jpg']) == -1){
		alert('gif, png, jpg 파일만 업로드 할 수 있습니다.');
		item.val("");
		return false;
	} else {
		return true;
	}
}

function checkForm(item, msg){
	if ($(item).val().replace(/\s/g, "") == "") {
		$(item).attr("placeholder", msg+" 입력해주세요.");
		$(item).focus();
		return false;
	} else {
		return true;
	}
}