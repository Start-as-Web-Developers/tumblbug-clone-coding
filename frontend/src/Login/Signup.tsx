import React, { useState, useEffect, ChangeEvent } from "react";
import Logo from "../TumblbugLogo/Logo";
import "./Signup.scss";
import "./LoginForm/LoginForm.scss";

interface LoginProps {
  transferBox: (singinup: string) => void;
}

function SignupBox(props: LoginProps) {
  const [allChecked, setAllChecked] = useState(false);
  const [checkboxes, setCheckboxes] = useState([
    { id: 1, checked: false, required: true },
    { id: 2, checked: false, required: true },
    { id: 3, checked: false, required: true },
    { id: 4, checked: false, required: false },
    { id: 5, checked: false, required: false },
  ]);
  const [showMessage, setShowMessage] = useState(false);
  const [email, setEmail] = useState("");
  const [emailConfirm, setEmailConfirm] = useState("");
  const [isValidEmail, setIsValidEmail] = useState(true);
  const [isValidEmailConfirm, setIsValidEmailConfirm] = useState(true);
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [isValidPassword, setIsValidPassword] = useState(true);
  const [isValidPasswordConfirm, setIsValidPasswordConfirm] = useState(true);

  const handleAllChecked = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { checked } = event.target;
    setAllChecked(checked);
    setCheckboxes((prevCheckboxes) =>
      prevCheckboxes.map((checkbox) => ({ ...checkbox, checked }))
    );
  };

  const handleCheckboxChecked =
    (id: number) => (event: React.ChangeEvent<HTMLInputElement>) => {
      const { checked } = event.target;
      setCheckboxes((prevCheckboxes) =>
        prevCheckboxes.map((checkbox) =>
          checkbox.id === id ? { ...checkbox, checked } : checkbox
        )
      );
      setAllChecked(
        (prevAllChecked) =>
          prevAllChecked ||
          (checkboxes.filter(
            (checkbox) => checkbox.required && !checkbox.checked
          ).length === 0 &&
            checked)
      );
    };

  useEffect(() => {
    const hasUncheckedRequired = checkboxes.some(
      (checkbox) => checkbox.required && !checkbox.checked
    );
    setShowMessage(hasUncheckedRequired);
  }, [checkboxes]);

  const handleEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    const emailValue = event.target.value;
    setEmail(emailValue);

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    setIsValidEmail(emailRegex.test(emailValue));
  };
  const handleEmailConfirmChange = (event: ChangeEvent<HTMLInputElement>) => {
    const emailConfirmValue = event.target.value;
    setEmailConfirm(emailConfirmValue);

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    setIsValidEmailConfirm(emailRegex.test(emailConfirmValue));
  };
  const handlePasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
    const passwordValue = event.target.value;
    setPassword(passwordValue);

    const passwordRegex = /^.{6,20}$/;
    setIsValidPassword(passwordRegex.test(passwordValue));
  };
  const handlePasswordConfirmChange = (
    event: ChangeEvent<HTMLInputElement>
  ) => {
    const passwordConfirmValue = event.target.value;
    setPasswordConfirm(passwordConfirmValue);

    const passwordRegex = /^.{6,20}$/;
    setIsValidPasswordConfirm(passwordRegex.test(passwordConfirmValue));
  };

  const clickTransfer = (signinup: string) => {
    const { transferBox } = props;
    transferBox(signinup);
  };

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
              className={`LoginInput ${!isValidEmail && "InvalidInput"}`}
              placeholder="이메일 주소를 입력해주세요."
              value={email}
              onChange={handleEmailChange}
            />
            {!isValidEmail && (
              <p className="WarningMessage">유효하지 않은 이메일 형식입니다.</p>
            )}
            <input
              type="email"
              id="useremailConfirm"
              className={`LoginInput ${!isValidEmailConfirm && "InvalidInput"}`}
              placeholder="이메일 주소를 확인합니다."
              value={emailConfirm}
              onChange={handleEmailConfirmChange}
            />
            {!isValidEmailConfirm && (
              <p className="WarningMessage">유효하지 않은 이메일 형식입니다.</p>
            )}
          </label>
          <label htmlFor="userpassword" className="SignupLabel">
            <p className="LoginLabel">비밀번호</p>
            <input
              type="password"
              id="userpassword"
              className={`LoginInput ${!isValidPassword && "InvalidInput"}`}
              placeholder="비밀번호를 입력해주세요."
              value={password}
              onChange={handlePasswordChange}
            />
            {!isValidPassword && (
              <p className="WarningMessage">
                비밀번호는 6자 이상, 20자 이하로 입력하세요.
              </p>
            )}
            <input
              type="password"
              id="UserPasswordConfirm"
              className={`LoginInput ${
                !isValidPasswordConfirm && "InvalidInput"
              }`}
              placeholder="비밀번호를 확인합니다."
              value={passwordConfirm}
              onChange={handlePasswordConfirmChange}
            />
            {!isValidPasswordConfirm && (
              <p className="WarningMessage">
                비밀번호는 6자 이상, 20자 이하로 입력하세요.
              </p>
            )}
          </label>
          <div className="SignupLabel">
            <label htmlFor="SignupAcceptAll" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptAll"
                className="LoginCheck"
                checked={allChecked}
                onChange={handleAllChecked}
              />
              <span className="SignupAcceptLabel">전체동의</span>
            </label>
            <hr />
            <label htmlFor="SignupAcceptAge" className="SignupCheckbox">
              <input
                type="checkbox"
                id="SignupAcceptAge"
                className="LoginCheck"
                checked={checkboxes[0].checked}
                onChange={handleCheckboxChecked(1)}
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
                checked={checkboxes[1].checked}
                onChange={handleCheckboxChecked(2)}
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
                checked={checkboxes[2].checked}
                onChange={handleCheckboxChecked(3)}
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
                checked={checkboxes[3].checked}
                onChange={handleCheckboxChecked(4)}
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
                checked={checkboxes[4].checked}
                onChange={handleCheckboxChecked(5)}
              />
              <span className="SignupAcceptLabel">
                마케팅 정보 수신 동의 (선택)
              </span>
            </label>
            {showMessage && (
              <p className="RequireCheckMessage">
                필수 동의 내용을 체크해주세요.
              </p>
            )}
          </div>
          <button type="submit" className="LoginButton">
            가입하기
          </button>
        </form>
        <div className="SignuptoLogin">
          <p className="SignupSuggestion">이미 텀블벅 계정이 있으신가요?</p>
          <button
            type="button"
            className="LoginSuggestionLink"
            onClick={() => clickTransfer("login")}
          >
            기존 계정으로 로그인하기
          </button>
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
