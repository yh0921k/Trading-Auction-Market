
-- 물품 예제 데이터 
insert into tam_stuff(stuff_id, name, state, seller, category, price)
  values(11, 'Mac Pro 2019', 'A+', 'kyh', 'laptop', 3000000);
insert into tam_stuff(stuff_id, name, state, seller, category, price)
  values(12, 'Galaxy S20', 'S', 'lee', 'cellphone', 1200000);
insert into tam_stuff(stuff_id, name, state, seller, category, price)
  values(13, 'iPhone 10', 'A', 'shin', 'cellphone', 1000000);
insert into tam_stuff(stuff_id, name, state, seller, category, price)
  values(14, 'Python Basic Programming', 'B', 'Jo', 'book', 30000);
insert into tam_stuff(stuff_id, name, state, seller, category, price)
  values(15, 'Arduino Uno', 'C', 'kyh', 'etc', 80000);

-- 회원 예제 데이터
insert into tam_member(member_id, name, email, pwd, addr) 
  values(11, 'kyh', 'kyh@gmail.com', password('1111'), "Seoul");
insert into tam_member(member_id, name, email, pwd, addr) 
  values(12, 'lee', 'lee@test.com', password('1111'), "Busan");
insert into tam_member(member_id, name, email, pwd, addr) 
  values(13, 'shin', 'shin@naver.com', password('1111'), "Korea");
insert into tam_member(member_id, name, email, pwd, addr) 
  values(14, 'Jo', 'Jo@test.com', password('1111'), "Korea");

-- 게시물 예제 데이터
insert into tam_board(board_id, conts) values(1, '내용1');
insert into tam_board(board_id, conts) values(2, '내용2');
insert into tam_board(board_id, conts) values(3, '내용3');
insert into tam_board(board_id, conts) values(4, '내용4');
insert into tam_board(board_id, conts) values(5, '내용5');

-- 물품 사진 게시물 예제 데이터
insert into lms_photo(photo_id, lesson_id, titl) 
  values(1, 101, '자바 컴파일러 구동 원리');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(2, 101, '자바 클래스 실행하는 방법');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(3, 101, '옵저버 패턴 클래스 다이어그램');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(4, 104, 'HTML/CSS/JavaScript 관계');
insert into lms_photo(photo_id, lesson_id, titl) 
  values(5, 104, '자바 스크립트 구동 원리');

-- 물품 사진 게시물 첨부 파일 예제 데이터
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(1, 1, 'a1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(2, 1, 'a2.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(3, 1, 'a3.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(4, 2, 'b1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(5, 3, 'c1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(6, 3, 'c2.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(7, 4, 'd1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(8, 5, 'e1.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(9, 5, 'e2.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(10, 5, 'e3.gif');
insert into lms_photo_file(photo_file_id, photo_id, file_path)
  values(11, 5, 'e4.gif');

