package step7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//# 아티스트 관리 프로그램 2단계

class Manager{
	public int idMax = (int)Double.NEGATIVE_INFINITY;
	public List<Artist> list = new ArrayList<Artist>();
	public void loadJson(String file) {
		//file명을 입력받아서 list 에 넣어준다.
		try {
			FileInputStream fis = new FileInputStream(new File(file));
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String json = "";
			
			while(true) {
				String line = br.readLine();
				if(line==null) break;
				json += line;
			}
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(json);
			JsonArray jsonArray = element.getAsJsonArray();
			for (JsonElement jsonElement : jsonArray) {
				
//				System.out.println(jsonElement.getAsJsonObject().get("id").getAsInt());
				
				Artist artist = new Artist();
				artist.setId(jsonElement.getAsJsonObject().get("id").getAsInt());
				artist.setUser_about(jsonElement.getAsJsonObject().get("user_about").getAsString());
				artist.setUser_id(jsonElement.getAsJsonObject().get("user_id").getAsString());
				artist.setUser_name(jsonElement.getAsJsonObject().get("user_name").getAsString());
				artist.setUser_pw(jsonElement.getAsJsonObject().get("user_pw").getAsString());
				list.add(artist);
			}
			
			for(int i = 0; i < this.list.size(); i++) if(this.list.get(i).getId() > this.idMax) this.idMax = this.list.get(i).getId();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일을 못찾아용 ㅜㅜ");
		}
		
	}
	
	public void add(String user_id, String user_pw, String user_name, String user_about) {
		//4개의 값을 받아 새로운 유저를 만들어서 list에 넣어준다.
		//이때 id는 자동으로 증가되어 들어간다. json 파일에 10번까지 있으니 새로운 유저가 들어가면 11번이된다.
		//이후 추가시에는 12번이 된다.
		Artist asdf = new Artist();
		boolean qqq = false;
		for (Artist artist : list) {
			if(artist.getUser_name().equals(user_id)) {
				System.out.println("이미 존재 하는 유저아이디 입니다.");
				qqq = true;
				break;
			}
		}
		if(qqq) return;
		asdf.setUser_id(user_id);
		asdf.setUser_pw(user_pw);
		asdf.setUser_name(user_name);
		asdf.setUser_about(user_about);
		asdf.setId(this.idMax);
		this.idMax++;
		list.add(asdf);
		System.out.println("추가가 완료되었습니다.");
	}
	
	public void save(String fileName) {
		//현재 list에 있는 데이터를 artist.json에 다시 저장한다.
		try {
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw );
			
			JsonArray jsonArray = new JsonArray();
			
			for(int i = 0; i < this.list.size();i++) {
				Artist at = this.list.get(i);
		        JsonObject info = new JsonObject();
				info.addProperty("id",at.getId());
				info.addProperty("user_id", at.getUser_id());
				info.addProperty("user_name",at.getUser_name());
				info.addProperty("user_pw", at.getUser_pw());
				info.addProperty("user_about", at.getUser_about());
				jsonArray.add(info);
			}
			
			String jsonInfo = jsonArray.toString();
			bw.write(jsonInfo);
			bw.flush();
			System.out.println(fileName+" 파일에 성공적으로 저장되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 못찾음 ㅅㄱ");
		}
	}
	
	public void remove(String userId) {
		int userIndex = -1; 
		for(int i=0; i < this.list.size(); i++) {
			if(this.list.get(i).getUser_id().equals(userId)) {
				userIndex = i;
				break;
			}
		}
		if(userIndex == -1) System.out.println("해당하는 아이디의 유저를 찾을 수 없습니다.");
		else {
			Scanner in = new Scanner(System.in);
			Artist art = this.list.get(userIndex);
			System.out.print("비밀번호 입력 : ");
			String pwd = in.next();
			if(pwd.equals(art.getUser_pw())) {
				this.list.remove(userIndex);
				System.out.println("유저 삭제가 성공적으로 진행되었습니다.");
			} else System.out.println("잘못된 비밀번호 입니다.");
//			in.close();
		}
	}
	
	public void search(String name) {
		for(int i = 0; i < list.size();i++) if(list.get(i).getUser_name().contains(name)) System.out.println(list.get(i).toString());
	}

}

public class Ex02 {
	public static void main(String[] args) {

		Manager m = new Manager();
		m.loadJson("data/artist.json"); 
		//여기서 유저를 추가할 수 있도록 메뉴를 구성한다
		/*
		 * 1. 유저 추가
		 * 2. 저장
		 * 3. 삭제  => 삭제할 유저의 user_id를 입력하여 삭제해준다.
		 * 3. 검색
		 * 4. 종료
		 */
		//유저 추가의 인터페이스는 자율적으로 하되 4개의 값(id,pw,name, about)을 입력받아 저장해야 한다.
		//검색시 사용자의 이름을 입력받고 해당 이름이 포함된 사용자를 전부 출력한다. Ex01과 동일함
		//저장시 m.save가 호출되고 현재 데이터가 저장된다.
		//저장후 종료하고 다시 실행하면 이전데이터가 그대로 살아있어야 한다.
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("========================================");
			System.out.println("[1] 유저 추가");
			System.out.println("[2] 저장");
			System.out.println("[3] 삭제");
			System.out.println("[4] 검색");
			System.out.println("[5] 종료");
			System.out.print("번호 입력 : ");
			int sel = in.nextInt();
			
			if(sel==1) {
				System.out.print("유저 아이디 입력 : ");
				String id = in.next();
				System.out.print("비밀번호 입력 : ");
				String pwd = in.next();
				System.out.print("유저 이름 입력 : ");
				String name = in.next();
				System.out.print("유저 정보 입력 (띄어쓰기 입력 불가..?) : ");
				String about = in.next();
				m.add(id,pwd, name, about);
				
			}
			else if(sel==2) {
				m.save("data/artist.json");
			}
			else if(sel==3) {
				System.out.print("삭제할 유저 아이디 입력 : ");
				String userId = in.next();
				m.remove(userId);
			}
			else if(sel==4) {
				System.out.print("검색할 이름 입력 : ");
				String name = in.next();
				m.search(name);
			}
			else if(sel==5) break;
			else continue;
		}
		in.close();
	}
}
