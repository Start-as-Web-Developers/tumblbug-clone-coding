import React, { useState, useEffect } from "react";
import "./profileContent.scss";
import { FaCog } from "react-icons/fa";
import { useLocation, Link } from "react-router-dom";
import ProfileIntroduction from "./ProfileIntroduction/ProfileIntroduction";
import ProfileMyProject from "./ProfileMyProject/ProfileMyProject";
import ProfileSupported from "./ProfileSupported/ProfileSupported";

function ProfileContent() {
  const location = useLocation();
  const [activeTab, setActiveTab] = useState("profile");

  useEffect(() => {
    const path = location.pathname;
    if (path === "/u/created") {
      setActiveTab("created");
    } else if (path === "/u/backed") {
      setActiveTab("supported");
    } else if (path === "/u") {
      setActiveTab("profile");
    }
  }, [location.pathname]);

  let content;

  if (activeTab === "profile") {
    content = <ProfileIntroduction />;
  } else if (activeTab === "created") {
    content = <ProfileMyProject />;
  } else if (activeTab === "supported") {
    content = <ProfileSupported />;
  }

  return (
    <section className="body">
      <section className="ProfileBox">
        <div className="userProfileImg" />
        <div>
          <h3 className="ProfileUserName">
            Username <FaCog className="ProfileSettingImg" />
          </h3>
          <p className="ProfileSinceRegister">Signed up 1 month ago</p>
        </div>
      </section>
      <ul className="ProfileTabs">
        <li
          className={
            activeTab === "profile" ? "ProfileTab ChosenTab" : "ProfileTab"
          }
        >
          <Link to="/u">프로필</Link>
        </li>
        <li
          className={
            activeTab === "created" ? "ProfileTab ChosenTab" : "ProfileTab"
          }
        >
          <Link to="/u/created">올린 프로젝트</Link>
        </li>
        <li
          className={
            activeTab === "supported" ? "ProfileTab ChosenTab" : "ProfileTab"
          }
        >
          <Link to="/u/backed">후원한 프로젝트</Link>
        </li>
      </ul>
      <hr />
      {content}
    </section>
  );
}

export default ProfileContent;
