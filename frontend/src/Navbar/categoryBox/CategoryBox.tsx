import React from 'react';
import './categoryBox.scss';
import {
  Logo01, Logo02, Logo03, Logo04, Logo05, Logo06, Logo07, Logo08, Logo09, Logo10, Logo11, 
  Logo12, Logo13, Logo14, Logo15, Logo16, Logo17, Logo18, Logo19, Logo20, Logo21, Logo22,
} from "./Logo";

function CategoryBox() {
  type LogoType = typeof Logo01 | typeof Logo02;
  const Logos: LogoType[] = [
    Logo01, Logo02, Logo03, Logo04, Logo05, Logo06, Logo07, Logo08, Logo09, Logo10,
    Logo11, Logo12, Logo13, Logo14, Logo15, Logo16, Logo17, Logo18, Logo19, Logo20,
    Logo21, Logo22,
  ];

  const LogoNames: string[] = [
    "전체", "디자인 문구", "푸드", "출판", "영화·비디오",
    "보드게임·RPG", "캐릭터·굿즈", "향수·뷰티", "디자인", "공연",
    "디지털 게임", "홈·리빙", "의류", "예술",
    "웹툰·만화", "테크·가전", "잡화", "사진",
    "웹툰 리소스", "반려동물", "주얼리", "음악"
  ];

  return (
    <section className="categoryBox">
      <ul className="categoryContainer">
        {Logos.map((Logo, index) => (
          <li className="categoryContainer__item" key={Logo.name}>
            <Logo />
            <span>{LogoNames[index]}</span>
          </li>
        ))}
      </ul>
    </section>
  );
}

export default CategoryBox;