import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LoginBox from './Login/LoginBox';
import Main from './Main/Main';

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Main/>
    }, 
    {
      path: "/login",
      element: <LoginBox/>
    }
  ])

  return (
    <RouterProvider router={router}/>
  );
}

export default App;
