import React from "react";
import "./LoginFormKakao.scss";

function LoginFormKakao() {
  return (
    <form className="LoginFormKakao">
      <div>
        <label htmlFor="kakaoemail">
          <p className="LoginLabel">카카오 메일</p>
          <div className="LoginEmailInput">
            <input
              type="text"
              id="kakaoemail"
              className="LoginInput"
              placeholder="카카오 아이디를 입력해주세요"
            />
            <span className="LoginLabelKakao">@ kakao.com</span>
          </div>
        </label>
      </div>
      <div>
        <label htmlFor="password">
          <p className="LoginLabel">비밀번호</p>
          <input
            type="password"
            id="password"
            className="LoginInput"
            placeholder="비밀번호를 입력해주세요"
          />
        </label>
      </div>
      <button type="submit" className="LoginButtonKakao">
        <svg width="34" height="34" viewBox="0 0 34 34" fill="none">
          <path
            fillRule="evenodd"
            clipRule="evenodd"
            d="M17.0002 9.03125C12.0901 9.03125 8.146 12.0938 8.146 15.8415C8.30698 18.4205 9.91683 20.6369 12.2511 21.604L11.4059 24.6666C11.3657 24.7472 11.4059 24.8681 11.4864 24.908C11.5669 24.989 11.7279 24.989 11.8084 24.908L15.3903 22.5309C15.9135 22.6115 16.4367 22.6518 17.0002 22.6518C21.87 22.6518 25.8543 19.5892 25.8543 15.8415C25.8543 12.0938 21.9102 9.03125 17.0002 9.03125Z"
            fill="black"
            fillOpacity="0.9"
          />
        </svg>
        카카오로 로그인
      </button>
    </form>
  );
}

export default LoginFormKakao;
