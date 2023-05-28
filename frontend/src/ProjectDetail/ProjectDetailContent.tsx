import React, { useState, useEffect } from "react";
import { useLocation, Link } from "react-router-dom";
import "./projectDetailContent.scss";
import ProjectStory from "./ProjectStory/ProjectStory";
import ProjectUpdate from "./ProjectUpdate/ProjectUpdate";
import ProjectCommunity from "./ProjectCommunity/ProjectCommunity";
import ProjectCreator from "./ProjectCreator/ProjectCreator";
import ProjectPrice from "./ProjectPrice/ProjectPrice";

function ProjectDetailContent() {
  const location = useLocation();
  const [activeTab, setActiveTab] = useState("story");

  useEffect(() => {
    const path = location.pathname;
    if (path === "/project-detail/community/creator") {
      setActiveTab("update");
    } else if (path === "/project-detail/community/backer") {
      setActiveTab("community");
    } else if (path === "/project-detail") {
      setActiveTab("story");
    }
  }, [location.pathname]);

  let content;

  if (activeTab === "story") {
    content = <ProjectStory />;
  } else if (activeTab === "update") {
    content = <ProjectUpdate />;
  } else if (activeTab === "community") {
    content = <ProjectCommunity />;
  }

  return (
    <section className="ProjectDetailContent">
      <ul className="ProjectTabs">
        <li
          className={
            activeTab === "story" ? "ProjectTab ChosenTab" : "ProjectTab"
          }
        >
          <Link to="/project-detail">프로젝트 계획</Link>
        </li>
        <li
          className={
            activeTab === "update" ? "ProjectTab ChosenTab" : "ProjectTab"
          }
        >
          <Link to="/project-detail/community/creator">업데이트</Link>
        </li>
        <li
          className={
            activeTab === "community" ? "ProjectTab ChosenTab" : "ProjectTab"
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
