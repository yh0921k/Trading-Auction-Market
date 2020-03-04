-- 물품 테이블 삭제 
drop table if exists tam_stuff;

-- 회원 테이블 삭제
drop table if exists tam_member;

-- 게시판 테이블 삭제 
drop table if exists tam_board;

-- 사진 게시판 테이블 삭제
drop table if exists tam_photo;

-- 사진 게시물 첨부 파일 테이블 삭제
drop table if exists tam_photo_file;

-- 물품 테이블 생성
create table tam_stuff (
  stuff_id int not null auto_increment primary key comment '물품 데이터 식별 번호', 
  name varchar(100) not null comment '물품명',
  state varchar(30) not null comment '물품 상태',
  seller varchar(50) not null comment '물품 판매자',
  category varchar(50) not null comment '물품 분류',
  price int not null comment '물품 가격'
) comment '물품'; 

-- 회원 테이블 생성
create table tam_member (
  member_id int not null auto_increment primary key comment '회원 데이터 식별 번호',
  name varchar(30) not null comment '회원 이름',
  email varchar(50) not null comment '회원 이메일',
  addr varchar(100) not null comment '회원 주소',
  pwd varchar(255) not null comment '회원 암호',
  cdt datetime default now() comment '회원 등록일', 
  tel varchar(20) comment '회원 전화',
  photo varchar(255) comment '회원 사진'
) comment '회원';

create unique index UIX_tam_member_email
  on tam_member ( -- 회원
    email asc    -- 이메일
  );

-- 게시물 테이블 생성
create table tam_board (
  board_id int not null auto_increment primary key comment '게시물 식별 번호',
  conts text not null comment '게시글 내용',
  cdt datetime default now() comment '게시글 생성일',
  vw_cnt int default 0 comment '게시글 조회수', 
  writer varchar(50) comment '게시글 작성자'
) comment '게시물';

-- 사진 게시물 테이블 생성
create table tam_photo (
  photo_id int not null auto_increment primary key comment '사진 게시물 식별 번호',
  stuff_id int not null comment '물품 번호',
  titl varchar(255) not null comment '제목',
  cdt datetime default now() comment '생성일',
  vw_cnt int default 0 comment '조회수',
  -- stuff_id에 저장되는 값은 tam_stuff 테이블의 stuff_id 값으로 제한하는 조건을 추가한다.
  constraint fk_photo_to_stuff foreign key (stuff_id)
    references tam_stuff (stuff_id)
) comment '사진게시물';

-- 사진 게시물에 첨부하는 사진 파일 테이블 생성
create table tam_photo_file (
  photo_file_id int not null auto_increment primary key comment '사진 파일 식별 번호',
  photo_id int not null,
  file_path varchar(255) not null,
  constraint fk_photo_file_to_photo foreign key (photo_id)
    references tam_photo (photo_id)
) comment '사진 게시물 첨부파일 테이블'; 






