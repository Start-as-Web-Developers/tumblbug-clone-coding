import React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LoginBox from "./Login/LoginBox";
import Main from "./Main/Main";
import ProjectUpload from "./ProjectUpload/ProjectUpload";
import SignupForm from "./Login/Singup/Signup";
import Profile from "./Profile/profile";

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
      path: "/project-upload",
      element: <ProjectUpload />,
    },
    {
      path: "/register",
      element: <SignupForm />,
    },
    {
      path: "/u",
      element: <Profile />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
