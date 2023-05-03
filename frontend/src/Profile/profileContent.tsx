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
        <li>������Ʈ</li>
        <li>�ø� ������Ʈ</li>
        <li>�Ŀ��� ������Ʈ</li>
      </ul>
    </section>
  );
}

export default ProfileContent;
