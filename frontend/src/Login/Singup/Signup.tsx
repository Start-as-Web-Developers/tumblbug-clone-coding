import React, { useState, useEffect } from "react";
import Logo from "../../TumblbugLogo/Logo";
import "./Signup.scss";
import "../LoginForm/LoginForm.scss";

function SignupBox() {
  return (
    <section className="SignupSection">
      <div className="SignupImage" />
      <section className="SignupBox">
        <Logo />
        <h2 className="SignupTitle">이메일로 가입하기</h2>
        <form className="SignupForm">
          <label htmlFor="username" className="SignupLabel">
            <p className="LoginLabel">이름</p>
            <input
              type="text"
              id="username"
              className="LoginInput"
              placeholder="사용하실 이름을 입력해주세요."
            />
          </label>
          <label htmlFor="useremail" className="SignupLabel">
            <p className="LoginLabel">이메일 주소</p>
            <input
              type="email"
              id="useremail"
              className="LoginInput"
              placeholder="이메일 주소를 입력해주세요."
            />
            <input
              type="email"
              id="useremailConfirm"
              className="LoginInput"
              placeholder="이메일 주소를 확인합니다."
            />
          </label>
          <label htmlFor="userpassword" className="SignupLabel">
            <p className="LoginLabel">비밀번호</p>
            <input
              type="password"
              id="userpassword"
              className="LoginInput"
              placeholder="비밀번호를 입력해주세요."
            />
            <input
              type="password"
              id="UserPasswordConfirm"
              className="LoginInput"
              placeholder="비밀번호를 확인합니다."
            />
          </label>
          <div className="SignupLabel">
            <label htmlFor="SignupAcceptAll" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptAll"
                className="LoginCheck"
              />
              <span className="SignupAcceptLabel">전체동의</span>
            </label>
            <hr />
            <label htmlFor="SignupAcceptAge" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptAge"
                className="LoginCheck"
              />
              <span className="SignupAcceptLabel">
                만 14세 이상입니다. (필수)
              </span>
            </label>
            <label htmlFor="SignupAcceptTerms" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptTerms"
                className="LoginCheck"
              />
              <span className="SignupAcceptLabel">
                텀블벅 이용 약관동의 (필수)
              </span>
            </label>
            <label htmlFor="SignupAcceptPrivacy" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptPrivacy"
                className="LoginCheck"
              />
              <span className="SignupAcceptLabel">
                개인정보 수집 및 이용 동의 (필수)
              </span>
            </label>
            <label htmlFor="SignupAcceptShare" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptShare"
                className="LoginCheck"
              />
              <span className="SignupAcceptLabel">
                개인정보 제 3자 제공 동의 (선택)
              </span>
            </label>
            <label htmlFor="SignupAcceptMarketing" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptMarketing"
                className="LoginCheck"
              />
              <span className="SignupAcceptLabel">
                마케팅 정보 수신 동의 (선택)
              </span>
            </label>
          </div>
          <button type="submit" className="LoginButton">
            가입하기
          </button>
        </form>
        <div className="SignuptoLogin">
          <p className="SignupSuggestion">이미 텀블벅 계정이 있으신가요?</p>
          <a href="https://www.google.co.kr">기존 계정으로 로그인하기</a>
        </div>
        <p className="SignupTransferMessage">또는</p>
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
          카카오로 가입하기
        </button>
        <footer>© 2023 Tumblbug Inc.</footer>
      </section>
    </section>
  );
}

export default SignupBox;
