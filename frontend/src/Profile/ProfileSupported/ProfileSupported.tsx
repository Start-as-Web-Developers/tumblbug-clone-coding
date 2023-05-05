import React from "react";
import "./ProfileSupported.scss";
import { FaSearch } from "react-icons/fa";

function ProfileSupported() {
  return (
    <section className="ProfileMyProject">
      <p>
        <span className="ProjectCount">0</span>개의 프로젝트가 있습니다.
      </p>
      <div className="EmptyProject">
        <FaSearch />
        <p>후원한 프로젝트가 없습니다.</p>
      </div>
    </section>
  );
}

export default ProfileSupported;
