import React from "react";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Main from "./Main/Main";
import ProjectUpload from "./ProjectUpload/ProjectUpload";
import Profile from "./Profile/profile";
import ProjectDetail from "./ProjectDetail/ProjectDetail";
import EnterPage from "./Login/LoginSignup";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Main />,
    },
    {
      path: "/login",
      element: <EnterPage />,
    },
    {
      path: "/project-upload",
      element: <ProjectUpload />,
    },
    {
      path: "/u/*",
      element: <Profile />,
    },
    {
      path: "/project-detail/*",
      element: <ProjectDetail />,
    },
  ]);

  return <RouterProvider router={router} />;
}

export default App;
