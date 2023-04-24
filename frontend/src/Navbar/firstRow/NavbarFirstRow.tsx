import React from 'react';
import Logo from "../../TumblbugLogo/Logo";
import NavbarProfileImage from './NavbarProfileImage';
import './navbarFirstRow.scss';

function NavbarFirstRow() {
  return (
    <section className="navbarFirstRow">
      <Logo />
      <div className="navbarFirstRow__btnArea">
        <button type="button" className="navbarFirstRow__upLoadBtn">
          프로젝트 올리기
        </button>
        <div className="navbarFirstRow__loginArea">
          <NavbarProfileImage />
          <button type="button" className="navbarFirstRow__loginBtn">
            로그인/회원가입
          </button>
        </div>
      </div>
    </section>
  );
}

export default NavbarFirstRow;