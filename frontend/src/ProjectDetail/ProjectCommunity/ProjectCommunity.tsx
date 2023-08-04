import React, { useState } from "react";
import { Link } from "react-router-dom";
import { FaPencilAlt } from "react-icons/fa";
import NavbarProfileImage from "../../Navbar/firstRow/NavbarProfileImage";
import "./ProjectCommunity.scss";

function ProjectCommunity() {
  const [Logedin, setLogedin] = useState(false);

  const communityComments = [
    {
      communityId: 1,
      project: null,
      user: {
        userIdx: 1,
        userName: "name",
        userId: "id",
        userPassword: null,
        userEmail: "email",
        greeting: "greeting",
        userImg: "img",
        lastLogin: null,
        active: false,
      },
      comment: "너무이쁘다 기대듕듕",
      writeDate: "2023-05-17 17:16:12",
      modiDate: "2023-05-17 17:16:14",
    },
    {
      communityId: 2,
      project: null,
      user: {
        userIdx: 23,
        userName: "사용자123",
        userId: "user123",
        userPassword: null,
        userEmail: "asdf@naver.com",
        greeting: "ASDF입니다",
        userImg: "img",
        lastLogin: null,
        active: false,
      },
      comment: "그렇게 이쁘진 않은 듯",
      writeDate: "2023-05-20 13:33:24",
      modiDate: null,
    },
  ];

  return (
    <section className="ProjectCommunity">
      <div
        className={
          Logedin
            ? "ProjectCommunityCommentBox"
            : "ProjectCommunityCommentBox Unavailable"
        }
      >
        {Logedin ? <Link to="/">댓글 쓰기</Link> : "로그인 해주세요."}
        <FaPencilAlt />
      </div>
      <section className="ProjectCommunityCommentContainer">
        {communityComments.map((comment, index) => (
          <section
            className="ProjectCommunityCommentWrapper"
            key={comment.communityId}
          >
            <section className="ProjectCommunityWriter">
              <NavbarProfileImage />
              <div className="ProjectCommunityNameBody">
                <h5>{comment.user.userName}</h5>
              </div>
            </section>
            <article className="ProjectCommunityContent">
              {comment.comment}
            </article>
            <footer className="ProjectCommentFooter">
              <div className="ProjectCommentDateWrapper">
                {comment.writeDate}
                {comment.modiDate !== null && (
                  <span className="ProjectCommentModified">수정됨</span>
                )}
              </div>
            </footer>
            {index !== communityComments.length - 1 && (
              <hr className="ProjectCommunityHr" />
            )}
          </section>
        ))}
      </section>
    </section>
  );
}

export default ProjectCommunity;
