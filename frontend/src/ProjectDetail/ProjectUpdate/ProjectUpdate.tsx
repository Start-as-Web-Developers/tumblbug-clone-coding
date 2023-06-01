import React from "react";
import NavbarProfileImage from "../../Navbar/firstRow/NavbarProfileImage";
import "./ProjectUpdate.scss";

function ProjectUpdate() {
  const creatorName = "정태훈";

  const Updates = [
    {
      content: "안녕하세요, 케이엘피코리아입니다.\n\n...",
      modified: false,
      updateDate: "2023-04-23",
      totalComment: 2,
      updateId: 37,
    },
    {
      content:
        "대통령은 취임에 즈음하여 다음의 선서를 한다. 국교는 인정되지 아니하며, 종교와 정치는 분리된다. 신체장애자 및 질병·노령 기타의 사유로 생활능력이 없는 국민은 법률이 정하는 바에 의하여 국가의 보호를 받는다.\n\n국무회의는 정부의 권한에 속하는 중요한 정책을 심의한다. 군인은 현역을 면한 후가 아니면 국무총리로 임명될 수 없다. 국회나 그 위원회의 요구가 있을 때에는 국무총리·국무위원 또는 정부위원은 출석·답변하여야 하며, 국무총리 또는 국무위원이 출석요구를 받은 때에는 국무위원 또는 정부위원으로 하여금 출석·답변하게 할 수 있다.",
      modified: true,
      updateDate: "2023-05-23",
      totalComment: 0,
      updateId: 2,
    },
  ];

  return (
    <section className="ProjectUpdate">
      {Updates.map((update, index) => (
        <section className="ProjectUpdateWrapper" key={update.updateDate}>
          <section className="ProjectUpdateWriter">
            <NavbarProfileImage />
            <div className="ProjectCreatorNameBody">
              <h5>{creatorName}</h5>
            </div>
          </section>
          <section className="ProjectUpdateBody">
            <article className="ProjectUpdateContent">{update.content}</article>
          </section>
          <footer className="ProjectUpdateFooter">
            <div className="ProjectUpdateDateWrapper">
              {update.updateDate}
              {update.modified === true && (
                <span className="ProjectUpdateModified">수정됨</span>
              )}
            </div>
            <div className="ProjectUpdateCommentWrapper">
              <span className="ProjectUpdateComment">
                {update.totalComment > 0
                  ? `댓글 ${update.totalComment}개`
                  : "댓글쓰기"}
              </span>
            </div>
          </footer>
          {index !== Updates.length - 1 && <hr className="ProjectUpdateHr" />}
        </section>
      ))}
    </section>
  );
}

export default ProjectUpdate;
