import React, { useState, useEffect } from "react";
import Logo from "../TumblbugLogo/Logo";
import LoginFormKakao from "./LoginFormKakao/LoginFormKakao";
import LoginForm from "./LoginForm/LoginForm";
import LoginTransfer from "./LoginTransfer/LoginTransfer";
import "./LoginBox.scss";

interface LoginProps {
  transferBox: (singinup: string) => void;
}

function LoginBox(props: LoginProps) {
  const [loginMethod, setLoginMethod] = useState<string>("kakao");

  const handleTransferClick = (title: string) => {
    setLoginMethod(title);
  };

  const clickTransfer = (signinup: string) => {
    const { transferBox } = props;
    transferBox(signinup);
  };

  return (
    <section className="LoginSection">
      <section className="LoginBox">
        <Logo />
        <h2 className="LoginTitle">
          {loginMethod === "kakao" && "카카오로 로그인"}
          {loginMethod === "email" && "이메일로 로그인"}
        </h2>
        {loginMethod === "kakao" && <LoginFormKakao />}
        {loginMethod === "email" && <LoginForm />}
        <p className="LoginTransferMessage">다른 방법으로 로그인</p>
        <LoginTransfer transferLogin={handleTransferClick} />

        <p className="LoginSuggestion">
          아직 텀블벅 계정이 없으신가요?
          <button
            type="button"
            className="LoginSuggestionLink"
            onClick={() => clickTransfer("login")}
          >
            회원가입
          </button>
        </p>
        <p className="LoginSuggestion">
          혹시 비밀번호를 잊으셨나요?
          <a href="https://www.google.co.kr">비밀번호 재설정</a>
        </p>
      </section>
      {loginMethod === "kakao" && <div className="LoginImageKakao" />}
      {loginMethod === "email" && <div className="LoginImage" />}
    </section>
  );
}

export default LoginBox;
