package com.br.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy { // FileRenamePolicy를 구현하는 구현체여야함

	// 원본파일 전달 받아서 파일명 수정작업 후 수정된 파일을 반환시켜주는 메소드
	@Override
	public File rename(File originFile) {
		
		// 원본파일명 ("aaa.jpg")
		String originName = originFile.getName();
		
		// 수정파일명("2023011211263012312.jpg")
		//   파일업로드시간(년월일시분초) + 5자리랜덤값(10000~99999) + 원본파일확장자
		
		// 1. 파일업로드시간
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// 2. 5자리랜덤값
		int ranNum = (int)(Math.random() * 90000 + 10000);
		// 3. 원본파일확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = currentTime + ranNum + ext;
		
		return new File(originFile.getParent(), changeName);
	} 
	
	
	

}
