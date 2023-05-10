import React from "react";
import { Link } from "react-router-dom";
import "./MyDropdown.scss";

function MyDropdown() {
  return (
    <div className="ProfileMenu">
      <ul>
        <li>
          <Link to="/u">프로필</Link>
        </li>
        <hr />
        <li>후원현황</li>
        <li>관심 프로젝트</li>
        <hr />
        <li>내가 만든 프로젝트</li>
        <li>설정</li>
        <hr />
        <li>로그아웃</li>
      </ul>
    </div>
  );
}

export default MyDropdown;
