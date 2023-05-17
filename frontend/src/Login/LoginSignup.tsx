import React, { useState, useEffect, useRef } from "react";
import LoginContainer from "./LoginBox";
import SignupContainer from "./Signup";
import "./LoginSignup.scss";
import { $, changeCSS } from "../utils/commonFunction";

function EnterPage() {
  const [loginSignup, setLoginSignup] = useState("login");
  const containerRef = useRef(null);

  const handleLoginClick = () => {
    setLoginSignup("login");
  };

  useEffect(() => {
    const $FormBox = $(".FormContainer") as HTMLElement;
    changeCSS($FormBox, "overflow-y", "scroll");

    const scrollToPosition =
      loginSignup === "signup" ? $FormBox.scrollHeight : 0;

    $FormBox.scrollTo({
      top: scrollToPosition,
      behavior: "smooth",
    });

    setTimeout(() => {
      changeCSS($FormBox, "overflow-y", "hidden");
    }, 500);
  }, [loginSignup]);

  const handleSignupClick = () => {
    setLoginSignup("signup");
  };

  return (
    // <section>
    //   {loginSignup === "login" && <LoginBox transferBox={handleSignupClick} />}
    //   {loginSignup === "signup" && <SignupBox transferBox={handleLoginClick} />}
    // </section>
    <section className="FormContainer" ref={containerRef}>
      <LoginContainer transferBox={handleSignupClick} />
      <SignupContainer transferBox={handleLoginClick} />
    </section>
  );
}

export default EnterPage;
