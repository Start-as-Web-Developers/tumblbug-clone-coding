import React from "react";
import "./ProfileMyProject.scss";
import { FaSearch } from "react-icons/fa";

function ProfileMyProject() {
  return (
    <section className="ProfileMyProject">
      <p>
        <span className="ProjectCount">0</span>개의 프로젝트가 있습니다.
      </p>
      <div className="EmptyProject">
        <FaSearch />
        <p>올린 프로젝트가 없습니다.</p>
      </div>
    </section>
  );
}

export default ProfileMyProject;
