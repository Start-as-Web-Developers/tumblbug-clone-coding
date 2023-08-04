import React from "react";
import { FaEnvelope } from "react-icons/fa";
import NavbarProfileImage from "../../Navbar/firstRow/NavbarProfileImage";
import "./ProjectCreator.scss";

function ProjectCreator() {
  const creatorName = "정태훈";
  const lastLogin = new Date().toISOString();
  const creatorIntro =
    "멀듯이, 위에 하나에 시와 청춘이 나의 하나 쓸쓸함과 별 거외다. 묻힌 것은 오면 마리아 별 불러 계집애들의 별 있습니다. 지나고 청춘이 내 봅니다.";
  const creatorEmail = "hoonystory98@gmail.com";

  return (
    <div className="ProjectCreator">
      <h4>창작자 소개</h4>
      <div className="ProjectCreatorName">
        <NavbarProfileImage />
        <div className="ProjectCreatorNameBody">
          <h5>{creatorName}</h5>
          <p>{lastLogin}</p>
        </div>
      </div>
      <div className="ProjectCreatorIntro">{creatorIntro}</div>
      <a href={`mailto:${creatorEmail}`} className="ProjectCreatorButton">
        <FaEnvelope />
        창작자 문의
      </a>
    </div>
  );
}

export default ProjectCreator;
