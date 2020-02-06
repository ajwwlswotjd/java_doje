/*
 * 클래스의 생성자
 * 
 */
package step7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

//# 아티스트 관리 프로그램 1단계

class Artist {
	private int id;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_about;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_about() {
		return user_about;
	}
	public void setUser_about(String user_about) {
		this.user_about = user_about;
	}
	@Override
	public String toString() {
		return "Artist [id=" + id + ", user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name
				+ ", user_about=" + user_about + "]";
	}	
}

public class Ex01 {
	public static void main(String[] args) {
		
		List<Artist> list = new ArrayList<Artist>();
		
		//gson 라이브러리를 활용하여 제공된 artist.json을 로드하시오.
		//그 결과가 list에 들어가면 됩니다.
		
		String json = "";
		try {
			FileInputStream fis = new FileInputStream(new File("data/artist.json"));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			while(true) {
				String line = br.readLine();
				if(line==null) break;
				json += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 인풋 스트림 에러");
		}
		
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonArray jsonArray = element.getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			
//			System.out.println(jsonElement.getAsJsonObject().get("id").getAsInt());
			
			Artist artist = new Artist();
			artist.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
			artist.setUser_about(jsonElement.getAsJsonObject().get("user_about").getAsString());
			artist.setUser_id(jsonElement.getAsJsonObject().get("user_id").getAsString());
			artist.setUser_name(jsonElement.getAsJsonObject().get("user_name").getAsString());
			artist.setUser_pw(jsonElement.getAsJsonObject().get("user_pw").getAsString());
			list.add(artist);
		}
		
		
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("검색하고자 하는 이름 입력 : ");
		String name = in.next();
		for(int i = 0; i < list.size();i++) if(list.get(i).getUser_name().contains(name)) System.out.println(list.get(i).toString());;
		
		
		
		//name 의 문자가 속하는 모든 유저들의 이름과 user_about이 출력
		//김 이라고 만 검색했다면 김이 들어가는 모든 이름의 유저가 출력되야 함.
		
		
	}
}
