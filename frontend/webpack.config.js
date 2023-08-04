module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://zangsu-backend.store",
        changeOrigin: true,
        secure: false,
      },
    },
    https: {
      key: "./localhost+1-key.pem", // 생성된 파일의 이름을 입력해주세요
      cert: "./localhost+1.pem",
    },
  },
  module: {
    rules: [
      {
        test: /\.scss$/,
        use: ["style-loader", "css-loader", "sass-loader"],
      },
    ],
  },
};
