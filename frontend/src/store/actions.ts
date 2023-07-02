import { LOGIN, LOGOUT } from "./actionTypes";

interface loginData {
  userId: string;
  userPassword: string;
}

interface userInfo {
  name: string;
}

export const login = (user: userInfo) => ({
  type: LOGIN,
  payload: user,
});
export const logout = () => ({
  type: LOGOUT,
});
