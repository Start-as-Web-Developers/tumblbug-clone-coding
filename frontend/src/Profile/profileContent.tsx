import React, { useState, useEffect } from "react";
import "./profileContent.scss";
import { FaCog } from "react-icons/fa";
import { Route, Routes, useLocation, Link } from "react-router-dom";
import ProfileIntroduction from "./ProfileIntroduction/ProfileIntroduction";
import ProfileMyProject from "./ProfileMyProject/ProfileMyProject";
import ProfileSupported from "./ProfileSupported/ProfileSupported";

function ProfileContent() {
  const location = useLocation();
  const [activeTab, setActiveTab] = useState("profile");

  useEffect(() => {
    const path = location.pathname;
    if (path === "/created") {
      setActiveTab("uploadedProjects");
    } else if (path === "/backed") {
      setActiveTab("supportedProjects");
    } else if (path === "/u") {
      setActiveTab("profile");
    }
  }, [location.pathname]);

  let content;

  if (activeTab === "profile") {
    content = <ProfileIntroduction />;
  } else if (activeTab === "created") {
    content = <ProfileMyProject />;
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
          <Link to="/">프로필</Link>
        </li>
        <li
          className={
            activeTab === "uploadedProjects"
              ? "ProfileTab ChosenTab"
              : "ProfileTab"
          }
        >
          <Link to="/created">올린 프로젝트</Link>
        </li>
        <li
          className={
            activeTab === "supportedProjects"
              ? "ProfileTab ChosenTab"
              : "ProfileTab"
          }
        >
          <Link to="/backed">후원한 프로젝트</Link>
        </li>
      </ul>
      <hr />
      <div>
        <ProfileIntroduction />
      </div>
    </section>
  );
}

export default ProfileContent;
