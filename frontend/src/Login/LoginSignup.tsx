import React, { useState, useEffect, ChangeEvent } from "react";
import LoginBox from "./LoginBox";
import SignupBox from "./Signup";

function EnterPage() {
  const [loginSignup, setLoginSignup] = useState("login");

  const handleLoginClick = () => {
    setLoginSignup("login");
  };

  const handleSignupClick = () => {
    setLoginSignup("signup");
  };

  return (
    <section>
      {loginSignup === "login" && <LoginBox transferBox={handleSignupClick} />}
      {loginSignup === "signup" && <SignupBox transferBox={handleLoginClick} />}
    </section>
  );
}

export default EnterPage;
