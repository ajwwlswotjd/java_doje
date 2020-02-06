package step7;

import java.util.Scanner;

class Application {
	
	public Manager m;
	public Artist user = null;
	
	public Application() {
		m = new Manager();
		m.loadJson("data/artist.json");
	}
	
	public boolean login(String id, String pw) {
		//아이디와 비밀번호가 존재하는 사람의 경우 true, 
		//그렇지 않으면 false를 반환
		boolean found = false;
		for(int i=0; i < this.m.list.size(); i++) {
			Artist at = this.m.list.get(i);
			if(at.getUser_id().contentEquals(id) && at.getUser_pw().equals(pw)) {
				this.user = at;
				found = true;
				break;
			}
		}
		return found;
	}
	
	public void printUserInfo() {
		//현재 로그인된 유저의 정보를 출력한다.(형식은 다음과 같이)
		/*"id": 9,
		"user_id": "hoc660115",
		"user_pw": "Whang1111",
		"user_name": "황영철",
		"user_about": "만천리에 인정받는 그날까지"*/
		System.out.println("\"id\" : "+user.getId()+" ,");
		System.out.println("\"user_id\" : \""+user.getUser_id()+"\" ,");
		System.out.println("\"user_pw\" : \""+user.getUser_pw()+"\" ,");
		System.out.println("\"user_name\" : \""+user.getUser_name()+"\" ,");
		System.out.println("\"user_about\" : \""+user.getUser_about()+"\"");
	}
	
	public void adjustInfo(Artist artist) {
		//아티스트 정보를 받아서 해당 정보로 수정한다.
		//자동으로 m.save()를 호출하여 저장한다.
		int userIndex = -1;
		for(int i = 0;  i < this.m.list.size();i++) {
			if(this.m.list.get(i).getId() == this.user.getId()) {
				this.m.list.set(i, artist);
				break;
			}
		}
		this.user = artist;
		System.out.println("정보수정이 성공적으로 이루어졌습니다.");
		this.m.save("data/artist.json");
	}
	
	public void logout() {
		//로그아웃 처리
		this.user = null;
	}
}

public class Ex03 {
	public static void main(String[] args) {
		Application app = new Application();
		
		/*
		 *다음과 같이 메뉴를 구성한다.
		 *1. 로그인 => 로그인되지 않은 사람만 뜬다.
		 *2. 정보보기  => 로그인 한 사람만 뜬다.
		 *3. 정보수정 => 로그인 한 사람만 뜬다. 
		 *4. 로그아웃 => 로그인 한 사람만 뜬다.
		 *5. 종료
		 */
		
		// 로그인 후에 정보보기 시
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("=====================================");
			if(app.user==null) System.out.println("[1] 로그인");
			else {
				System.out.println("[2] 정보보기");
				System.out.println("[3] 정보수정");
				System.out.println("[4] 로그아웃");
			}
			System.out.println("[5] 종료");
			System.out.print("번호 입력 : ");
			int sel = in.nextInt();
			if(sel==1) {
				if(app.user!=null) {
					System.out.println("잘못된 접근 입니다.");
					continue;
				}
				
				System.out.print("유저 아이디 입력 : ");
				String userId = in.next();
				System.out.print("비밀번호 입력 : ");
				String pwd = in.next();
				System.out.println(app.login(userId, pwd) ? "성공적으로 로그인 되었습니다." : "존재하지 않는 아이디이거나 비밀번호가 틀렸습니다.");
				
				
			}
			else if(sel==2) {
				if(app.user==null) {
					System.out.println("잘못된 접근 입니다.");
					continue;
				}
				app.printUserInfo();
				
			}
			else if(sel==3) {
				if(app.user==null) {
					System.out.println("잘못된 접근 입니다.");
					continue;
				}
				System.out.print("바꿀 유저 아이디 입력 : ");
				String userId = in.next();
				System.out.print("바꿀 유저 이름 입력 : ");
				String userName = in.next();
				System.out.print("바꿀 비밀번호 입력 : ");
				String pwd = in.next();
				System.out.print("바꿀 유저 정보 입력 : ");
				String info = in.next();
				Artist at = new Artist();
				at.setUser_id(userId);
				at.setUser_about(info);
				at.setUser_name(userName);
				at.setUser_pw(pwd);
				at.setId(app.m.idMax);
				app.m.idMax++;
				app.adjustInfo(at);
			}
			else if(sel==4) {
				if(app.user==null) {
					System.out.println("잘못된 접근 입니다.");
					continue;
				}
				
				app.user = null;
				
			}else if(sel==5) break;
			else continue;
				
			
		}
	}
}
