import React from "react";
import "./LoginForm.scss";

function LoginForm() {
  return (
    <form className="LoginForm">
      <div>
        <label htmlFor="username">
          <p className="LoginLabel">이메일 주소</p>
          <input
            type="text"
            id="username"
            className="LoginInput"
            placeholder="이메일 주소를 입력해주세요"
          />
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
      <button type="submit" className="LoginButton">
        로그인
      </button>
    </form>
  );
}

export default LoginForm;
