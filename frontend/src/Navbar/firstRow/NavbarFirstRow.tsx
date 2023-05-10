import React, { useState } from "react";
import { Link } from "react-router-dom";
import Logo from "../../TumblbugLogo/Logo";
import NavbarProfileImage from "./NavbarProfileImage";
import "./navbarFirstRow.scss";

function NavbarFirstRow() {
  const [Logedin, setLogedin] = useState(true);

  return (
    <section className="navbarFirstRow">
      <Logo />
      <div className="navbarFirstRow__btnArea">
        <Link to="/project-upload">
          <button type="button" className="navbarFirstRow__upLoadBtn">
            프로젝트 올리기
          </button>
        </Link>
        <div className="navbarFirstRow__loginArea">
          <NavbarProfileImage />
          <button type="button" className="navbarFirstRow__loginBtn">
            {Logedin === true ? (
              <div>
                <button type="button">USERNAME</button>
              </div>
            ) : (
              <Link to="/login">로그인/회원가입</Link>
            )}
          </button>
        </div>
      </div>
    </section>
  );
}

export default NavbarFirstRow;
