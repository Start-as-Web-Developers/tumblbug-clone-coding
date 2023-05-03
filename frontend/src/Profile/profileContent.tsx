import React from "react";
import "./profileContent.scss";
import { FaCog } from "react-icons/fa";

function ProfileContent() {
  return (
    <section className="body">
      <section className="ProfileBox">
        <div className="userProfileImg" />
        <div>
          <h3>
            Username <FaCog />
          </h3>
          <p>Signed up 1 month ago</p>
        </div>
      </section>
      <ul>
        <li>프로젝트</li>
        <li>올린 프로젝트</li>
        <li>후원한 프로젝트</li>
      </ul>
    </section>
  );
}

export default ProfileContent;
