import React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LoginBox from "./Login/LoginBox";
import SignupForm from "./Login/Singup/Signup";
import Main from "./Main/Main";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Main />,
    },
    {
      path: "/login",
      element: <LoginBox />,
    },
    {
      path: "/register",
      element: <SignupForm />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
