package com.br.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class AJAXController2
 */
@WebServlet("/test2.do")
public class AJAXController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AJAXController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// 요청처리가 다 됐다는 가정하에
		
		// v1. 응답할 데이터가 하나일 경우
		/*
		String responseData = "이름 : " + name + ", 나이 : " + age;
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(responseData);
		*/
		
		// v2. 응답할 데이터가 여러개일 경우
		/*
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(name);
		response.getWriter().print(age);
		*/
		// => success:function의 첫번째 매개변수에 연이어서 하나의 문자열로 담김
		
		/*
		* JSON(JavaScript Object Notation :자바스크립트 객체 표기법)
		* - ajax 통신시 데이터 전송에 자주 사용되는 포맷형식 중 하나
		* 
		* > [value, value, value, ..] => 자바스크립트에서의 배열 객체 => JSONArray
		* > {key:value, key:value, ..} => 자바스크립트에서의 일반 객체 => JSONObject
		* 
		* - 라이브러리 필요 (https://code.google.com/archive/p/json-simple/downloads)
		*/
		
		// 방법1. 자바스크립트의 배열형태
		/*
		JSONArray jArr = new JSONArray();
		jArr.add(name); // ["홍길동"]
		jArr.add(age); // ["홍길동", 20]
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jArr);
		*/
		
		// 방법2. 자바스크립트의 일반객체형태
		JSONObject jObj = new JSONObject();
		jObj.put("name", name);
		jObj.put("age", age);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
