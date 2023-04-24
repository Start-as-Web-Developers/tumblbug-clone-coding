import React from "react";
import Logo from "../TumblbugLogo/Logo";
import LoginForm from "./LoginForm/LoginForm";
import LoginFormKakao from "./LoginFormKakao/LoginFormKakao";
import LoginTransfer from "./LoginTransfer/LoginTransfer";
import "./LoginBox.scss";

function LoginBox() {
  return (
    // 이메일로 로그인을 위한 부분
    // <section className="LoginSection">
    //   <section className="LoginBox">
    //     <Logo />
    //     <h2 className="LoginTitle">이메일로 로그인</h2>
    //     <LoginForm />
    //     <p className="LoginTransferMessage">다른 방법으로 로그인</p>
    //     <LoginTransfer />
    //     <p className="LoginSuggestion">
    //       아직 텀블벅 계정이 없으신가요?
    //       <a href="https://www.google.co.kr">회원가입</a>
    //     </p>
    //     <p className="LoginSuggestion">
    //       혹시 비밀번호를 잊으셨나요?
    //       <a href="https://www.google.co.kr">비밀번호 재설정</a>
    //     </p>
    //   </section>
    //   <div className="LoginImage" />
    // </section>

    // 카카오로 로그인을 위한 부분
    <section className="LoginSection">
      <section className="LoginBox">
        <Logo />
        <h2 className="LoginTitle">카카오로 로그인</h2>
        <LoginFormKakao />
        <p className="LoginTransferMessage">다른 방법으로 로그인</p>
        <LoginTransfer />

        <p className="LoginSuggestion">
          아직 텀블벅 계정이 없으신가요?
          <a href="https://www.google.co.kr">회원가입</a>
        </p>
      </section>
      <div className="LoginImageKakao" />
    </section>
  );
}

export default LoginBox;
