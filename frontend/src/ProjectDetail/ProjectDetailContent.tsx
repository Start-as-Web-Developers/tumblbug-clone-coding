import React, { useState, useEffect } from "react";
import { useLocation, Link } from "react-router-dom";
import "./projectDetailContent.scss";
import ProjectStory from "./ProjectStory/ProjectStory";
import ProjectCreator from "./ProjectCreator/ProjectCreator";
import ProjectPrice from "./ProjectPrice/ProjectPrice";

function ProjectDetailContent() {
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

  const content = <ProjectStory />;

  //   if (activeTab === "profile") {
  //     content = <ProfileIntroduction />;
  //   } else if (activeTab === "created") {
  //     content = <ProfileMyProject />;
  //   } else if (activeTab === "supported") {
  //     content = <ProfileSupported />;
  //   }

  return (
    <section className="ProjectDetailContent">
      <ul className="ProjectTabs">
        <li
          className={
            activeTab === "profile" ? "ProjectTab ChosenTab" : "ProjectTab"
          }
        >
          <Link to="/project-detail/story">프로젝트 계획</Link>
        </li>
        <li
          className={
            activeTab === "created" ? "ProjectTab ChosenTab" : "ProjectTab"
          }
        >
          <Link to="/project-detail/community/creator">업데이트</Link>
        </li>
        <li
          className={
            activeTab === "supported" ? "ProjectTab ChosenTab" : "ProjectTab"
          }
        >
          <Link to="/project-detail/community/backer">커뮤니티</Link>
        </li>
      </ul>
      <section className="ProjectDetailBody">
        {content}
        <section className="ProjectDetailSide">
          <ProjectCreator />
          <ProjectPrice />
        </section>
      </section>
    </section>
  );
}

export default ProjectDetailContent;
