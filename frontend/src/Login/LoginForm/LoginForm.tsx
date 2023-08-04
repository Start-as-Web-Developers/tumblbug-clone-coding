import React, { useState, ChangeEvent, FormEvent } from "react";
import "./LoginForm.scss";

function LoginForm() {
  const [loginId, setLoginId] = useState("");
  const [loginPassword, setLoginPassword] = useState("");

  const handleLoginId = (event: ChangeEvent<HTMLInputElement>) => {
    const loginIdValue = event.target.value;
    setLoginId(loginIdValue);
  };
  const handleLoginPassword = (event: ChangeEvent<HTMLInputElement>) => {
    const loginPasswordValue = event.target.value;
    setLoginPassword(loginPasswordValue);
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const userLogin = {
      userId: loginId,
      userPassword: loginPassword,
    };

    fetch("https://zangsu-backend.store/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Content-Length": String(JSON.stringify(userLogin).length),
      },
      body: JSON.stringify(userLogin),
      credentials: "include",
    })
      .then((response) => {
        if (response.ok) {
          console.log("Login Success");
          return fetch("https://zangsu-backend.store/user", {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
            credentials: "include",
          });
        }
        console.log("Login Failed");
        return response;
      })
      .then((dataResponse) => {
        if (dataResponse.ok) {
          return dataResponse.json();
        }
        throw new Error("GET 요청이 실패했습니다.");
      })
      .then((data) => {
        // 응답 데이터 처리
        console.log(data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <form className="LoginForm" onSubmit={handleSubmit}>
      <div>
        <label htmlFor="username">
          <p className="LoginLabel">아이디</p>
          <input
            type="text"
            id="username"
            className="LoginInput"
            placeholder="아이디를 입력해주세요"
            value={loginId}
            onChange={handleLoginId}
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
            value={loginPassword}
            onChange={handleLoginPassword}
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
